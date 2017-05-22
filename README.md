Mater Tua by Eugenia Chien and Christine Yu
5/7/17

Note: the title is very much a work in progress


--INTRODUCTION--

Mater Tua is a top-down game designed to prevent people from becoming bored and to provide cheap entertainment. 
In it, the player controls a character that must reach the goal within a specified time limit while killing enemies by using skills. 
Taking too much damage will result in the player's death (game over). Running out of time before reaching the goal 
will also cause the player's death (game over). Half of the extra time left at the end of a level will be added to the next level's time limit.


--INSTRUCTIONS--

Arrow keys: movement

A: ranged attack (basic attack) (no cooldown)

S: multi-directional ranged attack (4 second cooldown)

D: heal (10 second cooldown)

F: damages all enemies (ultimate) (60 second cooldown)

Attack enemies to kill and get past them. 
Getting attacked by enemies does damage and stuns you for a short amount of time, and taking too much damage means game over. 
Get to the end of the level before time runs out, or game over. 
Half of the time left at the end of a level will be added to the next level's time limit. 
Skill cooldowns carry between levels.


--CLASS LIST--

Main - runner

Overworld - handles all game graphics, player/enemy/obstacle interactions

Level - handles level design

Solid - anything that cannot be "phased through", i.e. has a hitbox of some kind

Actor (implements Solid) - anything that can move

Player (extends Actor) - the player

Enemy (extends Actor) - normal enemy

EnemyStrong (extends Enemy) - tougher and stronger enemy

Projectile (implements Solid) - anything that is fired by Actors when they attack

Obstacle (implements Solid) - anything that Actors cannot move through, and are unable to move

Goal (extends Goal) - the area the player must touch to move onto the next level

TitleScreen - the menu when the game is opened

InstructionScreen - screen showing instructions


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

Eugenia: Solid, Actor, Player, Enemy, EnemyStrong, Obstacle, Projectile, Overworld, collision, pathfinding, interactions

Christine: Level, Projectile, Goal, Main, TitleScreen, InstructionScreen, menus

Our name might need work ("might need work" as in "really needs work") (literally your mom)

