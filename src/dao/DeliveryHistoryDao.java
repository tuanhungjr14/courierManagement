/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connection.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 *
 * @author KhoaTran
 */
public class DeliveryHistoryDao {

    Connection con = MyConnection.getConnetion();
    PreparedStatement ps;
    ResultSet rs;

    // Tạo mới delivery history
    public void createDeliveryHistory(int deliveryId, int statusId, Timestamp deliveryTime, int storageId) {
        String sql = "INSERT INTO delivery_history (delivery_id, status_id, delivery_time, storage_id) VALUES (?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, deliveryId);
            ps.setInt(2, statusId);
            ps.setTimestamp(3, deliveryTime);
            ps.setInt(4, storageId);

            if (ps.executeUpdate() > 0) {
                System.out.println("New delivery history added successfully.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Đọc thông tin delivery history
    public void readDeliveryHistory(int deliveryId) {
        String sql = "SELECT * FROM delivery_history WHERE delivery_id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, deliveryId);
            rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("delivery_id");
                int statusId = rs.getInt("status_id");
                Timestamp deliveryTime = rs.getTimestamp("delivery_time");
                int storageId = rs.getInt("storage_id");

                System.out.println("Delivery ID: " + id);
                System.out.println("Status ID: " + statusId);
                System.out.println("Delivery Time: " + deliveryTime);
                System.out.println("Storage ID: " + storageId);
            } else {
                System.out.println("Delivery history with delivery_id " + deliveryId + " not found.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    // Cập nhật thông tin delivery history

    public void updateDeliveryHistory(int deliveryId, int statusId, Timestamp deliveryTime, int storageId) {
        String sql = "UPDATE delivery_history SET status_id = ?, delivery_time = ?, storage_id = ? WHERE delivery_id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, statusId);
            ps.setTimestamp(2, deliveryTime);
            ps.setInt(3, storageId);
            ps.setInt(4, deliveryId);

            if (ps.executeUpdate() > 0) {
                System.out.println("Delivery history updated successfully.");
            } else {
                System.out.println("Delivery history with delivery_id " + deliveryId + " not found.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    // Xóa delivery history

    public void deleteDeliveryHistory(int deliveryId) {
        String sql = "DELETE FROM delivery_history WHERE delivery_id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, deliveryId);

            if (ps.executeUpdate() > 0) {
                System.out.println("Delivery history deleted successfully.");
            } else {
                System.out.println("Delivery history with delivery_id " + deliveryId + " not found.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
