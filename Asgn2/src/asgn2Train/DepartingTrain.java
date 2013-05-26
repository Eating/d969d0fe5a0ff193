package asgn2Train;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import asgn2Exceptions.TrainException;
import asgn2RollingStock.FreightCar;
import asgn2RollingStock.Locomotive;
import asgn2RollingStock.PassengerCar;
import asgn2RollingStock.RollingStock;
/**
 * 
 * @author Yiting Zhang
 * Student number: 08779210
 *
 */
public class DepartingTrain {
	private static final String LOCOMOTIVE = "Locomotive";
	private static final String PASSENGERCAR = "PassengerCar";
	private static final String FREIGHTCAR = "FreightCar";
	private int pointer = 0;

	private Stack<RollingStock> train = new Stack<RollingStock>();

	public DepartingTrain() {
	}
	
	
	public void addCarriage(RollingStock newCarriage) throws TrainException {
		if (train.size() == 0) {
			if (!newCarriage.getClass().getSimpleName().equals(LOCOMOTIVE))
				throw new TrainException("First carriage must be a locomotive");
			else
				train.push(newCarriage);
		} else if (newCarriage.getClass().getSimpleName().equals(LOCOMOTIVE))
			throw new TrainException("cannot add two locomotive");
		else if (this.numberOnBoard() != 0)
			throw new TrainException("people on board");
		else if (train.peek().getClass().getSimpleName().equals(FREIGHTCAR)
				&& newCarriage.getClass().getSimpleName().equals(PASSENGERCAR))
			throw new TrainException(
					"cannot add passengercar after a FREIGHTCAR");
		else
			train.push(newCarriage);
	}

	public Integer board(Integer newPassengers) throws TrainException {
		if (newPassengers < 0)
			throw new TrainException("negative passengers number");
		else if (newPassengers == 0)
			return 0;
		else if (this.numberOfSeats() - this.numberOnBoard() <= newPassengers) {
			for (int i = 1; i < train.size(); i++)
				if (train.get(i).getClass().getSimpleName()
						.equals(PASSENGERCAR)) {
					((PassengerCar) train.get(i)).board(((PassengerCar) train
							.get(i)).numberOfSeats()
							- ((PassengerCar) train.get(i)).numberOnBoard());
				}
			return newPassengers - this.numberOfSeats() - this.numberOnBoard();
		} else {
			List<int[]> passengerCarList = new ArrayList<int[]>();
			for (int i = 1; i < train.size(); i++) {
				if (train.get(i).getClass().getSimpleName()
						.equals(PASSENGERCAR)) {
					if (((PassengerCar) train.get(i)).numberOfSeats()
							- ((PassengerCar) train.get(i)).numberOnBoard() > 0) {
						int[] tmp = new int[3];
						tmp[0] = i;
						tmp[1] = ((PassengerCar) train.get(i)).numberOfSeats()
								- ((PassengerCar) train.get(i)).numberOnBoard();
						tmp[2] = 0;
						passengerCarList.add(tmp);
					}
				}
			}
			Random ran = new Random();
			for (int i = 0; i < newPassengers; i++) {
				int settlePassengerCar = ran.nextInt(passengerCarList.size());
				passengerCarList.get(settlePassengerCar)[2]++;
				if (passengerCarList.get(settlePassengerCar)[1] == passengerCarList
						.get(settlePassengerCar)[2]) {
					((PassengerCar) train.get(passengerCarList
							.get(settlePassengerCar)[0]))
							.board(passengerCarList.get(settlePassengerCar)[1]);
					passengerCarList.remove(i);
				}
			}
			for (int i = 0; i < passengerCarList.size(); i++) {
				((PassengerCar) train.get(passengerCarList.get(i)[0]))
						.board(passengerCarList.get(i)[2]);
			}

			return 0;
		}
	}

	public RollingStock firstCarriage() {
		if (train.size() == 0)
			return null;
		else {
			pointer = 1;
			return train.firstElement();
		}
	}

	public RollingStock nextCarriage() {
		if (pointer >= train.size())
			return null;
		else {
			int tmp = pointer;
			pointer++;
			return train.get(tmp);
		}
	}

	public Integer numberOfSeats() {
		int numberOfSeats = 0;
		for (int i = 1; i < train.size(); i++) {
			if (train.get(i).getClass().getSimpleName().equals(PASSENGERCAR))
				numberOfSeats = numberOfSeats
						+ ((PassengerCar) train.get(i)).numberOfSeats();
		}
		return numberOfSeats;
	}

	public Integer numberOnBoard() {
		int numberOnBoard = 0;
		for (int i = 1; i < train.size(); i++) {
			if ((train.get(i).getClass().getSimpleName()).equals(PASSENGERCAR))
				numberOnBoard = numberOnBoard
						+ ((PassengerCar) train.get(i)).numberOnBoard();
		}
		return numberOnBoard;
	}

	public void removeCarriage() throws TrainException {
		if (train.size() == 0)
			throw new TrainException("no carriages now");
		else if (this.numberOnBoard() != 0)
			throw new TrainException("passengers on board");
		else {
			train.pop();
		}
	}

	public String toString() {
		String description = null;
		if (!train.isEmpty()) {
			int lastOne = train.size() - 1;
			for (int i = 0; i < lastOne; i++) {
				switch (train.get(i).getClass().getSimpleName()) {
				case (String) LOCOMOTIVE:
					description += ((Locomotive) train.get(i)).toString() + "-";
					break;
				case (String) PASSENGERCAR:
					description += ((PassengerCar) train.get(i)).toString()
							+ "-";
					break;
				case (String) FREIGHTCAR:
					description += ((FreightCar) train.get(i)).toString() + "-";
					break;

				}
			}

			switch (train.get(lastOne).getClass().getSimpleName()) {
			case (String) LOCOMOTIVE:
				description += ((Locomotive) train.get(lastOne)).toString();
				break;
			case (String) PASSENGERCAR:
				description += ((PassengerCar) train.get(lastOne)).toString();
				break;
			case (String) FREIGHTCAR:
				description += ((FreightCar) train.get(lastOne)).toString();
				break;

			}
		}
		return description;
	}

	public boolean trainCanMove() {

		if (train.size() == 0)
			return true;
		else {
			int totalWeight = 0;
			for (int i = 0; i < train.size(); i++) {
				switch (train.get(i).getClass().getSimpleName()) {
				case LOCOMOTIVE:
					totalWeight += ((Locomotive) train.get(i)).getGrossWeight();
					break;
				case PASSENGERCAR:
					totalWeight += ((PassengerCar) train.get(i))
							.getGrossWeight();
					break;
				case FREIGHTCAR:
					totalWeight += ((FreightCar) train.get(i)).getGrossWeight();
					break;
				}
			}
			if (((Locomotive) train.get(0)).power() >= totalWeight)
				return true;
			else
				return false;

		}
	}
}
