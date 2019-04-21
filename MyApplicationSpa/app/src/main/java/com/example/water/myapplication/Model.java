package com.example.water.myapplication;

public class Model {
    public String id_application;
    public String id_cus;
    public String name_cus;
    public String lasname_cus;
    private String price_a;
    private String date_a;
    private String time_a;
    private String img_path;
    private String pay;
    private String admin_id;


    /*public Model (String id_cus,String name_cus,String lasname_cus){
        this.id_cus = id_cus;
        this.name_cus = name_cus;
        this.lasname_cus = lasname_cus;
    }*/
    public String getId_application() {
        return id_application;
    }

    public void setId_application(String id_application) {
        this.id_application = id_application;
    }

    public String getId_cus() {
        return id_cus;
    }

    public String getName_cus() {
        return name_cus;
    }

    public void setName_cus(String name_cus) {
        this.name_cus = name_cus;
    }

    public String getLasname_cus() {
        return lasname_cus;
    }

    public void setLasname_cus(String lasname_cus) {
        this.lasname_cus = lasname_cus;
    }

    public String getPrice_a() {

        return price_a;
    }

    public void setPrice_a (String price_a) {

        this.price_a = price_a;
    }

    public String getDate_a() {

        return date_a;
    }

    public void setDate_a(String date_a) {
        this.date_a = date_a;
    }

    public String getTime_a() {

        return time_a;
    }

    public void setTime_a(String time_a) {

        this.time_a = time_a;
    }

    public String getImg_path() {
        return img_path;
    }

    public void setImg_path(String img_path) {

        this.img_path = img_path;
    }
    public void setPay(String pay) {

        this.pay = pay;
    }
    public String getPay() {
        return admin_id;
    }
    public String getId_admin() {

        return admin_id;
    }

    public void setId_admin(String admin_id_a) {

        this.admin_id = admin_id;
    }
}
