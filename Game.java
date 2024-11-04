import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * This class is the "The Forgotten Castle" application. 
 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * Maryam Marei
 * K21192007
 */
public class Game 
{
private Parser parser;
private Room currentRoom;
private Room room;
private List<Room> rooms;
private List<Room> previousRooms = new ArrayList<>();
private Item item;
private Items items;
private Character character;
private Characters characters;
private Player player;
private int capacity;
private int addWeight; 
private int subWeight;
private int count; 


/**
* Create the game and initialise its internal map.
*/
public Game() 
{
    printWelcome();
    
    createRooms();
    parser = new Parser();
    
    System.out.println("your command words are:");
    parser.showCommands();
    
    player = new Player();
    capacity = 0;
}

        
        
/**
* Create all the rooms and link their exits together.
*/
private void createRooms()
{
    Room outside, castle, hall, lab, library, storage, dining, throne, courtyard, magic, vault; 
  
    // create the rooms
    outside = new Room("outside a castle", "in front of you is a large gate which leads to a castle");
    castle = new Room("in the castle", "ahead of you is a long hall");
    hall = new Room("walking in the castle's entrance hall", "the hall is decorated with beautiful paintings");
    lab = new Room("in an alchemy lab", "there are endless shelves of potions and books");
    magic =  new Room("in the magic transporter room!", "there's a door in each direction");
    storage = new Room("in a storage room", "not much to see here");
    dining = new Room("in the dining hall", "there is a grand table decorated with food, but no one to eat it...");
    library = new Room("in a library", "its massive and has thousands of books... they're colllecting dust.");
    vault = new Room("in a vault room", "there is a vault at the end of the room..."); 
    throne = new Room("in the throne room", "large throne in the midst of the room");
    courtyard = new Room("in the castle's courtyard", "decorated with trees and flowers");
   
    // initialise room exits
    outside.setExit("north", castle);
    outside.setCharacter("dwarf", "a kind-looking dwarf", "welcome! i hear you're here to help", "here's a map to help you! just enter map to use it!"); //initialise room character
    
    castle.setExit("north", hall);
    castle.setItem("lore 1", 0, false, false, "long ago, this castle was formed..."); // initialise room items
    castle.setCharacter("wizard", "a grey-bearded wizard with purple robes", "greetings!", "im glad you're here, this castle has been abandoned for too long... i'll be in the lab");
       
    
    
    hall.setExit("south", castle);
    hall.setExit("north", dining);
    hall.setExit("east", lab);
    hall.setExit("west", storage);
    hall.setItem("paintings", 0, false, false, "paintings showing the castle before it was abandoned");
    hall.setCharacter("witch", "a young witch with blue robes, she's looking at a painting", "nice to meet you.. i'm the wizard's apprentice.", "the paintings in this hall depict how full of life this castle was...");
    hall.setItem("lore 2", 0, false, false, "it was filled with people and life... but the castle's source of life depended on gems... ");
    
    lab.setExit("west", hall);
    lab.setExit("east", magic);
    lab.setItem("books", 0 , false, false, "potion books.");
    lab.setItem("potions", 0 , false, false, "vials of potions of different colours,such as purple, red, yellow & green.");
    lab.setCharacter("wizard", "a grey-bearded wizard with purple robes", "nice to see you again!", "feel free to have a look around.");
    lab.setItem("lore 3", 0 , false, false, "one day, a villain took these gems, and everyone in the castle fled");

    storage.setExit("east", hall);
    storage.setItem("green gem", 5, true, false, "it has the number 2 on it.");
    storage.setItem("boxes", 0, false, false, "just boxes.");
    storage.setItem("lore 4", 0 , false, false, "some characters stayed and fought, getting the gems back...");
    
    dining.setExit("north", throne);
    dining.setExit("west", library);
    dining.setExit("south", hall);
    dining.setItem("red gem", 5, true, false, "it has the number 1 on it.");
    dining.setItem("cake", 1, true, true, "chocolate cake");
    dining.setItem("apple", 1, true, true, "red apple.");
    dining.setItem("lore 5", 0 , false, false, "but the gems were not able to find their own way back...");
    
    library.setExit("west", vault);
    library.setExit("east", dining);
    library.setCharacter("librarian",  "an elf wearing glasses", "...", "...");
    library.setItem("book", 5, true, true, "book: history of the castle");
  
    
    vault.setExit("east", library);
    vault.setItem("lock", 0, false, false, "enter combination: ");
    
    throne.setExit("north", courtyard);
    throne.setExit("south", dining);
    throne.setItem("yellow gem", 5, true, false, "it has the number 0 on it.");
    throne.setCharacter("queen", " the queen sat on her throne * " + ".\n" +  "* she looks sad, her crown has a crown with purple, red, yellow, & green gems", "are you here to help?", " you should go to the vault");
    
   
    courtyard.setExit("south", throne);
    courtyard.setItem("purple gem", 5, true, false, "it has the number 2 on it.");
    courtyard.setCharacter("witch", "a young witch with blue robes", "purple, red, yellow, green...", "purple, red, yellow, green... she's repeating the same thing");
    
    
    rooms = new ArrayList<>(); //create an arraylist of rooms for magic transporter room
    rooms.add(outside);
    rooms.add(castle);
    rooms.add(hall);
    rooms.add(lab);
    rooms.add(storage);
    rooms.add(dining);
    rooms.add(library);
    rooms.add(vault);
    rooms.add(throne);
    rooms.add(courtyard);
    rooms.add(magic);
    
    
    currentRoom = outside; // start game outside
    previousRooms.add(currentRoom); // add current room to arraylist of previous rooms
}



/**
 *  Main play routine.  Loops until end of play.
**/
public void play() 
{            
    printWelcome();
    
    // Enter the main command loop.  Here we repeatedly read commands and
    // execute them until the game is over.
    
    boolean finished = false;
    while (! finished) {
        Command command = parser.getCommand();
        finished = processCommand(command);
    }
    System.out.println("thank you for playing. good bye.");
}



/**
 * Print out the opening message for the player.
 */
private void printWelcome()
{
    System.out.println();
    System.out.println("welcome to The Forgotten Castle!");
    System.out.println("embark on an enchanting journey into a castle of mystery and adventure!");
    System.out.println("you find yourself standing before The Castle — a place lost in time and forgotten by many.");
    System.out.println();
    System.out.println("you were exploring when, behold, you stumbled upon a hidden gem —");
    System.out.println("the castle seems to be abandoned, and you are eager to explore...");
    System.out.println("the air is still, and the castle beckons you to uncover its mysteries.");
    System.out.println();
    System.out.println("if you look carefully, there are clues everywhere...");
    System.out.println("there's someone here... try the look & interact commands!");
    System.out.println();
    System.out.println("type 'help' to unravel the secrets that await your discovery....");
}



    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command)   
    {
    boolean wantToQuit = false;

    if(command.isUnknown()) {
        System.out.println("i don't know what you mean...");
        return false;
    }

    String commandWord = command.getCommandWord();
    if (commandWord.equals("help")) {
        printHelp();
    }
    else if (commandWord.equals("go")) {
        goRoom(command);
    }
    else if(commandWord.equals("back")) {
        back(command);
    }
    else if(commandWord.equals("look")) {
        look(command);
    }
     else if(commandWord.equals("inspect")){
        inspect(command);
    }
    else if(commandWord.equals("interact")){
        interact(command);
    }
    else if (commandWord.equals("x")){
        talk(command);
    }
    else if(commandWord.equals("take")){
        take(command);
    }
    else if(commandWord.equals("drop")){
        drop(command);
    }
    else if(commandWord.equals("inventory")){
        inventory(command);
    }
    else if (commandWord.equals("give")){
        give(command);
    }
    else if(commandWord.equals("map")){
        map(command);
    }
    else if (commandWord.equals("quit")) {
        wantToQuit = quit(command);
    }
    else if(commandWord.equals("2102")){
        wantToQuit = winGame(command);
    }
    
    return wantToQuit; // else command not recognised.
}
    


