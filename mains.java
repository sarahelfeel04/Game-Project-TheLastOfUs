package application;

import engine.Game;
import exceptions.MovementException;
import exceptions.NotEnoughActionsException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.stage.Stage;
import model.characters.Direction;
import model.characters.Explorer;
import model.characters.Fighter;
import model.characters.Hero;
import model.characters.Medic;
//import javafx.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.DropShadow;
//import javafx.scene.control.Popup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class mains extends Application {
	public static BorderPane borderPane;
	public static Hero currentHero;
	public static VBox startGame;
	public static boolean flagStart = false;
	public static Stage primary1;
	public static Button t = new Button();
	public static VBox forText = new VBox();
	public static Scene startScene;
	public static String path = "ThemeSong.mp3";
    public static Media media = new Media(new File(path).toURI().toString());
    public static MediaPlayer mediaPlayer = new MediaPlayer(media);
    
    public static String pathChange = "ChangeHero.mp3";
    public static Media mediaChange = new Media(new File(pathChange).toURI().toString());
    public static MediaPlayer mediaPlayerChange = new MediaPlayer(mediaChange);
    
	
	

	public static void setCurrentHero(Hero h) {
		currentHero = h;
		if (!Game.heroes.contains(h)) {
			Game.heroes.add(h);
			
			
			
			Game.availableHeroes.remove(h);

		}

	}

	public static void addHero(Hero s) {
		if (!Game.heroes.contains(s)) {
			Game.heroes.add(s);
			Game.availableHeroes.remove(s);
		}
	}

	public static void rerun() {
		mediaPlayer.seek(javafx.util.Duration.ZERO);
        mediaPlayer.play();
		createAvailableHero();
		mains.primary1.hide();
		mains.primary1.setScene(startScene);
		mains.primary1.show();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		//start music
		
	    mediaPlayer.play();
	    mediaPlayer.setVolume(1);
	
		
		
		
		createAvailableHero();

		// create starting button
		Button startButton = new Button("Start Game");
		startButton.setPrefWidth(250);
		startButton.setPrefHeight(100);
		
		Button instructionsButton = new Button("Instructions");
		instructionsButton.setPrefWidth(250);
		instructionsButton.setPrefHeight(100);

		//creating vbox with button
		startGame = new VBox();
		startGame.setPrefSize(1600, 1200);
		startGame.getChildren().addAll(startButton, instructionsButton);

		startButton.setTranslateX(100);
		startButton.setTranslateY(500);
		instructionsButton.setTranslateX(100);
		instructionsButton.setTranslateY(530);
		

		Font font = Font.loadFont("file:PressStart2P-Regular.ttf", 19);
		startButton.setFont(font);
		startButton.setStyle("-fx-background-color: #64ad6a");
		instructionsButton.setFont(font);
		instructionsButton.setStyle("-fx-background-color: #64ad6a");
		instructionsButton.setPrefSize(300,80);
		startButton.setPrefSize(300,80);

		
		
		//change color when mouse entered
		startButton.setOnMouseEntered(e -> {

			startButton.setStyle("-fx-background-color: #e60000");
		});
		
		startButton.setOnMouseExited(e -> {

			startButton.setStyle("-fx-background-color: #64ad6a");
		});
		

		//change color when mouse exited
		instructionsButton.setOnMouseExited(e -> {

			instructionsButton.setStyle("-fx-background-color: #64ad6a");
		});

		instructionsButton.setOnMouseEntered(e -> {

			instructionsButton.setStyle("-fx-background-color: #e60000");
		});
		
		instructionsButton.setOnMouseClicked(e -> {

			Alert alert = createInstructions();
			alert.showAndWait();
		});

		//change color when mouse exited
		
		
		startButton.setAlignment(Pos.CENTER);
		instructionsButton.setAlignment(Pos.CENTER);
		
		//add image to starting scene
		Image startImage = new Image("File:GamePicture.jpg");
		BackgroundImage aa = new BackgroundImage(startImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT,
				new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true));

		//add the startGame VBox to the root
		Pane root = new Pane();
		root.setPrefSize(1600, 1200);
		root.getChildren().add(startGame);
		root.setBackground(new Background(aa));

		//add the root to the startScene and add the startScene to the primaryStage. Make primary1 equal to primaryStage
		startScene = new Scene(root, 1600, 1200);
		primaryStage.setTitle("The Last Of Us");
		primaryStage.setScene(startScene);
		primaryStage.show();
		primary1 = primaryStage;
		primary1.setResizable(false);
		

		
		// choosing heroes scene
		startButton.setOnAction(e -> {

			//hide old stage
			primaryStage.hide();

			//create new stage
			Stage selection = new Stage();
			BorderPane characters = new BorderPane();
			forText = new VBox();
			forText.setPrefSize(400, 300);
			VBox heroess = new VBox();
			heroess.setSpacing(10);
			heroess.setAlignment(Pos.CENTER);
			heroess.setTranslateX(50);

			Label h = new Label("Select from the available Heroes");
			Font font2 = Font.loadFont("file:PressStart2P-Regular.ttf", 25);
			h.setFont(font2);
			
			h.setTextFill(Color.color(1, 1, 1));
			h.setTranslateX(70);
			h.setTranslateY(50);
			
			

			characters.setTop(h);

			forText.getChildren().add(t);
			characters.setRight(forText);
			forText.setTranslateX(-200);
			forText.setTranslateY(450);

			for (int i = 0; i < Game.availableHeroes.size(); i++) {
				String name = Game.availableHeroes.get(i).getName();
				Hero hero = Game.availableHeroes.get(i);
				Button button = new Button(name);
				button.setPrefWidth(280);

				Font font1 = Font.loadFont("file:PressStart2P-Regular.ttf", 13);
				button.setPrefHeight(60);
				button.setFont(font1);
				heroess.getChildren().add(button);
				button.setTranslateX(50);
				characters.setLeft(heroess);
				button.setStyle("-fx-background-color: #cec8c8");

				// Set the background fill color
		        t.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");

		        // Set the font color
		        t.setTextFill(Color.WHITE);
		        t.setText("All available Heroes");
		        t.setFont(Font.font("Courier New", FontWeight.BOLD, 16));
				t.setPrefSize(300, 200);
				t.setTranslateX(130);
				t.setTranslateY(50);
				Image g=new Image("File:Guitar.jpeg");
				BackgroundImage gg = new BackgroundImage(g, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT,
				new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true));
				characters.setBackground(new Background(gg));
				
				
				button.setOnMouseEntered(event -> {
					displayDataStart(hero);
					button.setStyle("-fx-background-color: #e60000");
					t.setPrefSize(300, 200);

					switch (button.getText()) {
					case "Joel Miller":
						Image joel=new Image("File:Joel Miller.jpg");
						BackgroundImage miller = new BackgroundImage(joel, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
						BackgroundPosition.DEFAULT,
						new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true));
						characters.setBackground(new Background(miller));break;

						case"Ellie Williams":
						Image ellie=new Image("File:Ellie.jpeg");
						BackgroundImage williams = new BackgroundImage(ellie, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
						BackgroundPosition.DEFAULT,
						new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true));
						characters.setBackground(new Background(williams));break;

						case"Tess":
						Image tess=new Image("File:Tess.jpg");
						BackgroundImage tessBackground = new BackgroundImage(tess, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
						BackgroundPosition.DEFAULT,
						new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true));
						characters.setBackground(new Background(tessBackground));break;

						case"Riley Abel":
						Image riley=new Image("File:Riley.jpeg");
						BackgroundImage abel = new BackgroundImage(riley, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
						BackgroundPosition.DEFAULT,
						new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true));
						characters.setBackground(new Background(abel));break;

						case"Tommy Miller":

						Image tommy=new Image("File:Tommy Miller.jpeg");
						BackgroundImage tMiller = new BackgroundImage(tommy, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
						BackgroundPosition.DEFAULT,
						new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true));
						characters.setBackground(new Background(tMiller));break;
						
						case"Bill":
							Image Bill=new Image("File:Bill.jpeg");
							BackgroundImage billy = new BackgroundImage(Bill, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
							BackgroundPosition.DEFAULT,
							new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true));
							characters.setBackground(new Background(billy));break;
							
						case"David":
							Image David=new Image("File:DavidM.jpeg");
							BackgroundImage davidy = new BackgroundImage(David, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
							BackgroundPosition.DEFAULT,
							new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true));
							characters.setBackground(new Background(davidy));break;
							
						case "Henry Burell":
							Image Henry=new Image("File:Henry.png");
							BackgroundImage henryy = new BackgroundImage(Henry, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
							BackgroundPosition.DEFAULT,
							new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true));
							characters.setBackground(new Background(henryy));break;
						}
					    
				
					
					

					
					

				});
				button.setOnMouseExited(event -> {
					/*
					 t.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");

				        // Set the font color
				        t.setTextFill(Color.WHITE);
				        t.setText("All available Heroes");
				        t.setFont(Font.font("Courier New", FontWeight.BOLD, 16));
						t.setPrefSize(300, 200);
						t.setTranslateX(130);
						t.setTranslateY(50);
					characters.setBackground(new Background(gg));*/
					button.setStyle("-fx-background-color: #cec8c8");
				});

				//start Game transition
				button.setOnAction(event -> {
					mediaPlayer.setVolume(0.2);
					
					mediaPlayer.setOnEndOfMedia(() -> {
			            // Rewind the MediaPlayer to the beginning
			            mediaPlayer.seek(javafx.util.Duration.ZERO);
			            // Play the sound again
			            mediaPlayer.play();
			        });

					
					// change scene again to game when clicked:
					mains.setCurrentHero(hero);

					borderPane = new BorderPane();

					//create border
					Game.startGame(hero);
					LeftSide.createLeftSide();
					RightSide.startRightSide();
					CenterSide.createCenterSide();
					//DownSide.createDownSide();
					Label l = new Label("");
					borderPane.setTop(l);
					//l.setPrefSize(0, 0);
					Label l2 = new Label("");
					borderPane.setBottom(l2);
					//add to border
					borderPane.setLeft(LeftSide.leftSideBox);
					CenterSide.gridInABox.setPrefHeight(750);
					CenterSide.gridInABox.setPrefWidth(900);
					borderPane.setCenter(CenterSide.gridInABox);
					borderPane.setRight(RightSide.allData);
					//borderPane.setBottom(DownSide.downMove);

					// set alignment and spacing and sizes
					LeftSide.leftSideBox.setAlignment(Pos.CENTER);
					RightSide.allData.setAlignment(Pos.CENTER);
					//DownSide.downMove.setSpacing(10);
					//DownSide.setSizes();
					//change to gridInABox
					CenterSide.gridInABox.setAlignment(Pos.CENTER);
					LeftSide.leftSideBox.setPrefWidth(200);
					//HERE
					CenterSide.gridInABox.setPrefHeight(750);
					CenterSide.gridInABox.setPrefWidth(900);
					RightSide.allData.setPrefWidth(200);

					//hide old scene
					primary1.hide();
					Scene scene1 = new Scene(borderPane, 1600, 1200);
					Image startImage2 = new Image("File:blurredBackground.jpeg");
					BackgroundImage aa2 = new BackgroundImage(startImage2, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
							BackgroundPosition.DEFAULT,
							new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true));
					borderPane.setBackground(new Background(aa2));
					
					//set Starting music
					//start music
					//String path2 = "ThemeSong.mp3";
				    //Media media2 = new Media(new File(path2).toURI().toString());
				   // MediaPlayer mediaPlayer2 = new MediaPlayer(media2);
				    //mediaPlayer.stop();
				    //mediaPlayer2.setAutoPlay(true);
					
					scene1.getStylesheets().add("File:button-style.css");
					scene1.getStylesheets().add("File:herButton-style.css");
					scene1.getStylesheets().add("File:her2Button-style.css");
					scene1.getStylesheets().add("File:button3-style.css");
					primary1.setScene(scene1);
					primary1.show();
					
					
					
					scene1.setOnKeyPressed(e1->{
						KeyCode keyCode = e1.getCode();
						switch(keyCode) {
						case W: {
							try {
								mains.currentHero.move(Direction.UP);
								
								mains.checkGameOverView();
								mains.updateWindow();
							} catch (MovementException e2) {
								
								DownSide.mediaPlayerMove.seek(javafx.util.Duration.ZERO);
								DownSide.mediaPlayerMove.play();
								
							} catch (NotEnoughActionsException e2) {
								DownSide.mediaPlayerMove.seek(javafx.util.Duration.ZERO);
								DownSide.mediaPlayerMove.play();
								Alert alert = new Alert(AlertType.WARNING);
								alert.getDialogPane().setHeaderText(null);
						        alert.getDialogPane().setHeader(null);
								alert.setTitle("Not Enough Actions");
								alert.getDialogPane().setGraphic(null);
								alert.getDialogPane().setStyle("-fx-background-color: #A3866A;");
								//Label l = new Label (e1.getMessage());
								//l.setFont(LeftSide.font);
								alert.setContentText(e2.getMessage());
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
							break;
							}
							
						case A: {
							try {
								mains.currentHero.move(Direction.LEFT);
								
								mains.checkGameOverView();
								mains.updateWindow();
							} catch (MovementException e2) {
								
								DownSide.mediaPlayerMove.seek(javafx.util.Duration.ZERO);
								DownSide.mediaPlayerMove.play();
								
							} catch (NotEnoughActionsException e2) {
								DownSide.mediaPlayerMove.seek(javafx.util.Duration.ZERO);
								DownSide.mediaPlayerMove.play();
								Alert alert = new Alert(AlertType.WARNING);
								alert.getDialogPane().setHeaderText(null);
						        alert.getDialogPane().setHeader(null);
								alert.setTitle("Not Enough Actions");
								alert.getDialogPane().setGraphic(null);
								alert.getDialogPane().setStyle("-fx-background-color: #A3866A;");
								//Label l = new Label (e1.getMessage());
								//l.setFont(LeftSide.font);
								alert.setContentText(e2.getMessage());
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
							break;
							}
						case D: {
							try {
								mains.currentHero.move(Direction.RIGHT);
								
								mains.checkGameOverView();
								mains.updateWindow();
							} catch (MovementException e2) {
								
								DownSide.mediaPlayerMove.seek(javafx.util.Duration.ZERO);
								DownSide.mediaPlayerMove.play();
								
							} catch (NotEnoughActionsException e2) {
								DownSide.mediaPlayerMove.seek(javafx.util.Duration.ZERO);
								DownSide.mediaPlayerMove.play();
								Alert alert = new Alert(AlertType.WARNING);
								alert.getDialogPane().setHeaderText(null);
						        alert.getDialogPane().setHeader(null);
								alert.setTitle("Not Enough Actions");
								alert.getDialogPane().setGraphic(null);
								alert.getDialogPane().setStyle("-fx-background-color: #A3866A;");
								//Label l = new Label (e1.getMessage());
								//l.setFont(LeftSide.font);
								alert.setContentText(e2.getMessage());
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
							break;
							}
						case S: {
							try {
								mains.currentHero.move(Direction.DOWN);
								
								mains.checkGameOverView();
								mains.updateWindow();
							} catch (MovementException e2) {
								
								DownSide.mediaPlayerMove.seek(javafx.util.Duration.ZERO);
								DownSide.mediaPlayerMove.play();
								
							} catch (NotEnoughActionsException e2) {
								DownSide.mediaPlayerMove.seek(javafx.util.Duration.ZERO);
								DownSide.mediaPlayerMove.play();
								Alert alert = new Alert(AlertType.WARNING);
								alert.getDialogPane().setHeaderText(null);
						        alert.getDialogPane().setHeader(null);
								alert.setTitle("Not Enough Actions");
								alert.getDialogPane().setGraphic(null);
								alert.getDialogPane().setStyle("-fx-background-color: #A3866A;");
								//Label l = new Label (e1.getMessage());
								//l.setFont(LeftSide.font);
								alert.setContentText(e2.getMessage());
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
							break;
							}
							
							
							
							
							
							
						
						}
						
						
					});
					

					

				});

			}

			//if not the case remain on the scene with characters
			Scene sc2 = new Scene(characters, 1600, 1200);
			primary1.setScene(sc2);
			primary1.setResizable(false);
			primary1.show();


		});
	}

	public static void updateWindow() {
		// create them again
		Label l = new Label("");
		borderPane.setTop(l);
		//l.setPrefSize(0, 0);
		Label l2 = new Label("");
		borderPane.setBottom(l2);
		
		LeftSide.createLeftSide();
		RightSide.startRightSide();
		CenterSide.createCenterSide();

		borderPane.setLeft(LeftSide.leftSideBox);
		CenterSide.gridInABox.setPrefHeight(750);
		CenterSide.gridInABox.setPrefWidth(900);
		borderPane.setCenter(CenterSide.gridInABox);
		borderPane.setRight(RightSide.allData);
		//borderPane.setBottom(DownSide.downMove);

		// update the alignment and position
		LeftSide.leftSideBox.setAlignment(Pos.CENTER);
		RightSide.allData.setAlignment(Pos.CENTER);
		//DownSide.downMove.setSpacing(10);
		//DownSide.setSizes();
		CenterSide.gridInABox.setAlignment(Pos.CENTER);
//200,1200,200
		LeftSide.leftSideBox.setPrefWidth(200);
		//HER
		CenterSide.gridInABox.setPrefWidth(900);
		CenterSide.gridInABox.setPrefHeight(750);
		RightSide.allData.setPrefWidth(200);

	}

	public static void createAvailableHero() {

		try {
			Game.loadHeroes("Heroes.csv");
		} catch (IOException e) {
		
		}

	}

	

	public static void checkGameOverView() {
		if (Game.checkGameOver() == true) {

			Alert alert1 = new Alert(AlertType.WARNING);
			
			Image win=new Image("File:GameWin2.gif");
			ImageView won=new ImageView(win);	
			won.setFitWidth(450);
			won.setFitHeight(240);
			
			alert1.setGraphic(null);

			if (Game.checkWin()) {
				ButtonType b = new ButtonType("Play again ?", ButtonData.YES);
				ButtonType a = ButtonType.CLOSE;
				Dialog<ButtonType> dialog = new Dialog<>();
				dialog.getDialogPane().setContent(won);
				alert1.getDialogPane().setContent(dialog.getDialogPane().getContent());
				alert1.initOwner(mains.primary1);
				alert1.getButtonTypes().setAll(a);
				alert1.getButtonTypes().add(b);
				if (!LeftSide.s) {
				String path = "GameWinMario.mp3";
			    Media media = new Media(new File(path).toURI().toString());
			    MediaPlayer mediaPlayer = new MediaPlayer(media);
			    mediaPlayer.seek(javafx.util.Duration.ZERO);
			    mediaPlayer.play();}
				
				alert1.setTitle("GAME WON");
								alert1.setHeaderText(null);
				alert1.showAndWait();
				if (alert1.getResult() == b) {
					mains.mediaPlayer.stop();
					//mains.mediaPlayer.setAutoPlay(true);
					rerun();
				} 
				
				else if (alert1.getResult() == a)
					mains.primary1.close();
				
				alert1.setOnCloseRequest(event-> {
					alert1.close();
				});

			}

			else {
				ButtonType b = new ButtonType("Play Again ?", ButtonData.YES);
				ButtonType a = ButtonType.CLOSE;
				alert1.getButtonTypes().setAll(a);
				alert1.getButtonTypes().add(b);
				alert1.setTitle("GAME OVER");
				alert1.setGraphic(null);
				ImageView l=(new ImageView(new Image("File:GameLost2.gif")));
				l.setFitWidth(450);
				l.setFitHeight(240);
				
				Dialog<ButtonType> dialog = new Dialog<>();
				dialog.getDialogPane().setContent(l);
				alert1.getDialogPane().setContent(dialog.getDialogPane().getContent());
		        
			if (!LeftSide.s) {
				String path = "LoseSound.mp3";
			    Media media = new Media(new File(path).toURI().toString());
			    MediaPlayer mediaPlayer = new MediaPlayer(media);
			    mediaPlayer.seek(javafx.util.Duration.ZERO);
				mediaPlayer.play();
			}  
			   // mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(javafx.util.Duration.ZERO));
			    
			    
				alert1.initOwner(mains.primary1);
				alert1.setHeaderText(null);
				//alert1.setContentText("THE ZOMBIES DEFEATED YOU AND YOU LOST! HARD LUCK. \nDo you want to play again?");
				alert1.showAndWait();
				if (alert1.getResult() == b) {
					//mediaPlayer.stop();
					mains.mediaPlayer.stop();
					mediaPlayer.stop();
					//mains.mediaPlayer.setAutoPlay(true);
					rerun();
//					
				} else if (alert1.getResult() == a)
					//mediaPlayer.stop();
					mains.primary1.close();
				
				alert1.setOnCloseRequest(event-> {
					//mediaPlayer.stop();
					alert1.close();
				});

			}

		}
	}

	public static void displayDataStart(Hero h) {
		//String backgroundColor = "-fx-background-color: rgba(0, 0, 0, 1);";
		t.setPrefSize(300, 200);

        // Set the background fill color
        t.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");

        // Set the font color
        t.setTextFill(Color.WHITE);
        //t.setStyle(backgroundColor);
        
		
		String type = "";
		if (h instanceof Fighter)
			type = "Fighter: ";
		else if (h instanceof Medic)
			type = "Medic: ";
		else if (h instanceof Explorer)
			type = "Explorer: ";
		else
			type = "Hero: ";

		String text = type + h.getName() + "\n\nCurrent HP:  " + h.getCurrentHp() + "\nAttack Damage: "
				+ h.getAttackDmg() + "\nActions Available: " + h.getActionsAvailable() + "\nVaccines: "
				+ h.getVaccineInventory().size() + "\nSupplies: " + h.getSupplyInventory().size();

		if (h.getCurrentHp() <= 0)
			text = "Your hero is dead, please" + "\nselect another hero";

		// add data to textArea
		t.setText(text);
		
		t.setFont(Font.font("Courier New", FontWeight.BOLD, 16));
		t.setTranslateX(130);
		t.setTranslateY(50);
		//t.setStyle("-fx-font-weight: bold;");
		//t.setStyle("-fx-text-fill: white;");
		//t.setPrefSize(700, 300);

		//t.setEditable(false);
		// return a;
	}
	
	public static Alert createInstructions() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Game Instructions");
        alert.getDialogPane().setHeader(null);
		alert.getDialogPane().setPrefSize(625, 350);
		alert.getDialogPane().setGraphic(null);
		//alert.getDialogPane().setStyle("-fx-background-color: #A3866A; -fx-font-family: Courier New; -fx-font-size: 12px; -fx-font-weight: bold; -fx-text-fill: white;");
		 
		String text ="The Last of Us: Legacy is a single player survival game set in a zombie apocalyptic world. The game is conducted in a turn based manner, in which each player character receives a specific number of action points per turn, which they can use to move, attack or cure zombies, or use special actions.\n\n"
				+ "The player starts the game controlling only one hero, but can gain additional heroes by curing zombies.\nThe objective of the game for the player is to survive as long as it takes in order to cure a sufficient number of zombies enough to build a community to survive the apocalypse.\nThe player can only see the directly adjacent cells next to their pool of heroes. The player then keeps taking his turn trying to collect vaccines, and cure or kill zombies.\n\nThe game ends when the player has collected and used all vaccines or when all heroes have been overwhelmed and defeated by the zombies.\n"
				+ "The player only wins if he has successfully collected and used all vaccines and has 5 or more heroes alive.";
		alert.setContentText(text);
		alert.getDialogPane().setStyle("-fx-background-color: #A3866A;-fx-font-family: 'Courier New'; -fx-font-size: 12px; -fx-text-fill: white;");
	       alert.getDialogPane().lookup(".content.label").setStyle("-fx-font-weight: bold;");
		//alert.getDialogPane().getContentText().setFont(LeftSide.font3);
	    //Label l = new Label(text);
	    //l.setStyle("-fx-text-fill: white;");
	    alert.setHeaderText(null);
	    ButtonType closeButton = ButtonType.CANCEL;
        alert.getButtonTypes().setAll(closeButton);
        for (ButtonType buttonType : alert.getDialogPane().getButtonTypes()) {
               alert.getDialogPane().lookupButton(buttonType).setStyle("-fx-background-color: #333333; -fx-text-fill: white;");
           }
		
		return alert;
		
	}

	public static void main(String[] args) {

		launch(args); // Step 5
	}

}