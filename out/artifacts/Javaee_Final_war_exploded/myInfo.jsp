<%@page import="Model.Store"%>
<%@page import="Database.StoreDataController"%>
<%@page import="Database.UserDataController"%>
<%@page import="Model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>个人中心</title>
        <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
        <link href="./css/myInfo.css" rel="stylesheet">
    </head>

    <body>  
        <%
            if (session.getAttribute("CurrentUser") == null) {
                response.sendRedirect("login.jsp");
            }
            User currentUser = (User) session.getAttribute("CurrentUser");

            UserDataController userDataController = new UserDataController();
            User user = userDataController.queryByID(currentUser.getUserID());

            userDataController.closeConnection();
            StoreDataController storeDataController = new StoreDataController();
            Store store = new Store();
            if (user.getStoreID() == null) {
                store.setAddress(null);
            } else {
                store = storeDataController.queryByID(user.getStoreID());
            }
            storeDataController.closeConnection();
        %>
        <div class="container">
            <div class="row">
                <div class="page-header">
                    <h1>个人中心 <small>查看&编辑个人信息</small>
                        <a href="index.jsp"><button type="button" class="btn btn-primary" style="float: right;">返回主页</button></a>
                    </h1>
                </div>
                <div class="jumbotron">
                    <h1><%=user.getName()%></h1>
                    <div class="row">
                        <div class="col-xs-6 col-md-3">
                            <img style="width: 100%;" src="./imgs/微信图片_20200408134722.jpg" alt="Avatar" class="img-circle">
                        </div>
                        <div class="col-xs-18 col-md-9" style="padding-left: 30px;">
                            <ul class="myInfo-list">
                                <li class="myInfo-item">
                                    <span class="glyphicon glyphicon-user"></span>
                                    <span class="label">姓名:</span> <%=user.getName()%>
                                </li>
                                <li class="myInfo-item">
                                    <span class="glyphicon glyphicon-th-list"></span>
                                    <span class="label">ID:</span> <%=user.getUserID()%>
                                </li>
                                <li class="myInfo-item">
                                    <span class="glyphicon glyphicon-bookmark"></span>
                                    <span class="label">职位:</span> <%=user.getPosition()%>
                                </li>
                                <li class="myInfo-item">
                                    <span class="glyphicon glyphicon-home"></span>
                                    <span class="label">所在店面地址:</span> <%=store.getAddress()%>
                                </li>
                                <li class="myInfo-item">
                                    <span class="glyphicon glyphicon-time"></span>
                                    <span class="label">本月工作时长:</span> <%=user.getMonthWorkTime()%>
                                </li>
                                <li class="myInfo-item">
                                    <span class="glyphicon glyphicon-yen"></span>
                                    <span class="label">月工资:</span> <%=user.getMonthSalary()%>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <hr>
                    <button style="float: right;user-select: none;text-decoration: none;" data-toggle="modal" data-target="#myModal" type="button" class="btn btn-link">编辑个人资料</button>
                </div>
                <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                    <div class="modal-dialog" style="top: 20vh;" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title" id="myModalLabel">编辑个人资料</h4>
                            </div>
                            <div class="modal-body">
                                <form id="editForm" action="user/update" method="post">
                                    <input hidden name="userID" value="<%=user.getUserID()%>">

                                    <span class="glyphicon glyphicon-user"></span>
                                    <span class="">Account:</span> <%=user.getUsername()%>
                                    <input hidden name="username" value="<%=user.getUsername()%>">
                                    <input hidden name="storeID" value="<%=user.getStoreID()%>">
                                    <input hidden name="position" value="<%=user.getPosition()%>">
                                    <input hidden name="monthSalary" value="<%=user.getMonthSalary()%>">
                                    <input hidden name="workTime" value="<%=user.getMonthWorkTime()%>">


                                    <input style="margin-top: 5px" class="form-control" name="name" value="<%=user.getName()%>">
                                    <div class="form-group">
                                        <!--                                        <label for="inputPassword">密码</label>-->
                                        <input style="margin-top: 5px" type="password" id="inputPassword" name="password" class="form-control" placeholder="Password" required>
                                        <!--                                        <label for="inputPassword">密码</label>-->
                                        <input style="margin-top: 5px" type="password" id="inputPassword" name="validPassword" class="form-control" placeholder="validPassword" required>
                                    </div>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                <button type="submit" form="editForm" class="btn btn-primary">提交</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script>
            <%
                if (session.getAttribute("Code") != null && (Integer) session.getAttribute("Code") != 0) {
            %>
            alert("<%=session.getAttribute("Message").toString()%>");
            <%
                session.removeAttribute("Code");
                session.removeAttribute("Message");
            } else if (session.getAttribute("Code") != null) {
            %>
            alert("<%=session.getAttribute("Message").toString()%>");
            <%
                }
            %>
        </script>
        <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script src="./js/libs/anime.min.js"></script>
        <script src="./js/myInfo.js"></script>
    </body>

</html>