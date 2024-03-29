package bankingSystem;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="banking")
public class Bank_Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

private int Bank_id;
private String location;
private String bankName;

@ManyToMany( cascade = CascadeType.ALL)
private Collection<CustomerAccountDetails> customeraccountdetails ;

@OneToMany( cascade = CascadeType.ALL)
private Collection<Transactions> Transactiondetails ;


public Bank_Branch(int bank_id, String location, String bankName,
Collection<CustomerAccountDetails> customeraccountdetails, Collection<Transactions> transactiondetails) {
	super();
	Bank_id = bank_id;
	this.location = location;
	this.bankName = bankName;
	this.customeraccountdetails = customeraccountdetails;
	Transactiondetails = transactiondetails;
}

@Override
public String toString() {
	return "Bank_Branch [Bank_id=" + Bank_id + ", location=" + location + ", bankName=" + bankName
			+ ", customeraccountdetails=" + customeraccountdetails + ", Transactiondetails=" + Transactiondetails + "]";
}

public Collection<CustomerAccountDetails> getCustomeraccountdetails() {
	return customeraccountdetails;
}

public void setCustomeraccountdetails(Collection<CustomerAccountDetails> customeraccountdetails) {
	this.customeraccountdetails = customeraccountdetails;
}

public Collection<Transactions> getTransactiondetails() {
	return Transactiondetails;
}

public void setTransactiondetails(Collection<Transactions> transactiondetails) {
	Transactiondetails = transactiondetails;
}

public int getBank_id() {
	return Bank_id;
}

public void setBank_id(int bank_id) {
	Bank_id = bank_id;
}

public String getLocation() {
	return location;
}

public void setLocation(String location) {
	this.location = location;
}

public String getBankName() {
	return bankName;
}

public void setBankName(String bankName) {
	this.bankName = bankName;
}

public Collection<CustomerAccountDetails> getAccounts() {
	return customeraccountdetails;
}

public void setAccounts(HashSet<CustomerAccountDetails> accounts) {
	this.customeraccountdetails = (HashSet<CustomerAccountDetails>) accounts;
}





public Bank_Branch() {
	super();
}
 
}
