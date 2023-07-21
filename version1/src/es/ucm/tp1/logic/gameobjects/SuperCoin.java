package es.ucm.tp1.logic.gameobjects;

import es.ucm.tp1.logic.Game;

public class SuperCoin extends Coin {
	public static final String SYMBOL = "$";
	public static int SUPERCOIN =1000;
	public static boolean present=false;
	
	public SuperCoin(Game game, int x, int y) {
		super(game, x, y);}

	
	public String toString() {
		return SYMBOL;}
	
	@Override
	public boolean receiveCollision(Player player) {
		player.AddSuperCoin();
		alive =false;
		present=false;
		return false;
	}

	@Override
	public void onEnter() {
		alive = true;
		present=true;
	}

	@Override
	public void onDelete() {
		alive=false;
		present=false;
	}
	
	public void reset() {
		alive = false;}

    public static boolean isPresent() {
    	return present;}
    
	public boolean isAlive() {
		return alive;}
	
}