package fr.enac.core;

import javax.swing.SwingUtilities;

import fr.enac.controller.Controller;
import fr.enac.model.Model;
import fr.enac.vue.Vue;

public class MainClass {

	public static void main(String[] args) {
		
		/*
		 * S'assurer que la GUI et les petites taches 
		 * soient gerees par l'Event Dispatch Thread
		 */
		SwingUtilities.invokeLater(new Runnable() {
	        @Override
	    public void run() {
	        Model model = new Model();
	        Controller controller = new Controller(model);

	        Vue vue = new Vue();
	        controller.ajoutVue(vue);
	
	        
	    }
	});

	}
	
}
