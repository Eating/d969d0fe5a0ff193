/**
 * @author Tian XIN
 * 
 * @studentNumber 8779295
 * 
 * @version 1.0
 * 
 * @license MIT
 */
package asgn2Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import asgn2Exceptions.TrainException;
import asgn2RollingStock.FreightCar;
import asgn2RollingStock.Locomotive;
import asgn2RollingStock.PassengerCar;
import asgn2RollingStock.RollingStock;
import asgn2Train.DepartingTrain;

public class TrainTests {
	private static final int grossWeight = 100;
	private static final String locoClassification = "4E";
	private static final String loco2Classification = "1D";
	private static final int passenger1Seats = 50;
	private static final int passenger2Seats = 10;
	private static final String freight1Type = "R";
	private static final String freight2Type = "D";
	private RollingStock loco = null;
	private RollingStock loco2 = null;
	private RollingStock passenger1 = null;
	private RollingStock passenger2 = null;
	private RollingStock freight1 = null; 
	private RollingStock freight2 = null;
	private DepartingTrain d = null;
	/*Construct an empty train*/
	/**
	 * Test of departing train constructor
	 */
	@Test
	public void testConstructor() {
		d = new DepartingTrain();
	}
	
	/**
	 * Test trainCanMove() of an empty train
	 */
	@Test
	public void testEmptyTrainCanMove(){
		d = new DepartingTrain();
		assertTrue(d.trainCanMove());
	}
	
	/** 
	 * +++++++++++ 
	 * | Freight |
	 * +++++++++++
	 * Test add freight carriage at first position
	 * @throws TrainException 
	 */
	@Test (expected = TrainException.class)
	public void testEmptyAddFreight() throws TrainException {
		d = new DepartingTrain();
		freight1 = new FreightCar(grossWeight, freight1Type);
		d.addCarriage(freight1);
	}
	/**
	 * +++++++++++++ 
	 * | Passenger |
	 * +++++++++++++
	 * Test add passenger carriage at first position
	 * @throws TrainException 
	 */
	@Test(expected = TrainException.class)
	public void testEmptyAddPassenger() throws TrainException{
		d = new DepartingTrain();
		passenger1 = new PassengerCar(grossWeight, passenger1Seats);
		d.addCarriage(passenger1);
	}
	
	/**
	 * Test boarding on an empty train
	 * @throws TrainException 
	 */
	@Test
	public void testBoardingOnEmptyTrain() throws TrainException{
		d = new DepartingTrain();
		int newPassengers = 20;
		assertEquals(newPassengers, (int)d.board(newPassengers));
	}
	
	/**
	 * Test first carriage of an empty train
	 */
	@Test
	public void testFirstCarriageOfEmptyTrain(){
		d = new DepartingTrain();
		assertNull(d.firstCarriage());
	}
	
	/**
	 * Test next carriage of an empty train
	 */
	@Test
	public void testNextCarriageOfEmptyTrain(){
		d = new DepartingTrain();
		assertNull(d.nextCarriage());
	}
	
	/**
	 * Test number of seats of empty train
	 */
	@Test
	public void testEmptySeats(){
		d= new DepartingTrain();
		assertEquals(0, (int)d.numberOfSeats());
	}
	
	/**
	 * Test number on board of empty train
	 */
	@Test
	public void testEmptyOnBoard(){
		d= new DepartingTrain();
		assertEquals(0, (int)d.numberOnBoard());
	}
	
	/**
	 * Test remove carriage of an empty train
	 * @throws TrainException 
	 */
	@Test(expected = TrainException.class)
	public void testRemoveCarriageOfEmptyTrain() throws TrainException{
		d = new DepartingTrain();
		d.removeCarriage();
	}
	
	/**
	 * ++++++++++++++
	 * | locomotive |
	 * ++++++++++++++
	 * Test just one locomotive carriage
	 * @throws TrainException 
	 */
	@Test
	public void testAddLocomotiveCarriage() throws TrainException{
		d = new DepartingTrain();
		loco = new Locomotive(grossWeight, locoClassification);
		d.addCarriage(loco);
		
	}
	
