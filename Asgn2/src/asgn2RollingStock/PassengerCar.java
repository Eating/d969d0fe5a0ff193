package asgn2RollingStock;

import asgn2Exceptions.TrainException;

public class PassengerCar extends RollingStock {

	private int numberOfSeats;
	private int numberOnBoard;
	public PassengerCar(Integer grossWeight, Integer numberOfSeats) throws TrainException{
		super(grossWeight);
		if (numberOfSeats < 0){
			throw new TrainException("Number of seats is negative");
		}
		this.numberOfSeats = numberOfSeats;
		this.numberOnBoard = 0;
	}
	
	public Integer board(Integer newPassengers) throws TrainException{
		if (newPassengers < 0){
			throw new TrainException("Number of new passengers is negative");
		}
		this.numberOnBoard += newPassengers;
		int ret = 0;
		if (this.numberOnBoard > this.numberOfSeats){
			ret = this.numberOnBoard - numberOfSeats;
			numberOnBoard = numberOfSeats;
		}
		return ret;
	}
	
	public void alight(Integer departingPassengers)
            throws TrainException{
		if (departingPassengers > this.numberOnBoard || departingPassengers < 0){
			throw new TrainException("Number of departing passengers is illegal");
		}
		this.numberOnBoard -= departingPassengers;
	}
	
	public Integer numberOnBoard(){
		return this.numberOnBoard;
	}
	public Integer numberOfSeats(){
		return this.numberOfSeats;
	}
	public String toString(){
		return "Passenger(" + this.numberOnBoard + "/" + this.numberOfSeats() + ")";
	}
	

}
