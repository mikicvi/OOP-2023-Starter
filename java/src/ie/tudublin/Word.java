package ie.tudublin;

import java.util.ArrayList;

public class Word {

    private String word;

    private ArrayList<Follow> follow = new ArrayList<Follow>();

    
    
    
    public Word(String word, ArrayList<Follow> follow) {
        this.word = word;
        this.follow = follow;
    }

    // toString Method that will iterate through the follows ArrayList
    @Override
    public String toString() {
        String s = word + ": ";
        for (Follow f : follow) {
            s += f.toString() + ", ";
        }
        return s;
    }


    // getters and setters below
    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public ArrayList<Follow> getFollow() {
        return follow;
    }

    public void setFollow(ArrayList<Follow> follow) {
        this.follow = follow;
    }

    

    
}
