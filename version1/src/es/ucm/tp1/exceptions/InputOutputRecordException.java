package es.ucm.tp1.exceptions;

public class InputOutputRecordException extends CommandExecuteException{
	
    private static final String MENSAJE_EXCEPCION = "File not found";
    
    public InputOutputRecordException() {
        super(MENSAJE_EXCEPCION);
    }
}