package es.ucm.tp1.control.commands;

import es.ucm.tp1.control.Buyable;
import es.ucm.tp1.exceptions.CommandExecuteException;
import es.ucm.tp1.exceptions.CommandParseException;
import es.ucm.tp1.exceptions.InvalidPositionException;
import es.ucm.tp1.exceptions.NotEnoughCoinsException;
import es.ucm.tp1.logic.Game;
import es.ucm.tp1.logic.gameobjects.Grenade;

public class GrenadeCommand extends Command implements Buyable{

	private static final String NAME = "grenade";

	private static final String DETAILS = "[g]renade <x> <y>";

	private static final String SHORTCUT = "g";

	private static final String HELP = "add a grenade in position x, y";
	
	private static final String FAILED_MSG = "Fail to add grenade";
	
	private static final String FAILED_POSITION = "Incorrect number of arguments for grenade command: [g]renade <x> <y>";
	
	private static final int COST = 3;
	
	public static int newX, newY;
	
	public GrenadeCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		try {
			if (buy(game)) {
				newX += game.getPlayerX();
				game.addObject(new Grenade(game, newX, newY));
				game.activateWait();			
				game.update();
				return true;
			}
		}
		catch (NotEnoughCoinsException e) {
			System.out.println(e.getMessage());
			throw new CommandExecuteException(String.format("[ERROR]: %s", FAILED_MSG), e);
			}
		catch (InvalidPositionException e) {
			System.out.println(e.getMessage());
			throw new CommandExecuteException(String.format("[ERROR]: %s", FAILED_MSG), e);
		}
		return false;
	}
	
	@Override
	public Command parse(String[] commandWords) throws CommandParseException  {
		if(matchCommandName(commandWords[0])) {
			if(commandWords.length == 3) {
				newX = Integer.parseInt(commandWords[1]);
		        newY = Integer.parseInt(commandWords[2]);
		        return this;
		      }
			else
				throw new CommandParseException(String.format("[ERROR]: %s", FAILED_POSITION));
	    }
	    else 
	    	return null;
	}

	@Override
	public int cost() {
		return COST;}
}