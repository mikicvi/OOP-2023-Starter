package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;

public class DANI extends PApplet {
	
	ArrayList<Word> model = new ArrayList<Word>();
	String sonet[];
	
	
	public void settings() {
		size(1000, 1000);
	}
	public void setup() {
		colorMode(HSB);

		loadFile("java/data/small.txt");
		//loadFile("java/data/shakespere.txt");
		printModel();
	}
	
    public String[] writeSonnet()
    {
        return null;
    }

	public void loadFile(String fname)
	{
		String[] lines = loadStrings(fname);
		for (String line : lines) 
		{
			String[] words = split(line, ' ');
			for (int i = 0; i < words.length; i++) 
			{
				String w = words[i].replaceAll("[^\\w\\s]", "").toLowerCase();
				if (w.length() == 0) 
				{
					continue;
				}
				Word word = findWord(w);
				if (word == null) 
				{
					word = new Word(w, null);
					model.add(word);
				}
				if (i < words.length - 1) 
				{
					String nextW = words[i + 1].replaceAll("[^\\w\\s]", "").toLowerCase();
					if (nextW.length() == 0) 
					{
						continue;
					}
					word.findFollow(nextW);
				}
			}
		}
	}

	public Word findWord(String word)
	{
		for (Word w : model) {
			if (w.getWord().equals(word)) {
				return w;
			}
		}
		return null;
	}

	public void printModel() {
		for (Word word : model) {
			System.out.print(word.getWord() + ": ");
			for (Follow follow : word.getFollow()) {
				System.out.print(follow.getWord() + "(" + follow.getCount() + ") ");
			}
			System.out.println();
		}
	}



	public void keyPressed() {

	}

	float off = 0;

	public void draw() 
    {
		background(0);
		fill(255);
		noStroke();
		textSize(20);
        textAlign(CENTER, CENTER);
		
	}
}
