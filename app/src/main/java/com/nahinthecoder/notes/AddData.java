package com.nahinthecoder.notes;

public class AddData {

  /**  private String Full_Name;
    private String Fathers_Name;
    private String Mothers_Name;
    private String NID_Number;
    private String Address_location;
**/
    private String Topic;
    private String Note;
    private String Date;
    private String Time;

    public AddData(String topic, String note, String date, String time) {
        Topic = topic;
        Note = note;
        Date = date;
        Time = time;
    }
    public AddData()
    {
        //empty constructor
    }

    public String getTopic() {
        return Topic;
    }

    public void setTopic(String topic) {
        Topic = topic;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }


}

  /**  public AddData(String full_Name, String fathers_Name, String mothers_Name, String NID_Number, String address_location) {
        Full_Name = full_Name;
        Fathers_Name = fathers_Name;

        // Fathers_Name = firstLine;
        Mothers_Name = mothers_Name;
        this.NID_Number = NID_Number;
        Address_location = address_location;
    }

    /**
    public String getFull_Name() {
        return Full_Name;
    }

    public void setFull_Name(String full_Name) {
        Full_Name = full_Name;
    }

    public String getFathers_Name() {
        return Fathers_Name;
    }

    public void setFathers_Name(String fathers_Name) {
        Fathers_Name = fathers_Name;
    }

    public String getMothers_Name() {
        return Mothers_Name;
    }

    public void setMothers_Name(String mothers_Name) {
        Mothers_Name = mothers_Name;
    }

    public String getNID_Number() {
        return NID_Number;
    }

    public void setNID_Number(String NID_Number) {
        this.NID_Number = NID_Number;
    }

    public String getAddress_location() {
        return Address_location;
    }

    public void setAddress_location(String address_location) {
        Address_location = address_location;
    }
}
**/