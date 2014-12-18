package fr.enac.model;


/**
 * @author hodiqual
 *
 */
public class Enseignant extends Personne implements IEnseignant {

	private final int nbMaxMatiere = 3;
	private String matieresEnseignees[] = new String[nbMaxMatiere];
	private int nbHeures[] = new int[nbMaxMatiere];
	private double tarifHoraire;
	
	private void initTabMatiere()
	{
		for (String string : matieresEnseignees)
			string = new String();
	}
	
	/**
	 * 
	 */
	public Enseignant() {
		initTabMatiere();
	}

	/**
	 * @param nom
	 * @param prenom
	 * @param dateDeNaissance
	 */
	public Enseignant(String nom, String prenom, String dateDeNaissance) {
		super(nom, prenom, dateDeNaissance);
		initTabMatiere();
	}
	
	public void setMatiereEnseignee(int index, String nom)
	{
		matieresEnseignees[index] = nom;
	}
	
	@Override
	public String getMatiereEnseignee(int index) {
		return matieresEnseignees[index];
	}

	public void setNbHeures(int index, int heure) {
		nbHeures[index] = heure;
	}

	@Override
	public int getHeures(int index) {
		// TODO Auto-generated method stub
		return nbHeures[index];
	}

	public void setTarifHoraire(double d) {
		this.tarifHoraire = d;
	}
	
	public int calculerNbHeuresTotal()
	{
		int total =0;
		for (int heure : nbHeures) {
			total+=heure;
		}
		return total;
	}
	
	public double calculCout()
	{
		return calculerNbHeuresTotal()*tarifHoraire;
	}
	
	public String toString()
	{
		return super.toString() + " (nb horaire " + calculerNbHeuresTotal()
				+ " - cout total prof: " + calculCout();
	}

	@Override
	public double getTarifHoraire() {
		return tarifHoraire;
	}
	
	

}
