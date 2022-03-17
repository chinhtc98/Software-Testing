/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group6.capstoneprojectregistration.daos;

import com.group6.capstoneprojectregistration.dtos.GroupDTO;
import com.group6.capstoneprojectregistration.untils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author admin
 */
public class GroupDAO {

    private static final String INSERT = " INSERT INTO [Group] (Name, IsApproved) VALUES (?,?)";
    private static final String GET_GROUP_BY_NAME = " SELECT * FROM [Group] WHERE Name=?";
    private static final String GET_GROUP_NAME = " SELECT Name FROM [Group] WHERE GroupId=?";
    private static final String CHECK_DUPLICATE = " SELECT Name FROM [Group] WHERE Name=? ";
    private static final String UPDATE_PROJECTID = " UPDATE [Group] SET ProjectId=? WHERE Name=? ";

    public boolean isDuplicateGroupName(String groupName) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(CHECK_DUPLICATE);
                stm.setString(1, groupName);
                rs = stm.executeQuery();
                if (rs.next()) {
                    check = true;
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
        return check;
    }

    public GroupDTO getGroupById(int groupId) throws SQLException {
        GroupDTO group = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = GET_GROUP_NAME;
                stm = conn.prepareStatement(sql);
                stm.setInt(1, groupId);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String name = rs.getString("Name");

                    group = new GroupDTO(groupId, name);
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

        return group;
    }

    public GroupDTO getGroupByName(String groupName) throws SQLException {
        GroupDTO group = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_GROUP_BY_NAME);
                stm.setString(1, groupName);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int groupId = rs.getInt("GroupId");
                    boolean isApproved = rs.getBoolean("IsApproved");
                    String projectId = rs.getString("ProjectId");
                    group = new GroupDTO(groupId, groupName, isApproved, projectId);
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

        return group;
    }

    public boolean insertGroupName(String groupName) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(INSERT);
                stm.setString(1, groupName);
                stm.setBoolean(2, false);
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

    public boolean updateProjectId(String projectId, String name) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = UPDATE_PROJECTID;
                stm = conn.prepareStatement(sql);
                stm.setString(1, projectId);
                stm.setString(2, name);
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
