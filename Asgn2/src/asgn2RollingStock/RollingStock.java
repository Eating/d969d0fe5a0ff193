package asgn2RollingStock;

import asgn2Exceptions.TrainException;

public abstract class RollingStock  {

	/**
	 * @param args
	 */
	public RollingStock(){
		
	}
	public RollingStock(Integer grossWeight)
            throws TrainException{
		
	}
	public Integer getGrossWeight(){
		return 100;
	}
	public abstract String toString();
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
