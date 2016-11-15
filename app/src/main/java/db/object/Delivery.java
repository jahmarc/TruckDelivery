package db.object;

/**
 * Created by Marc on 15/11/2016.
 */

public class Delivery {
    private int id;
    private int driverid;
    private int customerid;
    private String date;
    private int quantity;
    private String conditioning;
    private String article;

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
