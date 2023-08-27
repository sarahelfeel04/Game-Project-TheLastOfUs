package application;




import javafx.scene.control.Label;

import java.io.File;

//import javax.print.attribute.standard.Media;

import exceptions.MovementException;

import exceptions.NotEnoughActionsException;
import javafx.geometry.Insets;
//import javafx.*;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import model.characters.Direction;
import model.characters.Hero;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
//import javafx.scene.media.Media;

public class DownSide {
	public static Button upButton = new Button("Up");
	public static Button downButton = new Button("Down");
	public static Button leftButton = new Button("Left");
	public static Button rightButton = new Button("Right");
	public static Button centerButton = new Button("Center");
	public static BorderPane downMove = new BorderPane();
	
	
	
	public static String pathMove = "Error.mp3";
    public static Media mediaMove = new Media(new File(pathMove).toURI().toString());
    public static MediaPlayer mediaPlayerMove = new MediaPlayer(mediaMove);
    public static String pathMoveC = "moveF.mp3";
    public static Media mediaMoveC = new Media(new File(pathMoveC).toURI().toString());
    public static MediaPlayer mediaPlayerMoveC = new MediaPlayer(mediaMoveC);
    public static String pathMoveVS = "collectible.mp3";
    public static Media mediaMoveVS = new Media(new File(pathMoveVS).toURI().toString());
    public static MediaPlayer mediaPlayerMoveVS = new MediaPlayer(mediaMoveVS);
    
    public static String pathDeath= "DeathSoundEffect.mp3";
    public static Media mediaDeath = new Media(new File(pathDeath).toURI().toString());
    public static MediaPlayer mediaPlayerDeath = new MediaPlayer(mediaDeath);
    
    public static String pathTrap= "TrapSound.mp3";
    public static Media mediaTrap = new Media(new File(pathTrap).toURI().toString());
    public static MediaPlayer mediaPlayerTrap = new MediaPlayer(mediaTrap);

	// public static Hero currentHero;

	public static void createDownSide() {
		createButtonsMove();
	}
	
	public static BorderPane getDownMove() {
		
		createButtonsMove();
		setSizes();
		downMove.setTop(upButton);
		downMove.setBottom(downButton);
		downMove.setLeft(leftButton);
		downMove.setRight(rightButton);
		downMove.setCenter(centerButton);
		upButton.setTranslateX(70);
		downButton.setTranslateX(70);
		rightButton.setTranslateX(-33);
		leftButton.setTranslateX(20);
		centerButton.setTranslateX(-5);
		
		
		return downMove;
	}

	public static void setCurrenthero(Hero h) {
		mains.currentHero = h;
	}

	public static void setSizes() {
		
		setPictures();
		upButton.setPrefSize(50, 50);
		upButton.setPadding(new Insets(0,0,0,0));
		downButton.setPrefSize(50, 52);
		downButton.setPadding(new Insets(0,0,0,0));
		rightButton.setPrefSize(50, 48);
		rightButton.setPadding(new Insets(0,0,0,0));
		leftButton.setPrefSize(50, 50);
		leftButton.setPadding(new Insets(0,0,0,0));
		centerButton.setPrefSize(49.8,49.3);
		centerButton.setPadding(new Insets(0,0,0,0));
		
		upButton.setTranslateY(-10);
		downButton.setTranslateY(-14);
		rightButton.setTranslateY(-10);
		leftButton.setTranslateY(-10);
		centerButton.setTranslateY(-10);
		
		//upButton.setBorder();
		//downMove.setAlignment(Pos.TOP_CENTER);
		
	}

	public static void setPictures() {
		
	
        
        ImageView right=new ImageView(new Image("File:RB.jpeg"));
        rightButton.getStyleClass().clear();
        right.setFitHeight(48);
        right.setFitWidth(50);
    	rightButton.setGraphic(right);
    	rightButton.setText("");
    	
    	ImageView left=new ImageView(new Image("File:LB.jpeg"));
    	leftButton.getStyleClass().clear();
    	left.setFitHeight(50);
    	left.setFitWidth(50);
    	leftButton.setGraphic(left);
    	leftButton.setText("");
    	
    	
    	ImageView up=new ImageView(new Image("File:UB.jpeg"));
    	upButton.getStyleClass().clear();
    	up.setFitHeight(50);
    	up.setFitWidth(50);
    	upButton.setGraphic(up);
    	upButton.setText("");
    	
    	
    	ImageView center =new ImageView(new Image("File:CB.jpeg"));
    	centerButton.getStyleClass().clear(); // Remove default button styles
    	//centerButton.getStylesheets().add("styles.css");
    	//centerButton.setEffect(null); // Remove the glowing effect
    	center.setFitHeight(49.3);
    	center.setFitWidth(49.8);
    	
    	centerButton.setGraphic(center);
    	centerButton.setText("");
    	
    	ImageView down =new ImageView(new Image("File:DB2.jpeg"));
    	downButton.getStyleClass().clear();
    	down.setFitHeight(52);
    	down.setFitWidth(50);
    	downButton.setGraphic(down);
    	downButton.setText("");
    	
    	

	}

