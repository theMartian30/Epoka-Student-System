module EpokaSystem {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	requires java.sql;
	
	opens applicationE to javafx.graphics, javafx.fxml, javafx.base;
}
