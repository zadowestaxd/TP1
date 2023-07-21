package es.ucm.tp1.view;

import es.ucm.tp1.logic.Game;
import java.text.DecimalFormat;

public abstract class View {
	
	static Game game;
	static DecimalFormat df = new DecimalFormat("#.##");
	
	public View(Game game) {
		this.game=game;
	}

	public static String printTime() {
		String str;
		if (!game.getActivate()) {
			if (game.getCycles() == 0)
				return "Elapsed time: 0.00 s";
			else
				return ("Elapsed time: " + df.format((double) ((System.currentTimeMillis() - game.GetInitTime()) / 1000.)) + " s");
		}
		return "";
	}
}