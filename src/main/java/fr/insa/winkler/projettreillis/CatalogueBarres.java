/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.winkler.projettreillis;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author elodieherrmann
 */
public class CatalogueBarres {
    private List<TypeBarre> liste;
    
    public CatalogueBarres(){
         this.liste = new ArrayList<TypeBarre>();
         liste.add(new TypeBarre (1, 9.60, 1, 4, 340, 380));
         liste.add(new TypeBarre (2, 8.90, 1, 2, 450, 450));
         liste.add(new TypeBarre(3, 14.50, 0.80, 2.40, 40, 40));
         liste.add(new TypeBarre (4, 19.02, 1, 5, 470,  675));
         liste.add(new TypeBarre(5,50,60,70,80,78));
         liste.add(new TypeBarre(6,50,1,100,1000,1000));
    }

    public List<TypeBarre> getListe() {
        return liste;
    }

    public void add(TypeBarre TB){
    if(this.liste.contains(TB)==true){
        throw new Error ("Ce type de barre existe déjà");
    }
    this.liste.add(TB);
    }

    public void remove(TypeBarre TB){
        if(this.liste.contains(TB)==false){
            throw new Error ("Ce type de barre n'est pas dans le catalogue");
        }
        this.liste.remove(TB);
    }


    public void removeAll (){
        for (int i=0; i<this.liste.size(); i++){
            this.liste.clear();
        }
    }
}
