package com.imooc.jdbc.servlet;

import com.imooc.jdbc.bean.Message;
import com.imooc.jdbc.bean.User;
import com.imooc.jdbc.service.MessageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 消息列表Servlet
 *
 * @version 1.0
 */
@WebServlet(urlPatterns = {"/my/message/list.do", "/deleteMessagePrompt.do", "/editMessagePrompt.do","/editMessage.do"})
public class PersonalMessageListServlet extends HttpServlet {

    private MessageService messageService;

    @Override
    public void init() throws ServletException {
        super.init();
        messageService = new MessageService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathName = request.getServletPath();
        boolean result ;
        if (Objects.equals("/deleteMessagePrompt.do", pathName)) {
            String tempId = request.getParameter("id");
            long id = Long.parseLong(tempId);
            result = messageService.deleteMessage(id);
            if (result) {
                request.setAttribute("deleteResult", true);
                request.getRequestDispatcher("/my/message/list.do").forward(request, response);  //删除成功
                return;
            } else {
                request.setAttribute("deleteResult", true);
                request.getRequestDispatcher("/my/message/list.do").forward(request, response);  //删除失败
                return;
            }
        } else if (Objects.equals("/editMessagePrompt.do", pathName)) {
            String tempId = request.getParameter("id");
            long id = Long.parseLong(tempId);
            Message message = messageService.getMessageById(id);
            request.setAttribute("editMessage", message);
            request.getRequestDispatcher("/WEB-INF/views/biz/edit_message.jsp").forward(request, response);
            return;
        } else if (Objects.equals("/editMessage.do", pathName)) { //修改留言
            Long id = Long.valueOf(request.getParameter("id"));
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            Date createTime = null;
            try {
                createTime = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("createTime"));
            } catch (ParseException e) {
                System.out.println("格式化Birthday字段失败");
                e.printStackTrace();
            }
            User user = (User) request.getSession().getAttribute("user");

            Message message = new Message(id, user.getId(), user.getName(), title, content, createTime);
            result = messageService.UpdateMessage(message);
            if (result) {
                request.getRequestDispatcher("/my/message/list.do").forward(request,response);  //成功返回到我的留言页面
                return;
            }else{
                request.getRequestDispatcher("/WEB-INF/views/biz/404.jsp").forward(request, response);
                return;
            }
        }
        String pageStr = request.getParameter("page");//当前页码
        int page = 1;//页码默认值为1
        if (null != pageStr && (!"".equals(pageStr))) {
            try {
                page = Integer.parseInt(pageStr);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        //默认显示我的留言信息
        User user = (User) request.getSession().getAttribute("user");
        List<Message> personalMessages = messageService.getMessagesByUser(page, 5, user.getName());//分页查询登录用户全部留言
        int count = messageService.countUserMessage(user.getName());
        int last = count % 5 == 0 ? (count / 5) : ((count / 5) + 1);
        request.setAttribute("last", last);
        request.setAttribute("personalMessages", personalMessages);
        request.setAttribute("page", page);
        request.getRequestDispatcher("/WEB-INF/views/biz/user_message_list.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    public void destroy() {
        super.destroy();
        messageService = null;
    }

}
