
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

/**
 *
 * @author tuan1
 */
public class UserDao {
    Connection con = MyConnection.getConnetion();
    PreparedStatement ps;
    Statement st;
    ResultSet rs;

public int getMaxRow(){
int row=0;
        try {
            st= con.createStatement();
            rs=st.executeQuery("select max(uid) from user");
            while(rs.next()){
            row = rs.getInt(1);}
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row+1;
    }
//check email already exits
public boolean isEmailExist(String email){
        try {
            ps = con.prepareStatement("select * from user where email =?");
            ps.setString(1, email);
            rs = ps.executeQuery();
            if(rs.next()){
            return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            
        }
return false;
}
//check phone number already exists
public boolean isPhoneExist(String phone){
        try {
            ps = con.prepareStatement("select * from user where phone =?");
            ps.setString(1, phone);
            rs = ps.executeQuery();
            if(rs.next()){
            return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            
        }
return false;
}
//insert data  into user table
public void insert(String id,String username,String email,String pass,String phone,String seq,String ans,String address ){
String sql="insert into user values(?,?,?,?,?,?) where role_id=3 ";
        try {
            ps= con.prepareStatement(sql);
            ps.setString(1,id);
            ps.setString(2, username);
            ps.setString(3, email);
            ps.setString(4, pass);
            ps.setString(5, phone);
            ps.setString(6, seq);
            ps.setString(7, ans);
            ps.setString(6, address);
            
            if(ps.executeUpdate()>0){
                JOptionPane.showMessageDialog(null, "User  added  successfully");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
}
}
