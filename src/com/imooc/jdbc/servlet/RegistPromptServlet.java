package com.imooc.jdbc.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 跳转到登录页面的Servlet
 *
 * @version 1.0
 */
@WebServlet("/regPrompt.do")
public class RegistPromptServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/regist_user.jsp").forward(request, response);
    }

}
