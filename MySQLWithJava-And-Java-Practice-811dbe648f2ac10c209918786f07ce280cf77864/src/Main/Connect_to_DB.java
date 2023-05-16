package Main;
import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connect_to_DB {

    private static String servername = "localhost";
    private static String username = "root";
    private static String dbname  = "getting_data";
    private static Integer portnumber  = 3306;
    private static String password = "peter";

    public static Connection getConnection()
    {
        Connection con = null;

        MysqlDataSource datasource = new MysqlDataSource();

        datasource.setServerName(servername);
        datasource.setUser(username);
        datasource.setPassword(password);
        datasource.setDatabaseName(dbname);
        datasource.setPortNumber(portnumber);

        try {
            con = datasource.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(" Get Connection -> " + Connect_to_DB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return con;
    }

}