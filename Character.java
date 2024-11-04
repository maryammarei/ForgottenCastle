import java.util.HashMap;

/**
 * Write a description of class Character here.
 *
 * Maryam Marei
 * K21192007
 */
public class Character
{
    private HashMap<String, Character> characters;
    private String charDescription;
    private String speechOne;
    private String speechTwo;
  
    /** Constructor for the Character class
     * Initializes the character with their description and speech lines
     *
     * @param charDescription Description of character
     * @param speechOne First speech line of character
     * @param speechTwo Second speech line of the character
     */
    public Character(String charDescription, String speechOne, String speechTwo){
        this.charDescription = charDescription;
        this.speechOne = speechOne;
        this.speechTwo = speechTwo;
    }
    
    /**
     * Gets the description of character
     *
     * @return The description of character
     */
    public String getCharDescription(){
        return charDescription;
    }
    
    /**
     * Gets the first speech line of character
     *
     * @return The first speech line of character
     */
    public String getSpeechOne(){
        return speechOne;
    }
    
    /**
     * Gets the second speech line of character
     *
     * @return The second speech line of character
     */
    public String getSpeechTwo(){
        return speechTwo;
    }
}
