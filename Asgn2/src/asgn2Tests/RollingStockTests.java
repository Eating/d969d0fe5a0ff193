/**
 * @author Yiting Zhang
 * 
 * @studentNumber: 8779210
 * 
 * @version 1.0
 */
package asgn2Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn2Exceptions.TrainException;
import asgn2RollingStock.FreightCar;
import asgn2RollingStock.Locomotive;
import asgn2RollingStock.PassengerCar;


public class RollingStockTests {

	@Before
	public void setUp() throws Exception {

	}

	/**
	 * Test constructor of Locomotive with valid parameter.
	 * 
	 * @throws TrainException
	 */
	@Test
	public void testLocomotive_ValidParameter() throws TrainException {
		new Locomotive(100, "4S");
	}

	/**
	 * Test constructor of Locomotive with a zero grossweight, expecting to
	 * throw TrainException.
	 * 
	 * @throws TrainException
	 */
	@Test(expected = TrainException.class)
	public void testLocomotive_ZeroGrossweight() throws TrainException {
		new Locomotive(0, "4S");
	}

	/**
	 * Test constructor of Locomotive with a negative grossweight, expecting to
	 * throw TrainException.
	 * 
	 * @throws TrainException
	 */
	@Test(expected = TrainException.class)
	public void testLocomotive_NegativeGrossweight() throws TrainException {
		new Locomotive(-100, "4S");
	}

	/**
	 * Test constructor of Locomotive with invalid power out of the range of
	 * 1-9, expecting to throw TrainException.
	 * 
	 * @throws TrainException
	 */
	@Test(expected = TrainException.class)
	public void testLocomotive_InvalidPowerClass() throws TrainException {
		new Locomotive(100, "11S");
	}

	/**
	 * Test constructor of Locomotive with an invalid engine type, expecting to
	 * throw TrainException.
	 * 
	 * @throws TrainException
	 */
	@Test(expected = TrainException.class)
	public void testLocomotive_InvalidEngineType() throws TrainException {
		new Locomotive(100, "4A");
	}

	/**
	 * Test constructor of Locomotive with an invalid classification, expecting
	 * to throw TrainException.
	 * 
	 * @throws TrainException
	 */
	@Test(expected = TrainException.class)
	public void testLocomotive_InvalidClassification() throws TrainException {
		new Locomotive(100, "invalid");
	}

	/**
	 * Test Locomotive.power() to get the right power.
	 * 
	 * @throws TrainException
	 */
	@Test
	public void testLocomotive_GetRightPower() throws TrainException {
		Locomotive loco = new Locomotive(100, "5E");
		org.junit.Assert.assertEquals((int) 500, (int) loco.power());
	}

	/**
	 * Test Locomotive.getGrossWeight() to get the right gross weight.
	 * 
	 * @throws TrainException
	 */
	@Test
	public void testLocomotive_GetGrossWeight() throws TrainException {
		Locomotive loco = new Locomotive(100, "5E");
		org.junit.Assert.assertEquals((int) 100, (int) loco.getGrossWeight());
	}

	/**
	 * Test Locomotive.toString() to make sure it is overwritten.
	 * 
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	@Test
	public void testToStringHasOverrideLocomotive()
			throws NoSuchMethodException, SecurityException {
		String declaringClass = Locomotive.class.getMethod("toString")
				.getDeclaringClass().getName();
		assertEquals(declaringClass, "asgn2RollingStock.Locomotive");
	}

	/**
	 * Test the constructor of FreightCar with valid parameters.
	 * 
	 * @throws TrainException
	 */
	@Test
	public void testFreightCar_ValidGrossWeight() throws TrainException {
		new FreightCar(180, "G");
	}

	/**
	 * Test the constructor of FreightCar with zero gross weight, expecting to
	 * throw a TrainException.
	 * 
	 * @throws TrainException
	 */
	@Test(expected = TrainException.class)
	public void testFreightCar_ZeroGrossWeight() throws TrainException {
		new FreightCar(0, "R");
	}

	/**
	 * Test constructor of FreightCar with negative gross weight, expectiong to
	 * throw a TrainException.
	 * 
	 * @throws TrainException
	 */
	@Test(expected = TrainException.class)
	public void testFreightCar_NegativeGrossWeight() throws TrainException {
		new FreightCar(-10, "R");
	}

	/**
	 * Test the constructor of FerightCar with an invalid goods type that is not
	 * "G", "R" or "D", expecting to throw a TrainException.
	 * 
	 * @throws TrainException
	 */
	@Test(expected = TrainException.class)
	public void testFreightCar_InvalidGoodsType() throws TrainException {
		new FreightCar(190, "Z");
	}

	/**
	 * Test FreightCar.getGrossWeight() to get the right gross weight of a
	 * freigtcar.
	 * 
	 * @throws TrainException
	 */
	@Test
	public void testFreightCar_GetGrossWeight() throws TrainException {
		FreightCar frei = new FreightCar(190, "R");
		assertEquals((int) 190, (int) frei.getGrossWeight());

	}

	/**
	 * Test FreightCar.goodsType() to get the right goods type.
	 * 
	 * @throws TrainException
	 */
	@Test
	public void testFreightCar_GetGoodsType() throws TrainException {
		FreightCar frei = new FreightCar(190, "R");
		assertEquals("R", frei.goodsType());
	}