	/**
	 * ++++++++++++++   ++++++++++++++
	 * | locomotive |===| locomotive |
	 * ++++++++++++++---++++++++++++++
	 * Test add another locomotive carriage after already added one
	 * @throws TrainException 
	 */
	@Test(expected = TrainException.class)
	public void testAddAnotherLocomotiveCarriage() throws TrainException{
		d = new DepartingTrain();
		loco = new Locomotive(grossWeight, locoClassification);
		d.addCarriage(loco);
		loco2 = new Locomotive(grossWeight, loco2Classification);
		d.addCarriage(loco2);
		
	}
	/**
	 * ++++++++++++++
	 * | locomotive |
	 * ++++++++++++++
	 * Test trainCanMove of a train with just one locomotive carriage
	 * @throws TrainException 
	 */
	@Test
	public void testTrainCanMoveWithLocomotive() throws TrainException{
		d = new DepartingTrain();
		loco = new Locomotive(grossWeight, locoClassification);
		d.addCarriage(loco);
		assertTrue(d.trainCanMove());
		
		d = new DepartingTrain();
		loco2 = new Locomotive(2*grossWeight, loco2Classification);
		d.addCarriage(loco2);
		assertFalse(d.trainCanMove());
	}
	/**
	 * ++++++++++++++
	 * | locomotive |
	 * ++++++++++++++
	 * Test boarding on a train with just one locomotive carriage
	 * @throws TrainException 
	 */
	@Test
	public void testBoardingOnLocomotive() throws TrainException{
		d = new DepartingTrain();
		loco = new Locomotive(grossWeight, locoClassification);
		d.addCarriage(loco);
		int newPassengers = 20;
		assertEquals(newPassengers, (int)d.board(newPassengers));
		
	}
	
	/**
	 * ++++++++++++++
	 * | locomotive |
	 * ++++++++++++++
	 * Test firstCarriage() of a train with just one locomotive carriage
	 * @throws TrainException 
	 */
	@Test
	public void testFirstCarriageOfLocomotive() throws TrainException{
		d = new DepartingTrain();
		loco = new Locomotive(grossWeight, locoClassification);
		d.addCarriage(loco);
		assertEquals(d.firstCarriage(), loco);
	}	
	
	/**
	 * ++++++++++++++
	 * | locomotive |
	 * ++++++++++++++
	 * Test nextCarriage() of a train with just one locomotive carriage
	 * @throws TrainException 
	 */
	@Test
	public void testNextCarriageOfLocomotive() throws TrainException{
		d = new DepartingTrain();
		loco = new Locomotive(grossWeight, locoClassification);
		d.addCarriage(loco);
		d.firstCarriage();
		assertNull(d.nextCarriage());
	}	
	
	/**
	 * ++++++++++++++
	 * | locomotive |
	 * ++++++++++++++
	 * Test number of seats of a train with just one locomotive carriage
	 * @throws TrainException 
	 */
	@Test
	public void testNumberOfSeatsOfLocomotive() throws TrainException{
		d = new DepartingTrain();
		loco = new Locomotive(grossWeight, locoClassification);
		d.addCarriage(loco);
		assertEquals(0, (int)d.numberOfSeats());
	}
	
	/**
	 * ++++++++++++++
	 * | locomotive |
	 * ++++++++++++++
	 * Test number of passengers on board of a train with just
	 *   one locomotive carriage
	 * @throws TrainException 
	 */
	@Test
	public void testNumberOnBoardOfLocomotive() throws TrainException{
		d = new DepartingTrain();
		loco = new Locomotive(grossWeight, locoClassification);
		d.addCarriage(loco);
		assertEquals(0, (int)d.numberOnBoard());
	}	
	
	/**
	 * ++++++++++++++
	 * | locomotive |
	 * ++++++++++++++
	 * Test of removing the only locomotive carriage of a train
	 * @throws TrainException 
	 */
	@Test
	public void testRemoveCarriageOfLocomotive() throws TrainException{
		d = new DepartingTrain();
		loco = new Locomotive(grossWeight, locoClassification);
		d.addCarriage(loco);
		d.removeCarriage();
	}	
	
	/**
	 * ++++++++++++++   +++++++++++++
	 * | locomotive |===| passenger |
	 * ++++++++++++++---+++++++++++++
	 * Test add passenger carriage after one locomotive
	 * @throws TrainException 
	 */
	@Test
	public void testAddPassengerCarriageAfterLocomotive() throws TrainException{
		d = new DepartingTrain();
		loco = new Locomotive(grossWeight, locoClassification);
		d.addCarriage(loco);
		passenger1 = new PassengerCar(grossWeight, passenger1Seats);
		d.addCarriage(passenger1);
		assertEquals(loco, d.firstCarriage());
		assertEquals(passenger1, d.nextCarriage());
		assertNull(d.nextCarriage());
	}
	
