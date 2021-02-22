package cn.qkmango.hm.homework.web.controller;

import cn.qkmango.hm.exception.HomeworkException;
import cn.qkmango.hm.homework.domain.Course;
import cn.qkmango.hm.homework.domain.Homework;
import cn.qkmango.hm.homework.service.HomeworkService;
import cn.qkmango.hm.homework.service.impl.HomeworkServiceImpl;
import cn.qkmango.hm.utils.PrintJson;
import cn.qkmango.hm.utils.ServiceFactory;
import cn.qkmango.hm.utils.UUIDUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @Description: //TODO
 * <p>类简介</p>
 * <p>类详细介绍</p>
 * @className HomeworkControllerHomeworkControllerController
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
        }
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

        homework.setId(UUIDUtil.getUUID());
        homework.setTitle(request.getParameter("title"));
        homework.setCourse(request.getParameter("course"));
        homework.setDeadline(request.getParameter("deadline"));
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
