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
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Window;

public class PlayController  implements Initializable{
	FXGameEngine game;
		
	int rowNumFirst = 0;
	int colNumFirst = 0;
	int rowNumLast = 0;
	int colNumLast = 0;
	
	@FXML
	GridPane gridPane;
	
	@FXML
	Label firstTeamLabel1;

	@FXML
	Label firstTeamLabel2;
	
	@FXML
	Label firstTeamLabel3;
	
	@FXML
	Label firstTeamLabel4;
	
	@FXML
	Label firstTeamLabel5;
	
	@FXML
	Label firstTeamLabel6;
	
	@FXML
	Label firstTeamCord1;

	@FXML
	Label firstTeamCord2;
	
	@FXML
	Label firstTeamCord3;
	
	@FXML
	Label firstTeamCord4;
	
	@FXML
	Label firstTeamCord5;
	
	@FXML
	Label firstTeamCord6;
	
	@FXML
	Label secondTeamLabel1;

	@FXML
	Label secondTeamLabel2;
	
	@FXML
	Label secondTeamLabel3;
	
	@FXML
	Label secondTeamLabel4;
	
	@FXML
	Label secondTeamLabel5;
	
	@FXML
	Label secondTeamLabel6;
	
	@FXML
	Label secondTeamCord1;

	@FXML
	Label secondTeamCord2;
	
	@FXML
	Label secondTeamCord3;
	
	@FXML
	Label secondTeamCord4;
	
	@FXML
	Label secondTeamCord5;
	
	@FXML
	Label secondTeamCord6;
	
	@FXML
	Label whoseTurnLabel;
	
	void setLabels(){
		firstTeamLabel1.setText(FXGameEngine.toLabelName(game.getFirstTeam()[0], 1));
		firstTeamCord1.setText(FXGameEngine.toLabelString(game.getFirstTeam()[0]));
		firstTeamLabel2.setText(FXGameEngine.toLabelName(game.getFirstTeam()[1], 2));
		firstTeamCord2.setText(FXGameEngine.toLabelString(game.getFirstTeam()[1]));
		firstTeamLabel3.setText(FXGameEngine.toLabelName(game.getFirstTeam()[2], 3));
		firstTeamCord3.setText(FXGameEngine.toLabelString(game.getFirstTeam()[2]));
		firstTeamLabel4.setText(FXGameEngine.toLabelName(game.getFirstTeam()[3], 4));
		firstTeamCord4.setText(FXGameEngine.toLabelString(game.getFirstTeam()[3]));
		firstTeamLabel5.setText(FXGameEngine.toLabelName(game.getFirstTeam()[4], 5));
		firstTeamCord5.setText(FXGameEngine.toLabelString(game.getFirstTeam()[4]));
		firstTeamLabel6.setText(FXGameEngine.toLabelName(game.getFirstTeam()[5], 6));
		firstTeamCord6.setText(FXGameEngine.toLabelString(game.getFirstTeam()[5]));
		
		secondTeamLabel1.setText(FXGameEngine.toLabelName(game.getSecondTeam()[0], 1));
		secondTeamCord1.setText(FXGameEngine.toLabelString(game.getSecondTeam()[0]));
		secondTeamLabel2.setText(FXGameEngine.toLabelName(game.getSecondTeam()[1], 2));
		secondTeamCord2.setText(FXGameEngine.toLabelString(game.getSecondTeam()[1]));
		secondTeamLabel3.setText(FXGameEngine.toLabelName(game.getSecondTeam()[2], 3));
		secondTeamCord3.setText(FXGameEngine.toLabelString(game.getSecondTeam()[2]));
		secondTeamLabel4.setText(FXGameEngine.toLabelName(game.getSecondTeam()[3], 4));
		secondTeamCord4.setText(FXGameEngine.toLabelString(game.getSecondTeam()[3]));
		secondTeamLabel5.setText(FXGameEngine.toLabelName(game.getSecondTeam()[4], 5));
		secondTeamCord5.setText(FXGameEngine.toLabelString(game.getSecondTeam()[4]));
		secondTeamLabel6.setText(FXGameEngine.toLabelName(game.getSecondTeam()[5], 6));
		secondTeamCord6.setText(FXGameEngine.toLabelString(game.getSecondTeam()[5]));

	}
	
	void whoseTurn(){
		if(game.isFirstPlayer()){
			whoseTurnLabel.setText("Red turn!");
			whoseTurnLabel.setTextFill(Color.RED);
		} else {
			whoseTurnLabel.setText("Blue turn!");
			whoseTurnLabel.setTextFill(Color.BLUE);
		}
	}
	
	@FXML
	void handleBoardButton(ActionEvent event) throws IOException {
		rowNumLast = rowNumFirst;
		colNumLast = colNumFirst;
		
		Button clickedButton = (Button) event.getTarget();
				
		if (GridPane.getRowIndex(clickedButton.getParent()) == null)
			rowNumFirst = 0;
		else
			rowNumFirst = GridPane.getRowIndex(clickedButton.getParent());
		
		if (GridPane.getColumnIndex(clickedButton.getParent()) == null)
			colNumFirst = 0;
		else
			colNumFirst = GridPane.getColumnIndex(clickedButton.getParent());
		if(!game.isEnded())
			game.letsPlay(rowNumLast, colNumLast, rowNumFirst, colNumFirst);
		
		if(game.isEnded()){
			Main.winner = game.firstWon();
	        Stage stage;
	        Parent root;
	        
	        root=FXMLLoader.load(getClass().getResource("/fxml/End.fxml"));
	        Window mainScene= gridPane.getScene().getWindow();
	        stage=(Stage) gridPane.getScene().getWindow();
	        stage.setHeight(mainScene.getHeight());
	        stage.setWidth(mainScene.getWidth());
	        Scene scene = new Scene(root);
	        stage.setScene(scene);
	        stage.show();
		}
		
		if(!game.isFirstPlayer() && Main.isCPU && !game.isEnded())
			game.moveAI();
		
		game.drawBoard(gridPane);
		this.setLabels();
		this.whoseTurn();
	}

	public void initialize(URL location, ResourceBundle resources) {

		game = new FXGameEngine();
		game.initGame(Main.firstTeam, Main.secondTeam);
		game.drawBoard(gridPane);
		this.setLabels();
		this.whoseTurn();
	}
}

