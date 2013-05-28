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

public class FreightCar extends RollingStock {

	String goodsType;
	public FreightCar(Integer grossWeight,
            String goodsType) throws TrainException{
		super(grossWeight);
		if (!(goodsType.equals("G")||
				goodsType.equals("R")||
				goodsType.equals("D"))){
			throw new TrainException("Invalid goods type for freight car");
		}
		this.goodsType = goodsType;
	}
	public String goodsType(){
		return this.goodsType;
	}
	public String toString(){
		return "Freight(" + this.goodsType + ")";
	}

}
