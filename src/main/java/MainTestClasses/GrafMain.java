package MainTestClasses;

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

import java.awt.BasicStroke;
import java.awt.Color;
import org.jware.dviz.core.DrawSomeCircles;
import org.jware.dviz.core.Drawable;
import org.jware.dviz.core.DrawSomeRectangles;
import org.jware.dviz.core.DrawSomeLines;
//import org.jware.dviz.core.AbstractGraphicsHandler;
import org.jware.dviz.core.Graphics2DHandler;
import org.jware.dviz.util.Utilities;
import static org.jware.dviz.util.Utilities.Utility;

/**
 * File: GrafTestMain.java Created On: 10/26/2014
 *
 * @author J. Paul Jackson <jwareservices@gmail.com>
 *
 * Purpose: Test the JDVL classes.  
 */

/**
 * This test creates two windows and draws some stuff randomly.
 * Bring the back window to the front and click and move the mouse around
 * to see the mouse being used.  The windows are updated pretty fast.
 * There is some flicker on the erases.  To be worked on.
 * 
 */

public class GrafMain {

    Graphics2DHandler drawHandler;
    Graphics2DHandler drawHandler1;

    DrawSomeRectangles rectangleDrawable;
    Drawable lineDrawable;
    Drawable circleDrawable;
 
    int clearCounter = 0;
    long runTime;
    
    Utilities.Clock clock;

    public GrafMain() {

        drawHandler = new Graphics2DHandler();
        drawHandler1 = new Graphics2DHandler();
        
        rectangleDrawable = new DrawSomeRectangles();
        lineDrawable = new DrawSomeLines();
        circleDrawable = new DrawSomeCircles();

        clock = Utility.getTimer();
        clock.startTime();
    }

    public void start() {
        drawHandler.addDrawable(rectangleDrawable);
        drawHandler.addDrawable(lineDrawable);
        drawHandler.addDrawable(circleDrawable);
    }


    public void doLoop() {

        drawHandler.setStroke(drawHandler.wideStroke);
        rectangleDrawable.setPosition(drawHandler.mouseX(), drawHandler.mouseY(), 
                                        drawHandler.mouseX() + 50, drawHandler.mouseY() + 50);
        
        rectangleDrawable.setColor(Color.BLUE);
        
        lineDrawable.setPosition(0, 0, drawHandler.mouseX(), drawHandler.mouseY());

        int n1 = Utilities.getRandom(200, 400);
        int n2 = Utilities.getNextRandom(600);
        int n3 = Utilities.getRandomBetween(3, 200);

        circleDrawable.setPosition(n1, n2, n3, n3);
        drawHandler.text("Move the mouse around.", 0, 300);
        drawHandler.loop();
       
        drawHandler.setStroke(new BasicStroke(25.0f));
        drawHandler.fillRect(n1, n2, n3, n3);
        drawHandler.circle(n1, n2, n3);
        
System.out.println(drawHandler1.key());

        drawHandler1.text("Move the mouse around.", 0, 300);
        drawHandler1.text(String.valueOf(drawHandler1.key()), 0, 300);

        if (clock.step()%2==0) {
            drawHandler1.clearScreen();
        }
    }

    public static void main(String[] args) {
        GrafMain gt = new GrafMain();
        gt.start();
        while (true) {
            gt.doLoop();
        }
    }
}
