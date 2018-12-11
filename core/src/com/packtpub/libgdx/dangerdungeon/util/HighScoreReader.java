package com.packtpub.libgdx.dangerdungeon.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Useless class, but might have use later on
 * @author Tyler Forrester
 *
 */
public class HighScoreReader {
	private Scanner x;
	
	public void openFile()
	{
		try
		{
			x = new Scanner(new File("Z:\\tf0199\\GitRepositiories\\Dangerdungeon\\HighScore.txt"));
		}
		catch(Exception e)
		{
			System.out.println("could not find file");
		}
	}
		
	public void readFile()
	{
		while(x.hasNext())
		{
			String a = x.next();
			String b = x.next();
			
			System.out.println(a + b);
		}
	}
	
	public void closeFile()
	{
		x.close();
	}

}