	/**
	 * ++++++++++++++   +++++++++++++   ++++++++++++++
	 * | locomotive |===| passenger |===| locomotive |
	 * ++++++++++++++---+++++++++++++---++++++++++++++
	 * Test add another locomotive carriage after drawn above
	 * @throws TrainException 
	 */
	@Test (expected = TrainException.class)
	public void testAddAnotherLocomotiveAfterLP() throws TrainException{
		d = new DepartingTrain();
		loco = new Locomotive(grossWeight, locoClassification);
		d.addCarriage(loco);
		passenger1 = new PassengerCar(grossWeight, passenger1Seats);
		d.addCarriage(passenger1);
		loco2 = new Locomotive(grossWeight, loco2Classification);
		d.addCarriage(loco2);
	}
	
	
	/**
	 * ++++++++++++++   +++++++++++++
	 * | locomotive |===| passenger |
	 * ++++++++++++++---+++++++++++++
	 * Test trainCanMove() of the structure drawn above
	 * @throws TrainException 
	 */
	@Test
	public void testTrainCanMoveOfLP() throws TrainException{
		d = new DepartingTrain();
		loco = new Locomotive(grossWeight, locoClassification);
		d.addCarriage(loco);
		passenger1 = new PassengerCar(grossWeight, passenger1Seats);
		d.addCarriage(passenger1);
		assertTrue(d.trainCanMove());
		
		d = new DepartingTrain();
		loco2 = new Locomotive(grossWeight, loco2Classification);
		d.addCarriage(loco2);
		d.addCarriage(passenger1);
		assertFalse(d.trainCanMove());
	}
	
	/**
	 * ++++++++++++++   +++++++++++++
	 * | locomotive |===| passenger |
	 * ++++++++++++++---+++++++++++++
	 * Test number of passengers on board with just one passenger carriage
	 * @throws TrainException 
	 */
	@Test
	public void testNumberOnBoardOfLP() throws TrainException{
		d = new DepartingTrain();
		loco = new Locomotive(grossWeight, locoClassification);
		d.addCarriage(loco);
		passenger1 = new PassengerCar(grossWeight, passenger1Seats);
		d.addCarriage(passenger1);
		assertEquals(0, (int)d.numberOnBoard());
	}
	
	/**
	 * ++++++++++++++   +++++++++++++
	 * | locomotive |===| passenger |
	 * ++++++++++++++---+++++++++++++
	 * Test boarding passengers with the structure drawn above
	 * @throws TrainException 
	 */
	@Test
	public void testBoardingPassengersOfLP() throws TrainException{
		d = new DepartingTrain();
		loco = new Locomotive(grossWeight, locoClassification);
		d.addCarriage(loco);
		passenger1 = new PassengerCar(grossWeight, passenger1Seats);
		d.addCarriage(passenger1);
		assertEquals(0, (int)d.numberOnBoard());
		int newPassengers = 20;
		d.board(newPassengers);
		assertEquals(newPassengers > d.numberOfSeats() ? d.numberOfSeats() : newPassengers,
				(int)d.numberOnBoard());
		d.board(newPassengers);
		assertEquals(newPassengers*2 > d.numberOfSeats() ? d.numberOfSeats() : newPassengers*2, 
				(int)d.numberOnBoard());
		d.board(newPassengers);
		assertEquals(newPassengers*3 > d.numberOfSeats() ? d.numberOfSeats() : newPassengers*3,
				(int)d.numberOnBoard());
	}
	
	/**
	 * ++++++++++++++   +++++++++++++
	 * | locomotive |===| passenger |
	 * ++++++++++++++---+++++++++++++
	 * Test number of fixed seats of the structure drawn aboe
	 * @throws TrainException 
	 */
	@Test
	public void testNumberOfSeatsOfLP() throws TrainException{
		d = new DepartingTrain();
		loco = new Locomotive(grossWeight, locoClassification);
		d.addCarriage(loco);
		passenger1 = new PassengerCar(grossWeight, passenger1Seats);
		d.addCarriage(passenger1);
		assertEquals((int)passenger1Seats, (int)d.numberOfSeats());
	}
	
	/**
	 * ++++++++++++++   +++++++++++++   +++++++++++
	 * | locomotive |===| passenger |===| freight |
	 * ++++++++++++++---+++++++++++++---+++++++++++
	 * Test adding a freight carriage after locomotive and passenger carriages
	 * @throws TrainException 
	 */
	@Test
	public void testAddingFreightCarriageAfterLP() throws TrainException{
		d = new DepartingTrain();
		loco = new Locomotive(grossWeight, locoClassification);
		d.addCarriage(loco);
		passenger1 = new PassengerCar(grossWeight, passenger1Seats);
		d.addCarriage(passenger1);
		freight1 = new FreightCar(grossWeight, freight1Type);
		d.addCarriage(freight1);
		assertEquals(loco, d.firstCarriage());
		assertEquals(passenger1, d.nextCarriage());
		assertEquals(freight1, d.nextCarriage());
		assertNull(d.nextCarriage());
	}
	
