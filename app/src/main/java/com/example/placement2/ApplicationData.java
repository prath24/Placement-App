package com.example.placement2;

public class ApplicationData {
    String companyname,username,job,placementid,response;

    public ApplicationData() {
    }

    public ApplicationData(String companyname, String username, String job, String placementid,String response) {
        this.companyname = companyname;
        this.username = username;
        this.job = job;
        this.placementid = placementid;
        this.response = response;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
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
