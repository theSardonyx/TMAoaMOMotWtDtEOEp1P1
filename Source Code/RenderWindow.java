import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

import javax.swing.*;

public class RenderWindow extends JFrame {
	private int width, height;
	private Canvas canvas;
	private BufferStrategy buffer;
	private Graphics2D g;
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
	
	@Override
	public void addMouseListener(MouseListener ml) {
		super.addMouseListener(ml);
		canvas.addMouseListener(ml);
	}
	
	@Override
	public void addMouseMotionListener(MouseMotionListener mml) {
		super.addMouseMotionListener(mml);
		canvas.addMouseMotionListener(mml);
	}
	
	@Override
	public void addKeyListener(KeyListener kl) {
		super.addKeyListener(kl);
		canvas.addKeyListener(kl);
	}
	
	public void preDraw() {
		g = (Graphics2D) buffer.getDrawGraphics();
		g.setColor(new Color(0x000000));
		g.fillRect(0, 0, width, height);
	}
	
	public void draw(Drawable d) {
		d.draw(g);
	}
	
	public void finishDraw() {
		buffer.show();
		g.dispose();
	}
}
