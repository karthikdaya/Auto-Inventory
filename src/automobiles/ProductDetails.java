package automobiles;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
public class ProductDetails {
    
    private final StringProperty Model_No;
    private final IntegerProperty cubic;
    private final IntegerProperty seat;
    private final LongProperty price;
    private final String Model;
    public ProductDetails(String Model_No, int cubic, int seat, long price) {
        this.Model_No = new SimpleStringProperty(Model_No);
        this.cubic = new SimpleIntegerProperty(cubic);
        this.seat = new SimpleIntegerProperty(seat);
        this.price = new SimpleLongProperty(price);
        this.Model=Model_No;
    }

    public StringProperty getModel_No() {
        return Model_No;
    }
    public String getModel(){
        return Model;
    }
    public IntegerProperty getCubic() {
        return cubic;
    }

    public IntegerProperty getSeat() {
        return seat;
    }

    public LongProperty getPrice() {
        return price;
    }
    public void setModel_No(String value){
        Model_No.set(value);
    }
    public void setcubic(int value){
        cubic.set(value);
    }
     public void setseat(int value){
        seat.set(value);
    }
     public void setprice(long value){
         price.set(value);
     }
     
      public IntegerProperty cubicProperty() {
        return cubic;
    }

    public IntegerProperty seatProperty() {
        return seat;
    }
     public StringProperty Model_NoProperty() {
        return Model_No;
    }
     public LongProperty priceProperty(){
    return price;
}
}