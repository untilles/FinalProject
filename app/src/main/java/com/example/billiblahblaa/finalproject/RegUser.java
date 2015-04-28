package com.example.billiblahblaa.finalproject;

import com.microsoft.windowsazure.mobileservices.table.TableOperationCallback;

/**
 * Created by Billiblahblaa on 4/21/2015.
 */
public class RegUser {

    @com.google.gson.annotations.SerializedName("id")
    private String uId;

    @com.google.gson.annotations.SerializedName("name")
    private String uName;

    @com.google.gson.annotations.SerializedName("username")
    private String uUsername;

    @com.google.gson.annotations.SerializedName("passwd")
    private String uPasswd;

    @com.google.gson.annotations.SerializedName("curriculum")
    private String uCurriculum;

    @com.google.gson.annotations.SerializedName("academicyear")
    private String uAcademicYear;

    @com.google.gson.annotations.SerializedName("course1")
    private String uCourse1;

    @com.google.gson.annotations.SerializedName("course2")
    private String uCourse2;

    @com.google.gson.annotations.SerializedName("course3")
    private String uCourse3;

    @com.google.gson.annotations.SerializedName("course4")
    private String uCourse4;

    @com.google.gson.annotations.SerializedName("course5")
    private String uCourse5;

    @com.google.gson.annotations.SerializedName("course6")
    private String uCourse6;

    @com.google.gson.annotations.SerializedName("course7")
    private String uCourse7;

    @com.google.gson.annotations.SerializedName("course8")
    private String uCourse8;

    @com.google.gson.annotations.SerializedName("course9")
    private String uCourse9;

    @com.google.gson.annotations.SerializedName("course10")
    private String uCourse10;

    public RegUser() {
        // empty
    }

    public RegUser(String name, String username, String passwd, String curriculum, String academicyear,
                   String course1, String course2, String course3, String course4, String course5,
                   String course6, String course7, String course8, String course9, String course10) {

        this.setName(name);
        this.setUsername(username);
        this.setPasswd(passwd);
        this.setCurriculum(curriculum);
        this.setAcademicYear(academicyear);
    }

    public String getId() {
        return uId;
    }

    public final void setName(String name) {
        uName = name;
    }

    public String getName() {
        return uName;
    }

    public final void setUsername(String username) {
        uUsername = username;
    }

    public String getUsername() {
        return uUsername;
    }

    public final void setPasswd(String passwd) {
        uPasswd = passwd;
    }

    public String getPasswd() {
        return uPasswd;
    }

    public final void setCurriculum(String curriculum) {
        uCurriculum = curriculum;
    }

    public String getCurriculum() {
        return uCurriculum;
    }

    public final void setAcademicYear(String academicYear) {
        uAcademicYear = academicYear;
    }

    public String getAcademicYear() {
        return uAcademicYear;
    }

    public final void setCourse1(String course1) {
        uCourse1 = course1;
    }

    public final void setCourse2(String course2) {
        uCourse2 = course2;
    }

    public final void setCourse3(String course3) {
        uCourse3 = course3;
    }

    public final void setCourse4(String course4) {
        uCourse4 = course4;
    }

    public final void setCourse5(String course5) {
        uCourse5 = course5;
    }

    public final void setCourse6(String course6) {
        uCourse6 = course6;
    }

    public final void setCourse7(String course7) {
        uCourse7 = course7;
    }

    public final void setCourse8(String course8) {
        uCourse8 = course8;
    }

    public final void setCourse9(String course9) {
        uCourse9 = course9;
    }

    public final void setCourse10(String course10) {
        uCourse10 = course10;
    }

    public String getCourse1() {
        return uCourse1;
    }

    public String getCourse2() {
        return uCourse2;
    }

    public String getCourse3() {
        return uCourse3;
    }

    public String getCourse4() {
        return uCourse4;
    }

    public String getCourse5() {
        return uCourse5;
    }

    public String getCourse6() {
        return uCourse6;
    }

    public String getCourse7() {
        return uCourse7;
    }

    public String getCourse8() {
        return uCourse8;
    }

    public String getCourse9() {
        return uCourse9;
    }

    public String getCourse10() {
        return uCourse10;
    }



}
