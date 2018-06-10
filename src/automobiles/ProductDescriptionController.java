package automobiles;

import static automobiles.DBUtil.dbConnect;
import static automobiles.DBUtil.infoBox;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ProductDescriptionController implements Initializable {

    Parent root;
    Stage dialogStage;
    static int q;
    public static int count=0;
    @FXML
    private TextField color;

    @FXML
    private TextField Model;

    @FXML
    private TextField Eno;

    @FXML
    private TextField fuel;

    @FXML
    void Back(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        dialogStage = (Stage) source.getScene().getWindow();
        dialogStage.close();
        Parent root1 = FXMLLoader.load(getClass().getResource("Purchase.fxml"));
        Scene scene = new Scene(root1);
        dialogStage.setScene(scene);
        dialogStage.show();
    }

    @FXML
    void InsertProductdesc(ActionEvent event) throws IOException {
        PurchaseController pc = new PurchaseController();
        if(count==q)
        {
           infoBox("Sorry all the products are already inserted", "Success", null);
            Node source = (Node) event.getSource();
        dialogStage = (Stage) source.getScene().getWindow();
        dialogStage.close();
        Parent root1 = FXMLLoader.load(getClass().getResource("Purchase.fxml"));
        Scene scene = new Scene(root1);
        dialogStage.setScene(scene);
        dialogStage.show();
           
        }
         Connection con = dbConnect();
        PreparedStatement ps = null;
        String model = Model.getText();
        int eno = Integer.parseInt(Eno.getText());
        String col = color.getText().toUpperCase();
        String fu = fuel.getText().toUpperCase();
        String query = "INSERT INTO `inventory`.`productdescription` ( `Engine No`, `ModelNO`, `Color`, `Fuel`) VALUES (?,?,?,?)";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, eno);
            ps.setString(2, model);
            ps.setString(3, col);
            ps.setString(4, fu);
            ps.executeUpdate();
            infoBox("Details Inserted", "Success", null);
        } catch (SQLException e) {
            System.out.println("Error" + e);
        }
                count++;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    public void Getqty(int n){
        this.q=n;
    }

}