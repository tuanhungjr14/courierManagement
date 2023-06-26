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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PackageDao {

    Connection con = MyConnection.getConnetion();
    PreparedStatement ps;
    ResultSet rs;

    // Tạo package mới
    public int createPackage(String weight, String size, int typeId, String content, String deliveryType, String cost) {
        String sql = "INSERT INTO package (weight, size, type_id, content, delivery_type, cost) VALUES (?, ?, ?, ?, ?, ?)";
        int packageId = -1; // Giá trị mặc định nếu không lấy được ID

        try {
            ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, weight);
            ps.setString(2, size);
            ps.setInt(3, typeId);
            ps.setString(4, content);
            ps.setString(5, deliveryType);
            ps.setString(6, cost);

            if (ps.executeUpdate() > 0) {
                System.out.println("New package added successfully.");
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    packageId = generatedKeys.getInt(1);
                    System.out.println("Generated package ID: " + packageId);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return packageId;
    }
//kiểm tra exist

    public boolean packageExists(int packageId) {
        String sql = "SELECT COUNT(*) FROM package WHERE package_id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, packageId);
            rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    // Đọc thông tin package
    public void readPackage(int packageId) {
        String sql = "SELECT * FROM package WHERE package_id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, packageId);
            rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("package_id");
                String weight = rs.getString("weight");
                String size = rs.getString("size");
                int typeId = rs.getInt("type_id");
                String content = rs.getString("content");
                String deliveryType = rs.getString("delivery_type");
                String cost = rs.getString("cost");

                System.out.println("Package ID: " + id);
                System.out.println("Weight: " + weight);
                System.out.println("Size: " + size);
                System.out.println("Type ID: " + typeId);
                System.out.println("Content: " + content);
                System.out.println("Delivery Type: " + deliveryType);
                System.out.println("Cost: " + cost);
            } else {
                System.out.println("Package with package_id " + packageId + " not found.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Cập nhật thông tin package
    public void updatePackage(int packageId, String weight, String size, int typeId, String content, String deliveryType, String cost) {
        String sql = "UPDATE package SET weight = ?, size = ?, type_id = ?, content = ?, delivery_type = ?, cost = ? WHERE package_id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, weight);
            ps.setString(2, size);
            ps.setInt(3, typeId);
            ps.setString(4, content);
            ps.setString(5, deliveryType);
            ps.setString(6, cost);
            ps.setInt(7, packageId);

            if (ps.executeUpdate() > 0) {
                System.out.println("Package updated successfully.");
            } else {
                System.out.println("Package with package_id " + packageId + " not found.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Xóa package
    public void deletePackage(int packageId) {
        String sql = "DELETE FROM package WHERE package_id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, packageId);

            if (ps.executeUpdate() > 0) {
                System.out.println("Package deleted successfully.");
            } else {
                System.out.println("Package with package_id " + packageId + " not found.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Kiểm tra giá trị đầu vào hợp lệ
    public boolean validateInput(int packageId, String weight, String size, int typeId, String content, String deliveryType, String cost) {
        // Kiểm tra các ràng buộc hợp lệ
        if (packageId <= 0 || weight.isEmpty() || size.isEmpty() || typeId <= 0 || content.isEmpty() || deliveryType.isEmpty() || cost.isEmpty()) {
            System.out.println("Invalid input. Please provide valid values for all fields.");
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        PackageDao packageDao = new PackageDao();

        // Test tạo package mới
        int packageId = 2;
        String weight = "10 kg";
        String size = "30x20x10";
        int typeId = 1;
        String content = "Books";
        String deliveryType = "Express";
        String cost = "$20";
        if (packageDao.validateInput(packageId, weight, size, typeId, content, deliveryType, cost)) {
            packageDao.createPackage(weight, size, typeId, content, deliveryType, cost);
        }
        

        // Test đọc thông tin package
        packageDao.readPackage(packageId);

        // Test cập nhật thông tin package
//        weight = "12 kg";
//        size = "35x25x15";
//        typeId = 2;
//        content = "Electronics";
//        deliveryType = "Standard";
//        cost = "$15";
//        if (packageDao.validateInput(packageId, weight, size, typeId, content, deliveryType, cost)) {
//            packageDao.updatePackage(packageId, weight, size, typeId, content, deliveryType, cost);
//        }
        // Test xóa package
//        packageDao.deletePackage(packageId);
    }
    public void getOrderValue(JTable table, String search) {
        String sql = "select * from employees where concat(id, username, email, phone) like ? order by id desc";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + search + "%");
            rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            Object[] row;
            while (rs.next()) {
                row = new Object[6];
                row[0] = rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);
                row[5] = rs.getString(6);
                
                
                model.addRow(row);
            }

        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
