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
            this.liste.remove(liste.get(i));
        }
    }
}
