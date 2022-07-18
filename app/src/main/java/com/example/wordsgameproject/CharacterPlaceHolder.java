package com.example.wordsgameproject;

public class CharacterPlaceHolder {
    private Character character;
    private boolean Visibility;
    private boolean isNull=false;
    private String Tag="0";

    public String getTag() {
        return Tag;
    }

    public void setTag(String tag) {
        Tag = tag;
    }

    public boolean isNull() {
        return isNull;
    }

    public void setNull(boolean aNull) {
        isNull = aNull;
    }

    public CharacterPlaceHolder(Character character, boolean visibility) {
        this.character = character;
        Visibility = visibility;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public boolean isVisibility() {
        return Visibility;
    }

    public void setVisibility(boolean visibility) {
        Visibility = visibility;
    }
}
