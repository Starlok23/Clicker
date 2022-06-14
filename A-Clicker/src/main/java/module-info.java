module es.iesfranciscodelosrios.A_Clicker {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.sql;
	requires java.xml.bind;
	requires java.desktop;
	requires javafx.base;
	requires javafx.graphics;

    opens es.iesfranciscodelosrios.A_Clicker to javafx.fxml;
    opens es.iesfranciscodelosrios.Connection to java.xml.bind;
    opens es.iesfranciscodelosrios.Model to javafx.base;
    exports es.iesfranciscodelosrios.A_Clicker;
}