	/**
	 * ++++++++++++++   +++++++++++++   +++++++++++   +++++++++++++
	 * | locomotive |===| passenger |===| freight |===| passenger |
	 * ++++++++++++++---+++++++++++++---+++++++++++---+++++++++++++
	 * Test adding passenger carriage after after a freight carriage
	 * @throws TrainException 
	 */
	@Test(expected = TrainException.class)
	public void testAddingPassengerCarriageAfterLPF() throws TrainException{
		d = new DepartingTrain();
		loco = new Locomotive(grossWeight, locoClassification);
		d.addCarriage(loco);
		passenger1 = new PassengerCar(grossWeight, passenger1Seats);
		d.addCarriage(passenger1);
		freight1 = new FreightCar(grossWeight, freight1Type);
		d.addCarriage(freight1);
		passenger2 = new PassengerCar(grossWeight, passenger2Seats);
		d.addCarriage(passenger2);
	}
	
	
	/**
	 * ++++++++++++++   +++++++++++++   +++++++++++   ++++++++++++++
	 * | locomotive |===| passenger |===| freight |===| locomotive |
	 * ++++++++++++++---+++++++++++++---+++++++++++---++++++++++++++
	 * Test adding a locomotive carriage after locomotive and passenger carriages
	 * @throws TrainException 
	 */
	@Test(expected = TrainException.class)
	public void testAddingLocomotiveAfterLPF() throws TrainException{
		d = new DepartingTrain();
		loco = new Locomotive(grossWeight, locoClassification);
		d.addCarriage(loco);
		passenger1 = new PassengerCar(grossWeight, passenger1Seats);
		d.addCarriage(passenger1);
		freight1 = new FreightCar(grossWeight, freight1Type);
		d.addCarriage(freight1);
		loco2 = new Locomotive(grossWeight, loco2Classification);
		d.addCarriage(loco2);
	}
	
	/**
	 * ++++++++++++++   +++++++++++++   +++++++++++
	 * | locomotive |===| passenger |===| freight |
	 * ++++++++++++++---+++++++++++++---+++++++++++
	 * Test trainCanMove() of the structure drawn above
	 * @throws TrainException 
	 */
	@Test
	public void testTrainCanMoveOfLPF() throws TrainException{
		d = new DepartingTrain();
		loco = new Locomotive(grossWeight, locoClassification);
		d.addCarriage(loco);
		passenger1 = new PassengerCar(grossWeight, passenger1Seats);
		d.addCarriage(passenger1);
		freight1 = new FreightCar(grossWeight, freight1Type);
		d.addCarriage(freight1);
		assertTrue(d.trainCanMove());
		
		d = new DepartingTrain();
		loco2 = new Locomotive(grossWeight, loco2Classification);
		d.addCarriage(loco2);
		passenger1 = new PassengerCar(grossWeight, passenger1Seats);
		d.addCarriage(passenger1);
		freight1 = new FreightCar(grossWeight, freight1Type);
		d.addCarriage(freight1);
		assertFalse(d.trainCanMove());
	}
	
	/**
	 * ++++++++++++++   +++++++++++++   +++++++++++
	 * | locomotive |===| passenger |===| freight |
	 * ++++++++++++++---+++++++++++++---+++++++++++
	 * Test removing freight carriage of the structure drawn above
	 * @throws TrainException 
	 */
	@Test
	public void testRemovingFreightOfLPF() throws TrainException{
		d = new DepartingTrain();
		loco = new Locomotive(grossWeight, locoClassification);
		d.addCarriage(loco);
		passenger1 = new PassengerCar(grossWeight, passenger1Seats);
		d.addCarriage(passenger1);
		freight1 = new FreightCar(grossWeight, freight1Type);
		d.addCarriage(freight1);
		d.removeCarriage();        // =====================
	}
	
	/**
	 * ++++++++++++++   +++++++++++++   +++++++++++
	 * | locomotive |===| passenger |===| freight |
	 * ++++++++++++++---+++++++++++++---+++++++++++
	 *                        |
	 *                        |
	 *                        V
	 * ++++++++++++++   +++++++++++++
	 * | locomotive |===| passenger |
	 * ++++++++++++++---+++++++++++++
	 *                        |
	 *                        |
	 *                        V
	 * ++++++++++++++   +++++++++++++   +++++++++++++
	 * | locomotive |===| passenger |===| passenger |
	 * ++++++++++++++---+++++++++++++---+++++++++++++
	 * 
	 * Test the changes drawn above
	 * @throws TrainException 
	 */
	@Test
	public void testFromLPFtoLPtoLPP() throws TrainException{
		d = new DepartingTrain();
		loco = new Locomotive(grossWeight, locoClassification);
		d.addCarriage(loco);
		passenger1 = new PassengerCar(grossWeight, passenger1Seats);
		d.addCarriage(passenger1);
		freight1 = new FreightCar(grossWeight, freight1Type);
		d.addCarriage(freight1);
		d.removeCarriage();
		passenger2 = new PassengerCar(grossWeight, passenger2Seats);
		d.addCarriage(passenger2);
	}
	
