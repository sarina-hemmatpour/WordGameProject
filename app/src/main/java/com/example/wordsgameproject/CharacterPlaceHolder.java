package com.example.wordsgameproject;

public class CharacterPlaceHolder {
    private Character character;
    private boolean Visibility;

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
