/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connection;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;





public class Database {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/"; // Đường dẫn kết nối tới máy chủ cơ sở dữ liệu
        String databaseName = "delivery_management"; // Tên cơ sở dữ liệu mới
        String username = "root"; // Tên người dùng
        String password = "root"; // Mật khẩu

        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement()) {
            // Tạo cơ sở dữ liệu
            String sql = "CREATE DATABASE " + databaseName;
            statement.executeUpdate(sql);
            System.out.println("Cơ sở dữ liệu đã được tạo thành công.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
