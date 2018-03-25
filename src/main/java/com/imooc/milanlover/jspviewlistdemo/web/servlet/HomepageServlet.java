package com.imooc.milanlover.jspviewlistdemo.web.servlet;

import com.imooc.milanlover.jspviewlistdemo.dao.ItemsDAO;
import com.imooc.milanlover.jspviewlistdemo.entity.Items;
import com.imooc.milanlover.jspviewlistdemo.util.AppCtxUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Faithful-Mind on 2018/3/26.
 */
@WebServlet(name = "homepage", urlPatterns = "/homepage")
public class HomepageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ItemsDAO itemsDao = (ItemsDAO) AppCtxUtil.getBean("itemsDAO");
        List<Items> list = itemsDao.getAllItems();
        req.setAttribute("list", list);
    }
}
