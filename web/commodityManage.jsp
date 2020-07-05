<%@page import="javax.naming.NamingException"%>
<%@page import="java.sql.SQLException"%>
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
        <title>商品管理</title>
        <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
        <link href="./css/commodityManage.css" rel="stylesheet">
    </head>

    <body>
        <%
            if (session.getAttribute("CurrentUser") == null) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>
        <div class="container">
            <div class="row">
                <div class="page-header">
                    <h1>商品管理 <small>增加&修改商品</small>
                        <a href="index.jsp"><button type="button" class="btn btn-primary" style="float: right;">返回主页</button></a>
                        <button data-toggle="modal" data-target="#myModal" type="button" class="btn btn-primary" style="float: right; margin-right: 10px;">新增商品</button>
                    </h1>
                </div>
                <div class="table-responsive">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>商品编号</th>
                                <th>名称</th>
                                <th>单价</th>
                                <th>操作</th>
                            </tr>
                        </thead>
                        <tbody class="tableBody">
                            <%
                                try {
                                    ProductDataController productDataController = new ProductDataController();
                                    List<Product> products = productDataController.queryByIDStatusFalse();
                                    productDataController.closeConnection();
                                    for (Product product : products) {

                            %>
                        <form  action="product/update" method="post">
                            <tr>
                                <td><%=product.getProductID()%>
                                    <input hidden name="productID" value="<%=product.getProductID()%>">
                                </td>
                                <td>
                                    <input class="form-control" name="productName" style="width: 40%" value="<%=product.getProductName()%>">
                                </td>
                                <td>
                                    <input class="form-control" name="productPrice" style="width: 20%" value="<%=product.getProductPrice()%>">
                                </td>
                                <td>
                                    <ul style="padding-left: 0;">
                                        <li style="color: royalblue;list-style: none;text-decoration: none;cursor: pointer">
                                            <span class="glyphicon glyphicon-pencil"></span>
                                            <button type="submit" style="padding-left: 5px;color: royalblue;text-decoration: none;" value="<%=product.getProductID()%>" class="btn btn-link">修改</button>
                                        </li>
                                        <li style="color: red;list-style: none;text-decoration: none;cursor: pointer;">
                                            <span class="glyphicon glyphicon-trash"></span>
                                            <a href="${pageContext.request.contextPath}/product/delete?productID=<%=product.getProductID()%>">
                                                <button type="button"
                                                        style="color: red;padding-left: 5px;text-decoration: none;"
                                                        class="btn btn-link">删除
                                                </button>
                                            </a>
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
                            }
                        %>

                        </tbody>
                    </table>

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
                                        <!--增加-->新增商品</h4>
                                </div>
                                <div class="modal-body">
                                    <form id="editForm" action="product/add" method="post">
                                        <div class="form-group"style="display: inline-block;width: 100%;">
                                            <label for="Name">名称</label>
                                            <input type="text" class="form-control" id="Name" placeholder="Name" name="productName" required>
                                        </div>
                                        <div class="form-group">
                                            <label for="Price">单价</label>
                                            <input type="text" class="form-control" id="Price" placeholder="Price" name="productPrice" required>
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
        </div>

        <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js "></script>
        <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js "></script>
        <script src="./js/libs/anime.min.js "></script>
        <script src="./js/commodityManage.js "></script>
    </body>

</html>