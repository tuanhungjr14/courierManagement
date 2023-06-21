/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author KhoaTran
 */
import connection.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StatusDao {

    Connection con = MyConnection.getConnetion();
    PreparedStatement ps;
    ResultSet rs;

    // Tạo mới status
    public void createStatus(int statusId, String statusName) {
        String sql = "INSERT INTO status (status_id, status_name) VALUES (?, ?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, statusId);
            ps.setString(2, statusName);

            if (ps.executeUpdate() > 0) {
                System.out.println("New status added successfully.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Đọc thông tin status
    public void readStatus(int statusId) {
        String sql = "SELECT * FROM status WHERE status_id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, statusId);
            rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("status_id");
                String statusName = rs.getString("status_name");

                System.out.println("Status ID: " + id);
                System.out.println("Status Name: " + statusName);
            } else {
                System.out.println("Status with status_id " + statusId + " not found.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Cập nhật thông tin status
    public void updateStatus(int statusId, String statusName) {
        String sql = "UPDATE status SET status_name = ? WHERE status_id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, statusName);
            ps.setInt(2, statusId);

            if (ps.executeUpdate() > 0) {
                System.out.println("Status updated successfully.");
            } else {
                System.out.println("Status with status_id " + statusId + " not found.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Xóa status
    public void deleteStatus(int statusId) {
        String sql = "DELETE FROM status WHERE status_id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, statusId);

            if (ps.executeUpdate() > 0) {
                System.out.println("Status deleted successfully.");
            } else {
                System.out.println("Status with status_id " + statusId + " not found.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Hàm main để kiểm tra
    public static void main(String[] args) {
        StatusDao statusDao = new StatusDao();

        // Test tạo mới status
        int statusId = 1;
        String statusName = "Đang vận chuyển";
        statusDao.createStatus(1, "Chuẩn bị hàng");
        statusDao.createStatus(2, "Đang vận chuyển");
        statusDao.createStatus(3, "Thành công");

//        // Test đọc thông tin status
//        statusDao.readStatus(statusId);
//
//        // Test cập nhật thông tin status
//        statusName = "Inactive";
//        statusDao.updateStatus(statusId, statusName);
//
//        // Test xóa status
//        statusDao.deleteStatus(statusId);

    }
}
