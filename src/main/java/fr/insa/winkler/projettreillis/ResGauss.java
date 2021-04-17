package fr.insa.winkler.projettreillis;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mariewinkler
 */
public class ResGauss {
    public int rang;
    public int sigPerm;
    
 public ResGauss (int rang, int sig){
     this.rang=rang;
     this.sigPerm=sig;
 }
 
 public String toString(){
     String res="ResGauss: rang = "+this.rang+" ; sigPerm = "+ this.sigPerm;
     return res;
 }
}
