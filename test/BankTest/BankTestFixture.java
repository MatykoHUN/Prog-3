package BankTest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import junitlab.bank.impl.GreatSavingsBank;

import junitlab.bank.AccountNotExistsException;
import junitlab.bank.NotEnoughFundsException;
import junitlab.bank.impl.FirstNationalBank;

public class BankTestFixture {
	GreatSavingsBank temp;
	String szaml1;
	String szaml2;
	
	@Before
	public void SetUp() throws AccountNotExistsException {
		temp=new GreatSavingsBank();
		szaml1=temp.openAccount();
		szaml2=temp.openAccount();
		temp.deposit(szaml1, 1500);
		temp.deposit(szaml2, 12000);
		}
	
	@Test
	public void testTransfer() throws AccountNotExistsException, NotEnoughFundsException {
		temp.transfer(szaml2, szaml1, 3456);
		assertEquals(temp.getBalance(szaml1),4956);
		assertEquals(temp.getBalance(szaml2),8544);
	}
	
	@Test(expected=NotEnoughFundsException.class)
	public void testTransferWithoutEnoughFunds() throws AccountNotExistsException, NotEnoughFundsException {
			temp.transfer(szaml1, szaml1, 3456);
	}
}
