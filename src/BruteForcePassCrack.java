import java.security.MessageDigest;
import java.util.ArrayList;
import java.security.NoSuchAlgorithmException;

public class BruteForcePassCrack extends PassCrack {

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

       
       	private char [] alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();



	public String crack(String hash){
		
	 
                System.out.println("Attaque en cours ...");
		boolean cracked = false;
		char c;
		String Mdptest="";
		ArrayList<Integer>  prefixInd = new ArrayList<>() ;
		prefixInd.add(0);

		long startTime = System.nanoTime();

		while (!cracked){
				
				int taille = prefixInd.size();
		
				for (int i = taille -1; i > 0; i--){
					if (prefixInd.get(i) > alphabet.length -1){
					
						prefixInd.set(i , 0);
						int tmp = prefixInd.get(i-1);
						prefixInd.set(i-1 , tmp+1);
						

					}

				}
				

				if (prefixInd.get(0) > alphabet.length -1){
					prefixInd.set(0,0);
					prefixInd.add(0);
			
				}

				Mdptest="";
				
				for ( int i : prefixInd){
					Mdptest += alphabet[i];			
				}
				
				if ( Md5hash(Mdptest).equals(hash)){
					cracked =true ;
				}
			
				prefixInd.set(taille -1,prefixInd.get(taille -1)+1);	
		}
		long endTime = System.nanoTime();
		long duration = (endTime - startTime)/ 1000000000 ;

		long minutes = (int)duration / 60 ;
		long secondes = duration % 60 ;
		System.out.println("Temps de crackage: "+ minutes + " min : "+secondes+ " s");
		return Mdptest;
	}
}
