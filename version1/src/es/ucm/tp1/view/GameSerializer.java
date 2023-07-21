package es.ucm.tp1.view;
import es.ucm.tp1.logic.Game;

public class GameSerializer extends View{
	
	public GameSerializer(Game game) {
		super(game);
		this.game=game;
	}
	
	public String serializeToString() {
		StringBuilder str = new StringBuilder();
		str.append("---- ROAD FIGHTER SERIALIZED ----\n")
			.append("Level: ")
			.append(game.getLevel())
			.append("\nCycles: ")
			.append(game.getCycles())
			.append("\nCoins: ")
			.append(game.getCoinCounter())
			.append("\n")
			.append(printTime())
			.append("\nGame Objects: \n")
			.append(game.playerSerializer())
			.append("\n")
			.append(game.serializer());
		return str.toString();
	}

	public void printSerializer() {
		System.out.println(serializeToString());}
}