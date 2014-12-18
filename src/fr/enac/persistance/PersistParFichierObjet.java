package fr.enac.persistance;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import fr.enac.model.Model;
import fr.enac.model.Personne;

public class PersistParFichierObjet implements DAO {

	@Override
	public void sauver(String ficname, Model<Personne> model) throws DAOException {		
		
		//Java 7 try-with-ressource -> ObjectOutputStream implements Closeable -> AUTOCLOSE gere aussi le cas null
		try ( ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ficname)) )
		{
		   oos.writeObject(model.getList());
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			throw new DAOException(e1);
		} catch (IOException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void charger(String ficname, Model<Personne> model) throws DAOException {
		
		//Java 7 try-with-ressource -> ObjectOutputStream implements Closeable -> AUTOCLOSE gere aussi le cas null
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ficname))){
		   
		   Object readObject = ois.readObject();
		   if (readObject instanceof ArrayList<?>) {
			model.setList( (ArrayList<Personne>) readObject );		
		   }
		} catch (FileNotFoundException ex) {
			System.out.println( "FileNotFoundException " + ex.toString());
			throw new DAOException(ex);
		} catch (IOException ex) {
		    ex.printStackTrace();
		    
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
