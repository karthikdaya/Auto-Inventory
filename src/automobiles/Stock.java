package automobiles;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
public class Stock {
    
     private final StringProperty Model_No;
    private final IntegerProperty cubic;
    private final IntegerProperty seat;
    private final StringProperty color;
     private final StringProperty fuel;
      private final IntegerProperty qty;

    public Stock(String Model_No, int cubic, int seat, String color, String fuel, int qty) {
        this.Model_No = new SimpleStringProperty(Model_No);
        this.cubic = new SimpleIntegerProperty(cubic);
        this.seat = new SimpleIntegerProperty(seat);
        this.color = new SimpleStringProperty(color);
        this.fuel = new SimpleStringProperty(fuel);
        this.qty = new SimpleIntegerProperty(qty);
    }

    public StringProperty getModel_No() {
        return Model_No;
    }

    public IntegerProperty getCubic() {
        return cubic;
    }

    public IntegerProperty getSeat() {
        return seat;
    }

    public StringProperty getColor() {
        return color;
    }

    public StringProperty getFuel() {
        return fuel;
    }

    public IntegerProperty getQty() {
        return qty;
    }
     public void setcubic(int value){
        cubic.set(value);
    }
      public void setModel_No(String value){
        Model_No.set(value);
    }
       public void setseat(int value){
        seat.set(value);
    }
        public void setcolor(String value){
        color.set(value);
    }
         public void setfuel(String value){
        fuel.set(value);
    }
          public void setqty(int value){
        qty.set(value);
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
      public StringProperty colorProperty() {
        return color;
    }
      public StringProperty fuelProperty() {
        return fuel;
    }
    public IntegerProperty qtyProperty() {
        return qty;
    }
}