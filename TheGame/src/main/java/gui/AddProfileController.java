package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import persistence.WriteInXML;

public class AddProfileController implements Initializable {

	ObservableList<String> units = FXCollections.observableArrayList(
			"Cavalry",
			"Guard",
			"Infantry",
			"Lancers",
			"Militia",
			"Paladin",
			"Peasant",
			"Raiders",
			"Spearman",
			"Swordsmen"
	);

	List<String> chosen = new ArrayList<String>();

	public static boolean isAlphabet(String word) {
	    return word.matches("[a-zA-Z]+");
	}

	@FXML
	Button addProfileButton;

	@FXML
	Button backAdd;

	@FXML
	TextField textFieldAdd;

	@FXML
	Label labelAdd;

	@FXML
	ComboBox<String> firstBoxAdd;
	
	@FXML
	ComboBox<String> secondBoxAdd;

	@FXML
	ComboBox<String> thirdBoxAdd;

	@FXML
	ComboBox<String> fourthBoxAdd;

	@FXML
	ComboBox<String> fifthBoxAdd;

	@FXML
	ComboBox<String> sixthBoxAdd;

	@FXML
	void handleAddProfile(ActionEvent event) throws IOException {
		String text = textFieldAdd.getText();
		if(!isAlphabet(text)){
			labelAdd.setText("Name is not acceptable!");
		} else if (WriteInXML.checkDir(text)){
			labelAdd.setText("Name is already in use! " + text);
		} else {
			chosen.add(text);
			chosen.add((String)firstBoxAdd.getSelectionModel().getSelectedItem().toString());
			chosen.add((String)secondBoxAdd.getSelectionModel().getSelectedItem().toString());
			chosen.add((String)thirdBoxAdd.getSelectionModel().getSelectedItem().toString());
			chosen.add((String)fourthBoxAdd.getSelectionModel().getSelectedItem().toString());
			chosen.add((String)fifthBoxAdd.getSelectionModel().getSelectedItem().toString());
			chosen.add((String)sixthBoxAdd.getSelectionModel().getSelectedItem().toString());
			WriteInXML.writeXML(chosen);
			labelAdd.setText("Success!");
			chosen.clear();
		}
	}
	
	@FXML
	void handleAddBack(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;

        root=FXMLLoader.load(getClass().getResource("/fxml/Opt.fxml"));
        Window mainScene= backAdd.getScene().getWindow();
        stage=(Stage) backAdd.getScene().getWindow();
        stage.setHeight(mainScene.getHeight());
        stage.setWidth(mainScene.getWidth());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}

	public void initialize(URL location, ResourceBundle resources) {
		firstBoxAdd.setItems(units);
		firstBoxAdd.getSelectionModel().select(0);
		secondBoxAdd.setItems(units);
		secondBoxAdd.getSelectionModel().select(1);
		thirdBoxAdd.setItems(units);
		thirdBoxAdd.getSelectionModel().select(2);
		fourthBoxAdd.setItems(units);
		fourthBoxAdd.getSelectionModel().select(3);
		fifthBoxAdd.setItems(units);
		fifthBoxAdd.getSelectionModel().select(4);
		sixthBoxAdd.setItems(units);
		sixthBoxAdd.getSelectionModel().select(5);
	}

}
