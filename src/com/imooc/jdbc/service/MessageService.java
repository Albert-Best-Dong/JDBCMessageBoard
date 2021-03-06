package com.imooc.jdbc.service;

import com.imooc.jdbc.bean.Message;
import com.imooc.jdbc.dao.MessageDAO;

import java.util.Date;
import java.util.List;

/**
 * 消息Service
 *
 * @version 1.0
 */
public class MessageService {

    private MessageDAO messageDAO;

    public MessageService() {
        messageDAO = new MessageDAO();
    }

    public boolean addMessage(Message message) {
        message.setCreateTime(new Date());
        return messageDAO.save(message);
    }

    /**
     * 分页查询全部留言
     * @param page 当前页码
     * @param pageSize 每页记录数
     * @return
     */
    public List<Message> getMessages(int page, int pageSize) {
        return messageDAO.getMessages(page, pageSize);
    }

    /**
     * 计算所有留言数量
     * @return
     */
    public int countMessages() {
        return messageDAO.countMessages();
    }
    /**
    *   查询当前用户的留言
    *   @author albert
    *   @date 2019/4/30
    *
    */
    public List<Message> getMessagesByUser(int page, int pageSize, String name) {
        return messageDAO.getMessageByUser(page, pageSize, name);
    }

    /**
     * 计算当前用户所有留言数量
     * @return
     */
    public int countUserMessage(String name) {
        return messageDAO.countUserMessage(name);
    }
    /**
     * 删除留言
     * @return
     */
    public boolean deleteMessage(long id) {
        return messageDAO.deleteMessage(id);
    }
    /**
     * 通过留言id获得留言
     * @return
     */
    public Message getMessageById(long id) {
        return messageDAO.getMessageById(id);
    }
    /**
     * 修改留言
     * @return
     */
    public boolean UpdateMessage(Message message) {
        return messageDAO.updateMessage(message);
    }
}
