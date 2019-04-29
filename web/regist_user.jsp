<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    pageContext.setAttribute("basePath", basePath);
%>
<html>
    <head>
        <meta charset="UTF-8">
        <title>注册</title>
        <link rel="stylesheet" href="${pageScope.basePath}/css/bootstrap.min.css">
        <link rel="stylesheet" href="${pageScope.basePath}/css/add.css">
        <script type="text/javascript" src="${pageScope.basePath}/js/jquery-3.3.1.js"></script>
    </head>
    <body>
        <nav class="navbar navbar-default">
            <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">
                        慕课网留言板个人信息
                    </a>
                </div>
            </div>
        </nav>
        <div class="container">
            <div class="jumbotron">
<%--                <h1>Hello, ${user.name}!</h1>--%>
<%--                <p>请斟酌后修改 ^_^</p>--%>
            </div>
            <div class="page-header">
                <h3><small>个人信息</small></h3>
            </div>
            <form class="form-horizontal" action="${pageScope.basePath}/registUser.do" method="post">
                <input id="id" name="id" type="hidden" value="${user.id}">
                <div class="form-group">
                    <label for="name" class="col-sm-2 control-label">用户 ：</label>
                    <div class="col-sm-6">
                        <input name="name" class="form-control" id="name">
                    </div>
                </div>
                <div class="form-group">
                    <label for="password" class="col-sm-2 control-label">密码 ：</label>
                    <div class="col-sm-6">
                        <input name="password" type="password" class="form-control" id="password" >
                    </div>
                </div>
                <div class="form-group">
                    <label for="checkPassword" class="col-sm-2 control-label">确认密码 ：</label>
                    <div class="col-sm-6">
                        <input name="checkPassword" type="password" class="form-control" id="checkPassword">
                    </div>
                </div>
                <div class="form-group">
                    <label for="realName" class="col-sm-2 control-label">姓名 ：</label>
                    <div class="col-sm-8">
                        <input name="realName" class="form-control" id="realName">
                    </div>
                </div>
                <div class="form-group">
                    <label for="birthday" class="col-sm-2 control-label">生日 ：</label>
                    <div class="col-sm-8">
                        <input name="birthday" class="form-control" id="birthday">
                    </div>
                </div>
                <div class="form-group">
                    <label for="phone" class="col-sm-2 control-label">电话 ：</label>
                    <div class="col-sm-8">
                        <input name="phone"  class="form-control" id="phone">
                    </div>
                </div>
                <div class="form-group">
                    <label for="address" class="col-sm-2 control-label">地址 ：</label>
                    <div class="col-sm-8">
                        <input name="address"  class="form-control" id="address">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <td>验证码</td>
                        <td style="border-right-style:none;">
                            <input type="text" name="checkCode" placeholder="请输入验证码" id="inputCode">

                        </td>
                        <td style="border-left-style:none;">
                            <img src="${pageScope.basePath}kaptcha.jpg" id="checkCode"/>

                        </td>
                        <button type="submit" class="btn btn-primary">注册</button>&nbsp;&nbsp;&nbsp;
                        <button type="reset" class="btn btn-primary">重置</button>&nbsp;
                    </div>
                </div>
            </form>
        </div>
        <footer class="text-center" >
            copy@imooc
        </footer>
    <script type="text/javascript">
        $("#checkCode").on("click", function () {
            $(this).attr("src", "kaptcha.jpg?d=" + new Date().getTime());
        });
        var pwd = null;
        var checkpwd = null;
        $("input[type='submit']").click(function () {
            var code = $("input[name='checkCode']").val();
            var username = $("input[name='username']").val();
            var password = $("input[name='password']").val();
            $.ajax({
                "url": "mook_course/login",
                "data": {"code": code, "username": username, "password": password},
                "type": "post",
                "dataType": "json",
                "success": function (data) {
                    if(data.success!=null){
                        console.log(data);
                        window.location.replace("http://localhost:8080/mook_course/pages/admin/server.jsp");
                    }else if(data.fail!=null){
                        alert("信息有误");
                        window.location.replace("http://localhost:8080/mook_course/");
                    }


                },
                "error": function (err) {
                    alert("信息有误");
                    window.location.replace("http://localhost:8080/mook_course/");
                    console.log(err);

                }
            });
        })
    </script>
    </body>
</html>
