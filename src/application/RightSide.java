package application;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.characters.Explorer;
import model.characters.Fighter;
import model.characters.Hero;
import model.characters.Medic;
//import javafx.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
//import javafx.scene.control.Popup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class RightSide {

	// Right side - Hero Data
    public static Label selectedHeroLabel = new Label("Selected Hero: ");
    public static TextArea heroDataTextArea = new TextArea();
    public static VBox heroDataBox;
    //public static Hero currentHero;
    public static VBox actionsBox;
    public static VBox allData;
    
    
    public static Label updatesActions;
    public static VBox updateArea;
    public static TextArea textForUpdates;
    
    
    
    public static String pathAttack = "attackeffectfinal.mp3";
    public static Media mediaAttack = new Media(new File(pathAttack).toURI().toString());
    public static MediaPlayer mediaPlayerAttack = new MediaPlayer(mediaAttack);
    
    public static String pathCure = "CureHero.mp3";
    public static Media mediaCure = new Media(new File(pathCure).toURI().toString());
    public static MediaPlayer mediaPlayerCure = new MediaPlayer(mediaCure);
    
    public static String pathUse = "UseSpecial.mp3";
    public static Media mediaUse = new Media(new File(pathUse).toURI().toString());
    public static MediaPlayer mediaPlayerUse = new MediaPlayer(mediaUse);
    
    public static Font font = Font.loadFont("file:PressStart2P-Regular.ttf", 16);
    public static Font font3 = Font.loadFont("file:PressStart2P-Regular.ttf", 13);
	public static Font font2 = Font.font("Courier New", FontWeight.BOLD, 14);
	public static Font font4 = Font.font("Courier New", FontWeight.BOLD, 11);
	
	public static int counterMedicSpecial;
	
    
    public static void startRightSide() {
    	createRightSideUp();
    	createRightSideDown();
    	
    	//create the VBOX
    	allData= new VBox (20, heroDataBox, actionsBox, updateArea);
    }
    
    public static void createRightSideUp() {
    	//find data of current Hero
    	String type="";
 	    if (mains.currentHero instanceof Fighter)
 	    	type="Fighter: ";
 	    else if (mains.currentHero instanceof Medic)
 	    	type="Medic: ";
 	    else if (mains.currentHero instanceof Explorer)
 	    	type="Explorer: ";
 	    else
 	    	type="Hero: ";
 	   
 	   String text = type +"\n" 
 			   +mains.currentHero.getName() + "\n\nCurrent HP:  " + mains.currentHero.getCurrentHp()+ "\nAttack Damage: " + mains.currentHero.getAttackDmg() + "\nActions Available: " + mains.currentHero.getActionsAvailable() + "\nVaccines: " + mains.currentHero.getVaccineInventory().size()+ "\nSupplies: "+ mains.currentHero.getSupplyInventory().size() ;
 	  
 	  if (mains.currentHero.getCurrentHp()<=0)
		   text="Your hero is dead, please"+"\nselect another hero";
 	   
 	   //add data to textArea
 	   heroDataTextArea= new TextArea(text);
 	   heroDataTextArea.setFont(font2);
 	   heroDataTextArea.setPrefSize(170, 200);
 	   heroDataTextArea.setTranslateX(-10);
 	   heroDataTextArea.setEditable(false);
 	   
 	   //add to VBOX
 	   selectedHeroLabel= new Label ("Selected \nHero: ");
 	   selectedHeroLabel.setFont(font);
 	   selectedHeroLabel.setStyle("-fx-text-fill: white;");
 	   heroDataBox = new VBox (10, selectedHeroLabel, heroDataTextArea);
 	   heroDataBox.setAlignment(Pos.CENTER);
 	   heroDataBox.setTranslateX(-15);
 	   
    }
    
    public static void createRightSideDown() {
    	//add actions buttons
    	 Button attackButton = new Button("Attack");
    	 attackButton.setFont(font3);
    	 attackButton.getStyleClass().add("button-style");
    	// attackButton.setPrefHeight(40);
    	// attackButton.setStyle("-fx-background-color: #727764");
    	 //attackButton.setStyle("-fx-background-radius: 25;");
    	
    	 
         Button cureButton = new Button("Cure");
         cureButton.setFont(font3);
         //cureButton.setPrefHeight(40);
         cureButton.getStyleClass().add("button-style");
         //cureButton.setStyle("-fx-background-radius: 25;");
         
         Button endTurnButton = new Button("End Turn");
         endTurnButton.setFont(font3);
         endTurnButton.getStyleClass().add("button-style");
         //endTurnButton.setPrefHeight(40);
        // endTurnButton.setStyle("-fx-background-radius: 25;");
         
         Button useSpecialButton = new Button("Use Special");
         useSpecialButton.setFont(font3);
         useSpecialButton.getStyleClass().add("button-style");
         //useSpecialButton.setPrefHeight(40);
         //useSpecialButton.setStyle("-fx-background-radius: 25;");
         
         actionsBox= new VBox(10, attackButton, cureButton, endTurnButton, useSpecialButton);
         actionsBox.setAlignment(Pos.CENTER);
         actionsBox.setTranslateX(-15);
         attackButton.setOnMouseEntered(event->{
         	
        	 attackButton.setStyle("-fx-background-color: #75BFB5; -fx-text-fill: white;");
        	 attackButton.setFont(font3);
        	 
          });
          
          attackButton.setOnMouseExited(event->{
          	
          	//attackButton.getStyleClass().clear();
          	
          	attackButton.setText(attackButton.getText());
          	attackButton.setFont(font3);
          	 attackButton.setStyle(" -fx-min-height: 40px; -fx-background-color: #62798b; -fx-text-fill: white; -fx-background-radius: 20;  ");
          	
          	attackButton.getStyleClass().add("button-style");
          });
          
          
          
          cureButton.setOnMouseEntered(event->{
           	
        	  cureButton.setStyle("-fx-background-color: #75BFB5; -fx-text-fill: white;");
        	  cureButton.setFont(font3);
         	 
           });
           
          cureButton.setOnMouseExited(event->{
           	
           	//attackButton.getStyleClass().clear();
           	
        	  cureButton.setText(cureButton.getText());
        	  cureButton.setFont(font3);
        	  cureButton.setStyle(" -fx-min-height: 40px; -fx-background-color: #62798b; -fx-text-fill: white; -fx-background-radius: 20;  ");
           	
        	  cureButton.getStyleClass().add("button-style");
           });
          
          endTurnButton.setOnMouseEntered(event->{
             	
        	  endTurnButton.setStyle("-fx-background-color: #75BFB5; -fx-text-fill: white;");
        	  endTurnButton.setFont(font3);
         	 
           });
           
          endTurnButton.setOnMouseExited(event->{
           	
           	//attackButton.getStyleClass().clear();
           	
        	  endTurnButton.setText(endTurnButton.getText());
        	  endTurnButton.setFont(font3);
        	  endTurnButton.setStyle(" -fx-min-height: 40px; -fx-background-color: #62798b; -fx-text-fill: white; -fx-background-radius: 20;  ");
           	
        	  endTurnButton.getStyleClass().add("button-style");
           });
          
          useSpecialButton.setOnMouseEntered(event->{
           	
        	  useSpecialButton.setStyle("-fx-background-color: #75BFB5; -fx-text-fill: white;");
        	  useSpecialButton.setFont(font3);
         	 
           });
           
          useSpecialButton.setOnMouseExited(event->{
           	
           	//attackButton.getStyleClass().clear();
           	
        	  useSpecialButton.setText(useSpecialButton.getText());
        	  useSpecialButton.setFont(font3);
        	  useSpecialButton.setStyle(" -fx-min-height: 40px; -fx-background-color: #62798b; -fx-text-fill: white; -fx-background-radius: 20;  ");
           	
        	  useSpecialButton.getStyleClass().add("button-style");
           });
         
          
         //set their actions
         attackButton.setOnMouseClicked(e -> showMessageAttack("Attack action selected! Please select a zombie to attack from the map"));
         cureButton.setOnMouseClicked(e -> showMessageCure("Cure action selected! Please select a zombie to cure from the map"));
         endTurnButton.setOnMouseClicked(e -> showMessageEndTurn("Are you sure you want to end turn?"));
         useSpecialButton.setOnMouseClicked(e -> showMessageUse("Use Special action selected!"));
         
        
         
         
         
         //buttonRight-style
         updatesActions= new Label ("\n \nUpdates in \nthe game:");
         updatesActions.setStyle("-fx-text-fill: white;");
         updatesActions.setFont(font);
         updatesActions.setAlignment(Pos.CENTER);
   
  	     textForUpdates = new TextArea(Hero.msg);
  	     textForUpdates.setFont(font4);
  	     heroDataTextArea.setEditable(false);
  	  
         updateArea= new VBox (10,updatesActions, textForUpdates);
        
         heroDataTextArea.setPrefSize(170, 200);
         updateArea.setAlignment(Pos.CENTER);
         updateArea.setTranslateX(-20);
         //updateArea.setTranslateX(-15);
        
         
    }
    
    /*public static void updateTextArea() {
    	textForUpdates.setText(Hero.getMsg());
    	updateArea= new VBox (10,updatesActions, textForUpdates);
    	mains.updateWindow();
    }*/
    
    public static void showMessageEndTurn(String message) {
   
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
    	
        
        alert.setTitle("End Turn");
        alert.setContentText(message);
       // ButtonType cancelButton = new ButtonType("Cancel");
        
        alert.getDialogPane().setHeaderText(null);
	        alert.getDialogPane().setHeader(null);
			
			alert.getDialogPane().setGraphic(null);
			alert.getDialogPane().setStyle("-fx-background-color: #A3866A;");
			
			
			Label contentLabel1 = (Label) alert.getDialogPane().lookup(".content.label");
		    contentLabel1.setFont(LeftSide.font);
         
         
       // ButtonType cancelButton = new ButtonType("Cancel");
        ButtonType cancelButton = ButtonType.CANCEL;
        ButtonType endTurnOkay = new ButtonType("End Turn");
        alert.getButtonTypes().setAll(cancelButton);
	    alert.getButtonTypes().add(endTurnOkay);
        for (ButtonType buttonType : alert.getDialogPane().getButtonTypes()) {
            alert.getDialogPane().lookupButton(buttonType).setStyle("-fx-background-color: #333333; -fx-text-fill: white;");
        }
       
        
       
       
       
	    alert.initOwner(mains.primary1);
	    
		alert.setOnCloseRequest(event-> {
			alert.close();
		});
       
	    
	    alert.showAndWait().ifPresent(response -> {
            if (response == endTurnOkay) {
        try {
			Game.endTurn();
			
			//mains.checkGameWinView ();
 			mains.checkGameOverView();
 			
 			mains.updateWindow();
 			CenterSide.gridPane.setStyle("-fx-border-color: #FFD700; -fx-border-width: 5px;");
		} catch (NotEnoughActionsException e) {
			
			Alert alert1 = new Alert(AlertType.WARNING);
            alert1.setTitle("Not Enough Resources");
            alert1.setHeaderText(null);
            alert1.setContentText(e.getMessage());

           
            DownSide.mediaPlayerMove.seek(javafx.util.Duration.ZERO);
			DownSide.mediaPlayerMove.play();
			

			alert1.getDialogPane().setHeaderText(null);
	        alert1.getDialogPane().setHeader(null);
			
			alert1.getDialogPane().setGraphic(null);
			alert1.getDialogPane().setStyle("-fx-background-color: #A3866A;");
			
			
			Label contentLabel = (Label) alert1.getDialogPane().lookup(".content.label");
		    contentLabel.setFont(LeftSide.font);
            
            
            
            
            
            
            ButtonType closeButton = ButtonType.CANCEL;
            alert1.getButtonTypes().setAll(closeButton);
            
            for (ButtonType buttonType : alert1.getDialogPane().getButtonTypes()) {
                alert1.getDialogPane().lookupButton(buttonType).setStyle("-fx-background-color: #333333; -fx-text-fill: white;");
            }
            alert1.initOwner(mains.primary1);

            alert1.showAndWait();
		} catch (InvalidTargetException e) {
			System.out.println("problem1");
			Alert alert1 = new Alert(AlertType.WARNING);
            alert1.setTitle("Invalid Target");
            alert1.setHeaderText(null);
            alert1.setContentText(e.getMessage());

            DownSide.mediaPlayerMove.seek(javafx.util.Duration.ZERO);
			DownSide.mediaPlayerMove.play();
			
            alert.getDialogPane().setHeaderText(null);
	        alert.getDialogPane().setHeader(null);
			
			alert.getDialogPane().setGraphic(null);
			alert.getDialogPane().setStyle("-fx-background-color: #A3866A;");
			
			
			Label contentLabel = (Label) alert.getDialogPane().lookup(".content.label");
		    contentLabel.setFont(LeftSide.font);
            
            ButtonType closeButton = ButtonType.CANCEL;
            alert1.getButtonTypes().setAll(closeButton);
            for (ButtonType buttonType : alert1.getDialogPane().getButtonTypes()) {
                alert1.getDialogPane().lookupButton(buttonType).setStyle("-fx-background-color: #333333; -fx-text-fill: white;");
            }
            alert1.initOwner(mains.primary1);

            alert1.showAndWait();
		}
        mains.updateWindow();
       
		
	}
            
        });
    }
        

    public static void showMessageCure(String message) {
    	
    	
    	 Alert alert = new Alert(Alert.AlertType.INFORMATION);
     	
         alert.setTitle("Cure");
         alert.setContentText(message);
        // ButtonType cancelButton = new ButtonType("Cancel");
         
         alert.getDialogPane().setHeaderText(null);
	        alert.getDialogPane().setHeader(null);
			
			alert.getDialogPane().setGraphic(null);
			alert.getDialogPane().setStyle("-fx-background-color: #A3866A;");
			
			
			Label contentLabel1 = (Label) alert.getDialogPane().lookup(".content.label");
		    contentLabel1.setFont(LeftSide.font);
          
          
         ButtonType cancelButton = ButtonType.CANCEL;
         ButtonType cureHeroButton = new ButtonType("Cure zombie");
        
         alert.getButtonTypes().setAll(cancelButton);
 	    alert.getButtonTypes().add(cureHeroButton);
 	   for (ButtonType buttonType : alert.getDialogPane().getButtonTypes()) {
           alert.getDialogPane().lookupButton(buttonType).setStyle("-fx-background-color: #333333; -fx-text-fill: white;");
       }
 	   alert.initOwner(mains.primary1);
         
        
 	    
 	    alert.showAndWait().ifPresent(response -> {
             if (response == cureHeroButton) {
         try {
 			mains.currentHero.cure();
 			if (Game.checkWin()==false) {
 			mediaPlayerCure.seek(javafx.util.Duration.ZERO);
			mediaPlayerCure.play();}
 			//mains.checkGameWinView ();
 			mains.checkGameOverView();
 		} catch (NotEnoughActionsException e) {
 			
 			Alert alert1 = new Alert(AlertType.WARNING);
             alert1.setTitle("Not Enough Resources");
             alert1.setHeaderText(null);
             alert1.setContentText(e.getMessage());

             alert1.getDialogPane().setHeaderText(null);
 	        alert1.getDialogPane().setHeader(null);
 			
 	       DownSide.mediaPlayerMove.seek(javafx.util.Duration.ZERO);
			DownSide.mediaPlayerMove.play();
			
 			alert1.getDialogPane().setGraphic(null);
 			alert1.getDialogPane().setStyle("-fx-background-color: #A3866A;");
 			
 			
 			Label contentLabel = (Label) alert1.getDialogPane().lookup(".content.label");
 		    contentLabel.setFont(LeftSide.font);
             
             
             ButtonType closeButton = ButtonType.CANCEL;
             alert1.getButtonTypes().setAll(closeButton);
             for (ButtonType buttonType : alert1.getDialogPane().getButtonTypes()) {
	                alert1.getDialogPane().lookupButton(buttonType).setStyle("-fx-background-color: #333333; -fx-text-fill: white;");
	            }
             alert1.initOwner(mains.primary1);

             alert1.showAndWait();
 		} catch (InvalidTargetException e) {
 			System.out.println("problem1");
 			Alert alert1 = new Alert(AlertType.WARNING);
             alert1.setTitle("Invalid Target");
             alert1.setHeaderText(null);
             alert1.setContentText(e.getMessage());

             alert1.getDialogPane().setHeaderText(null);
 	        alert1.getDialogPane().setHeader(null);
 			
 			alert1.getDialogPane().setGraphic(null);
 			alert1.getDialogPane().setStyle("-fx-background-color: #A3866A;");
 			
 			 DownSide.mediaPlayerMove.seek(javafx.util.Duration.ZERO);
 			DownSide.mediaPlayerMove.play();
 			
 			Label contentLabel = (Label) alert1.getDialogPane().lookup(".content.label");
 		    contentLabel.setFont(LeftSide.font);
             
             
             
             ButtonType closeButton = ButtonType.CANCEL;
             alert1.getButtonTypes().setAll(closeButton);
             for (ButtonType buttonType : alert1.getDialogPane().getButtonTypes()) {
	                alert1.getDialogPane().lookupButton(buttonType).setStyle("-fx-background-color: #333333; -fx-text-fill: white;");
	            }
             alert1.initOwner(mains.primary1);

             alert1.showAndWait();
 		}
         catch (NoAvailableResourcesException e) {
  			System.out.println("problem1");
  			Alert alert1 = new Alert(AlertType.WARNING);
              alert1.setTitle("No available resources");
              alert1.setHeaderText(null);
              alert1.setContentText(e.getMessage());

              alert1.getDialogPane().setHeaderText(null);
  	        alert1.getDialogPane().setHeader(null);
  	      DownSide.mediaPlayerMove.seek(javafx.util.Duration.ZERO);
			DownSide.mediaPlayerMove.play();
  			alert1.getDialogPane().setGraphic(null);
  			alert1.getDialogPane().setStyle("-fx-background-color: #A3866A;");
  			
  			
  			Label contentLabel = (Label) alert1.getDialogPane().lookup(".content.label");
  		    contentLabel.setFont(LeftSide.font);
              
              
              
              ButtonType closeButton = ButtonType.CANCEL;
              alert1.getButtonTypes().setAll(closeButton);
              
              for (ButtonType buttonType : alert1.getDialogPane().getButtonTypes()) {
	                alert1.getDialogPane().lookupButton(buttonType).setStyle("-fx-background-color: #333333; -fx-text-fill: white;");
	            }
              alert1.initOwner(mains.primary1);

              alert1.showAndWait();
  		}
          
         mains.updateWindow();
        
 		
 	}
             
         });
     }
    	
    	
    	
    
        
        
       
    
    public static void showMessageUse(String message) {
    	Alert alert = new Alert(Alert.AlertType.INFORMATION);
    	
        
        
        
        alert.setTitle("Use Special");
        alert.setContentText(message);
       // ButtonType cancelButton = new ButtonType("Cancel");
        
        alert.getDialogPane().setHeaderText(null);
	        alert.getDialogPane().setHeader(null);
			
			alert.getDialogPane().setGraphic(null);
			alert.getDialogPane().setStyle("-fx-background-color: #A3866A;");
			
			
			Label contentLabel1 = (Label) alert.getDialogPane().lookup(".content.label");
		    contentLabel1.setFont(LeftSide.font);
         
         
       // ButtonType cancelButton = new ButtonType("Cancel");
        ButtonType cancelButton = ButtonType.CANCEL;
        ButtonType endTurnOkay = new ButtonType("Use Special");
        alert.getButtonTypes().setAll(cancelButton);
	    alert.getButtonTypes().add(endTurnOkay);
        for (ButtonType buttonType : alert.getDialogPane().getButtonTypes()) {
            alert.getDialogPane().lookupButton(buttonType).setStyle("-fx-background-color: #333333; -fx-text-fill: white;");
        }
       
        
        
	    alert.initOwner(mains.primary1);
	    
	    alert.showAndWait().ifPresent(response -> {
            if (response == endTurnOkay) {
             try {
				mains.currentHero.useSpecial();
				mediaPlayerUse.seek(javafx.util.Duration.ZERO);
				mediaPlayerUse.play();
				counterMedicSpecial=1;
				
				//mains.checkGameWinView ();
	 			mains.checkGameOverView();
	 			mains.updateWindow();
	 			CenterSide.gridPane.setStyle("-fx-border-color: #FF0000; -fx-border-width: 5px;");
			} catch (NoAvailableResourcesException e) {
				Alert alert1 = new Alert(AlertType.WARNING);
	            alert1.setTitle("Not Enough Resources");
	            alert1.setHeaderText(null);
	            alert1.setContentText(e.getMessage());

	            alert1.getDialogPane().setHeaderText(null);
		        alert1.getDialogPane().setHeader(null);
		        DownSide.mediaPlayerMove.seek(javafx.util.Duration.ZERO);
				DownSide.mediaPlayerMove.play();
				alert1.getDialogPane().setGraphic(null);
				alert1.getDialogPane().setStyle("-fx-background-color: #A3866A;");
				
				
				Label contentLabel = (Label) alert1.getDialogPane().lookup(".content.label");
			    contentLabel.setFont(LeftSide.font);
	            
	            
	            
	            ButtonType closeButton = ButtonType.CANCEL;
	            alert1.getButtonTypes().setAll(closeButton);
	            
	            for (ButtonType buttonType : alert1.getDialogPane().getButtonTypes()) {
	                alert1.getDialogPane().lookupButton(buttonType).setStyle("-fx-background-color: #333333; -fx-text-fill: white;");
	            }
	            alert1.initOwner(mains.primary1);

	            alert1.showAndWait();
			} catch (InvalidTargetException e) {
				Alert alert1 = new Alert(AlertType.WARNING);
	            alert1.setTitle("Not Enough Resources");
	            alert1.setHeaderText(null);
	            alert1.setContentText(e.getMessage());

	            alert1.getDialogPane().setHeaderText(null);
		        alert1.getDialogPane().setHeader(null);
		        DownSide.mediaPlayerMove.seek(javafx.util.Duration.ZERO);
				DownSide.mediaPlayerMove.play();
				alert1.getDialogPane().setGraphic(null);
				alert1.getDialogPane().setStyle("-fx-background-color: #A3866A;");
				
				
				Label contentLabel = (Label) alert1.getDialogPane().lookup(".content.label");
			    contentLabel.setFont(LeftSide.font);
	            
	            
	            ButtonType closeButton = ButtonType.CANCEL;
	            alert1.getButtonTypes().setAll(closeButton);
	            
	            for (ButtonType buttonType : alert1.getDialogPane().getButtonTypes()) {
	                alert1.getDialogPane().lookupButton(buttonType).setStyle("-fx-background-color: #333333; -fx-text-fill: white;");
	            }
	            alert1.initOwner(mains.primary1);

	            alert1.showAndWait();
			}
             //mains.updateWindow();
       
		
	}
            
        });
    
    
    }
    
    
    
	public static void showMessageAttack(String message) {

		 Alert alert = new Alert(Alert.AlertType.INFORMATION);
	    	
		 
		 
	        alert.setTitle("Attack");
	        alert.setContentText(message);
	       // ButtonType cancelButton = new ButtonType("Cancel");
	        
	        alert.getDialogPane().setHeaderText(null);
		        alert.getDialogPane().setHeader(null);
				
				alert.getDialogPane().setGraphic(null);
				alert.getDialogPane().setStyle("-fx-background-color: #A3866A;");
				
				
				Label contentLabel1 = (Label) alert.getDialogPane().lookup(".content.label");
			    contentLabel1.setFont(LeftSide.font);
	         
	         
	       // ButtonType cancelButton = new ButtonType("Cancel");
	        ButtonType cancelButton = ButtonType.CANCEL;
	        ButtonType endTurnOkay = new ButtonType("Attack");
	        alert.getButtonTypes().setAll(cancelButton);
		    alert.getButtonTypes().add(endTurnOkay);
	        for (ButtonType buttonType : alert.getDialogPane().getButtonTypes()) {
	            alert.getDialogPane().lookupButton(buttonType).setStyle("-fx-background-color: #333333; -fx-text-fill: white;");
	        }
	       
	        
		 
	        
		    alert.initOwner(mains.primary1);
		    
		    alert.showAndWait().ifPresent(response -> {
	            if (response == endTurnOkay) {
	        try {
				mains.currentHero.attack();
				mediaPlayerAttack.seek(javafx.util.Duration.ZERO);
				mediaPlayerAttack.play();
		       // mediaPlayerAttack.play();
				//mediaPlayerAttack.setAutoPlay(true);
				
				
				//System.out.println("hello");
				//mains.checkGameWinView ();
	 			mains.checkGameOverView();
			} catch (NotEnoughActionsException e) {
				
				Alert alert1 = new Alert(AlertType.WARNING);
	            alert1.setTitle("Not Enough Resources");
	            alert1.setHeaderText(null);
	            alert1.setContentText(e.getMessage());

	            
	            alert1.getDialogPane().setHeaderText(null);
		        alert1.getDialogPane().setHeader(null);
		        DownSide.mediaPlayerMove.seek(javafx.util.Duration.ZERO);
				DownSide.mediaPlayerMove.play();
				alert1.getDialogPane().setGraphic(null);
				alert1.getDialogPane().setStyle("-fx-background-color: #A3866A;");
				
				
				Label contentLabel = (Label) alert1.getDialogPane().lookup(".content.label");
			    contentLabel.setFont(LeftSide.font);
	            
	            ButtonType closeButton = ButtonType.CANCEL;
	            alert1.getButtonTypes().setAll(closeButton);
	            
	            for (ButtonType buttonType : alert1.getDialogPane().getButtonTypes()) {
	                alert1.getDialogPane().lookupButton(buttonType).setStyle("-fx-background-color: #333333; -fx-text-fill: white;");
	            }
	            alert1.initOwner(mains.primary1);

	            alert1.showAndWait();
			} catch (InvalidTargetException e) {
				
				Alert alert1 = new Alert(AlertType.WARNING);
	            alert1.setTitle("Invalid Target");
	            alert1.setHeaderText(null);
	            alert1.setContentText(e.getMessage()+"\nClick on a cell to select your target");

	            alert1.getDialogPane().setHeaderText(null);
		        alert1.getDialogPane().setHeader(null);
		        DownSide.mediaPlayerMove.seek(javafx.util.Duration.ZERO);
				DownSide.mediaPlayerMove.play();
				alert1.getDialogPane().setGraphic(null);
				alert1.getDialogPane().setStyle("-fx-background-color: #A3866A;");
				
				
				Label contentLabel = (Label) alert1.getDialogPane().lookup(".content.label");
			    contentLabel.setFont(LeftSide.font);
	            
	            
	            
	            ButtonType closeButton = ButtonType.CANCEL;
	            alert1.getButtonTypes().setAll(closeButton);
	           
	            for (ButtonType buttonType : alert1.getDialogPane().getButtonTypes()) {
	                alert1.getDialogPane().lookupButton(buttonType).setStyle("-fx-background-color: #333333; -fx-text-fill: white;");
	            }
	            
	            alert1.initOwner(mains.primary1);

	            alert1.showAndWait();
			}
	        mains.updateWindow();
	       
			
		}
	            
	        });
        
        
        
    }
	
    
    
	
	
}
