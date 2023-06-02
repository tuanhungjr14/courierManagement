package dao;

import connection.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ForgotPasswordDao {

    Connection con = MyConnection.getConnetion();
    PreparedStatement ps;
    Statement st;
    ResultSet rs;

    
    public boolean isEmailExist(String email) {
        try {
            ps = con.prepareStatement("select * from user where email = ?");
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                ForgotPasswords.jComboBox1.setText(rs.getString(6));
                return true;
            }else{
            JOptionPane.showMessageDialog(null, "Email address doesn't exist")           
            }

        } catch (SQLException ex) {
            Logger.getLogger(ForgotPasswordDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
