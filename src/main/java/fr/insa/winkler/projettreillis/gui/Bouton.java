/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.winkler.projettreillis.gui;


import javax.swing.* ;
import java.awt.* ;
import java.awt.event.* ;

/**
 *
 * @author mariewinkler
 */
    class CreationTreillis extends JFrame implements ActionListener
{ public CreationTreillis ()
  { setTitle ("Creation Treillis") ;
    setSize (300, 200) ;
    monBouton0 = new JButton ("Annuler") ;
    monBouton1 = new JButton ("Barre") ;
    monBouton2 = new JButton ("Noeud") ;
    Container contenu = getContentPane() ; // Pointeur de la fenêtre courante
    contenu.setLayout(new FlowLayout()) ; // Gestionnaire de mise en forme
    contenu.add(monBouton0);
    contenu.add(monBouton1) ; // Insertion du bouton
    contenu.add(monBouton2) ;
    monBouton0.addActionListener(this);
    monBouton1.addActionListener(this);
    monBouton2.addActionListener(this);
  }
  
  public void actionPerformed (ActionEvent ev)
  { if (ev.getSource() == monBouton1)
	  {
      System.out.println ("action sur bouton numero 1") ;
	  CreationBarre fen1 = new CreationBarre() ;
      fen1.setVisible(true) ;
	  }
    if (ev.getSource() == monBouton2)
			  {
       System.out.println ("action sur bouton numero 2") ;
	  CreationNoeud fen2 = new CreationNoeud() ;
      fen2.setVisible(true) ;
	  }
    if (ev.getSource()== monBouton0){
        System.out.println ("action sur bouton numero 0") ;
        dispose();
    }
  
  }
  private JButton monBouton1, monBouton2, monBouton0;
}
//  Fin Profil Fenetre1

// Profil Fenetre 2 *****************
class CreationNoeud extends JFrame implements ActionListener
{ public CreationNoeud ()
  { setTitle ("Création noeud") ;
    setSize (600, 300) ;
	setLocation (350, 0) ;
    monBouton1 = new JButton ("Noeud simple") ;
    monBouton2 = new JButton ("Noeud appui") ;
    Container contenu = getContentPane() ;
    contenu.setLayout(new FlowLayout()) ;
    contenu.add(monBouton1) ;
    contenu.add(monBouton2) ;
    monBouton1.addActionListener(this);
    monBouton2.addActionListener(this);
  }
  
  public void actionPerformed (ActionEvent ev)
  { if (ev.getSource() == monBouton1)
      System.out.println ("action sur bouton A") ;
    if (ev.getSource() == monBouton2)
      System.out.println ("action sur bouton B") ;
  }
  private JButton monBouton1, monBouton2 ;
}
//  Fin Profil Fenetre2

class CreationBarre extends JFrame implements ActionListener
{ public CreationBarre ()
  { setTitle ("Création Barre") ;
    setSize (600, 300) ;
	setLocation (350, 0) ;
    monBouton3 = new JButton ("type de Barre") ;
    Container contenu = getContentPane() ;
    contenu.setLayout(new FlowLayout()) ;
    contenu.add(monBouton3) ;
    monBouton3.addActionListener(this);
  }
  
  public void actionPerformed (ActionEvent ev)
  { if (ev.getSource() == monBouton3)
      System.out.println ("action sur bouton C") ;
  }
  private JButton monBouton3 ;
}

public class Bouton {
 public static void main (String args[]){ 
    CreationTreillis fen = new CreationTreillis() ;
    fen.setVisible(true);
 }
}

    

