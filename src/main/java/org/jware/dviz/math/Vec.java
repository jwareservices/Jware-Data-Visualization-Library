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
 */

public class Vec {

    public float x;
    public float y;
    public float z;

    public Vec() {
        x = y = z = 0f;
    }

    public Vec(final float x, final float y, final float z) {
        set(x, y, z);
    }

    public Vec(Vec other) {
        set(other);
    }

    public final void set(final Vec vector) {
        x = vector.x;
        y = vector.y;
        z = vector.z;
    }

    public final void set(final float x, final float y, final float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void add(final Vec vector) {
        x += vector.x;
        y += vector.y;
        z += vector.z;
    }

    public void add(final float x, final float y, final float z) {
        this.x += x;
        this.y += y;
        this.z += z;
    }

    public void sub(final Vec vector) {
        x -= vector.x;
        y -= vector.y;
        z -= vector.z;
    }

    public void mul(final float multiplier) {
        x *= multiplier;
        y *= multiplier;
        z *= multiplier;
    }

    public void mul(final Vec vector) {
        x *= vector.x;
        y *= vector.y;
        z *= vector.z;
    }

    public void div(final float divisor) {
        if (divisor != 0.0f) {
            x /= divisor;
            y /= divisor;
            z /= divisor;
        }
    }

    public float dot(final Vec vector) {
        return (x * vector.x) + (y * vector.y) + (z * vector.z);
    }

    public float mag() {
        return (float) Math.sqrt((x * x) + (y * y) + (z * z));
    }

    public float dist(final Vec v) {
        float dx = x - v.x;
        float dy = y - v.y;
        float dz = z - v.z;
        return (float) Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    public void normalize() {
        final float length = mag();
        if (length != 0 && length != 1) {
            div(length);
        }
    }

    public void rotate(final float theta) {
        float tempX = x;
        x = (float) (x * Math.cos(theta) - y * Math.sin(theta));
        y = (float) (tempX * Math.sin(theta) + y * Math.cos(theta));
    }
    
    public String toString() {
        return "[Vector]" + "\nx:" + x + "\ny:" + y + "\nz:" + z;
    }
    
/*    public static void main(String[] args) {
        
        Vec v = new Vec(13, 53, 56);
        
        System.out.println(v.toString());
    }
*/
}
