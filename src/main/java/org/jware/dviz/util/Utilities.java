package org.jware.dviz.util;

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
import java.io.PrintStream;
import java.text.DateFormat;
import java.util.Date;
import java.util.Random;
import java.util.logging.Logger;
import org.jware.dviz.math.Vec;

/**
 * File: Utilities.java Created On: 05/00/2014
 *
 * @author J. Paul Jackson <jwareservices@gmail.com>
 *
 * Purpose: To provide a set of general utility features for the library.
 */
public class Utilities {

    /**
     * Most methods called via static reference except Clock. To gain access to
     * the Clock class use: Utility.getTimer();
     */
    public final static Utilities Utility = new Utilities();

    public final static PrintStream out = System.out;


    final static boolean DEBUG = false;

    public static final Logger getLogger(final String loggerClass) {
        return Logger.getLogger(loggerClass);
    }

    /**
     * A singleton clock to used for timing sequences.
     */
    public static class Clock {

        long startTime = 0;

        private Clock() {
        }

        /**
         * Current time.
         */
        public void startTime() {
            startTime = System.currentTimeMillis();
        }

        /**
         * How long have we been running in seconds.
         *
         * @return Length of time since start.
         */
        public long step() {
            return ((System.currentTimeMillis() - startTime)/1000);
        }
    }

    /**
     *
     * @return The timer.
     */
    public final Clock getTimer() {
        return new Clock();
    }
    // Clock end.

    /**
     *
     * @param w The first vector.
     * @param u The second vector.
     * @return Squared distance between two Vectors.
     */
    public final static float squareDistanceBetweenVectors(final Vec w, final Vec u) {
        return (w.x - u.x) * (w.x - u.x) + (w.y - u.y) * (w.y - u.y);
    }

    /**
     *
     * @param w The first vector.
     * @param u The second vector.
     * @return Distance between two vectors.
     */
    public final static float distanceBetweenVectors(final Vec w, final Vec u) {
        return (float) Math.sqrt(squareDistanceBetweenVectors(w, u));
    }

    /**
     * Find the polar coordinates for the ending points of a vector starting at
     * 0 ,0.
     *
     * @param radius
     * @param theta
     * @return
     */
    public static float[] getPolarCoorindates(final int radius, final float theta) {

        float[] points = new float[2];
        points[0] = (float) (radius * Math.cos(theta));
        points[1] = (float) (radius * Math.sin(theta));

        return points;
    }

    public static final int byteToUnsignedInt(byte b) {
        return (b >= 0) ? ((int) b) : ((int) (256 + b));
    }

    public static int fourBytesToInt(byte b[], int offset) {
        int value;

        value = byteToUnsignedInt(b[offset++]);
        value |= (byteToUnsignedInt(b[offset++]) << 8);
        value |= (byteToUnsignedInt(b[offset++]) << 16);
        value |= (byteToUnsignedInt(b[offset++]) << 24);

        return (value);
    }

    public static final void intToFourBytes(int iValue, byte b[], int offset) {
        b[offset++] = (byte) ((iValue) & 0xff);
        b[offset++] = (byte) ((iValue >>> 8) & 0xff);
        b[offset++] = (byte) ((iValue >>> 16) & 0xff);
        b[offset++] = (byte) ((iValue >>> 24) & 0xff);
    }

    /**
     * Print to standard system out.
     *
     * @param msg Message to be printed.
     */
    public final static void printMsg(final String msg) {
        System.out.println(msg);
    }

    /* Randomizer stuff */
    final static Random r = new Random();

    public final static int getRandom(int aStart, int aEnd) {
        //get the range, casting to long to avoid overflow problems
        long range = (long) aEnd - (long) aStart + 1;
        // compute a fraction of the range, 0 <= frac < range
        long fraction = (long) (range * r.nextDouble());
        return (int) (fraction + aStart);
    }

    public final static int getNextRandom(int limit) {
        return r.nextInt(limit);
    }

    public final static int getRandomBetween(int lowerBound, int upperBound) {
        return (int) (Math.random() * upperBound) + lowerBound;
    }

    //***********************  This will move or may not even be implemented
    
    public final RGB getRGB(double r, double g, double b) {
        return new RGB (r, g, b);
    }
    
    public class RGB {

        double r, g, b;

        public RGB(double r, double g, double b) {
            this.r = r;
            this.g = g;
            this.b = b;
        }

        /**
         * Really need to implement the alpha channel too.
         * @param rgb 
         */
        public RGB(int rgb) {
            r = (double) (rgb >> 16 & 0xff) / 255;
            g = (double) (rgb >> 8 & 0xff) / 255;
            b = (double) (rgb >> 0 & 0xff) / 255;
        }

        public void scale(double scale) {
            r *= scale;
            g *= scale;
            b *= scale;
        }

        public void add(RGB texel) {
            r += texel.r;
            g += texel.g;
            b += texel.b;
        }

        public int toRGB() {
            return 0xff000000 | (int) (r * 255.99) << 16
                    | (int) (g * 255.99) << 8 | (int) (b * 255.99) << 0;
        }
    }

}


/*
    public static final Color BLACK = Color.BLACK;
    public static final Color BLUE = Color.BLUE;
    public static final Color CYAN = Color.CYAN;
    public static final Color DARK_GRAY = Color.DARK_GRAY;
    public static final Color GRAY = Color.GRAY;
    public static final Color GREEN = Color.GREEN;
    public static final Color LIGHT_GRAY = Color.LIGHT_GRAY;
    public static final Color MAGENTA = Color.MAGENTA;
    public static final Color ORANGE = Color.ORANGE;
    public static final Color PINK = Color.PINK;
    public static final Color RED = Color.RED;
    public static final Color WHITE = Color.WHITE;
    public static final Color YELLOW = Color.YELLOW;

*/