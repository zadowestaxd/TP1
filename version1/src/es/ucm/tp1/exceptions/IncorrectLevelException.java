package es.ucm.tp1.exceptions;

public class IncorrectLevelException extends CommandParseException{
	
    private static final String MENSAJE_EXCEPCION = "Level not recognised";
    
    public IncorrectLevelException() {
        super(MENSAJE_EXCEPCION);
    }
}