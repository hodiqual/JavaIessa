package fr.enac.controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;
import javax.swing.event.SwingPropertyChangeSupport;

import fr.enac.model.Model;
import fr.enac.model.Personne;
import fr.enac.persistance.DAO;
import fr.enac.persistance.DAOType;
import fr.enac.persistance.PersistParFichierObjet;
import fr.enac.persistance.PersistParFichierTexte;

/**
 * 
 * @author hodiqual
 * Controle les données parvenu de la vue avant de les soumettre au model
 * Gere egalement la notification des changements du modele en garantissant que 
 * les longues taches soient faites hors du Event Dispatch Thread pour que la GUI ne freeze pas
 * 
 */
public class Controller {
	
	private Model<Personne> _model;
	
	//Permet de notifier la vue en garantissant que cela soit dans l'Event Dispatch Manager
	private SwingPropertyChangeSupport _swingObservable;
	
	DAOType _dao ; 

	public Controller(Model<Personne> model, DAOType dao)
	{
		_model = model;
		_swingObservable = new SwingPropertyChangeSupport(_model, true);
		_dao = dao;
	}
	
	public void ajoutVue(PropertyChangeListener vue)
	{
		_swingObservable.addPropertyChangeListener(vue);
		
		//rafraichir la vue avec les donnees du modèle
		ModelEvent evt = ModelEvent.INITIALISATION;		
		vue.propertyChange(new PropertyChangeEvent(_model, evt.toString(), null, _model));
	}
	
	private DAO buildDAO()
	{
		switch (_dao) {
			case BINAIRE:
				return new PersistParFichierObjet();
			case TEXTE:
				return new PersistParFichierTexte();
		}
		return null;
	}
	
	public void charger(String ficname)
	{
		//Controle de ficname
		if(ficname == null || ficname.equals(""))
		{
			ModelEvent evtfin = ModelEvent.CHARGER_RATER;	
			_swingObservable.firePropertyChange(evtfin.toString(), null, "Le nom du fichier n'est pas renseigné");	
			return;
		}
		
		
		//Tache possiblement longue donc a faire dans un thread different de l'EDT 
		SwingWorker sw = new SwingWorker<Integer, Integer>(){
		      protected Integer doInBackground() throws Exception {
		        	  buildDAO().charger(ficname, _model);
		        return _model.size();
		      }
		      
		      //process & pusblish pour la gestion des resultats intermediaires
		         
		      public void done(){
		    	 try {
					int nbPersChargee = get();
			    	//rafraichir la vue avec les donnees du modèle
			    	  ModelEvent evt = ModelEvent.INITIALISATION;	
					_swingObservable.firePropertyChange(new PropertyChangeEvent(_model, evt.toString(), null, _model));
			    	//notifier la fin du chargement
			    	 ModelEvent evtfin = ModelEvent.CHARGER;	
					_swingObservable.firePropertyChange(evtfin.toString(), 0, nbPersChargee);
				} catch (ExecutionException | InterruptedException e) {
					//Cas ou le doInBackground a lancé une exception ou a ete interrompu
					e.printStackTrace();
			    	 ModelEvent evtfin = ModelEvent.CHARGER_RATER;	
					_swingObservable.firePropertyChange(evtfin.toString(), null, e.getCause().getMessage());
				}
		      }         
		    };
		    //On lance le SwingWorker
		    sw.execute();
	}
	
	public void sauver(String ficname)
	{
		//Controle de ficname
		if(ficname == null || ficname.equals(""))
		{
			ModelEvent evtfin = ModelEvent.SAUVER_RATER;	
			_swingObservable.firePropertyChange(evtfin.toString(), null, "Le nom du fichier n'est pas renseigné");	
			return;
		}
		
		//Tache possiblement longue donc a faire dans un thread different de l'EDT 
		SwingWorker sw = new SwingWorker<Integer, Integer>(){
		      protected Integer doInBackground() throws Exception {
		        	  buildDAO().charger(ficname, _model);
		        return _model.size();
		      }
		      
		      //process & pusblish pour la gestion des resultats intermediaires
		         
		      public void done(){
		    	 try {
					int nbPersChargee = get();
			    	//rafraichir la vue avec les donnees du modèle
			    	  ModelEvent evt = ModelEvent.INITIALISATION;	
					_swingObservable.firePropertyChange(new PropertyChangeEvent(_model, evt.toString(), null, _model));
			    	//notifier la fin du chargement
			    	 ModelEvent evtfin = ModelEvent.CHARGER;	
					_swingObservable.firePropertyChange(evtfin.toString(), 0, nbPersChargee);
				} catch (ExecutionException | InterruptedException e) {
					//Cas ou le doInBackground a lancé une exception ou a ete interrompu
					e.printStackTrace();
			    	 ModelEvent evtfin = ModelEvent.CHARGER_RATER;	
					_swingObservable.firePropertyChange(evtfin.toString(), null, e.getCause().getMessage());
				}
		      }         
		    };
		    //On lance le SwingWorker
		    sw.execute();
		
	}
	

}
