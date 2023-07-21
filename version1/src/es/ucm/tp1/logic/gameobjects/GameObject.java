package es.ucm.tp1.logic.gameobjects;

import es.ucm.tp1.logic.Collider;
import es.ucm.tp1.logic.Game;

public abstract class GameObject implements Collider {
	protected int x, y;
	protected Game game;
	Coin coin;
	Obstacle obstacle;
	protected String symbol;
	protected boolean alive=true;
	
	public GameObject(Game game, int x, int y) {
		this.x = x;
		this.y = y;
		this.game = game;
	}
	
	public static int getCoins() {
		return Coin.cont;}
	
	public static int getObstacles() {
		return Obstacle.cont;}
	
	public static boolean isSuperCoinPresent() {
		return SuperCoin.present;}
	
	protected String getSymbol() {
		return symbol;}

	@Override
	public String toString() {
		if (this.isAlive())
			return getSymbol();
		return "";
	}

	public boolean isInPosition(int x, int y) {
		return this.x == x && this.y == y;}

	public int getX() {
		return x;}

	public int getY() {
		return y;}
	
	public boolean isAlive() {
		return alive;
	}
	public boolean ObstacleisAlive() {
		return obstacle.isAlive();
	}
	public boolean CoinisAlive() {
		return coin.isAlive();
	}
	
	public void suicide() {
		this.alive=false;
	}
	public abstract void onEnter();

	public abstract void update();

	public abstract void onDelete();

	public void wave() {
		x++;
	}

	public abstract String serializer();
	
}