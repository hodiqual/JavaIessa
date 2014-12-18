package fr.enac.model;

import java.text.DateFormat;
import javax.swing.JOptionPane;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;


public class Personne implements java.io.Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -2831675449026453550L;
	
	private String nom;
	private String prenom;
	private Date dateDeNaissance;
	
	private static int compteur;
	private final int uid = getCompteur();
	
	Personne()
	{
		compteur++;
		setNom(JOptionPane.showInputDialog ("Saisir nom:")) ;
		setPrenom(JOptionPane.showInputDialog ("Saisir prenom:")) ;
		try {
			setDateDeNaissance( DateFormat.getDateInstance(DateFormat.SHORT,new Locale("fr")).parse(JOptionPane.showInputDialog ("Saisir prenom:")) );
		} catch (ParseException e) {
			e.printStackTrace();
			this.dateDeNaissance = new Date();
		}
	}
	
	public Personne(String nom, String prenom, String dateDeNaissance)
	{
		compteur++;
		this.nom = nom;
		this.prenom = prenom;
		
		try {
			this.dateDeNaissance =  DateFormat.getDateInstance(DateFormat.SHORT,new Locale("fr")).parse(dateDeNaissance);
		} catch (ParseException e) {
			e.printStackTrace();
			this.dateDeNaissance = new Date();
		}
	}
	
	public void finalize()
	{
		System.out.println("Destruction de " + uid + "-" + this);
	}
	
	public String toString()
	{
		return nom + " " + prenom + " " + dateDeNaissance;
	}
		/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * @return the dateDeNaissance
	 */
	public Date getDateDeNaissance() {
		return dateDeNaissance;
	}

	/**
	 * @param dateDeNaissance the dateDeNaissance to set
	 */
	public void setDateDeNaissance(Date dateDeNaissance) {
		this.dateDeNaissance = dateDeNaissance;
	}

	/**
	 * @return the compteur
	 */
	public static int getCompteur() {
		return compteur;
	}

	/**
	 * @return the uid
	 */
	public int getUid() {
		return uid;
	}
	
	public String stringToSave()
	{
		String result =  "";
		result += this.getClass().getName() + " " + nom + " " + prenom + " " + dateDeNaissance;
		return result;
	}
}
