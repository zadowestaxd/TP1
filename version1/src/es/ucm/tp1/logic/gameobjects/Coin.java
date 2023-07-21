package es.ucm.tp1.logic.gameobjects;

import es.ucm.tp1.logic.Game;

public class Coin extends GameObject {
	public static final String SYMBOL = "¢";
	public static int cont=0;
	
	public Coin(Game game, int x, int y) {
		super(game, x, y);}
	
	public String toString() {
		return SYMBOL;}
	
	public boolean isAlive() {
		return alive;}
	
	@Override
	public boolean receiveCollision(Player player) {
		player.AddCoin();
		alive=false;
		return false;
	}

	@Override
	public void onEnter() {
		cont++;}

	@Override
	public void onDelete() {
		cont--;
		alive = false;
	}

	public void reset() {
		cont=0;
	}

	@Override
	public void update() {
		if(x < game.getPlayerX())
			alive = false;
	}

	@Override
	public boolean receiveShoot() {
		return false;}

	@Override
	public boolean receiveExplosion() {
		return false;}
	
	public void setAlive() {
		alive=false;
	}

	@Override
	public String serializer() {
		String serial;
		serial = toString()+" ("+this.x+", "+this.y+")"+"\n";
		return serial;
	}
	
}