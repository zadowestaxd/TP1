package es.ucm.tp1.logic.gameobjects;

import es.ucm.tp1.logic.Game;

public class Truck extends Obstacle {
	public static final String SYMBOL= "←";
	
	public Truck(Game game, int x, int y) {
		super(game, x, y);}

	public String toString() {
		return SYMBOL;}

	
	@Override
	public void update() {
		if(x<game.getPlayerX())
			alive=false;
		x--;}
}