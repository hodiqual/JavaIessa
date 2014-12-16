package fr.enac.controller;

import java.beans.PropertyChangeListener;

import javax.swing.event.SwingPropertyChangeSupport;

import fr.enac.model.Model;

public class Controller {
	
	private Model _model;
	
	private SwingPropertyChangeSupport _swingObservable;
	
	public Controller(Model model)
	{
		_model = model;
		_swingObservable = new SwingPropertyChangeSupport(_model, true);
	}
	
	public void ajoutVue(PropertyChangeListener vue)
	{
		_swingObservable.addPropertyChangeListener(vue);
		
	}

}
