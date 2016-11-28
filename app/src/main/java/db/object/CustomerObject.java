package db.object;

/**
 * Created by Marc on 15/11/2016.
 */

public class CustomerObject {
    private int id;
    private String society;
    private String name;
    private String firstname;
    private String phone;
    private String adress;
    private int postcode;
    private String locality;

    //Empty constructor
    public CustomerObject(){}

    //Constructor of a Customer
    public CustomerObject(int id, String society, String name, String firstname, String phone, String adress, int postcode, String locality){
        this.id = id;
        this.society = society;
        this.name = name;
        this.firstname = firstname;
        this.phone = phone;
        this.adress = adress;
        this.postcode = postcode;
        this.locality = locality;
    }

    //Constructor of a Customer without id
    public CustomerObject(String society, String name, String firstname, String phone, String adress, int postcode, String locality){
        this.society = society;
        this.name = name;
        this.firstname = firstname;
        this.phone = phone;
        this.adress = adress;
        this.postcode = postcode;
        this.locality = locality;
    }

    //getters and setters
    public int getId(){return id;}
    public void setId(int id){this.id = id;}

    public String getSociety(){return society;}
    public void setSociety(String society){this.society = society;}

    public String getName(){return name;}
    public void setName(String name){this.name = name;}

    public String getFirstname(){return firstname;}
    public void setFirstname(String firstname){this.firstname = firstname;}

    public String getPhone(){return phone;}
    public void setPhone(String phone){this.phone = phone;}

    public String getAdress(){return adress;}
    public void setAdress(String adress){this.adress = adress;}

    public int getPostcode(){return postcode;}
    public void setPostcode(int postcode){this.postcode = postcode;}

    public String getLocality(){return locality;}
    public void setLocality(String locality){this.locality = locality;}
}
