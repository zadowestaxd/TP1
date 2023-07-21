package es.ucm.tp1.logic.gameobjects;

import es.ucm.tp1.logic.Collider;
import es.ucm.tp1.logic.Game;

public class Player extends GameObject {
	public static final int INIT_COINS=0;
	public static final int STEP =1;
	private boolean alive = true;
	private Integer coinCounter=0;
	private static Game game;
	
	public Player(Game game, int x, int y) {
		super(game, x, y);
		this.x=x;
		this.y=y;
		}
	
	public void initPos() {
			x = 0;
			y = game.getRoadWidth()/ 2; 
	}

	public boolean isInPosition(int x, int y) {
		return (this.x == x && this.y == y);}
	
	public String statusToString() {
		if (alive == true) 
			return ">";
		else
			return "@";
	}
	
	public void update(int mov, Game width) {
		if(mov==-1) goUp(width);
		else goDown();
	} 
	
	public void goUp(Game width) {
		if(y + STEP != width.getRoadWidth())
			y += STEP;
	}
	
	public void goDown() {
		if(y - STEP >= 0)
			y -= STEP;
	}
	
	public Boolean doPlayerCollision(Game game) {		
		Collider other = game.getObjectInPosition(x, y);
		
		if (other != null)
			return other.receiveCollision (this);
		return false;
	}
	
	public int getCoin() {
		return coinCounter;}
	
	public void reset() {
		coinCounter = 0;
	}
	
	public boolean doCollision() {
		Collider other = game.getObjectInPosition(this.x, this.y);
		if (other != null)
			return other.receiveCollision (this);
		return false;
	}
	
	public boolean isAlive() {
		return alive;}

	@Override
	public boolean receiveCollision(Player player) {
		return false;}

	@Override
	public void onEnter() {	
	}

	@Override
	public void update() {
		x++;
		//cycles++;
	}

	@Override
	public void onDelete() {
	}

	public void AddCoin() {
		coinCounter++;}

	public void SetNotAlive() {
		alive=false;}
	
	public int getX() {
		return x;}

	@Override
	public boolean receiveShoot() {
		return false;}

	@Override
	public boolean receiveExplosion() {
		return false;}

	public void AddSuperCoin() {
		coinCounter += 1000;}

	public void doTurbo(int step) {
		this.x+=step;
	}

	public void resetCoin() {
		coinCounter=0;}

	public void lessCoin() {
		coinCounter--;}

	public void less() {
		x--;}

	@Override
	public String serializer() {
		// TODO Auto-generated method stub
		return null;
	}
}