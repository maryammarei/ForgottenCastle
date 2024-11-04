import java.util.HashMap;
/**
 * Represents the Player in the game and their inventory.
 *
 * @Maryam Marei
 * K21192007
 */
public class Player
{
    private HashMap<String, Item> inventory; // stores items in inventory
    private Item item;
    private Player player;
    private int capacity;
    
    // initialises player's inventory and sets initicial capacity to 0
    public Player(){
        inventory = new HashMap<>();
        capacity = 0;
    }
    
    /**
     * Adds an item to the inventory.
     * @param name The name of the item
     * @param item The item to be added to the inventory
     */
    public void addToInventory(String name, Item item) {
        inventory.put(name, item);
    }
    
    /**
     * @return player's inventory
     */
    public HashMap<String, Item> getInventory(){
        return inventory;
    }
}
