package fr.enac.persistance;

import java.io.FileNotFoundException;
import java.io.IOException;

public class DAOException extends Exception {

	public DAOException(FileNotFoundException e1) {
		super(e1.getMessage());
	}

	public DAOException(IOException e) {
		super(e.getMessage());
	}
	
}
