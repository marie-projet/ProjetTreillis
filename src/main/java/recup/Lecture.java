package recup;
import fr.insa.winkler.projettreillis.*;
import java.io.*;class  
 Lecture{
    public static void main(String[]args){
        try
{
BufferedReader etudiants=new BufferedReader(new FileReader("Etudiants.txt"));
String lignelue;// Ligne lue depuis le fichier
   while((lignelue=etudiants.readLine())!=null)
   {
   System.out.println(lignelue);
   }
etudiants.close();
}
catch(FileNotFoundException err){
System.out.println( "Erreur :le fichier n’existe pas!\n "+err);}
catch (IOException err){
System.out.println(" Erreur :\n "+err);}

}// Fin main()
} // Fin classe