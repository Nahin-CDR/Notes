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


    @Override
    public String toString(){
        return  "\n"
                +Date+"     "+Time+"\n"
                +Topic+"\n"+
                "\n\n"+Note+"\n";
        //   Animation animation = AnimationUtils.loadAnimation(Context,R.anim.chatting);
    }


}
