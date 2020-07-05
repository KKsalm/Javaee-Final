<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="./css/login.css">
    <title>Register</title>
</head>

<body>
    <div class="container willAnimate" style="text-align: left;">
        <div class="jumbotron" style="text-align: center;">
            <h1 style="color: rgba(12, 47, 23, .7);">益禾堂销售系统</h1>
            <%--        <p class="lead">苟利国家生死以，岂因祸福避趋之</p>--%>
            <img src="./imgs/logo.jpg" alt="yht" id="animateImg">
        </div>
        <form class="form-signin" action="register" method="post">
            <h2 class="form-signin-heading">Sign up</h2>
            <label for="inputEmail" class="sr-only">Account</label>
            <input type="text" id="inputEmail" name="username" class="form-control" placeholder="Account" required autofocus>
            <label for="inputPassword" class="sr-only">Password</label>
            <input style="margin-top: 10px" type="password" id="inputPassword" name="password" class="form-control" placeholder="Password" required>
            <input style="margin-top: 10px" type="password" id="inputPassword" name="validPassword" class="form-control" placeholder="validPassword" required>
            <!-- <div class="checkbox" style="text-align: left;">
                <label>
              <input type="checkbox" value="remember-me"> Remember me
            </label>
            </div> -->
            <button class="btn btn-lg btn-success btn-block" type="submit">Sign up</button>
            <a href="/login.jsp" type="button" style="padding-left: 0;text-align: left;" class="btn btn-link">Already have an Account?<br/>Sign in right now!</a>
        </form>
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
    <!-- /container -->
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="./js/jquery-1.12.4.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <script src="./js/libs/anime.min.js"></script>
    <script src="./js/login.js"></script>
</body>

</html>