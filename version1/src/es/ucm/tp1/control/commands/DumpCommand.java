package es.ucm.tp1.control.commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import es.ucm.tp1.exceptions.CommandExecuteException; //creo que hay que meterlo de alguna manera dentro del execute
import es.ucm.tp1.exceptions.CommandParseException;
import es.ucm.tp1.logic.Game;

public class DumpCommand extends Command{
	
	private static final String NAME = "dump";

	private static final String DETAILS = "[d]ump filename";

	private static final String SHORTCUT = "d";

	private static final String HELP = "Shows the content of a saved file";
	
	private static String dumpFile;
	
	public DumpCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);}

	@Override
	public boolean execute(Game game) throws FileNotFoundException {
		File file = new File(dumpFile + ".txt");
	    Scanner myFile = new Scanner(file);
		while (myFile.hasNextLine()) {
		    String data = myFile.nextLine();
		    System.out.println(data);
		  }	
		myFile.close();
		return false;
	}

	@Override
	public Command parse(String[] words) throws CommandParseException{
		if (matchCommandName(words[0])) {
			if (words.length == 2) {
				dumpFile = words[1];
				return this;}
			else 
				throw new CommandParseException(String.format("[ERROR]: Command %s: %s", NAME, INCORRECT_NUMBER_OF_ARGS_MSG));
		}
		return null;
	}
}