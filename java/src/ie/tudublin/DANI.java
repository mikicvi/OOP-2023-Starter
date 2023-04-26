package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;

public class DANI extends PApplet 
{
	
	ArrayList<Word> model = new ArrayList<Word>();
	String sonnet[];


	
	
	public void settings() 
	{
		size(1000, 1000);
	}
	public void setup() 
	{
		colorMode(HSB);

		//loadFile("java/data/small.txt");
		loadFile("java/data/shakespere.txt");
		printModel();

		sonnet = writeSonnet();
	}
	
    public String[] writeSonnet()
    {
		String[] sonnet = new String[14]; // 14 lines in a sonnet
		for (int i = 0; i < sonnet.length; i++) 
		{
			String sLine = "";
			Word currentWord = model.get((int) random(model.size()));
			for (int j = 0; j < 8; j++)  // 8 words in a line if possible
			{
				ArrayList<Follow> follows = currentWord.getFollow(); // get the follows, if there are none, break
				if (follows.size() == 0) 
				{
					break;
				}

				Follow follow = follows.get((int) random(follows.size()));	// get a random follow
				sLine += " " + follow.getWord();
				currentWord = findWord(follow.getWord()); // find the next word
			}
			sonnet[i] = sLine;
		}
		return sonnet;
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
				if (w.length() == 0)  // skip empty words
				{
					continue;
				}
				Word word = findWord(w);
				if (word == null)  // word not found
				{
					word = new Word(w, null);
					model.add(word);
				}
				if (i < words.length - 1)  // not the last word
				{
					String nextW = words[i + 1].replaceAll("[^\\w\\s]", "").toLowerCase();
					if (nextW.length() == 0) 
					{
						continue;
					}
					word.findFollow(nextW); // find the follow
				}
			}
		}
	}

	public Word findWord(String word)
	{
		for (Word w : model) 
		{
			if (w.getWord().equals(word)) // if the word is found
			{
				return w;
			}
		}
		return null;
	}

	public void printModel()  
	{
		for (Word word : model)  
		{
			System.out.print(word.getWord() + ": ");
			for (Follow follow : word.getFollow()) 
			{
				System.out.print(follow.getWord() + "(" + follow.getCount() + ") ");
			}
			System.out.println();
		}
	}



	public void keyPressed() {

		if (key == ' ') {

			sonnet = writeSonnet();

			// for (int i = 0; i < sonnet.length; i++) { // for testing only
			// 	System.out.println(sonnet[i]);
			// }
			redraw();
		}
	}
	float off = 0;

	public void draw() 
    {
		background(0);
		fill(255);
		noStroke();
		textSize(20);
        textAlign(CENTER, CENTER);

		float x = width / 2;
		float y = (height / 2) - 200; // offset the initial y position so the sonnet is centered
		float gap = height  / 40;

		for (int i = 0; i < sonnet.length; i++)
		{
			text(sonnet[i], x,y);
			y += gap;
		}


		
	}
}
