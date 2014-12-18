package fr.enac.persistance;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import fr.enac.model.Model;
import fr.enac.model.Personne;

public class PersistParFichierTexte implements DAO {

	@Override
	public void sauver(String ficname, Model<Personne> model) throws DAOException {		
		
		//Java 7 try-with-ressource -> BufferedWriter implements Closeable -> AUTOCLOSE gere aussi le cas null
		try (BufferedWriter bf=new BufferedWriter(new FileWriter(ficname));) 
		{
			bf.write(model.toString());
			bf.newLine();
			/*for (Joueur[] joueurs : grille_) {
				for (Joueur joueur : joueurs) {
					bf.write(joueur.toString() + ";");
				}
			}*/
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			throw new DAOException(e1);
		} catch (IOException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

	@Override
	public void charger(String ficname, Model<Personne> model) throws DAOException {
		
		//Java 7 try-with-ressource -> Scanner implements Closeable -> AUTOCLOSE  gere aussi le cas null
		try(Scanner scan = new Scanner(new FileReader(ficname));) {
			scan.useDelimiter("\n");
			model.getList().clear();
			while(scan.hasNext())
			{
				String ligne = scan.next();
				Scanner scanByLine = new Scanner(ligne);
				scanByLine.useDelimiter(";|\n");
				
				Personne persAajoutePersonne 
					= new Personne(scanByLine.next().trim(),scanByLine.next().trim(),scanByLine.next().trim());
				
				model.setElement(persAajoutePersonne);
				scanByLine.close();
			}
			/*joueurCourant_ =  Enum.valueOf(Joueur.class, scan.next());
			nbCoups_ = Integer.parseInt(scan.next());
			gagnee_ = Boolean.parseBoolean(scan.next());
			for (int i = 0; i < grille_.length; i++) {
				for (int j = 0; j < grille_.length; j++) {
					grille_[i][j] = Enum.valueOf(Joueur.class, scan.next());
				}
			}
			*/
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			throw new DAOException(e1);
		} catch (IOException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}
}
