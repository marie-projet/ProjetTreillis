/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.winkler.projettreillis;
import java.util.ArrayList;
/**
 *
 * @author elodieherrmann
 */
public class CatalogueBarres {
    private List<TypeBarre> contient;
    
public CatalogueBarre(){
    this.contient = new ArrayList<TypeBarre>();
}
public void add(TypeBarre TB){
    if(TB.getCatalogueBarres() != this){
    if (TB.getCatalogueBarres() != null){
    throw new Error ("Ce type de barre existe déjà");
}
    this.contient.add(TB);
    TB.setCatalogueBarres(this);
}
}
public void remove(TypeBarre TB){
    if(TB.getCatalogueBarre() != this){
        throw new Error ("Ce type de barre n'est pas dans le catalogue");
    }
public void removeAll (List<TypeBarre> lTB){
    for (TypeBarre TB : lTB){
    this.remove(TB);
}
}
}
