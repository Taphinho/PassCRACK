public  class FabriquePassCrack {
        public static PassCrack getInstance(String Type){
                PassCrack passCrack = null;
                switch(Type){
                        case "Brute":
                                passCrack = new BruteForcePassCrack();
                                break;
                        case "Dico":
                                passCrack =  new DictionnaryPassCrack();
                                break;
                        default :
                                System.out.println("Type Introuvable");
                                break;
                }
                return passCrack;
        }
}
