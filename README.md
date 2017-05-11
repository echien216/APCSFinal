Mater Tua by Eugenia Chien and Christine Yu
5/7/17

Note: the title is very much a work in progress


--INTRODUCTION--

Mater Tua is a top-down game designed to prevent people from becoming bored and to provide cheap entertainment. 
In it, the player controls a character that must reach the goal within a specified time limit while killing enemies by using skills. 
Getting hit by an enemy damages and stuns the player, and taking too much damage will result in the player's death (game over). 
Running out of time before reaching the goal will also cause the player's death (game over). 
Any extra time left at the end of a level will be added to the next level's time limit.


--INSTRUCTIONS--

Arrow keys: movement

A: melee attack/skill 1 (basic attack) (no cooldown)

S: ranged attack/skill 2 (medium cooldown)

D: heal/skill 3 (long cooldown)

F: giant explosion/skill 4 (ultimate) (very long cooldown)

Attack enemies to kill and get past them. 
Getting attacked by enemies does damage and stuns you for a short amount of time, and taking too much damage means game over. 
Get to the end of the level before time runs out, or game over. 
Any time left at the end of a level will be added to the next level's time limit. 
Skill cooldowns carry between levels (choose wisely).


--CLASS LIST--

Main - runner

Overworld - handles all game graphics, player/enemy/obstacle interactions

Level - handles level design

Solid - anything that cannot be "phased through"

Actor - anything that can move

Player (extends Actor) - the player

Enemy (extends Actor) - enemies

Obstacle - anything that Actors cannot move through, but cannot move itself

TitleScreen - the menu when the game is opened

InstructionScreen - screen showing instructions

MusicPlayer (stretch) - plays music

DeathScreen (stretch) - shows stats after player dies

Projectile - anything that is fired, ex. attacks


--FEATURES LIST--

Must have:

Smooth movement and attacks

Nicely working hitboxes (ex. dodge attacks if out of range)

Multiple connected levels, with a short countdown between each level

Different types of enemies that disappear when dead but are obstacles when alive

Nicely working AI path finding 


Want to have:

Player can choose what character they want to play as (ex. generic melee vs. tank vs. ranged mage vs. glass cannon) (each character has different skills and stats)

Character stats (ex. attack, defense, health)

Bonus stage after beating all levels where enemies swarm player and player must survive for as long as possible

Cool looking, awesomely animated sprites (attack and move animations)

Multiple difficulty levels that player can choose before starting the game

Stretch:

Background music (on repeat) that plays faster each level (generally just some kind of background music)

Cheat screen that allows player to change background music, gain bonuses, etc. by typing in certain keywords

Cutscenes between levels with short phrases that connect into a short story

Characters can pick up items that give bonuses

Death recap screen


--RESPONSIBILITIES--

Eugenia: Actor, Player, Enemy, Obstacle, Projectile

Christine: Overworld, Level(s)

Both: Main, TitleScreen, InstructionScreen, graphics
Our name might need work ("might need work" as in "really needs work") (literally your mom)

(QUESTIONS)
Recommend that you describe the class list in your README to make it clear to the reader what each class is used for (especially Overworld and Projectile).
What is the goal of your game and what does your program look like? Is it a maze? (Add into README to clarify)
Recommend that you make your attack methods (skills) unique to make your program different from other obstacle games.
Is your game like a grid-type game (Pacman) or more like Mario (on the AnimationDemo)?
