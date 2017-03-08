package com.example.xiaoyangyu.comprehensiveproject;

public class Building {
    private int id;
    private String name;
    private String introduction;
    private double longitude;
    private double latitude;
    public Building( String name,String introduction,double longtitude,double latitude)
    {
        this.latitude = latitude;
        this.longitude = longtitude;
        this.name = name;
        this.introduction = introduction;
    }
    public Building(int id, String name)
    {
        this.id=id;
        this.name=name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongtitude() {
        return longitude;
    }

    public void setLongtitude(double longitude) {
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String toString()
    {
        return name + "\n" + introduction+"\n";
    }
    public String totoString()
    {
        return id+":"+name+"\n";
    }
}

