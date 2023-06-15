
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

/**
 *
 * @author tuan1
 */
public class OrderDao {
     Connection con = MyConnection.getConnetion();
    PreparedStatement ps;
    Statement st;
    ResultSet rs;
    
    public void insert(String id,String username,String email,String pass,String phone,String seq,String ans,String address ){
String sql="insert into user values(?,?,?,?,?,?,?,?)  ";
        try {
            ps= con.prepareStatement(sql);
            ps.setString(1,id);
            ps.setString(2, username);
            ps.setString(3, email);
            ps.setString(4, pass);
            ps.setString(5, phone);
            ps.setString(6, seq);
            ps.setString(7, ans);
            ps.setString(8, address);
            
            if(ps.executeUpdate()>0){
                JOptionPane.showMessageDialog(null, "Order  added  successfully");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
}
}
