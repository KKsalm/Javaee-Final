<%@page import="Model.Store"%>
<%@page import="Database.StoreDataController"%>
<%@page import="javax.naming.NamingException"%>
<%@page import="java.sql.SQLException"%>
<%@page import="Database.UserDataController"%>
<%@page import="Model.User"%>
<%@page import="Model.Product"%>
<%@page import="java.util.List"%>
<%@page import="Database.ProductDataController"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>店员管理</title>
        <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
        <link href="./css/storeMasterManage.css" rel="stylesheet">
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
                    <h1>
                        店员管理 <small>增加&修改店员</small>
                        <a href="index.jsp"><button type="button" class="btn btn-primary" style="float: right;">返回主页</button></a>
                    </h1>
                </div>
                <div class="table-responsive">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>店员工号</th>
                                <th>店员名称</th>
                                <th>职位</th>
                                <th>所在店铺编号</th>
                                <th>本月工作时长</th>
                                <th>月工资</th>
                                <th>是否为店长</th>
                                <th>操作</th>
                            </tr>
                        </thead>
                        <tbody class="tableBody">
                            <%
                                try {
                                    List<User> users = null;
                                    UserDataController userDataController = new UserDataController();
                                    users = userDataController.queryAll();
                                    userDataController.closeConnection();

                                    StoreDataController storeDataController = new StoreDataController();
                                    List<Store> stores = storeDataController.queryAll();
                                    storeDataController.closeConnection();

                                    session.setAttribute("Stores", stores);
                                    for (User user : users) {
                                        if (currentUser.getPosition() == null) {
                                            break;
                                        }

                                        assert user.getStoreID() != null;
                                        if ((!currentUser.getPosition().equals("boss")
                                                && user.getStoreID() != currentUser.getStoreID())
                                                || (!currentUser.getPosition().equals("boss") && user.getPosition().equals("boss"))) {
                                            continue;
                                        }
                            %>
                        <form  action="user/updateinfo" method="post">
                            <tr>
                                <td><%=user.getUserID()%>
                                    <input hidden name="userID" value="<%=user.getUserID()%>"></td>
                                <td>
                                    <%=user.getName()%>
                                </td>
                                <td>
                                    <select name="position" style="width: 100%;" class="form-control" required>
                                        <%
                                            out.println("<option value=\"staff\">" + "店员" + "</option>");
                                            out.println("<option value=\"manager\">" + "店长" + "</option>");
                                            if ( currentUser.getPosition().equals("boss")) {
                                                out.println("<option value=\"boss\">" + "老板" + "</option>");
                                            }
                                        %>
                                    </select>
                                    当前职位:<%=user.getPosition()%>
                                </td>
                                <td>
                                    <select name="storeID" style="width: 90%;" class="form-control" required>
                                        <%
                                            for (Store store2 : stores) {
                                                out.println("<option value=\"" + store2.getStoreID() + "\">" + store2.getStoreID() + "</option>");
                                            }
                                        %>
                                    </select>
                                    当前店铺编号:<%=user.getStoreID()%>
                                </td>
                                <td>
                                    <input  class="form-control" name="monthWorkTime" style="width: 30%" value="<%=user.getMonthWorkTime()%>">
                                </td>
                                <td>
                                    <input  class="form-control" name="monthSalary" style="width: 40%" value="<%=user.getMonthSalary()%>">
                                </td>
                                <td><span class="label label-success">
                                        <%if (user.getPosition() == null || user.getPosition().equals("staff")) {
                                                out.println("NO");
                                            } else {
                                                out.println("YES");
                                            }%></span></td>
                                <td>
                                    <ul style="padding-left: 0;">
                                        <li style="color: royalblue;list-style: none;text-decoration: none;cursor: pointer">
                                            <span class="glyphicon glyphicon-pencil"></span>
                                            <button type="submit" style="padding-left: 5px;color: royalblue;text-decoration: none;" class="btn btn-link">修改信息</button>
                                        </li>
                                        <li style="color: red;list-style: none;text-decoration: none;cursor: pointer;">
                                            <span class="glyphicon glyphicon-trash"></span>
                                            <a href="user/delete?deleteID=<%=user.getUserID()%>">
                                                <button type="button"
                                                        style="color: red;padding-left: 5px;text-decoration: none;"
                                                        class="btn btn-link">删除店员
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
                </div>
            </div>
        </div>
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
        <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script src="./js/libs/anime.min.js"></script>
        <script src="./js/storeMasterManage.js"></script>
    </body>

</html>