/**
 * Print out some help information.
 * Here we print list of the command words and what each one does.
 */
private void printHelp() 
{
    System.out.println();
    System.out.println();
    System.out.println("your command words are:");
    parser.showCommands();
    System.out.println();
    System.out.println("go: to move around");
    System.out.println();
    System.out.println("back: takes you to previous room"); 
    System.out.println();
    System.out.println("look: look around a room and see if there are items/characters");
    System.out.println();   
    System.out.println("inspect: look at item closely");
    System.out.println();
    System.out.println("interact: speak with character");
    System.out.println();   
    System.out.println("take: pick-up an item");
    System.out.println();   
    System.out.println("drop: drop an item");
    System.out.println();
    System.out.println("inventory: to view");
    System.out.println();
    System.out.println("give: give a character an item");
    System.out.println();
    System.out.println("here's a clue, give a character food and they might help...");
}



/** 
 * Generates a random room from the list of rooms
 * Returns random room.
 */
    private Room getRandomRoom(List<Room> rooms){
    Random random = new Random();
    int index = random.nextInt(rooms.size()); // get random number within bounds
    return rooms.get(index); // return room with that index
}

/** 
 * Try to in to one direction. If there is an exit, enter the new
 * room, otherwise print an error message.
 * If the index of the room is 10, the magic transporter room, generates random room.
 */
