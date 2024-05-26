import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DictionnaryPassCrack extends PassCrack {
        
	private String Md5hash(String Mdp){

                try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(Mdp.getBytes());

                byte [] digest = md.digest();

                StringBuilder hexString = new StringBuilder();

                for (byte b : digest){
                        hexString.append(String.format("%02x",b));

                }
                return hexString.toString();

                } catch (NoSuchAlgorithmException e){
                        System.err.println("L'algorithm Md5 n'est pas disponible.");
                        e.printStackTrace();
                        return null;
                }
        }
	public String crack (String hash){
			System.out.println("Attaque Lancee ....");
				
			long startTime = System.nanoTime();

			String CheminFile = "/home/taphinho/Bureau/DESIGN PATTERN/Passcrack/rockyou.txt";
			boolean cracked =false;
			String Mdptest=null;		

			try {
				FileReader fichier = new FileReader(CheminFile);

				BufferedReader lecteur  = new BufferedReader(fichier);
				
				String ligne;
				while (((ligne=lecteur.readLine()) !=null) && !cracked){
					
					
					
					if (Md5hash(ligne).equals(hash)){
						cracked =true;
						Mdptest = ligne;						
					}
				}
				lecteur.close();
				fichier.close();

				
			}catch (IOException e){
				System.out.println("Fichier introuvable"+e.getMessage());
			}
			long endTime = System.nanoTime();
                	long duration = (endTime - startTime)/ 1000000000 ;

                	long minutes = (int)duration / 60 ;
                	long secondes = duration % 60 ;
			System.out.println("Temps de crackage: "+ minutes + " min : "+secondes+ " s");
			return Mdptest;

        	}
}
