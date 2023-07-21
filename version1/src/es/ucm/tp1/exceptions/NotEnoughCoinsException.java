package es.ucm.tp1.exceptions;

public class NotEnoughCoinsException extends CommandExecuteException {
	    
    public NotEnoughCoinsException(String string) {
        super(string);
    }
}