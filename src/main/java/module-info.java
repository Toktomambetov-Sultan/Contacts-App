module java_fx.contactsapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens java_fx.contactsapp to javafx.fxml;
    exports java_fx.contactsapp;
}