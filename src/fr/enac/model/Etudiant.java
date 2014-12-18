package fr.enac.model;


/**
 * @author hodiqual
 *
 */
public class Etudiant extends Personne implements IEtudiant {
	
	private final int nbMaxMatiere = 3;
	private String matieresSuivies[] = new String[nbMaxMatiere];
	private double notes[] = new double[nbMaxMatiere];
	

	
	private void initTabMatiere()
	{
		for (String string : matieresSuivies)
			string = new String();
	}
	
	public Etudiant()
	{
		initTabMatiere();
	}
	
	Etudiant(String nom, String prenom, String dateDeNaissance)
	{
		super(nom,prenom,dateDeNaissance);
		initTabMatiere();
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
		return super.toString() + ": " + calculMoyenne();
	}
}
