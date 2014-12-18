package fr.enac.persistance;

import fr.enac.model.Model;
import fr.enac.model.Personne;

public interface DAO {
	
	void sauver(final String ficname, final Model<Personne> model) throws DAOException;
	
	void charger(final String ficname, final Model<Personne> model) throws DAOException;

}