	public static void createButtonsMove() {
		upButton = new Button("Up");
		downButton = new Button("Down");
		leftButton = new Button("Left");
		rightButton = new Button("Right");
		centerButton=new Button("Center");
		downMove.setTop(upButton);
		downMove.setBottom(downButton);
		downMove.setLeft(leftButton);
		downMove.setRight(rightButton);
		downMove.setCenter(centerButton);
		//downMove = new HBox(20, upButton, downButton, leftButton, rightButton);

		upButton.setOnAction(e -> {
			try {
				mains.currentHero.move(Direction.UP);
				//mediaPlayerMoveC.seek(javafx.util.Duration.ZERO);
				//mediaPlayerMoveC.play();
				mains.checkGameOverView();
				mains.updateWindow();
			} catch (MovementException e1) {
				//Alert alert = new Alert(AlertType.WARNING);
				//alert.setTitle("Invalid Movement");
				//alert.setHeaderText(null);
				//alert.setContentText(e1.getMessage());
				
				mediaPlayerMove.seek(javafx.util.Duration.ZERO);
				mediaPlayerMove.play();
				
				
				
				//ButtonType closeButton = ButtonType.CLOSE;
				//alert.getButtonTypes().setAll(closeButton);
				//alert.initOwner(mains.primary1);

				//alert.showAndWait();
			} catch (NotEnoughActionsException e1) {
				mediaPlayerMove.seek(javafx.util.Duration.ZERO);
				mediaPlayerMove.play();
				Alert alert = new Alert(AlertType.WARNING);
				alert.getDialogPane().setHeaderText(null);
		        alert.getDialogPane().setHeader(null);
				alert.setTitle("Not Enough Actions");
				alert.getDialogPane().setGraphic(null);
				alert.getDialogPane().setStyle("-fx-background-color: #A3866A;");
				//Label l = new Label (e1.getMessage());
				//l.setFont(LeftSide.font);
				alert.setContentText(e1.getMessage());
				Label contentLabel = (Label) alert.getDialogPane().lookup(".content.label");
			    contentLabel.setFont(LeftSide.font);

				
				ButtonType closeButton = ButtonType.CLOSE;
				alert.getButtonTypes().setAll(closeButton);
				for (ButtonType buttonType : alert.getDialogPane().getButtonTypes()) {
		            alert.getDialogPane().lookupButton(buttonType).setStyle("-fx-background-color: #333333; -fx-text-fill: white;");
		        }
				alert.initOwner(mains.primary1);

				alert.showAndWait();
			}

		});
		
		
		
		
		downButton.setOnAction(e -> {
			try {
				mains.currentHero.move(Direction.DOWN);
				//mediaPlayerMoveC.seek(javafx.util.Duration.ZERO);
				//mediaPlayerMoveC.play();
				mains.checkGameOverView();
				mains.updateWindow();
			} catch (MovementException e1) {
				//Alert alert = new Alert(AlertType.WARNING);
				//alert.setTitle("Invalid Movement");
				//alert.setHeaderText(null);
				//alert.setContentText(e1.getMessage());

				mediaPlayerMove.seek(javafx.util.Duration.ZERO);
				mediaPlayerMove.play();
			    
				//ButtonType closeButton = ButtonType.CLOSE;
				//alert.getButtonTypes().setAll(closeButton);
				//alert.initOwner(mains.primary1);

				//alert.showAndWait();
			} catch (NotEnoughActionsException e1) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Not Enough Actions");
				alert.setHeaderText(null);
				alert.setContentText(e1.getMessage());
				mediaPlayerMove.seek(javafx.util.Duration.ZERO);
				mediaPlayerMove.play();
				
				alert.getDialogPane().setGraphic(null);
				alert.getDialogPane().setStyle("-fx-background-color: #A3866A;");
				//Label l = new Label (e1.getMessage());
				//l.setFont(LeftSide.font);
				
				Label contentLabel = (Label) alert.getDialogPane().lookup(".content.label");
			    contentLabel.setFont(LeftSide.font);

				
				ButtonType closeButton = ButtonType.CLOSE;
				alert.getButtonTypes().setAll(closeButton);
				for (ButtonType buttonType : alert.getDialogPane().getButtonTypes()) {
		            alert.getDialogPane().lookupButton(buttonType).setStyle("-fx-background-color: #333333; -fx-text-fill: white;");
		        }
				alert.initOwner(mains.primary1);

				alert.showAndWait();
			}

		});
		rightButton.setOnAction(e -> {
			try {
				mains.currentHero.move(Direction.RIGHT);
				//mediaPlayerMoveC.seek(javafx.util.Duration.ZERO);
				//mediaPlayerMoveC.play();
				// mains.checkGameWinView ();
				mains.checkGameOverView();
				mains.updateWindow();
			} catch (MovementException e1) {
				/*Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Invalid Movement");
				alert.setHeaderText(null);
				alert.setContentText(e1.getMessage());*/
				mediaPlayerMove.seek(javafx.util.Duration.ZERO);
				mediaPlayerMove.play();

				//ButtonType closeButton = ButtonType.CLOSE;
				//alert.getButtonTypes().setAll(closeButton);
				//alert.initOwner(mains.primary1);

				//alert.showAndWait();
			} catch (NotEnoughActionsException e1) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Not Enough Actions");
				
				
				alert.getDialogPane().setHeaderText(null);
		        alert.getDialogPane().setHeader(null);
				
				alert.getDialogPane().setGraphic(null);
				alert.getDialogPane().setStyle("-fx-background-color: #A3866A;");
				
				alert.setContentText(e1.getMessage());
				Label contentLabel = (Label) alert.getDialogPane().lookup(".content.label");
			    contentLabel.setFont(LeftSide.font);
				
				
			
				mediaPlayerMove.seek(javafx.util.Duration.ZERO);
				mediaPlayerMove.play();
				ButtonType closeButton = ButtonType.CLOSE;
				alert.getButtonTypes().setAll(closeButton);
				for (ButtonType buttonType : alert.getDialogPane().getButtonTypes()) {
		            alert.getDialogPane().lookupButton(buttonType).setStyle("-fx-background-color: #333333; -fx-text-fill: white;");
		        }
				alert.initOwner(mains.primary1);

				alert.showAndWait();
			}

		});
		leftButton.setOnAction(e -> {
			try {
				mains.currentHero.move(Direction.LEFT);
				//mediaPlayerMoveC.seek(javafx.util.Duration.ZERO);
				//mediaPlayerMoveC.play();
				// mains.checkGameWinView ();
				mains.checkGameOverView();
				mains.updateWindow();
			} catch (MovementException e1) {
//				Alert alert = new Alert(AlertType.WARNING);
//				alert.setTitle("Invalid Movement");
//				alert.setHeaderText(null);
//				alert.setContentText(e1.getMessage());
				mediaPlayerMove.seek(javafx.util.Duration.ZERO);
				mediaPlayerMove.play();
//				ButtonType closeButton = ButtonType.CLOSE;
//				alert.getButtonTypes().setAll(closeButton);
//				alert.initOwner(mains.primary1);
//
//				alert.showAndWait();
			} catch (NotEnoughActionsException e1) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Not Enough Actions");
				alert.setHeaderText(null);
				

				alert.getDialogPane().setHeaderText(null);
		        alert.getDialogPane().setHeader(null);
				
				alert.getDialogPane().setGraphic(null);
				alert.getDialogPane().setStyle("-fx-background-color: #A3866A;");
				
				alert.setContentText(e1.getMessage());
				Label contentLabel = (Label) alert.getDialogPane().lookup(".content.label");
			    contentLabel.setFont(LeftSide.font);
				
				
				
				mediaPlayerMove.seek(javafx.util.Duration.ZERO);
				mediaPlayerMove.play();
				ButtonType closeButton = ButtonType.CLOSE;
				alert.getButtonTypes().setAll(closeButton);
				for (ButtonType buttonType : alert.getDialogPane().getButtonTypes()) {
		            alert.getDialogPane().lookupButton(buttonType).setStyle("-fx-background-color: #333333; -fx-text-fill: white;");
		        }
				alert.initOwner(mains.primary1);

				alert.showAndWait();
			}

		});
		
		
		
		
		
		
		
		
		

	}
}
