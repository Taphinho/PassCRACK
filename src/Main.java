public class Main {
	public static void main (String [] args ){
		try{
			String Type = args[0];
			String hash = args[1];

			PassCrack passCrack = FabriquePassCrack.getInstance(Type);
			
			String Mdp = passCrack.crack(hash);
			
			if ( Mdp != null ){
				System.out.println("Hash cracker, Le mot de passe est : " + Mdp);
			}
			else {
				System.out.println("Hash non cracker");
			}
			
			
		}
		catch(ArrayIndexOutOfBoundsException e){
			System.out.println("Erreur Format : Main <type : Dico / Brute> <hash MD5>");
		}
	}
}
