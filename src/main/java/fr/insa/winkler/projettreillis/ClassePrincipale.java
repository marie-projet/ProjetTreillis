/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.winkler.projettreillis;


/**
 *
 * @author mariewinkler
 */
public class ClassePrincipale {
    public static void main (String args[]){
       Treillis t= Treillis.treillisTest();
       //t.calculForces();
       Point p1= new Point (-0.5,0);
       Point p2=new Point(0,0);
       Point p= new Point (-3,4);
       //t.choisiType();
       AppuiDouble ap=(AppuiDouble) t.getListeNoeuds().get(0);
       //System.out.println(t.toString());
       //t.menuTexte();
       t.menuTexte();
       t.Enregistrer("test");
}

}
