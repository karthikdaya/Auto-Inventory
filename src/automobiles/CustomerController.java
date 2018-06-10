package automobiles;

import static automobiles.DBUtil.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

public class CustomerController implements Initializable {

    Parent root;
    Stage dialogStage;
    private final ObservableList<String> status1 = FXCollections.observableArrayList("SOLD", "QUEUE");
    private ObservableList<Customer> data;
    private ObservableList<Customer> data1;
    private Connection con = null;
    private final ObservableList<String> filt = FXCollections.observableArrayList("Order_ID", "Cust_Name", "Contact", "Model_No");
    @FXML
    private JFXDatePicker P_date;
    @FXML
    private TextField cname;
    @FXML
    private TextField Model;
    @FXML
    private TextField contact_no;
    @FXML
    private TextField Book_amt;
    @FXML
    private TextField Addr;
    @FXML
    private TextField Color;
    @FXML
    private TextField fuel;
    @FXML
    private JFXButton deletebutton;
    @FXML
    private TableView<Customer> order;

    @FXML
    private TableColumn<Customer, String> order_id;

    @FXML
    private TableColumn<Customer, String> FUEL;

    @FXML
    private TableColumn<Customer, String> Cname;

    @FXML
    private TableColumn<Customer, String> Address;

    @FXML
    private TableColumn<Customer, String> contact_No;

    @FXML
    private TableColumn<Customer, String> moddel_no;

    @FXML
    private TableColumn<Customer, Date> Expected_Delivery;

    @FXML
    private TableColumn<Customer, String> book_amt;

    @FXML
    private TableColumn<Customer, String> Total_Amt;

    @FXML
    private TableColumn<Customer, String> COLOR;
    @FXML
    private TableColumn<Customer, String> STATUS;
    @FXML
    private ComboBox<String> searchcb;

    @FXML
    private JFXTextField searchtx;

    @FXML
    private JFXButton search;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        searchcb.setItems(filt);
        order.setEditable(true);
        STATUS.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    @FXML
    private void InsertCustomer(ActionEvent event) throws SQLException, IOException {
        Connection conn = dbConnect();
        long pri = 0;
        Date d = Date.valueOf(P_date.getValue().toString());
        PreparedStatement ps1, ps;
        String c_name = cname.getText().toUpperCase();
        String add = Addr.getText().toUpperCase();
        long cont = Long.parseLong(contact_no.getText());
        String Model_NO = Model.getText().toUpperCase();
        int book = Integer.parseInt(Book_amt.getText());
        String color = Color.getText().toUpperCase();
        String fl = fuel.getText().toUpperCase();

        String subquery = "select Price from `inventory`.`product` where ModelNO= ? ";
        ps1 = conn.prepareStatement(subquery);
        ps1.setString(1, Model_NO);

        ResultSet rs = ps1.executeQuery();
        if (rs.next()) {
            pri = rs.getLong(1);
        }
        try {
            String query = "INSERT INTO `inventory`.`customer_order`(`Cust_Name`, `Address`, `Contact`, `Model_No`, `Booking_Amt`, `Total_Amt`, `ProposedDelivery`,`Fuel`, `color`) VALUES(?,?, ?, ?,?,?,?,?,?)";
            ps = conn.prepareStatement(query);
            ps.setString(1, c_name);
            ps.setString(2, add);
            ps.setLong(3, cont);
            ps.setString(4, Model_NO);
            ps.setInt(5, book);
            ps.setLong(6, pri);
            ps.setDate(7, d);
            ps.setString(8, fl);
            ps.setString(9, color);
            int n = ps.executeUpdate();
            infoBox("Customer Details Inserted", "Success", null);
            ps.close();
            dbDisconnect(conn);
        } catch (NumberFormatException e) {
            System.out.println(e);

        }
    }

    @FXML
    private void LoadCustomer(ActionEvent event) throws SQLException, IOException {

        try {
            con = dbConnect();
            data = FXCollections.observableArrayList();
            ResultSet rs = con.createStatement().executeQuery("select * from inventory.customer_order");
            data = DisplayTable(rs);
        } catch (SQLException e) {
            System.out.println("ERROR" + e);
        }
        order.setItems(null);
        order.setItems(data);
        dbDisconnect(con);
    }

