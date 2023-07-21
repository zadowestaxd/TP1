package es.ucm.tp1.exceptions;

public class InvalidPositionException extends CommandExecuteException{
	
    private static final String MENSAJE_EXCEPCION = "Invalid position.";
    
    public InvalidPositionException() {
        super(MENSAJE_EXCEPCION);
    }
}