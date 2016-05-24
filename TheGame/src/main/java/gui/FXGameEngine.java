package gui;

import engine.*;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class FXGameEngine extends Game{
	
	public void drawBoard(GridPane gridPane){
		boolean isThere = false;
		for (int x = 0; x < 10; x++){
			for (int y = 0; y < 10; y++){
				for (int i = 0; i < 6; i++){
					if (GameUtility.sameZone(this.getBoard().getZone(x, y),this.getFirstTeam()[i].getUnitZone())){
						((AnchorPane) getNodeFromGrid(gridPane,x,y)).setStyle("-fx-background-color: red;");
						isThere = true;
						break;
					} else if (GameUtility.sameZone(this.getBoard().getZone(x, y),
							this.getSecondTeam()[i].getUnitZone())){
						((AnchorPane) getNodeFromGrid(gridPane,x,y)).setStyle("-fx-background-color: blue;");
						isThere = true;
						break;
					} else
						isThere = false;
				}
				if (!isThere)
					((AnchorPane) getNodeFromGrid(gridPane,x,y)).setStyle("-fx-background-color: lightgreen;");
			}
		}
	}
	
	
	public static Node getNodeFromGrid(GridPane gridPane, int row, int col) {
		int realRow;
		int realCol;
		for (Node node : gridPane.getChildren()) {
			if (GridPane.getRowIndex(node) == null)
				realRow = 0;
			else
				realRow = GridPane.getRowIndex(node);
			
			if (GridPane.getColumnIndex(node) == null)
				realCol = 0;
			else
				realCol = GridPane.getColumnIndex(node);
			
			if (realRow == row && realCol == col) {
				return node;
			}
		}
		return null;
	}
	
	public static String toLabelName(CombatUnit unit,int i){
		return Integer.toString(i) + ".unit: " + unit.getUnitName();
	}
	
	public static String toLabelString(CombatUnit unit){
		return "Coordinates: " +
				Integer.toString(unit.getUnitZone().getX()) + "," +
				Integer.toString(unit.getUnitZone().getY()) + " Health: " + 
				Integer.toString(unit.getHealth());
	}
}

