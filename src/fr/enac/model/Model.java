package fr.enac.model;

import java.util.ArrayList;

public class Model<T> {

	/**
	 * attributs de la classe
	 */
	private ArrayList<T> _liste;
	
	/**
	 * constructeur sans paramétre : liste vide
	 *
	 */
	public Model() {
		_liste=new ArrayList<T>();
	}
	
	/**ajoute un élément de type T
	 * 
	 * @param p instance de type T é ajouter
	 */
	public void setElement(T p) {
		_liste.add(p);
	}
	
	/**supprime un élément
	 * 
	 * @param p instance é supprimer
	 */
	public void removeElement(T p) {
		_liste.remove(p);
	}
	
	
	/**affiche l'ensemble des éléments
	 * 
	 */
	public void print() {
		
		for (int i=0;i<_liste.size();i++)
			{
			System.out.println(_liste.get(i));
		/*  on peut écrire également :
		  for (T t: liste)	
		 		System.out.println(t);
		 */
		}
	}
	
	
	/**
	 * retourne le nombre d'éléments
	 * @return le nombre d'éléments
	 * 	 */
	public int size() {
		return _liste.size();	
	}
	
	
	/**
	 * retourne un élément
	 * @param index position dans la liste
	 * @return l'élément à cette index
	 */
	public T getElement(int index) {
		
		if (_liste !=null) 
			return _liste.get(index);
		return null;	
	}
	
	public ArrayList<T> getList()
	{
		return _liste;
	}
	

	
	public void setList(ArrayList<T> liste)
	{
		this._liste = liste;
	}
	
	

}
