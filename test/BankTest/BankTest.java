package BankTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import junitlab.bank.AccountNotExistsException;
import junitlab.bank.Bank;
import junitlab.bank.impl.FirstNationalBank;
import junitlab.bank.impl.GreatSavingsBank;

public class BankTest {

	@Test
	public void testOpenAccount() throws AccountNotExistsException {
		GreatSavingsBank temp=new GreatSavingsBank();
		String stemp=temp.openAccount();
		long itemp=temp.getBalance(stemp);
		assertEquals(itemp,0);
		assertNotEquals(stemp, null);
		}
	
	@Test
	public void testUniqueAccount() {
		GreatSavingsBank temp1=new GreatSavingsBank();
		String stemp1=temp1.openAccount();
		String stemp2=temp1.openAccount();
		assertNotEquals(stemp2,stemp1);
	}
	
	@Test(expected=AccountNotExistsException.class)
	public void testInvalidAccount() throws AccountNotExistsException {
		GreatSavingsBank temp=new GreatSavingsBank();
		long templ=temp.getBalance("anyad");

	}
	
	@Test
	public void testDeposit() throws AccountNotExistsException {
		GreatSavingsBank temp=new GreatSavingsBank();
		String stemp=temp.openAccount();
		temp.deposit(stemp, 2000);
		assertEquals(temp.getBalance(stemp),2000);
	}
	
}
