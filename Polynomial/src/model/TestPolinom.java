package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class TestPolinom {
	public Polinom p1,p2;
	
	@Before
	public void setUp()
	{
		try{p1=new Polinom();
		p1.convertToPolinom("X^1+3X^2+2X^3+67");
		p2=new Polinom();
		p2.convertToPolinom("X^7+X^3+4X^1+1");
		}
		catch(WrongPolinom e)
		{
			assertNotNull(e);
		}
		
	}
	
	@Test
	public void testAddPolinom() {
		String s="";
		Polinom p=p1.addPolinom(p2);
		s=p.polinomString();
		System.out.println(s);
		assertEquals(s,"+1X^7+3X^3+3X^2+5X^1+68");
	}
	
	@Test
	public void testPolinomSub()
	{
		String s=p1.subPolinom(p2).polinomString();
		assertEquals(s,"1X^7+1X^3+3X^2+66");// ca sa nu aiba esec adaugam - in fata la 1X^7
	}
	
	@Test
	public void testPolinomMul()
	{
		String s=p1.mulPolinom(p2).polinomString();
		assertEquals(s,"+2X^10+3X^9+1X^8+67X^7+2X^6+3X^5+9X^4+81X^3+7X^2+269X^1+67");
	}
	
	@Test
	public void testPolinomDivision()
	{
	  String s=p1.divisionPolinom(p2);
	  assertEquals(s,"Restul este:+2X^3+3X^2+1X^1+67");
	}
	
	@Test
	public void testPolinomDerivation()
	{
		String s=p1.polinomDerivation().polinomString();
		assertEquals(s,"+6X^2+6X^1+1");
	}
	
	@Test 
	public void testPolinomIntegration()
	{
		String s=p1.polinomIntegration().polinomDoubleString();
		assertEquals(s,"+0.5X^4+1.0X^3+0.5X^2+67.0X^1");
	}
}
