package db.object;

/**
 * Created by Marc on 15/11/2016.
 */

public class Customer {
    private int id;
    private String society;
    private String name;
    private String firstname;
    private String phone;
    private String adress;
    private int postcode;
    private String locality;
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
