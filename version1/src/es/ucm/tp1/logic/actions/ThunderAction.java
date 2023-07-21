package es.ucm.tp1.logic.actions;

import java.util.Random;

import es.ucm.tp1.logic.Game;
import es.ucm.tp1.logic.gameobjects.GameObject;

public class ThunderAction implements InstantAction{
	private Random random;
	public static int ancho, largo;
	private int cont = 0;
	
	@Override
	public void execute(Game game) {
        random = new Random();
        ancho = game.getRandomLane();
        largo = random.nextInt(game.getVisibility());
    }
	
	public String setThunderKill(Game game) {
		GameObject obj = game.getObjectInPosition(game.getPlayerX() + largo, ancho);
		if(obj != null) {
			if(cont==1) {
            obj.suicide();
            cont = 0;
			}
			else cont++;
            return obj.toString();}
        
        else return " ";
	}
}