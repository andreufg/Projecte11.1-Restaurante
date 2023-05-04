module es.progcipfpbatoi {
    requires javafx.controls;
    requires javafx.fxml;
    opens es.progcipfpbatoi.controller to javafx.fxml;
    opens es.progcipfpbatoi to javafx.fxml;
    exports es.progcipfpbatoi;
}