	/**
	 * ++++++++++++++   +++++++++++++   +++++++++++
	 * | locomotive |===| passenger |===| freight |
	 * ++++++++++++++---+++++++++++++---+++++++++++
	 *                        |
	 *                        |
	 *                        V
	 * ++++++++++++++   +++++++++++++
	 * | locomotive |===| passenger |
	 * ++++++++++++++---+++++++++++++
	 *                        |
	 *                        |
	 *                        V
	 * ++++++++++++++   +++++++++++++   +++++++++++++   ++++++++++++++
	 * | locomotive |===| passenger |===| passenger |===| locomotive |
	 * ++++++++++++++---+++++++++++++---+++++++++++++---++++++++++++++
	 * 
	 * Test Adding Locomotive after the changes drawn above
	 * @throws TrainException 
	 */
	@Test (expected = TrainException.class)
	public void testFromLPFtoLPtoLPPtoLPPL() throws TrainException{
		d = new DepartingTrain();
		loco = new Locomotive(grossWeight, locoClassification);
		d.addCarriage(loco);
		passenger1 = new PassengerCar(grossWeight, passenger1Seats);
		d.addCarriage(passenger1);
		freight1 = new FreightCar(grossWeight, freight1Type);
		d.addCarriage(freight1);
		d.removeCarriage();
		passenger2 = new PassengerCar(grossWeight, passenger2Seats);
		d.addCarriage(passenger2);
		loco2 = new Locomotive(grossWeight, loco2Classification);
		d.addCarriage(loco2);
	}
	
	/**
	 * ++++++++++++++   +++++++++++++   +++++++++++
	 * | locomotive |===| passenger |===| freight |
	 * ++++++++++++++---+++++++++++++---+++++++++++
	 *                        |
	 *                        |
	 *                        V
	 * ++++++++++++++   +++++++++++++
	 * | locomotive |===| passenger |
	 * ++++++++++++++---+++++++++++++
	 *                        |
	 *                        |
	 *                        V
	 * ++++++++++++++   +++++++++++++   +++++++++++++
	 * | locomotive |===| passenger |===| passenger |
	 * ++++++++++++++---+++++++++++++---+++++++++++++
     *                        |
     *                        |
     *                        V
	 * ++++++++++++++   +++++++++++++   +++++++++++++   +++++++++++
	 * | locomotive |===| passenger |===| passenger |===| freight |
	 * ++++++++++++++---+++++++++++++---+++++++++++++---+++++++++++
	 * Test the changes drawn above
	 * @throws TrainException 
	 */
	@Test
	public void testFromLPFtoLPtoLPPtoLPPF() throws TrainException{
		d = new DepartingTrain();
		loco = new Locomotive(grossWeight, locoClassification);
		d.addCarriage(loco);
		passenger1 = new PassengerCar(grossWeight, passenger1Seats);
		d.addCarriage(passenger1);
		freight1 = new FreightCar(grossWeight, freight1Type);
		d.addCarriage(freight1);
		d.removeCarriage();
		passenger2 = new PassengerCar(grossWeight, passenger2Seats);
		d.addCarriage(passenger2);
		d.addCarriage(freight1);
	}
	
	/**
	 * ++++++++++++++   +++++++++++++   +++++++++++
	 * | locomotive |===| passenger |===| freight |
	 * ++++++++++++++---+++++++++++++---+++++++++++
	 *                        |
	 *                        |
	 *                        V
	 * ++++++++++++++   +++++++++++++
	 * | locomotive |===| passenger |
	 * ++++++++++++++---+++++++++++++
	 *                        |
	 *                        |
	 *                        V
	 * ++++++++++++++   +++++++++++++   +++++++++++++
	 * | locomotive |===| passenger |===| passenger |
	 * ++++++++++++++---+++++++++++++---+++++++++++++
     *                        |
     *                        |
     *                        V
	 * ++++++++++++++   +++++++++++++   +++++++++++++   +++++++++++
	 * | locomotive |===| passenger |===| passenger |===| freight |
	 * ++++++++++++++---+++++++++++++---+++++++++++++---+++++++++++
	 * Test Adding a locomotive after the changes above
	 * @throws TrainException 
	 */
	@Test (expected = TrainException.class)
	public void testFromLPFtoLPtoLPPtoLPPFtoLPPFL() throws TrainException{
		d = new DepartingTrain();
		loco = new Locomotive(grossWeight, locoClassification);
		d.addCarriage(loco);
		passenger1 = new PassengerCar(grossWeight, passenger1Seats);
		d.addCarriage(passenger1);
		freight1 = new FreightCar(grossWeight, freight1Type);
		d.addCarriage(freight1);
		d.removeCarriage();
		passenger2 = new PassengerCar(grossWeight, passenger2Seats);
		d.addCarriage(passenger2);
		d.addCarriage(freight1);
		loco2 = new Locomotive(grossWeight, loco2Classification);
		d.addCarriage(loco2);
	}
	
