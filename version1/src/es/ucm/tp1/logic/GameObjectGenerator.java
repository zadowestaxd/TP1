package es.ucm.tp1.logic;

import es.ucm.tp1.control.Level;
import es.ucm.tp1.logic.actions.ThunderAction;
import es.ucm.tp1.logic.gameobjects.Coin;
import es.ucm.tp1.logic.gameobjects.GameObject;
import es.ucm.tp1.logic.gameobjects.Obstacle;
import es.ucm.tp1.logic.gameobjects.Pedestrian;
import es.ucm.tp1.logic.gameobjects.SuperCoin;
import es.ucm.tp1.logic.gameobjects.Truck;
import es.ucm.tp1.logic.gameobjects.Turbo;
import es.ucm.tp1.logic.gameobjects.Wall;

public class GameObjectGenerator {
	static Game game;
	static SuperCoin supercoin;
	static Coin coin;
	static Obstacle obstacle;
	
	public static void generateRuntimeObjects(Game game) {
		if (game.getLevel().hasAdvancedObjects())
			game.execute(new ThunderAction());
	}
		
	public static void generateGameObjects(Game game, Level level) {
		for(int x = game.getVisibility() /2; x < game.getLength(); x ++) {
			game.tryToAddObject(new Obstacle(game, x, game.getRandomLane()), level.getObstacleFrequency());
			game.tryToAddObject(new Coin(game, x, game.getRandomLane()), level.getCoinFrequency());
			
			if (level.hasAdvancedObjects()) {
				game.tryToAddObject(new Wall(game, x, game.getRandomLane()), level.getAdvancedObjectFrequency());
				game.tryToAddObject(new Turbo(game, x, game.getRandomLane()), level.getAdvancedObjectFrequency());
				
				if (!SuperCoin.present)
					game.tryToAddObject(new SuperCoin(game, x, game.getRandomLane()), level.getAdvancedObjectFrequency());
				game.tryToAddObject(new Truck(game, x, game.getRandomLane()), level.getAdvancedObjectFrequency());
				game.tryToAddObject(new Pedestrian(game, x, 0), level.getAdvancedObjectFrequency());
			}
		}
	}

	public static void forceAdvanceObject(Game game, int id, int x) {
		GameObject o = null;
		switch (id) {
			case 1:
				o = new Wall(game, x, game.getRandomLane());
				break;
			case 2:
				o = new Turbo(game, x, game.getRandomLane());
				break;
			case 3:
				o = new SuperCoin(game, x, game.getRandomLane());
				break;
			case 4:
				o = new Truck(game, x, game.getRandomLane());
				break;
			case 5:
				o = new Pedestrian(game, x, 0);
				break;
		}
		game.forceAddObject(o);
	}
	
	public static void reset() {
		obstacle.reset();
		coin.reset();
	}
}