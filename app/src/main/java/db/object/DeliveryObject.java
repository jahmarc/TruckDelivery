package db.object;

/**
 * Created by Marc on 15/11/2016.
 */

public class DeliveryObject {
    private int id;
    private int driverid;
    private int customerid;
    private String date;
    private int quantity;
    private String conditioning;
    private String article;

    //Empty constructor
    public DeliveryObject(){}

    //Constructor of a delivery
    public DeliveryObject(int id, int driverid, int customerid, String date, int quantity, String conditioning, String article){
        this.id = id;
        this.driverid = driverid;
        this.customerid = customerid;
        this.date = date;
        this.quantity = quantity;
        this.conditioning = conditioning;
        this.article = article;
    }

    //Constructor of a delivery without id
    public DeliveryObject(int driverid, int customerid, String date, int quantity, String conditioning, String article){
        this.driverid = driverid;
        this.customerid = customerid;
        this.date = date;
        this.quantity = quantity;
        this.conditioning = conditioning;
        this.article = article;
    }

    //getters and setters
    public int getId(){return id;}
    public void setId(int id){this.id = id;}

    public int getDriverid(){return driverid;}
    public void setDriverid(int driverid) {this.driverid = driverid;}

    public int getCustomerid(){return customerid;}
    public void setCustomerid(int customerid){this.customerid = customerid;}

    public String getDate(){return date;}
    public void setDate(String date){this.date = date;}

    public int getQuantity(){return quantity;}
    public void setQuantity(int quantity){this.quantity = quantity;}

    public String getConditioning(){return conditioning;}
    public void setConditioning(String conditioning){this.conditioning = conditioning;}

    public String getArticle(){return article;}
    public void setArticle(String article){this.article = article;}





}
