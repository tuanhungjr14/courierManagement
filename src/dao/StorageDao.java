
package dao;

import connection.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    public void getStorageValue(JTable table, String search) {
        String sql = "select * from storage where concat(storage_id,storage_name,storage_address) like ? order by storage_id desc";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + search + "%");
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
            Logger.getLogger(EmployeeDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String[] args) {
        // Test tạo mới storage
        StorageDao storageDao = new StorageDao();
        storageDao.createStorage("Storage 1", "123 Main Street");

        // Test đọc thông tin storage
        storageDao.readStorage(1);

//        // Test cập nhật thông tin storage
//        storageDao.updateStorage(1, "Updated Storage 1", "456 New Street");
//
//        // Test xóa storage
//        storageDao.deleteStorage(1);
    }
}
