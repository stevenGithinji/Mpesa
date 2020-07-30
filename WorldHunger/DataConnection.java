package WorldHunger;

import javafx.stage.Window;

import javax.swing.*;
import java.util.Properties;
import java.sql.*;

public class DataConnection {

    private static boolean successfullConnection=false;

    public static Connection getConnection(){

        Connection con=null;
        try {
            String url = "jdbc:mysql://127.0.0.1:3306/worldhunger?serverTimezone=UTC";
            Properties info = new Properties();
            info.put("user","root");
            info.put("password", "120352");
            con = DriverManager.getConnection(url, info);

            if(con != null) {
                successfullConnection=true;
                System.out.println("DB connected");
            }

        }catch (Exception ex){
            System.out.println("DB Error: " + ex.getMessage());
        }
        return con;
    }

    public static boolean isSuccessfullConnection() {
        return successfullConnection;
    }
}