	/**
	 * Test FreightCar.toString() to make sure it is overwritten.
	 * 
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	@Test
	public void testToStringHasOverrideFreightCar()
			throws NoSuchMethodException, SecurityException {
		String declaringClass = FreightCar.class.getMethod("toString")
				.getDeclaringClass().getName();
		assertEquals(declaringClass, "asgn2RollingStock.FreightCar");
	}

	/**
	 * Test the constructor of PassengerCar with valid parameters.
	 * 
	 * @throws TrainException
	 */
	@Test
	public void testPassengerCar_ValidParameter() throws TrainException {
		new PassengerCar(100, 40);
	}

	/**
	 * Test the constructor of PassengerCar with zero gross weight and an valid
	 * number of seats, expecting to throw a TrainException.
	 * 
	 * @throws TrainException
	 */
	@Test(expected = TrainException.class)
	public void testPassengerCar_ZeroGrossWeight() throws TrainException {
		new PassengerCar(0, 40);
	}

	/**
	 * Test the constructor of PassengerCar with negative gross weight and an
	 * valid number of seats, expecting to throw a TrainException.
	 * 
	 * @throws TrainException
	 */
	@Test(expected = TrainException.class)
	public void testPassengerCar_NegativeGrossWeight() throws TrainException {
		new PassengerCar(-100, 40);
	}

	/**
	 * Test the construcort of PassengerCar with valid gross weight and a
	 * negative number of seats, expecting to throw a TrainException.
	 * 
	 * @throws TrainException
	 */
	@Test(expected = TrainException.class)
	public void testPassengerCar_InvalidSeats() throws TrainException {
		new PassengerCar(100, -10);
	}

	/**
	 * Test the constructor of PassengerCar with valid gross weight and an zero
	 * number of seats.
	 * 
	 * @throws TrainException
	 */
	@Test
	public void testPassengerCar_ZeroSeats() throws TrainException {
		new PassengerCar(100, 0);
	}

	/**
	 * Test PassengerCar.board(int newPassengers) with negative number of
	 * passengers, expecting to throw a TrainException.
	 * 
	 * @throws TrainException
	 */
	@Test(expected = TrainException.class)
	public void testPassengerCar_NegativeNewPassengers() throws TrainException {
		PassengerCar pC = new PassengerCar(100, 40);
		pC.board(-10);
	}

	/**
	 * Test PassengerCar.board(int) with a value less than the number of seats,
	 * expecting to return 0.
	 * 
	 * @throws TrainException
	 */
	@Test
	public void testPassengerCar_LessNewPassengers() throws TrainException {
		PassengerCar pC = new PassengerCar(100, 40);
		assertEquals((int) 0, (int) pC.board(10));
	}

	/**
	 * Test PassengerCar.board(int) with a value more than the number of seats,
	 * expecting to return the right number of persons unable to board.
	 * 
	 * @throws TrainException
	 */
	@Test
	public void testPassengerCar_MoreNewPassengers() throws TrainException {
		PassengerCar pC = new PassengerCar(100, 40);
		assertEquals((int) 10, (int) pC.board(50));
	}

	/**
	 * Test PassengerCar.alight(int) with a value more than the number of
	 * persons on board, expecting to throw a TrainException.
	 * 
	 * @throws TrainException
	 */
	@Test(expected = TrainException.class)
	public void testPassengerCar_alightMoreThanOnBoard() throws TrainException {
		PassengerCar pC = new PassengerCar(100, 40);
		pC.board(10);
		pC.alight(20);
	}

	/**
	 * Test PassengerCar.alight(int) with a value less than the number of
	 * persons on board, expecting to return the right number of persons left on
	 * board.
	 * 
	 * @throws TrainException
	 */
	@Test
	public void testPassengerCar_Alight() throws TrainException {
		PassengerCar pC = new PassengerCar(100, 40);
		pC.board(10);
		pC.alight(2);
		assertEquals((int) 8, (int) pC.numberOnBoard());
	}

	/**
	 * Test PassengerCar.numberOnBoard() to return the right number of persons
	 * on board.
	 * 
	 * @throws TrainException
	 */
	@Test
	public void testPassengerCar_NumberOnBoardFirst() throws TrainException {
		PassengerCar pC = new PassengerCar(100, 30);
		pC.board(10);
		assertEquals((int) 10, (int) pC.numberOnBoard());
	}

	/**
	 * Test PassengerCar.numberOnBoard() to return the right number of persons
	 * on board.
	 * 
	 * @throws TrainException
	 */
	@Test
	public void testPassengerCar_NumberOnBoardSecond() throws TrainException {
		PassengerCar pC = new PassengerCar(100, 30);
		pC.board(10);
		pC.alight(3);
		assertEquals((int) 7, (int) pC.numberOnBoard());
	}

	/**
	 * Test PassengerCar.numberOfSeats() to get the right number of seats.
	 * 
	 * @throws TrainException
	 */
	@Test
	public void testPassengerCar_NumberOfSeats() throws TrainException {
		PassengerCar pC = new PassengerCar(100, 40);
		assertEquals((int) 40, (int) pC.numberOfSeats());
	}

	/**
	 * Test PassengerCar.toString() to make sure it is overwritten.
	 * 
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	@Test
	public void testToStringHasOverridePassengerCar()
			throws NoSuchMethodException, SecurityException {
		String declaringClass = PassengerCar.class.getMethod("toString")
				.getDeclaringClass().getName();
		assertEquals(declaringClass, "asgn2RollingStock.PassengerCar");
	}

}
