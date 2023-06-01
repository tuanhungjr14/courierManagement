package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author tuan1
 */
public class MyConnection {

    public static final String username = "root";
    public static final String password = "";
    public static final String url = "jdbc:mysql://localhost:3306/delivery_management";
    public static Connection con = null;

    public static Connection getConnetion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
        } catch (Exception ex) {

                JOptionPane.showMessageDialog(null, "" + ex, "", JOptionPane.WARNING_MESSAGE);
        }
        return con;
    }
}
