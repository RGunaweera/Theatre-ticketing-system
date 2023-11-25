/**
 * Task 10 - Create a new class file called Ticket with a constructor and the following attributes: row, seat, price, and Person.
 * Person will be is an object created using the class Person.
 * @author : Randima Gunaweera
 */
public class Ticket
{
    //attributes
    public int getRow() {
        return row;
    }
    public void setRow(int row) {
        this.row = row;
    }
    private int row;
    //-------------------------------------
    public int getSeat() {
        return seat;
    }
    public void setSeat(int seat) {
        this.seat = seat;
    }
    private int seat;
    //---------------------------------------
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    private double price;
    //-------------------------------------------
    public Person getPerson() {
        return person;
    }
    public void setPerson(Person person) {
        this.person = person;
    }
    private Person person;
    //-----------------------------------------------

    //constructor
    public Ticket(int row, int seat, double price, Person person)
    {
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;
    }
    //method to print ticket information
    public void print()
    {

        String print = "Name  = "+person.getName()+" "+
                person.getSurname()+
                "\nEmail = "+person.getEmail()+"\n"+
                "Row   = "+this.row+"\n"+
                "Seat  = "+this.seat+"\n"+
                "Price = "+this.price+"\n";
        System.out.println(print);
    }
}
