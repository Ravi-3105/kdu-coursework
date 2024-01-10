package org.example;

public class Staff extends User{
    private long staffId;
    private int yearsOfExperience;
    private String description;
    private double salary;

    public void setStaffId(long staffId) {
        this.staffId = staffId;
    }

    public long getStaffId() {
        return staffId;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }
}
