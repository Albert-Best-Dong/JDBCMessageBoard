package com.imooc.jdbc.servlet;

import com.imooc.jdbc.bean.User;
import com.imooc.jdbc.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet("/registUser.do")
public class RegiterServlet extends HttpServlet {
    private UserService userService;
    @Override
    public void init() throws ServletException {
        super.init();
        userService = new UserService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        Long id = Long.valueOf(request.getParameter("id"));
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String realName = request.getParameter("realName");
        String birthday = request.getParameter("birthday");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String code = request.getParameter("code");
        String checkCode = (String) request.getSession().getAttribute("kcode");
        User user = new User();
//        user.setId(id);
        user.setName(name);
        user.setPassword(password);
        user.setRealName(realName);
        try {
            user.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(birthday));
        } catch (ParseException e) {
            System.out.println("格式化Birthday字段失败");
            e.printStackTrace();
        }
        user.setPhone(phone);
        user.setAddress(address);
        userService.regist(user);
        request.getRequestDispatcher("/WEB-INF/views/biz/login.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
