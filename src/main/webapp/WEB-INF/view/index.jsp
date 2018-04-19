<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java"  contentType="text/html; charset=utf-8"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//  request.getRequestDispatcher("/homepage").include(request, response);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <base href="<%=basePath%>">

  <title>My JSP 'index.jsp' starting page</title>
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
      margin: 10px;
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
<h1>商品展示</h1>
<hr>

<center>
  <table width="750" height="60" cellpadding="0" cellspacing="0" border="0">
    <tr>
      <td>

        <!-- 商品循环开始 -->
        <c:forEach var="item" items="${itemList}">
        <div>
          <dl>
            <dt>
              <a href="details?id=${item.id}"><img src="images/${item.picture}" width="120" height="90" border="1"/></a>
            </dt>
            <dd class="dd_name">${item.name}</dd>
            <dd class="dd_city">产地:${item.city}&nbsp;&nbsp;价格:￥ ${item.price}</dd>
          </dl>
        </div>
        </c:forEach>
        <!-- 商品循环结束 -->
      </td>
    </tr>
  </table>
</center>
</body>
</html>
