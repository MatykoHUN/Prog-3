package BankTest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameters;

import junitlab.bank.AccountNotExistsException;
import junitlab.bank.NotEnoughFundsException;
import junitlab.bank.impl.FirstNationalBank;
import junitlab.bank.impl.GreatSavingsBank;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class BankParamTest {
	private int amount;
	private int rounded;
	private GreatSavingsBank temp;
	
	public BankParamTest(int amount,int rounded) {
		this.amount=amount;
		this.rounded=rounded;
	}
	@Test(expected=IllegalArgumentException.class)
	public void PositiveNumber() throws AccountNotExistsException {
		temp=new GreatSavingsBank();
		String test=temp.openAccount();
		temp.deposit(test, -5);
	}
	@Test(expected=IllegalArgumentException.class)
	public void PositiveNumverTransfer() throws AccountNotExistsException, NotEnoughFundsException {
		temp=new GreatSavingsBank();
		String test=temp.openAccount();
		String test2=temp.openAccount();
		temp.transfer(test, test2, -5);
	}
	
	@Test
	public void CloseAccount() throws AccountNotExistsException {
		temp=new GreatSavingsBank();
		String test=temp.openAccount();
		temp.closeAccount(test);
	}
	
	@Test
	public void CloseNotemptyAccount() throws AccountNotExistsException {
		temp=new GreatSavingsBank();
		String test=temp.openAccount();
		temp.deposit(test, 69);
		
		assertEquals(temp.closeAccount(test),false);
		
	}
	
	@Test
	public void testWithdrawRounding () throws AccountNotExistsException, NotEnoughFundsException{
		temp=new GreatSavingsBank();
		String test=temp.openAccount();
		temp.deposit(test, 10000);
		temp.withdraw(test, amount);
		assertEquals(temp.getBalance(test),10000-rounded);
	}
	
	@Parameters
	public static List<Object[]> parameters() {
	List<Object[]> params = new ArrayList<Object[]>();
	params.add(new Object[] {1100, 1100});
	params.add(new Object[] {1101, 1100});
	params.add(new Object[] {1149, 1100});
	params.add(new Object[] {1150, 1200});
	params.add(new Object[] {1151, 1200});
	params.add(new Object[] {1199, 1200});
	params.add(new Object[] {1200, 1200});
	return params;
	}
}
