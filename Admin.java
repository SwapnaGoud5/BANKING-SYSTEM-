package bankingSystem;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Admin
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private static String 
    userName = "sap";
    private static int password = 1412;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public static String getUserName()
    {
        return userName;
    }

    public static int getPassword()
    {
        return password;
    }

}
