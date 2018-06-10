package automobiles;

import static automobiles.DBUtil.dbConnect;
import static automobiles.DBUtil.dbDisconnect;
import static automobiles.DBUtil.infoBox;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ProductDetailsController implements Initializable {

    Parent root;
    Stage dialogStage;

    @FXML
    private TextField mno;

    @FXML
    private TextField CC;

    @FXML
    private TextField Seat;

    @FXML
    private TextField price;
    @FXML
    private JFXButton deletebutton;
    @FXML
    private TableView<ProductDetails> product;

    @FXML
    private TableColumn<ProductDetails, String> MODEL;

    @FXML
    private TableColumn<ProductDetails, String> CUBIC;

    @FXML
    private TableColumn<ProductDetails, String> SEAT;

    @FXML
    private TableColumn<ProductDetails, String> PRICE;

    private ObservableList<ProductDetails> data1;

    @FXML
    void InsertProduct(ActionEvent event) throws SQLException {
        Connection con = dbConnect();
        PreparedStatement ps = null;
        String model = mno.getText().toUpperCase();
        int cc = Integer.parseInt(CC.getText());
        int se = Integer.parseInt(Seat.getText());
        Long p = Long.parseLong(price.getText());
        String query = "INSERT INTO `inventory`.`product` (`ModelNO`, `CC`, `SeatCapacity`, `Price`) VALUES (?,?,?,?)";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, model);
            ps.setInt(2, cc);
            ps.setInt(3, se);
            ps.setLong(4, p);
            ps.executeUpdate();
            infoBox("Product Details Inserted", "Success", null);
        } catch (SQLException e) {
            System.out.println("Error" + e);
        }
    }

    @FXML
    public void HomePage(ActionEvent event) throws SQLException, IOException {
        Node source = (Node) event.getSource();
        dialogStage = (Stage) source.getScene().getWindow();
        dialogStage.close();
        Parent root1 = FXMLLoader.load(getClass().getResource("Home_page.fxml"));
        Scene scene = new Scene(root1);
        dialogStage.setScene(scene);
        dialogStage.show();
    }

    @FXML
    void DeleteSelected(ActionEvent event) {

        deletebutton.setOnAction(e -> {
            Connection CON = dbConnect();
            ProductDetails selectedItem = product.getSelectionModel().getSelectedItem();
            String mod = selectedItem.getModel();
            String qy = "delete from `inventory`.`product` where ModelNO=?";

            try {
                int i = 0;
                PreparedStatement dps = CON.prepareStatement(qy);
                dps.setString(1, mod);
                i = dps.executeUpdate();
                if (i != 0) {
                    infoBox("One Row is deleted", "Success", null);
                } else {
                    infoBox("No Row is deleted", "Success", null);
                }

                dbDisconnect(CON);
            } catch (SQLException ex) {
                Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
            }

            product.getItems().remove(selectedItem);
        });
    }

    public void Loadproduct() throws SQLException {
        Connection conn = null;
        try {
            conn = dbConnect();
            data1 = FXCollections.observableArrayList();
            ResultSet rs1 = conn.createStatement().executeQuery("select * from inventory.product");
            while (rs1.next()) {
                data1.add(new ProductDetails(rs1.getString(1), rs1.getInt(2), rs1.getInt(3), rs1.getLong(4)));
            }
        } catch (SQLException e) {
            System.out.println("ERROR" + e);
        }

        MODEL.setCellValueFactory(new PropertyValueFactory<>("Model_No"));
        CUBIC.setCellValueFactory(new PropertyValueFactory<>("cubic"));
        SEAT.setCellValueFactory(new PropertyValueFactory<>("seat"));
        PRICE.setCellValueFactory(new PropertyValueFactory<>("price"));
        product.setItems(null);
        product.setItems(data1);
        dbDisconnect(conn);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

}