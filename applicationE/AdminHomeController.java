package applicationE;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AdminHomeController implements Initializable {

	@FXML
	private Button add_btn;

	@FXML
	private Label add_label;

	@FXML
	private ComboBox<String> comboBox_category;

	@FXML
	private ComboBox<String> comboBox_exam;

	@FXML
	private ComboBox<String> comboBox_subject;

	@FXML
	private Button grade_btn;

	@FXML
	private Button logout_btn;

	@FXML
	private Button exit_btn;

	@FXML
	private Label grade_label;

	@FXML
	private Label nameLabel;

	@FXML
	private Button remove_btn;

	@FXML
	private Label remove_label;

	@FXML
	private TextField tfBirthday; 
	
	@FXML
	private TextField tfBirthplace;
	
	@FXML
	private TextField tfCategory;

	@FXML
	private TextField tfGrade;

	@FXML
	private TextField tfID;

	@FXML
	private TextField tfName;

	@FXML
	private TextField tfSurname;

	@FXML
	private TextField tfUsername;

	@FXML
	private TextField tfUsername_grade;

	@FXML
	private TextField tfUsername_remove;

	@FXML
	private TextField tfUsername_update;

	@FXML
	private Button update_btn;

	@FXML
	private Label update_label;
	
	//Database tools
	
	private Connection connect;
	private PreparedStatement statement;
	
	
	private double x = 0;
	private double y = 0; 

	@FXML
	public void exit1() {
		System.exit(1);
	}

	@FXML
	public void logout() {

		try {
			logout_btn.getScene().getWindow().hide();
			Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));

			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

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
	
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public void account() {
		nameLabel.setText(Admin.username);
	}
	
	
	public void dropShadowEffect1() {
		DropShadow original = new DropShadow(20, Color.valueOf("#ffffff"));
		original.setRadius(30);
		add_label.setEffect(original);
		add_label.setOnMouseEntered((MouseEvent event) -> {
			DropShadow shadow = new DropShadow(60, Color.valueOf("#e03ed5"));
			add_label.setCursor(Cursor.HAND);

			add_label.setStyle("-fx-text-fill:#f44747");
			add_label.setEffect(shadow);
		});
		add_label.setOnMouseExited((MouseEvent event) -> {
			DropShadow shadow = new DropShadow(30, Color.valueOf("#ffffff"));
			shadow.setRadius(30);

			add_label.setStyle("-fx-text-field:#f44747");
			add_label.setEffect(shadow);
		});
	}
	
	public void dropShadowEffect2() {
		DropShadow original = new DropShadow(20, Color.valueOf("#ffffff"));
		original.setRadius(30);
		grade_label.setEffect(original);
		grade_label.setOnMouseEntered((MouseEvent event) -> {
			DropShadow shadow = new DropShadow(50, Color.valueOf("#e03ed5"));
			grade_label.setCursor(Cursor.HAND);

			grade_label.setStyle("-fx-text-fill:#f44747");
			grade_label.setEffect(shadow);
		});
		grade_label.setOnMouseExited((MouseEvent event) -> {
			DropShadow shadow = new DropShadow(30, Color.valueOf("#ffffff"));
			shadow.setRadius(30);

			grade_label.setStyle("-fx-text-field:#f44747");
			grade_label.setEffect(shadow);
		});
	}
	
	public void dropShadowEffect3() {
		DropShadow original = new DropShadow(20, Color.valueOf("#ffffff"));
		original.setRadius(30);
		remove_label.setEffect(original);
		remove_label.setOnMouseEntered((MouseEvent event) -> {
			DropShadow shadow = new DropShadow(60, Color.valueOf("#e03ed5"));
			remove_label.setCursor(Cursor.HAND);

			remove_label.setStyle("-fx-text-fill:#f44747");
			remove_label.setEffect(shadow);
		});
		remove_label.setOnMouseExited((MouseEvent event) -> {
			DropShadow shadow = new DropShadow(30, Color.valueOf("#ffffff"));
			shadow.setRadius(30);

			remove_label.setStyle("-fx-text-field:#f44747");
			remove_label.setEffect(shadow);
		});
	}
	
	public void dropShadowEffect4() {
		DropShadow original = new DropShadow(20, Color.valueOf("#ffffff"));
		original.setRadius(30);
		update_label.setEffect(original);
		update_label.setOnMouseEntered((MouseEvent event) -> {
			DropShadow shadow = new DropShadow(60, Color.valueOf("#e03ed5"));
			update_label.setCursor(Cursor.HAND);

			update_label.setStyle("-fx-text-fill:#f44747");
			update_label.setEffect(shadow);
		});
		update_label.setOnMouseExited((MouseEvent event) -> {
			DropShadow shadow = new DropShadow(30, Color.valueOf("#ffffff"));
			shadow.setRadius(30);

			update_label.setStyle("-fx-text-field:#f44747");
			update_label.setEffect(shadow);
		});
	}
	
	public void textfieldDesignAdmin() {
		if(tfBirthday.isFocused()) {
			tfBirthday.setStyle("-fx-background-color:#fff; -fx-border-width:2px");
			tfBirthplace.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfCategory.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfGrade.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfID.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfName.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfSurname.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfUsername.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfUsername_grade.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfUsername_remove.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfUsername_update.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			
		}else if(tfBirthplace.isFocused()) {
			tfBirthday.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfBirthplace.setStyle("-fx-background-color:#fff; -fx-border-width:2px");
			tfCategory.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfGrade.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfID.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfName.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfSurname.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfUsername.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfUsername_grade.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfUsername_remove.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfUsername_update.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			
		}else if(tfCategory.isFocused()) {
			tfBirthday.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfBirthplace.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfCategory.setStyle("-fx-background-color:#fff; -fx-border-width:2px");
			tfGrade.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfID.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfName.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfSurname.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfUsername.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfUsername_grade.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfUsername_remove.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfUsername_update.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			
		}else if(tfGrade.isFocused()) {
			tfBirthday.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfBirthplace.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfCategory.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfGrade.setStyle("-fx-background-color:#fff; -fx-border-width:2px");
			tfID.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfName.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfSurname.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfUsername.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfUsername_grade.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfUsername_remove.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfUsername_update.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			
		}else if(tfID.isFocused()) {
			tfBirthday.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfBirthplace.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfCategory.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfGrade.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfID.setStyle("-fx-background-color:#fff; -fx-border-width:2px");
			tfName.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfSurname.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfUsername.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfUsername_grade.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfUsername_remove.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfUsername_update.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			
		}else if(tfName.isFocused()) {
			tfBirthday.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfBirthplace.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfCategory.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfGrade.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfID.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfName.setStyle("-fx-background-color:#fff; -fx-border-width:2px");
			tfSurname.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfUsername.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfUsername_grade.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfUsername_remove.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfUsername_update.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			
		}else if(tfSurname.isFocused()) {
			tfBirthday.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfBirthplace.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfCategory.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfGrade.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfID.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfName.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfSurname.setStyle("-fx-background-color:#fff; -fx-border-width:2px");
			tfUsername.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfUsername_grade.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfUsername_remove.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfUsername_update.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			
		}else if(tfUsername.isFocused()) {
			tfBirthday.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfBirthplace.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfCategory.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfGrade.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfID.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfName.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfSurname.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfUsername.setStyle("-fx-background-color:#fff; -fx-border-width:2px");
			tfUsername_grade.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfUsername_remove.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfUsername_update.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			
		}else if(tfUsername_grade.isFocused()) {
			tfBirthday.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfBirthplace.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfCategory.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfGrade.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfID.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfName.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfSurname.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfUsername.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfUsername_grade.setStyle("-fx-background-color:#fff; -fx-border-width:2px");
			tfUsername_remove.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfUsername_update.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			
		}else if(tfUsername_remove.isFocused()) {
			tfBirthday.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfBirthplace.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfCategory.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfGrade.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfID.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfName.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfSurname.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfUsername.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfUsername_grade.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfUsername_remove.setStyle("-fx-background-color:#fff; -fx-border-width:2px");
			tfUsername_update.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			
		}else if(tfUsername_update.isFocused()) {
			tfBirthday.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfBirthplace.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfCategory.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfGrade.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfID.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfName.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfSurname.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfUsername.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfUsername_grade.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfUsername_remove.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfUsername_update.setStyle("-fx-background-color:#fff; -fx-border-width:2px");
			
		}else if(comboBox_category.isFocused()) {
			tfBirthday.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfBirthplace.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfCategory.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfGrade.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfID.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfName.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfSurname.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfUsername.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfUsername_grade.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfUsername_remove.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfUsername_update.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			comboBox_category.setStyle("-fx-background-color:#fff; -fx-border-width:2px");
			comboBox_exam.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			comboBox_subject.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			
		}else if(comboBox_exam.isFocused()) {
			tfBirthday.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfBirthplace.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfCategory.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfGrade.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfID.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfName.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfSurname.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfUsername.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfUsername_grade.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfUsername_remove.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfUsername_update.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			comboBox_category.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			comboBox_exam.setStyle("-fx-background-color:#fff; -fx-border-width:2px");
			comboBox_subject.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			
		}else if(comboBox_subject.isFocused()) {
			tfBirthday.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfBirthplace.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfCategory.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfGrade.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfID.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfName.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfSurname.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfUsername.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfUsername_grade.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfUsername_remove.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			tfUsername_update.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			comboBox_category.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			comboBox_exam.setStyle("-fx-background-color:transparent;" + "-fx-border-width:1px");
			comboBox_subject.setStyle("-fx-background-color:#fff; -fx-border-width:2px");
			
		}

	}
	
	public void alertInformation(String s) {
		Alert alert = new Alert(AlertType.INFORMATION);

		alert.setTitle("Confirmation Message");
		alert.setHeaderText(null);
		alert.setContentText("Student is successfully " + s);
		alert.showAndWait();
	}
	
	public void insertStudent() {
		
		try {
			
			connect = Database.connectDb();
            String sql = "insert into students (username,password,name,surname,ID,birthday,birthplace,compulsory1,"
            		+ "midterm1,final1,compulsory2,midterm2,final2,compulsory3,midterm3,final3,tech,midterm4,final4,"
            		+ "nontech,midterm5,final5) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            statement = connect.prepareStatement(sql);
            statement.setString(1,tfUsername.getText());
            statement.setString(2, "");
            statement.setString(3,tfName.getText());
            statement.setString(4,tfSurname.getText());
            statement.setString(5,tfID.getText() );
            statement.setString(6,tfBirthday.getText());
            statement.setString(7,tfBirthplace.getText());
            statement.setString(8, "");
            statement.setString(9, "");
            statement.setString(10, "");
            statement.setString(11, "");
            statement.setString(12, "");
            statement.setString(13, "");
            statement.setString(14, "");
            statement.setString(15, "");
            statement.setString(16, "");
            statement.setString(17, "");
            statement.setString(18, "");
            statement.setString(19, "");
            statement.setString(20, "");
            statement.setString(21, "");
            statement.setString(22, "");
            
            statement.executeUpdate();
            
            tfUsername.setText("");
            tfName.setText("");
            tfSurname.setText("");
            tfID.setText("");
            tfBirthday.setText("");
            tfBirthplace.setText("");
            
            alertInformation("inserted!");
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void alertWindow(String s) {
		Alert alert = new Alert(Alert.AlertType.ERROR);

		alert.setTitle("Error Message");
		alert.setHeaderText(null);
		alert.setContentText("Check " + s + "!");
		alert.showAndWait();
	}
	
	public void removeStudent() {
		
		connect = Database.connectDb();
		String sql = "delete from students where username = ?";
		try {
			statement = connect.prepareStatement(sql);
			statement.setString(1,tfUsername_remove.getText());
			statement.executeUpdate();
			
			if(tfUsername_remove.getText() == "")
				alertWindow("username");
			else {
				alertInformation("removed!");
				tfUsername_remove.setText("");
			}
				
			
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
	
	
	public void updateStudentSubroutine(String s) {
		try {
			connect = Database.connectDb();
		String sql = "update students set " + s + " = ? where username = ?";
		statement = connect.prepareStatement(sql);
		statement.setString(1,tfCategory.getText());
		statement.setString(2,tfUsername_update.getText());
		statement.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void updateStudent() {
		
		if(tfCategory.getText() == "")
			alertWindow("category text field");
		if(tfUsername_update.getText() == "")
			alertWindow("username");
		
		try {
			if(comboBox_category.getSelectionModel().getSelectedItem().toString() == "name") {
				
				updateStudentSubroutine("name");
				
			}else if(comboBox_category.getSelectionModel().getSelectedItem().toString() == "surname") {
				
				updateStudentSubroutine("surname");
				
			}else if(comboBox_category.getSelectionModel().getSelectedItem().toString() == "ID") {
				
				updateStudentSubroutine("ID");
				
			}else if(comboBox_category.getSelectionModel().getSelectedItem().toString() == "birthday") {
			
				updateStudentSubroutine("birthday");
				
			}else if(comboBox_category.getSelectionModel().getSelectedItem().toString() == "birthplace") {
				
				updateStudentSubroutine("birthplace");
				
			}
			
			comboBox_category.getSelectionModel().clearSelection();
			alertInformation("updated!");
			tfUsername_update.setText("");
			tfCategory.setText("");
		}
		catch(Exception e) {
			alertWindow("category box");
			e.printStackTrace();
		}
	}
	
	private ObservableList<String> subjects;
	String compulsory1;
	String compulsory2;
	String compulsory3;
	String tech;
	String nontech;
	
	String selectedSubject;
	String selectedExam;
	
		
public void getSelectedSubjects() {
		
		if(comboBox_subject.isFocused())	
		try {
				connect = Database.connectDb();

				String query = ("SELECT compulsory1, compulsory2, compulsory3, tech, nontech FROM students WHERE username = \'"
						+ tfUsername_grade.getText() + "\'");
				
				
				Statement st = connect.createStatement();
				ResultSet rs = st.executeQuery(query);

				while (rs.next()) {

					compulsory1 = rs.getString("compulsory1");
					compulsory2 = rs.getString("compulsory2");
					compulsory3 = rs.getString("compulsory3");
					tech = rs.getString("tech");
					nontech = rs.getString("nontech");
					
					subjects = FXCollections.observableArrayList(compulsory1, compulsory2, compulsory3, tech, nontech);
					

				}

				comboBox_subject.setItems(subjects);
				

			} catch (Exception e) {
				e.printStackTrace();
			}

	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		dropShadowEffect1();
		dropShadowEffect2();
		dropShadowEffect3();
		dropShadowEffect4();
		
		account();
		
		ObservableList<String> categories = FXCollections.observableArrayList("name", "surname","ID","birthday","birthplace");
		comboBox_category.setItems(categories);
		
		ObservableList<String> exams = FXCollections.observableArrayList("Midterm","Final");
		comboBox_exam.setItems(exams);
		
		comboBox_subject.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				
				 selectedSubject = comboBox_subject.getSelectionModel().getSelectedItem();
				
			}
			
		});
		
		comboBox_exam.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				
				 selectedExam = comboBox_exam.getSelectionModel().getSelectedItem();
				
			}
			
		});
		
		
		
	}
	
	
	
	
	public void gradeStudent() {
		
		connect = Database.connectDb();
		try {	
		
			String sql;
			
			if((selectedSubject == compulsory1) && (selectedExam == "Midterm")) 
			{
				sql = "update students set midterm1 = ? where username = ?";
				statement = connect.prepareStatement(sql);
				statement.setString(1,tfGrade.getText());
				statement.setString(2, tfUsername_grade.getText());
				statement.executeUpdate();
				
			}
			
			if(selectedSubject == compulsory1 && selectedExam == "Final") 
			{
				sql = "update students set final1 = ? where username = ?";
				statement = connect.prepareStatement(sql);
				statement.setString(1,tfGrade.getText());
				statement.setString(2, tfUsername_grade.getText());
				statement.executeUpdate();
			}
			
			if(selectedSubject == compulsory2 && selectedExam == "Midterm") 
			{
				sql = "update students set midterm2 = ? where username = ?";
				statement = connect.prepareStatement(sql);
				statement.setString(1,tfGrade.getText());
				statement.setString(2, tfUsername_grade.getText());
				statement.executeUpdate();
			}
			
			if(selectedSubject == compulsory2 && selectedExam == "Final") 
			{
				sql = "update students set final2 = ? where username = ?";
				statement = connect.prepareStatement(sql);
				statement.setString(1,tfGrade.getText());
				statement.setString(2, tfUsername_grade.getText());
				statement.executeUpdate();
			}
			
			if(selectedSubject == compulsory3 && selectedExam == "Midterm") 
			{
				sql = "update students set midterm3 = ? where username = ?";
				statement = connect.prepareStatement(sql);
				statement.setString(1,tfGrade.getText());
				statement.setString(2, tfUsername_grade.getText());
				statement.executeUpdate();
			}
			
			if(selectedSubject == compulsory3 && selectedExam == "Final") 
			{
				sql = "update students set final3 = ? where username = ?";
				statement = connect.prepareStatement(sql);
				statement.setString(1,tfGrade.getText());
				statement.setString(2, tfUsername_grade.getText());
				statement.executeUpdate();
			}
			
			if(selectedSubject == tech && selectedExam == "Midterm") 
			{
				sql = "update students set midterm4 = ? where username = ?";
				statement = connect.prepareStatement(sql);
				statement.setString(1,tfGrade.getText());
				statement.setString(2, tfUsername_grade.getText());
				statement.executeUpdate();
			}
			
			if(selectedSubject == tech && selectedExam == "Final") 
			{
				sql = "update students set final4 = ? where username = ?";
				statement = connect.prepareStatement(sql);
				statement.setString(1,tfGrade.getText());
				statement.setString(2, tfUsername_grade.getText());
				statement.executeUpdate();
			}
			
			if(selectedSubject == nontech && selectedExam == "Midterm") 
			{
				sql = "update students set midterm5 = ? where username = ?";
				statement = connect.prepareStatement(sql);
				statement.setString(1,tfGrade.getText());
				statement.setString(2, tfUsername_grade.getText());
				statement.executeUpdate();
			}
			
			if(selectedSubject == nontech && selectedExam == "Final") 
			{
				sql = "update students set final5 = ? where username = ?";
				statement = connect.prepareStatement(sql);
				statement.setString(1,tfGrade.getText());
				statement.setString(2, tfUsername_grade.getText());
				statement.executeUpdate();
				
			}
			
			
			tfGrade.setText("");
			tfUsername_grade.setText("");
			comboBox_subject.getSelectionModel().clearSelection();
			comboBox_exam.getSelectionModel().clearSelection();
			alertInformation("updated!");
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}


}
