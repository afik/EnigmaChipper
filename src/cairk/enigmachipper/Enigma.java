package cairk.enigmachipper;

public class Enigma {
	public String Layer1 = new String ();
	public String Layer2 = new String ();
	public String Layer3 = new String ();
	public String StrInput = new String ();
	public Enigma(String Inner, String Middle, String Outside, String Input){
		if(IsPlateValid(Inner) && IsPlateValid(Middle) && IsPlateValid(Outside) && IsInputValid(Input)){
			Layer1 = Inner;
			Layer2 = Middle;
			Layer3  = Outside;
			StrInput = Input;
			System.out.println("Input valid!");
		}else{
			System.out.println("Maaf input tidak valid!");
		}
	}
	
	public boolean IsPlateValid(String str){
		String template = "ABCDEFGHIJKLMNOPQRSTUVWXYZ#";
		if(str.length()==template.length()){
			for (int i = 0; i < template.length() ; i++){
				if(!str.contains(String.valueOf(template.charAt(i)))){
					return false;
				}
			}
			return true;
		}else
			return false;
	}
	public boolean IsInputValid(String str){
		String template = "ABCDEFGHIJKLMNOPQRSTUVWXYZ#.";
		if(str.charAt(str.length()-1)=='.'){
			for (int i = 0; i < str.length() ; i++){
				if(!template.contains(String.valueOf(str.charAt(i)))){
					return false;
				}
			}
			return true;
		}else
			return false;
	}
	public int IdxMatch(String Str, char cc){
		int i = 0;
		while(Str.charAt(i)!=cc)
			i++;
		return i;
		// relatif thd 0
	}
	
	
	
	public String RotatePlate(String Plate){
		Plate = Plate.charAt(Plate.length()-1)+Plate;
		Plate = Plate.substring(0,Plate.length()-1);
		return Plate;
	}
	
	public String UndoRotatePlate(String Plate){
		Plate = Plate+Plate.charAt(0);
		Plate = Plate.substring(1,Plate.length());
		return Plate;
	}
	
	public String Encrypt(){
		String StrEncrypted = new String ();
		for(int i = 0; i < StrInput.length()-1 ; i ++){
			int Idx = IdxMatch(Layer1,StrInput.charAt(i));
			char CharAtLayer3 = Layer3.charAt(Idx);
			Idx = IdxMatch(Layer2,CharAtLayer3);
			StrEncrypted +=Layer3.charAt(Idx); 
			Layer1 = RotatePlate(Layer1);
			if((i+1)% 27 == 0){
				Layer2 = RotatePlate(Layer2);
			}
			if((i+1)% (27*27) == 0){
				Layer3 = RotatePlate(Layer3);
			}
		}
		StrEncrypted +=".";
		return StrEncrypted;
	}
	public String Decrypt(){
		String StrDecrypted = new String ();
		for(int i = StrInput.length()-2; i >= 0  ; i --){
			if((i+1)% 27 == 0){
				Layer2 = UndoRotatePlate(Layer2);
			}
			if((i+1)% (27*27) == 0){
				Layer3 = UndoRotatePlate(Layer3);
			}
			int Idx = IdxMatch(Layer3,StrInput.charAt(i));
			char CharAtLayer2 = Layer2.charAt(Idx);
			Layer1 = UndoRotatePlate(Layer1);
			
			Idx = IdxMatch(Layer3,CharAtLayer2);
			StrDecrypted =Layer1.charAt(Idx) + StrDecrypted; 
		}
		StrDecrypted +=".";
		return StrDecrypted;
	}
	
	public static void main (String [] args){
		String Inner    ="RYELSZFMT#GNUAHOVBIPWCJQXDK";
		String Middle  = "#EJOTYCHMRWAFKPUZDINSXBGLQV";
		String Outside = "#BDFHJLNPRTVXZACEGIKMOQSUWY";
		String Input = "AAAAAAAABBBBBBBBBBBBBCCCCCCCCCDDDDDDDDDDDDDDEEEEEEEEE.";
		String Enc = new String();
		String Dec = new String();
		//Melakukan enkripsi
		Enigma myEnigmaEnc = new Enigma(Inner,Middle,Outside,Input);
		Enc = myEnigmaEnc.Encrypt();
		System.out.println(Enc);	
		//decript
		Enigma myEnigmaDec = new Enigma(Inner,Middle,Outside,Enc);
		Dec = myEnigmaDec.Decrypt();
		System.out.println(Dec);
	}
}