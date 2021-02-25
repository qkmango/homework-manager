package cn.qkmango.hm.homework.web.controller;

import cn.qkmango.hm.exception.HomeworkException;
import cn.qkmango.hm.homework.domain.Course;
import cn.qkmango.hm.homework.domain.Homework;
import cn.qkmango.hm.homework.service.HomeworkService;
import cn.qkmango.hm.homework.service.impl.HomeworkServiceImpl;
import cn.qkmango.hm.system.domain.User;
import cn.qkmango.hm.utils.DateTimeUtil;
import cn.qkmango.hm.utils.PrintJson;
import cn.qkmango.hm.utils.ServiceFactory;
import cn.qkmango.hm.utils.UUIDUtil;

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
 * @Description: //TODO
 * <p>类简介</p>
 * <p>类详细介绍</p>
 * @className HomeworkController
 * @author: Mango
 * @date: 2021-02-22 10:06
 */
public class HomeworkController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        if ("/homework/getCourseList.do".equals(path)) {
            getCourseList(request,response);
        } else if ("/homework/getHomeworkById.do".equals(path)) {
            getHomeworkById(request,response);
        } else if ("/homework/addHomework.do".equals(path)) {
            addHomework(request,response);
        } else if ("/homework/getHomeworkPageList.do".equals(path)) {
            getHomeworkPageList(request,response);
        } else if ("/homework/getHomeworkIsCommit.do".equals(path)) {
            getHomeworkIsCommit(request,response);
        } else if ("/homework/commitHomework.do".equals(path)) {
            commitHomework(request,response);
        } else if ("/homework/deleteCommitHomework.do".equals(path)) {
            deleteCommitHomework(request,response);
        }
    }

    private void deleteCommitHomework(HttpServletRequest request, HttpServletResponse response) {
        
    }

    private void commitHomework(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            PrintJson.printJsonFlag(response,false);
            return;
        }

        String uid = ((User) session.getAttribute("user")).getId();
        String fileLink = request.getParameter("fileLink");
        String hid = request.getParameter("hid");

        HashMap<String, String> map = new HashMap<>();
        map.put("uid",uid);
        map.put("hid",hid);
        map.put("fileLink",fileLink);

        HomeworkService hs = (HomeworkService) ServiceFactory.getService(new HomeworkServiceImpl());
        boolean flag = hs.commitHomework(map);

        PrintJson.printJsonFlag(response,flag);
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
        // System.out.println("=======>>>>>>> " + user);

        String  course      = request.getParameter("course");
        String  status      = request.getParameter("status");
        String  title       = request.getParameter("title");
        int     pageNo      = Integer.valueOf(request.getParameter("pageNo"));
        int     pageSize    = Integer.valueOf(request.getParameter("pageSize"));
        //略过的记录条数
        int     skipCount   = (pageNo-1)*pageSize;

        Map<String, Object> map = new HashMap<>();
        map.put("uid",user.getId());
        map.put("course",course);
        map.put("status",status);
        map.put("title",title);
        map.put("skipCount",skipCount);
        map.put("pageSize",pageSize);

        System.out.println("uid="+user.getId());
        System.out.println("course="+course);
        System.out.println("status="+status);
        System.out.println("title="+title);
        System.out.println("skipCount="+skipCount);
        System.out.println("pageSize="+pageSize);

        HomeworkService hs = (HomeworkService) ServiceFactory.getService(new HomeworkServiceImpl());
        Map<String, Object> resultMap = hs.getHomeworkPageList(map);


        /*
        {
            count:1000,
            list:[
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
        Map<String, Object> map = hs.getHomeworkById(id);

        PrintJson.printJsonObj(response,map);
    }

    private void getCourseList(HttpServletRequest request, HttpServletResponse response) {
        HomeworkService hs = (HomeworkService) ServiceFactory.getService(new HomeworkServiceImpl());
        List<Course> courseList = hs.getCourseList();
        PrintJson.printJsonObj(response,courseList);
    }

    private void addHomework(HttpServletRequest request, HttpServletResponse response) {

        Homework homework = new Homework();

        // homework.setId(UUIDUtil.getUUID());
        homework.setTitle(request.getParameter("title"));
        homework.setCourse(request.getParameter("course"));
        homework.setLastCommitDate(request.getParameter("lastCommitDate"));
        homework.setCreateDate(DateTimeUtil.getSysDate());
        homework.setBriefInfo(request.getParameter("briefInfo"));
        homework.setDetailInfo(request.getParameter("detailInfo"));

        System.out.println(homework.getDetailInfo());

        HomeworkService hs = (HomeworkService) ServiceFactory.getService(new HomeworkServiceImpl());

        boolean flag = false;
        try {
            hs.addHomeWork(homework);
            flag = true;
        } catch (HomeworkException e) {
            e.printStackTrace();
        } finally {
            PrintJson.printJsonFlag(response,flag);
        }
    }

}
