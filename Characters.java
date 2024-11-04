import java.util.HashMap;

/**
 * Represents characters in the game
 *
 * Maryam Marei
 * K21192007
 */
public class Characters
{
   private HashMap<String, Character> characters;
    
   // initialises HashMap to store characters
    public Characters(){
       characters = new HashMap<>();
   }
   
   /**
    * Adds a character
    * @param name The name of the character
    * @param character The character to be added 
    */
    public void addCharacter(String name, Character character){
       characters.put(name, character);
   }
   
   /**
     * Gets the HashMap containing characters.
     *
     * @return The HashMap representing characters
     */
   public HashMap<String, Character> getCharactersHashMap(){
       return characters;
   }
}
