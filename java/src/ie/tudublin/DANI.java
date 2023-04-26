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

    public String[] writeSonnet()
    {
        return null;
    }

	public void loadFile()
	{
		sonnet = loadStrings("sonnet.txt");
		words = split(sonnet[0], ' ');
		for (int i = 0; i < words.length; i++)
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
        
	}
}
