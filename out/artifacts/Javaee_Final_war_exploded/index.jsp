<%@page import="Database.UserDataController"%>
<%@page import="Model.MonthlySaleAmount"%>
<%@page import="Database.MonthlySaleAmountDataController"%>
<%@page import="Model.DailySaleAmount"%>
<%@page import="Database.DailySaleAmountDataController"%>
<%@page import="Model.Store"%>
<%@page import="Database.StoreDataController"%>
<%@ page import="Model.User" %>
<%@ page import="Model.SaleRecord" %>
<%@ page import="java.util.List" %>
<%@ page import="Database.SaleRecordDataController" %>
<%@ page import="javax.naming.NamingException" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="Database.ProductDataController" %>
<%@ page import="Model.Product" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
        <meta name="description" content="">
        <meta name="author" content="">
        <title>益禾堂管理系统</title>
        <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
        <link href="./css/index.css" rel="stylesheet">
    </head>

    <body>

        <script>
            <%
                if (session.getAttribute("Code") != null) {
            %>
            alert("<%=session.getAttribute("Message").toString()%>");
            <%
                    session.removeAttribute("Code");
                    session.removeAttribute("Message");
                }
            %>
        </script>

        <!-- 顶部 -->
        <nav class="navbar navbar-inverse navbar-fixed-top" style="background-color: rgb(76, 153, 88);">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                            aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand"
                       style="color: #efefef;font-weight: 500;letter-spacing: .02em;font-size: 22px;padding: 0 15px;"
                       href="javascript: void(0)">
                        <span style="font-size: 24px;display: inline-block;margin-top: 15px;">益禾堂管理系统</span>
                    </a>
                </div>
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li><a style="color: #efefef;" href="myInfo.jsp">个人中心</a></li>
                        <li><a style="color: #efefef;" href="logout">退出登录</a></li>
                        <!-- <li><a style="color: #efefef;" href="javascript: void(0)">Help</a></li> -->
                    </ul>
                    <!--                    <form class="navbar-form navbar-right">
                                            <input type="text" class="form-control" placeholder="Search...">
                                        </form>-->
                </div>
            </div>
        </nav>

        <div class="container-fluid">
            <div class="row">
                <!-- 侧边栏 -->
                <div class="col-sm-3 col-md-2 sidebar" style="padding-top: 0;">
                    <!-- <h3>common</h3> -->
                    <ul class="nav nav-sidebar">
                        <li style="padding: 10px 15px;text-align: left;">
                            <img src="./imgs/logo.jpg" style="height: 100%;display: inline-block;cursor: pointer;" alt="">
                        </li>
                        <li class="active"><a href="javascript: void(0)">主页 <span class="sr-only">(current)</span></a></li>
                        <li><a href="orderManage.jsp">订单管理</a></li>
                        <!-- <li><a href="javascript: void(0)">下单</a></li> -->
                        <li><a href="myInfo.jsp">个人中心</a></li>
                    </ul>
                    <!-- <h3>老板</h3> -->
                    <ul class="nav nav-sidebar">
                        <li><a href="storeManage.jsp">店铺管理</a></li>
                        <!-- <li><a href="">店长管理</a></li> -->
                    </ul>
                    <!-- <h3>店长</h3> -->
                    <ul class="nav nav-sidebar">
                        <li><a href="storeMasterManage.jsp">店员管理</a></li>
                        <li><a href="getCommodity.jsp">进货管理</a></li>
                        <!-- <li><a href="">结算员工工资</a></li> -->
                        <li><a href="commodityManage.jsp">商品管理</a></li>
                    </ul>
                </div>
                <!-- 主页， 路由窗口，从这里开始通过路径拿jsp往里怼页面， 记得切换相应的js和css，已经按名字分好了 -->
                <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                    <h1 class="page-header">欢迎您，
                        <%
                            User currentUser = (User) session.getAttribute("CurrentUser");
                            if (currentUser == null) {
                                response.sendRedirect("login.jsp");
                            } else {

                                UserDataController userDataController = new UserDataController();
                                User user = userDataController.queryByID(currentUser.getUserID());
                                userDataController.closeConnection();

                                StoreDataController storeDataController = new StoreDataController();
                                Store store = storeDataController.queryByID(user.getStoreID());
                                storeDataController.closeConnection();

                                DailySaleAmountDataController dailySaleAmountDataController = new DailySaleAmountDataController();
                                DailySaleAmount dailySaleAmount = dailySaleAmountDataController.query(store.getStoreID());
                                dailySaleAmountDataController.closeConnection();

                                MonthlySaleAmountDataController monthlySaleAmountDataController = new MonthlySaleAmountDataController();
                                MonthlySaleAmount monthlySaleAmount = monthlySaleAmountDataController.query(store.getStoreID());
                                monthlySaleAmountDataController.closeConnection();

                        %>
                    </h1>
                    <div class="row placeholders">
                        <div class="col-xs-8 col-sm-4 placeholder">
                            <!-- 随便搞点图 -->
                            <img src="./imgs/微信图片_20200408134722.jpg" width="200" height="200" class="img-responsive"
                                 alt="Generic placeholder thumbnail">
                            <h4><b>当日营销额</b></h4>
                            <span class="text-muted">￥<%=dailySaleAmount.getAmount()%></span>
                        </div>
                        <div class="col-xs-8 col-sm-4 placeholder">
                            <img src="./imgs/微信图片_20200613092722.jpg" width="200" height="200" class="img-responsive"
                                 alt="Generic placeholder thumbnail">
                            <h4><b>当月营销额</b></h4>
                            <span class="text-muted">￥<%=monthlySaleAmount.getAmount()%></span>
                        </div>
                        <!--                        <div class="col-xs-8 col-sm-4 placeholder">
                                                    <img src="./imgs/zuihao.png" width="200" height="200" class="img-responsive"
                                                         alt="Generic placeholder thumbnail">
                                                    <h4><b>本月最佳员工</b></h4>
                                                     建议用工作时长来衡量 
                                                    <span class="text-muted">ZZR</span>
                                                </div>-->
                    </div>
                    <%}
                    %>
                    <h2 class="sub-header">销售记录</h2>
                    <div class="table-responsive">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>序号</th>
                                    <th>名称</th>
                                    <th>数量</th>
                                    <th>总价格</th>
                                    <th>日期</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    try {
                                        SaleRecordDataController saleRecordDataController = new SaleRecordDataController();
                                        List<SaleRecord> saleRecords = saleRecordDataController.queryAll();

                                        for (SaleRecord saleRecord : saleRecords) {
                                            ProductDataController productDataController = new ProductDataController();
                                            Product product = productDataController.queryByID(saleRecord.getProductID());
                                            productDataController.closeConnection();
                                %>
                                <tr>
                                    <td><%=saleRecord.getSaleRecordID()%>
                                    </td>
                                    <td><%=product.getProductName()%>
                                    </td>
                                    <td><%=saleRecord.getProductNum()%>
                                    </td>
                                    <td><%=saleRecord.getTotalPrice()%>
                                    </td>
                                    <td><%=saleRecord.getDate()%>
                                    </td>
                                </tr>
                                <%

                                        }
                                    } catch (NamingException namingException) {
                                        namingException.printStackTrace();
                                    } catch (SQLException sqlException) {
                                        sqlException.printStackTrace();
                                    } catch (IllegalAccessException illegalAccessException) {
                                        illegalAccessException.printStackTrace();
                                    } catch (InstantiationException instantiationException) {
                                        instantiationException.printStackTrace();
                                    }

                                %>
                            </tbody>
                        </table>
                        <ul class="pagination">
                            <li>
                                <a href="#" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                            <li><a href="#">1</a></li>
                            <li><a href="#">2</a></li>
                            <li><a href="#">3</a></li>
                            <li><a href="#">4</a></li>
                            <li><a href="#">5</a></li>
                            <li>
                                <a href="#" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script src="./js/libs/anime.min.js"></script>
        <script src="./js/index.js"></script>
    </body>

</html>