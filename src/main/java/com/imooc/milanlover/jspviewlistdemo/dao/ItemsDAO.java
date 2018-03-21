package com.imooc.milanlover.jspviewlistdemo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.imooc.milanlover.jspviewlistdemo.entity.Items;
import com.imooc.milanlover.jspviewlistdemo.util.DBHelper;

/** 商品的业务逻辑类 */
public class ItemsDAO {

    /** 获得所有的商品信息 */
    public ArrayList<Items> getAllItems() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Items> list = new ArrayList<>(); // 商品集合

        try {
            conn = DBHelper.getConnection();
            String sql = "SELECT * FROM items;"; // SQL 语句
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Items item = new Items();
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setCity(rs.getString("city"));
                item.setNumber(rs.getInt("number"));
                item.setPrice(rs.getInt("price"));
                item.setPicture(rs.getString("picture"));
                list.add(item); // 把一个商品加入集合
            }
            return list; // 返回集合
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            // 释放数据集对象
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            // 释放语句对象
            if (stmt != null) {
                try {
                    stmt.close();
                    stmt = null;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            // don't close conn here. It's singleton。
        }
    }

    /** 根据商品编号，获得商品资料 */
    public Items getItemsById(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBHelper.getConnection();
            String sql = "SELECT * FROM items WHERE id=?;"; // SQL 语句
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                Items item = new Items();
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setCity(rs.getString("city"));
                item.setNumber(rs.getInt("number"));
                item.setPrice(rs.getInt("price"));
                item.setPicture(rs.getString("picture"));
                return item;
            } else {
                return null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            // 释放数据集对象
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            // 释放语句对象
            if (stmt != null) {
                try {
                    stmt.close();
                    stmt = null;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            // don't close conn here. It's singleton。
        }
    }

    /** 获取最近浏览的前五条商品信息 */
    public ArrayList<Items> getViewList(String recentCsv) {
        ArrayList<Items> itemsList = new ArrayList<Items>();
        int iCount = 5; // 每次返回前几条记录
        if (recentCsv != null && recentCsv.length() > 0) {
            String[] arr = recentCsv.split(",");

            // 倒序以获得最近 iCount 个ID （recentCsv 最后 iCount 个ID）
            if (arr.length >= iCount) { // 如果记录大于 iCount 条，
                for (int i = arr.length-1; i > arr.length-iCount-1; i--) {
                    itemsList.add(getItemsById(Integer.parseInt(arr[i])));
                }
            } else {
                for (int i = arr.length-1; i >= 0; i--) {
                    itemsList.add(getItemsById(Integer.parseInt(arr[i])));
                }
            }
            return itemsList;
        } else {
            return null;
        }
    }
}