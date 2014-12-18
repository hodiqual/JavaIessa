package fr.enac.model;


/**
 * @author hodiqual
 *
 */
public class EtudiantEnseignant extends Personne implements IEnseignant,
		IEtudiant {
	
	private final int nbMaxMatiere = 3;
	
	private String matieresEnseignees[] = new String[nbMaxMatiere];
	private int nbHeures[] = new int[nbMaxMatiere];
	private double tarifHoraire;	
	
	private String matieresSuivies[] = new String[nbMaxMatiere];
	private double notes[] = new double[nbMaxMatiere];
	
	
	private void initTabMatiere()
	{
		for (String string : matieresEnseignees)
			string = new String();
		
		for (String string : matieresSuivies)
			string = new String();
	}
	

	/**
	 * 
	 */
	public EtudiantEnseignant() {
		initTabMatiere();
	}

	/**
	 * @param nom
	 * @param prenom
	 * @param dateDeNaissance
	 */
	public EtudiantEnseignant(String nom, String prenom, String dateDeNaissance) {
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
	
	@Override
	public double getTarifHoraire() {
		return tarifHoraire;
	}
	
	public void setMatiereSuivie(int index, String nom)
	{
		matieresSuivies[index] = nom;
	}
	
	public void setNote(int index, double note)
	{
		notes[index] = note;
	}
	
	double calculMoyenne()
	{
		double moyenne = 0.;
		for (double note : notes) {
			moyenne += note/nbMaxMatiere;
		}
		return moyenne;
	}

	@Override
	public String getMatiereSuivie(int index) {
		return matieresSuivies[index];
	}

	@Override
	public double getNote(int index) {
		return notes[index];
	}
	
	public String toString()
	{
		return super.toString() + " (nb horaire " + calculerNbHeuresTotal()
				+ " - cout total prof: " + calculCout() + " - moyenne eleve: " + calculMoyenne();
	}

}
