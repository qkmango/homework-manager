package cn.qkmango.hm.web.filter;

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

        HttpSession session = request.getSession(false);

        /*
        如果session ！= null
        或 访问的路径是 登录页/登陆请求
        则放行*/
        String path = request.getServletPath();
        if (session != null ||
            "/system/user/login.do".equals(path)||
            "/system/user/logout.do".equals(path)||
            "/homework/getCourseList.do".equals(path)||
            "/system/user/getUserinfo.do".equals(path)) {
            chain.doFilter(req,resp);
        } else {
            /*说明未登陆，重定向到login.jsp

            在实际项目开发中，对于路径的使用，不论操作的是前端还是后端，应该一律使用绝对路径
            转发：
                使用的是一种特殊的绝对路径的使用方式，这种绝对路径的使用方式路径前面不加 /项目名，这种路径称为内部路径 /login.jsp
            重定向：
                使用的是传统的绝对路径的写法，前面必须以 /项目名 开头，后面跟具体的资源名

            为了使项目更加灵活，项目名通过 request.getContextPath() 来获取 /crm
                request.getContextPath()+"/login.jsp"
                "crm/login.jsp"
            */

            response.sendRedirect(request.getContextPath()+"/system/login.html");
        }
    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException { }
}
