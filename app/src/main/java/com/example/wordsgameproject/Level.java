package com.example.wordsgameproject;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Level implements Parcelable {
    private int Id;
    private ArrayList<String> words;

    public Level() {
        words=new ArrayList<>();
    }

    //This method will be called on the receiving activity to collect the data.
    protected Level(Parcel in) {
        Id = in.readInt();
        words = in.createStringArrayList();
    }

    /*
    This binds everything together. Its job is simple—it generates instances of your
     Parcelable class from a Parcel using the parcel data provided.
      The creator calls the constructor you defined above, passing it a Parcel object,
       and the constructor initializes the class attributes.
     */
    public static final Creator<Level> CREATOR = new Creator<Level>() {
        @Override
        public Level createFromParcel(Parcel in) {
            return new Level(in);
        }

        @Override
        public Level[] newArray(int size) {
            return new Level[size];
        }
    };

    public ArrayList<String> getWords() {
        return words;
    }

    public void setWords(ArrayList<String> words) {
        this.words = words;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }


    //The writeToParcel method is where you add all your class data to the parcel
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(Id);
        parcel.writeStringList(words);
    }

    public int maxLength()
    {
        int max=words.get(0).length();
        for (int i = 1; i < words.size(); i++) {
            if (words.get(i).length()>max)
            {
                max=words.get(i).length();
            }
        }
        return max;
    }
}
