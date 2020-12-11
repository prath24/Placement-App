package com.example.placementstaff;

public class CompanyDetailsAdderClass {
    String placementid,name,job,address,requirements,salary,imageurl,branch,mincgpa,ktlimit,email,contact,site;

    public CompanyDetailsAdderClass() {
    }

    public CompanyDetailsAdderClass(String placementid, String name, String job, String address, String requirements, String salary, String imageurl,String branch,String mincgpa ,String ktlimit,String email,String contact,String site) {
        this.placementid = placementid;
        this.name = name;
        this.job = job;
        this.address = address;
        this.requirements = requirements;
        this.salary = salary;
        this.imageurl = imageurl;
        this.branch = branch;
        this.mincgpa = mincgpa;
        this.ktlimit = ktlimit;
        this.email = email;
        this.contact = contact;
        this.site = site;

    }

    public String getPlacementid() {
        return placementid;
    }

    public void setPlacementid(String placementid) {
        this.placementid = placementid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }



    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getMincgpa() {
        return mincgpa;
    }

    public void setMincgpa(String mincgpa) {
        this.mincgpa = mincgpa;
    }

    public String getKtlimit() {
        return ktlimit;
    }

    public void setKtlimit(String ktlimit) {
        this.ktlimit = ktlimit;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
}
