package ie.tudublin;

import java.util.ArrayList;

public class Word {

    private String word;

    private ArrayList<Follow> follow;

    
    public Word(String word, ArrayList<Follow> follow) {
        this.word = word;
        this.follow = new ArrayList<Follow>();
    }

    public void findFollow(String nextWord2)
    {
        for (int i = 0; i < follow.size(); i++) 
        {
            if(follow.get(i).getWord().equals(nextWord2))
            {
                follow.get(i).setCount(follow.get(i).getCount() + 1);
                return;
            }
        }
    }


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
