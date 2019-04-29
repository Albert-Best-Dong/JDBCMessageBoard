//package com.imooc.jdbc.common;
//
//import com.imooc.jdbc.bean.User;
//
//import javax.servlet.http.HttpServletRequest;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//
//public final class AddOrEditUtil {
//
//    public static void save(HttpServletRequest request) {
//        String name = request.getParameter("name");
//        String password = request.getParameter("password");
//        String realName = request.getParameter("realName");
//        String birthday = request.getParameter("birthday");
//        String phone = request.getParameter("phone");
//        String address = request.getParameter("address");
//        User user = new User();
////        user.setId(id);
//        user.setName(name);
//        user.setPassword(password);
//        user.setRealName(realName);
//        try {
//            user.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(birthday));
//        } catch (ParseException e) {
//            System.out.println("格式化Birthday字段失败");
//            e.printStackTrace();
//        }
//        user.setPhone(phone);
//        user.setAddress(address);
//    }
//
//}