private void goRoom(Command command) 
{
   if(!command.hasSecondWord()) {
        // if there is no second word, we don't know where to go...
        System.out.println("go where?");
        return;
    }

   String direction = command.getSecondWord(); // get second word

   
   Room nextRoom;
  
   if (rooms.indexOf(currentRoom) == 10) { // room is magic transporter room, gets random.
    nextRoom = getRandomRoom(rooms);
    } else {
        nextRoom = currentRoom.getExit(direction); 
    }

   if (nextRoom == null) {
        System.out.println("there is no door!");
    } else {
    currentRoom = nextRoom;
    previousRooms.add(currentRoom); 
    // add current room into an array list of previous rooms for the back command.
        
    items = currentRoom.getItems(); // get the items in the room 
    System.out.println(currentRoom.getLongDescription()); // display long description.
    System.out.println();
    System.out.println("your command words are:"); // display command words
    parser.showCommands();
    }
}



private void back(Command command) {
    // check if there are previous rooms to go back to
    if (previousRooms.size() > 1) {
        // remove the most recent room from the history
        previousRooms.remove(previousRooms.size() - 1);

        // set the current room to the previous room
        currentRoom = previousRooms.get(previousRooms.size() - 1);
    }

    // get items in the current room
    items = currentRoom.getItems();
    count = 0;

    // display the long description of the current room
    System.out.println(currentRoom.getLongDescription());
    
    // display command words
    System.out.println("Your command words are:");
    parser.showCommands();
}



 /** 
 * Allows player to see all characters and items present in the room by retrieving 
 * relevant characcter and item objects.
 */
private void look(Command command) 
{
    System.out.println(currentRoom.getLongDescription());
    // display the extended description of the current room
    System.out.println(currentRoom.getLongerDescription());
    System.out.println();
    System.out.println("items in this room: " + currentRoom.displayItemName()); 
    // display items
    System.out.println();
    // display  characters present in the current room
    System.out.println("characters in this room: " + currentRoom.displayCharacterName());
    System.out.println();

    // display character description for every character if in the room.
    for (HashMap.Entry<String, Character> entry : currentRoom.getCharacters().getCharactersHashMap().entrySet()) {
        String name = entry.getKey();
        Character character = entry.getValue();
        // check if the character is a displayed character in the room
        if (currentRoom.displayCharacterName().contains(name)) {
            System.out.println("* you see " + character.getCharDescription() + " *");
        }
    }
}



/**
 * Simulates inspecting an object. Displays relevant information about object.
 */ 
private void inspect(Command command) {
    // initialize item name variable
    String name = null;

    // check if there is a second word
    if (!command.hasSecondWord()) {
        System.out.println("inspect what?");
        return;
    }

    // check there is a third word, if not get item name from the second word
    if (!command.hasThirdWord()) {
        name = command.getSecondWord();
    } else {
        // if there is third word call method to make second and third word item name
        name = command.getSecondThird();
    }

    // check if specified item is in  current room
    if (currentRoom.displayItemName().contains(name)) {
        // get the item object associated with the name
        Item item = currentRoom.getItems().getItemsHashMap().get(name);

        // display information about the item
        System.out.println("name: " + name);
        System.out.println("weight: " + item.getWeight());
        System.out.println("description: " + item.itemDescription());
    } else {
        // display a message if the item is not present in the room or has been picked up
        System.out.println("nothing to inspect here... enter 'inventory' to view your items.");
    }
}



/** 
 * Allows player to interact with characters.
 * Simulates so by retrieving character's speech.
 */
