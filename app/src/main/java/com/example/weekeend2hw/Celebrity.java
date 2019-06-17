package com.example.weekeend2hw;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "celebrity_table")
public class Celebrity {


    @PrimaryKey
    @NonNull
    private String celebrityName;
    private String profession;
    private int fameLevel;



    public Celebrity(@NonNull String celebrityName, String profession, int fameLevel){
        this.celebrityName = celebrityName;
        this.profession =  profession;
        this.fameLevel = fameLevel;
    }

    public String getCelebrityName() {
        return celebrityName;
    }

    public void setCelebrityName(String celebrityName) {
        this.celebrityName = celebrityName;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public int getFameLevel() {
        return fameLevel;
    }

    public void setFameLevel(int fameLevel) {
        this.fameLevel = fameLevel;
    }
}
