package fr.enac.vue;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JFrame;

public class Vue extends JFrame implements PropertyChangeListener {
	
	//Quand le model change la vue est notifiee par cette methode.
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		
	}

}
