<%@page import="javax.naming.NamingException"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="Model.MonthlySaleAmount"%>
<%@page import="Model.DailySaleAmount"%>
<%@page import="Database.MonthlySaleAmountDataController"%>
<%@page import="Database.DailySaleAmountDataController"%>
<%@page import="Model.User"%>
<%@page import="Database.StoreDataController"%>
<%@page import="Model.Store"%>
<%@page import="java.util.List"%>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>店铺管理</title>
        <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
        <link href="./css/storeManage.css" rel="stylesheet">
    </head>

    <body>
        <%
            User currentUser = (User) session.getAttribute("CurrentUser");
            if (currentUser == null) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>
        <div class="container">
            <div class="row">
                <div class="page-header">
                    <h1>店铺管理 <small>增加&修改店铺</small>
                        <a href="index.jsp"><button type="button" class="btn btn-primary" style="float: right;">返回主页</button></a>
                        <button data-toggle="modal" data-target="#myModal" type="button" class="btn btn-primary" style="float: right; margin-right: 10px;">新增店铺</button>
                    </h1>
                </div>
                <div class="table-responsive">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>店铺编号</th>
                                <th>地址</th>
                                <th>本日营销额</th>
                                <th>本月营销额</th>
                                <th>操作</th>
                            </tr>
                        </thead>
                        <tbody class="tableBody">
                            <%
                                try {

                                    List<Store> stores = new ArrayList<>();
                                    StoreDataController storeDataController = new StoreDataController();
                                    if (currentUser.getPosition().equals("boss")){
                                        stores = storeDataController.queryByIDStatusFalse();
                                    } else {
                                        stores.add(storeDataController.queryByID(currentUser.getStoreID()));
                                    }
                                    storeDataController.closeConnection();


                                    for (Store store : stores) {

                                        DailySaleAmountDataController dailySaleAmountDataController = new DailySaleAmountDataController();
                                        dailySaleAmountDataController.createSaleAmount(store.getStoreID());
                                        DailySaleAmount dailySaleAmount = dailySaleAmountDataController.query(store.getStoreID());
                                        dailySaleAmountDataController.closeConnection();

                                        MonthlySaleAmountDataController monthlySaleAmountDataController = new MonthlySaleAmountDataController();
                                        monthlySaleAmountDataController.createSaleAmount(store.getStoreID());
                                        MonthlySaleAmount monthlySaleAmount = monthlySaleAmountDataController.query(store.getStoreID());
                                        monthlySaleAmountDataController.closeConnection();
                            %>
                        <form  action="/store/update" method="post">
                            <tr>
                                <td><%=store.getStoreID()%>
                                    <input hidden name="storeID" value="<%=store.getStoreID()%>">
                                </td>
                                <td>
                                    <input name="address" class="form-control" style="width: 30%" value="<%=store.getAddress()%>">
                                </td>
                                <td><%=dailySaleAmount.getAmount()%></td>
                                <td><%=monthlySaleAmount.getAmount()%></td>
                                <td>
                                    <ul style="padding-left: 0;">
                                        <li style="color: royalblue;list-style: none;text-decoration: none;cursor: pointer">
                                            <span class="glyphicon glyphicon-pencil"></span>
                                            <button type="submit" style="padding-left: 5px;color: royalblue;text-decoration: none;" class="btn btn-link">修改</button>
                                        </li>
                                        <li style="color: red;list-style: none;text-decoration: none;cursor: pointer;">
                                            <span class="glyphicon glyphicon-trash"></span>
                                            <a href="store/delete?storeID=<%=store.getStoreID()%>">
                                                <button type="button"
                                                        style="color: red;padding-left: 5px;text-decoration: none;"
                                                        class="btn btn-link">删除
                                                </button>
                                        </li>
                                    </ul>
                                </td>
                            </tr>
                        </form>
                        <%
                                }
                            } catch (SQLException sqlException) {
                                sqlException.printStackTrace();
                            } catch (NamingException namingException) {
                                namingException.printStackTrace();
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
                    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                        <div class="modal-dialog" style="top: 20vh;" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                    <h4 class="modal-title" id="myModalLabel">
                                        <!--增加-->编辑店铺</h4>
                                </div>
                                <div class="modal-body">
                                    <form id="editForm" action="store/add" method="post">
                                        <div class="form-group">
                                            <label for="exampleInputEmail1">地址</label>
                                            <input type="text" class="form-control" id="exampleInputEmail1" placeholder="Address" name="address" required>
                                        </div>                               
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                            <button type="submit" form="editForm" class="btn btn-primary">提交</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script>
            <%                            if (session.getAttribute("Code") != null) {
            %>
            alert("<%=session.getAttribute("Message").toString()%>");
            <%
                    session.removeAttribute("Code");
                    session.removeAttribute("Message");
                }
            %>
        </script>
        <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js "></script>
        <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js "></script>
        <script src="./js/libs/anime.min.js "></script>
        <script src="./js/storeManage.js "></script>
    </body>

</html>