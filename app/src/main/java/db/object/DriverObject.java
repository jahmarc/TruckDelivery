package db.object;

/**
 * Created by Marc on 15/11/2016.
 */

public class DriverObject {
    private int id;
    private String name;
    private String firstname;
    private String phone;
    private String plate;
    private String numTruck;
    private String password;

    //Empty constructor
    public DriverObject(){}

    //Constructor of a driver withtout id
    public DriverObject(String name, String firstname, String phone, String plate, String numTruck, String password){
        this.name = name;
        this.firstname = firstname;
        this.phone = phone;
        this.plate = plate;
        this.numTruck = numTruck;
        this.password = password;
    }

    //Constructor of a driver
    public DriverObject(int id, String name, String firstname, String phone, String plate, String numTruck, String password){
        this.id = id;
        this.name = name;
        this.firstname = firstname;
        this.phone = phone;
        this.plate = plate;
        this.numTruck = numTruck;
        this.password = password;
    }


    //getters and setters
    public int getId(){return id;}
    public void setId(int id){this.id = id;}

    public String getName(){return name;}
    public void setName(String name){this.name = name;}

    public String getFirstname(){return firstname;}
    public void setFirstname(String firstname){this.firstname = firstname;}

    public String getPhone(){return phone;}
    public void setPhone(String phone){this.phone = phone;}

    public String getPlate(){return plate;}
    public void setPlate(String plate){this.plate = plate;}

    public String getNumTruck() {return numTruck;}
    public void setNumTruck(String numTruck) {this.numTruck = numTruck;}

    public String getPassword(){return password;}
    public void setPassword(String password){this.password = password;}

}
