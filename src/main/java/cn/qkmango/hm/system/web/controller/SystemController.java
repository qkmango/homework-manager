package cn.qkmango.hm.system.web.controller;

import cn.qkmango.hm.system.domain.AliOssSts;
import cn.qkmango.hm.system.domain.User;
import cn.qkmango.hm.system.service.impl.OSSServiceImpl;
import cn.qkmango.hm.system.service.UserService;
import cn.qkmango.hm.system.service.impl.UserServiceImpl;
import cn.qkmango.hm.utils.PrintJson;
import cn.qkmango.hm.utils.RespMap;
import cn.qkmango.hm.utils.RespStatusMsg;
import cn.qkmango.hm.utils.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * <p>SystemController</p>
 * <p>系统控制器，相关用户的登陆，阿里OSS相关的服务的请求都由此类进行控制</p>
 * @className UserController
 * @author: Mango
 * @date: 2021-01-26 17:31
 */
public class SystemController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();

        if ("/system/oss/getOssSts.do".equals(path)) {
            getOssSts(request,response);
        } else if ("/system/user/login.do".equals(path)) {
            login(request,response);
        } else if ("/system/user/logout.do".equals(path)) {
            logout(request,response);
        } else if ("/system/user/getUserinfo.do".equals(path)) {
            getUserinfo(request,response);
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("===> logout");
        request.getSession().invalidate();

        try {
            request.getRequestDispatcher("/system/login/login.html").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getUserinfo(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(false);
        // Map<String, Object> map = new HashMap<>();
        RespMap<Object> map = new RespMap<>();

        if (session != null) {
            User user = (User) session.getAttribute("user");
            map.put("user",user);
            map.putSuccess(true);
        } else {
            map.putSuccess(false);
        }

        PrintJson.printJsonObj(response,map);
    }

    private void login(HttpServletRequest request, HttpServletResponse response) {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserService us = (UserService) ServiceFactory.getService(new UserServiceImpl());

        Map<String,Object> map = us.login(username,password);

        if (map.get("success").equals(true)) {
            User user = (User)map.get("user");
            request.getSession(true).setAttribute("user",user);
            PrintJson.printJsonFlag(response,true);
        } else {
            PrintJson.printJsonFlag(response,false);
        }
    }


    /**
     * 获取OSS_STS
     * @param request
     * @param response
     */
    private void getOssSts(HttpServletRequest request, HttpServletResponse response) {
        AliOssSts ossSTS = OSSServiceImpl.getOssSTS();
        PrintJson.printJsonObj(response, ossSTS);
    }




}
