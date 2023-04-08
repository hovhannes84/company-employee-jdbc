import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConectionProvider {
    private final static DBConectionProvider INSTANCE = new DBConectionProvider();
    private Connection connection;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/company_employee?useUnicode=true";
    private static final String USERNAME ="root";
    private static final String PASSWORD ="root";
    private DBConectionProvider(){

    }
    public Connection getConnection(){
        try {
            if (connection == null || connection.isClosed()){
                connection = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static DBConectionProvider getInstance(){
        return INSTANCE;
    }
}
