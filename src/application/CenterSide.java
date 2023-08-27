package application;

import engine.Game;


import model.world.*;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.characters.Character;
import model.characters.Explorer;
import model.characters.Fighter;
import model.characters.Hero;
import model.characters.Medic;
import model.characters.Zombie;
import model.collectibles.Supply;
import model.collectibles.Vaccine;
import model.world.CharacterCell;
import model.world.CollectibleCell;
import model.world.TrapCell;
//import javafx.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
//import javafx.scene.control.Popup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;

public class CenterSide {

	public static GridPane gridPane;
	public static HBox gridInABox;
	public static Button [][] gridButtons = new Button [15][15];
	public static Character target;
	public static boolean targetSelected;
	public static Button targetButton;
	public static int xtarget=20;
	public static int ytarget=20;
	public static int count=0;
	public static int xHero;
	public static int yHero;
	public static BackgroundImage SupplyPic = new BackgroundImage(new Image("File:chest_MapFinal.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
			BackgroundPosition.CENTER,
			new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true));
    public static BackgroundImage VaccinePic = new BackgroundImage(new Image("File:VaccineInMap.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
			BackgroundPosition.CENTER,
			new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true));
    public static BackgroundImage ZombiePic = new BackgroundImage(new Image("File:ZombieInMapF.jpeg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
			BackgroundPosition.CENTER,
			new BackgroundSize(60, 50, false, false, true, true));
    public static BackgroundImage joel = new BackgroundImage(new Image("file:JoelInMapFinall.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
    		BackgroundPosition.CENTER,
			new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true));
	public static Background miller=new Background(joel);
	
	public static BackgroundImage e = new BackgroundImage(new Image("file:EllieInMapFinal.jpeg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
			BackgroundPosition.CENTER,
			new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true));
	public static Background ellie=new Background(e);
	
	public static BackgroundImage b = new BackgroundImage(new Image("file:Billl.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
			BackgroundPosition.CENTER,
			new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true));
	public static Background bill=new Background(b);
	
	public static BackgroundImage t = new BackgroundImage(new Image("file:TommyinMapFinal.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
			BackgroundPosition.CENTER,
			new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true));
	public static Background tommy=new Background(t);

	public static BackgroundImage d = new BackgroundImage(new Image("file:David.jpeg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
			BackgroundPosition.CENTER,
			new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true));
	public static Background david=new Background(d);
	
	public static BackgroundImage h = new BackgroundImage(new Image("file:Henry.jpeg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
			BackgroundPosition.CENTER,
			new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true));
	public static Background henry=new Background(h);
	
	public static BackgroundImage ts = new BackgroundImage(new Image("file:TessInMapFinall.jpeg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
			BackgroundPosition.CENTER,
			new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true));
	public static Background tess=new Background(ts);
	
	public static BackgroundImage r = new BackgroundImage(new Image("file:female.jpeg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
			BackgroundPosition.CENTER,
			new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true));
	public static Background riley=new Background(r);
	
	
	
	public static void createCenterSide () {
		//gridBox = createGridPane();
		gridInABox=createGridPane();
		gridInABox.setPrefHeight(750);
		
		
	}
	public static void setTargetForActions(Character c) {
		mains.currentHero.setTarget(c);
		target=c;
	}
	
	//return GridPane
	public static HBox createGridPane() {
		gridPane = new GridPane();
		gridPane.setHgap(0); // Horizontal gap between buttons
        gridPane.setVgap(0);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(0);
        gridPane.setVgap(0);
        xHero=(int)mains.currentHero.getLocation().getX();
        yHero=(int)mains.currentHero.getLocation().getY();

        for (int row = 14; row >= 0; row--) {
            for (int col = 0; col < 15; col++) {
            	
                Button button = new Button();
                button.getStyleClass().clear();
                //button.setStyle("-fx-background-radius: 0; -fx-border-radius: 0;");
                button.setId(row+","+col);
                
                //button.setOn
                button.setPrefSize(60, 50);
     
                if (Game.map[row][col] instanceof CharacterCell && ((CharacterCell) Game.map[row][col]).getCharacter()!=null && ((CharacterCell) Game.map[row][col]).getCharacter() instanceof Hero) {
                	
                	String heroName=((CharacterCell)Game.map[row][col]).getCharacter().getName();
                	
                	switch(heroName) {
                	
                	case "Joel Miller":button.setBackground(miller);break;
                	
                	case"Ellie Williams":button.setBackground(ellie);break;
                	
                	case"Bill":button.setBackground(bill);break;
                	
                	case"Tommy Miller":button.setBackground(tommy);break;
                	
                	case "Henry Burell":button.setBackground(henry);break;
                		
                	case"David":button.setBackground(david);break;
                	
                	case "Tess":button.setBackground(tess);break;
                		
                	case"Riley Abel":button.setBackground(riley);break;
                		
                	}
                	
                	// button.setStyle("-fx-background-color: #92d500;");
                ((CharacterCell) Game.map[row][col]).getCharacter().setLocation(new Point(row, col));
               // button.setText(((CharacterCell) Game.map[row][col]).getCharacter().getFirstName());
                }
                if (Game.map[row][col] instanceof CharacterCell && ((CharacterCell) Game.map[row][col]).getCharacter()!=null && ((CharacterCell) Game.map[row][col]).getCharacter() instanceof Zombie) {
                  // button.setStyle(generateRnd());
                
                	((CharacterCell) Game.map[row][col]).getCharacter().setLocation(new Point(row, col));
               // button.setText("Z");
                Background background = new Background(ZombiePic);
               // background.se
                //button.setStyle(generateRnd());
                	
                button.setBackground(background);
                button.setText("");
                
                
                }
                if (Game.map[row][col] instanceof CharacterCell && ((CharacterCell) Game.map[row][col]).getCharacter()==null )
                    //button.setStyle("-fx-background-color: #transparent;");
                	 button.setStyle(generateRnd());
                if (Game.map[row][col] instanceof CollectibleCell && ((CollectibleCell) Game.map[row][col]).getCollectible() instanceof Vaccine ) {
                    //button.setStyle("-fx-background-color: #6bcdb3;");
                	button.setBackground(new Background(VaccinePic));
                    Background background = new Background(VaccinePic);
                   // button.setStyle("-fx-background-color: transparent; -fx-background-image: url('File:grass.png');");
                    button.setBackground(background);
                    button.setText("");
                	
                    //button.setText("V");
                    
                }
                if (Game.map[row][col] instanceof CollectibleCell && ((CollectibleCell) Game.map[row][col]).getCollectible() instanceof Supply ) {
                    //button.setStyle("-fx-background-color: #773dbc;");
                    
                   
                    Background background = new Background(SupplyPic);
                    button.setBackground(background);
                    button.setText("");
                    
                    //button.setText("S");
                    }
                if (Game.map[row][col] instanceof TrapCell ) {
                    button.setStyle(generateRnd());
                    //button.setStyle("-fx-background-color:#ff0000;");
                }
                if (row == xtarget&& col==ytarget &&count==1) {
                    button.setStyle("-fx-border-color: #FFD700; -fx-border-width: 3px;");
                    count++;
                    }
                if (row == xHero&& col==yHero ) {
                    button.setStyle("-fx-border-color: #000000; -fx-border-width: 3px;");
                    
                    }
                if (Game.map[row][col].isVisible()==false) {
                	//button.setStyle("-fx-background-color: transparent;");
                	//CHANGE
                	button.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");
                	button.setText("");
                	button.setGraphic(null);
                }
                
                
                button.setOnAction(e->{
                	
                	DropShadow borderGlow = new DropShadow();
                    borderGlow.setWidth(10);
                    borderGlow.setHeight(10);
                    borderGlow.setColor(javafx.scene.paint.Color.BLUE);
                    button.setEffect(borderGlow);
                    targetButton=button;
                    
                	String s =button.getId();
                	String []x= s.split(",");
                	int i=Integer.parseInt(x[0]);
                	int j=Integer.parseInt(x[1]);
                	Cell c =Game.map[i][j];
                	if (c instanceof CharacterCell && ((CharacterCell) c).getCharacter()!=null) {
                	target = ((CharacterCell) c).getCharacter();
                	setTargetForActions(target);
                	targetSelected=true;
                	xtarget=i;
                	ytarget=j;
                	targetButton.getStylesheets().clear();
                	targetButton.setStyle("-fx-border-color: #FFD700; -fx-border-width: 3px;");
                	
                	//System.out.println("hello");
                	
                	Hero.msg=Hero.msg+"You set "+ target.getName() +" as target\n";
                	count=1;
                	
                	//CHECK CHECK CHECK 
                	mains.updateWindow();
                	
                	}
                	
                	
                	
                });
                
                
//                Image startImage = new Image("File:grass.png");
//        		BackgroundImage aa = new BackgroundImage(startImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
//        				BackgroundPosition.DEFAULT,
//        				new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true));
//        		Background b = new Background(aa);
        		
        		//gridPane.setBackground(b);
        		
                gridButtons[row][col] = button;
                gridPane.add(button, col, 14-row);
               // gridPane.setPrefSize(900, 750);
                //gridPane.getStyleClass().clear();
                gridPane.setStyle("-fx-border-color: #FFD700; -fx-border-width: 5px;");
                if (mains.currentHero.isSpecialAction())
                	gridPane.setStyle("-fx-border-color: #FF0000; -fx-border-width: 5px;");
                if (mains.currentHero.isSpecialAction()&&mains.currentHero instanceof Medic && RightSide.counterMedicSpecial!=1) {
                	 gridPane.setStyle("-fx-border-color: #FFD700; -fx-border-width: 5px;");
                }
                if (mains.currentHero.isSpecialAction()&&mains.currentHero instanceof Medic && RightSide.counterMedicSpecial==1) {
               	 gridPane.setStyle("-fx-border-color: #FF0000; -fx-border-width: 5px;");
                RightSide.counterMedicSpecial++;
               }
                gridInABox=new HBox(gridPane);
                gridInABox.setPrefSize(900,750);
                
            }
        }
       // flipHorizontalAxis(gridButtons);

        //return gridPane;
        return gridInABox;
	}
	public static void setVisibilityAroundHeroes() {
		for (Hero h : Game.heroes) {
			Game.adjustVisibility(h);
		    CenterSide.createCenterSide();
		}
		
	}
	
	public static String generateRnd() {
		
		String c1 = "-fx-background-color:#4f782b";
        String c2 = "-fx-background-color:#4a7128";
        String c3 = "-fx-background-color:#55812e";
        String c4 = "-fx-background-color:#446725";
        String c5 = "-fx-background-color:#456925";
        String c6 = "-fx-background-color:#679d38";
        String c7 = "-fx-background-color:#5f9033";
        String c8 = "-fx-background-color:#4d752a";
        String c9 = "-fx-background-color:#619334";
        String c10= "-fx-background-color:#609234";
        String c11= "-fx-background-color:#135e19";
        String c12= "-fx-background-color:#4cb050";
       // String c13= "-fx-background-color:#609234";
        
        
        
        String color="";
        
        int rnd = (int) (Math.random()*5);
        //System.out.println(rnd);
        switch(rnd) {
        case 0:color=c1; break;
        case 1:color=c2; break;
        case 2:color=c3; break;
        case 3:color=c4; break;
        case 4:color=c5; break;
        case 5:color=c6; break;
        case 6:color=c7; break;
        case 7:color=c8; break;
        case 8:color=c9; break;
        case 9:color=c10; break;
        case 10:color=c11; break;
        case 11:color=c12; break;
        
        
        }
        return color;
	}
	
}