import java.util.HashMap;
import java.util.Set;

/**
 * A public class representing items in the game
 *
 * Maryam Marei
 * k21192007
 */

public class Items {
    // Private HashMap to store items with their names as keys
    private HashMap<String, Item> items;

    //  initialize the Items class
    public Items() {
        items = new HashMap<>();
    }

    // method to add an item 
    public void addItem(String name, Item item) {
        items.put(name, item);
    }

    // method to get the HashMap containing items
    public HashMap<String, Item> getItemsHashMap() {
        return items;
    }
}
