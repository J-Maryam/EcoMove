package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DbFunctions {

    private static DbFunctions instance;
    private Connection con;

    private DbFunctions() {}

    public static DbFunctions getInstance() {
        if (instance == null) {
            instance = new DbFunctions();
        }
        return instance;
    }

    public Connection connectToDb( String dbName, String user, String pass) {
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbName, user, pass);

            if (con != null) {
                System.out.println("Connected to PostgreSQL database");
            }
            else {
                System.out.println("Failed to connect to PostgreSQL database");
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return con;
    }
}
