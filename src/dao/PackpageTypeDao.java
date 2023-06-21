/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connection.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

/**
 *
 * @author KhoaTran
 */
public class PackpageTypeDao {

    Connection con = MyConnection.getConnetion();
    PreparedStatement ps;
    Statement st;
    ResultSet rs;

    public void createPackageType(int typeId, String name, String description) {
        String sql = "INSERT INTO package_type (type_id, type_name, type_description) VALUES (?, ?, ?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, typeId);
            ps.setString(2, name);
            ps.setString(3, description);

            if (ps.executeUpdate() > 0) {
                System.out.println("New package type added successfully.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void readPackageType(int typeId) {
        String sql = "SELECT * FROM package_type WHERE type_id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, typeId);
            rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("type_id");
                String name = rs.getString("type_name");
                String description = rs.getString("type_description");

                System.out.println("Type ID: " + id);
                System.out.println("Name: " + name);
                System.out.println("Description: " + description);
            } else {
                System.out.println("Package type with type_id " + typeId + " not found.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void updatePackageType(int typeId, String name, String description) {
        String sql = "UPDATE package_type SET type_name = ?, type_description = ? WHERE type_id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, description);
            ps.setInt(3, typeId);

            if (ps.executeUpdate() > 0) {
                System.out.println("Package type updated successfully.");
            } else {
                System.out.println("Package type with type_id " + typeId + " not found.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deletePackageType(int typeId) {
        String sql = "DELETE FROM package_type WHERE type_id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, typeId);

            if (ps.executeUpdate() > 0) {
                System.out.println("Package type deleted successfully.");
            } else {
                System.out.println("Package type with type_id " + typeId + " not found.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
