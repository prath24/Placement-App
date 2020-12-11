package com.example.placement2;

public class CompanyData
{
    private String job,name,placementid,imageurl;

    public CompanyData() {
    }

    public CompanyData(String job, String name, String placementid) {
        this.job = job;
        this.name = name;
        this.placementid = placementid;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlacementid() {
        return placementid;
    }

    public void setPlacementid(String placementid) {
        this.placementid = placementid;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
