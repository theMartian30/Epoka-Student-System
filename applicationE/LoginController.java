package applicationE;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginController implements Initializable {
	@FXML
	private ComboBox<String> combo_box;

	@FXML
	private Label epoka_label;

	@FXML
	private Button exit_btn;

	@FXML
	private Button login_btn;

	@FXML
	private ImageView logo;

	@FXML
	private PasswordField password;

	@FXML
	private ImageView sign_logo;

	@FXML
	private TextField username;

	// TOOLS FOR DATABASE

	private Connection connect;
	private PreparedStatement prepare;
	private ResultSet result;
	String sql;
	
	//Stage variables for position
	
	private double x = 0;
	private double y = 0;

	public void exit() {
		System.exit(0);
	}

	@FXML
	String select() {
		if (combo_box.getSelectionModel().getSelectedItem() == null)
			return "Not selected";

		else {
			String s = combo_box.getSelectionModel().getSelectedItem().toString();
			return s;
		}
	}

	public void textfieldDesign() {
		if (username.isFocused()) {
			username.setStyle("-fx-background-color:#fff;" + "-fx-border-width:2px");
			password.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			combo_box.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
		} else if (password.isFocused()) {
			username.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			password.setStyle("-fx-background-color:#fff;" + "-fx-border-width:2px");
			combo_box.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
		} else if (combo_box.isFocused()) {
			username.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			password.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			combo_box.setStyle("-fx-background-color:#fff;" + "-fx-border-width:2px");
		}
	}

	public void dropShadowEffect() {
		DropShadow original = new DropShadow(20, Color.valueOf("#ae44a5"));
		original.setRadius(30);
		epoka_label.setEffect(original);
		epoka_label.setOnMouseEntered((MouseEvent event) -> {
			DropShadow shadow = new DropShadow(60, Color.valueOf("#e03ed5"));
			epoka_label.setCursor(Cursor.HAND);

			epoka_label.setStyle("-fx-text-fill:#000");
			epoka_label.setEffect(shadow);
		});
		epoka_label.setOnMouseExited((MouseEvent event) -> {
			DropShadow shadow = new DropShadow(30, Color.valueOf("#ae44a5"));
			shadow.setRadius(30);

			epoka_label.setStyle("-fx-text-field:#000");
			epoka_label.setEffect(shadow);
		});
	}

	public void alertWindow(String s) {
		Alert alert = new Alert(Alert.AlertType.ERROR);

		alert.setTitle("Error Message");
		alert.setHeaderText(null);
		alert.setContentText("Check " + s + "!");
		alert.showAndWait();
	}

	public void login() {
		connect = Database.connectDb();

		try {
			if (select() == "Admin") {
				sql = "SELECT * FROM admins WHERE username = ? and password = ?";
				prepare = connect.prepareStatement(sql);
				prepare.setString(1, username.getText());
				prepare.setString(2, password.getText());
				

				result = prepare.executeQuery();
				
				
				if (result.next()) {
					
					Admin.username = result.getString("username");
					
					Alert alert = new Alert(AlertType.INFORMATION);

					alert.setTitle("Confirmation Message");
					alert.setHeaderText(null);
					alert.setContentText("Successfully Login!");
					alert.showAndWait();

					login_btn.getScene().getWindow().hide();

					Parent root = FXMLLoader.load(getClass().getResource("AdminHome.fxml"));

					Scene scene = new Scene(root);

					Stage stage = new Stage();
					
					
					root.setOnMousePressed((MouseEvent event) -> {
						x = event.getSceneX();
						y = event.getSceneY();
					});
					
					root.setOnMouseDragged((MouseEvent event) -> {
						stage.setX(event.getScreenX() - x);
						stage.setY(event.getScreenY() - y);
					});
					
					stage.initStyle(StageStyle.TRANSPARENT);

					stage.setScene(scene);
					stage.show();

				}else alertWindow("username/password");

			}

			else if (select() == "Student") {
				sql = "SELECT * FROM students WHERE username = ? and password = ?";
				
				prepare = connect.prepareStatement(sql);
				prepare.setString(1, username.getText());
				prepare.setString(2, password.getText());

				result = prepare.executeQuery();
				if (result.next()) {
					
					Student.username = result.getString("username");
					
					Alert alert = new Alert(AlertType.INFORMATION);

					alert.setTitle("Confirmation Message");
					alert.setHeaderText(null);
					alert.setContentText("Successful Login! " + "Welcome " + username.getText() + "!");
					alert.showAndWait();

					login_btn.getScene().getWindow().hide();

					Parent root = FXMLLoader.load(getClass().getResource("StudentHome.fxml"));

					Scene scene = new Scene(root);

					Stage stage = new Stage();
					
					root.setOnMousePressed((MouseEvent event) -> {
						x = event.getSceneX();
						y = event.getSceneY();
					});
					
					root.setOnMouseDragged((MouseEvent event) -> {
						stage.setX(event.getScreenX() - x);
						stage.setY(event.getScreenY() - y);
					});
					
					stage.initStyle(StageStyle.TRANSPARENT);

					stage.setScene(scene);
					stage.show();
					
				}else alertWindow("username/password");
				
			} else
				alertWindow("account");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		ObservableList<String> list = FXCollections.observableArrayList("Student", "Admin");
		combo_box.setItems(list);

		dropShadowEffect();

	}

}
