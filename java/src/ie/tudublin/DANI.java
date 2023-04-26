package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;

public class DANI extends PApplet {

	

	public void settings() {
		size(1000, 1000);
		//fullScreen(SPAN);
	}

    String[] sonnet;
	String[] words;

	ArrayList<Word> model = new ArrayList<Word>();



    public String[] writeSonnet()
    {
        return null;
    }

	// the model is an ArrayList of Word objects
	// each Word object has a word and an ArrayList of Follow objects
	// each Follow object has a word and a count
	// the model is built by iterating through the words in the input file
	// for each word, find the word that follows it
	// if the word is already in the model, increment the count of the Follow object
	// if the word is not in the model, add it to the model
	
	public void loadFile()
	{
		sonnet = loadStrings("small.txt");
		words = split(sonnet[0], ' ');
		for (int i = 0; i < sonnet.length; i++)
		{
			words[i] = words[i].replaceAll("[^\\w\\s]", "");
			words[i] = words[i].toLowerCase();
		}
	}

	public String findWord(String word)
	{
		for (int i = 0; i < words.length; i++)
		{
			if (words[i].equals(word))
			{
				if (i < words.length - 1)
				{
					String nextWord = words[i + 1];
					return nextWord;
				}
			}
		}
		return null;
	}

	public void printModel()
	{
		for (int i = 0; i < words.length; i++)
		{
			String word = words[i];
			String nextWord = findWord(word);
			if (nextWord != null)
			{
				System.out.println(word + ": " + nextWord);
			}
		}

	}


	public void setup() {
		colorMode(HSB);
		

       
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
		loadFile();

		printModel();
		
        
	}
}
