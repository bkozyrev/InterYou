package com.dtd.interyou.model;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Egor on 02.06.2015.
 */
public class ItemProfile {
    public enum Type{
        CHOOSE, WRITE
    }

    private String Title;
    private Type type;
    private ArrayList<String> Options;
    private String choice;

    public ItemProfile() {
    }

    public ItemProfile(String title, Type type) {
        Title = title;
        this.type = type;
        Options = null;
    }

    public ItemProfile(String title, Type type, ArrayList<String> options) {
        Title = title;
        this.type = type;
        Options =  options;
    }

    public ItemProfile(String title, Type type, String[] options)
    {
        Title = title;
        this.type = type;
        Options = new ArrayList<>(Arrays.asList(options));
    }


    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public ArrayList<String> getOptions() {
        return Options;
    }

    public void setOptions(ArrayList<String> options) {
        Options = options;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
