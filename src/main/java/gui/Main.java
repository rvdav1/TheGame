package gui;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import persistence.ReadInXML;
import persistence.WriteInXML;


public class Main extends Application {
	public static List<String> firstTeam;
	public static List<String> secondTeam;;
	public static boolean isCPU;
	public static boolean winner;
	public static String location;
	
	public static String getName(){
		return new java.io.File(Main.class.getProtectionDomain()
				  .getCodeSource()
				  .getLocation()
				  .getPath())
				.getName();
	}
	
	public final static Logger logger = Logger.getLogger(Main.class.getName());
	
	@Override
	public void start(Stage stage) {
		firstTeam = ReadInXML.readXML(getClass().getResourceAsStream("/profiles/.xml"));
		secondTeam = ReadInXML.readXML(getClass().getResourceAsStream("/profiles/.xml"));
		location = getClass().getProtectionDomain().getCodeSource().getLocation().toString();
		Main.logger.log(Level.INFO, WriteInXML.getFolder());
		WriteInXML.setDep();
		try {
	        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Main.fxml"));
	        
	        Scene scene = new Scene(root);
	        
	        stage.setTitle("The GaMe!");
	        stage.setScene(scene);
	        stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
