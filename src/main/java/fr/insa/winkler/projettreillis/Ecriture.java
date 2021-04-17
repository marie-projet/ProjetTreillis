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

import java.io.*;

public class Ecriture {
    public static void main(String []args){
        try{
            BufferedWriter out = new BufferedWriter(new FileWriter("clients.txt"));
            out.write("Philippe");
            out.newLine();
            out.write("Bernard");
            out.newLine();
            out.close();
        }
        catch (IOException err){
            System.out.println("Erreur :\n"+err);
            
        }
    }
}
