package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.Window;

public class PlayModeController implements Initializable{
	@FXML
	Button CPUPlayModeButton;
	
	@FXML
	Button playerPlayModeButton;
	
	@FXML
	Button backPlayModeButton;
	
    @FXML
	void handlePlayModeCPU(ActionEvent event) throws IOException{
    	Main.isCPU = true;
    	
        Stage stage;
        Parent root;
        
        root=FXMLLoader.load(getClass().getResource("/fxml/Play.fxml"));
        stage=(Stage) CPUPlayModeButton.getScene().getWindow();
        stage.setHeight(800);
        stage.setWidth(800);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
	void handlePlayModePlayer(ActionEvent event) throws IOException{
    	Main.isCPU = false;
    	
        Stage stage;
        Parent root;
        
        root=FXMLLoader.load(getClass().getResource("/fxml/Play.fxml"));
        stage=(Stage) CPUPlayModeButton.getScene().getWindow();
        stage.setHeight(800);
        stage.setWidth(800);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
	@FXML
	void handlePlayModeBack(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        
        Window mainScene= backPlayModeButton.getScene().getWindow();
        root=FXMLLoader.load(getClass().getResource("/fxml/Main.fxml"));
        stage=(Stage) backPlayModeButton.getScene().getWindow();
        stage.setHeight(mainScene.getHeight());
        stage.setWidth(mainScene.getWidth());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}

	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
