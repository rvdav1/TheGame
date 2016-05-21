package gui;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.Window;

import persistence.*;

public class OptController implements Initializable {
	List<File> files;
	
	File firstProf;
	File secondProf;
	
	@FXML
	Button firstOptTeamButton;
	
	@FXML
	Button secondOptTeamButton;
	
	@FXML
	Button newOptProfile;
	
	@FXML
	Button deleteOptProfile;
	
	@FXML
	Button backOpt;
	
	@FXML
	ComboBox<File> firstOptTeamBox;
	
	@FXML
	ComboBox<File> secondOptTeamBox;
	
	@FXML
	Label labelOpt;
	
	@FXML
	void handleOptFirstTeam(ActionEvent event) throws IOException {
		firstProf = firstOptTeamBox.getSelectionModel().getSelectedItem();
		if (files.size() > 0){
			labelOpt.setText("First team profile set!");
			Main.firstTeam = ReadInXML.readXML(firstProf);
			Main.logger.log(Level.INFO, Main.firstTeam.get(0) + " " + Main.firstTeam.get(1) + " " + 
					Main.firstTeam.get(2) + " " + Main.firstTeam.get(3) + " " + 
					Main.firstTeam.get(4) + " " + Main.firstTeam.get(5));
		} else
			labelOpt.setText("Nothing to set!");
	}
	
	@FXML
	void handleOptSecondTeam(ActionEvent event) throws IOException {
		secondProf = secondOptTeamBox.getSelectionModel().getSelectedItem();
		if (files.size() > 0){
			labelOpt.setText("Second team profile set!");
			Main.secondTeam = ReadInXML.readXML(secondProf);
			Main.logger.log(Level.INFO, Main.secondTeam.get(0) + " " + Main.secondTeam.get(1) + " " + 
					Main.secondTeam.get(2) + " " + Main.secondTeam.get(3) + " " + 
					Main.secondTeam.get(4) + " " + Main.secondTeam.get(5));
		} else
			labelOpt.setText("Nothing to set!");
	}
	
	@FXML
	void handleOptNewProfile(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        
        root=FXMLLoader.load(getClass().getResource("/fxml/AddProfile.fxml"));
        Window mainScene= newOptProfile.getScene().getWindow();
        stage=(Stage) newOptProfile.getScene().getWindow();
        stage.setHeight(mainScene.getHeight());
        stage.setWidth(mainScene.getWidth());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}
	
	@FXML
	void handleOptDeleteProfile(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        
        root=FXMLLoader.load(getClass().getResource("/fxml/DeleteProfile.fxml"));
        Window mainScene= deleteOptProfile.getScene().getWindow();
        stage=(Stage) deleteOptProfile.getScene().getWindow();
        stage.setHeight(mainScene.getHeight());
        stage.setWidth(mainScene.getWidth());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}
	
	@FXML
	void handleOptBack(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        Window mainScene= backOpt.getScene().getWindow();
        root=FXMLLoader.load(getClass().getResource("/fxml/Main.fxml"));
        stage=(Stage) backOpt.getScene().getWindow();
        stage.setHeight(mainScene.getHeight());
        stage.setWidth(mainScene.getWidth());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}


	public void initialize(URL location, ResourceBundle resources) {
		File f = new File(WriteInXML.getFolder());
		files = new ArrayList<File>(Arrays.asList(f.listFiles()));
		ObservableList<File> allFiles = FXCollections.observableArrayList(files);
		firstOptTeamBox.setItems(allFiles);
		firstOptTeamBox.getSelectionModel().select(0);
		secondOptTeamBox.setItems(allFiles);
		secondOptTeamBox.getSelectionModel().select(0);
	}

}
