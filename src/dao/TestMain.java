/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author KhoaTran
 */
public class TestMain {

    public static void main(String[] args) {
        PackpageTypeDao packageTypeDao = new PackpageTypeDao();
         packageTypeDao.createPackageType(1, "Type 1", "Description 1");
        packageTypeDao.readPackageType(1);
        packageTypeDao.updatePackageType(1, "Updated Type 1", "Updated Description 1");
//        packageTypeDao.deletePackageType(1);

//        OrderDao order = new OrderDao();
//        order.insert(1, "", email, pass, phone, seq, ans, address);
    }
}
