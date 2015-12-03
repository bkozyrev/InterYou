package com.dtd.interyou.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by 123 on 02.05.2015.
 */
public class ItemPoll implements Parcelable{
    private ArrayList<String> Questions;
    private ArrayList<String> Answers;
    private String Type;
    private String Title;
    private Boolean Others;
    private int Id;

    public ItemPoll(ArrayList<String> questions, ArrayList<String> answers, String type, String title, Boolean others, int id) {
        Questions = questions;
        Answers = answers;
        Type = type;
        Title = title;
        Others = others;
        Id = id;
    }

    public ArrayList<String> getQuestion() {
        return Questions;
    }

    public String getType(){
        return Type;
    }

    public String getTitle(){
        return Title;
    }

    public ArrayList<String> getAnswers() {
        return Answers;
    }

    public void setAnswers(ArrayList<String> answers) {
        Answers = answers;
    }

    public int getAnswersCount(){
        return Answers.size();
    }

    public int getQuestionsCount(){
        return Questions.size();
    }

    public boolean getOthers(){
        return Others;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public ItemPoll(Parcel in) {
        Questions = in.readArrayList(null);
        Answers = in.readArrayList(null);
        Type = in.readString();
        Title = in.readString();
        Others  = (in.readInt() == 0) ? false : true;
        Id = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeList(Questions);
        parcel.writeList(Answers);
        parcel.writeString(Type);
        parcel.writeString(Title);
        parcel.writeInt(Others ? 1 : 0);
        parcel.writeInt(Id);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        @Override
        public ItemPoll createFromParcel(Parcel in) {
            return new ItemPoll(in);
        }

        @Override
        public ItemPoll[] newArray(int size) {
            return new ItemPoll[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }
}
