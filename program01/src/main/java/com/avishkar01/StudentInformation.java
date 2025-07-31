package com.avishkar01;

//import javax.swing.plaf.ColorUIResource;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StudentInformation extends Application {

    private final ObservableList<Student> studentlist = FXCollections.observableArrayList();
    private final TableView<Student> tableView = new TableView<>();

    @Override
    public void start(Stage stage) throws Exception {

        TextField rollnoField = new TextField();
        rollnoField.setPromptText("Student Roll No");
        rollnoField.setFocusTraversable(false);

        TextField nameTextField = new TextField();
        nameTextField.setPromptText("Student Name");
        nameTextField.setFocusTraversable(false);

        TextField courseField = new TextField();
        courseField.setPromptText("Student Course");
        courseField.setFocusTraversable(false);

        TextField emailField = new TextField();
        emailField.setPromptText("Student Email");
        emailField.setFocusTraversable(false);

        TableColumn<Student, String> rollColumn = new TableColumn<>("RollNo");
        rollColumn.setCellValueFactory(new PropertyValueFactory<>("rollNo"));

        TableColumn<Student, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Student, String> courseColumn = new TableColumn<>("Course");
        courseColumn.setCellValueFactory(new PropertyValueFactory<>("course"));

        TableColumn<Student, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        rollColumn.setPrefWidth(100);
        nameColumn.setPrefWidth(150);
        courseColumn.setPrefWidth(150);
        emailColumn.setPrefWidth(200);

        tableView.setItems(studentlist);
        rollColumn.setText("RollNo");
        nameColumn.setText("Name");
        courseColumn.setText("Course");
        emailColumn.setText("Email");

        tableView.getColumns().addAll(rollColumn, nameColumn, courseColumn, emailColumn);
        tableView.setPrefHeight(200);

        Button button1 = new Button("ADD");

        button1.setMaxWidth(100);
        button1.setOnAction(e -> {
            if (validInputs(rollnoField, nameTextField, courseField, emailField)) {
                studentlist.add(new Student(rollnoField.getText(), nameTextField.getText(), courseField.getText(),
                        emailField.getText()));
                clearInputs(rollnoField, nameTextField, courseField, emailField);
            }
        });

        Button button2 = new Button("READ");

        button2.setMaxWidth(100);
        button2.setOnAction(e -> {

            System.out.println("Students in the list:");
            for (Student s : studentlist) {
                System.out.println(s.getRollNo() + ", " + s.getName() + ", " + s.getCourse() + ", " + s.getEmail());
            }
        });

        Button button3 = new Button("UPDATE");

        button3.setMaxWidth(100);
        button3.setOnAction(e -> {
            Student selected = tableView.getSelectionModel().getSelectedItem();
            if (selected != null && validInputs(rollnoField, nameTextField, courseField, emailField)) {
                selected.setRollNo(rollnoField.getText());
                selected.setName(nameTextField.getText());
                selected.setCourse(courseField.getText());
                selected.setEmail(emailField.getText());
                tableView.refresh();
                clearInputs(rollnoField, nameTextField, courseField, emailField);

            }
        });

        Button button4 = new Button("DELETE");

        button4.setMaxWidth(100);
        button4.setOnAction(e -> {
            Student selectedStudent = tableView.getSelectionModel().getSelectedItem();
            if (selectedStudent != null) {
                studentlist.remove(selectedStudent);
                clearInputs(rollnoField, nameTextField, courseField, emailField);
            }
        });

        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            try {
                if (newSel != null) {

                    rollnoField.setText(newSel.getRollNo() != null ? newSel.getRollNo() : "");
                    nameTextField.setText(newSel.getName() != null ? newSel.getName() : "");
                    courseField.setText(newSel.getCourse() != null ? newSel.getCourse() : "");
                    emailField.setText(newSel.getEmail() != null ? newSel.getEmail() : "");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        HBox hb = new HBox(10, button1, button2, button3, button4);
        HBox hb1 = new HBox(10, rollnoField, nameTextField, courseField, emailField);

        VBox vb1 = new VBox(40, hb1, hb, tableView);
        vb1.setStyle("-fx-background-color:whitesmoke");
        Scene scene = new Scene(vb1, 900, 700);
        stage.setScene(scene);
        stage.show();
    }

    private boolean validInputs(TextField rollno, TextField name, TextField course,
            TextField email) {
        return !rollno.getText().isEmpty() && !name.getText().isEmpty() &&
                !email.getText().isEmpty() && !course.getText().isEmpty();

    }

    private void clearInputs(TextField... fields) {
        for (TextField f : fields)
            f.clear();

    }

}