	/**
	 * ++++++++++++++   +++++++++++++   +++++++++++
	 * | locomotive |===| passenger |===| freight |
	 * ++++++++++++++---+++++++++++++---+++++++++++
	 *                        |
	 *                        |
	 *                        V
	 * ++++++++++++++   +++++++++++++
	 * | locomotive |===| passenger |
	 * ++++++++++++++---+++++++++++++
	 *                        |
	 *                        |
	 *                        V
	 * ++++++++++++++   +++++++++++++   +++++++++++++
	 * | locomotive |===| passenger |===| passenger |
	 * ++++++++++++++---+++++++++++++---+++++++++++++
     *                        |
     *                        |
     *                        V
	 * ++++++++++++++   +++++++++++++   +++++++++++++   +++++++++++
	 * | locomotive |===| passenger |===| passenger |===| freight |
	 * ++++++++++++++---+++++++++++++---+++++++++++++---+++++++++++
	 * Test Adding a passenger carriage after the changes above
	 * @throws TrainException 
	 */
	@Test (expected = TrainException.class)
	public void testFromLPFtoLPtoLPPtoLPPFtoLPPFP() throws TrainException{
		d = new DepartingTrain();
		loco = new Locomotive(grossWeight, locoClassification);
		d.addCarriage(loco);
		passenger1 = new PassengerCar(grossWeight, passenger1Seats);
		d.addCarriage(passenger1);
		freight1 = new FreightCar(grossWeight, freight1Type);
		d.addCarriage(freight1);
		d.removeCarriage();
		passenger2 = new PassengerCar(grossWeight, passenger2Seats);
		d.addCarriage(passenger2);
		d.addCarriage(freight1);
		RollingStock passenger3 = new PassengerCar(grossWeight, passenger1Seats);
		d.addCarriage(passenger3);
	}
	
	/**
	 * ++++++++++++++   +++++++++++++   +++++++++++
	 * | locomotive |===| passenger |===| freight |
	 * ++++++++++++++---+++++++++++++---+++++++++++
	 *                        |
	 *                        |
	 *                        V
	 * ++++++++++++++   +++++++++++++
	 * | locomotive |===| passenger |
	 * ++++++++++++++---+++++++++++++
	 *                        |
	 *                        |
	 *                        V
	 * ++++++++++++++   +++++++++++++   +++++++++++++
	 * | locomotive |===| passenger |===| passenger |
	 * ++++++++++++++---+++++++++++++---+++++++++++++
     *                        |
     *                        |
     *                        V
	 * ++++++++++++++   +++++++++++++   +++++++++++++   +++++++++++
	 * | locomotive |===| passenger |===| passenger |===| freight |
	 * ++++++++++++++---+++++++++++++---+++++++++++++---+++++++++++
	 * Test Adding a freight carriage after the changes above
	 * @throws TrainException 
	 */
	@Test
	public void testFromLPFtoLPtoLPPtoLPPFtoLPPFF() throws TrainException{
		d = new DepartingTrain();
		loco = new Locomotive(grossWeight, locoClassification);
		d.addCarriage(loco);
		passenger1 = new PassengerCar(grossWeight, passenger1Seats);
		d.addCarriage(passenger1);
		freight1 = new FreightCar(grossWeight, freight1Type);
		d.addCarriage(freight1);
		d.removeCarriage();
		passenger2 = new PassengerCar(grossWeight, passenger2Seats);
		d.addCarriage(passenger2);
		d.addCarriage(freight1);
		freight2 = new FreightCar(grossWeight, freight2Type);
		d.addCarriage(freight2);
	}
	
	/**
	 * ++++++++++++++   +++++++++++
	 * | locomotive |===| freight |
	 * ++++++++++++++---+++++++++++
	 * Test adding a freight carriage after just one locomotive
	 * @throws TrainException 
	 */
	@Test
	public void testAddFreightAfterL() throws TrainException{
		d = new DepartingTrain();
		loco = new Locomotive(grossWeight, locoClassification);
		d.addCarriage(loco);
		freight1 = new FreightCar(grossWeight, freight1Type);
		d.addCarriage(freight1);
		assertEquals(loco, d.firstCarriage());
		assertEquals(freight1, d.nextCarriage());
		assertNull(d.nextCarriage());
	}
	