    @FXML
    private void Back(ActionEvent event) throws SQLException, IOException {
        Node source = (Node) event.getSource();
        dialogStage = (Stage) source.getScene().getWindow();
        dialogStage.close();
        Parent root1 = FXMLLoader.load(getClass().getResource("Home_page.fxml"));
        Scene scene = new Scene(root1);
        dialogStage.setScene(scene);
        dialogStage.show();
    }

    @FXML
    private void SearchItems(ActionEvent event) throws SQLException, IOException {
        try {
            con = dbConnect();
            PreparedStatement ps1;
            data1 = FXCollections.observableArrayList();
            String sc = searchcb.getSelectionModel().getSelectedItem();
            String tx = searchtx.getText();
            String query = "select * from customer_order where " + sc + " = ? ";
            ps1 = con.prepareStatement(query);
            switch (sc) {
                case "Model_No":
                case "Cust_Name":
                    ps1.setString(1, tx);
                    break;
                case "Contact":
                    long va = Long.parseLong(tx);
                    ps1.setLong(1, va);
                    break;
                case "Order_ID":
                    int val = Integer.parseInt(tx);
                    ps1.setInt(1, val);
                    break;
                default:
                    break;
            }
            ResultSet rs1 = ps1.executeQuery();
            data1 = DisplayTable(rs1);
            order.setItems(null);
            order.setItems(data1);
        } catch (SQLException e) {
            System.out.println("ERROR" + e);
        }
        dbDisconnect(con);
    }

    @FXML
    void DeleteSelected(ActionEvent event) {
        Connection CON = dbConnect();
        Customer selectedItem = order.getSelectionModel().getSelectedItem();
        int oid = selectedItem.getOID();
        String qy = "delete from `inventory`.`customer_order` where Order_ID=?";

        try {
            int i = 0;
            PreparedStatement dps = CON.prepareStatement(qy);
            dps.setInt(1, oid);
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

        order.getItems().remove(selectedItem);
    }

    private ObservableList<Customer> DisplayTable(ResultSet rs1) {
        data1 = FXCollections.observableArrayList();
        try {
            while (rs1.next()) {
                data1.add(new Customer(rs1.getInt(1), rs1.getString(2), rs1.getString(3), rs1.getInt(4), rs1.getString(5), rs1.getInt(6), rs1.getInt(7), rs1.getDate(8), rs1.getString(9), rs1.getString(10), rs1.getString(11)));
            }
        } catch (SQLException e) {
            System.out.println("ERROR" + e);
        }

        order_id.setCellValueFactory(new PropertyValueFactory<>("Order_ID"));
        Cname.setCellValueFactory(new PropertyValueFactory<>("c_name"));
        Address.setCellValueFactory(new PropertyValueFactory<>("Address"));
        contact_No.setCellValueFactory(new PropertyValueFactory<>("Contact"));
        moddel_no.setCellValueFactory(new PropertyValueFactory<>("Model"));
        Expected_Delivery.setCellValueFactory(new PropertyValueFactory<>("ProposedDate"));
        Total_Amt.setCellValueFactory(new PropertyValueFactory<>("Total_Amt"));
        book_amt.setCellValueFactory(new PropertyValueFactory<>("BooKing_Amt"));
        FUEL.setCellValueFactory(new PropertyValueFactory<>("fuelT"));
        COLOR.setCellValueFactory(new PropertyValueFactory<>("color1"));
        STATUS.setCellValueFactory(new PropertyValueFactory<>("status"));
        STATUS.setOnEditCommit((TableColumn.CellEditEvent<Customer, String> event2) -> {
            Customer user = event2.getRowValue();
            user.setstatus(event2.getNewValue());
            updateData("Status", event2.getNewValue(), user.getOID());
        });
        return data1;

    }

    private void updateData(String column, String newValue, int id) {
        try {
            Connection connection = dbConnect();
            PreparedStatement stmt = connection.prepareStatement("UPDATE `inventory`.`customer_order` SET " + column + " = ? WHERE Order_ID = ? ");
            stmt.setString(1, newValue);
            stmt.setInt(2, id);
            stmt.execute();
        } catch (SQLException ex) {
            System.err.println("Error");
            ex.printStackTrace(System.err);
        }
    }
}
