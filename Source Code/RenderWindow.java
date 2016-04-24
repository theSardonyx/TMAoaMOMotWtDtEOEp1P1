import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

import javax.swing.*;

public class RenderWindow extends JFrame {
	private int width, height;
	private Canvas canvas;
	private BufferStrategy buffer;
	private Graphics2D g;
	/*
	Constructor for a RenderWindow object
	The whole window for the game, and where most objects will be rendered
	@param title: Name of the game window
	@param width: Width of the game window
	@param height: Height of the game window
	*/
	public RenderWindow(String title, int width, int height) {
		this.width = width;
		this.height = height;
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		add(canvas);
		
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		pack();
		requestFocus();
		setVisible(true);
		
		canvas.setBounds(0, 0, width, height);
		canvas.setIgnoreRepaint(true);
		canvas.createBufferStrategy(2);
		buffer = canvas.getBufferStrategy();
	}
	
	/*
	Override method for adding a mouseListener
	Essentially needed for mouse button press detection
	@param ml: MouseListener object for this RenderWindow
	*/
	@Override
	public void addMouseListener(MouseListener ml) {
		super.addMouseListener(ml);
		canvas.addMouseListener(ml);
	}
	/*
	Override method for adding a MouseMotionListener
	Essentially needed for mouse motion detection
	@param ml: MouseListener object for this RenderWindow
	*/
	@Override
	public void addMouseMotionListener(MouseMotionListener mml) {
		super.addMouseMotionListener(mml);
		canvas.addMouseMotionListener(mml);
	}
	/*
	Override method for adding a mouseListener
	Essentially needed for keyboard press detection
	@param ml: MouseListener object for this RenderWindow
	*/
	@Override
	public void addKeyListener(KeyListener kl) {
		super.addKeyListener(kl);
		canvas.addKeyListener(kl);
	}
	
	/*
	Function for clearing the screen, before rendering the objects
	*/
	public void preDraw() {
		g = (Graphics2D) buffer.getDrawGraphics();
		g.setColor(new Color(0x000000));
		g.fillRect(0, 0, width, height);
	}
	/*
	Function for rendering/drawing the objects
	*/
	public void draw(Drawable d) {
		d.draw(g);
	}
	
	/*
	Function for the RenderWindow object to stop drawing
	And delete the graphics object to prevent memory leaks
	*/
	public void finishDraw() {
		buffer.show();
		g.dispose();
	}
}
