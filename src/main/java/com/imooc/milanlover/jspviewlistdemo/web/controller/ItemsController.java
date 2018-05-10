package com.imooc.milanlover.jspviewlistdemo.web.controller;

import com.imooc.milanlover.jspviewlistdemo.dao.ItemDao;
import com.imooc.milanlover.jspviewlistdemo.entity.Item;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Faithful-Mind on 2018/5/10.
 */
@RestController
public class ItemsController {

    ItemDao itemDao;

    public ItemsController(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    @RequestMapping("/ajax/items")
    public List<Item> items() {
        return itemDao.getAllItems();
    }
}
