package es.ucm.tp1.logic.actions;

import es.ucm.tp1.logic.Game;
import es.ucm.tp1.logic.gameobjects.GameObject;
import es.ucm.tp1.control.commands.GrenadeCommand;

public class ExplotionAction implements InstantAction{

	@Override
	public void execute(Game game) {
        GameObject ok=null;
		for(int i = GrenadeCommand.newX -1; i<=GrenadeCommand.newX+1;i++) {
            for(int j =GrenadeCommand.newY-1;j<=GrenadeCommand.newY+1;j++) {
	            ok= game.getObjectInPosition(i, j);
	            if(ok!=null)
	            	ok.receiveExplosion();
            }
        }
	}
}
