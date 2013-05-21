package asgn2RollingStock;

import asgn2Exceptions.TrainException;

public class Locomotive extends RollingStock{

	private int powerClass;
	private String classification;
	public Locomotive(Integer grossWeight, String classification) throws TrainException{
		super(grossWeight);
		String powerClass = classification.substring(0, classification.length()-1);
		String engineType = classification.substring(classification.length() - 1);
		try{
			this.powerClass = Integer.parseInt(powerClass);
			if (this.powerClass < 0 || this.powerClass > 9){
				throw new Exception();
			}
			if (!(engineType.equals("E") ||
					engineType.equals("D") ||
					engineType.equals("S"))){
				throw new Exception();
			}
			this.classification = classification;
		}
		catch (Exception e){
			throw new TrainException("Locomotive classification format error");
		}
	}

	public Integer power(){
		return powerClass * 100;
	}
	public String toString(){
		return "Loco(" + this.classification + ")";
	}

}
