package es.ucm.tp1.control.commands;

import java.util.regex.Pattern;

import es.ucm.tp1.exceptions.CommandParseException;
import es.ucm.tp1.exceptions.IncorrectCommandException;
import es.ucm.tp1.logic.Game;

public class CheatCommand extends Command {

	private static final String NAME = "cheat";

	private static final String DETAILS = "Cheat [1..5]";

	private static final String SHORTCUT = "c";

	private static final String HELP = "Removes all elements of last visible column, and adds an Advanced Object";
	
	private static Integer id;
	
	public CheatCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);}

	@Override
	public boolean execute(Game game) {
		game.addCheat(id);
		return true;
	}
	
	public Command parse(String[] commandWords) throws IncorrectCommandException {
		boolean aux = true;

	       if(commandWords[0].length() == 1){
	    	   for (char c : commandWords[0].toCharArray()) {
	    		   if (!Character.isDigit(c)) {
	    			   aux = false;
	    			   break;
	            }
	        }
	        if(aux == false)
	            return null;
	        else{
	            id = Integer.parseInt(commandWords[0]);
	            if(0 < id && id < 6)
	                return this;
	            else
	                return null;
	        }
	        }
	       return null;
	}
}