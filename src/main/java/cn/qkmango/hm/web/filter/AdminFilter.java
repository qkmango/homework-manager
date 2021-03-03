package cn.qkmango.hm.web.filter;

import cn.qkmango.hm.system.domain.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @version 1.0
 * @Description: //TODO
 * <p>类简介</p>
 * <p>类详细介绍</p>
 * @className AdminFilter
 * @author: Mango
 * @date: 2021-03-03 11:15
 */
public class AdminFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        HttpSession session = request.getSession(false);

        if (session == null) {
            response.setStatus(200);
            response.sendError(403,"您没有权限");
            return;
        }

        User user = (User) session.getAttribute("user");
        if (user != null && "1".equals(user.getPower())) {
            chain.doFilter(req,resp);
        } else {
            response.setStatus(200);
            response.sendError(403,"您没有权限");
        }
    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException { }

}
