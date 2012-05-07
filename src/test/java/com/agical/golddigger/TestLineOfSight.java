package com.agical.golddigger;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.agical.golddigger.TestFindingGold.FieldCreatorImplementation;
import com.agical.golddigger.model.Digger;
import com.agical.golddigger.model.GoldField;
import com.agical.golddigger.model.Position;
import com.agical.golddigger.model.Square;
import com.agical.golddigger.model.fieldcreator.FieldCreator;

public class TestLineOfSight {
	private FieldCreator fieldCreator;
	private GoldField goldField;
	private Digger digger;
	
	// 5x5 tile map
	private final class FieldCreatorImplementation extends FieldCreator {
        public Square[][] createField() {
            return new Square[][]{
                                {w(),w(),w(),w(),w()},
                                {w(),e(),e(),e(),w()},
                                {w(),e(),b(),e(),w()},
                                {w(),e(),e(),e(),w()},
                                {w(),w(),w(),w(),w()}};
        }
        
    }

    @Before
    public void before() throws Exception {
        fieldCreator = new FieldCreatorImplementation();
        goldField = new GoldField(fieldCreator);
        
    }
    
    // Test sight view for the centre
    @Test
    public void centreSquareSightView() throws Exception {
    	digger = new Digger("Diggers name", "secretName");
        digger.newGame(goldField);
        
        Position startPosition = new Position(2,2);
        digger.setPosition(startPosition);
        assertEquals( "...\n.b.\n...\n", digger.getView());
        
    }
    
    // Test the sight view for the sides
    @Test
    public void sideSquareSightView() throws Exception {
    	digger = new Digger("Diggers name", "secretName");
        digger.newGame(goldField);
        
        Position startPosition = new Position(1,2);
        digger.setPosition(startPosition);
        assertEquals( "wee\nweb\nwee\n", digger.getView());
        
        startPosition = new Position(2,1);
        digger.setPosition(startPosition);
        assertEquals( "www\neee\nebe\n", digger.getView());
        
        startPosition = new Position(3,2);
        digger.setPosition(startPosition);
        assertEquals( "eew\nbew\neew\n", digger.getView());
        
        startPosition = new Position(2,3);
        digger.setPosition(startPosition);
        assertEquals( "ebe\neee\nwww\n", digger.getView());
        
    }
    
    // Test the sight view for the corners
    @Test
    public void cornerSquareSightView() throws Exception {
    	digger = new Digger("Diggers name", "secretName");
        digger.newGame(goldField);
        
        Position startPosition = new Position(1,1);
        digger.setPosition(startPosition);
        assertEquals( "www\nwee\nweb\n", digger.getView());
        
        startPosition = new Position(3,1);
        digger.setPosition(startPosition);
        assertEquals( "www\neew\nbew\n", digger.getView());
        
        startPosition = new Position(3,3);
        digger.setPosition(startPosition);
        assertEquals( "bew\neew\nwww\n", digger.getView());
        
        startPosition = new Position(1,3);
        digger.setPosition(startPosition);
        assertEquals( "web\nwee\nwww\n", digger.getView());
        
    }
    
    
    
}
