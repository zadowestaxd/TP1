package es.ucm.tp1.logic.gameobjects;

import es.ucm.tp1.logic.Game;
import es.ucm.tp1.logic.actions.ExplotionAction;

public class Grenade extends GameObject {
	public static final String SYMBOL = "ð";
	private int countdown = 3;
	
	public Grenade(Game game, int x, int y) {
		super(game, x, y);}
	
	public String toString() {
		return SYMBOL + "[" + countdown + "]";}

	@Override
	public boolean receiveCollision(Player player) {
		return false;}

	@Override
	public boolean receiveShoot() {
		return false;}

	@Override
	public void onEnter() {
		alive = true;}

	@Override
	public void update() {
		countdown--;
		if (countdown == 0) {
			game.execute(new ExplotionAction());
		alive =false;
		}
	}

	@Override
	public void onDelete() {
		alive = false;
	}

	@Override
	public boolean receiveExplosion() {
		return false;
	}

	@Override
	public String serializer() {
		String serial;
		serial = toString()+" ("+this.x+", "+this.y+") "+countdown+"\n";
		return serial;
	}
	
}