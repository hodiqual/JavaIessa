package fr.enac.core;


import javax.swing.SwingUtilities;

import fr.enac.controller.Controller;
import fr.enac.model.Model;
import fr.enac.model.Personne;
import fr.enac.persistance.DAOType;
import fr.enac.vue.Vue;

public class MainClass {

	public static void main(String[] args) {

		/*
		 * Garantir que la GUI et les petites taches 
		 * soient gerees par l'Event Dispatch Thread
		 */
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Model model = new Model<Personne>();
				Controller controller = new Controller(model,DAOType.TEXTE);

				Vue vue = new Vue("Annuaire Enac", controller);
				
				//Vue vue2 = new Vue("JUSTE POUR LE FUN", controller);
			}
		});
	}
}
