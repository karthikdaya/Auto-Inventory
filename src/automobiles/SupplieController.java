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

public class SupplieController implements Initializable {

    Parent root;
    Stage dialogStage;
    @FXML
    private TextField S_Id;

    @FXML
    private TextField S_name;

    @FXML
    private TextField S_Add;

    @FXML
    private TextField S_contact;
    @FXML
    private TableView<Supplier> Supply;

    @FXML
    private TableColumn<Supplier, String> Supplie_Id;

    @FXML
    private TableColumn<Supplier, String> S_Name;

    @FXML
    private TableColumn<Supplier, String> S_Address;

    @FXML
    private TableColumn<Supplier, String> S_Contact;

    private ObservableList<Supplier> data1;
    @FXML
    private JFXButton deletebutton;

    @FXML
    private void InsertSupplie(ActionEvent event) throws SQLException, IOException {

        Connection con = dbConnect();
        PreparedStatement ps;
        int id = Integer.parseInt(S_Id.getText());
        String name = S_name.getText().toUpperCase();
        String add = S_Add.getText().toUpperCase();
        long contact = Long.parseLong(S_contact.getText());
        String query = "INSERT INTO `inventory`.`supplie` (`Supplier_id`, `Supplier_name`, `Address`, `Contact_NO`) VALUES (?,?,?,?)";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, add);
            ps.setLong(4, contact);
            ps.executeUpdate();
            infoBox("Supplier Details Inserted", "Success", null);
            ps.close();
            dbDisconnect(con);
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
    }

    @FXML
    private void LoadSupplie(ActionEvent event) throws SQLException, IOException {
        Connection conn = null;
        try {
            conn = dbConnect();
            data1 = FXCollections.observableArrayList();
            ResultSet rs1 = conn.createStatement().executeQuery("select * from inventory.supplie");
            while (rs1.next()) {
                data1.add(new Supplier(rs1.getInt(1), rs1.getString(2), rs1.getString(3), rs1.getLong(4)));
            }
        } catch (SQLException e) {
            System.out.println("ERROR" + e);
        }
        Supplie_Id.setCellValueFactory(new PropertyValueFactory<>("Supplie_Id"));
        S_Name.setCellValueFactory(new PropertyValueFactory<>("Supplie_Name"));
        S_Address.setCellValueFactory(new PropertyValueFactory<>("S_Addr"));
        S_Contact.setCellValueFactory(new PropertyValueFactory<>("S_Contact"));
        Supply.setItems(null);
        Supply.setItems(data1);
        dbDisconnect(conn);
    }

    @FXML
    private void back(ActionEvent event) throws SQLException, IOException {
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
        Connection CON = dbConnect();
        Supplier selectedItem = Supply.getSelectionModel().getSelectedItem();
        int sid = selectedItem.getSID();
        String qy = "delete from `inventory`.`supplie` where Supplier_id= ?";

        try {
            int i = 0;
            PreparedStatement dps = CON.prepareStatement(qy);
            dps.setInt(1, sid);
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

        Supply.getItems().remove(selectedItem);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

}