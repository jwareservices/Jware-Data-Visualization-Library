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
import java.awt.Font;
import java.awt.FontMetrics;

/**
 * File: GraphicsState.java Created On: 10/10/2014
 *
 * @author J. Paul Jackson <jwareservices@gmail.com>
 *
 * Purpose: A simple wrapper for storing current screen colors and font information.
 */
public class GraphicsState {

    public Color backgroundColor;
    public Color foregroundColor;
    public Font font;
    public FontMetrics fontMetrics;

 
    public GraphicsState() {
//        backgroundColor = new Color(0, 0, 0);
//        foregroundColor = new Color(0, 0, 0);
//        font=new Font();

    }
    
   public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color background) {
        this.backgroundColor = background;
    }

    public Color getForegroundColor() {
        return foregroundColor;
    }

    public void setForegroundColor(Color foregroundColor) {
        this.foregroundColor = foregroundColor;
    }

    public FontMetrics getFontMetrics() {
        return fontMetrics;
    }

    public void setFontMetrics(FontMetrics fontMetrics) {
        this.fontMetrics = fontMetrics;
    }
}

