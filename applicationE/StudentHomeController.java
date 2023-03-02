package applicationE;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.animation.RotateTransition;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class StudentHomeController implements Initializable, Cloneable {

	@FXML
	private Circle c1;

	@FXML
	private Circle c2;

	@FXML
	private Circle c3;
	
	@FXML
    private Button play_btn;

	@FXML
	private Button courses_btn;

	@FXML
	private Button home_btn;

	@FXML
	private Button logout_btn;

	@FXML
	private Button profile_btn;

	@FXML
	private Button transcript_btn;

	@FXML
	private Button exit_btn;

	@FXML
	private Label welcome_label;

	@FXML
	private Button changePassword_btn;

	@FXML
	private TextField tfpassword;

	@FXML
	private AnchorPane home_page1;

	@FXML
	private AnchorPane home_page2;

	@FXML
	private AnchorPane profile_page;

	@FXML
	private AnchorPane courses_page;

	@FXML
	private AnchorPane transcript_page;
	
	@FXML
    private Label transcriptCGPA;

    @FXML
    private Label transcriptName;

    @FXML
    private Label transcriptSurname;

	@FXML
	private Label CGPA;

	@FXML
	private Label ID;

	@FXML
	private Label birthday;

	@FXML
	private Label birthplace;

	@FXML
	private Label name;

	@FXML
	private Label surname;

	@FXML
	private Label username;

	@FXML
	private ListView<String> tech_lv;

	@FXML
	private ListView<String> compulsory_lv;

	@FXML
	private ListView<String> nontech_lv;

	@FXML
	private TableView<Courses> coursesTable;

	@FXML
	private TableColumn<Courses, String> code;

	@FXML
	private TableColumn<Courses, String> course_name;
	
	 @FXML
	private TableView<Courses> gradesTable;

	@FXML
	private TableColumn<Courses, String> course_grades;

	@FXML
	private TableColumn<Courses, String> midterm_grades;

	@FXML
	private TableColumn<Courses, String> final_grades;

	@FXML
	private Button addCourses_btn;
	
	@FXML
	private Button download_btn;

	// Courses resources

	String currentTechnicalC;
	String currentNontechnicalC;
	ObservableList<String> currentCompulsoryC;

	CompulsoryCourse cc1 = new CompulsoryCourse("COMPUTER NETWORKS", "CEN307");
	CompulsoryCourse cc2 = new CompulsoryCourse("WEB TECHNOLOGIES AND PROGRAMMING", "CEN311");
	CompulsoryCourse cc3 = new CompulsoryCourse("PROFESSIONAL PRACTICE", "CEN351");

	TechnicalElectiveCourse tec1 = new TechnicalElectiveCourse("PARALLEL PROGRAMMING", "CEN330");
	TechnicalElectiveCourse tec2 = new TechnicalElectiveCourse("MACHINE LEARNING", "CEN380");
	TechnicalElectiveCourse tec3 = new TechnicalElectiveCourse("INTERNSHIP", "CEN348");

	NonTechnicalElectiveCourse nec1 = new NonTechnicalElectiveCourse("INTRODUCTION TO BUSINESS", "BUS103");
	NonTechnicalElectiveCourse nec2 = new NonTechnicalElectiveCourse("GERMAN II", "FL204");
	NonTechnicalElectiveCourse nec3 = new NonTechnicalElectiveCourse("ITALIAN II", "FL206");

	// Database tools

	private Connection connect;
	private PreparedStatement statement;
	private Statement st;
	private ResultSet rs;

	private double x = 0;
	private double y = 0;

	@FXML
	public void exit3() {
		System.exit(2);
	}
	
	public void setRotate(Circle c, boolean reverse, int angle, int duration) {
		
		RotateTransition rt = new RotateTransition(Duration.seconds(duration),c);
		rt.setAutoReverse(reverse);
		rt.setByAngle(angle);
		rt.setDelay(Duration.seconds(0));
		rt.setRate(3);
		rt.setCycleCount(10);
		rt.play();
	}
	@FXML
	public void play() {
		
		setRotate(c1,true,360,10);
		setRotate(c2,true,180,18);
		setRotate(c3,true,145,24);
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

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void accountStudent() {
		welcome_label.setText(Student.username);
	}

	@FXML
	public void changePassword() {

		try {
			connect = Database.connectDb();
			String sql = "update students set password = ? where username = ?";

			statement = connect.prepareStatement(sql);
			statement.setString(1, tfpassword.getText());
			statement.setString(2, Student.username);

			statement.executeUpdate();

			tfpassword.setText("");

			Alert alert = new Alert(AlertType.INFORMATION);

			alert.setTitle("Confirmation Message");
			alert.setHeaderText(null);
			alert.setContentText("Your password is updated!");
			alert.showAndWait();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void tfpasswordDesign() {

		if (tfpassword.isFocused()) {
			tfpassword.setStyle("-fx-background-color:#fff; -fx-border-width:2px");
		} else
			tfpassword.setStyle("-fx-background-color:transparent; -fx-border-width:1px");

	}

	public void dropShadowEffect1() {
		DropShadow original = new DropShadow(20, Color.valueOf("#ffffff"));
		original.setRadius(30);
		username.setEffect(original);
		username.setOnMouseEntered((MouseEvent event) -> {
			DropShadow shadow = new DropShadow(60, Color.valueOf("#e03ed5"));
			username.setCursor(Cursor.HAND);

			username.setStyle("-fx-text-fill:#f44747");
			username.setEffect(shadow);
		});
		username.setOnMouseExited((MouseEvent event) -> {
			DropShadow shadow = new DropShadow(30, Color.valueOf("#ffffff"));
			shadow.setRadius(30);

			username.setStyle("-fx-text-field:#f44747");
			username.setEffect(shadow);
		});
	}

	public void dropShadowEffect2() {
		DropShadow original = new DropShadow(20, Color.valueOf("#ffffff"));
		original.setRadius(30);
		name.setEffect(original);
		name.setOnMouseEntered((MouseEvent event) -> {
			DropShadow shadow = new DropShadow(50, Color.valueOf("#e03ed5"));
			name.setCursor(Cursor.HAND);

			name.setStyle("-fx-text-fill:#f44747");
			name.setEffect(shadow);
		});
		name.setOnMouseExited((MouseEvent event) -> {
			DropShadow shadow = new DropShadow(30, Color.valueOf("#ffffff"));
			shadow.setRadius(30);

			name.setStyle("-fx-text-field:#f44747");
			name.setEffect(shadow);
		});
	}

	public void dropShadowEffect3() {
		DropShadow original = new DropShadow(20, Color.valueOf("#ffffff"));
		original.setRadius(30);
		surname.setEffect(original);
		surname.setOnMouseEntered((MouseEvent event) -> {
			DropShadow shadow = new DropShadow(60, Color.valueOf("#e03ed5"));
			surname.setCursor(Cursor.HAND);

			surname.setStyle("-fx-text-fill:#f44747");
			surname.setEffect(shadow);
		});
		surname.setOnMouseExited((MouseEvent event) -> {
			DropShadow shadow = new DropShadow(30, Color.valueOf("#ffffff"));
			shadow.setRadius(30);

			surname.setStyle("-fx-text-field:#f44747");
			surname.setEffect(shadow);
		});
	}

	public void dropShadowEffect4() {
		DropShadow original = new DropShadow(20, Color.valueOf("#ffffff"));
		original.setRadius(30);
		CGPA.setEffect(original);
		CGPA.setOnMouseEntered((MouseEvent event) -> {
			DropShadow shadow = new DropShadow(60, Color.valueOf("#e03ed5"));
			CGPA.setCursor(Cursor.HAND);

			CGPA.setStyle("-fx-text-fill:#f44747");
			CGPA.setEffect(shadow);
		});
		CGPA.setOnMouseExited((MouseEvent event) -> {
			DropShadow shadow = new DropShadow(30, Color.valueOf("#ffffff"));
			shadow.setRadius(30);

			CGPA.setStyle("-fx-text-field:#f44747");
			CGPA.setEffect(shadow);
		});
	}

	public void dropShadowEffect5() {
		DropShadow original = new DropShadow(20, Color.valueOf("#ffffff"));
		original.setRadius(30);
		ID.setEffect(original);
		ID.setOnMouseEntered((MouseEvent event) -> {
			DropShadow shadow = new DropShadow(60, Color.valueOf("#e03ed5"));
			ID.setCursor(Cursor.HAND);

			ID.setStyle("-fx-text-fill:#f44747");
			ID.setEffect(shadow);
		});
		ID.setOnMouseExited((MouseEvent event) -> {
			DropShadow shadow = new DropShadow(30, Color.valueOf("#ffffff"));
			shadow.setRadius(30);

			ID.setStyle("-fx-text-field:#f44747");
			ID.setEffect(shadow);
		});
	}

	public void dropShadowEffect6() {
		DropShadow original = new DropShadow(20, Color.valueOf("#ffffff"));
		original.setRadius(30);
		birthday.setEffect(original);
		birthday.setOnMouseEntered((MouseEvent event) -> {
			DropShadow shadow = new DropShadow(60, Color.valueOf("#e03ed5"));
			birthday.setCursor(Cursor.HAND);

			birthday.setStyle("-fx-text-fill:#f44747");
			birthday.setEffect(shadow);
		});
		birthday.setOnMouseExited((MouseEvent event) -> {
			DropShadow shadow = new DropShadow(30, Color.valueOf("#ffffff"));
			shadow.setRadius(30);

			birthday.setStyle("-fx-text-field:#f44747");
			birthday.setEffect(shadow);
		});
	}

	public void dropShadowEffect7() {
		DropShadow original = new DropShadow(20, Color.valueOf("#ffffff"));
		original.setRadius(30);
		birthplace.setEffect(original);
		birthplace.setOnMouseEntered((MouseEvent event) -> {
			DropShadow shadow = new DropShadow(60, Color.valueOf("#e03ed5"));
			birthplace.setCursor(Cursor.HAND);

			birthplace.setStyle("-fx-text-fill:#f44747");
			birthplace.setEffect(shadow);
		});
		birthplace.setOnMouseExited((MouseEvent event) -> {
			DropShadow shadow = new DropShadow(30, Color.valueOf("#ffffff"));
			shadow.setRadius(30);

			birthplace.setStyle("-fx-text-field:#f44747");
			birthplace.setEffect(shadow);
		});
	}

	public void displayProfile() {
		try {
			connect = Database.connectDb();

			String query = ("SELECT name, surname, ID, birthday, birthplace FROM students WHERE username = \'"
					+ Student.username + "\'");

			st = connect.createStatement();

			rs = st.executeQuery(query);

			while (rs.next()) {
				name.setText(rs.getString("name"));
				surname.setText(rs.getString("surname"));
				ID.setText(rs.getString("ID"));
				birthday.setText(rs.getString("birthday"));
				birthplace.setText(rs.getString("birthplace"));
			}

			username.setText(Student.username);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	ObservableList<Courses> courses;
	Courses elective1;
	Courses elective2;

	public ObservableList<Courses> addCoursesTable() throws CloneNotSupportedException {

		  if(currentTechnicalC == tec1.getName()) 
			  elective1 = (Courses) tec1.clone();
		  else if(currentTechnicalC == tec2.getName()) 
			  elective1 = (Courses) tec2.clone();
		  else if(currentTechnicalC == tec3.getName()) 
			  elective1 = (Courses) tec3.clone();
		  
			if (currentNontechnicalC == nec1.getName())
				elective2 = (Courses) nec1.clone();
			else if (currentNontechnicalC == nec2.getName())
				elective2 = (Courses) nec2.clone();
			else if (currentNontechnicalC == nec3.getName())
				elective2 = (Courses) nec3.clone();
		 
		 ObservableList<Courses> temp = FXCollections.observableArrayList(cc1,cc2,cc3,elective1,elective2);
		 
		 return temp;
		
	}

	public void addCoursesDatabase() {

		try {
			connect = Database.connectDb();
			String sql = "update students set compulsory1 = ?, compulsory2 = ?, compulsory3 = ?, tech = ?,"
					+ " nontech = ? where username = ?";

			statement = connect.prepareStatement(sql);
			statement.setString(1, currentCompulsoryC.get(0).toString());
			statement.setString(2, currentCompulsoryC.get(1).toString());
			statement.setString(3, currentCompulsoryC.get(2).toString());
			statement.setString(4, currentTechnicalC);
			statement.setString(5, currentNontechnicalC);
			statement.setString(6, Student.username);

			statement.executeUpdate();

			courses = addCoursesTable();
			code.setCellValueFactory(new PropertyValueFactory<Courses, String>("code"));
			course_name.setCellValueFactory(new PropertyValueFactory<Courses, String>("name"));

			coursesTable.setItems(courses);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public double calculateCGPA(double fg1, double fg2, double fg3, double fg4, double fg5 ) {
		
		int n = 5;
		
		if(fg1 == 0)
			n--;
		if(fg2 == 0)
			n--;
		if(fg3 == 0)
			n--;
		if(fg4 == 0)
			n--;
		if(fg5 == 0)
			n--;
		
		return (6 * (fg1 + fg2 + fg3 + fg4 + fg5)/(6*n));  //ECTS = 6 for each subject
		
	}
	
	String subject1, subject2, subject3, subject4, subject5;
	String midterm1, midterm2, midterm3, midterm4, midterm5;
	String final1, final2, final3, final4, final5;
	
	public void displayTranscript() {

		try {
			connect = Database.connectDb();
			
			String query = ("SELECT name, surname, compulsory1, midterm1, final1, compulsory2, midterm2, final2,"
					+ "compulsory3, midterm3, final3, tech, midterm4, final4, nontech, midterm5, final5"
					+ " FROM students WHERE username = \'" + Student.username + "\'");

			st = connect.createStatement();

			rs = st.executeQuery(query);

			while (rs.next()) {
				transcriptName.setText(rs.getString("name"));
				transcriptSurname.setText(rs.getString("surname"));

				subject1 = rs.getString("compulsory1");
				subject2 = rs.getString("compulsory2");
				subject3 = rs.getString("compulsory3");
				subject4 = rs.getString("tech");
				subject5 = rs.getString("nontech");

				midterm1 = rs.getString("midterm1");
				final1 = rs.getString("final1");

				midterm2 = rs.getString("midterm2");
				final2 = rs.getString("final2");

				midterm3 = rs.getString("midterm3");
				final3 = rs.getString("final3");

				midterm4 = rs.getString("midterm4");
				final4 = rs.getString("final4");

				midterm5 = rs.getString("midterm5");
				final5 = rs.getString("final5");
				
			
			}

			courses = FXCollections.observableArrayList();

			courses.add(new CompulsoryCourse(subject1, midterm1, final1));
			courses.add(new CompulsoryCourse(subject2, midterm2, final2));
			courses.add(new CompulsoryCourse(subject3, midterm3, final3));
			courses.add(new TechnicalElectiveCourse(subject4, midterm4, final4));
			courses.add(new NonTechnicalElectiveCourse(subject5, midterm5, final5));

			course_grades.setCellValueFactory(new PropertyValueFactory<Courses, String>("name"));
			midterm_grades.setCellValueFactory(new PropertyValueFactory<Courses, String>("midterm"));
			final_grades.setCellValueFactory(new PropertyValueFactory<Courses, String>("finals"));

			gradesTable.setItems(courses);

			
			double fg1;
			double fg2;
			double fg3;
			double fg4;
			double fg5;

			if (!midterm1.isBlank() && !final1.isBlank())
				fg1 = 0.6 * Double.parseDouble(final1) + 0.4 * Double.parseDouble(midterm1);
			else
				fg1 = 0;

			if (!midterm2.isBlank() && !final2.isBlank())
				fg2 = 0.6 * Double.parseDouble(final2) + 0.4 * Double.parseDouble(midterm2);
			else
				fg2 = 0;
		
			if (!midterm3.isBlank() && !final3.isBlank())
				fg3 = 0.6 * Double.parseDouble(final3) + 0.4 * Double.parseDouble(midterm3);
			else
				fg3 = 0;

			if (!midterm4.isBlank() && !final4.isBlank())
				fg4 = 0.6 * Double.parseDouble(final4) + 0.4 * Double.parseDouble(midterm4);
			else
				fg4 = 0;

			if (!midterm5.isBlank() && !final5.isBlank())
				fg5 = 0.6 * Double.parseDouble(final5) + 0.4 * Double.parseDouble(midterm5);
			else
				fg5 = 0;

			
			if(fg1 == 0 && fg2 == 0 && fg3 == 0 && fg4 == 0 && fg5 == 0) {
				transcriptCGPA.setText("--");
				CGPA.setText("--");
				
			} 
			else {
				
				transcriptCGPA.setText(String.format("%.2f", calculateCGPA(fg1, fg2, fg3, fg4, fg5)));
				CGPA.setText(String.format("%.2f", calculateCGPA(fg1, fg2, fg3, fg4, fg5)));
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	FileChooser fileChooser = new FileChooser();
	
	public void save(File file, String content) {
		
		PrintWriter printWriter;
		try {
			printWriter = new PrintWriter(file);
			printWriter.write(content);
			printWriter.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void download() {
		
		String s = "Username: " + username.getText() + " Name: " + name.getText() + " Surname: " + surname.getText() 
		+ " ID: " + ID.getText() + " Birthday: " + birthday.getText() + " Birthplace: " + birthplace.getText() 
		+ " CGPA: " + CGPA.getText() + "/100.00";  
		
		File file = fileChooser.showSaveDialog(new Stage());
		
		if(file != null) {
			save(file,s);
		}
		
	}

	public void changePage() {

		if (home_btn.isFocused()) {

			home_page1.setVisible(true);
			home_page2.setVisible(true);
			profile_page.setVisible(false);
			courses_page.setVisible(false);
			transcript_page.setVisible(false);

		} else if (profile_btn.isFocused()) {

			home_page1.setVisible(false);
			home_page2.setVisible(false);
			profile_page.setVisible(true);
			courses_page.setVisible(false);
			transcript_page.setVisible(false);

			displayProfile();

		} else if (courses_btn.isFocused()) {

			home_page1.setVisible(false);
			home_page2.setVisible(false);
			profile_page.setVisible(false);
			courses_page.setVisible(true);
			transcript_page.setVisible(false);

		} else if (transcript_btn.isFocused()) {
			home_page1.setVisible(false);
			home_page2.setVisible(false);
			profile_page.setVisible(false);
			courses_page.setVisible(false);
			transcript_page.setVisible(true);
			
			displayTranscript();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		accountStudent();

		dropShadowEffect1();
		dropShadowEffect2();
		dropShadowEffect3();
		dropShadowEffect4();
		dropShadowEffect5();
		dropShadowEffect6();
		dropShadowEffect7();

		compulsory_lv.getItems().addAll(cc1.getName(), cc2.getName(), cc3.getName());
		compulsory_lv.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		currentCompulsoryC = compulsory_lv.getSelectionModel().getSelectedItems();

		tech_lv.getItems().addAll(tec1.getName(), tec2.getName(), tec3.getName());
		nontech_lv.getItems().addAll(nec1.getName(), nec2.getName(), nec3.getName());

		tech_lv.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {

				currentTechnicalC = tech_lv.getSelectionModel().getSelectedItem();

			}

		});

		nontech_lv.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {

				currentNontechnicalC = nontech_lv.getSelectionModel().getSelectedItem();

			}

		});

		code.setStyle( "-fx-alignment: CENTER;");
		midterm_grades.setStyle( "-fx-alignment: CENTER;");
		final_grades.setStyle( "-fx-alignment: CENTER;");

	}
}