	/**
	 * ++++++++++++++   +++++++++++   +++++++++++++
	 * | locomotive |===| freight |===| passenger |
	 * ++++++++++++++---+++++++++++---+++++++++++++
	 * Test adding a passenger carriage after a locomotive and a freight carriage
	 * @throws TrainException 
	 */
	@Test (expected = TrainException.class)
	public void testAddPassengerAfterLF() throws TrainException{
		d = new DepartingTrain();
		loco = new Locomotive(grossWeight, locoClassification);
		d.addCarriage(loco);
		freight1 = new FreightCar(grossWeight, freight1Type);
		d.addCarriage(freight1);
		passenger1 = new PassengerCar(grossWeight, passenger1Seats);
		d.addCarriage(passenger1);
	}
	
	/**
	 * ++++++++++++++   +++++++++++   ++++++++++++++
	 * | locomotive |===| freight |===| locomotive |
	 * ++++++++++++++---+++++++++++---++++++++++++++
	 * Test adding another locomotive after a locomotive and a freight carriage
	 * @throws TrainException 
	 */
	@Test
	public void testAddLocomotiveAfterLF() throws TrainException{
		d = new DepartingTrain();
		loco = new Locomotive(grossWeight, locoClassification);
		d.addCarriage(loco);
		freight1 = new FreightCar(grossWeight, freight1Type);
		d.addCarriage(freight1);
	}
	
	/**
	 * ++++++++++++++   +++++++++++
	 * | locomotive |===| freight |
	 * ++++++++++++++---+++++++++++
	 * Test boarding passengers on the structure drawn above
	 * @throws TrainException 
	 */
	@Test 
	public void testBoardingOnLF() throws TrainException{
		d = new DepartingTrain();
		loco = new Locomotive(grossWeight, locoClassification);
		d.addCarriage(loco);
		freight1 = new FreightCar(grossWeight, freight1Type);
		d.addCarriage(freight1);
		int newPassengers = 20;
		assertEquals(newPassengers, (int)d.board(newPassengers));
	}
	
	/**
	 * ++++++++++++++   +++++++++++
	 * | locomotive |===| freight |
	 * ++++++++++++++---+++++++++++
	 * Test number of seats of structure drawn above
	 * @throws TrainException 
	 */
	@Test 
	public void testNumberOfSeatsOfLF() throws TrainException{
		d = new DepartingTrain();
		loco = new Locomotive(grossWeight, locoClassification);
		d.addCarriage(loco);
		freight1 = new FreightCar(grossWeight, freight1Type);
		d.addCarriage(freight1);
		assertEquals(0, (int)d.numberOfSeats());
	}
	
	/**
	 * ++++++++++++++   +++++++++++
	 * | locomotive |===| freight |
	 * ++++++++++++++---+++++++++++
	 * Test number of passengers on board of structure drawn above
	 * @throws TrainException 
	 */
	@Test 
	public void testNumberOnBoardOfLF() throws TrainException{
		d = new DepartingTrain();
		loco = new Locomotive(grossWeight, locoClassification);
		d.addCarriage(loco);
		freight1 = new FreightCar(grossWeight, freight1Type);
		d.addCarriage(freight1);
		assertEquals(0, (int)d.numberOnBoard());
	}
	
	/**
	 * ++++++++++++++   +++++++++++
	 * | locomotive |===| freight |
	 * ++++++++++++++---+++++++++++
	 *           |
	 *           |
	 *           V
	 * ++++++++++++++
	 * | locomotive |
	 * ++++++++++++++
	 *           |
	 *           |
	 *           V
	 * ++++++++++++++   +++++++++++++
	 * | locomotive |===| passenger |
	 * ++++++++++++++---+++++++++++++
	 *           |
	 *           |
	 *           V
	 * ++++++++++++++   +++++++++++++   +++++++++++
	 * | locomotive |===| passenger |===| freight |
	 * ++++++++++++++---+++++++++++++---+++++++++++
	 * 
	 * Test the change drawn above
	 * @throws TrainException 
	 */
	@Test 
	public void testFromLFtoLtoLPtoLPF() throws TrainException{
		d = new DepartingTrain();
		loco = new Locomotive(grossWeight, locoClassification);
		d.addCarriage(loco);
		freight1 = new FreightCar(grossWeight, freight1Type);
		d.addCarriage(freight1);
		d.removeCarriage();
		passenger1 = new PassengerCar(grossWeight, passenger1Seats);
		d.addCarriage(passenger1);
		d.addCarriage(freight1);
		assertEquals(loco, d.firstCarriage());
		assertEquals(passenger1, d.nextCarriage());
		assertEquals(freight1, d.nextCarriage());
		assertNull(d.nextCarriage());
	}
	
