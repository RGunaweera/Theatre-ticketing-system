/**
 * Task 9 - Create a new class file called Person with a constructor and the following attributes: name, surname, and email.
 * @author : Randima Gunaweera - w1956412/ 20221480
 */
public class Person
{
    //attributes
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    private String name;
    //----------------------------------
    public String getSurname()
    {
        return surname;
    }
    public void setSurname(String surname)
    {
        this.surname = surname;
    }
    private String surname;
    //------------------------------------
    public String getEmail()
    {
        return email;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
    private String email;
    //----------------------------------------
    //methods
    public Person(String name, String surname, String email)
    {
        this.name = name;
        this.surname = surname;
        this.email = email;

    }
}

