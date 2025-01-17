/**
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * Maryam Marei
 * K21192007
 */

public class CommandWords
{
    // a constant array that holds all valid command words
    private static final String[] validCommands = {
    "go", "quit", "help", "back" ,"look","inspect", "interact", "take", "drop", "inventory", 
    "give", "x", "map", "2102",  
    };

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords(){
    // nothing to do here.        
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if it is, false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        for(int i = 0; i < validCommands.length; i++) {
            if(validCommands[i].equals(aString))
                return true;
        }
        // if we get here, the string was not found in the commands
        return false;
    }

    /**
     * Print all valid commands to System.out.
     */
    public void showAll() 
    {
        for(int i = 0; i < validCommands.length - 3; i++){
            System.out.print(validCommands[i] + "  "); 
        }
        System.out.println();
        //modified so that last 3 commands are hidden
    }
}
