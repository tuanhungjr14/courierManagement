
package dao;
import connection.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        return row;
    }
}
