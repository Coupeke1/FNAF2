module be.fnaf2 {
    requires javafx.controls;
    requires javafx.fxml;
    exports be.fnaf2 to javafx.graphics;

    opens be.fnaf2 to javafx.fxml;
    exports be.fnaf2.view.main to javafx.graphics;

}
