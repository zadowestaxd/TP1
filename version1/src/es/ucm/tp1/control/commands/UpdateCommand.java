package es.ucm.tp1.control.commands;

import es.ucm.tp1.exceptions.CommandParseException;
import es.ucm.tp1.logic.Game;

public class UpdateCommand extends Command {

	private static final String NAME = "update";

	private static final String DETAILS = "[n]one | []";

	private static final String SHORTCUT = "n";

	private static final String HELP = "update";

	public UpdateCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);}

	@Override
	public boolean execute(Game game) {
		if(!game.firstCollision())
			game.update();
		return true;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		if ("".equalsIgnoreCase(commandWords[0]))
			return this;
		if(commandWords[0].equals(SHORTCUT)) {
			if (commandWords.length != 1)
				throw new CommandParseException(String.format("[ERROR]: Command %s: %s", NAME, INCORRECT_NUMBER_OF_ARGS_MSG));
			else 
				return this;
		}
		return null;
	}
}