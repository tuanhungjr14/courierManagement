
package dao;
import connection.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author tuan1
 */
public class AdminDao {
    Connection con = MyConnection.getConnetion();
    PreparedStatement ps;
    Statement st;
    ResultSet rs;

public int getMaxRow(){
int row=0;
        try {
            st= con.createStatement();
            rs=st.executeQuery("select max(id) from admin");
            while(rs.next()){
            row = rs.getInt(1);}
        } catch (SQLException ex) {
            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row+1;
    }
//check email already exits
public boolean isEmailExist(String email){
        try {
            ps = con.prepareStatement("select * from employees where email =?");
            ps.setString(1, email);
            rs = ps.executeQuery();
            if(rs.next()){
            return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
            
        }
return false;
}
//check phone number already exists
public boolean isPhoneExist(String phone){
        try {
            ps = con.prepareStatement("select * from admin where phone =?");
            ps.setString(1, phone);
            rs = ps.executeQuery();
            if(rs.next()){
            return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
            
        }
return false;
}
//insert data  into user table
public void insert(int id,String username,String email,String pass ){
String sql="insert into admin values(?,?,?,?)  ";
        try {
            ps= con.prepareStatement(sql);
            ps.setInt(1,id);
            ps.setString(2, username);
            ps.setString(3, email);
            ps.setString(4,pass );
            
            
            if(ps.executeUpdate()>0){
                JOptionPane.showMessageDialog(null, "Admin  added  successfully");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
        }
}
   //update user data
    public void update(int id, String username, String email, String pass) {
        String sql = "update employees set username = ?,email = ?, password = ? where id = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, email);
            ps.setString(3, pass);
           
            ps.setInt(4, id);
            
           
            if (ps.executeUpdate() > 0) {

                JOptionPane.showMessageDialog(null, "Admin data successfully updated ");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ForgotPasswordDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //delete user
    public void delete(int id) {
        int x = JOptionPane.showConfirmDialog(null, "Are you sure want to delete this account", "Delete Account", JOptionPane.OK_CANCEL_OPTION, 0);
        if (x == JOptionPane.OK_OPTION) {
            try {
                ps = con.prepareStatement("delete from admin where id = ?");
            } catch (SQLException ex) {
                Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                ps.setInt(1, id);
            } catch (SQLException ex) {
                Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                if (ps.executeUpdate() > 0) {
                    JOptionPane.showMessageDialog(null, "Account deleted");
                }
            } catch (SQLException ex) {
                Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // get user value
    public String[] getAdminValue(int id) {
        String[] value = new String[6];
        try {
            ps = con.prepareStatement("select * from admin where id =?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                value[0] = rs.getString(1);
                value[1] = rs.getString(2);
                value[2] = rs.getString(3);
                value[3] = rs.getString(4);
                
              
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);

        }
        return value;
    }

    //get user id
    public int getAdminId(String email) {
        int id = 0;
        try {
            ps = con.prepareStatement("select id from admin where email =?");
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);

        }
        return id;
    }

    // get user data
    public void getAdminValue(JTable table, String search) {
        String sql = "select * from admin where concat(id, username, email) like ? order by id desc";

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
                
                
                
                model.addRow(row);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
