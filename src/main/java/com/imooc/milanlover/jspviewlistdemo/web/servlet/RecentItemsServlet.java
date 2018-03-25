package com.imooc.milanlover.jspviewlistdemo.web.servlet;

import com.imooc.milanlover.jspviewlistdemo.dao.ItemsDAO;
import com.imooc.milanlover.jspviewlistdemo.entity.Items;
import com.imooc.milanlover.jspviewlistdemo.util.AppCtxUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Faithful-Mind on 2018/3/26.
 */
@WebServlet(urlPatterns = "/GetRecentItems.do")
public class RecentItemsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String recentIdCsv = "";
        //从客户端获得Cookies集合
        Cookie[] cookies = req.getCookies();
        //遍历这个Cookies集合
        if(cookies != null && cookies.length > 0)
        {
            for(Cookie c:cookies)
            {
                if(c.getName().equals("ListViewCookie"))
                {
                    recentIdCsv = c.getValue();
                }
            }
        }

        recentIdCsv += req.getParameter("id") + ",";
        //如果浏览记录超过100条，清零.
        String[] arr = recentIdCsv.split(",");
        if (arr != null && arr.length>0)
        {
            if (arr.length >= 100)
            {
                recentIdCsv = "";
            }
        }
        Cookie cookie = new Cookie("ListViewCookie", recentIdCsv);
        resp.addCookie(cookie);

        List<Items> itemList = ((ItemsDAO) AppCtxUtil.getBean("itemsDAO")).getViewList(recentIdCsv);
        System.out.println("itemList.size="+ itemList.size());
        req.getSession().setAttribute("recentViews", itemList);
    }
}
