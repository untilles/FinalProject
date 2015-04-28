package com.example.billiblahblaa.finalproject;

/**
 * Created by Billiblahblaa on 4/22/2015.
 */
public class RegCourse {

    @com.google.gson.annotations.SerializedName("id")
    private String cId;

    @com.google.gson.annotations.SerializedName("coursecode")
    private String cCode;

    @com.google.gson.annotations.SerializedName("coursename")
    private String cName;

    @com.google.gson.annotations.SerializedName("coursecredit")
    private String cCredit;

    @com.google.gson.annotations.SerializedName("corusesection")
    private String cSection;

    @com.google.gson.annotations.SerializedName("courseroom")
    private String cRoom;

    @com.google.gson.annotations.SerializedName("coursedate")
    private String cDay;

    @com.google.gson.annotations.SerializedName("coursetime")
    private String cTime;

    public RegCourse() {
        // empty
    }

    public RegCourse(String coursecode, String coursename, String coursecredit, String corusesection, String courseroom, String courseday, String coursetime) {
        this.setCourseCode(coursecode);
        this.setCourseName(coursename);
        this.setCourseCredit(coursecredit);
        this.setCourseSection(corusesection);
        this.setCourseRoom(courseroom);
        this.setCourseDay(courseday);
        this.setCourseTime(coursetime);
    }

    public String getId() {
        return cId;
    }

    public final void setCourseCode(String coursecode) {
        cCode = coursecode;
    }

    public String getCourseCode() {
        return cCode;
    }

    public final void setCourseName(String coursename) {
        cName = coursename;
    }

    public String getCourseName() {
        return cName;
    }

    public final void setCourseCredit(String coursecredit) {
        cCredit = coursecredit;
    }

    public String getCourseCredit() {
        return cCredit;
    }

    public final void setCourseSection(String coursesection) {
        cSection = coursesection;
    }

    public String getCourseSection() {
        return cSection;
    }

    public final void setCourseRoom(String courseroom) {
        cRoom = courseroom;
    }

    public String getCourseRoom() {
        return cRoom;
    }

    public final void setCourseDay(String courseDay) {
        cDay = courseDay;
    }

    public String getCourseDay() {
        return cDay;
    }

    public final void setCourseTime(String coursetime) {
        cTime = coursetime;
    }

    public String getCourseTime() {
        return cTime;
    }



}
