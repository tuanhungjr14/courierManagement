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
public class CustomerAddressDao {

    Connection con = MyConnection.getConnetion();
    PreparedStatement ps;
    ResultSet rs;

    // Tạo mới địa chỉ khách hàng
    public void createCustomerAddress(int userId, String address) {
        String sql = "INSERT INTO customer_address (user_id, address) VALUES (?, ?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setString(2, address);

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Thêm địa chỉ khách hàng thành công");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Đọc thông tin địa chỉ khách hàng
    public void readCustomerAddress(int userId) {
        String sql = "SELECT * FROM customer_address WHERE user_id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("user_id");
                String address = rs.getString("address");

                System.out.println("User ID: " + id);
                System.out.println("Địa chỉ: " + address);
            } else {
                System.out.println("Không tìm thấy địa chỉ khách hàng với user_id " + userId);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void readAllCustomerAddresses(JTable table) {
        try {
            String sql = "SELECT * FROM customer_address";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0); // Xóa các dòng hiện tại

            while (rs.next()) {
                int id = rs.getInt("user_id");
                String address = rs.getString("address");

                Object[] row = { id, address };
                model.addRow(row);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Cập nhật thông tin địa chỉ khách hàng
    public void updateCustomerAddress(int userId, String address) {
        String sql = "UPDATE customer_address SET address = ? WHERE user_id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, address);
            ps.setInt(2, userId);

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Cập nhật địa chỉ khách hàng thành công");
            } else {
                JOptionPane.showMessageDialog(null, "Không tìm thấy địa chỉ khách hàng với user_id " + userId);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Xóa địa chỉ khách hàng
    public void deleteCustomerAddress(int userId) {
        String sql = "DELETE FROM customer_address WHERE user_id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, userId);

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Xóa địa chỉ khách hàng thành công");
            } else {
                JOptionPane.showMessageDialog(null, "Không tìm thấy địa chỉ khách hàng với user_id " + userId);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Test tạo mới địa chỉ khách hàng
        CustomerAddressDao addressDao = new CustomerAddressDao();
        addressDao.createCustomerAddress(1, "123 Main Street");

        // Test đọc thông tin địa chỉ khách hàng
        addressDao.readCustomerAddress(1);

//        // Test cập nhật thông tin địa chỉ khách hàng
//        addressDao.updateCustomerAddress(1, "456 New Street");
//
//        // Test xóa địa chỉ khách hàng
//        addressDao.deleteCustomerAddress(1);
    }

    public void readCustomerAddress(JTable jTable1) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
