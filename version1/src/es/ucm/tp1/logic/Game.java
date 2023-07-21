package es.ucm.tp1.logic;

import java.text.DecimalFormat;
import java.util.Random;

import es.ucm.tp1.control.Level;
import es.ucm.tp1.exceptions.IncorrectLevelException;
import es.ucm.tp1.exceptions.InvalidPositionException;
import es.ucm.tp1.logic.actions.InstantAction;
import es.ucm.tp1.logic.gameobjects.GameObject;
import es.ucm.tp1.logic.gameobjects.Player;

public class Game {
	private Level level;
	private GameObjectContainer container;
	private Long seed;
	private Player player;
	private int cycles = 0, thunderAncho, thunderLargo, xGrenade, yGrenade;
	private Boolean activate = false, exit=false, wait = false, shooted = false;
	private long initTime = 0;
	private Random random;
	private String thunderKill = " ";
		
	public Game(long seed, Level level) throws IncorrectLevelException {
		this.seed = seed;
		this.level = level;
		reset();
	}
	
	public void update() {
		GameObjectGenerator.generateRuntimeObjects(this);
		if(player.isAlive() && !wait && !shooted)
			player.update();
		player.doPlayerCollision(this);
		if(!wait)
			container.update();
		if (cycles == 0)
			initTime = System.currentTimeMillis();
		cycles++;
		eraseContainer();
		disableWait();
		disableShooted();
	}
	
	public void reset() throws IncorrectLevelException {
		random = new Random(seed);
		if(level == null)
			throw new IncorrectLevelException();
		this.player = new Player(this, 0, this.level.getWidth()/2);
		player.reset();
		cycles = 0;	
		this.container = new GameObjectContainer();
		GameObjectGenerator.generateGameObjects(this, level);
	}
	
	public void reset(Long newSeed, Level newLevel) throws IncorrectLevelException {
		seed =newSeed;
		level=newLevel;
		reset();
	}
	
	public String positionToString(int x, int y) {
		StringBuilder str = new StringBuilder();
		
		if (player.isInPosition(x, y)) {
			str.append(player.statusToString())
			.append(" ")
			.append(container.positionToString(x, y)); }
		else 
			if(!player.isInPosition(x, y))
				str.append(container.positionToString(x, y));
		if (level.getLength() == x)
			str.append("¦");
		return str.toString();
	}
	
	public void tryToAddObject(GameObject gameobject, double objectFrequency) {
		if(container.isinPosition(gameobject.getX(), gameobject.getY())==null && randomNumber() <= objectFrequency) {
			container.Add(gameobject);
			container.onEnter(gameobject);
		}
	}
	
	public void addObject(GameObject gameobject) throws InvalidPositionException {
		if ((gameobject.getX() >= getPlayerX()) && (gameobject.getX() < (getPlayerX() + getVisibility()))) {
			if ((gameobject.getY() >= 0) && (gameobject.getY() < getRoadWidth()))
				if(container.isinPosition(gameobject.getX(), gameobject.getY()) == null) {
					container.Add(gameobject);
					container.onEnter(gameobject);
			}
		}
		else 
			throw new InvalidPositionException();
	}
	
	public boolean isFinished() {
		boolean exit =false;
		if(!player.isAlive()) exit = true;
		else if(distanceTofinish() == 0) exit = true;
		else if(this.exit) exit= true;
			return exit;		
	}
	
	public int PrintFinish() {
		int finalmes=0;
		if(exit) {finalmes= 1;}
		else if(distanceTofinish() == 0) {finalmes=2;}
		return finalmes;
	}
	
	public boolean buy(int cost) {
		if(player.getCoin()>=cost) {
			for(int i=0;i<cost;i++) {
				player.lessCoin();
			}
			return true;
		}
		return false;
	}
	
	public void addCheat(Integer id) {
		boolean ok = false;
		for (int i = 0; i < getRoadWidth() && !ok; i++)
			if (getObjectInPosition(getVisibility() + player.getX() - 1, i) == null) {
					GameObjectGenerator.forceAdvanceObject(this, id, getVisibility() + player.getX() - 1);
					ok = true;
			}	
	}
	
	public void toggleTest() {
		activate = true;}
	
	public double randomNumber() {
		return random.nextDouble();}
	
	public void goUp() {
		player.update(1, this);}
	
	public void goDown() {
		player.update(-1, this);}
	
	public boolean getActivate() {
		return activate;}
	
	public int getVisibility() {
		return level.getVisibility();}
	
	public int getRoadWidth() {
		return level.getWidth();}
	
	public int getLength() {
		return level.getLength();}
	
	public int getPlayerX() {
		return player.getX();}
	
	public int getPlayerY() {
		return player.getY();}

	public double getCoinFrequency() {
		return level.getCoinFrequency();}
	
	public double getObstacleFrequency() {
		return level.getObstacleFrequency();}

	public int distanceTofinish() {
		return (getLength()-player.getX());}
	
	public String getTime() {
		DecimalFormat df = new DecimalFormat("#.##");
		return df.format((double) ((System.currentTimeMillis() - initTime) / 1000.));}

	public int getRandomLane() {
		return (int) (randomNumber() * getRoadWidth());}

	public GameObject getObjectInPosition(int x, int y) {
		return container.isinPosition(x, y);}

	public void setExit() {
		exit=true;}
	
	public int getCoinCounter() {
		return player.getCoin();}
	
	public int getCycles() {
		return cycles;}
	
	public long GetInitTime() {
		return initTime;}
	
	public void forceAddObject(GameObject o) { 
		container.Add(o);}
	
	public Level getLevel() {
		return level;}
	
	public void execute(InstantAction action) {
		action.execute(this);}
	
	public boolean firstCollision() {
		return player.doPlayerCollision(this);}
	
	public void eraseContainer() {
		container.erase();}
	
	public void loseCoins() {
		player.resetCoin();	}
	
	public void clearCommand() {
		container.removeAllObjects();}

	public void activateWait() {
        wait = true;}
	
    public void disableWait() {
    	wait = false;}
    
    public void activateShooted() {
        shooted = true;}
    
    public void disableShooted() {
    	shooted = false;}

	public String serializer() {
		return container.serializer();
	}

	public String playerSerializer() {
		String serializer = player.statusToString() + " (" + getPlayerX() + ", "+ getPlayerY()+")";
		return serializer;
	}

	public void reward(int j) {
		for(int i = 0; i < j; i++)
		player.AddCoin();
		
	}
}