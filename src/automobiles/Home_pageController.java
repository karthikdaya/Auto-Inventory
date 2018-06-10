package automobiles;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Home_pageController implements Initializable {

    Stage dialogStage = new Stage();
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Scene scene, scene1, scene2;
    Parent root;

    @FXML
    private Button NewOrder;

    @FXML
    private Button product;

    @FXML
    private Button supplier;

    @FXML
    private Button purchase;

    @FXML
    private Button stock;

    @FXML
    private Button report;

    @FXML
    private Button lout;

    @FXML
    void LogOut(ActionEvent event) throws IOException {
        SwitchScenes("Login.fxml", event);
    }

    @FXML
    void NewOrderAction(ActionEvent event) throws IOException {
        SwitchScenes("Customer.fxml", event);
    }

    @FXML
    public void SupplierAction(ActionEvent event) throws IOException {
        SwitchScenes("Supplie.fxml", event);
    }

    @FXML
    public void PurchaseAction(ActionEvent event) throws IOException {
        SwitchScenes("Purchase.fxml", event);
    }

    @FXML
    public void StockAction(ActionEvent event) throws IOException {
        SwitchScenes("Stock.fxml", event);
    }

    @FXML
    public void ProductAction(ActionEvent event) throws IOException {
        SwitchScenes("ProductDetails.fxml", event);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void SwitchScenes(String file, ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        dialogStage = (Stage) source.getScene().getWindow();
        dialogStage.close();
        Parent root1 = FXMLLoader.load(getClass().getResource(file));
        Scene scene = new Scene(root1);
        dialogStage.setScene(scene);
        dialogStage.show();
    }

}