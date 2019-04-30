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
import java.util.List;
import java.util.Objects;

/**
 * 消息列表Servlet
 *
 * @version 1.0
 */
@WebServlet(urlPatterns = {"/my/message/list.do","/deleteMessagePrompt.do"})
public class PersonalMessageListServlet extends HttpServlet {

    private MessageService messageService;

    @Override
    public void init() throws ServletException {
        super.init();
        messageService = new MessageService();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathName = request.getServletPath();
        if (Objects.equals("/deleteMessagePrompt.do", pathName)) {
            int id = Integer.parseInt(request.getParameter("id"));
            messageService.deleteMessage(id);
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
        User user = (User)request.getSession().getAttribute("user");
        List<Message> personalMessages = messageService.getMessagesByUser(page, 5,user.getName());//分页查询登录用户全部留言
        int count = messageService.countUserMessage(user.getName());
        int last = count % 5 == 0 ? (count / 5) : ((count / 5) + 1);
        request.setAttribute("last", last);
        request.setAttribute("personalMessages", personalMessages);
        request.setAttribute("page", page);
        request.getRequestDispatcher("/WEB-INF/views/biz/user_message_list.jsp").forward(request, response);
    }

    @Override
    public void destroy() {
        super.destroy();
        messageService = null;
    }

}