private void interact(Command command) {
    // check if the command has a second word (character name)
    if (!command.hasSecondWord()) {
        System.out.println("interact with who?");
        return;
    }

    String name = command.getSecondWord(); // get second word

    // check if the character is present in the current room
    if (currentRoom.displayCharacterName().contains(name)) {
        // get the character object with the name
        character = currentRoom.getCharacters().getCharactersHashMap().get(name);

        // check if the character object not null
        if (character != null) {
            // display the character first speech
            System.out.println(name + ": " + character.getSpeechOne());
            
            // prompt to continue 
            System.out.println("enter X to continue...");
        } else {
            // display a message if the character is not in the room
            System.out.println("Character " + name + " not found in the room.");
        }
    }
}

/**
 * Increments "count" everytime a user interacts with a character.
 */
private void incrementCount(){
    count++; // increments count
}

private int getCount(){
    return count; // returns count
}




/**
 * Simulates a conversation between character and player. 
 * Retrieves second speech after prompting player to continue.
 * Increments count so that after the player and character "talk", the character
 * leaves the room to simulate character movement.
 */
private void talk(Command command)
{
    // display character speech for every character.
    for (HashMap.Entry<String, Character> entry : currentRoom.getCharacters().getCharactersHashMap().entrySet()) {
        // get the character object assosciated with the current room
        String name = entry.getKey();
        Character character = entry.getValue();
    
        // check if the charactername is a displayed character in the room
        if (currentRoom.displayCharacterName().contains(name)) {
            // Display the second speech of the character
            System.out.println(name + ": " + character.getSpeechTwo());
        }
    
        // increment the count
        incrementCount();
    
        
        if (count >= 1) {
            // if count >= 1 , remove the character from the room
            currentRoom.getCharacters().getCharactersHashMap().remove(name);
    
            // display a message indicating that the character has left the room
            System.out.println("* " + name + " has left the room. *");
        }
    }
}



private void take(Command command) {
    // initialize the item name variable
    String name = null;

    // check if there is a second word (name)
    if (!command.hasSecondWord()) {
        System.out.println("Take what?");
        return;
    }

    // check if  there is a third word, if not get the item name from second word
    if (!command.hasThirdWord()) {
        name = command.getSecondWord();
    } else {
        // if there is third word call method to make second and third word item name
        name = command.getSecondThird();
    }

    // check if specified item is in the current room
        if (currentRoom.displayItemName().contains(name)) {
        // get the item object associated with the name
        Item item = currentRoom.getItems().getItemsHashMap().get(name);
        // check its not null
            if (item != null) {
                // check if picking up the item would exceed inventory capacity
                if (capacity + item.getWeight() >= 30) {
                    System.out.println("cannot pickup, exceeds capacity!");
                } else {
                    // check if  item can be picked up
                    if (item.canPickup()) {
                        // make boolean in inventory true
                        item.putInventory(true);
                        // add item to the inventory
                        player.addToInventory(name, item);
                        // increment inventory capacity by item's weight
                        incrementCapacity();
                        // remove item from current room
                        currentRoom.getItems().getItemsHashMap().remove(name);
    
                        // display a message indicating that item has been picked up
                        System.out.println("you picked up " + name + ".");
                        System.out.println(name + " is now in inventory.");
                    } else {
                        System.out.println(name + " cannot be picked up.");
                    }
                }
        } else {
            // display a message if the item is not present in the room
            System.out.println(name + " is not in the room.");
        }
    }
}



private void drop(Command command) {
    // check if there is a second word (name)
    if (!command.hasSecondWord()) {
        System.out.println("Drop what?");
        return;
    }

    // get item name from the second word
    String name = command.getSecondWord();

    // get the item assosciated with name from  inventory
    Item item = player.getInventory().get(name);

    // check if the item exists in inventory AND can be dropped
    if (player.getInventory().containsKey(name) && item.canDrop()) {
        // decrement inventory capacity by item's weight
        decrementCapacity();
        // remove the item from the  inventory
        player.getInventory().remove(name);
        // add the item to the current room's items
        currentRoom.getItems().getItemsHashMap().put(name, item);
        // display a message indicating that the item has been dropped.
        System.out.println("you dropped " + name);
    } else {
        // display a message if the item cannot be dropped.
        System.out.println("you cannot drop quest items!");
    }
}




/**
 * Displays the players inventory using the boolean in inventory.
 */
