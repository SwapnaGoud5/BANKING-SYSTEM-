package bankingSystem;

	import java.util.InputMismatchException;
    import java.util.List;
	import java.util.Scanner;
    import javax.persistence.Query;
	import org.hibernate.Session;
	import org.hibernate.SessionFactory;
	import org.hibernate.Transaction;
	import org.hibernate.cfg.Configuration;


	public class BankingSystem {

	
		static Configuration configuration;
		static Session session;
		static SessionFactory sessionfactory;
		static Scanner scanner;
		
		public static void createAccount()
		{
			scanner = new Scanner(System.in);
			
			configuration = new Configuration(); //class reads both the entity class and config  file
			configuration.configure(); //checks config file syntax
			    	
			//Interface. it takes metedata and build connection
			sessionfactory = configuration.buildSessionFactory();
			//session -> time period b/n start and end.
			session = sessionfactory.openSession();
			
			CustomerAccountDetails c=new CustomerAccountDetails()	;		
			System.out.print("Enter customer first Name: ");
			String name = scanner.nextLine();
			c.setFirstName(name);
			
			System.out.print("Enter customer last Name: ");
			String lastname = scanner.nextLine();
			c.setLastName(lastname);
			
			System.out.print("Enter customer Gender: ");
			String gender = scanner.next();
			c.setGender(gender);
			
			System.out.println("Enter accountType: ");
			String type = scanner.next();
			c.setAccountType(type);
			
			System.out.println("Enter Mobile_no: ");
			String mbl = scanner.next();
			c.setMobile_no(mbl);
			
			System.out.println("Enter the blans: ");
			double blnc = scanner.nextDouble();
			c.setBalance(blnc);
			 
			
			Bank_Branch b=new  Bank_Branch();
			System.out.println("Enter the bank_id: ");
			int i= scanner.nextInt();
			b.setBank_id(i);
			

			session.save(c);
			session.save(b);
			
			Transaction transaction = session.beginTransaction();
			
			transaction.commit();
			
			// Check if the account is Added or not
	    	
	   	 if (c.getACid() > 0)
	        {
	            System.out.println(" CustomerAccount Added Successfully.");
	        }
	        else
	        {
	            System.out.println("Failed to Add CustomerAccount!");
	        }
		}
		
		
				
		
		 public static void viewCustomerById()
				{
					System.out.print("\nEnter Customer ACid: ");
					int id = scanner.nextInt();

					CustomerAccountDetails	 c = session.get(CustomerAccountDetails.class,id );
					
					if(c == null)
					{
						System.out.println("There is no Record Found For This ACID.");
					}
					else
					{
						System.out.println("\nCustomerAccountDetails Details:");
						System.out.println("==================");
						
						System.out.println("+----+------------------+-------------+----------------+----------------------+");
						System.out.println("| ACid   | accountType  | firsttName  | lastName |  balance  |  gender  |   mobile_no  | Bank_id   |");
						System.out.println("+----+------------------+-------------+----------------+----------------------++----+------------------+-------------+----------------+----------------------+------------------+");
						System.out.printf("| %-4s | %-4s | %-10s| %-8s  | %-5s | %-4s | %-4s |%-6s |\n",c.getACid(),c.getAccountType(),c.getFirstName(),c.getLastName(),c.getBalance(),c.getGender(),c.getMobile_no(),c.getBank_branch());
						System.out.println("+----+------------------+-------------+----------------+----------------------++----+------------------+-------------+----------------+----------------------+------------------+");
					}
				}

		 
		 
		 public static void viewAllCustomers()
				{	
					String hqlQuery = "from CustomerAccountDetails";
					
					List<CustomerAccountDetails> data = session.createQuery(hqlQuery, CustomerAccountDetails.class).list();
					
					if(data.isEmpty())
					{
						System.out.println("There is no Custmuer Found");
					}
					else
					{
						System.out.println("\nCustomerAccountDetails Details:");
						System.out.println("================");
						
						System.out.println("+----+------------------+-------------+----------------+----------------------++----------------------++----------------------+");
						System.out.println("| ACid | ActType | firstName | lastName | balanc3 | gender |Mobile_no | Bank_id |");
						System.out.println("+----+------------------+-------------+----------------+----------------------++----------------------++----------------------+");
					
					for(CustomerAccountDetails c : data)
					{
						System.out.printf("| %-4s | %-4s | %-10s| %-8s  | %-5s | %-4s | %-4s |%-6s |\n",c.getACid(),c.getAccountType(),c.getFirstName(),c.getLastName(),c.getBalance(),c.getGender(),c.getMobile_no(),c.getBank_branch());
					}
					
					
					System.out.println("+----+------------------+-------------+----------------+----------------------+");
					}

				}
		 
		 
			public static void readCustomer()
				{
					while(true)
					{
						System.out.println("\nSelect How You Want To Read Customer Record:");
						System.out.println("---------------------------------------------");
						System.out.println("1. View Particular Customer.");
						System.out.println("2. View All Customer .");
						System.out.println("3. Back.");
						System.out.print("\nEnter your Choice: ");
						
						try
						{
							int choice = scanner.nextInt();
							
							switch(choice)
							{
							case 1:
								viewCustomerById();
								System.out.println();
								break;
								
							case 2:
								viewAllCustomers();
								System.out.println();
								break;
								
							case 3:
								return;
								
							default:
								System.out.println("Please Enter a Valid Choice.");
								System.out.println();
								break;
							}
						}
						
						catch(InputMismatchException e)
						{
							System.out.println("Please Enter a Integer Value.");
							System.out.println();
							scanner.nextLine();
						}
					}
				}

			
			
	    public static void updateCustomerFirstName()
				{
					scanner = new Scanner(System.in);
					
					configuration = new Configuration();
					configuration.configure();
					
					SessionFactory sessionFactory = configuration.buildSessionFactory();
					Session session = sessionFactory.openSession();
					System.out.print("Enter Customer Id to Update FirstName: ");
					int id = scanner.nextInt();
					scanner.nextLine();
					
					CustomerAccountDetails c = session.get(CustomerAccountDetails.class, id); //returns Doctor type of object
					
					if( c == null)
					{
						System.out.println("There is no Record Found for This Id.");
					}
					else
					{
						Transaction transaction = session.beginTransaction();
						
						System.out.print("Enter the New Name for Customer_ACid "+id+": ");
						String newName = scanner.nextLine();
						c.setFirstName(newName);
						
						session.update(c);
//						session.merge(d);
						
						System.out.println(" Customer FirstName Updated Successfully.");
						transaction.commit();
						session.close();
//						scanner.close();
					}
				}
				
				
	    public static void updateCustomerLastName()
				{
					scanner = new Scanner(System.in);
					
					configuration = new Configuration();
					configuration.configure();
					
					SessionFactory sessionFactory = configuration.buildSessionFactory();
					Session session = sessionFactory.openSession();
					System.out.print("Enter Customer Id to Update LastName: ");
					int id = scanner.nextInt();
					scanner.nextLine();
					
					CustomerAccountDetails c = session.get(CustomerAccountDetails.class, id); //returns  type of object
					
					if( c == null)
					{
						System.out.println("There is no Record Found for This Id.");
					}
					else
					{
						Transaction transaction = session.beginTransaction();
						
						System.out.print("Enter the New Name for Customer_ACid "+id+": ");
						String newName = scanner.nextLine();
						c.setLastName(newName);
						
						session.update(c);
//						session.merge(d);
						
						System.out.println(" Customer LastName Updated Successfully.");
						transaction.commit();
						session.close();
//						scanner.close();
					}
				}
				

				
	    
			public static void updateCustomerGender()
				{
					scanner = new Scanner(System.in);
					
					configuration = new Configuration();
					configuration.configure();
					
					SessionFactory sessionFactory = configuration.buildSessionFactory();
					Session session = sessionFactory.openSession();
					System.out.print("Enter Customer ACid to Update Gender: ");
					int id = scanner.nextInt();
					scanner.nextLine();
					
					CustomerAccountDetails c = session.get(CustomerAccountDetails.class, id); 
					if( c == null)
					{
						System.out.println("There is no Record Found for This Id.");
					}
					else
					{
						Transaction transaction = session.beginTransaction();
						
						System.out.print("Enter the New Gender for Customer ACid "+id+": ");
						String gender = scanner.next();
						c.setGender(gender);
						session.update(c);
//						session.merge(d);
						
						System.out.println(" Customer Gender Updated Successfully.");
						transaction.commit();
						session.close();
//						scanner.close();
					}
				}
			

			public static void updateAccountType()
				{
					scanner = new Scanner(System.in);
					
					configuration = new Configuration();
					configuration.configure();
					
					SessionFactory sessionFactory = configuration.buildSessionFactory();
					Session session = sessionFactory.openSession();
					System.out.print("Enter  Customer ACid to Update AccountType: ");
					int id = scanner.nextInt();
					scanner.nextLine();
					
					CustomerAccountDetails c = session.get(CustomerAccountDetails.class, id); //returns Doctor type of object
					if( c == null)
					{
						System.out.println("There is no Account Found for This Id.");
					}
					else
					{
						Transaction transaction = session.beginTransaction();
						
						System.out.print("Enter the AccountType to be updated for Patient_Id "+id+": ");
						String ACType = scanner.next();
						c.setAccountType(ACType);
						session.update(c);
//						session.merge(d);
						
						System.out.println("Costomer AccountType Updated Successfully.");
						transaction.commit();
						session.close();
//						scanner.close();
					}
				}
				

				
		public static void updateBlance()
				{
					scanner = new Scanner(System.in);
					
					configuration = new Configuration();
					configuration.configure();
					
					SessionFactory sessionFactory = configuration.buildSessionFactory();
					Session session = sessionFactory.openSession();
					System.out.print("Enter  Customer ACid to Update Blance: ");
					int id = scanner.nextInt();
					scanner.nextLine();
					
					CustomerAccountDetails c = session.get(CustomerAccountDetails.class, id); //returns Doctor type of object
					if( c == null)
					{
						System.out.println("There is no Account Found for This Id.");
					}
					else
					{
						Transaction transaction = session.beginTransaction();
						
						System.out.print("Enter the blance to be updated for Patient_Id "+id+": ");
						double blns = scanner.nextDouble();
						c.setBalance(blns);
						session.update(c);
//						session.merge(d);
						
						System.out.println("Costomer Blance Updated Successfully.");
						transaction.commit();
						session.close();
//						scanner.close();
					}
				}
				

		
			public static void updateMoblie_no()
				{
					scanner = new Scanner(System.in);
					
					configuration = new Configuration();
					configuration.configure();
					
					SessionFactory sessionFactory = configuration.buildSessionFactory();
					Session session = sessionFactory.openSession();
					System.out.print("Enter  Customer ACid to Update AccountType: ");
					int id = scanner.nextInt();
					scanner.nextLine();
					
					CustomerAccountDetails c = session.get(CustomerAccountDetails.class, id); //returns Doctor type of object
					if( c == null)
					{
						System.out.println("There is no Account Found for This Id.");
					}
					else
					{
						Transaction transaction = session.beginTransaction();
						
						System.out.print("Enter the Moblie Number to be updated for Patient_Id "+id+": ");
						String mb = scanner.next();
						c.setMobile_no(mb);;
						session.update(c);
//						session.merge(d);
						
						System.out.println("Costomer Moblie Number Updated Successfully.");
						transaction.commit();
						session.close();
//						scanner.close();
					}
				}
				
			
				
			public static void updateCustomerAccountDetails()
				{
					scanner = new Scanner(System.in);
					
					//step1
					Configuration config = new Configuration();
					config.configure();
							
					//step2
					SessionFactory factory = config.buildSessionFactory();
					session = factory.openSession();

					while(true)
					{
						System.out.println("\nSelect What you Want to Update in	CustomerAccountDetails.");
						System.out.println("-----------------------------------------------------");
						System.out.println("1. CustomerFirst Name.");
						System.out.println("2. CustomerLast Name.");
						System.out.println("3. Customer Gender.");
						System.out.println("4. Customer AccountType ");
						System.out.println("5. Customer Blance.");
						System.out.println("6. Customer Mobile Number.");
						System.out.println("7. Back.");
						System.out.print("Enter your Choice: ");
						
						try
						{
							int choice = scanner.nextInt();
							
							switch(choice)
							{
							case 1:
								updateCustomerFirstName();
								System.out.println();
								break;
								
							case 2:
								updateCustomerLastName();							
								System.out.println();
								break;
								
							case 3:
								updateCustomerGender();
								System.out.println();
								break;
								
							case 4:
								updateAccountType();
								System.out.println();
								break;
								
							case 5:
								updateBlance();
								System.out.println();
								break;
								
							case 6:
								updateMoblie_no();
								System.out.println();
								break;
								

							case 7:
								return;
								
							default:
								System.out.println("Invalid Choice! ..Enter a Valid Choice.");
								System.out.println();
								break;			
							}
						}
						
						catch(InputMismatchException e)
						{
							System.out.println("Please Enter a Integer Value.");
							System.out.println();
							scanner.nextLine();
						}
					}
				}

				
			
			
			public static void deleteCustomerById()
				{
					scanner = new Scanner(System.in);

				    System.out.print("Enter Customer ACid: ");
				    int ACid = scanner.nextInt();
				    scanner.nextLine();

				    CustomerAccountDetails c = session.get(CustomerAccountDetails.class, ACid);

				    if (c == null)
				    {
				        System.out.println("Customer with ACid" + ACid + " does not exist.");
				        return;
				    }
				    else
				    {
				    	System.out.println(". . . . . . . . . . . . . . . . . . .  A  L  E  R  T  . . . . . . . . . . . . . . . . . . .");
				    	System.out.println("You Are About to Permanently Delete a Particular CustomerAccountDetails ");
						System.out.print("Do You Wish to Continue? (Yes/No) : ");
						
						String confirmation = scanner.nextLine();
						
						if(confirmation.equalsIgnoreCase("y") || confirmation.equalsIgnoreCase("yes"))
						{
							Transaction tx = session.beginTransaction();

						    // Delete associated Transaction first
						    Query deleteTransactionDetailsQuery = session.createQuery("delete from Transactions where Customer_ACid = :id");
						    deleteTransactionDetailsQuery.setParameter("ACid", ACid);
						    int deletedTransactionCount = deleteTransactionDetailsQuery.executeUpdate();
						    
						    // Now, delete the Customer
						    String name = c.getFirstName();
						    session.delete(c);
						    
						    tx.commit();
						    
						    System.out.println("CustomerAccount " + name + " and " + deletedTransactionCount + " Transaction Details Removed.");
						}
						
						else
						{
							System.out.println("Deletion Cancelled!");
						}
						
				    }
				}
				

				
			public static void deleteAllCustomer()
				{
				    scanner = new Scanner(System.in); // Create a new scanner object

				    String hqlQuery = "select count(*) from CustomerAccountDetails";
				    Long count = session.createQuery(hqlQuery, Long.class).getSingleResult();
				    
				    if(count == 0)
				    {
				    	System.out.println("There are no Customer to Delete.");
				        return;
				    }
				    
				    System.out.println(". . . . . . . . . . . . . . . .  A   L   E   R   T . . . . . . . . . . . . . . . .");
				    System.out.println("You are about to permanently delete all Customer records and associated Transaction");
					System.out.print("Do You Wish to Continue? (Yes/No) : ");

				    String confirmation = scanner.nextLine();

				    if (confirmation.equalsIgnoreCase("yes") || confirmation.equalsIgnoreCase("y"))
				    {
				        Transaction tx = session.beginTransaction();

				        // Delete all Transaction
				        Query deleteTransactionDetailsQuery = session.createQuery("delete from Transactions");
				        int deletedTransactionCount = deleteTransactionDetailsQuery.executeUpdate();

				        // Now, delete all Customer
				        Query deleteCustomerQuery = session.createQuery("delete from CustomerAccountDetails");
				        int deletedCustomerCount = deleteCustomerQuery.executeUpdate();

				        tx.commit();

				        System.out.println(deletedCustomerCount + " Customer and " + deletedTransactionCount + " associated Transaction removed.");
				    }
				    else
				    {
				        System.out.println("Deletion cancelled.");
				    }
				}
				
				
				
			public static void deleteCustomer()
				{
					while(true)
					{
						System.out.println("\nSelect How You Want to Delete CustomerAccountDetails:");
						System.out.println("----------------------------------------------");
						System.out.println("1. Delete Particular Customer.");
						System.out.println("2. Delete All Customer.");
						System.out.println("3. Back.");
						System.out.print("\nEnter Your Choice: ");
						
						try
						{
							int choice = scanner.nextInt();
							
							switch(choice)
							{
							case 1:
								deleteCustomerById();
								System.out.println();
								break;
								
							case 2:
								deleteAllCustomer();
								System.out.println();
								break;
								
							case 3:
								return;
								
							default:
								System.out.println("Please Enter a Valid Choice.");
								System.out.println();
								break;
							}
						}
						
						catch(InputMismatchException e)
						{
							System.out.println("Please Enter a Integer Value.");
							System.out.println();
							scanner.nextLine();
						}
					}
				}
				
	//===================================================================================//
				public static void createTransactions()
				{
					scanner = new Scanner(System.in);
					
					// Initializing the configuration object
				    configuration = new Configuration();
				    configuration.configure();
				    
					sessionfactory = configuration.buildSessionFactory();
					
					session = sessionfactory.openSession();
					
					Transactions t= new Transactions();

			        System.out.print("Enter transaction  id: ");
			       long id = scanner.nextLong();
			        t.setId(id);

			        System.out.print("Enter transaction type (e.g., deposit, withdrawal): ");
			        String transactionType = scanner.next();			        
			        t.setTransactionType(transactionType);;

			        System.out.print("Enter transaction amount: ");
			        double amount = scanner.nextDouble();
                     t.setAmount(amount);
			       
			        
			    	session.save(t);

			    	Transaction tx = session.beginTransaction();

			    	tx.commit();
			    	
			    	
			    	
			    	 if (t.getId() > 0)
			         {
			    	        System.out.println("Transaction created successfully:");
			    	        
			         }
			         else
			         {
			             System.out.println(" Transaction Failed !");
			         }
				}
				
				public static void viewTransactionsById()
				{
					System.out.print("\nEnter Transaction Id: ");
					int id = scanner.nextInt();

					Transactions t= session.get(Transactions.class, id);
					if(t == null)
					{
						System.out.println("There is no Transactions Found For This ID.");
					}
					else
					{
						System.out.println("\nTransactions Details:");
						System.out.println("==================");
						
						System.out.println("+-----+-----------------+-----------------+------------+");
						System.out.println("| id  |       transactionType      | amount     |");
						System.out.println("+-----+-----------------+-----------------+------------+");
				        System.out.printf("| %-3s | %-15s | %-15s | %-10s |\n",t.getTransactionType(),t.getAmount(),t.getId());
					    System.out.println("+-----+-----------------+-----------------+------------+");
					}
				}
				

				public static void viewAllTransactions()
				{
					String hqlQuery = "from Transactions";
					List<Transactions> data = session.createQuery(hqlQuery, Transactions.class).list();
					
					
					if(data.isEmpty())
					{
						System.out.println("There is no Transactions Found");
					}
					else
					{
//						System.out.println("\nTransactions Details:");
//						System.out.println("==================");
						
						System.out.println("+-----+-----------------+-----------------+------------+");
						System.out.println("| id  |       transactionType      | amount     |");
						System.out.println("+-----+-----------------+-----------------+------------+----------------+");
						
					    for (Transactions t : data)
					    {
					        System.out.printf("| %-3s | %-15s | %-15s | %-10s |\n",t.getTransactionType(),t.getAmount(),t.getId());
					    }

					    System.out.println("+-----+-----------------+-----------------+------------+-------------------+");
					}
				}
				
				public static void readTransactions()
				{
					while(true)
					{
						System.out.println("\nSelect How You Want To Read Transactions Details:");
						System.out.println("--------------------------------------------");
						System.out.println("1. View Particular Transaction.");
						System.out.println("2. View All  Transactions.");
						System.out.println("3. Back.");
						System.out.print("\nEnter your Choice: ");
						
						try
						{
							int choice = scanner.nextInt();
							
							switch(choice)
							{
							case 1:
								viewTransactionsById();
								System.out.println();
								break;
								
							case 2:
								System.out.println("\nTransactions Details:");
								System.out.println("===============");
								viewAllTransactions();
								System.out.println();
								break;
								
							case 3:
								return;
								
							default:
								System.out.println("Please Enter a Valid Choice.");
								System.out.println();
								break;
							}
						}
						
						catch(InputMismatchException e)
						{
							System.out.println("Please Enter a Integer Value.");
							System.out.println();
							scanner.nextLine();
						}
					}
				}

				
				public static void updateTransactionsType() {
				    scanner = new Scanner(System.in);

				    configuration = new Configuration();
				    configuration.configure();

				    SessionFactory sessionFactory = configuration.buildSessionFactory();
				    Session session = sessionFactory.openSession();

				    System.out.print("Enter Transaction ID to Update Type: ");
				    int id = scanner.nextInt();
				    scanner.nextLine();

				    Transactions t = session.get(Transactions.class, id); // Returns TransactionType object
				    if (t == null) {
				        System.out.println("No Record Found for Transaction Type with ID " + id);
				    } else {
				        Transaction transaction = session.beginTransaction();

				        System.out.print("Enter the New Transaction Type for Transaction ID " + id + ": ");
				        String newType = scanner.nextLine();
				        t.setTransactionType(newType);
				        session.update(t);

				        System.out.println("Transaction Type Updated Successfully.");
				        transaction.commit();
				        session.close();
				    }
				}

				public static void updateTransactionAmount() {
				    scanner = new Scanner(System.in);

				    configuration = new Configuration();
				    configuration.configure();

				    SessionFactory sessionFactory = configuration.buildSessionFactory();
				    Session session = sessionFactory.openSession();

				    System.out.print("Enter Transaction ID to Update Amount: ");
				    int id = scanner.nextInt();
				    scanner.nextLine();

				    Transactions t = session.get(Transactions.class, id); // Returns TransactionType object
				    if (t == null) {
				        System.out.println("No Record Found for Transaction with ID " + id);
				    } else {
				        Transaction transaction = session.beginTransaction();

				        System.out.print("Enter the New Amount for Transaction ID " + id + ": ");
				        double newAmount = scanner.nextDouble();
				        t.setAmount(newAmount);
				        session.update(t);

				        System.out.println("Amount Updated Successfully.");
				        transaction.commit();
				        session.close();
				    }
				}
				public static void updateTransactions() {
				    scanner = new Scanner(System.in);

				    // Step 1: Configure Hibernate
				    Configuration config = new Configuration();
				    config.configure();

				    // Step 2: Create a session factory
				    SessionFactory factory = config.buildSessionFactory();
				    factory.openSession();

				    while (true) {
				        System.out.println("\nSelect What You Want to Update in Transaction Details.");
				        System.out.println("------------------------------------------------------");
				        System.out.println("1. Transaction Type.");
				        System.out.println("2. Transaction Amount.");
				        System.out.println("3. Back.");
				        System.out.print("Enter your Choice: ");

				        try {
				            int choice = scanner.nextInt();

				            switch (choice) {
				                case 1:
				                	updateTransactionsType();
				                    System.out.println();
				                    break;

				                case 2:
				                    updateTransactionAmount();
				                    System.out.println();
				                    break;

				                case 3:
				                    return;

				                default:
				                    System.out.println("Invalid Choice! Enter a Valid Choice.");
				                    System.out.println();
				                    break;
				            }
				        } catch (InputMismatchException e) {
				            System.out.println("Please Enter an Integer Value.");
				            System.out.println();
				            scanner.nextLine();
				        }
				    }
				}

				
				public static void deleteTransactionsById() {
				    scanner = new Scanner(System.in);

				    System.out.print("Enter Transaction ID: ");
				    int id = scanner.nextInt();
				    scanner.nextLine();

				    Transactions t = session.get(Transactions.class, id);

				    if (t == null) {
				        System.out.println("Transaction with ID " + id + " does not exist.");
				        return;
				    } else {
				        System.out.println(". . . . . . . . . . . . . . . . . . .  A  L  E  R  T  . . . . . . . . . . . . . . . . . . .");
				        System.out.println("You Are About to Permanently Delete a Particular Transaction Record.");
				        System.out.print("Do You Wish to Continue? (Yes/No): ");

				        String confirmation = scanner.nextLine();

				        if (confirmation.equalsIgnoreCase("y") || confirmation.equalsIgnoreCase("yes")) {
				            Transaction tx = session.beginTransaction();

				         // Delete associated Transactions first
						    Query deleteTransactionsQuery = session.createQuery("delete from Transactions where id = :id");
						    deleteTransactionsQuery.setParameter("id", id);
						   deleteTransactionsQuery.executeUpdate();
						    
				            long id1=t.getId();
				            session.delete(t);

				            tx.commit();

				            System.out.println("Transaction with ID " + id1 + " Removed Successfully.");
				        } else {
				            System.out.println("Deletion Cancelled!");
				        }
				    }
				}

				public static void deleteAllTransaction()
				{
				    scanner = new Scanner(System.in); // Create a new scanner object

				    String hqlQuery = "select count(*) from Transactions";
				    Long count = session.createQuery(hqlQuery, Long.class).getSingleResult();

				    if (count == 0)
				    {
				        System.out.println("There are no Transactions to Delete.");
				        return;
				    }

				    System.out.println(". . . . . . . . . . . . . . . .  A   L   E   R   T . . . . . . . . . . . . . . . .");
				    System.out.println("You are about to permanently delete all Transaction records.");
				    System.out.print("Do you wish to continue? (Yes/No): ");

				    String confirmation = scanner.nextLine();

				    if (confirmation.equalsIgnoreCase("yes") || confirmation.equalsIgnoreCase("y"))
				    {
				        Transaction tx = session.beginTransaction();

				        // Delete all Transactions
				        Query deleteTransactionsQuery = session.createQuery("delete from Transactions");
				        int deletedTransactionsCount = deleteTransactionsQuery.executeUpdate();

				        tx.commit();

				        System.out.println(deletedTransactionsCount + " Transactions removed.");
				    }
				    else
				    {
				        System.out.println("Deletion cancelled.");
				    }
				}
				
				public static void deleteTransaction()
				{
				    while (true)
				    {
				        System.out.println("\nSelect How You Want to Delete Transactions:");
				        System.out.println("-------------------------------------------");
				        System.out.println("1. Delete Particular Transaction.");
				        System.out.println("2. Delete All Transactions.");
				        System.out.println("3. Back.");
				        System.out.print("\nEnter Your Choice: ");

				        try
				        {
				            int choice = scanner.nextInt();

				            switch (choice)
				            {
				                case 1:
				                    deleteTransactionsById();
				                    System.out.println();
				                    break;

				                case 2:
				                	deleteAllTransaction();
				                    System.out.println();
				                    break;

				                case 3:
				                    return;

				                default:
				                    System.out.println("Please Enter a Valid Choice.");
				                    System.out.println();
				                    break;
				            }
				        }
				        catch (InputMismatchException e)
				        {
				            System.out.println("Please Enter an Integer Value.");
				            System.out.println();
				            scanner.nextLine();
				        }
				    }
				}
//======================================================================================//
				public static void createBankBranch() {
				    scanner = new Scanner(System.in);

				    configuration = new Configuration();
				    configuration.configure();

				    sessionfactory = configuration.buildSessionFactory();
				    session = sessionfactory.openSession();

				    Bank_Branch bb = new Bank_Branch();
				    
				    System.out.print("Enter bank ID: ");
				    int bankId = scanner.nextInt();
				    bb.setBank_id(bankId);
				    
				    System.out.print("Enter branch location: ");
				    String location = scanner.next();
				    bb.setLocation(location);
				    
				    System.out.print("Enter bank name: ");
				    String bankName = scanner.next();
				    bb.setBankName(bankName);

				    session.save(bb);

				    Transaction transaction = session.beginTransaction();
				    transaction.commit();

				    if (bb.getBank_id() > 0) {
				        System.out.println("Bank Branch Added Successfully.");
				    } else {
				        System.out.println("Failed to Add Bank Branch!");
				    }
				}

	public static void viewBankBranchById() {
				    System.out.print("\nEnter Bank Branch ID: ");
				    int branchId = scanner.nextInt();

				    Bank_Branch bankBranch = session.get(Bank_Branch.class, branchId);

				    if (bankBranch == null) {
				        System.out.println("There is no Record Found For This ID.");
				    } else {
				        System.out.println("\nBank Branch Details:");
				        System.out.println("====================");

				        System.out.println("+----+------------+----------------+------------+");
				        System.out.println("| ID |  Location  |   Bank Name    |  Bank ID   |");
				        System.out.println("+----+------------+----------------+------------+");
				        System.out.printf("| %-2s | %-10s | %-14s | %-10s |\n", bankBranch.getBank_id(), bankBranch.getLocation(), bankBranch.getBankName());
				        System.out.println("+----+------------+----------------+------------+");
				    }
				}

				
				public static void viewAllBankBranch() {
				    String hqlQuery = "from BankBranch";

				    List<Bank_Branch> data = session.createQuery(hqlQuery, Bank_Branch.class).list();

				    if (data.isEmpty()) {
				        System.out.println("There are no Bank Branches Found");
				    } else {
				        System.out.println("\nBank Branch Details:");
				        System.out.println("====================");

				        System.out.println("+----+------------+----------------+------------+");
				        System.out.println("| ID |  Location  |   Bank Name    |  Bank ID   |");
				        System.out.println("+----+------------+----------------+------------+");

				        for (Bank_Branch branch : data) {
				            System.out.printf("| %-2s | %-10s | %-14s | %-10s |\n", branch.getLocation(), branch.getBankName(), branch.getBank_id());
				        }

				        System.out.println("+----+------------+----------------+------------+");
				    }
				}
				
				
				public static void readBankBranch() {
				    while (true) {
				        System.out.println("\nSelect How You Want To Read Bank Branches:");
				        System.out.println("-------------------------------------------");
				        System.out.println("1. View Particular Bank Branch.");
				        System.out.println("2. View All Bank Branches.");
				        System.out.println("3. Back.");
				        System.out.print("\nEnter your Choice: ");

				        try {
				            int choice = scanner.nextInt();

				            switch (choice) {
				                case 1:
				                    viewBankBranchById();
				                    System.out.println();
				                    break;

				                case 2:
				                    viewAllBankBranch();
				                    System.out.println();
				                    break;

				                case 3:
				                    return;

				                default:
				                    System.out.println("Please Enter a Valid Choice.");
				                    System.out.println();
				                    break;
				            }
				        } catch (InputMismatchException e) {
				            System.out.println("Please Enter an Integer Value.");
				            System.out.println();
				            scanner.nextLine();
				        }
				    }
				}

				public static void updateBank_id() {
				    scanner = new Scanner(System.in);

				    configuration = new Configuration();
				    configuration.configure();

				    SessionFactory sessionFactory = configuration.buildSessionFactory();
				    Session session = sessionFactory.openSession();
				    System.out.print("Enter Bank Branch ID to Update Bank ID: ");
				    int Id = scanner.nextInt();
				    scanner.nextLine();

				    Bank_Branch bankBranch = session.get(Bank_Branch.class,Id);

				    if (bankBranch == null) {
				        System.out.println("There is no Record Found for This Bank Branch ID.");
				    } else {
				        Transaction transaction = session.beginTransaction();

				        System.out.print("Enter the New Bank ID for Branch ID " +Id + ": ");
				        int newBankId = scanner.nextInt();
				        bankBranch.setBank_id(newBankId);
				        session.update(bankBranch);

				        System.out.println("Bank ID Updated Successfully.");
				        transaction.commit();
				        session.close();
				    }
				}
				
	
			public static void updateBankName() {
				    scanner = new Scanner(System.in);

				    configuration = new Configuration();
				    configuration.configure();

				    SessionFactory sessionFactory = configuration.buildSessionFactory();
				    Session session = sessionFactory.openSession();
				    System.out.print("Enter Bank Branch ID to Update Bank Name: ");
				    int branchId = scanner.nextInt();
				    scanner.nextLine();

				    Bank_Branch bankBranch = session.get(Bank_Branch.class, branchId);

				    if (bankBranch == null) {
				        System.out.println("There is no Record Found for This Bank Branch ID.");
				    } else {
				        Transaction transaction = session.beginTransaction();

				        System.out.print("Enter the New Bank Name for Branch ID " + branchId + ": ");
				        String newBankName = scanner.nextLine();
				        bankBranch.setBankName(newBankName);
				        session.update(bankBranch);

				        System.out.println("Bank Name Updated Successfully.");
				        transaction.commit();
				        session.close();
				    }
				}

				
		  public static void updateBankLocation() {
				    scanner = new Scanner(System.in);

				    configuration = new Configuration();
				    configuration.configure();

				    SessionFactory sessionFactory = configuration.buildSessionFactory();
				    Session session = sessionFactory.openSession();
				    System.out.print("Enter Bank Branch ID to Update Location: ");
				    int branchId = scanner.nextInt();
				    scanner.nextLine();

				    Bank_Branch bankBranch = session.get(Bank_Branch.class, branchId);

				    if (bankBranch == null) {
				        System.out.println("There is no Record Found for This Bank Branch ID.");
				    } else {
				        Transaction transaction = session.beginTransaction();

				        System.out.print("Enter the New Location for Branch ID " + branchId + ": ");
				        String newLocation = scanner.nextLine();
				        bankBranch.setLocation(newLocation);
				        session.update(bankBranch);

				        System.out.println("Bank Branch Location Updated Successfully.");
				        transaction.commit();
				        session.close();
				    }
				}
	
		public static void updateBank() {
				    scanner  = new Scanner(System.in);

				    // Step 1: Initialize bank configuration
				    Configuration bankConfig = new Configuration();
				    bankConfig.configure();

				    // Step 2: Create a bank session factory
				    SessionFactory bankFactory = bankConfig.buildSessionFactory();
				    session  = bankFactory.openSession();

				    while (true) {
				        System.out.println("\nSelect What You Want to Update in Bank Records.");
				        System.out.println("-------------------------------------------------");
				        System.out.println("1. BankLocation.");
				        System.out.println("2. Bank_id.");
				        System.out.println("3.BankName .");
				        System.out.println("4. Back.");
				        System.out.print("Enter Your Choice: ");

				        try {
				            int choice = scanner.nextInt();

				            switch (choice) {
				                case 1:
				                	updateBankLocation();
				                    System.out.println();
				                    break;

				                case 2:
				                	updateBank_id();
				                    System.out.println();
				                    break;

				                case 3:
				                	updateBankName();
				                    System.out.println();
				                    break;

				                case 4:
				                    return;

				                default:
				                    System.out.println("Invalid Choice! Enter a Valid Option.");
				                    System.out.println();
				                    break;
				            }
				        } catch (InputMismatchException e) {
				            System.out.println("Please Enter an Integer Value.");
				            System.out.println();
				            scanner.nextLine();
				        }
				    }
				}
	
		public static void deleteBank_BranchById() {
				   scanner = new Scanner(System.in);

				    System.out.print("Enter Bank Branch ID: ");
				    int Bankid= scanner.nextInt();
				    scanner.nextLine();

				    Bank_Branch bankBranch = session.get(Bank_Branch.class, Bankid);

				    if (bankBranch == null) {
				        System.out.println("Bank branch with ID " + Bankid + " does not exist.");
				        return;
				    } else {
				        System.out.println(". . . . . . . . . . . . . . . . . . .  A  L  E  R  T  . . . . . . . . . . . . . . . . . . .");
				        System.out.println("You Are About to Permanently Delete a Particular Bank Branch Record");
				        System.out.print("Do You Wish to Continue? (Yes/No): ");

				        String confirmation = scanner.nextLine();

				        if (confirmation.equalsIgnoreCase("y") || confirmation.equalsIgnoreCase("yes")) {
				            Transaction tx = session.beginTransaction();

//				             Delete associated data (if any) first
				              Query  deleteBankBranchQuery = session.createQuery("delete from Bank_Branch where Bank_id = :id");
				            deleteBankBranchQuery.setParameter("id", Bankid);
				             deleteBankBranchQuery.executeUpdate();

				            // Now, delete the Bank Branch
				            String b = bankBranch.getBankName();
				            session.delete(bankBranch);

				            tx.commit();

				            System.out.println("Bank branch " + b + " Removed.");
				        } else {
				            System.out.println("Deletion Cancelled!");
				        }
				    }
				}

		public static void deleteAllBank_Branch() {
				scanner = new Scanner(System.in);

				    String hqlQuery = "select count(*) from Bank_Branch";
				    Long count = session.createQuery(hqlQuery, Long.class).getSingleResult();

				    if (count == 0) {
				        System.out.println("There are no Bank Branches to Delete.");
				        return;
				    }

				    System.out.println(". . . . . . . . . . . . . . . .  A   L   E   R   T . . . . . . . . . . . . . . . .");
				    System.out.println("You are about to permanently delete all bank branch records.");

				    System.out.print("Do You Wish to Continue? (Yes/No): ");
				    String confirmation = scanner.nextLine();

				    if (confirmation.equalsIgnoreCase("yes") || confirmation.equalsIgnoreCase("y")) {
				        Transaction tx = session.beginTransaction();

				        
				        // Now, delete all bank branches
				        Query deleteBankBranchesQuery = session.createQuery("delete from Bank_Branch");
				        int deletedBankBranchesCount = deleteBankBranchesQuery.executeUpdate();

				        tx.commit();

				        System.out.println(deletedBankBranchesCount + " bank branches removed.");
				    } else {
				        System.out.println("Deletion cancelled.");
				    }
				}

		public static void deleteBank_Branch()
				{
					while(true)
					{
						System.out.println("\nSelect How You Want to Delete:");
						System.out.println("----------------------------------------------");
						System.out.println("1. Delete Particular Bank.");
						System.out.println("2. Delete All ");
						System.out.println("3. Back.");
						System.out.print("\nEnter Your Choice: ");
						
						try
						{
							int choice = scanner.nextInt();
							
							switch(choice)
							{
							case 1:
								deleteBank_BranchById();
								System.out.println();
								break;
								
							case 2:
								deleteAllBank_Branch();
								System.out.println();
								break;
								
							case 3:
								return;
								
							default:
								System.out.println("Please Enter a Valid Choice.");
								System.out.println();
								break;
							}
						}
						
						catch(InputMismatchException e)
						{
							System.out.println("Please Enter a Integer Value.");
							System.out.println();
							scanner.nextLine();
						}
					}
				}

				
//==========================================================================================//
		
	
	public static void read()
	{
		while(true)
		{
			System.out.println("\nSelect What You Want to Read.");
			System.out.println("-------------------------------");
			System.out.println("1. Customer Record.");
			System.out.println("2. Transactions Record.");
			System.out.println("3.  BankBrancd Record.");
			System.out.println("4. Back.");
			System.out.print("\nEnter Your Choice: ");
			
			try
			{
				int choice = scanner.nextInt();
				
				switch(choice)
				{
				case 1:
					 readCustomer();
					System.out.println();
					break;
					
				case 2:
					readTransactions();
					System.out.println();
					break;
					
				case 3:
					readBankBranch();
					System.out.println();
					break;
					
				case 4:
					return;
					
				default:
					System.out.println("Enter a Valid Choice.");
					System.out.println();
					break;
				}
			}
			
			catch(InputMismatchException e)
			{
				System.out.println("Please Enter a Integer Value.");
				System.out.println();
				scanner.nextLine();
			}
			
		}
	}
	
//==========================================================================================//
	public static void delete()
	{
		while(true)
		{
			System.out.println("\nSelect What You Want to Delete:");
			System.out.println("---------------------------------");
			System.out.println("1. Customer Record.");
			System.out.println("2. Transaction Record.");
			System.out.println("3. BankBarnch Record.");
			System.out.println("4. Back.");
			System.out.print("\nEnter Your Choice: ");
			
			int choice = scanner.nextInt();
			
			try
			{
				switch(choice)
				{
				case 1:
					deleteCustomer();
					System.out.println();
					break;
					
				case 2:
					deleteTransaction();
					System.out.println();
					break;
					
				case 3:
					deleteBank_Branch();
					System.out.println();
					break;
					
				case 4:
					return;
					
				default:
					System.out.println("Please Enter a Valid Choice.");
					System.out.println();
					break;
				}
			}
			
			catch(InputMismatchException e)
			{
				System.out.println("Please Enter a Integer Value.");
				System.out.println();
				scanner.nextLine();
			}
		}
	}

	
//==========================================================================================//

	public static void update()
	{
		while(true)
		{
			System.out.println("\nSelect What You Want to Update:");
			System.out.println("---------------------------------");
			System.out.println("1. Customer Record.");
			System.out.println("2.Transaction Record.");
			System.out.println("3.BankBranch Record.");
			System.out.println("4. Back.");
			System.out.print("\nEnter Your Choice: ");
			
			try
			{
				int choice = scanner.nextInt();
				
				switch(choice)
				{
				case 1:
					updateCustomerAccountDetails();
					System.out.println();
					break;
					
				case 2:
					updateTransactions();
					System.out.println();
					break;
					
				case 3:
					updateBank();
					System.out.println();
					break;
					
				case 4:
					return;
					
				default:
					System.out.println("Please Enter a Valid Choice.");
					System.out.println();
					break;
				}
			}
			
			catch(InputMismatchException e)
			{
				System.out.println("Please Enter a Integer Value.");
				System.out.println();
				scanner.nextLine();
			}
		}
	}
	
//==========================================================================================//

	
	public static void create()
	{
		while(true)
		{
			System.out.println("\nSelect What You Want to Create:");
			
			System.out.println("---------------------------------");
			System.out.println("1. create New CustomerAccount");
			System.out.println("2. create New Transactions");
			System.out.println("3. create New BankBranch");
			System.out.println("4. Back.");
			System.out.print("\nEnter Your Choice: ");
			
			try
			{
				int choice = scanner.nextInt();
				
				switch(choice)
				{
				case 1:
					createAccount();
					System.out.println();
					break;
					
				case 2:
					createTransactions();
					System.out.println();
					break;
					
				case 3:
					createBankBranch();
					System.out.println();
					break;
					
				case 4:
					return;
					
				default:
					System.out.println("Please Enter a Valid Choice.");
					System.out.println();
					break;
				}
			}
			
			catch(InputMismatchException e)
			{
				System.out.println("Please Enter a Integer Value.");
				System.out.println();
				scanner.nextLine();
			}
			
		
			
		
	}
}
	
   
//==========================================================================================//
		public static void main(String[] args)
		{
			scanner = new Scanner(System.in);
			
			//step1
			Configuration config = new Configuration();
			config.configure();
			
			//step2
			SessionFactory factory = config.buildSessionFactory();
			session = factory.openSession();
			
			System.out.println("===========================");
			System.out.println("BANKING SYSTEM");
			System.out.println("===========================");
			
			System.out.println("Enter Admin-Username: ");
			String userName = scanner.next();
			System.out.println("Enter Admin-Password: ");
			int password = scanner.nextInt();
			
			
			if(userName.equals(Admin.getUserName()) && password == Admin.getPassword())
			{
				while(true)
				{
					System.out.println("\n======================================");
					System.out.println("VASAVI BANK");
					System.out.println("======================================");
					System.out.println("Select What You Want TO Do");
					System.out.println("1. Create Records.");
					System.out.println("2. read Records.");
					System.out.println("3. Update Records.");
					System.out.println("4. Delete Records.");

					System.out.println("5. Exit.");
					System.out.print("\nEnter Your Choice: ");
					
					try
					{
						int choice = scanner.nextInt();
						
						switch(choice)
						{
						case 1:
							create();
							System.out.println();
							break;
							
						case 2:
							read();
							System.out.println();
							break;
							
						case 3:
							update();
							System.out.println();
							break;	
							
						case 4:
							delete();
							System.out.println();
							break;
							
						
							
						case 5:
							return;
							
						default:
							System.out.println("Invalid Choice! ..Enter a Valid Choice.");
							System.out.println();
							break;
						}
					}
					
					catch(InputMismatchException e)
					{
						System.out.println("Please Enter a Integer Value.");
						System.out.println();
						scanner.nextLine();
					}
				
				
				}
			}
			else
			{
				System.out.println("Incorrect Username or Password");
			}
			
		}
	}
