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
        <title>新建留言</title>
        <link rel="stylesheet" href="${pageScope.basePath}/css/bootstrap.min.css">
        <link rel="stylesheet" href="${pageScope.basePath}/css/add.css">
    </head>
    <body>
        <nav class="navbar navbar-default">
            <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand" href="${pageScope.basePath}/message/list.do">
                        慕课网留言板
                    </a>
                </div>
            </div>
        </nav>
        <div class="container">
            <div class="jumbotron">
                <h1>Hello, ${user.name}!</h1>
                <p>既然来了，就说点什么吧</p>
            </div>
            <div class="page-header">
                <h3><small>新建留言</small></h3>
            </div>
            <form class="form-horizontal" action="${pageScope.basePath}/addMessage.do" method="post">
                <div class="form-group">
                    <label for="inputTitle" class="col-sm-2 control-label">标题 ：</label>
                    <div class="col-sm-8">
                        <input name="title" class="form-control" id="inputTitle" placeholder="title">
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputContent" class="col-sm-2 control-label">内容 ：</label>
                    <div class="col-sm-8">
                        <textarea name="content"  class="form-control" rows="3" id="inputContent" placeholder="Content"></textarea>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-primary">发布留言</button>&nbsp;&nbsp;&nbsp;
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <a class="btn btn-default" href="${pageScope.basePath}/message/list.do">查看留言</a>
                    </div>
                </div>
            </form>
        </div>
        <footer class="text-center" >
            copy@imooc
        </footer>
    </body>
</html>