	/**
	 * ++++++++++++++   +++++++++++
	 * | locomotive |===| freight |
	 * ++++++++++++++---+++++++++++
	 *           |
	 *           |
	 *           V
	 * ++++++++++++++
	 * | locomotive |
	 * ++++++++++++++
	 *           |
	 *           |
	 *           V
	 * ++++++++++++++   +++++++++++++
	 * | locomotive |===| passenger |
	 * ++++++++++++++---+++++++++++++
	 *           |
	 *           |
	 *           V
	 * ++++++++++++++   +++++++++++++   +++++++++++
	 * | locomotive |===| passenger |===| freight |
	 * ++++++++++++++---+++++++++++++---+++++++++++
	 * 
	 * Test adding another passenger carriage after the change above
	 * @throws TrainException 
	 */
	@Test (expected = TrainException.class)
	public void testFromLFtoLtoLPtoLPFtoLPFP() throws TrainException{
		d = new DepartingTrain();
		loco = new Locomotive(grossWeight, locoClassification);
		d.addCarriage(loco);
		freight1 = new FreightCar(grossWeight, freight1Type);
		d.addCarriage(freight1);
		d.removeCarriage();
		passenger1 = new PassengerCar(grossWeight, passenger1Seats);
		d.addCarriage(passenger1);
		d.addCarriage(freight1);
		passenger2 = new PassengerCar(grossWeight, passenger2Seats);
		d.addCarriage(passenger2);
	}
	
	/**
	 * ++++++++++++++   +++++++++++
	 * | locomotive |===| freight |
	 * ++++++++++++++---+++++++++++
	 *           |
	 *           |
	 *           V
	 * ++++++++++++++
	 * | locomotive |
	 * ++++++++++++++
	 *           |
	 *           |
	 *           V
	 * ++++++++++++++   +++++++++++++
	 * | locomotive |===| passenger |
	 * ++++++++++++++---+++++++++++++
	 *           |
	 *           |
	 *           V
	 * ++++++++++++++   +++++++++++++   +++++++++++
	 * | locomotive |===| passenger |===| freight |
	 * ++++++++++++++---+++++++++++++---+++++++++++
	 * 
	 * Test adding another locomotive after the change above
	 * @throws TrainException 
	 */
	@Test (expected = TrainException.class)
	public void testFromLFtoLtoLPtoLPFtoLPFL() throws TrainException{
		d = new DepartingTrain();
		loco = new Locomotive(grossWeight, locoClassification);
		d.addCarriage(loco);
		freight1 = new FreightCar(grossWeight, freight1Type);
		d.addCarriage(freight1);
		d.removeCarriage();
		passenger1 = new PassengerCar(grossWeight, passenger1Seats);
		d.addCarriage(passenger1);
		d.addCarriage(freight1);
		loco2 = new Locomotive(grossWeight, loco2Classification);
		d.addCarriage(loco2);
	}
	
	/**
	 * ++++++++++++++   +++++++++++
	 * | locomotive |===| freight |
	 * ++++++++++++++---+++++++++++
	 *           |
	 *           |
	 *           V
	 * ++++++++++++++
	 * | locomotive |
	 * ++++++++++++++
	 *           |
	 *           |
	 *           V
	 * ++++++++++++++   +++++++++++++
	 * | locomotive |===| passenger |
	 * ++++++++++++++---+++++++++++++
	 *           |
	 *           |
	 *           V
	 * ++++++++++++++   +++++++++++++   +++++++++++
	 * | locomotive |===| passenger |===| freight |
	 * ++++++++++++++---+++++++++++++---+++++++++++
	 *           |
	 *           |
	 *           V
	 * ++++++++++++++   +++++++++++++   +++++++++++   +++++++++++
	 * | locomotive |===| passenger |===| freight |===| freight |
	 * ++++++++++++++---+++++++++++++---+++++++++++---+++++++++++
	 * Test making the change drawn above
	 * @throws TrainException 
	 */
	@Test
	public void testFromLFtoLtoLPtoLPFtoLPFF() throws TrainException{
		d = new DepartingTrain();
		loco = new Locomotive(grossWeight, locoClassification);
		d.addCarriage(loco);
		freight1 = new FreightCar(grossWeight, freight1Type);
		d.addCarriage(freight1);
		d.removeCarriage();
		passenger1 = new PassengerCar(grossWeight, passenger1Seats);
		d.addCarriage(passenger1);
		d.addCarriage(freight1);
		freight2 = new FreightCar(grossWeight, freight2Type);
		d.addCarriage(freight2);
		assertEquals(loco, d.firstCarriage());
		assertEquals(passenger1, d.nextCarriage());
		assertEquals(freight1, d.nextCarriage());
		assertEquals(freight2, d.nextCarriage());
	}
	
}
