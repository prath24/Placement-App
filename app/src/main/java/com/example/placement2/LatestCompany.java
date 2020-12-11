package com.example.placement2;

public class LatestCompany {

    private String placementid;
    private String imageurl;
    private String job;
    private String expdate;
    private String name;

    public LatestCompany() {
    }

    public LatestCompany(String imageurl, String job, String expdate, String name,String placementid) {
        this.imageurl = imageurl;
        this.job = job;
        this.expdate = expdate;
        this.name = name;
        this.placementid = placementid;
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

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getExpdate() {
        return expdate;
    }

    public void setExpdate(String expdate) {
        this.expdate = expdate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
