package com.imooc.jdbc.dao;

import com.imooc.jdbc.bean.Message;
import com.imooc.jdbc.common.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 消息DAO
 *
 * @version 1.0
 */
public class MessageDAO {

    /**
     * 保存留言信息
     * @param message
     * @return
     */
    public boolean save(Message message) {
        Connection conn = ConnectionUtil.getConnection();
        String sql = "insert into message(user_id, username, title, content, create_time) values (?, ?, ?, ?, ?)";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, message.getUserId());
            stmt.setString(2, message.getUsername());
            stmt.setString(3, message.getTitle());
            stmt.setString(4, message.getContent());
            stmt.setTimestamp(5, new Timestamp(message.getCreateTime().getTime()));
            stmt.execute();
        } catch (Exception e) {
            System.out.println("保存留言信息失败。");
            e.printStackTrace();
            return false;
        } finally {
            ConnectionUtil.release(null, stmt, conn);
        }
        return true;
    }

    /**
     * 分页查询全部留言
     * @param page 当前页码
     * @param pageSize 每页记录数
     * @return
     */
    public List<Message> getMessages(int page, int pageSize) {
        Connection conn = ConnectionUtil.getConnection();
        String sql = "select * from message order by create_time desc limit ?, ?";//limit m, n：从第m条开始，取出总共n条记录
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Message> messages = new ArrayList<>();
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, (page - 1) * pageSize);
            stmt.setInt(2, pageSize);
            rs = stmt.executeQuery();
            while (rs.next()) {
                messages.add(new Message(rs.getLong("id"),
                        rs.getLong("user_id"),
                        rs.getString("username"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getTimestamp("create_time")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.release(rs, stmt, conn);
        }
        return messages;
    }

    /**
     * 计算所有留言数量
     * @return
     */
   public int countMessages() {
       Connection conn = ConnectionUtil.getConnection();
       String sql = "select count(*) total from message";
       PreparedStatement stmt = null;
       ResultSet rs = null;
       try {
           stmt = conn.prepareStatement(sql);
           rs = stmt.executeQuery();
           while (rs.next()) {
               return rs.getInt("total");
           }
       } catch (SQLException e) {
           e.printStackTrace();
       } finally {
           ConnectionUtil.release(rs, stmt, conn);
       }
       return 0;
   }
    /**
     *   查询当前用户的留言
     *   @author albert
     *   @date 2019/4/30
     *
     */
   public List<Message> getMessageByUser(int page, int pageSize, String name) {
       Connection conn = ConnectionUtil.getConnection();
       String sql = "select * from message where username = ? order by create_time desc limit ?, ?";//limit m, n：从第m条开始，取出总共n条记录
       PreparedStatement stmt = null;
       ResultSet rs = null;
       List<Message> messages = new ArrayList<>();
       try {
           stmt = conn.prepareStatement(sql);
           stmt.setString(1,name);
           stmt.setInt(2, (page - 1) * pageSize);
           stmt.setInt(3, pageSize);

           rs = stmt.executeQuery();
           while (rs.next()) {
               messages.add(new Message(rs.getLong("id"),
                       rs.getLong("user_id"),
                       rs.getString("username"),
                       rs.getString("title"),
                       rs.getString("content"),
                       rs.getTimestamp("create_time")));
           }
       } catch (SQLException e) {
           e.printStackTrace();
       } finally {
           ConnectionUtil.release(rs, stmt, conn);
       }
        return messages;
    }

    /**
     * 计算当前用户所有留言数量
     * @return
     */
    public int countUserMessage(String name) {
        Connection conn = ConnectionUtil.getConnection();
        String sql = "select count(*) total from message where username = ?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,name);
            rs = stmt.executeQuery();
            while (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.release(rs, stmt, conn);
        }
        return 0;
    }
    /**
     * 删除留言
     * @return
     */
    public boolean deleteMessage(long id) {
        Connection conn = ConnectionUtil.getConnection();
        String sql = "delete from message where id = ?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1,id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("删除失败");
            return false;
        } finally {
            ConnectionUtil.release(rs, stmt, conn);
        }
        return true;
    }
    /**
     * 通过留言id获得留言
     * @return
     */
    public Message getMessageById(long id) {
        Connection conn = ConnectionUtil.getConnection();
        String sql = "select * from message where id = ?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Message message = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1,id);


            rs = stmt.executeQuery();
            if(rs.next()) {

               message = new Message(rs.getLong("id"),
                        rs.getLong("user_id"),
                        rs.getString("username"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getTimestamp("create_time"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.release(rs, stmt, conn);
        }
        return message;
    }
    /**
     * 修改留言
     * @return
     */
    public boolean updateMessage(Message message) {
        Connection conn = ConnectionUtil.getConnection();
        String sql = "UPDATE message SET user_id = ?, username = ?, title = ?, content = ?, create_time = ? WHERE id = ?";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, message.getUserId());
            stmt.setString(2, message.getUsername());
            stmt.setString(3, message.getTitle());
            stmt.setString(4, message.getContent());
            stmt.setDate(5, new Date(message.getCreateTime().getTime()));

            stmt.setLong(6, message.getId());
            stmt.execute();
        } catch (SQLException e) {
            System.out.println("留言更新失败。");
            e.printStackTrace();
            return false;
        } finally {
            ConnectionUtil.release(null, stmt, conn);
        }
        return true;
    }
}
