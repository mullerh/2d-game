<h1>A simple RPG</h1>

<h2>The General Idea</h2>

<p>The goal of this application is to create the basis of a simple top-down RPG (role playing game). The game will be a
series of smaller maps/levels. The player will be able to navigate around the map encountering 
enemies along the way. The game will track stats of the player and the enemies they fight (like health, stamina, 
strength, etc.), as well as the items the user has equipped, and the items in their inventory. While the game will start
off as a text based console game, eventually the same system will be used for the visual version. Also as the game 
design progresses, more complexity could be added, like a larger set of 
fighting moves, and model.itemTypes synergies (water being effective against fire model.itemTypes enemies).</p>

<p>Any person that is interested in RPG games will be the intended user, although the hope is that it's simple enough
for anyone. Not much further is required of the user since the game is meant for anyone to be able to pick up and play.
I'm particularly interested in this project since I've been interested in games all my life, and I have a grasp of how
games are structured. I want to be able to work on the coding that goes on behind games, without being too distracted 
by the artwork needed behind the scenes.</p>

<h2>User Stories</h2>
<h3>Phase1</h3>
- As a user, I want to be able to choose an attack type which will calculate a damage amount.
- As a user, I want to be able to view my character stats
- As a user, I want to be able to pick up items (adding an item to user inventory)
- As a user, I want to be able to swap equipped weapons (adding item to user inventory, and adding an item in 
inventory to the user's equippedWeapon slot)

<h3>Phase2</h3>
- As a user I want to be able to save the state of the game
- As a user I want to be able to be able to load the saved state of the game with character stats maintained, and
location in the story

<h2>Instructions for Grader</h2>
- You can generate the first required event by using the **arrow keys** (keyCodes 37 through 40), and move the character
(yellow dot) onto the same space as one of the blue dots. This adds an item (X) to the user Inventory (Y), which can be
viewed in the panel on the left hand side of screen.
- You can generate the second required event by pressing the **number key** (keyCodes 48 through 57) that matches up 
with the item you want to equip displayed on the right hand panel. This will swap that **inventory item** to be the 
user's **equipped item** (this can be viewed taking place in the left hand panel). 
    - If that does not count, you can move the user
over the red dot, this will trigger you to press **one of two buttons** to deal different damage. This damage is 
**calculated from the equipped item** (an X). However this is harder to prove that it works, since you have to try and 
kill the enemy with different items to see that better items do in fact kill the enemy quicker.
- You can trigger my audio component by using the **arrow keys** to move, this will trigger a sound (**warning**, it 
may be loud if using headphones). If that doesn't count, a picture should display after defeating the enemy (red dot).
This can be done by repeatedly moving over the red dot and dealing damage (note: this is quicker if you choose strong
attack and an item with a high attack stat)
- You can save the state of my application by pressing **S key**
- You can reload the state of my application by pressing **L key**

<h2>Phase 4</h2>
<h3>Phase 4: Task 2:</h3>
- Already made use of the Map interface. The class MapItems is a map of Items that has a location (Cell), as a key. This
class is used in classes: Game (such as pickUpItems), GameRenderer (such as drawItems).
    - If for whatever reason this doesn't count, I have a type hierarchy in my types folder in model folder.
 
<h3>Phase 4: Task 3</h3>
NOTE: I made my first change after Task 2, but forgot to push in between. Since I finished Task 2 in the week prior, 
you can look at Phase 3 to see what it looked like before these changes.
- First Change: I got rid of a class called Type (different from the current class called Type) and merged it into
the class called ItemType (now called Type). This is because Type dealt with the Type of a user, and ItemType dealt
with the Type of an Item. Since the data in these classes were essentially dealt with in the same way, if anything 
changed in one (like adding an Element), it would have to be changed in the other, hence merging them reduces coupling.
- Second Change: Moved getTypeString() method from Earth, Fire, Normal, Plant, Water classes to the Type class.
This makes it easier to add more types in the future, since this reduces coupling. Another benefit is if you want to
change the formatting of how the string is represented (ie. all caps, no caps, etc.), you only need to change it in one
spot. It also just makes sense to do this since there is no need for each Type to deal with this method on its own.
- I also considered taking out equipped weapon from the Character and defining the first element in the inventory to be
the equipped weapon (also reducing coupling). However, since this would potentially break the single responsibility
principle, I decided against this change. In addition, since the equipped weapon and inventory are almost always
handled differently, it would likely not make much of an improvement to begin with.