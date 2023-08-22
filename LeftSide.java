package application;


import engine.Game;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.characters.Explorer;
import model.characters.Fighter;
import model.characters.Hero;
import model.characters.Medic;
import javafx.geometry.Insets;
//import javafx.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
//import javafx.scene.control.Popup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;




public class LeftSide {
    
	public static Label  availableHeroesLabel;
	public static Label  heroesLabel;
	public static VBox availableHeroesBox;
	public static VBox heroesBox;
	public static VBox leftSideBox;
	public static VBox exitControlMute;
	//public static Hero currentHero;
	public static Font font = Font.loadFont("file:PressStart2P-Regular.ttf", 16);
	public static Font font2 = Font.font("Courier New", FontWeight.BOLD, 14);
	public static boolean s; //if true -> music off, if false -> music on
	public static Button sound=new Button();
	public static Button exit;
	public static ImageView volume=  new ImageView(new Image("File:VolumeUp.jpeg"));
	public static ImageView volumeZero =  new ImageView(new Image("File:VolumeDown.jpeg"));
	public static Font font3 = Font.font("Courier New", FontWeight.BOLD, 12);
	
	
	public static BorderPane movement;
	
	
	public static void createLeftSide () {

	
	// Left side - Available Heroes
    availableHeroesLabel = new Label("Available\n Heroes");
    availableHeroesLabel.setStyle("-fx-text-fill: white;");
    availableHeroesLabel.setFont(font);
    availableHeroesBox = new VBox(availableHeroesLabel);
    availableHeroesBox.setSpacing(10);
    availableHeroesBox.setAlignment(Pos.CENTER);
    //mains.createAvailableHeroButtons();
    
    for (int i = 0; i < Game.availableHeroes.size(); i++) {
    	String name= Game.availableHeroes.get(i).getName();
    	Hero hero = Game.availableHeroes.get(i);
        Button button = new Button(name);
        //button.setStyle("-fx-background-radius: 15;");
        button.setFont(font2);
        button.getStyleClass().add("her-button-style");
        button.setOnMouseClicked(event -> displayHeroData2(hero));
        availableHeroesBox.getChildren().add(button);
        button.setOnMouseEntered(event->{
        	button.getStyleClass().add("button3-style");
        });
        button.setOnMouseExited(event->{
        	button.getStyleClass().clear();
        	button.setFont(font2);
        	button.setText(" "+name+" ");
        	
        	button.getStyleClass().add("her-button-style");
        });
       
    }
    
    heroesLabel = new Label("\nHeroes");
    heroesLabel.setFont(font);
    heroesLabel.setStyle("-fx-text-fill: white;");
    heroesBox = new VBox(heroesLabel);
    heroesBox.setAlignment(Pos.CENTER);
   // heroesBox.setPrefSize(100, 120);
    
    heroesBox.setSpacing(10);
    
    
    
    
    
    exit=new Button("EXIT");
    exit.setFont(font2);
    exit.setStyle("-fx-background-color: #B12E14; -fx-text-fill: white; ");
    exit.setOnAction(e->mains.primary1.close());
    sound=new Button();
    sound.getStyleClass().clear();
    sound.setStyle("-fx-background-color: white; -fx-border-width:3px; -fx-border-color:white;");
    
    //sound.setTranslateY(140);
    //exit.setTranslateY(140);
    //sound.setTranslateX(30);
   // exit.setTranslateX(30);
    
    
     //ImageView volume=  new ImageView(new Image("File:VolumeUp.jpeg"));
    volume.setFitHeight(30);
    volume.setFitWidth(30);
   if(!s) {
	   sound.setGraphic(volume);
	   volume.setFitHeight(30);
	   volume.setFitWidth(30);
	   
   }else
	   if(s) {
	   sound.setGraphic(volumeZero);
	   volumeZero.setFitHeight(30);
	   volumeZero.setFitWidth(30);
   }
   
    
   sound.setOnAction(e->{
	  
	   music();
	   if(s) {
		   sound.setGraphic(volume);
		   
		   
	   }if(!s) {
		   sound.setGraphic(volumeZero);
		   
	   }
	   
	  s=!s;
   });
    
    
    
    
    
    
    
    
    for (int i = 0; i < Game.heroes.size(); i++) {
    	String name= Game.heroes.get(i).getName();
    	Hero hero = Game.heroes.get(i);
        Button button = new Button(name);
       // button.setStyle("-fx-background-radius: 15;");
        button.setFont(font2);
        button.getStyleClass().add("her2-button-style");
        button.setOnMouseClicked(event -> displayHeroData(hero));
        heroesBox.getChildren().add(button);
        button.setOnMouseEntered(event->{
        	button.getStyleClass().add("button3-style");
        });
        
        button.setOnMouseExited(event->{
        	
        	button.getStyleClass().clear();
        	button.setFont(font2);
        	button.setText(" "+name+" ");
        	button.getStyleClass().add("her2-button-style");
        });
       
    }
    
    
    movement = DownSide.getDownMove();
    
    
    
    leftSideBox = new VBox (30,availableHeroesLabel,availableHeroesBox,heroesLabel, heroesBox, movement, sound, exit);
    
    availableHeroesLabel.setTranslateY(-50);
    availableHeroesBox.setTranslateY(-50);
    heroesLabel.setTranslateY(-50);
    heroesBox.setTranslateY(-50);
    /*movement.setTranslateY(120);
    movement.setTranslateX(40);
    heroesLabel.setTranslateX(20);
    heroesBox.setTranslateX(20);
    heroesLabel.setTranslateY(-100);
    heroesBox.setTranslateY(-100);
    */
    //leftSideBox = new VBox (30, availableHeroesLabel,availableHeroesBox, heroesLabel, heroesBox,exit,sound);
    leftSideBox.setSpacing(10);
    leftSideBox.setTranslateX(30);
    
    //leftSideBox = new VBox (30, availableHeroesLabel,availableHeroesBox, heroesLabel, heroesBox);
    //leftSideBox.setSpacing(20);
    //leftSideBox.getChildren().addAll(availableHeroesLabel,availableHeroesBox, heroesLabel, heroesBox );

	}

