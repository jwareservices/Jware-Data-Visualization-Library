/*
 * Copyright (C) 2014 J. Paul Jackson <jwareservices@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.jware.dviz.core;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author J. Paul Jackson <jwareservices@gmail.com>
 */
public class Graphics2DHandlerTest {
    
    public Graphics2DHandlerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of line method, of class Graphics2DHandler.
     */
    @org.junit.Test
    public void testLine() {
        System.out.println("line");
        float x1 = 0.0F;
        float y1 = 0.0F;
        float x2 = 0.0F;
        float y2 = 0.0F;
        Graphics2DHandler instance = new Graphics2DHandler();
        instance.line(x1, y1, x2, y2);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of circle method, of class Graphics2DHandler.
     */
    @org.junit.Test
    public void testCircle() {
        System.out.println("circle");
        int x = 0;
        int y = 0;
        int radius = 0;
        Graphics2DHandler instance = new Graphics2DHandler();
        instance.circle(x, y, radius);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fillRect method, of class Graphics2DHandler.
     */
    @org.junit.Test
    public void testFillRect_4args_1() {
        System.out.println("fillRect");
        float x1 = 0.0F;
        float y1 = 0.0F;
        float x2 = 0.0F;
        float y2 = 0.0F;
        Graphics2DHandler instance = new Graphics2DHandler();
        instance.fillRect(x1, y1, x2, y2);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fillRect method, of class Graphics2DHandler.
     */
    @org.junit.Test
    public void testFillRect_4args_2() {
        System.out.println("fillRect");
        int x1 = 0;
        int y1 = 0;
        int x2 = 0;
        int y2 = 0;
        Graphics2DHandler instance = new Graphics2DHandler();
        instance.fillRect(x1, y1, x2, y2);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of background method, of class Graphics2DHandler.
     */
    @org.junit.Test
    public void testBackground() {
        System.out.println("background");
        int rgb = 0;
        Graphics2DHandler instance = new Graphics2DHandler();
        instance.background(rgb);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
