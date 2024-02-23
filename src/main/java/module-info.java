module be.fnaf2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens be.fnaf2 to javafx.fxml;
    exports be.fnaf2;
}