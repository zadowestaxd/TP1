package es.ucm.tp1.view;

import es.ucm.tp1.utils.*;
import es.ucm.tp1.logic.Game;
import es.ucm.tp1.logic.gameobjects.GameObject;
import es.ucm.tp1.logic.actions.ThunderAction;

public class GamePrinter extends View{
	private static final String SPACE = " ";

	private static final String ROAD_BORDER_PATTERN = "═";

	private static final String LANE_DELIMITER_PATTERN = "─";

	private static final int CELL_SIZE = 7;

	private static final int MARGIN_SIZE = 2;

	private String indentedRoadBorder;

	private String indentedLlanesSeparator;

	private String margin;
	
	private static final String GAME_OVER_MSG = "[GAME OVER] "; 
	
	public String newLine; 

	private Game game;
	
	private ThunderAction thunderaction;
	
	public GamePrinter(Game game) {
		super(game);
		this.game = game;
		margin = StringUtils.repeat(SPACE, MARGIN_SIZE);
		
		String roadBorder = ROAD_BORDER_PATTERN + StringUtils.repeat(ROAD_BORDER_PATTERN, (CELL_SIZE + 1) *  game.getVisibility());
		indentedRoadBorder = String.format("%n%s%s%n", margin, roadBorder);

		String laneDelimiter = StringUtils.repeat(LANE_DELIMITER_PATTERN, CELL_SIZE);
		String lanesSeparator = SPACE + StringUtils.repeat(laneDelimiter + SPACE,  game.getVisibility() - 1) + laneDelimiter + SPACE;

		indentedLlanesSeparator = String.format("%n%s%s%n", margin, lanesSeparator);

		newLine =  System.getProperty("line.separator");
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		// Game Status
		str.append(getInfo());
		
		// Paint game

		str.append(indentedRoadBorder);

		String verticalDelimiter = SPACE;

		for (int y = 0; y < game.getRoadWidth(); y++) {
			str.append(this.margin).append(verticalDelimiter);
			for (int x = game.getPlayerX(); x < game.getVisibility()+game.getPlayerX(); x++) {
				str.append(StringUtils.centre(game.positionToString(x, y), CELL_SIZE))
						.append(verticalDelimiter);
			}
			if (y <  game.getRoadWidth() - 1) {
				str.append(this.indentedLlanesSeparator);
			}
		}
		str.append(this.indentedRoadBorder);

		return str.toString();
	}
	
	public String endMessage(int mensaje){
		StringBuilder str = new StringBuilder();
		String s = GAME_OVER_MSG;
		switch (mensaje) {
		case 1:
			str.append(s + "Player leaves the game");
			break;
		case 2:
			if(!game.getActivate())
				str.append(s + "Player wins!");
			else
				str.append(s + "Player wins!");
			break;
		default:
			str.append(s + "Player crashed!");
			break;
		}	
		return str.toString();
	}
	
	public String getInfo() {
		StringBuilder str = new StringBuilder();
		String distancia = String.valueOf(game.distanceTofinish());
		if(game.getLevel().hasAdvancedObjects() && game.getCycles() != 0) {
			System.out.print("Thunder hit position: (" + ThunderAction.ancho + " , " + ThunderAction.largo + ") ");
            thunderaction = new ThunderAction();
			if(!thunderaction.setThunderKill(game).equals(" "))
                System.out.println("-> " + thunderaction.setThunderKill(game));
            else
            	 System.out.print("\n");}
		System.out.println("Distance: " + distancia);
		System.out.println("Coins: " + game.getCoinCounter());
		System.out.println("Cycle: " + game.getCycles());
		System.out.println("Total obstacles: " + GameObject.getObstacles());
		System.out.println("Total coins: " + GameObject.getCoins());
		if(GameObject.isSuperCoinPresent())
		System.out.println("Supercoin is present");
		System.out.println(printTime());
		return str.toString();
	}
}