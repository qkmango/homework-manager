package cn.qkmango.hm.visualization.web.controller;

import cn.qkmango.hm.utils.PrintJson;
import cn.qkmango.hm.utils.ServiceFactory;
import cn.qkmango.hm.visualization.service.VisualizationService;
import cn.qkmango.hm.visualization.service.impl.VisualizationServiceImpl;

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
 * @className VisualizationController
 * @author: Mango
 * @date: 2021-03-07 14:56
 */
public class VisualizationController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        System.out.println("进入到VisualizationController");

        if ("/visualization/getRecentCommitCount.do".equals(path)) {
            getRecentCommitCount(request,response);
        } else if ("/visualization/getCommitDynamic.do".equals(path)) {
            getCommitDynamic(request,response);
        }
    }

    private void getCommitDynamic(HttpServletRequest request, HttpServletResponse response) {
        VisualizationService vs = (VisualizationService) ServiceFactory.getService(new VisualizationServiceImpl());
        List<Map<String, Object>> list = vs.getCommitDynamic();
        PrintJson.printJsonObj(response,list);
    }

    private void getRecentCommitCount(HttpServletRequest request, HttpServletResponse response) {
        VisualizationService vs = (VisualizationService) ServiceFactory.getService(new VisualizationServiceImpl());
        HashMap<String, Object> map = vs.getRecentCommitCount();

        PrintJson.printJsonObj(response,map);

    }
}
