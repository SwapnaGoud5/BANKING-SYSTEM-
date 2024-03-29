package bankingSystem;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String transactionType; // Deposit, withdrawal, etc.
    private double amount;

    @ManyToOne
    @JoinColumn(name = "ACid")
    private CustomerAccountDetails account;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public CustomerAccountDetails getAccount() {
		return account;
	}

	public void setAccount(CustomerAccountDetails account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", transactionType=" + transactionType + ", amount=" + amount + ", account="
				+ account + "]";
	}

	public Transactions(Long id, String transactionType, double amount, CustomerAccountDetails account) {
		super();
		this.id = id;
		this.transactionType = transactionType;
		this.amount = amount;
		this.account = account;
	}

	public Transactions() {
		super();
	}
	

}
