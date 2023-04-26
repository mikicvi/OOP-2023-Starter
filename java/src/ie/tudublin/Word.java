package ie.tudublin;

import java.util.ArrayList;

public class Word {

    private String word;

    private ArrayList<Follow> follow = new ArrayList<Follow>();

    
    public Word(String word, ArrayList<Follow> follow) {
        this.word = word;
        this.follow = follow;
    }

    public String findFollow(String[] words)
    {
        for (int i = 0; i < words.length; i++)
        {
            if (words[i].equals(word))
            {
                if (i < words.length - 1)
                {
                    String nextWord = words[i + 1];
                    boolean found = false;
                    for (Follow f : follow)
                    {
                        if (f.getWord().equals(nextWord))
                        {
                            f.setCount(f.getCount() + 1);
                            found = true;
                        }
                    }
                    if (!found)
                    {
                        follow.add(new Follow(nextWord, 1));
                    }
                }
            }
        }
        return null;
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
