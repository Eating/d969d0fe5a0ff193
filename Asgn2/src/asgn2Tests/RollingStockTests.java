package asgn2Tests;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn2Exceptions.TrainException;
import asgn2RollingStock.FreightCar;
import asgn2RollingStock.Locomotive;
import asgn2RollingStock.PassengerCar;

/**
 * @author Yiting Zhang
 * @studentnumber: /////////////////////
 * 
 */

public class RollingStockTests {
	
	@Before
	public void setUp() throws Exception {
		
	}
	
	
	@Test
	public void testLocomotive_ValidParameter() throws TrainException {
		Locomotive loco = new Locomotive(100, "4S");
	}
	
	@Test(expected = TrainException.class)
	public void testLocomotive_ZeroGrossweight() throws TrainException{
		Locomotive loco = new Locomotive(0,"4S");
	}
	
	@Test(expected = TrainException.class)
	public void testLocomotive_NegativeGrossweight() throws TrainException{
		Locomotive loco = new Locomotive(-100,"4S");
	}
	@Test(expected = TrainException.class)
	public void testLocomotive_InvalidPowerClass() throws TrainException{
		Locomotive loco = new Locomotive(100,"11S");
	}
	@Test(expected = TrainException.class)
	public void testLocomotive_InvalidEngineType() throws TrainException{
		Locomotive loco = new Locomotive(0,"4A");
	}
	@Test(expected = TrainException.class)
	public void testLocomotive_InvalidClassification() throws TrainException{
		Locomotive loco = new Locomotive(0,"invalid");
	}
	
	@Test
	public void testLocomotive_GetRightPower() throws TrainException {
		Locomotive loco = new Locomotive(100,"5E");
		org.junit.Assert.assertEquals((int)500, (int)loco.power());
	}
	@Test
	public void testLocomotive_GetGrossWeight() throws TrainException {
		Locomotive loco = new Locomotive(100,"5E");
		org.junit.Assert.assertEquals((int)100, (int)loco.getGrossWeight());
	}
	@Test
	public void testToStringHasOverrideLocomotive() throws NoSuchMethodException, SecurityException {
		String declaringClass = Locomotive.class.getMethod("toString").getDeclaringClass().getName();
		assertEquals(declaringClass, "asgn2RollingStock.Locomotive");
	}
	
	/**
	 *  我是华丽丽的分割线======================================================
	 */	
	
	@Test
	public void testFreightCar_ValidGrossWeight() throws TrainException {
		FreightCar frei = new FreightCar(180,"G");
	}
	@Test(expected = TrainException.class)
	public void testFreightCar_ZeroGrossWeight() throws TrainException {
		FreightCar frei = new FreightCar(0,"R");
	}
	@Test(expected = TrainException.class)
	public void testFreightCar_NegativeGrossWeight() throws TrainException {
		FreightCar frei = new FreightCar(-10,"R");
	}
	@Test(expected = TrainException.class)
	public void testFreightCar_InvalidGoodsType() throws TrainException {
		FreightCar frei = new FreightCar(190,"Z");
	}
	@Test
	public void testFreightCar_GetGrossWeight() throws TrainException {
		FreightCar frei = new FreightCar(190,"R");
		assertEquals((int)190, (int)frei.getGrossWeight());
		
	}
	@Test
	public void testFreightCar_GetGoodsType() throws TrainException {
		FreightCar frei = new FreightCar(190,"R");
		assertEquals("R", frei.goodsType());
	}
	@Test
	public void testToStringHasOverrideFreightCar() throws NoSuchMethodException, SecurityException {
		String declaringClass = FreightCar.class.getMethod("toString").getDeclaringClass().getName();
		assertEquals(declaringClass, "asgn2RollingStock.FreightCar");
	}
	
	
/**
 *  我是华丽丽的分割线======================================================
 */
	
	@Test
	public void testPassengerCar_ValidParameter() throws TrainException{
		PassengerCar pC = new PassengerCar(100,40);
	}
	
	@Test(expected=TrainException.class)
	public void testPassengerCar_ZeroGrossWeight() throws TrainException{
		PassengerCar pC = new PassengerCar(0,40);
	}
	@Test(expected=TrainException.class)
	public void testPassengerCar_NegativeGrossWeight() throws TrainException{
		PassengerCar pC = new PassengerCar(-100,40);
	}
	
	@Test(expected=TrainException.class)
	public void testPassengerCar_InvalidSeats() throws TrainException{
		PassengerCar pC = new PassengerCar(100,-10);
	}
	@Test(expected=TrainException.class)
	public void testPassengerCar_NegativeNewPassengers() throws TrainException{
		PassengerCar pC = new PassengerCar(100,40);
		pC.board(-10);
	}
	
	
	@Test
	public void testPassengerCar_LessNewPassengers() throws TrainException{
		PassengerCar pC = new PassengerCar(100,40);
		assertEquals((int)0, (int)pC.board(10));
	}	
	@Test
	public void testPassengerCar_MoreNewPassengers() throws TrainException{
		PassengerCar pC = new PassengerCar(100,40);
		assertEquals((int)10, (int)pC.board(50));
	}
	@Test(expected=TrainException.class)
	public void testPassengerCar_alightMoreThanOnBoard() throws TrainException{
		PassengerCar pC = new PassengerCar(100,40);
		pC.board(10);
		pC.alight(20);
	}
	@Test
	public void testPassengerCar_Alight() throws TrainException{
		PassengerCar pC = new PassengerCar(100,40);
		pC.board(10);
		pC.alight(2);
		assertEquals((int)8, (int)pC.numberOnBoard());
	}
	
	@Test
	public void testPassengerCar_NumberOnBoardFirst() throws TrainException{
		PassengerCar pC = new PassengerCar(100,30);
		pC.board(10);
		assertEquals((int)10, (int)pC.numberOnBoard());
	}
	@Test
	public void testPassengerCar_NumberOnBoardSecond() throws TrainException{
		PassengerCar pC = new PassengerCar(100,30);
		pC.board(10);
		pC.alight(3);
		assertEquals((int)7, (int)pC.numberOnBoard());
	}
	
	@Test
	public void testPassengerCar_NumberOfSeats() throws TrainException{
		PassengerCar pC = new PassengerCar(100,40);
		assertEquals((int)40, (int)pC.numberOfSeats());
	}
   
	@Test
	public void testToStringHasOverridePassengerCar() throws NoSuchMethodException, SecurityException {
		String declaringClass = PassengerCar.class.getMethod("toString").getDeclaringClass().getName();
		assertEquals(declaringClass, "asgn2RollingStock.PassengerCar");
	}
	
	
	
	
	
	
	
	
	
	
	

}
