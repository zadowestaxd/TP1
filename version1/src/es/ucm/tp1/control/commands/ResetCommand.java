package es.ucm.tp1.control.commands;

import es.ucm.tp1.control.Level;
import es.ucm.tp1.exceptions.CommandExecuteException;
import es.ucm.tp1.exceptions.CommandParseException;
import es.ucm.tp1.exceptions.IncorrectLevelException;
import es.ucm.tp1.logic.Game;

public class ResetCommand extends Command {
	
	private static final String NAME = "reset";

	private static final String DETAILS = "[r]eset [<level> <seed>]";

	private static final String SHORTCUT = "r";

	private static final String HELP = "reset game";
	
	private static final String FAILED_ARGUMENTS = "Incorrect number of arguments for reset command: ";
	
	private Level newLevel;
	private Long newSeed;
	
	public ResetCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);}
	
	@Override
	public boolean execute(Game game) throws CommandExecuteException, IncorrectLevelException {
		if (newSeed == null && newLevel == null)
			game.reset();
		else {
			try {
				game.reset(newSeed, newLevel);}
			catch (IncorrectLevelException e) {
				throw new CommandExecuteException(String.format("[ERROR]: %s", e.getMessage()), e);}
			}
		return true;
	}
	
	@Override
	public Command parse(String[] commandWords) throws CommandParseException  {
		if(matchCommandName(commandWords[0])) {
			if(commandWords.length == 3) {
			    newLevel = Level.valueOfIgnoreCase(commandWords[1]);
			    newSeed = Long.parseLong(commandWords[2]);
			    return this;
			  }
			else
				throw new CommandParseException(String.format("[ERROR]: %s%s", FAILED_ARGUMENTS, DETAILS));
		}
		return null;
	}
}