private void inventory(Command command) {
    // for every item in inventory
    for (HashMap.Entry<String, Item> entry : player.getInventory().entrySet()) {
        // get item name and associated Item object
        String name = entry.getKey();
        Item item = entry.getValue();

        // check if the item is in the  inventory
        if (item.inInventory()) {
            // display all items and their descriptions 
            System.out.println("inventory: ");
            System.out.println("name: " + name + ", weight: " + item.getWeight() + "\ndescription: " + item.itemDescription());

        }
    }

    // displaya the current capacity of inventory
    System.out.println("capacity = " + capacity + "/25");
}

private void incrementCapacity() {
    // method to increment capacity every time an item is picked up
    for (HashMap.Entry<String, Item> entry : player.getInventory().entrySet()) {
        String name = entry.getKey();
        Item item = entry.getValue();
        addWeight = item.getWeight();
    }
    capacity += addWeight;
}


private void decrementCapacity(){
    // method to decrement capacity every time an item is dropped
    for (HashMap.Entry<String, Item> entry : player.getInventory().entrySet()) {
        String name = entry.getKey();
        Item item = entry.getValue();
        subWeight = item.getWeight();
    }
    capacity -= subWeight;
}



/**
 * Allows player to "give" characters items.
 */
private void give(Command command) {
    // check if there is a second word (item)
    if (!command.hasSecondWord()) {
    System.out.println("give what?");
    return;
    }
    // check if there is a third word (character)
    if (!command.hasThirdWord()) {
    System.out.println("give to whom?");
    return;
    }
    
    // get item name and character name from command
    String name = command.getSecondWord();
    String charName = command.getThirdWord();
    
    //check if the item is in the  inventory
    if (player.getInventory().containsKey(name)) {
    // check if the character is in the room
    if (currentRoom.displayCharacterName().contains(charName)) {
        // "give" the item to the character
        Character character = currentRoom.getCharacters().getCharactersHashMap().get(charName);
        decrementCapacity(); 
        player.getInventory().remove(name);
        // call relevant methods to simulate item being remvoed
        System.out.println("you gave " + name + " to " + charName);
        System.out.println(charName + " : thanks!");
        System.out.println("for your kindness, ill give you a clue...");
        System.out.println("the order is purple, red, yellow, green.");
        // display message indicating item has been given, and clue that is returned.
    } else {
        System.out.println("character " + charName + " not found in the room.");
    }       // if character is not in the room
    } else {
    System.out.println(name + " is not in your inventory.");
    } // if item not in inventory
} 



/**
 * Displays the game's map 
 */
private void map(Command command){
System.out.println();
System.out.println("---------------------------------------------------------");
System.out.println("|                    courtyard                    N     |");
System.out.println("|                        |                      W * E   |");
System.out.println("|                        |                        S     |");
System.out.println("|                   throne room                         |");
System.out.println("|                        |                              |");
System.out.println("|                        |                              |");
System.out.println("| vault --- library --- dining hall                     |");
System.out.println("|                        |                              |");
System.out.println("|                        |                              |");
System.out.println("|           storage --- hall --- alchemy lab --- mystery|");
System.out.println("|                        |                              |");
System.out.println("|                        |                              |");
System.out.println("|                      castle                           |");
System.out.println("|                        |                              |");
System.out.println("|                        |                              |");
System.out.println("|                     entrance                          |");
System.out.println("---------------------------------------------------------");
System.out.println();
}    



/** 
 * "Quit" was entered. Check the rest of the command to see
 * whether we really quit the game.
 * @return true, if this command quits the game, false otherwise.
 */

private boolean quit(Command command) 
{
    if(command.hasSecondWord()) {
        System.out.println("Quit what?");
        return false;
    }
    else {
        return true;  // signal that we want to quit
    }
}
    


/**
 * If player enters correct command, player wins game.
 * Displays congratulating message.
 * Quits game.
 */
private boolean winGame(Command command){
System.out.println("congratulations! the once-abandoned castle now stands vibrant and alive!");
System.out.println("the echoes of life have returned to its halls, and the people, once gone, are back!");
System.out.println("laughter and chatter fill the air, replacing the eerie silence that haunted the castle.");
System.out.println("the courtyard, once overgrown with weeds, is now bustling with activity.");
System.out.println("you've restored the castle to its former glory, turning it into a thriving hub of life.");
System.out.println();
System.out.println("you are the true hero who brought life back to this once-forgotten kingdom!");
System.out.println("you have successfully won the game.");

boolean finished = true;
return finished;
}
}








    
    