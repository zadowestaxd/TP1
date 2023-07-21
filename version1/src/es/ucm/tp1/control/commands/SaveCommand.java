package es.ucm.tp1.control.commands;

import java.io.FileWriter;
import java.io.IOException;

import es.ucm.tp1.exceptions.CommandParseException;
import es.ucm.tp1.logic.Game;
import es.ucm.tp1.view.GameSerializer;

public class SaveCommand extends Command{
	private static final String NAME = "save";

	private static final String DETAILS = "sa[v]e <filename>";

	private static final String SHORTCUT = "v";

	private static final String HELP = "Save the state of the game to a file.";
	
	private static final String FAILED_ARGUMENTS = "Incorrect number of arguments for reset command: ";
	
	private String word;
	
	public SaveCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);}

	@Override
	public boolean execute(Game game) throws IOException{
		GameSerializer gameserializer = new GameSerializer(game);
		try (FileWriter myWriter = new FileWriter(word + ".txt")){
			myWriter.write(gameserializer.serializeToString());
			System.out.println("Game successfully saved to file " + word + ".txt");
		}
		catch (IOException e) {
			e.printStackTrace();
			System.out.println("File not found");
		}
		return false;}
	
	@Override
	public Command parse(String[] words) throws CommandParseException {
		if (matchCommandName(words[0])) {
			if (words.length == 2) {
				word = words[1];
				return this;}
			else
				throw new CommandParseException(String.format("[ERROR]: %s%s", FAILED_ARGUMENTS, DETAILS));
		}
		return null;
	}
}