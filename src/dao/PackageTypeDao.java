/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connection.MyConnection;
import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author KhoaTran
 */
public class PackageTypeDao {

    Connection con = MyConnection.getConnetion();
    PreparedStatement ps;
    Statement st;
    ResultSet rs;

    public boolean createPackageType(String name, String description) {
        String sql = "INSERT INTO package_type ( type_name, type_description) VALUES ( ?, ?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, description);

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "New package type added successfully.");
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return false;
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

    /**
     *
     * @return
     */
    public void readAllPackageTypes(JTable table) {

        try {
            String sql = "SELECT * FROM package_type";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            Object[] row;
            while (rs.next()) {
                row = new Object[3];
                row[0] = rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PackageTypeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String[] readAllPackageTypes() throws SQLException {
        ArrayList<String> packageTypes = new ArrayList<>();

        try {
            String sql = "SELECT * FROM package_type";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt(1);
                String packageName = rs.getString(2);
                String formattedPackage = id + ". " + packageName;
                packageTypes.add(formattedPackage);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PackageTypeDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return packageTypes.toArray(new String[0]);
    }

    public void updatePackageType(int typeId, String name, String description) {
        String sql = "UPDATE package_type SET type_name = ?, type_description = ? WHERE type_id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, description);
            ps.setInt(3, typeId);

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Package type updated successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "Package type with type_id " + typeId + " not found.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deletePackageType(int typeId) {
        String sql = "DELETE FROM package_type WHERE type_id = ?";
        int x = JOptionPane.showConfirmDialog(null, "Delete this Package type?");
        if (x == JOptionPane.OK_OPTION) {
            try {
                ps = con.prepareStatement(sql);
                ps.setInt(1, typeId);

                if (ps.executeUpdate() > 0) {
                    JOptionPane.showMessageDialog(null, "Package type with type_id " + typeId + " has been deleted");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Package type with type_id " + typeId + " not found.");
                ex.printStackTrace();
            }
        }

    }
}
