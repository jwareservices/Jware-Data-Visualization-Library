package org.jware.dviz.math;

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

/*
 * File: PolarCordinate.java
 *
 * @author J. Paul Jackson <jwareservices@gmail.com>
 * 
 * @version 1.2 Feb. 4, 1997
 */

/*
 * Calculate the polar coordinate points from a given radius and angle.  This
 * can be used to for example to display a series of objects around a curve.
 */
import java.awt.Point;

/**
 * @author J. Paul Jackson November, 1996
 */
public final class PolarCoordinate extends Object {

    private PolarCoordinate() {
    }

    public static Point getPoint(final int i_radius, final float i_theta) {

        float x = (float) (i_radius * Math.cos(i_theta));
        float y = (float) (i_radius * Math.sin(i_theta));

        return new Point((int) x, (int) y);
    }

    /**
     *
     * @param i_radius
     * @param i_theta
     * @return points on the graph.
     */
    public static float[] getPoints(final int i_radius, final float i_theta) {

        float[] points = new float[2];
        points[0] = (float) (i_radius * Math.cos(i_theta));
        points[1] = (float) (i_radius * Math.sin(i_theta));

        return points;
    }
}
