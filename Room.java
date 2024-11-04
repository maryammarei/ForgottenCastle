import java.util.Set;
import java.util.HashMap;
    /**
     * Represents a public class which holds information about the rooms, and its 
     * characters and items.
     * 
     * Maryam Marei
     * K21192007
     */
    public class Room 
{
    private String description;
    private String longerDescription; 
    private Items items;
    private Item item;
    private Character character;
    private Characters characters;
    private HashMap<String, Room> exits; // stores exits of this room.
    
    
    /**
     * Create a room described "description". 
     * @param description The room's description.
     * @param longerDescription An extended description.
     */
    public Room(String description, String longerDescription) 
    {
        this.description = description; // initialise room with descriptions
        this.longerDescription = longerDescription;
        
        this.item = item; // assign item and character to room
        this.character = character;
        
        exits = new HashMap<>(); // initialise exits, items, characters
        items = new Items();
        characters = new Characters();
    }
  
    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a description of the room in the form:
     *     You are in ...
     *     Exits: north west
     * @return A long description of this room
     */
    
    public String getLongDescription()  
    {
        return "you are " + description + ".\n" + getExitString();
    }
    
    /**
     *  Return the extended desciption of the room
     */
    public String getLongerDescription() 
    {
        return longerDescription;
    }
    
    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }
   
    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
    
    
    
    /**
     * Create an Item object.
     * @param name Item's name
     * @param weight Item's weight, etc.
     */
    public void setItem(String name,int weight, boolean canPickup, boolean canDrop, String description) {
        Item item = new Item(weight, canPickup, canDrop, description);
        items.addItem(name, item);
    }
    
    public Set<String> displayItemName(){
        return items.getItemsHashMap().keySet();
        // get the items from items instance
        // get the set of item names
    }
    
     public Items getItems(){
        return items; // return items
    }
    
    
    
    /**
     * Create a character object.
     * @param name Character's name
     * @param charDescription Character's description, etc.
     */
    public void setCharacter(String name, String charDescription, String speechOne, String speechTwo){
        Character character = new Character(charDescription, speechOne, speechTwo);
        characters.addCharacter(name, character);
    }
    
    public Set<String> displayCharacterName(){
        return characters.getCharactersHashMap().keySet(); 
        // get characters from characters instance
        // get the set of character names
    }

    public Characters getCharacters(){
        return characters; // return characters
    }
}

    
    
  
  