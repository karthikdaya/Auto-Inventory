package automobiles;

import static automobiles.DBUtil.dbConnect;
import static automobiles.DBUtil.dbDisconnect;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class StockController implements Initializable {

    Parent root;
    Stage dialogStage;
    @FXML
    private TableView<Stock> stock;
    @FXML
    private TableColumn<Stock, String> MODEL;
    @FXML
    private TableColumn<Stock, String> CUBIC;
    @FXML
    private TableColumn<Stock, String> SEAT;
    @FXML
    private TableColumn<Stock, String> COLOR;
    @FXML
    private TableColumn<Stock, String> QUANTITY;
    @FXML
    private TableColumn<Stock, String> FUEL;
    private ObservableList<Stock> data1;

    @FXML
    void Load(ActionEvent event) throws SQLException {
        Connection conn = null;
        try {
            conn = dbConnect();
            data1 = FXCollections.observableArrayList();
            ResultSet rs1 = conn.createStatement().executeQuery("select pd.ModelNO,p.CC,p.SeatCapacity,pd.Color,pd.Fuel,count(*)\n"
                    + "from `inventory`.`productdescription` pd,`inventory`.`product` p\n"
                    + "where pd.ModelNo=p.ModelNO\n"
                    + "group by pd.ModelNo, pd.Fuel,pd.Color;");
            while (rs1.next()) {
                data1.add(new Stock(rs1.getString(1), rs1.getInt(2), rs1.getInt(3), rs1.getString(4), rs1.getString(5), rs1.getInt(6)));
            }
        } catch (SQLException e) {
            System.out.println("ERROR" + e);
        }
        MODEL.setCellValueFactory(new PropertyValueFactory<>("Model_No"));
        CUBIC.setCellValueFactory(new PropertyValueFactory<>("cubic"));
        SEAT.setCellValueFactory(new PropertyValueFactory<>("seat"));
        QUANTITY.setCellValueFactory(new PropertyValueFactory<>("qty"));
        COLOR.setCellValueFactory(new PropertyValueFactory<>("color"));
        FUEL.setCellValueFactory(new PropertyValueFactory<>("fuel"));
        stock.setItems(null);
        stock.setItems(data1);
        dbDisconnect(conn);
    }

    @FXML
    void Back(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        dialogStage = (Stage) source.getScene().getWindow();
        dialogStage.close();
        Parent root1 = FXMLLoader.load(getClass().getResource("Home_page.fxml"));
        Scene scene = new Scene(root1);
        dialogStage.setScene(scene);
        dialogStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
