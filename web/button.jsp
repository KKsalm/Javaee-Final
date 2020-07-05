<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        #btn {
            transition: all ease .3s;
            height: 200px;
            width: 200px;
            margin: 300px auto;
            background-color: red;
            color: #efeeef;
            text-align: center;
            line-height: 200px;
            border-radius: 50%;
            cursor: pointer;
            user-select: none;
            position: relative;
        }
        
        #btn:hover {
            box-shadow: 0 8px 5px rgba(255, 0, 0, .8);
        }
        
        #btn:active {
            box-shadow: inset 0 -12px 5px rgba(0, 0, 0, .3);
        }
    </style>
</head>


<form action="/initDB" method="post">
    <body>
    <div id="btn"><button type="submit"><a style="text-decoration: none;color: #efefef;">Button</a></button></div>
    </body>
</form>


</html>