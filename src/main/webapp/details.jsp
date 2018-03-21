<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<%@ page import="com.imooc.milanlover.jspviewlistdemo.entity.Items"%>
<%@ page import="com.imooc.milanlover.jspviewlistdemo.dao.ItemsDAO"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'details.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <style type="text/css">
	   div{
	      float:left;
	      margin-left: 30px;
	      margin-right:30px;
	      margin-top: 5px;
	      margin-bottom: 5px;
	   }
	   div dd{
	      margin:0px;
	      font-size:10pt;
	   }
	   div dd.dd_name
	   {
	      color:blue;
	   }
	   div dd.dd_city
	   {
	      color:#000;
	   }
	</style>
  </head>
  
  <body>
    <h1>商品详情</h1>
    <hr>
    <center>
      <table width="750" height="60" cellpadding="0" cellspacing="0" border="0">
        <tr>
          <!-- 商品详情 -->
          <% 
             ItemsDAO itemDao = new ItemsDAO();
             Items item = itemDao.getItemsById(Integer.parseInt(request.getParameter("id")));
             request.setAttribute("item", item);
          %>
          <td width="70%" valign="top">
             <table>
               <tr>
                 <td rowspan="4"><img src="images/${item.picture}" width="200" height="160"/></td>
               </tr>
               <tr>
                 <td><B>${item.name}</B></td> 
               </tr>
               <tr>
                 <td>产地：${item.city}</td>
               </tr>
               <tr>
                 <td>价格：${item.price}￥</td>
               </tr> 
             </table>
          </td>
          <%
              String recentCsv ="";
              //从客户端获得Cookies集合
              Cookie[] cookies = request.getCookies();
              //遍历这个Cookies集合
              if(cookies!=null&&cookies.length>0)
              {
	              for(Cookie c:cookies)
	              {
	                  if(c.getName().equals("ListViewCookie"))
	                  {
	                     recentCsv = c.getValue();
	                  }
	              }
	          }

              recentCsv +=request.getParameter("id")+",";
              //如果浏览记录超过1000条，清零.
              String[] arr = recentCsv.split(",");
              if(arr!=null&&arr.length>0)
              {
                  if(arr.length>=1000)
                  {
                      recentCsv ="";
                  }
              }
              Cookie cookie = new Cookie("ListViewCookie", recentCsv);
              response.addCookie(cookie);

          %>
          <!-- 浏览过的商品 -->
          <td width="30%" bgcolor="#EEE" align="center">
            <br>
            <b>您浏览过的商品</b><br>
            <!-- 循环开始 -->
            <%
                ArrayList<Items> itemList = itemDao.getViewList(recentCsv);
                System.out.println("itemList.size="+ itemList.size());
                session.setAttribute("recentViews", itemList);

            %>
            <c:forEach var="i" items="${recentViews}">
            <div>
            <dl>
              <dt>
                <a href="details.jsp?id=${i.id}"><img src="images/${i.picture}" width="120" height="90" border="1"/></a>
              </dt>
              <dd class="dd_name">${i.name}</dd>
              <dd class="dd_city">产地:${i.city}&nbsp;&nbsp;价格:${i.price} ￥ </dd>
            </dl>
            </div>
            </c:forEach>
             <!-- 循环结束 -->
          </td>
        </tr>
      </table>
    </center>
  </body>
</html>
