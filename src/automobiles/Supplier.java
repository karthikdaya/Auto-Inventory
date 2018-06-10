package automobiles;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Supplier {

    private final IntegerProperty Supplie_Id;
    private final StringProperty Supplie_Name;
    private final StringProperty S_Addr;
    private final LongProperty S_Contact;
    private final int sid;

    public Supplier(int Supplie_Id, String Supplie_Name, String S_Addr, long S_Contact) {
        this.Supplie_Id = new SimpleIntegerProperty(Supplie_Id);
        this.Supplie_Name = new SimpleStringProperty(Supplie_Name);
        this.S_Addr = new SimpleStringProperty(S_Addr);
        this.S_Contact = new SimpleLongProperty(S_Contact);
        this.sid = Supplie_Id;
    }

    public IntegerProperty getSupplie_Id() {
        return Supplie_Id;
    }

    public StringProperty getSupplie_Name() {
        return Supplie_Name;
    }

    public StringProperty getS_Addr() {
        return S_Addr;
    }

    public int getSID() {
        return sid;
    }

    public LongProperty getS_Contact() {
        return S_Contact;
    }

    public void setSupplie_Id(int value) {
        Supplie_Id.set(value);
    }

    public void setSupplie_Name(String value) {
        Supplie_Name.set(value);
    }

    public void setS_Addr(String value) {
        S_Addr.set(value);
    }

    public void setS_Contact(long value) {
        S_Contact.set(value);
    }

    public IntegerProperty Supplie_IdProperty() {
        return Supplie_Id;
    }

    public StringProperty Supplie_NameProperty() {
        return Supplie_Name;
    }

    public StringProperty S_AddrProperty() {
        return S_Addr;
    }

    public LongProperty S_ContactProperty() {
        return S_Contact;
    }
}

