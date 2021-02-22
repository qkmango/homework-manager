package cn.qkmango.hm.admin.web.controller;

import cn.qkmango.hm.Exception.HomeworkException;
import cn.qkmango.hm.admin.service.AdminService;
import cn.qkmango.hm.admin.service.impl.AdminServiceImpl;
import cn.qkmango.hm.homework.domain.Homework;
import cn.qkmango.hm.utils.PrintJson;
import cn.qkmango.hm.utils.ServiceFactory;
import cn.qkmango.hm.utils.UUIDUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @version 1.0
 * @Description: //TODO
 * <p>类简介</p>
 * <p>类详细介绍</p>
 * @className AdminController
 * @author: Mango
 * @date: 2021-02-21 18:32
 */
public class AdminController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        if ("/admin/addHomework.do".equals(path)) {
            addHomework(request,response);
        } else if ("".equals(path)) {

        }
    }


    private void addHomework(HttpServletRequest request, HttpServletResponse response) {

        Homework homework = new Homework();

        homework.setId(UUIDUtil.getUUID());
        homework.setTitle(request.getParameter("title"));
        homework.setCourseId(request.getParameter("courseId"));
        homework.setDeadline(request.getParameter("deadline"));
        homework.setBriefInfo(request.getParameter("briefInfo"));
        homework.setDetailInfo(request.getParameter("detailInfo"));

        System.out.println(homework);

        AdminService as = (AdminService) ServiceFactory.getService(new AdminServiceImpl());

        boolean flag = false;
        try {
            as.addHomeWork(homework);
            flag = true;
        } catch (HomeworkException e) {
            e.printStackTrace();
        } finally {
            PrintJson.printJsonFlag(response,flag);
        }


    }
}
