package automobiles;

import static automobiles.DBUtil.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import static automobiles.DBUtil.dbConnect;
import static automobiles.DBUtil.dbDisconnect;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
public class LoginController implements Initializable {

    Stage dialogStage = new Stage();
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Scene scene;
    Parent root;
    @FXML
    private Label label;

    @FXML
    private TextField textUser;

    @FXML
    private PasswordField textPassword;

    @FXML
    private void handleSignInAction(ActionEvent event) throws SQLException, IOException {
        Connection conn = dbConnect();
        String user = textUser.getText();
        String password = textPassword.getText();
        String sql = "SELECT * FROM inventory.authorize WHERE user_id = ? and password = ?";
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                infoBox("Enter Correct User_Id and Password", "Failed", null);
            } else {
                infoBox("Login Successfull", "Success", null);
                Node source = (Node) event.getSource();
                dialogStage = (Stage) source.getScene().getWindow();
                dialogStage.close();
                Parent root1 = FXMLLoader.load(getClass().getResource("Home_page.fxml"));
                scene = new Scene(root1);
                dialogStage.setScene(scene);
                dialogStage.show();
                dbDisconnect(conn);

            }
        } catch (IOException | SQLException e) {
            throw e;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}