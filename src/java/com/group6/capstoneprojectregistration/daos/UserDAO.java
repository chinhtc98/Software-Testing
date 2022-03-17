/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group6.capstoneprojectregistration.daos;

import com.group6.capstoneprojectregistration.dtos.GroupDTO;
import com.group6.capstoneprojectregistration.dtos.UserDTO;
import com.group6.capstoneprojectregistration.untils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class UserDAO {

    private static final String GET_USER_BY_EMAIL = " SELECT UserId, Email, Username, Gender, Role, [Group], Isleader FROM [User] WHERE Email = ?";
    private static final String UPDATE_GROUP_USER = " UPDATE [User] SET [Group] = ?, IsLeader = ? WHERE UserId = ?";
    private static final String GET_LIST_USER_BY_GROUP_ID = " SELECT * FROM [User] WHERE [Group] = ?";
    private static final String GET_LIST_NO_GROUP_USER = " SELECT * FROM [User] WHERE [Group] is null AND [Role] = ?";
    private static final String GET_USER_BY_ID = " SELECT * FROM [User] WHERE UserId = ?";
    private static final String ADD_USER_INTO_GROUP = " UPDATE [User] SET [Group] = ? Where UserId = ?";
    private static final String COUNT_STUDENT_IN_GROUP = " SELECT count(*) as Students FROM [User] WHERE [Group] = ? ";

    public int countStudentInGroup(int group) throws SQLException {
        int count = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = COUNT_STUDENT_IN_GROUP;
                stm = conn.prepareStatement(sql);
                stm.setInt(1, group);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int numOfStudent = rs.getInt("Students");
                    count = numOfStudent;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return count;
    }

    public boolean addUserIntoGroup(UserDTO sender, String invitedUserId) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = ADD_USER_INTO_GROUP;
                stm = conn.prepareStatement(sql);
                stm.setInt(1, sender.getGroup().getGroupId());
                stm.setString(2, invitedUserId);
                check = stm.executeUpdate() > 0 ? true : false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return check;
    }

    public UserDTO getStrUserById(String strUserId) throws SQLException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = GET_USER_BY_ID;
                stm = conn.prepareStatement(sql);
                stm.setString(1, strUserId);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String email = rs.getString("Email");
                    String userName = rs.getString("UserName");
                    String gender = rs.getString("Gender");
                    int role = rs.getInt("Role");
                    int group = rs.getInt("Group");
                    boolean isLeader = rs.getBoolean("Isleader");
                    RoleDAO dao = new RoleDAO();
                    GroupDAO grdao = new GroupDAO();
                    user = new UserDTO(strUserId, email, userName, gender, dao.getRole(role), grdao.getGroupById(group), isLeader);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return user;
    }

    public List<UserDTO> getListNoGroupUser(int intRole) throws SQLException {
        List<UserDTO> listUser = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = GET_LIST_NO_GROUP_USER;
                stm = conn.prepareStatement(sql);
                stm.setInt(1, intRole);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String userId = rs.getString("UserId");
                    String email = rs.getString("Email");
                    String userName = rs.getString("Username");
                    String gender = rs.getString("Gender");
                    int role = rs.getInt("role");
                    int group = rs.getInt("Group");
                    boolean isLeader = rs.getBoolean("IsLeader");
                    RoleDAO rlDao = new RoleDAO();
                    GroupDAO grDao = new GroupDAO();
                    listUser.add(new UserDTO(userId, email, userName, gender, rlDao.getRole(role), grDao.getGroupById(group), isLeader));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return listUser;
    }

    public List<UserDTO> getListUserByGroupId(int groupId) throws SQLException {
        List<UserDTO> listUser = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = GET_LIST_USER_BY_GROUP_ID;
                stm = conn.prepareStatement(sql);
                stm.setInt(1, groupId);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String userId = rs.getString("UserId");
                    String email = rs.getString("Email");
                    String userName = rs.getString("UserName");
                    String gender = rs.getString("Gender");
                    int role = rs.getInt("Role");
                    boolean isLeader = rs.getBoolean("IsLeader");
                    RoleDAO rlDao = new RoleDAO();
                    GroupDAO grDao = new GroupDAO();
                    listUser.add(new UserDTO(userId, email, userName, gender, rlDao.getRole(role), grDao.getGroupById(groupId), isLeader));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return listUser;
    }

    public UserDTO getUserByEmail(String email) throws SQLException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            String sql = GET_USER_BY_EMAIL;
            stm = conn.prepareStatement(sql);
            stm.setString(1, email);
            rs = stm.executeQuery();
            if (rs.next()) {
                String userId = rs.getString("UserId");
                String userName = rs.getString("Username");
                String gender = rs.getString("Gender");
                int role = rs.getInt("Role");
                RoleDAO dao = new RoleDAO();
                boolean isLeader = rs.getBoolean("Isleader");
                int group = rs.getInt("Group");
                GroupDAO grdao = new GroupDAO();
                user = new UserDTO(userId, email, userName, gender, dao.getRole(role), grdao.getGroupById(group), isLeader);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return user;
    }

    public boolean updateGroupUser(UserDTO user, GroupDTO group) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = UPDATE_GROUP_USER;
                stm = conn.prepareStatement(sql);
                stm.setInt(1, group.getGroupId());
                stm.setBoolean(2, true);
                stm.setString(3, user.getUserId());
                check = stm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return check;
    }

}
