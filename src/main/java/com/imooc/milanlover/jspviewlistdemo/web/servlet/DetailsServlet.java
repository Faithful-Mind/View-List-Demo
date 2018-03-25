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

/**
 * Created by Faithful-Mind on 2018/3/26.
 */
@WebServlet(urlPatterns = "/GetItemDetails.do")
public class DetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ItemsDAO itemDao = (ItemsDAO) AppCtxUtil.getBean("itemsDAO");
        Items item = itemDao.getItemsById(Integer.parseInt(req.getParameter("id")));
        req.setAttribute("item", item);
    }
}