	public static void displayHeroData(Hero hero) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.getDialogPane().setHeaderText(null);
        alert.getDialogPane().setHeader(null);
		alert.getDialogPane().setPrefSize(350, 200);
		 alert.getDialogPane().setGraphic(null);
		alert.getDialogPane().setStyle("-fx-background-color: #A3866A;");
		
	    //alert.setHeaderText("Hero");
	   
	    String type="";
	    if (hero instanceof Fighter)
	    	type="Fighter: ";
	    else if (hero instanceof Medic)
	    	type="Medic: ";
	    else if (hero instanceof Explorer)
	    	type="Explorer: ";
	    else
	    	type="Hero: ";
	    String text = type   +hero.getName() + "\n\nCurrent HP:  " + hero.getCurrentHp()+ "\nAttack Damage: " + hero.getAttackDmg() + "\nActions Available: " + hero.getActionsAvailable() + "\nVaccines: " + hero.getVaccineInventory().size()+ "\nSupplies: "+ hero.getSupplyInventory().size() ;
	    
	    
	    alert.setContentText(text);
	    Label l = new Label(text);
	    l.setStyle("-fx-text-fill: white;");
	    //l.setFont(font3);
	  
	    
	 // Create an HBox to hold the image and content
        HBox hbox = new HBox();
        hbox.setSpacing(0);
        String path="";
        
        switch (hero.getName()) {
        case "Joel Miller": path="File:JoelInMapFinall3.png";
		break;
		case"Ellie Williams":
			path="File:EllieInMapFinal3.png";
		break;

		case"Tess":
			path="File:TessInMapFinall3.png";
		break;

		case"Riley Abel":
			path="File:female3.png";
		break;

		case"Tommy Miller":
			path="File:TommyInMapFinal3.png";
        break;
		
		case"Bill":path="File:Billl3.png";
			break;
			
		case"David":
			path="File:David3.png";
			break;
			
		case "Henry Burell":
			path="File:Henry3.png";
			break;
    
        
        }

        // Load and set the image
        Image image = new Image(path);
        
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(200);
        imageView.setFitHeight(170);
        
        
        

        hbox.getChildren().addAll(l, imageView);
        imageView.setTranslateX(40);
        alert.getDialogPane().setContent(hbox);
        
	    alert.initOwner(mains.primary1);
	   
        
	    alert.initOwner(mains.primary1);
	    
	    ButtonType changeHeroButton = new ButtonType("Change Hero");
	    ButtonType cancelButton = ButtonType.CANCEL;
	    
	    
	   
