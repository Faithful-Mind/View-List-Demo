package com.imooc.milanlover.jspviewlistdemo.web.controller;

import com.imooc.milanlover.jspviewlistdemo.dao.ItemDao;
import com.imooc.milanlover.jspviewlistdemo.entity.Item;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Faithful-Mind on 2018/3/26.
 */
@Controller
@RequestMapping("/")
public class ViewListDemoController {

    private ItemDao itemDao;

    public ViewListDemoController(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    @RequestMapping
    public String home(Model model) {
        model.addAttribute(itemDao.getAllItems());
        return "index";
    }

    @RequestMapping("/details")
    public String details(Model model, @RequestParam int id, HttpServletRequest req, HttpServletResponse resp) {
        Item item = itemDao.getItemsById(id);
        model.addAttribute("item", item);

        recents(req, resp);

        return "details";
    }

//    @RequestMapping("/GetRecentItems.do")
    public String recents(HttpServletRequest req, HttpServletResponse resp) {
        String recentIdCsv = "";
        //从客户端获得Cookies集合
        Cookie[] cookies = req.getCookies();
        //遍历这个Cookies集合
        if(cookies != null && cookies.length > 0) {
            for(Cookie c:cookies) {
                if(c.getName().equals("ListViewCookie")) {
                    recentIdCsv = c.getValue();
                }
            }
        }

        recentIdCsv += req.getParameter("id") + "_"; // "," is not allowed in cookie 0 version
        //如果浏览记录超过100条，清零.
        String[] arr = recentIdCsv.split("_");
        if (arr != null && arr.length >= 100) {
            recentIdCsv = "";
        }
        Cookie cookie = new Cookie("ListViewCookie", recentIdCsv);
        resp.addCookie(cookie);

        List<Item> itemList = itemDao.getViewList(recentIdCsv);
        System.out.println("itemList.size="+ itemList.size());
        req.getSession().setAttribute("recentViews", itemList);
        return "details";
    }
}
