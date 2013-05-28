/**
 * @author Tian XIN
 * 
 * @studentNumber 8779295
 * 
 * @version 1.0
 * 
 */
package asgn2RollingStock;

import asgn2Exceptions.TrainException;

public abstract class RollingStock  {
	protected int grossWeight;
	public RollingStock(Integer grossWeight)
            throws TrainException{
		if (grossWeight <= 0){
			throw new TrainException("Gross weight is not positive");
		}
		this.grossWeight = grossWeight;
	}
	public Integer getGrossWeight(){
		return this.grossWeight;
	}
	public abstract String toString();
	
}
