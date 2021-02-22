package cn.qkmango.hm.homework.web.controller;

import cn.qkmango.hm.homework.domain.Course;
import cn.qkmango.hm.homework.service.HomeworkService;
import cn.qkmango.hm.homework.service.impl.HomeworkServiceImpl;
import cn.qkmango.hm.utils.PrintJson;
import cn.qkmango.hm.utils.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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
        }
    }

    private void getCourseList(HttpServletRequest request, HttpServletResponse response) {
        HomeworkService hs = (HomeworkService) ServiceFactory.getService(new HomeworkServiceImpl());
        List<Course> courseList = hs.getCourseList();
        PrintJson.printJsonObj(response,courseList);
    }

}
