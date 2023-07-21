package es.ucm.tp1.control;

import es.ucm.tp1.exceptions.NotEnoughCoinsException;
import es.ucm.tp1.logic.Game;

public interface Buyable {

	public int cost();
	public default boolean buy(Game game) throws NotEnoughCoinsException{
		int cost = cost();

		if(!game.buy(cost))
			throw new NotEnoughCoinsException("Not enough coins\r");
		return true;
	};
}