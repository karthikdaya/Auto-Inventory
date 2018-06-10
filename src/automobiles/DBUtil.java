package automobiles;

import java.sql.*;
import javafx.scene.control.Alert;

public class DBUtil {

    private static Connection conn = null;
    private static final String user = "root";
    private static final String pass = "root123";
    private static final String conString = "jdbc:mysql://localhost:3306/inventory";

    public static Connection dbConnect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(conString, user, pass);
            System.out.print("Database is connected !");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.print("Do not connect to DB - Error:" + e);
        }
        return conn;
    }

    public static void dbDisconnect(Connection con) throws SQLException {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (SQLException e) {
            throw e;
        }
    }

    public static void infoBox(String infoMessage, String titleBar, String headerMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        alert.setContentText(infoMessage);
        alert.showAndWait();
    }

}
