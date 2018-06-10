package automobiles;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Purchase {

    private final IntegerProperty Supplie_Id;
    private final IntegerProperty Purchase_Id;
    private final StringProperty Model_No;
    private final StringProperty DATE;
    private final IntegerProperty qty;
    private final int pid;

    public Purchase(int Purchase_Id,String DATE, int qty, Long Amount,String Model_No,int Supplie_Id ) {
        this.Supplie_Id = new SimpleIntegerProperty(Supplie_Id);
        this.Purchase_Id = new SimpleIntegerProperty(Purchase_Id);
        this.Model_No = new SimpleStringProperty(Model_No);
        this.DATE = new SimpleStringProperty(DATE);
        this.qty = new SimpleIntegerProperty(qty);
        this.Amount = new SimpleLongProperty(Amount);
        this.pid=Purchase_Id;
    }

    public StringProperty getDATE() {
        return DATE;
    }
    private final LongProperty Amount;

    public IntegerProperty getSupplie_Id() {
        return Supplie_Id;
    }

    public IntegerProperty getPurchase_Id() {
        return Purchase_Id;
    }

    public StringProperty getModel_No() {
        return Model_No;
    }
    public int getPID(){
        return pid;
    }

    public IntegerProperty getQty() {
        return qty;
    }

    public LongProperty getAmount() {
        return Amount;
    }

    public void setSupplie_Id(int value) {
        Supplie_Id.set(value);
    }

    public void setPurchase_Id(int value) {
        Purchase_Id.set(value);
    }

    public void setModel_No(String value) {
        Model_No.set(value);
    }

    public void setDATE(String value) {
        DATE.set(value);
    }

    public void setQty(int value) {
        qty.set(value);
    }

    public void setAmount(Long value) {
        Amount.set(value);
    }

    public IntegerProperty Purchase_IdProperty() {
        return Purchase_Id;
    }

    public IntegerProperty Supplie_IdProperty() {
        return Supplie_Id;
    }

    public StringProperty DATEProperty() {
        return DATE;
    }

    public IntegerProperty qtyProperty() {
        return qty;
    }

    public LongProperty AmountProperty() {
        return Amount;
    }

    public StringProperty Model_NoProperty() {
        return Model_No;
    }
}