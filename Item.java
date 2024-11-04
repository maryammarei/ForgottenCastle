import java.util.HashMap;

/**
 * A public class representing an item.
 *
 * Maryam Marei
 * K21192007
 */
public class Item
{
  private int weight;
  private boolean canPickup;
  private boolean canDrop;
  private boolean canInteract;
  private String itemDescription;
  private boolean inInventory;
     
  // initialise the item and its properties
  public Item(int weight, boolean canPickup, boolean canDrop, String itemDescription) {
      this.weight = weight;
      this.canPickup = canPickup;
      this.canDrop = canDrop;
      this.itemDescription = itemDescription;
      this.inInventory = false;
  }
  
  public int getWeight(){
      return weight; // method get the weight
  }
  
  public boolean canPickup(){
     return canPickup; // method to check if item can be picked up
  }
  
  public boolean canDrop(){
      return canDrop; // method to check if item can be dropped
  }
  
  public String itemDescription(){
      return itemDescription; // method to get item description
  }
    
  public boolean inInventory(){
      return inInventory; // method to check if item is in inventory
  }
  
  public void putInventory(boolean inInventory){
      this.inInventory = true; // method to update inInventory boolean
  }
}
