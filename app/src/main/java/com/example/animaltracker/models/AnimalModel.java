package com.example.animaltracker.models;

public class AnimalModel {
    private String HendangeredLevel;
    private String Hname;
    private String Htype;
    private String endangeredLevel;
    private Integer image;
    //private String imageUrl;
    private String name;
    private String type;
    private  int id;

    // Empty constructor required for Firebase
    public AnimalModel() {
    }

    public AnimalModel(int id, String name, String Hname, String type, String Htype,
                       String endangeredLevel, String HendangeredLevel, Integer image/*String imageUrl*/) {
        this.id = id;
        this.name = name;
        this.Hname = Hname;
        this.type = type;
        this.Htype = Htype;
        this.endangeredLevel = endangeredLevel;
        this.HendangeredLevel = HendangeredLevel;
        this.image = image;
        //this.imageUrl = imageUrl;
    }

    // Getters and Setters
    public String getHendangeredLevel() {
        return HendangeredLevel;
    }

    public void setHendangeredLevel(String hendangeredLevel) {
        HendangeredLevel = hendangeredLevel;
    }

    public String getHname() {
        return Hname;
    }

    public void setHname(String hname) {
        Hname = hname;
    }

    public String getHtype() {
        return Htype;
    }

    public void setHtype(String htype) {
        Htype = htype;
    }

    public String getEndangeredLevel() {
        return endangeredLevel;
    }

    public void setEndangeredLevel(String endangeredLevel) {
        this.endangeredLevel = endangeredLevel;
    }

    /*
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image){
        this.image=image;
    }
}