	    alert.getButtonTypes().setAll(cancelButton);
	    alert.getButtonTypes().add(changeHeroButton);
	    for (ButtonType buttonType : alert.getDialogPane().getButtonTypes()) {
            alert.getDialogPane().lookupButton(buttonType).setStyle("-fx-background-color: #333333; -fx-text-fill: white;");
        }
	    alert.setOnCloseRequest(event -> alert.close());
	   
	    
	    alert.showAndWait().ifPresent(response -> {
            if (response == changeHeroButton) {
                mains.setCurrentHero(hero);
                mains.mediaPlayerChange.seek(javafx.util.Duration.ZERO);
    			mains.mediaPlayerChange.play();
                Hero.msg="You changed your hero to "+ hero.getFirstName()+"\n";
                
                RightSide.startRightSide();
                addHeroToHeroArrayList(hero);
                Game.availableHeroes.remove(hero);
                mains.updateWindow();
      
            }
        });
	}
	
	public static void displayHeroData2(Hero hero) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.getDialogPane().setHeaderText(null);
        alert.getDialogPane().setHeader(null);
		alert.getDialogPane().setPrefSize(350, 200);
		 alert.getDialogPane().setGraphic(null);
		alert.getDialogPane().setStyle("-fx-background-color: #A3866A;");
		
	    alert.setHeaderText(null);
	    String type="";
	    if (hero instanceof Fighter)
	    	type="Fighter: ";
	    else if (hero instanceof Medic)
	    	type="Medic: ";
	    else if (hero instanceof Explorer)
	    	type="Explorer: ";
	    else
	    	type="Hero: ";
	    String text = type  + hero.getName() + "\nCurrent HP:  " + hero.getCurrentHp()+ "\nAttack Damage: " + hero.getAttackDmg() + "\nActions Available: " + hero.getActionsAvailable() + "\nVaccines: " + hero.getVaccineInventory().size()+ "\nSupplies: "+ hero.getSupplyInventory().size() ;
	    
	    
	    alert.setContentText(text);
	    Label l = new Label(text);
	    l.setStyle("-fx-text-fill: white;");
	    //l.setFont(font3);
	  
	    
	 // Create an HBox to hold the image and content
        HBox hbox = new HBox();
        hbox.setSpacing(0);
        String path="";
        
        switch (hero.getName()) {
        case "Joel Miller": path="File:JoelInMapFinall3.png";
			break;
			case"Ellie Williams":
				path="File:EllieInMapFinal3.png";
			break;

			case"Tess":
				path="File:TessInMapFinall3.png";
			break;

			case"Riley Abel":
				path="File:female3.png";
			break;

			case"Tommy Miller":
				path="File:TommyInMapFinal3.png";
            break;
			
			case"Bill":path="File:Billl3.png";
				break;
				
			case"David":
				path="File:David3.png";
				break;
				
			case "Henry Burell":
				path="File:Henry3.png";
				break;
        
        }

        // Load and set the image
        Image image = new Image(path);
        
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(200);
        imageView.setFitHeight(170);
        
        
        

        hbox.getChildren().addAll(l, imageView);
        imageView.setTranslateX(40);
        alert.getDialogPane().setContent(hbox);
	    
	    alert.setContentText(text);
	    alert.initOwner(mains.primary1);
	    
	    
	    
	    
	    
	    ButtonType cancelButton = ButtonType.CANCEL;
		   
	    alert.getButtonTypes().setAll(cancelButton);
	    for (ButtonType buttonType : alert.getDialogPane().getButtonTypes()) {
            alert.getDialogPane().lookupButton(buttonType).setStyle("-fx-background-color: #333333; -fx-text-fill: white;");
        }
	   // alert.getButtonTypes().add(changeHeroButton);
	    alert.setOnCloseRequest(event -> alert.close());
	    
	    
	    alert.showAndWait();
	}
	
	public static void addHeroToHeroArrayList(Hero h) {
		if (!Game.heroes.contains(h)) {
		Game.heroes.add(h);
		heroesBox = new VBox(availableHeroesLabel);
	    heroesBox.setAlignment(Pos.TOP_CENTER);
	    for (int i = 0; i < Game.heroes.size(); i++) {
	    	String name= Game.heroes.get(i).getName();
	    	Hero hero = Game.heroes.get(i);
	        Button button = new Button(name);
	        button.setOnMouseClicked(event -> displayHeroData(hero));
	        heroesBox.getChildren().add(button);
	       
	    }
	    leftSideBox = new VBox (30,availableHeroesLabel,availableHeroesBox,heroesLabel, heroesBox,  movement, sound, exit);
	   
	   

	}}
	
public static void music() {
		
		if(!s) {
			   mains.mediaPlayer.setVolume(0);
			  RightSide.mediaPlayerAttack.setVolume(0);
			  RightSide.mediaPlayerCure.setVolume(0);
			  RightSide.mediaPlayerUse.setVolume(0);
			  DownSide.mediaPlayerDeath.setVolume(0);
			  DownSide.mediaPlayerDeath.setVolume(0);
			  DownSide.mediaPlayerMove.setVolume(0);
			  DownSide.mediaPlayerMoveC.setVolume(0);
			  DownSide.mediaPlayerMoveVS.setVolume(0);
			  DownSide.mediaPlayerTrap.setVolume(0);
			  volumeZero.setFitHeight(30);
			    volumeZero.setFitWidth(30);
			    mains.mediaPlayerChange.setVolume(0);
			    
		   } if(s){
			   
			   mains.mediaPlayer.setVolume(0.3);
				  RightSide.mediaPlayerAttack.setVolume(1);
				  RightSide.mediaPlayerCure.setVolume(1);
				  RightSide.mediaPlayerUse.setVolume(1);
				  DownSide.mediaPlayerDeath.setVolume(1);
				  DownSide.mediaPlayerDeath.setVolume(1);
				  DownSide.mediaPlayerMove.setVolume(1);
				  DownSide.mediaPlayerMoveC.setVolume(0.5);
				  DownSide.mediaPlayerMoveVS.setVolume(1);
				  DownSide.mediaPlayerTrap.setVolume(1);
				  
			  sound.setGraphic(volume);
			  volume.setFitHeight(30);
			    volume.setFitWidth(30);
			    
		   }
	}

	
	
	
}
