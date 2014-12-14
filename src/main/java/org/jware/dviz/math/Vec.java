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

/**
 * File: Vec.java Created On: 10/1/2014
 *
 * @author J. Paul Jackson <jwareservices@gmail.com>
 * 
 * Purpose: A Vector class to implement vector math on a graph. 
 * 
 * TO-DO: This class only does 2D stuff now, but will eventually be 3D.
 * Also will go through several iterations adding more functionality as 
 * we go
 *
 */

public class Vec {

    /**
     * The positional members
     */
    public float x;
    public float y;
    public float z;

    /**
     * Initialize me to nothingness
     */
    public Vec() {
        x = y = z = 0f;
    }

    /**
     * construct me again
     * @param x
     * @param y
     * @param z 
     */
    public Vec(final float x, final float y, final float z) {
        set(x, y, z);
    }

    /**
     * Make me someone else
     * @param other 
     */
    public Vec(Vec other) {
        set(other);
    }

    /**
     * Set the members from another Vec
     * @param vector 
     */
    public final void set(final Vec vector) {
        x = vector.x;
        y = vector.y;
        z = vector.z;
    }

    /**
     * Set up with primitives
     * @param x
     * @param y
     * @param z 
     */
    public final void set(final float x, final float y, final float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Add to vectors together
     * @param vector 
     */
    public void add(final Vec vector) {
        x += vector.x;
        y += vector.y;
        z += vector.z;
    }

    /**
     * Add primitives to this vector
     * @param x
     * @param y
     * @param z 
     */
    public void add(final float x, final float y, final float z) {
        this.x += x;
        this.y += y;
        this.z += z;
    }

    /**
     * Subtract this vector from another
     * @param vector 
     */
    public void sub(final Vec vector) {
        x -= vector.x;
        y -= vector.y;
        z -= vector.z;
    }

    /**
     * Multiply this vector by another
     * @param multiplier 
     */
    public void mul(final float multiplier) {
        x *= multiplier;
        y *= multiplier;
        z *= multiplier;
    }

    /**
     * Multiply with another vector
     * @param vector 
     */
    public void mul(final Vec vector) {
        x *= vector.x;
        y *= vector.y;
        z *= vector.z;
    }

    /**
     * Divide this vector by another
     * @param divisor 
     */
    public void div(final float divisor) {
        if (divisor != 0.0f) {
            x /= divisor;
            y /= divisor;
            z /= divisor;
        }
    }

    /**
     * Dot product with another vector
     * @param vector
     * @return product
     */
    public float dot(final Vec vector) {
        return (x * vector.x) + (y * vector.y) + (z * vector.z);
    }

    /**
     * Calculate the magnitude, or length, of this vector
     * @return magnitude
     */
    public float mag() {
        return (float) Math.sqrt((x * x) + (y * y) + (z * z));
    }

    /**
     * Calculate the distance between two points on a graph
     * @param v
     * @return 
     */
    public float dist(final Vec v) {
        float dx = x - v.x;
        float dy = y - v.y;
        float dz = z - v.z;
        return (float) Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    /**
     * Normalize to 1
     */
    public void normalize() {
        final float length = mag();
        if (length != 0 && length != 1) {
            div(length);
        }
    }

    /**
     * Rotate this vector in the graph
     * @param theta 
     */
    public void rotate(final float theta) {
        float tempX = x;
        x = (float) (x * Math.cos(theta) - y * Math.sin(theta));
        y = (float) (tempX * Math.sin(theta) + y * Math.cos(theta));
    }
    
    /**
     * What am I in a string
     * @return 
     */
    public String toString() {
        return "[Vector]" + "\nx:" + x + "\ny:" + y + "\nz:" + z;
    }
    
/*    public static void main(String[] args) {
        
        Vec v = new Vec(13, 53, 56);
        
        System.out.println(v.toString());
    }
*/
}
