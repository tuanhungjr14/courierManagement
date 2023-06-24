package dao;

import connection.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.sql.Timestamp;

/**
 *
 * @author tuan1
 */
public class OrderDao {

    Connection con = MyConnection.getConnetion();
    PreparedStatement ps;
    Statement st;
    ResultSet rs;

    // Tạo order mới
    public int createOrder(int userId, int packageId, Timestamp pickupTime, String customerName, String customerAddress, String customerPhone) {
        String sql = "INSERT INTO orders ( user_id, package_id, pickup_time, customer_name, customer_address, customer_phone) VALUES ( ?, ?, ?, ?, ?, ?)";
        int orderId = -1;
        try {
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, userId);
            ps.setInt(2, packageId);
            ps.setTimestamp(3, pickupTime);
            ps.setString(4, customerName);
            ps.setString(5, customerAddress);
            ps.setString(6, customerPhone);

            if (ps.executeUpdate() > 0) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    orderId = generatedKeys.getInt(1);
                }
                JOptionPane.showMessageDialog(null, "New order added successfully. Order ID: " + orderId);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return orderId;
    }

    // Đọc thông tin order
    public void readOrder(int orderId) {
        String sql = "SELECT * FROM orders WHERE order_id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, orderId);
            rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("order_id");
                String userId = rs.getString("user_id");
                int packageId = rs.getInt("package_id");
                Timestamp pickupTime = rs.getTimestamp("pickup_time");
                String customerName = rs.getString("customer_name");
                String customerAddress = rs.getString("customer_address");
                String customerPhone = rs.getString("customer_phone");

                System.out.println("Order ID: " + id);
                System.out.println("User ID: " + userId);
                System.out.println("Package ID: " + packageId);
                System.out.println("Pickup Time: " + pickupTime);
                System.out.println("Customer Name: " + customerName);
                System.out.println("Customer Address: " + customerAddress);
                System.out.println("Customer Phone: " + customerPhone);
            } else {
                System.out.println("Order with order_id " + orderId + " not found.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Cập nhật thông tin order
    public void updateOrder(int orderId, String userId, int packageId, Timestamp pickupTime, String customerName, String customerAddress, String customerPhone) {
        String sql = "UPDATE orders SET user_id = ?, package_id = ?, pickup_time = ?, customer_name = ?, customer_address = ?, customer_phone = ? WHERE order_id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, userId);
            ps.setInt(2, packageId);
            ps.setTimestamp(3, pickupTime);
            ps.setString(4, customerName);
            ps.setString(5, customerAddress);
            ps.setString(6, customerPhone);
            ps.setInt(7, orderId);

            if (ps.executeUpdate() > 0) {
                System.out.println("Order updated successfully.");
            } else {
                System.out.println("Order with order_id " + orderId + " not found.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Xóa order
    public void deleteOrder(int orderId) {
        String sql = "DELETE FROM orders WHERE order_id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, orderId);

            if (ps.executeUpdate() > 0) {
                System.out.println("Order deleted successfully.");
            } else {
                System.out.println("Order with order_id " + orderId + " not found.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Hàm main để kiểm tra
    public static void main(String[] args) {
        OrderDao orderDao = new OrderDao();

        // Test tạo order mới
        int orderId = 2;
        int userId = 1;
        int packageId = 1;
        Timestamp pickupTime = Timestamp.valueOf("2023-06-21 10:00:00");
        String customerName = "John Doe";
        String customerAddress = "123 Street";
        String customerPhone = "123456789";
        orderDao.createOrder(userId, packageId, pickupTime, customerName, customerAddress, customerPhone);

        // Test đọc thông tin order
        orderDao.readOrder(orderId);

//        // Test cập nhật thông tin order
//        userId = "user456";
//        packageId = 2;
//        pickupTime = Timestamp.valueOf("2023-06-21 12:00:00");
//        customerName = "Jane Smith";
//        customerAddress = "456 Avenue";
//        customerPhone = "987654321";
//        orderDao.updateOrder(orderId, userId, packageId, pickupTime, customerName, customerAddress, customerPhone);
//
//        // Test xóa order
//        orderDao.deleteOrder(orderId);
    }

}
