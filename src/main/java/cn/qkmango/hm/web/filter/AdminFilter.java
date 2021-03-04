package cn.qkmango.hm.web.filter;

import cn.qkmango.hm.system.domain.User;
import cn.qkmango.hm.utils.PrintJson;
import cn.qkmango.hm.utils.RespStatusMsg;

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

        System.out.println("AdminFilter");

        HttpSession session = request.getSession(false);

        if (session == null) {
            PrintJson.printFlagAndMsg(response,false, RespStatusMsg.Insufficient_Permissions);
            return;
        }

        User user = (User) session.getAttribute("user");
        if (user != null && "1".equals(user.getPower())) {
            chain.doFilter(req,resp);
        } else {
            PrintJson.printFlagAndMsg(response,false, RespStatusMsg.Insufficient_Permissions);
        }
    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException { }

}
