package es.ucm.tp1.logic;


import java.util.ArrayList;
import java.util.List;

import es.ucm.tp1.logic.gameobjects.GameObject;

public class GameObjectContainer {
	GameObject gameobject;
	private List<GameObject> gameobjects;
	
	public GameObjectContainer() {
		gameobjects = new ArrayList<>();}
	
	public void removeAllObjects() {
		for (GameObject gameobject_1 : gameobjects)
			gameobject_1.suicide();
	}
	
	public void Add(GameObject objeto) {
		gameobjects.add(objeto);}
	
	public GameObject isinPosition(int x, int y) {
		boolean test = false;
		GameObject go = null;
		int i = 0;

		while(i < gameobjects.size() && !test) {
			go = gameobjects.get(i);
			if(gameobjects.get(i).isAlive())
				test = go.isInPosition(x, y);
			i++;
		}
		if (!test) return null;
		return go;
	}

	public void erase() {
		List<GameObject> aux;
		aux = new ArrayList<>();
		
		for(GameObject gameobject_1 : gameobjects) {
			if(gameobject_1.isAlive()) 
				aux.add(gameobject_1);
			else
				gameobject_1.onDelete();
		}
		gameobjects = aux;
	}
	
	public int getpositionY() {
		return 0;}
	
	public void onEnter(GameObject gameobject) {
		gameobject.onEnter();}
	
	public void update() {
		for(GameObject gameobject_1 : gameobjects)
			gameobject_1.update();		
	}
	
	public Object positionToString(int x, int y) {
		StringBuilder str = new StringBuilder();

		for (GameObject gameobject_1 : gameobjects)
			if (gameobject_1.isInPosition(x, y) == true)
				str.append(gameobject_1.toString())
					.append(" ");
		return str;
	}

	public int size() {
		return gameobjects.size();
	}

	public String serializer() {
		StringBuilder str = new StringBuilder();
		for(int i = 0; i < gameobjects.size();i++)
		str.append(gameobjects.get(i).serializer());
		return str.toString();
	}
	
}