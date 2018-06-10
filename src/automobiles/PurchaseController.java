package automobiles;

import static automobiles.DBUtil.dbConnect;
import static automobiles.DBUtil.dbDisconnect;
import static automobiles.DBUtil.infoBox;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class PurchaseController implements Initializable {

    Parent root;
    Stage dialogStage;
    public static int count = 0;
    @FXML
    private TextField S_ID;

    @FXML
    private TextField Model;

    @FXML
    private TextField Qty;

    @FXML
    private TextField Total_Amt;

    @FXML
    private JFXDatePicker dp;

    @FXML
    private TextField Pid;
    @FXML
    private TableView<Purchase> purchase;

    @FXML
    private TableColumn<Purchase, String> P_Id;

    @FXML
    private TableColumn<Purchase, String> S_Id;

    @FXML
    private TableColumn<Purchase, String> P_model;
    @FXML
    private TableColumn<Purchase, String> qty;
    @FXML
    private TableColumn<Purchase, String> date;
    @FXML
    private JFXButton deletebutton;

    @FXML
    private TableColumn<Purchase, String> Total;
    private ObservableList<Purchase> data1;
    private ObservableList<Purchase> data;
    private final ObservableList<String> purc = FXCollections.observableArrayList("PurchaseId", "Model", "SupplierId");

    @FXML
    private ComboBox<String> searchcbp;

    @FXML
    private JFXTextField searchtxp;

    @FXML
    private JFXButton search;

    @FXML
    public void InsertPurchase(ActionEvent event) throws IOException, SQLException {
        Connection con = dbConnect();
        PreparedStatement ps;
        int id1 = Integer.parseInt(Pid.getText());
        int id = Integer.parseInt(S_ID.getText());
        String model = Model.getText().toUpperCase();
        int qty = Integer.parseInt(Qty.getText());
        Date d = Date.valueOf(dp.getValue().toString());
        String query = "INSERT INTO `inventory`.`purchase` ( `PurchaseId`,`DateOfPurchase`, `Quantity`, `Model`, `SupplierId`) VALUES(?,?,?,?,?)";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, id1);
            ps.setDate(2, d);
            ps.setInt(3, qty);
            ps.setString(4, model);
            ps.setInt(5, id);
            ps.executeUpdate();
            infoBox("Purchase Details Inserted", "Success", null);
            ps.close();
             String proc = "{call Purchase_Amt(?,?)}";
        CallableStatement cs = con.prepareCall(proc);
        cs.setString(1, model);
        cs.setInt(2,id1);

        ResultSet rs = cs.executeQuery();
            dbDisconnect(con);
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
        ProductDescriptionController pdc = new ProductDescriptionController();
        pdc.Getqty(qty);
        SwitchScenes("ProductDescription.fxml", event);
    }

    @FXML
    void LoadPurchase(ActionEvent event) throws SQLException {
        LoadPurchase();
    }

    public void LoadPurchase() throws SQLException {
        Connection conn = null;
        try {
            conn = dbConnect();
            data1 = FXCollections.observableArrayList();
            ResultSet rs1 = conn.createStatement().executeQuery("select * from inventory.purchase");
            while (rs1.next()) {
                data1.add(new Purchase(rs1.getInt(1), rs1.getString(2), rs1.getInt(3), rs1.getLong(4), rs1.getString(5), rs1.getInt(6)));
            }
        } catch (SQLException e) {
            System.out.println("ERROR" + e);
        }

        S_Id.setCellValueFactory(new PropertyValueFactory<>("Supplie_Id"));
        P_Id.setCellValueFactory(new PropertyValueFactory<>("Purchase_Id"));
        P_model.setCellValueFactory(new PropertyValueFactory<>("Model_No"));
        date.setCellValueFactory(new PropertyValueFactory<>("DATE"));
        qty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        Total.setCellValueFactory(new PropertyValueFactory<>("Amount"));
        purchase.setItems(null);
        purchase.setItems(data1);
        dbDisconnect(conn);
    }

    @FXML
    private void SearchPurchase(ActionEvent event) throws SQLException, IOException {
        Connection con = null;
        try {
            con = dbConnect();
            PreparedStatement ps1 = null;
            data = FXCollections.observableArrayList();
            String sc = searchcbp.getSelectionModel().getSelectedItem();
            String tx = searchtxp.getText();
            String query = "select * from purchase where " + sc + " = ? ";
            if (sc.equals("Model")) {
                ps1 = con.prepareStatement(query);
                ps1.setString(1, tx);

            } else if (sc.equals("SupplierId") || sc.equals("PurchaseId")) {
                int va = Integer.parseInt(tx);
                ps1 = con.prepareStatement(query);
                ps1.setInt(1, va);
            }
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()) {
                data.add(new Purchase(rs1.getInt(1), rs1.getString(2), rs1.getInt(3), rs1.getLong(4), rs1.getString(5), rs1.getInt(6)));
            }
        } catch (SQLException e) {
            System.out.println("ERROR" + e);
        }
        S_Id.setCellValueFactory(new PropertyValueFactory<>("Supplie_Id"));
        P_Id.setCellValueFactory(new PropertyValueFactory<>("Purchase_Id"));
        P_model.setCellValueFactory(new PropertyValueFactory<>("Model_No"));
        date.setCellValueFactory(new PropertyValueFactory<>("DATE"));
        qty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        Total.setCellValueFactory(new PropertyValueFactory<>("Amount"));
        purchase.setItems(null);
        purchase.setItems(data);
        dbDisconnect(con);
    }

    @FXML
    public void Back(ActionEvent event) throws SQLException, IOException {
        SwitchScenes("Home_page.fxml", event);
    }

    @FXML
    void AddProduct(ActionEvent event) throws IOException {
        SwitchScenes("ProductDescription.fxml", event);
    }

    @FXML
    void DeleteSelected(ActionEvent event) {

        deletebutton.setOnAction(e -> {
            Connection CON = dbConnect();
            Purchase selectedItem = purchase.getSelectionModel().getSelectedItem();
            int pid = selectedItem.getPID();
            String qy = "delete from `inventory`.`purchase` where PurchaseId=?";

            try {
                int i = 0;
                PreparedStatement dps = CON.prepareStatement(qy);
                dps.setInt(1, pid);
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

            purchase.getItems().remove(selectedItem);
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        searchcbp.setItems(purc);
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