package com.agical.golddigger.model.fieldcreator;

import java.util.Enumeration;
import java.util.Hashtable;

import com.agical.golddigger.model.Square;

public class StringFieldCreator extends FieldCreator {
    
    private static final int WALLS = 2;
    private final String result;
    private Square[][] squares;

    public StringFieldCreator(String result) {
        this.result = result;
    }

    public Square[][] createField() {
        return getSquares();
    }

    private Square[][] getSquares() {
        if(squares!=null) return squares;
        String[] rows = result.split("\n");
        squares = new Square[rows.length][];
        for (int rowCount = 0; rowCount<rows.length; rowCount++) {
            String charRow = rows[rowCount];
            Square[] squareRow = new Square[charRow.length()];
            squares[rowCount] = squareRow;
            for (int i = 0; i < charRow.length(); i++) {
                char squareChar = charRow.charAt(i);
                squareRow[i] = Square.createFromChar(squareChar); 
            }
        }
        return squares;
    }
    
    private String getTileShape() {
    	String tileShape = null;
    	String[] shapeSplit = null;
    	Hashtable<Integer, String> shape = new Hashtable<Integer, String>();
    	
    	shape.put(3, "triangle");
    	shape.put(4, "square");
    	shape.put(6, "hexagon");
    	
    	String[] rows = result.split("\n");
    	for (int rowCount = 0; rowCount < rows.length; rowCount++){
    		shapeSplit = rows.toString().split("shape = ");
    	}
    	
    	if (shapeSplit.length > 0){
    		for (int i = 0; i < shapeSplit.length; i++){
    			if (shape.containsKey(Integer.parseInt(shapeSplit[i]))){
    				tileShape = shape.get(Integer.parseInt(shapeSplit[i]));
    			}
    		}
    	} 
    	else {
    		tileShape = "square";
    	}
    	
    	return tileShape;
    }
    
    private int getLineOfSight(){
    	int lineOfSight = 0;
    	String[] sightSplit = null;
    	
    	String[] rows = result.split("\n");
    	for (int rowCount = 0; rowCount < rows.length; rowCount++){
    		sightSplit = rows.toString().split("line of sight = ");
    	}
    	
    	if (sightSplit.length > 0){
    		for (int i = 0; i < sightSplit.length; i++){
    			lineOfSight = Integer.parseInt(sightSplit[i]);
    		}
    	}
    	else {
    		lineOfSight = 1;
    	}
    	
		return lineOfSight;
    }

    
    public int getMaxLatitude() {
        return getSquares().length-WALLS;
    }
    
    public int getMaxLongitude() {
        return getSquares()[0].length-WALLS;
    }
    
}
