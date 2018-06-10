package automobiles;

import java.sql.Date;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Customer {

    private final IntegerProperty Order_ID;
    private final int id;

    public Customer(int Order_ID, String c_name, String Address, int Contact, String Model, long Total_Amt, int BooKing_Amt, Date ProposedDate,String fuelT, String color1, String status) {
        this.Order_ID = new SimpleIntegerProperty(Order_ID);
        this.c_name = new SimpleStringProperty(c_name);
        this.Address = new SimpleStringProperty(Address);
        this.Contact = new SimpleLongProperty(Contact);
        this.Model = new SimpleStringProperty(Model);
        this.ProposedDate = new SimpleStringProperty(ProposedDate.toString());
        this.Total_Amt = new SimpleLongProperty(Total_Amt);
        this.BooKing_Amt = new SimpleIntegerProperty(BooKing_Amt);
        this.fuelT = new SimpleStringProperty(fuelT);
        this.color1 = new SimpleStringProperty(color1);
        this.status = new SimpleStringProperty(status);
        this.id = Order_ID;
    }
    private final StringProperty c_name;
    private final StringProperty Address;
    private final LongProperty Contact;
    private final StringProperty Model;
    private final StringProperty fuelT;
    private final StringProperty color1;
    private final StringProperty status;

    public StringProperty getColor1() {
        return color1;
    }

    public StringProperty getStatus() {
        return status;
    }

    public int getOID() {
        return id;
    }

    public IntegerProperty getOrder_ID() {
        return Order_ID;
    }

    public StringProperty getFuelT() {
        return fuelT;
    }

    private final StringProperty ProposedDate;
    private final LongProperty Total_Amt;
    private final IntegerProperty BooKing_Amt;

    public StringProperty getC_name() {
        return c_name;
    }

    public StringProperty getAddress() {
        return Address;
    }

    public LongProperty getContact() {
        return Contact;
    }

    public StringProperty getModel() {
        return Model;
    }

    public StringProperty getProposedDate() {
        return ProposedDate;
    }

    public LongProperty getTotal_Amt() {
        return Total_Amt;
    }

    public IntegerProperty getBooKing_Amt() {
        return BooKing_Amt;
    }

    public void setOrder_ID(int value) {
        Order_ID.set(value);
    }

    public void setc_name(String value) {
        c_name.set(value);
    }

    public void setAddress(String value) {
        Address.set(value);
    }

    public void setContact(long value) {
        Contact.set(value);
    }

    public void setModel(String value) {
        Model.set(value);
    }

    public void setTotal_Amt(long value) {
        Total_Amt.set(value);
    }

    public void setBooKing_Amt(int value) {
        BooKing_Amt.set(value);
    }

    public void setProposedDate(Date d) {
        String value = d.toString();
        ProposedDate.set(value);
    }

    public void setcolor1(String value) {
        color1.set(value);
    }

    public void setstatus(String value) {
        status.set(value);
    }
    public void setfuelT(String value){
        fuelT.set(value);
    }

    public IntegerProperty Order_IDProperty() {
        return Order_ID;
    }

    public StringProperty c_nameProperty() {
        return c_name;
    }

    public StringProperty AddressProperty() {
        return Address;
    }

    public LongProperty ContactProperty() {
        return Contact;
    }

    public StringProperty ModelProperty() {
        return Model;
    }

    public StringProperty ProposedDateProperty() {
        return ProposedDate;
    }

     public StringProperty fuelTProperty() {
        return fuelT;
    }
    
    public StringProperty color1Property() {
        return color1;
    }

    public StringProperty statusProperty() {
        return status;
    }

    public LongProperty Total_AmtProperty() {
        return Total_Amt;
    }

    public IntegerProperty BooKing_AmtProperty() {
        return BooKing_Amt;
    }
}