package cn.qkmango.hm.homework.web.controller;

import cn.qkmango.hm.homework.domain.CommitHomework;
import cn.qkmango.hm.homework.domain.Course;
import cn.qkmango.hm.homework.domain.Format;
import cn.qkmango.hm.homework.domain.Homework;
import cn.qkmango.hm.homework.service.CommitHomeworkService;
import cn.qkmango.hm.homework.service.FormatService;
import cn.qkmango.hm.homework.service.HomeworkService;
import cn.qkmango.hm.homework.service.impl.CommitHomeworkServiceImpl;
import cn.qkmango.hm.homework.service.impl.FormatServiceImpl;
import cn.qkmango.hm.homework.service.impl.HomeworkServiceImpl;
import cn.qkmango.hm.system.domain.User;
import cn.qkmango.hm.utils.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * <p>HomeworkController</p>
 * <p>作业控制器，关于 作业的获取、作业的提交等等都由此控制器控制转发</p>
 * @className HomeworkController
 * @author: Mango
 * @date: 2021-02-22 10:06
 */
public class HomeworkController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();

        System.out.println("进入到HomeworkController");

        if ("/homework/getCourseList.do".equals(path)) {
            getCourseList(request,response);
        } else if ("/homework/getHomeworkById.do".equals(path)) {
            getHomeworkById(request,response);
        }  else if ("/homework/getHomeworkPageList.do".equals(path)) {
            getHomeworkPageList(request,response);
        } else if ("/homework/getHomeworkIsCommit.do".equals(path)) {
            getHomeworkIsCommit(request,response);
        } else if ("/homework/commitHomework.do".equals(path)) {
            commitHomework(request,response);
        } else if ("/homework/deleteCommitHomework.do".equals(path)) {
            deleteCommitHomework(request,response);
        } else if ("/homework/deleteHomework.admin".equals(path)) {
            deleteHomework(request,response);
        } else if ("/homework/getHomeworkByIdOfEdit.admin".equals(path)) {
            getHomeworkByIdOfEdit(request,response);
        } else if ("/homework/editHomework.admin".equals(path)) {
            editHomework(request,response);
        } else if ("/homework/addHomework.admin".equals(path)) {
            addHomework(request,response);
        } else if ("/homework/getHomeworkAndCommitCountPageList.do".equals(path)) {
            getHomeworkAndCommitCountPageList(request,response);
        } else if ("/homework/getFormat.do".equals(path)) {
            getFormat(request,response);
        }
    }

    private void getFormat(HttpServletRequest request, HttpServletResponse response) {
        FormatService fs = (FormatService) ServiceFactory.getService(new FormatServiceImpl());

        List<Format> list = fs.getFormat();

        RespMap<Object> resultMap = new RespMap<>();
        resultMap.putSuccess(true);
        resultMap.putData(list);

        PrintJson.printJsonObj(response,resultMap);


    }


    private void getHomeworkAndCommitCountPageList(HttpServletRequest request, HttpServletResponse response) {

        String  course      = request.getParameter("course");
        String  title       = request.getParameter("title");
        int     page        = Integer.valueOf(request.getParameter("page"));
        int     limit       = Integer.valueOf(request.getParameter("limit"));
        //略过的记录条数
        int     skipCount   = (page-1)*limit;

        Map<String, Object> map = new HashMap<>();
        map.put("course",course);
        map.put("title",title);
        map.put("skipCount",skipCount);
        map.put("limit",limit);

        HomeworkService hs = (HomeworkService) ServiceFactory.getService(new HomeworkServiceImpl());
        RespMap<Object> resultMap = hs.getHomeworkAndCommitCountPageList(map);


        /*
        {
            code:0
            count:1000,
            data:[
                {},
                {},
                {},
                {},
            ]
        }
         */
        resultMap.putSuccess(true);
        PrintJson.printJsonObj(response,resultMap);

    }

    private void editHomework(HttpServletRequest request, HttpServletResponse response) {

        Homework homework = new Homework();

        homework.setId(request.getParameter("id"));
        homework.setTitle(request.getParameter("title"));
        homework.setCourse(request.getParameter("course"));
        homework.setLastCommitDate(request.getParameter("lastCommitDate"));
        homework.setBriefInfo(request.getParameter("briefInfo"));
        homework.setDetailInfo(request.getParameter("detailInfo"));
        homework.setFormat(request.getParameter("format"));

        HomeworkService hs = (HomeworkService) ServiceFactory.getService(new HomeworkServiceImpl());

        HashMap<String, Object> map = null;
        try {
             map = hs.editHomework(homework);
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            PrintJson.printJsonObj(response,map);
        }
    }

    private void getHomeworkByIdOfEdit(HttpServletRequest request, HttpServletResponse response) {

        String hid = request.getParameter("hid");
        HomeworkService hs = (HomeworkService) ServiceFactory.getService(new HomeworkServiceImpl());
        HashMap<String, Object> map = hs.getHomeworkByIdOfEdit(hid);

        PrintJson.printJsonObj(response,map);

    }

    private void deleteHomework(HttpServletRequest request, HttpServletResponse response) {
        String hid = request.getParameter("hid");

        HomeworkService hs = (HomeworkService) ServiceFactory.getService(new HomeworkServiceImpl());

        HashMap<String, Object> map = null;
        try {
            map = hs.deleteHomework(hid);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            PrintJson.printJsonObj(response,map);
        }



    }

    private void deleteCommitHomework(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(false);
        String hid = request.getParameter("hid");
        String uid = ((User) session.getAttribute("user")).getId();

        CommitHomework ch = new CommitHomework();
        ch.setHid(hid);
        ch.setUid(uid);

        CommitHomeworkService chs = (CommitHomeworkService) ServiceFactory.getService(new CommitHomeworkServiceImpl());

        HashMap<String, Object> map = null;
        try {
            map = chs.deleteCommitHomework(ch);
        } catch (Exception e) {
            e.printStackTrace();
        }

        PrintJson.printJsonObj(response,map);

    }

    private void commitHomework(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);

        String uid = ((User) session.getAttribute("user")).getId();
        String filePath = request.getParameter("filePath");
        String hid = request.getParameter("hid");

        CommitHomework ch = new CommitHomework();
        ch.setUid(uid);
        ch.setHid(hid);
        ch.setFilePath(filePath);
        ch.setDatetime(DateTimeUtil.getSysDateTime());

        CommitHomeworkService chs = (CommitHomeworkService) ServiceFactory.getService(new CommitHomeworkServiceImpl());
        HashMap<String, Object> map = chs.commitHomework(ch);

        PrintJson.printJsonObj(response,map);
    }

    private void getHomeworkIsCommit(HttpServletRequest request, HttpServletResponse response) {
        String uid = ((User)request.getSession(false).getAttribute("user")).getId();
        String hid = request.getParameter("hid");
        HomeworkService hs = (HomeworkService) ServiceFactory.getService(new HomeworkServiceImpl());

        boolean flag = hs.getHomeworkIsCommit(uid, hid);

        PrintJson.printJsonFlag(response,flag);

    }

    private void getHomeworkPageList(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        String  course      = request.getParameter("course");
        String  status      = request.getParameter("status");
        String  title       = request.getParameter("title");
        int     page        = Integer.valueOf(request.getParameter("page"));
        int     limit       = Integer.valueOf(request.getParameter("limit"));
        //略过的记录条数
        int     skipCount   = (page-1)*limit;

        Map<String, Object> map = new HashMap<>();
        map.put("uid",user.getId());
        map.put("course",course);
        map.put("status",status);
        map.put("title",title);
        map.put("skipCount",skipCount);
        map.put("limit",limit);

        HomeworkService hs = (HomeworkService) ServiceFactory.getService(new HomeworkServiceImpl());
        RespMap<Object> resultMap = hs.getHomeworkPageList(map);

        resultMap.putSuccess(true);

        /*
        {
            code:0
            count:1000,
            data:[
                {},
                {},
                {},
                {},
            ]
        }
         */
        PrintJson.printJsonObj(response,resultMap);
    }

    private void getHomeworkById(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        HomeworkService hs = (HomeworkService) ServiceFactory.getService(new HomeworkServiceImpl());
        HashMap<String, Object> map = hs.getHomeworkById(id);

        PrintJson.printJsonObj(response,map);
    }

    private void getCourseList(HttpServletRequest request, HttpServletResponse response) {
        HomeworkService hs = (HomeworkService) ServiceFactory.getService(new HomeworkServiceImpl());
        List<Course> courseList = hs.getCourseList();
        PrintJson.printJsonObj(response,courseList);
    }

    private void addHomework(HttpServletRequest request, HttpServletResponse response) {

        Homework homework = new Homework();

        homework.setTitle(request.getParameter("title"));
        homework.setCourse(request.getParameter("course"));
        homework.setLastCommitDate(request.getParameter("lastCommitDate"));
        homework.setCreateDate(DateTimeUtil.getSysDate());
        homework.setBriefInfo(request.getParameter("briefInfo"));
        homework.setDetailInfo(request.getParameter("detailInfo"));
        homework.setFormat(request.getParameter("format"));

        HomeworkService hs = (HomeworkService) ServiceFactory.getService(new HomeworkServiceImpl());

        HashMap<String, Object> map = null;
        try {
            map = hs.addHomeWork(homework);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            PrintJson.printJsonObj(response,map);
        }
    }

}
