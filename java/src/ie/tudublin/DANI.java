package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;

public class DANI extends PApplet 
{
	
	ArrayList<Word> model = new ArrayList<Word>();
	String sonet[];

	float x = width / 2;
	float y = height / 2;
	
	
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

		sonet = writeSonnet();
	}
	
    public String[] writeSonnet()
    {
		String[] sonnet = new String[14];
		for (int i = 0; i < sonnet.length; i++) 
		{
			String sLine = "";
			Word currentWord = model.get((int) random(model.size()));
			for (int j = 0; j < 7; j++) 
			{
				ArrayList<Follow> follows = currentWord.getFollow();
				if (follows.size() == 0) 
				{
					break;
				}
				Follow follow = follows.get((int) random(follows.size()));	
				sLine += " " + follow.getWord();
				currentWord = findWord(follow.getWord());
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
		for (Word w : model) 
		{
			if (w.getWord().equals(word)) 
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



	public void keyPressed() 
	{

	}

	float off = 0;

	public void draw() 
    {
		background(0);
		fill(255);
		noStroke();
		textSize(20);
        textAlign(CENTER, CENTER);

		float gap = height  / 20;

		for (int i = 0; i < sonet.length; i++)
		{
			text(sonet[i], x,y);
			y += gap;
		}


		
	}
}
