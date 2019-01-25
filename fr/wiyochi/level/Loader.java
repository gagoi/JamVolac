package fr.wiyochi.level;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class Loader {

	FileInputStream fis = null;
	private int x;
	private int y;
	private char[][] charMap;
	private HashMap<String, Integer[]> pickups = new HashMap<String, Integer[]>();

	public void loadLevel(String filename) {
		Properties properties = new Properties();
		FileInputStream input = null;

		try {
			input = new FileInputStream(filename);
			properties.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		x = Integer.parseInt(properties.getProperty("x"));
		y = Integer.parseInt(properties.getProperty("y"));
	    
	    charMap = new char[x][y];
	    String mapStr[] = new String[x*y];
	    mapStr = properties.getProperty("map").split("");
	    
	    for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				charMap[i][j] = mapStr[i*x + j].charAt(0);
			}
		}
	    
	    String[] namePickups = properties.getProperty("pickups").split(",");
	    String[] strPosPickUp;
	    Integer[] posPickups = new Integer[2];
	    for (int i = 0; i < namePickups.length; i++) {
	    	strPosPickUp = properties.getProperty(namePickups[i]).split(",");
	    	posPickups[0] = Integer.parseInt(strPosPickUp[0]);
	    	posPickups[1] = Integer.parseInt(strPosPickUp[1]);
	    	
	    	System.out.println("Name: " + namePickups[i]);
	    	System.out.println("Pos: " + posPickups[0] + "," + posPickups[1]);
	    	
	    	pickups.put(namePickups[i], posPickups);
		}
	}

}




















