package fr.enac.model;


public interface IEnseignant {
	public void setMatiereEnseignee(int index, String matiere);
	public String getMatiereEnseignee(int index);
	public void setNbHeures(int index, int heure);
	public int getHeures(int index);
	public void setTarifHoraire(double d);
	public double getTarifHoraire();
	public double calculCout();
	public int calculerNbHeuresTotal();

}
