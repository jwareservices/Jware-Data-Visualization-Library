package org.jware.dviz.core;


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
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/**
 * File: DrawSomeRectangles.java Created On: 26/00/2014
 *
 * @author J. Paul Jackson <jwareservices@gmail.com>
 *
 * Purpose:
 */
public class DrawSomeRectangles implements Drawable {

    int x1=0;
    int y1=0;
    int x2=0;
    int y2=0;

    Color color;
    
    public DrawSomeRectangles() {
    }

    @Override
    public void draw(Graphics grafPort) {

        grafPort.setColor(color);
        grafPort.fillRect(50, 50, 100, 100);
        
        grafPort.setColor(Color.BLACK);
        ((Graphics2D)grafPort).draw(new Rectangle2D.Float(x1, y1, x2, y2));
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
    @Override
    public void setPosition(int x1, int y1, int x2, int y2) {
        this.x1=x1;
        this.y1=y1;
        this.x2=x2;
        this.y2=y2;
    }
}
