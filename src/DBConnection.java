/*
 * DBConnection.java
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Mohammed Raamizuddin
 */
public class DBConnection 
{
    private static Connection conn;
    private static final String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASS = "Raamiz@251001";
    /** Creates a new instance of DBConnection */
    private DBConnection() 
    {
        initConnection();
    }
    public static Connection getDBConnection()
    {
        if(conn == null)
            new DBConnection();
        return conn; 
    }
    public void initConnection()
    {
        try 
        {
            try 
            {
                Class.forName(MYSQL_DRIVER);
                String connectioURL = "jdbc:mysql://localhost:3306/keystroke";
                conn = DriverManager.getConnection(connectioURL, DATABASE_USER, DATABASE_PASS);
            } 
            catch (SQLException ex) 
            {
                ex.printStackTrace();
            }
        } 
        catch (ClassNotFoundException ex) 
        {
            ex.printStackTrace();
        }  
    }
}