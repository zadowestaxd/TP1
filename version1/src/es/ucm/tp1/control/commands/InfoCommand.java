package es.ucm.tp1.control.commands;

import es.ucm.tp1.exceptions.CommandParseException;
import es.ucm.tp1.logic.Game;

public class InfoCommand extends Command {

	private static final String NAME = "info";

	private static final String DETAILS = "[i]nfo";

	private static final String SHORTCUT = "i";

	private static final String HELP = "prints gameobject info";
	
	private static final String INFO =
		"Available objects:" 
		+ "\n" + "[Car] the racing car" 
		+ "\n" + "[Coin] gives 1 coin to the player"
		+ "\n" + "[Obstacle] hits car"
		+ "\n" + "[GRENADE] Explodes in 3 cycles, harming everyone around"
		+ "\n" + "[WALL] hard obstacle"
		+ "\n" + "[TURBO] pushes the car: 3 columns"
		+ "\n" + "[SUPERCOIN] gives 1000 coins"
		+ "\n" + "[TRUCK] moves towards the player"
		+ "\n" + "[PEDESTRIAN] person crossing the road up and down"
		+ "\n" + "\n";

	public InfoCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);}

	@Override
	public boolean execute(Game game) {
		System.out.print(INFO);
		return false;
	}
	
	@Override
	public Command parse(String[] words) throws CommandParseException {
		if (matchCommandName(words[0])) {
			if (words.length != 1)
				throw new CommandParseException(String.format("[ERROR]: Command %s: %s", NAME, INCORRECT_NUMBER_OF_ARGS_MSG));
			else 
				return this;
		}
		return null;
	}
}