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
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author KhoaTran
 */
public class StorageDao {

    Connection con = MyConnection.getConnetion();
    PreparedStatement ps;
    ResultSet rs;

    // Tạo mới storage
    public void createStorage(String name, String address) {
        String sql = "INSERT INTO storage ( storage_name, storage_address) VALUES (?, ?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, address);

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Storage  added  successfully");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Đọc thông tin storage
    public void readStorage(int storageId) {
        String sql = "SELECT * FROM storage WHERE storage_id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, storageId);
            rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("storage_id");
                String name = rs.getString("storage_name");
                String address = rs.getString("storage_address");

                System.out.println("Storage ID: " + id);
                System.out.println("Name: " + name);
                System.out.println("Address: " + address);
            } else {
                System.out.println("Storage with storage_id " + storageId + " not found.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void readAllStorage(JTable table) {
        try {
            String sql = "SELECT * FROM storage";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0); // Clear existing rows

            while (rs.next()) {
                int id = rs.getInt("storage_id");
                String name = rs.getString("storage_name");
                String address = rs.getString("storage_address");

                Object[] row = {id, name, address};
                model.addRow(row);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Cập nhật thông tin storage
    public void updateStorage(int storageId, String name, String address) {
        String sql = "UPDATE storage SET storage_name = ?, storage_address = ? WHERE storage_id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, address);
            ps.setInt(3, storageId);

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Storage updated successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "Storage with storage_id " + storageId + " not found.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Xóa storage
    public void deleteStorage(int storageId) {
        String sql = "DELETE FROM storage WHERE storage_id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, storageId);

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Storage deleted successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "Storage with storage_id " + storageId + " not found.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Test tạo mới storage
        StorageDao storageDao = new StorageDao();
//        storageDao.createStorage(2, "Storage 1", "123 Main Street");

        // Test đọc thông tin storage
        storageDao.readStorage(1);

//        // Test cập nhật thông tin storage
//        storageDao.updateStorage(1, "Updated Storage 1", "456 New Street");
//
//        // Test xóa storage
//        storageDao.deleteStorage(1);
    }
}
