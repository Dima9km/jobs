package com.dima.jobs;

import java.util.Date;

public class Profile {

    private String firstname;
    private String patronymic;
    private String lastname;
    private String sex;
    private Date birthday;

    public Profile(String firstname, String patronymic, String lastname, String sex, Date birthday) {
        this.firstname = firstname;
        this.patronymic = patronymic;
        this.lastname = lastname;
        this.sex = sex;
        this.birthday = birthday;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getLastname() {
        return lastname;
    }

    public String getSex() {
        return sex;
    }

    public Date getBirthday() {
        return birthday;
    }
}
