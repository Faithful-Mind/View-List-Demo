package com.imooc.milanlover.jspviewlistdemo.dao;

import java.util.ArrayList;
import java.util.List;

import com.imooc.milanlover.jspviewlistdemo.entity.Item;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/** 商品的业务逻辑类 */
@Component
public class ItemDao {
    private JdbcTemplate jdbcTemplate;

    public ItemDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /** 获得所有的商品信息 */
    public List<Item> getAllItems() {
        return jdbcTemplate.query("SELECT * FROM items;", new BeanPropertyRowMapper<>(Item.class));
    }

    /** 根据商品编号，获得商品资料 */
    public Item getItemsById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM items WHERE id=?",
                new BeanPropertyRowMapper<>(Item.class), id);
    }

    /** 获取最近浏览的前五条商品信息 */
    public List<Item> getViewList(String recentIdCsv) {
        ArrayList<Item> itemList = new ArrayList<Item>();
        int iCount = 5; // 每次返回前几条记录
        if (recentIdCsv != null && recentIdCsv.length() > 0) {
            String[] recentIds = recentIdCsv.split("_");

            ArrayList<String> addedIds = new ArrayList<>(); // for dedup

            // 倒序以获得最近 iCount 个不重复的 ID （recentIdCsv 最后 iCount 个不重复的 ID）
            for (int i = recentIds.length-1; i >= 0; i--) {
                if (addedIds.contains(recentIds[i])) continue; // dedup

                itemList.add(getItemsById(Integer.parseInt(recentIds[i])));

                addedIds.add(recentIds[i]); // for dedup
                if (itemList.size() >= iCount) break;
            }

            return itemList;
        } else {
            return null;
        }
    }
}