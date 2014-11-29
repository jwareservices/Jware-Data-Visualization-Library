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
import java.awt.geom.Ellipse2D;
import org.jware.dviz.util.Utilities;

/**
 * File: DrawSomeCircles.java Created On: 31/00/2014
 *
 * @author J. Paul Jackson <jwareservices@gmail.com>
 *
 * Purpose:
 */
public class DrawSomeCircles implements Drawable {

    int x1, y1, x2, y2;

    @Override
    public void draw(Graphics grafPort) {
        ((Graphics2D) grafPort).draw(new Ellipse2D.Float(x1, y1, x2, y2));

        grafPort.setColor(new Color(Utilities.getRandom(0, 255), Utilities.getRandom(0, 255), Utilities.getRandom(0, 255)));
        grafPort.fillOval(x1 + 20, y1 + 20, x2 + 20, y2 + 20);
    }

    @Override
    public void setPosition(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
}
