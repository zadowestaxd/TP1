package es.ucm.tp1.logic.actions;

import es.ucm.tp1.logic.Game;
import es.ucm.tp1.logic.gameobjects.GameObject;

public class WaveAction implements InstantAction{

	@Override
	public void execute(Game game) {
		for (int i = 0; i < game.getRoadWidth(); i++) {
			for (int j = game.getPlayerX()+game.getVisibility(); j >= game.getPlayerX() ; j--) {
                GameObject obj = game.getObjectInPosition(j, i);
                if(obj != null)
                    obj.wave();
			}
		}
	}
}
