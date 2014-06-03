package cairk.enigmachipper;

import android.app.Application;


public class Enigma extends Application{
	public String Layer1 = new String ();
	public String Layer2 = new String ();
	public String Layer3 = new String ();
	public String genuineInner = new String();
	public String genuineMiddle= new String();
	public String genuineOuter = new String();
	public String StrInput = new String();
	
	public Enigma(){
		Layer1 = "RYELSZFMT#GNUAHOVBIPWCJQXDK"; 
		genuineInner = "RYELSZFMT#GNUAHOVBIPWCJQXDK";
		Layer2 = "#EJOTYCHMRWAFKPUZDINSXBGLQV"; 
		genuineMiddle = "#EJOTYCHMRWAFKPUZDINSXBGLQV";
		Layer3  = "#BDFHJLNPRTVXZACEGIKMOQSUWY"; 
		genuineOuter = "#BDFHJLNPRTVXZACEGIKMOQSUWY";
	}
	
	public void setPlate(String Inner, String Middle, String Outside) {
		if(IsAllPlateValid(Inner,Middle,Outside)){
			Layer1 = Inner; genuineInner = Inner;
			Layer2 = Middle; genuineMiddle = Middle;
			Layer3  = Outside; genuineOuter = Outside;
		}
	}
	
	public void setInput(String Input) {
		if(IsInputValid(Input)) {
			StrInput = Input;
		}
	}
	
	public void setLayerLikeBefore(){
		Layer1 = genuineInner;
		Layer2 = genuineMiddle;
		Layer3 = genuineOuter;
	}
	
	public boolean IsAllPlateValid(String Inner, String Middle, String Outside){
		return IsPlateValid(Inner) && IsPlateValid(Middle) && IsPlateValid(Outside);
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
		if(str.length()>0 && str.charAt(str.length()-1)=='.'){
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
		setLayerLikeBefore();
		return StrEncrypted;
	}
	
	public void SetUpPlate(){
		for(int i = 0; i < StrInput.length()-1 ; i ++){
			Layer1 = RotatePlate(Layer1);
			if((i+1)% 27 == 0){
				Layer2 = RotatePlate(Layer2);
			}
			if((i+1)% (27*27) == 0){
				Layer3 = RotatePlate(Layer3);
			}
		}
	}
	
	public String Decrypt(){
		String StrDecrypted = new String ();
		SetUpPlate();
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
		setLayerLikeBefore();
		return StrDecrypted;
	}
	
}