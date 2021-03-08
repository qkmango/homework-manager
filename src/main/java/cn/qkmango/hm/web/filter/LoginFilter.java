package cn.qkmango.hm.web.filter;

import cn.qkmango.hm.utils.PrintJson;
import cn.qkmango.hm.utils.RespStatusMsg;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @version 1.0
 * <p>登陆过滤器</p>
 * <p>验证用户是否登陆，登陆则放行，未登录则重定向到登陆页面</p>
 * @className LoginFilter
 * @author: Mango
 * @date: 2021-01-28 16:51
 */
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        System.out.println("LoginFilter");

        /*
        如果session ！= null
        或 访问的路径是 登录页/登陆请求 等
        则放行*/
        String path = request.getServletPath();
        if ("/system/user/login.do".equals(path)||
            "/system/user/logout.do".equals(path)||
            "/homework/getCourseList.do".equals(path)||
            "/visualization/getRecentCommitCount.do".equals(path)||
            "/visualization/getCommitDynamic.do".equals(path)||
            "/visualization/getHeatmap.do".equals(path)||
            "/homework/getHomeworkById.do".equals(path)) {
            chain.doFilter(req,resp);
        } else if (request.getSession(false)!= null) {
            chain.doFilter(req,resp);
        } else {
            PrintJson.printFlagAndMsg(response,false, RespStatusMsg.Not_Logged_In);
        }
    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException { }
}
