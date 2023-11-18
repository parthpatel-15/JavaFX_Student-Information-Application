import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class Information extends Application {
    private BorderPane borderPane;
    private GridPane empPanel;
    private Label lblName,lblAdress,lblProvince,lblCity,lblPostalCode,lblNum,lblEmail;
    private TextField txtName,txtAdress,txtProvince,txtCity,txtPostalCode,txtNum,txtEmail;
    private Button btnDisplay;
    private final String[] computerCourses = {"Java","DataBase","Python"};
    private final String[] businessCourses = {"Management","Marketing","Law"};
    private CheckBox council,volunteer;
    private TextArea tArea;
    private ToggleGroup group;
    private ListView<String> selectedCourses = new ListView<>();

    public Information() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        borderPane = new BorderPane();
        //create the grid pane for entries
        empPanel = new GridPane();
        empPanel.setHgap(5);
        empPanel.setVgap(5);
        //create labels
        lblName=new Label("Name:");
        lblAdress=new Label("Address:");
        lblProvince=new Label("Province:");
        lblCity=new Label("City:");
        lblPostalCode=new Label("Postal Code:");
        lblNum=new Label("Phone Number:");
        lblEmail=new Label("Email:");

        //create text fields
        txtName=new TextField();
        txtAdress=new TextField();
        txtProvince=new TextField();
        txtCity=new TextField();
        txtPostalCode=new TextField();
        txtNum=new TextField();
        txtEmail=new TextField();
        //Check Box
        council= new CheckBox("Student Council");
        volunteer = new CheckBox("Volunteer work");
        //create display buttons
        btnDisplay=new Button("Display");

        //set labels, text field , checkbox in grid pane.
        empPanel.add(lblName,0,0);
        empPanel.add(txtName,4,0);
        empPanel.add(lblAdress,0,1);
        empPanel.add(txtAdress,4,1);
        empPanel.add(lblProvince,0,2);
        empPanel.add(txtProvince,4,2);
        empPanel.add(lblCity,0,3);
        empPanel.add(txtCity,4,3);
        empPanel.add(lblPostalCode,0,4);
        empPanel.add(txtPostalCode,4,4);
        empPanel.add(lblNum,0,5);
        empPanel.add(txtNum,4,5);
        empPanel.add(lblEmail,0,6);
        empPanel.add(txtEmail,4,6);
        empPanel.add(council,6,1);
        empPanel.add(volunteer,6,5);
        empPanel.add(btnDisplay,6,7);
        GridPane.setHalignment(btnDisplay, HPos.RIGHT);
        // courses section
        VBox courses = new VBox();
        courses.setSpacing(15);
        HBox rButton= new HBox();
        rButton.setSpacing(15);
        borderPane.setRight(courses);
        group = new ToggleGroup();
        //radio button
        RadioButton computerRB = new RadioButton("Computer Science");
        computerRB.setToggleGroup(group);
        computerRB.setSelected(true);
        RadioButton businessRB = new RadioButton("Business");
        businessRB.setToggleGroup(group);
        rButton.getChildren().add(computerRB);
        rButton.getChildren().add(businessRB);
        courses.getChildren().add(rButton);
        //Combo Box for courses
        ComboBox<String> courseCombo = new ComboBox<>();
        ObservableList<String> courseList = FXCollections.observableArrayList(computerCourses);
        courseCombo.getItems().addAll(courseList);
        courseCombo.setPrefWidth(200);
        courseCombo.setPromptText("select courses...");
        selectedCourses.setPrefHeight(150);
        courses.getChildren().add(courseCombo);
        courses.getChildren().add(selectedCourses);

        // Radio button action
        computerRB.setOnAction(e ->{
            if(computerRB.isSelected()){
                courseCombo.getItems().setAll(computerCourses);
            }
        } );
        businessRB.setOnAction(e -> {
            if(businessRB.isSelected()){
                courseCombo.getItems().setAll(businessCourses);
            }
        });

        // Combo Box action
        courseCombo.setOnAction(e -> {
            String value = courseCombo.getValue();
            boolean done = false;
            for (String item: selectedCourses.getItems()){
                if(item == value) done = true;
            }
            if(!done) selectedCourses.getItems().add(value);

        });

        //create the text area
        tArea= new TextArea();
        tArea.setEditable(false);
        tArea. setPrefHeight(100);
        tArea.setPrefWidth(600);
        // Create a scroll pane to hold the text area
        ScrollPane scrollPane = new ScrollPane(tArea);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        //handle click events
        btnDisplay.setOnAction(e -> displayEntries());
        //set grid pane in the center of border pane
        borderPane.setCenter(empPanel);
        //set the scroll pane to the bottom of border pane
        borderPane.setBottom(scrollPane);
        // Create a scene and place it in the stage
        Scene scene = new Scene(borderPane, 600, 300);
        primaryStage.setTitle("Student Information"); // Set title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }

    public void displayEntries()
    {
        String Name = txtName.getText();
        String address=txtAdress.getText();
        String province=txtProvince.getText();
        String city=txtCity.getText();
        String postalCode=txtPostalCode.getText();
        String num=txtNum.getText();
        String email=txtEmail.getText();

        //
        tArea.appendText(Name+", "+
                address+", "+
                province+", "+
                city+", "+
                postalCode+", "+
                num+", "+
                email +"\n"+
                "\n"+
                "---------------------\n"+
                "courses :\n");

        for (String course : selectedCourses.getItems()){
            tArea.appendText(" "+course+"; ");
        }
        tArea.appendText("\n");
        tArea.appendText("\n----------------------");
        tArea.appendText("\nActivities :");

        if (council.isSelected()) tArea.appendText("\n"+council.getText());
        if (volunteer.isSelected()) tArea.appendText("\n"+volunteer.getText());
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        launch(args);

    }

}




