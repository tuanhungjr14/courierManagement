/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import dao.OrderDao;
import dao.PackageDao;
import java.sql.Timestamp;

/**
 *
 * @author KhoaTran
 */
public class OrderController {

    private PackageDao packageDao;
    private OrderDao orderDao;

    public OrderController() {
        packageDao = new PackageDao();
        orderDao = new OrderDao();
    }

    public void createOrderWithPackage(int userId, String weight, String size, int typeId, String content, String deliveryType, String cost, Timestamp pickupTime, String customerName, String customerAddress, String customerPhone) {
        // Tạo gói hàng mới
//        packageDao.createPackage( weight, size, typeId, content, deliveryType, cost);

        // Kiểm tra gói hàng đã được tạo thành công chưa
        int packageIdExists = packageDao.createPackage(weight, size, typeId, content, deliveryType, cost);

        if (packageIdExists > 0) {
            // Gói hàng đã tồn tại, tiến hành tạo đơn hàng mới
            orderDao.createOrder(userId, packageIdExists, pickupTime, customerName, customerAddress, customerPhone);
//            orderDao.
        } else {
            System.out.println("Failed to create package. Cannot proceed with order creation.");
        }
    }

    public static void main(String[] args) {
//        OrderController orderController = new OrderController();

        // Test tạo đơn hàng mới với gói hàng
//        int orderId = 2;
//        String userId = "1";
//        String weight = "10 kg";
//        String size = "30x20x10";
//        int typeId = 11;
//        String content = "Books";
//        String deliveryType = "Express";
//        String cost = "$20";
//        Timestamp pickupTime = Timestamp.valueOf("2023-06-21 10:00:00");
//        String customerName = "John Doe";
//        String customerAddress = "123 Street";
//        String customerPhone = "123456789";
//        orderController.createOrderWithPackage(orderId, userId, weight, size, typeId, content, deliveryType, cost, pickupTime, customerName, customerAddress, customerPhone);
        // Test đọc thông tin đơn hàng
//        orderController.orderDao.readOrder(orderId);
        // Test đọc thông tin gói hàng
//        orderController.packageDao.readPackage(orderId);
    }
}
