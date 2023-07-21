package es.ucm.tp1.logic.gameobjects;

import es.ucm.tp1.logic.Game;

public class Wall extends Obstacle {
	public static final String WEAK="░";
	public static final String MEDIUM="▒";
	public static final String HARD="█";
	private int life = 3;
	public static final int reward = 5;

	public Wall(Game game, int x, int y) {
		super(game, x, y);}

	@Override
	public boolean receiveShoot() {
		life--;
		if(life == 0) {
			alive=false;
		game.reward(reward); //TODO QUE LE DE 5 MONEDAS AL PLAYER
		}
		return true;
	}
	
	public String toString() {
		if(life == 3)
			return HARD;
		else if (life == 2)
			return MEDIUM;
		else
			return WEAK;
	}
	

	@Override
	public void onDelete() {
		cont--;
		alive = false;
	}
	
	@Override
	public String serializer() {
		String serial;
		serial = toString()+" ("+this.x+", "+this.y+") "+ life +"\n";
		return serial;
	}
	
}