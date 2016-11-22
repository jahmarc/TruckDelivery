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
    private String user;
    private String password;

    public DriverObject(String name, String firstname, String phone, String plate, String user, String password){
        this.name = name;
        this.firstname = firstname;
        this.phone = phone;
        this.plate = plate;
        this.user = user;
        this.password = password;
    }
    public DriverObject(){

    }

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

    public String getUser(){return user;}
    public void setUser(String user){this.user = user;}

    public String getPassword(){return password;}
    public void setPassword(String password){this.password = password;}

}