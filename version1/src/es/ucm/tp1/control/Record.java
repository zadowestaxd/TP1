package es.ucm.tp1.control;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import es.ucm.tp1.exceptions.InputOutputRecordException;
import es.ucm.tp1.logic.Game;

public class Record {
	static Game game;
	private static String RecordLevel;
	private static String[] RecordInfo, RecordFile2= null, aux;
	private static int i = 0, j = 0, cont = 0;
	private static int segundos;
	private File file;
	
	public Record(Game game) {
		this.game = game;
	}
	private void readRecord() throws FileNotFoundException{
	file = new File("record.txt");
	Scanner myFile = new Scanner(file);
	while (myFile.hasNextLine()) {
		myFile.nextLine();
	    cont++;}
		String RecordFile[] = new String[cont];
	Scanner theFile = new Scanner(file);
	while (i < cont) {
		String data = theFile.nextLine();
	    RecordFile[i] = data;
	    i++;
	}
	RecordFile2 = RecordFile;
	myFile.close();
	RecordInfo = RecordFile[j].split("[:]");
	RecordLevel = RecordInfo[0];
	while (!game.getLevel().toString().equals(RecordLevel) && j < i) {
		j++;
		RecordInfo = RecordFile[j].split("[:]");
		RecordLevel = RecordInfo[0];
	}
	segundos = Integer.parseInt(RecordInfo[1]);
}
	
	public void writeRecord() throws InputOutputRecordException{
		try (FileWriter myWriter = new FileWriter("record.txt")){
			i=0;
			while(i<cont) {
				aux = RecordFile2[i].split("[:]");
				if(!aux[0].equals(game.getLevel().toString())) {
					myWriter.write(RecordFile2[i] + "\n");
				}
				else {myWriter.write(game.getLevel().toString() + ":" + (System.currentTimeMillis() - game.GetInitTime())+ "\n");}
			i++;
			}
		}
		catch (IOException e) {
			throw new InputOutputRecordException();
		}
		
	}
	public boolean newRecord() throws FileNotFoundException {
		readRecord();
		double newrecord = (double)((System.currentTimeMillis() - game.GetInitTime()) );
		if((double)segundos > newrecord )
		return true;
	return false;
}
}

