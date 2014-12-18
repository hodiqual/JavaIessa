package fr.enac.model;


public interface IEtudiant {
	public void setMatiereSuivie(int index, String matiere);
	public String getMatiereSuivie(int index);
	public void setNote(int index, double note);
	public double getNote(int index);
	
}
