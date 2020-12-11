package com.example.placement2;

public class StoreApplicationData {

    String companyname,job,username,placementid,response;

    public StoreApplicationData() {
    }

    public StoreApplicationData(String companyname, String job, String username, String placementid,String response) {
        this.companyname = companyname;
        this.job = job;
        this.username = username;
        this.placementid = placementid;
        this.response = response;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPlacementid() {
        return placementid;
    }

    public void setPlacementid(String placementid) {
        this.placementid = placementid;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
