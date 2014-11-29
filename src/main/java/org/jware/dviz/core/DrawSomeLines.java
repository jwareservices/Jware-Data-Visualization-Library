package org.jware.dviz.core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

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

/**
 * File: DrawSomeLines.java 
 Created On: 27/00/2014
 * 
 * @author J. Paul Jackson <jwareservices@gmail.com>
 *
 * Purpose:
 */
public class DrawSomeLines implements Drawable {
    int x1=0;
    int y1=0;
    int x2=0;
    int y2=0;

    @Override
    public void draw(Graphics grafPort) {
        grafPort.setColor(Color.RED);
        ((Graphics2D) grafPort).draw(new Line2D.Float(x1, y1, x2, y2));
    }

    public void setPosition(int x1, int y1, int x2, int y2) {
        this.x1=x1;
        this.y1=y1;
        this.x2=x2;
        this.y2=y2;
    }
}
