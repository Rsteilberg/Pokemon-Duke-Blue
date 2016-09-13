CompSci 308: JavaFX Game README.md
======================

Robert Steilberg | rhs16

----------------------------

Names of contributors

* Robert Steilberg

Date started:

* 5 September 2016

Date finished:

* 12 September 2016

Hours spent working on project:

* Approximately 24 hours

Individual roles:

* I had full responsibility for this entire project.

Resources:

* TAs Eric, Karen, Arjun, Daniel
* Oracle Java documentation
* http://docs.oracle.com/javafx/2/get_started/jfxpub-get_started.htm
* http://www.oracle.com/technetwork/java/javase/overview/javafx-overview-2158620.html
* https://carlfx.wordpress.com/2012/03/29/javafx-2-gametutorial-part-1/ : Parts 1 through 4 
* http://stackoverflow.com/questions/23202272/how-to-play-sounds-with-javafx

Files used to start the project:

* The example files given in class

Files used to test project:

* None

Data or resource files:

* Images for the project are located in the images folder and are in PNG or JPG format
* Sounds for the project are located in the music folder and are in the MP3 format

Pertinent information:

* The arrow keys control the player
* The Z key is used to engage non-player-characters (NPCs) or attack during battles
* The C key, when pressed during the menu scene, brings up a list of cheats
* The B key, when pressed during the menu scene, takes the player directly to battle
* The I key, when pressed during battle, toggles invincibility for the user's Pokemon

Known bugs:

* Due to the timing step function that I use to call opponent attacks, I was unable to remove the POW image from the scene on GAME OVER when the player loses
* During the battle mode, quick attacks made on the opponent's Pokemon will cause the game to lag due to the time required to process sound effects for battle attacks
* A warning is generated every time a sound is played. This is normal and is not indicative of any exception to the regular program flow of the game.

Extra features:

* Sound effects and background music, obstructed sprite movement

Impression:

* This assignment was a great introduction to JavaFX and the class in general. I really enjoyed coding my first game and feel like I could spend even more time improving it in the future. I felt that the guidance for JavaFX given in class was sufficient to jump start my coding process, and the TAs were sufficiently knowledgeable about JavaFX to help me with any problems. It would have been helpful if we were given a more complex example code file to build off of, because I feel that it didn't really use the best coding standards that are expected of us for this project.