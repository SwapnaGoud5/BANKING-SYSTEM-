package bankingSystem;


import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class CustomerAccountDetails {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int ACid;
	private String accountType;
    private String firstName;
    private String lastName;
    private double balance;
    private String gender;
    private String mobile_no;
    @ManyToOne	
    @JoinColumn(name="Bank_id")
    private Bank_Branch bank_branch;
    
	public CustomerAccountDetails(int aCid, String accountType, String firstName, String lastName, double balance,
			String gender, String mobile_no, Bank_Branch bank_branch) {
		super();
		ACid = aCid;
		this.accountType = accountType;
		this.firstName = firstName;
		this.lastName = lastName;
		this.balance = balance;
		this.gender = gender;
		this.mobile_no = mobile_no;
		this.bank_branch = bank_branch;
	}
	public int getACid() {
		return ACid;
	}
	public void setACid(int aCid) {
		ACid = aCid;
	}
	public Bank_Branch getBank_branch() {
		return bank_branch;
	}
	public void setBank_branch(Bank_Branch bank_branch) {
		this.bank_branch = bank_branch;
	}
	
	
	public String getMobile_no() {
		return mobile_no;
	}
	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
		public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
			public CustomerAccountDetails() {
		super();
	}
			public void setBank_branch(double d) {
				// TODO Auto-generated method stub
				
			}
	    
}


