package com.dima.jobs.data.model;

public class UserProfile {

    private String firstName;
    private String patronymic;
    private String lastName;
    private int sex;
    private int userLocation;
    private String birthday;

    public UserProfile(String firstName, String patronymic, String lastName, int sex, int userLocation, String birthday) {
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.lastName = lastName;
        this.sex = sex;
        this.userLocation = userLocation;
        this.birthday = birthday;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getLastName() {
        return lastName;
    }

    public int getSex() {
        return sex;
    }

    public int getUserLocation() {
        return userLocation;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public void setUserLocation(int userLocation) {
        this.userLocation = userLocation;
    }
}
