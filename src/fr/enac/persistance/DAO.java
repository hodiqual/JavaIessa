package fr.enac.persistance;

import fr.enac.model.Model;

public interface DAO {
	
	void sauver(final String ficname, final Model model) throws DAOException;
	
	void charger(final String ficname, final Model model) throws DAOException;

}
