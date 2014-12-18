package fr.enac.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import fr.enac.controller.Controller;
import fr.enac.controller.ModelEvent;
import fr.enac.model.Model;
import fr.enac.model.Personne;

public class Vue extends JFrame implements PropertyChangeListener {
	
	private static final long serialVersionUID = 5743238144673811430L;
	
	private JButton b1;
    private JLabel lab1,lab2,lab3,lab4;
    private JTextArea ta1,ta2 ;
    private JPanel pan1,pan2 ;
    private JComboBox<Personne> _combobox ;
    private Controller _controller;
	
	@SuppressWarnings("unchecked")
	public Vue(String titre, Controller controller)
	{
		super(titre);
		_controller = controller;
		_controller.ajoutVue(this);
		this.setBounds(200, 200, 600, 200);

        lab1=new JLabel("Gestion des Personnes");
        lab1.setFont(new Font("SansSerif",Font.BOLD,16));
        lab1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lab1.setBackground(Color.BLACK);
        
        lab2=new JLabel("Nom du fichier :");
        lab3=new JLabel("Liste des personnes du fichier :");
        lab4=new JLabel("Personne sélectionnée :");
        
        ta1 = new JTextArea("nom du fichier ",1,30) ;
        ta1.setBackground(Color.lightGray);
        ta2 = new JTextArea("vide",1,40) ;
        ta2.setBackground(Color.CYAN);
        
        b1 = new JButton("charger") ;
        b1.setSize(30,30);
        b1.addActionListener(new ActionCharger());
       
        _combobox = new JComboBox<Personne>();
        //Permet de redefinir l'appel de toString par defaut pour l'affichage dans les lignes des composants
        _combobox.setRenderer(new BasicComboBoxRenderer() {
        	@Override
        	public Component getListCellRendererComponent(JList list,
                    Object value,
                    int index,
                    boolean isSelected,
                    boolean cellHasFocus)
                    {
        				Component cmp = super.getListCellRendererComponent(list,value,index,isSelected,cellHasFocus);
        			     if (value instanceof Icon) 
		    	             setIcon((Icon)value);
		    	         else if (value instanceof Personne)
		    	         {
		    	        	 setText((value == null) ? "" : ((Personne)value).getNom() + " " + ((Personne)value).getPrenom());		
		    	         }
		    	         else
		    	             setText((value == null) ? "" : value.toString());			
        				return cmp;
                    }
        });
        System.out.println(_combobox.getRenderer().toString());
        _combobox.addItemListener(new SelectionnerItem());
        
        // Construction d'un panneau (sera un container interne au cadre) :
        pan1 = new JPanel() ;
        pan1.setLayout(new GridLayout(2,3));
        pan2 = new JPanel() ;
        pan2.setLayout(new GridLayout(2,2));
        
        pan1.add(new JLabel());
        pan1.add(lab1);
        pan1.add(new JLabel());
        pan1.add(lab2);
        pan1.add(ta1);
        pan1.add(b1);
        
        pan2.add(lab3);
        pan2.add(lab4);
        pan2.add(_combobox);
        pan2.add(ta2);
        
        this.add(pan1, BorderLayout.NORTH) ;
        this.add(pan2, BorderLayout.SOUTH) ;
            
        //    	- une barre de menus
    	JMenuBar jmb = new JMenuBar();
    	JMenu menuGerer = new JMenu("Gérer");
    	jmb.add(menuGerer);
    	JMenuItem menuCharger = new JMenuItem("Charger fichier");
    	JMenuItem  menuQuitter = new JMenuItem("Quitter");
    	this.setJMenuBar(jmb);
    	menuGerer.add(menuCharger);
    	menuGerer.addSeparator();
    	menuGerer.add(menuQuitter);
        
    	
    	menuCharger.addActionListener(new ActionCharger());
    	menuQuitter.addActionListener(new QuitterListener());
        
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    	// Réajustement automatique des dimensions des figures :
        this.pack() ;  
	}
	
    /** Quitter le jeu. */
    public void quitter() {
		this.dispose();
		/*System.out.println("Fin du jeu...");
		System.exit(0);*/
	 }
    
 // Quelques r�actions aux interactions de l'utilisateur
 	// ----------------------------------------------------
    
    class ActionCharger implements ActionListener {
    	public void actionPerformed(ActionEvent arg0) {
    		ta1.setBackground(Color.lightGray);
    		_controller.charger(ta1.getText());
    	}

    }
    
    class SelectionnerItem implements ItemListener {
    	@Override
    	public void itemStateChanged(ItemEvent evt) { 
    		if (evt.getStateChange() == ItemEvent.SELECTED)
    		{
    			ta2.setText(_combobox.getSelectedItem().toString());
    		}
    	}    	
    }

    class QuitterListener implements ActionListener{
    	@Override
    	public void actionPerformed(ActionEvent arg0) {
    		quitter();	
    	}    	
    }

    class CoupListener extends MouseAdapter{

    	int i,j;

    	CoupListener(int i, int j){
    		this.i = i;
    		this.j = j;
    	}

    	@Override
    	public void mouseClicked(MouseEvent evt) {
    		//model_.joueCoup(i, j);
    	}    	
    }

	//Quand le model change, la vue est notifiee par cette methode.
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		ModelEvent evttype = ModelEvent.valueOf(evt.getPropertyName());
		
		switch (evttype) {
			case INITIALISATION:
			{
		        this.setVisible(true) ;
		        //init combobox
		        if(_combobox != null)
		        	_combobox.removeAllItems();
		        
		        for (Personne p : ((Model<Personne>)evt.getSource()).getList()) {
					_combobox.addItem(p);
				}
				break;
			}
			case CHARGER:
			{
				JOptionPane.showMessageDialog(null, 
						"Chargement reussi de " + evt.getNewValue() + " personnes", 
						"" , JOptionPane.INFORMATION_MESSAGE);   
				break;
			}
			case CHARGER_RATER:
			{
	    		ta1.setBackground(Color.RED);
				JOptionPane.showMessageDialog(null, 
						"Chargement echoue: " + evt.getNewValue(), 
						"" , JOptionPane.ERROR_MESSAGE); 
				break;
			}
	
			default:
				break;
		}
	}

}
