package es.ucm.tp1.logic.actions;

import es.ucm.tp1.logic.Game;
import es.ucm.tp1.logic.gameobjects.GameObject;

public class ShootAction implements InstantAction {

	@Override
	public void execute(Game game) {
		boolean ok=false;
		
		for (int i = game.getPlayerX(); i < game.getPlayerX() + game.getVisibility() && !ok; i++) {
			GameObject obj = game.getObjectInPosition(i, game.getPlayerY());
			if(obj != null)
				if(obj.receiveShoot()!=ok)
					ok=true;
		}
	}
}