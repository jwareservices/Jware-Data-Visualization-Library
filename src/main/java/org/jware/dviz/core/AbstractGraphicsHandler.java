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
import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.VolatileImage;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author J. Paul Jackson <jwareservices@gmail.com>
 * Future use.
 */
class GrafPortCanvas extends Canvas {

    public GrafPortCanvas() {
        super();
    }

    @Override
    public void paint(Graphics g) {
    }
}

/**
 * @author J. Paul Jackson <jwareservices@gmail.com>
 */
interface JDVLAbstractGraphicsInputListener extends MouseListener, MouseMotionListener, KeyListener {
    // (J)ware (D)ata (V)isualization (L)ibrary
}

/**
 * File: AbstractGraphicsHandler.java Created On: 10/15/2014
 *
 * @author J. Paul Jackson <jwareservices@gmail.com>
 *
 * Purpose: Provide an easy to wrapper for the Java drawing interfaces.
 */
public abstract class AbstractGraphicsHandler implements JDVLAbstractGraphicsInputListener {

    private static WindowListener windowCloser = new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
            lastWindow--;
            e.getWindow().setVisible(false);
            if (lastWindow <= 0) {
                System.exit(0);
            }
        }
    };

    /**
     * The circumference of circle
     */
    public static final float PI = (float) Math.PI;
    /**
     * Two times the circumference of circle
     */
    public static final float TWO_PI = (float) (2.0 * Math.PI);
    /**
     * One half the circumference of circle
     */
    public static final float HALF_PI = (float) (Math.PI / 2.0);

    /**
     * Key input stuff, ex: if ((key == CODED) && (eventyCode == UP))
     */
    public static final int CODED = 0xffff;

    /**
     * The key will be CODED and eventyCode will be this value
     */
    public static final int UP = KeyEvent.VK_UP;
    public static final int DOWN = KeyEvent.VK_DOWN;
    public static final int LEFT = KeyEvent.VK_LEFT;
    public static final int RIGHT = KeyEvent.VK_RIGHT;

    /**
     * The key will be CODED and eventyCode will be this value
     */
    public static final int ALT = KeyEvent.VK_ALT;
    public static final int CONTROL = KeyEvent.VK_CONTROL;
    public static final int SHIFT = KeyEvent.VK_SHIFT;

    /**
     * The maximum size of the push/pop stack for holding the current graphics
     * settings.
     */
    protected static final int MAX_STACK = 30;

    /**
     * The minimum size of the handler window
     */
    protected static final int MIN_WIDTH = 10;

    /**
     * The minimum height of the handler window.
     */
    protected static final int MIN_HEIGHT = 10;

    /**
     * The default width of the window.
     */
    protected static final int DEFAULT_FRAME_WIDTH = 800;

    /**
     * The default height of the window.
     */
    protected static final int DEFAULT_FRAME_HEIGHT = 600;

    /**
     * The default color settings for the background, foreground and screen
     * clear.
     */
    protected static final Color DEFAULT_BACK_COLOR = Color.WHITE;
    protected static final Color DEFAULT_FORE_COLOR = Color.BLACK;
    protected static final Color DEFAULT_CLEAR_SCR_COLOR = DEFAULT_BACK_COLOR;

    /**
     * The default Font to use when rendering text.
     */
    protected static final Font DEFAULT_FONT = new Font("Arial", Font.PLAIN, 12);

    /**
     * The default pen size for strokes
     */
    private static final float DEFAULT_PEN_SIZE = 0.1f;

    /**
     * Our drawing windows and MVC implementation, future use.
     */
    protected Container focusController;

    /**
     * A window count for exiting the program, Frames will dispose until the
     * last one which will EXIT_ON_CLOSE..
     */
    private static int lastWindow = 0;

    /**
     * The handlers drawing window.
     */
    private static JFrame grafWindow;

    /**
     * The handlers canvas, future use.
     */
    private JPanel grafCanvas;

    /**
     * The drawing windows current width
     */
    protected int frameWidth;

    /**
     * The drawing windows current height
     */
    protected int frameHeight;

    /**
     * Holds the value of the visible screen width.
     */
    protected int displayWidth;

    /**
     * Holds the value of the visible screen height.
     */
    protected int displayHeight;

    /**
     * The array to save the graphics state into.
     */
    protected int stateCount = 0;

    /**
     *
     */
    protected GraphicsState[] grafPortState;

    /**
     * The default pen size for strokes
     */
    public  BasicStroke defaultStroke = new BasicStroke(DEFAULT_PEN_SIZE);

    /**
     * The default pen size for strokes
     */
    public  BasicStroke wideStroke = new BasicStroke(DEFAULT_PEN_SIZE * 100);

    /**
     * The default pen size for strokes
     */
    public  Stroke currentStroke;

    /**
     * The current background colors.
     */
    protected Color currentBackColor;

    /**
     * The current foreground colors.
     */
    protected Color currentForeColor;

    /**
     * The current font in use.
     */
    protected Font grafPortFont;

    /**
     * Are we using double buffers, i.e. offscreen surface. Future use.
     */
    protected boolean useStrategy = false;

    /**
     * Graphics configuration is where the handler gets its buffers and
     * strategy.
     */
    private GraphicsConfiguration grafPortConfig;

    /**
     *
     */
    protected BufferStrategy grafPortStrategy;

    /**
     *
     */
    protected Graphics2D grafPort;

    /**
     * These are for future use, may set up our own buffering system.
     */
    protected BufferedImage grafPortBackImage;

    /**
     *
     */
    protected BufferedImage grafPortImage;

    /**
     *
     */
    protected VolatileImage vimage;

    /**
     *
     */
    protected Graphics2D offScreenPort;

    /**
     * The drawing interfaces.
     */
    Drawable currentDrawable;

    /**
     * A list of drawable objects.
     */
    ArrayList<Drawable> drawables;

    /**
     * The array to hold the current set of transformations.
     */
    private int stackCount = 0;

    /**
     * The stack holding a list of saved transformations.
     */
    AffineTransform[] transformationStack;

    /**
     * The current Graphics transformation.
     */
    AffineTransform currentTransform;

    /**
     * Is mouse active
     */
    protected boolean mousePressed;
    /**
     * Holds the x position of the mouse.
     */
    protected int mouseX;

    /**
     * The y position of the mouse.
     */
    protected int mouseY;

    /**
     * Future use, the drawing surface.
     */
    GrafPortCanvas drawCanvas;

    /**
     * Construct. Initialize the structures to store the graphics states,
     * transformations, and the list of Drawable interfaces.
     *
     */
    protected AbstractGraphicsHandler() {

        grafPortState = new GraphicsState[MAX_STACK];
        transformationStack = new AffineTransform[MAX_STACK];
        drawables = new ArrayList<>();

        initContainer();
        initGrafPort();
    }

    /**
     * Initialize the window structures. Must set up frame and set to visible
     * before using the buffer strategy API as it requires the windows peers to
     * be created in order to work.
     */
    private void initContainer() {

        frameWidth = DEFAULT_FRAME_WIDTH;
        frameHeight = DEFAULT_FRAME_HEIGHT;
        currentBackColor = DEFAULT_BACK_COLOR;
        currentForeColor = DEFAULT_FORE_COLOR;
        grafPortFont = DEFAULT_FONT;

        grafPortConfig = GraphicsEnvironment.
                getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        if (grafPortConfig != null) {
            grafWindow = new JFrame(grafPortConfig);
            getDisplaySize();
        } else {
            grafWindow = new JFrame();
        }

        lastWindow++;

        /**
         * *******************************************
         * Future use
         */
        grafCanvas = new JPanel();
        grafCanvas.setSize(frameWidth, frameHeight);
        grafWindow.add(grafCanvas);
        // *******************************************

        grafWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        grafWindow.setResizable(false);
        grafWindow.setSize(frameWidth, frameHeight);
        grafWindow.setVisible(true);
        grafWindow.addMouseListener(this);
        grafWindow.addMouseMotionListener(this);

        grafWindow.addWindowListener(windowCloser);
    }

    private void initGrafPort() {
        grafWindow.createBufferStrategy(2);
        grafPortStrategy = grafWindow.getBufferStrategy();
    }

    private void getDisplaySize() {
        if (grafPortConfig != null) {
            GraphicsDevice displayDevice = grafPortConfig.getDevice();

            if (displayDevice != null) {
                Rectangle screenRect
                        = displayDevice.getDefaultConfiguration().getBounds();

                displayWidth = screenRect.width;
                displayHeight = screenRect.height;
            }
        }
    }

    /**
     *
     * @param width
     */
    public void setWidth(final int width) {
        this.frameWidth = width;
    }

    /**
     *
     * @param height
     */
    public void setHeight(final int height) {
        this.frameHeight = height;
    }

    public void setStroke(){
        currentStroke=defaultStroke;
    }
    
    public void setStroke(Stroke stroke){
        currentStroke=stroke;
    }
    
    /**
     *
     * @param transform
     */
    public void setTransform(AffineTransform transform) {
        currentTransform = transform;
    }

    /**
     *
     * @param transform
     * @return
     */
    public AffineTransform getTransform(AffineTransform transform) {
        return currentTransform;
    }

    /**
     *
     */
    public void pushTransform() {
        transformationStack[stackCount] = grafPort.getTransform();
        stackCount++;
    }

    /**
     *
     */
    public void popTransform() {
        stackCount--;
        grafPort.setTransform(transformationStack[stackCount]);
    }

    /**
     *
     */
    public void pushState() {
        grafPortState[stateCount] = new GraphicsState();
        grafPortState[stateCount].backgroundColor = grafPort.getBackground();
        grafPortState[stateCount].foregroundColor = grafPort.getColor();
        grafPortState[stateCount].font = grafPort.getFont();
        stateCount++;
    }

    /**
     *
     */
    public void popState() {
        stateCount--;
        grafPort.setColor(grafPortState[stateCount].foregroundColor);
        grafPort.setBackground(grafPortState[stateCount].backgroundColor);
        grafPort.setFont(grafPortState[stateCount].font);
        grafPortState[stateCount] = null;
    }

    /**
     *
     * @param x
     * @param y
     */
    public void translate(float x, float y) {
        grafPort.translate(x, y);
    }

    /**
     *
     */
    public void clearScreen() {
        grafPort = (Graphics2D) grafPortStrategy.getDrawGraphics();
        grafPort.setColor(Color.white);
        grafPort.fillRect(0, 0, displayWidth, displayHeight);
        grafPortStrategy.show();
        Toolkit.getDefaultToolkit().sync();
    }

    /**
     *
     * @param c
     */
    public void setFillColor(Color c) {
        currentBackColor = c;
        grafPort.setColor(currentBackColor);
    }

    /**
     *
     * @param c
     */
    public void setColor(Color c) {
        currentForeColor = c;
        grafPort.setColor(currentForeColor);
    }

    /**
     *
     * @param drawable
     */
    public void setDrawable(Drawable drawable) {
        currentDrawable = drawable;
    }

    /**
     *
     * @param drawable
     */
    public void addDrawable(Drawable drawable) {
        drawables.add(drawable);
    }

    /**
     *
     * @param drawable
     */
    public void removeDrawable(Drawable drawable) {
        drawables.add(drawable);
    }

    /**
     * Draw a string.
     * @param text
     * @param x
     * @param y
     */
    public void text(String text, int x, int y) {
        beginDraw();
        grafPort.drawString(text, y, y);
        endDraw();
    }
    
    /**
     * Call draw on the stored list of drawable objects.
     */
    protected void drawAll() {
        for (Drawable d : drawables) {
            currentDrawable = d;
            update();
        }
    }

    /**
     * Draw the current drawable only. Use with the set drawable method to
     * provide an animation.
     *
     * @see setDrawable()
     */
    public void loop() {
        drawAll();
     //   update();
    }

    /**
     * Call to set up grafPort when not using Drawables.
     */
    protected void beginDraw() {
        grafPort = (Graphics2D) grafPortStrategy.getDrawGraphics();
    }

    /**
     * Show the buffer on screen when using Drawables.
     */
    protected void endDraw() {
        if (grafPort != null) {
            grafPort.dispose();
        }
        grafPortStrategy.show();
        Toolkit.getDefaultToolkit().sync();

    }

    /**
     * Update is called to do the actual draw, for use with Drawables.
     */
    private void update() {
        do {
            do {
                try {
                    grafPort = (Graphics2D) grafPortStrategy.getDrawGraphics();
                    currentDrawable.draw(grafPort);
                } finally {
                    grafPort.dispose();
                }
            } while (grafPortStrategy.contentsRestored());

            grafPortStrategy.show();

        } while (grafPortStrategy.contentsLost());

        // According to the article this call is good to keep the screen from flickering.
        // It could take a couple of extras ms for the screen to update if not called directly,
        // causing a flicker.  So this call basically forces the peer to update.
        // http://docs.oracle.com/javase/6/docs/api/java/awt/Toolkit.html#sync()
        Toolkit.getDefaultToolkit().sync();
    }

    /**
     *
     * @return
     */
    public int mouseX() {
        return mouseX;
    }

    /**
     *
     * @return
     */
    public int mouseY() {
        return mouseY;
    }

    /**
     * @param event
     */
    @Override
    public void mousePressed(MouseEvent event) {
        mousePressed = true;
        mouseX = event.getX();
        mouseY = event.getY();
    }

    /**
     * @param event
     */
    @Override
    public void mouseReleased(MouseEvent event) {
        mousePressed = false;
        mouseX = event.getX();
        mouseY = event.getY();
    }

    /**
     * @param event
     */
    @Override
    public void mouseEntered(MouseEvent event) {
        mouseX = event.getX();
        mouseY = event.getY();
    }

    /**
     * @param event
     */
    @Override
    public void mouseExited(MouseEvent event) {
        mouseX = event.getX();
        mouseY = event.getY();
    }

    /**
     * @param event
     */
    @Override
    public void mouseDragged(MouseEvent event) {
        mousePressed = true;
        mouseX = event.getX();
        mouseY = event.getY();
    }

    /**
     * @param event
     */
    @Override
    public void mouseClicked(MouseEvent event) {
    }

    /**
     * @param event
     */
    @Override
    public void mouseMoved(MouseEvent event) {
    }

    /**
     * @param event
     */
    @Override
    public void keyTyped(KeyEvent event) {
    }

    /**
     * @param event
     */
    @Override
    public void keyPressed(KeyEvent event) {
    }

    /**
     * @param event
     */
    @Override
    public void keyReleased(KeyEvent event) {
    }
}
