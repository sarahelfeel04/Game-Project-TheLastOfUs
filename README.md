# Game-Project-TheLastOfUs
The Last Of Us themed Java Game


## Game Description: 
Welcome to **The Last Of Us: Legacy**, a Java-based game set in a world gripped by a zombie apocalypse. In this single-player survival game, you'll navigate through a turn-based environment where strategic decisions are key to survival. Each player character receives a specific number of action points per turn, allowing them to move, attack or cure zombies, and utilize special actions.

Your journey begins with one hero under your control. By successfully curing zombies, you can recruit additional heroes. Your ultimate objective is to endure long enough to cure a sufficient number of zombies and establish a resilient community capable of surviving the apocalypse.

## Snippets from the game
![Screen Shot 2023-08-27 at 11 54 40 PM](https://github.com/sarahelfeel04/Game-Project-TheLastOfUs/assets/142936159/a761df7f-f45f-4aad-92df-b7cf45b54ecb)
![Screen Shot 2023-08-27 at 11 54 57 PM](https://github.com/sarahelfeel04/Game-Project-TheLastOfUs/assets/142936159/d877ee79-2de4-4802-9c9d-4ccbc4ad115f)
![Screen Shot 2023-08-27 at 11 55 14 PM](https://github.com/sarahelfeel04/Game-Project-TheLastOfUs/assets/142936159/3adf6cc9-76f3-4cfb-a6c6-9c3cef966fd1)
![Screen Shot 2023-08-27 at 11 55 26 PM](https://github.com/sarahelfeel04/Game-Project-TheLastOfUs/assets/142936159/402345e3-aec6-4e4c-894e-e03483e31dda)
![Screen Shot 2023-08-27 at 11 55 42 PM](https://github.com/sarahelfeel04/Game-Project-TheLastOfUs/assets/142936159/1106ad15-6666-4d6a-aaca-3c52eca8b4f8)
![Screen Shot 2023-08-27 at 11 55 58 PM](https://github.com/sarahelfeel04/Game-Project-TheLastOfUs/assets/142936159/1223d853-dbd8-4fc0-8ca0-c7242cfabf4f)

## Video of the game 

Here's a video of me playing the game, check this following link [here](https://drive.google.com/file/d/13tecFC7DrL22-nVYQZ35Y4vdS-CqspWC/view?usp=share_link)

## Characters
Characters in the game are split into Heroes or Zombies. Heroes
Heroes are the types of characters that the player can control. There are several types of heroes available in the game, each one provides different assets for the player in order to win the game. Any character; Zombie or Hero, has an initial amount of health that decreases whenever they are attacked. If the character’s health ever reaches 0 they are killed and removed from the game.
Considering that the aim of the game is to build a large enough community to survive the apocalypse thus the player must try to maintain and protect their heroes at all costs, as well as try to expand their available pool of heroes.
Each hero type has a unique action they can add to the player’s team:

* Explorer: Allows the player to be able to see the entirety of the map for 1 turn whenever a supply is used.
*  Medic: Can heal and restore health to other heroes or themselves, each process of healing uses 1 supply.
* Fighter: Can attack as many times in a turn without costing action points, for 1 turn whenever a supply is used.

Possible actions that can be done by a character: 
- Move
- Attack a zombie
- Cure a zombie
- Use their class dependant unique action

## Zombies
Zombies are the types of characters that threaten the player during the game. Zombies cannot be controlled, however they can be cured or attacked. Each time a zombie is killed another zombie will spawn somewhere on the map. In addition to extra zombies spawning every time the player ends a turn.
Whenever a zombie is cured an extra hero will take its place and be available for the player to use in future turns.

## Collectibles
Collectibles are scattered objects across the map that can help the player survive and advance in the game. Each collectible is only usable once, and after that is discarded from the hero’s inventory and cannot be reused.
- Vaccines: Vaccines are an integral and important part of the game. As the player can only win the game once all vaccines have been collected and used. Vaccines are also the only means through which players can cure zombies and recruit new heroes.
- Supplies: Supplies are the other type of collectible available in the game. Supplies enable the carrying hero to use their special action.

## Gameplay Flow
The player starts off in a 15x15 grid map with just one hero and 10 zombies. The player can only see the directly adjacent cells next to their pool of heroes. The player then keeps taking his turn trying to collect vaccines, and cure or kill zombies. The game ends when the player has collected and used all vaccines or when all heroes have been overwhelmed and defeated by the zombies.
The player only wins if he has successfully collected and used all vaccines and has 5 or more heroes alive.
 
## Implementation: 
The game was implemented using Java OOP feautures. For the graphical user interface part, JavaFX was used to demonstrate the actions happening in the game to the view. 

## Usage information and project grading
This project was given a grade of A+ for its outstanding graphical user interface. 
This game was built using JavaFX SDK installed on Eclipse. To be able to play the game and compile the code correctly, JavaSDK IDE has to be installed. For more information, check this [link](https://docs.oracle.com/javafx/2/installation/jfxpub-installation.htm). Enjoy the game!

