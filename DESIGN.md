CompSci 308: JavaFX Game DESIGN.md
======================

Robert Steilberg | rhs16

----------------------------

High-level design goals:

* I sought to create an imitation Pokemon game that was as genuinely close to the real RPG games that I could get to with my current coding skill level. I wanted to maximize the role-playing aspect, so I made sure to have plenty of non-player-characters (NPCs) that the player could engage, in addition to a turn-based battle sequence.

How to add new features:

* The world can be changed by using a different image file for the background. Then, obstructions need only be updated through the functions provided in World.java and Houses.java to prevent the sprite from walking over them. More battling options can be added for each NPC by creating a new Battle instance and then returning to the World scene after each battle. The sprite can likewise be changed by changing the sprite's image file. The player's and opponent's Pokemon can also be changed by changing their respective image files. Text prompts can be changed by changing the variables in their associated classes. Sound effects can be added through use of Java's MediaPlayer, or manipulated by changing which sound files are included in the project folders.

Major design choices:

* Due to time and skill constraints, I did not make it possible for the player to choose their Pokemon, or those of their opponent, during the game. Furthermore, only the boss can be battled so that scene changes are simplified. This made implementation easy, but decreased the similarity between my game and the actual Pokemon RPG games. Because sound effects are an integral component of Pokemon games, I also took the time to add background music and sound effects to many components of the game. I chose 8-bit sound files and low-resolution images to simulate the actual Pokemon games to the highest degree possible.

Assumptions made:

* I assume that the user does not make rapid and constant input into the game. For example, during the battle scene, if the user presses the Z key incessantly, this will overload the game engine due to the repeated sound effects that must be processed and player for each attack. This is also true when engaging NPCs during the world mode.