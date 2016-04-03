import java.awt.*;
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
		//canvas.setBackground(Color.BLACK);
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
		g = (Graphics2D) buffer.getDrawGraphics();
	}
	
	public void preDraw() {
		g.clearRect(0, 0, width, height);
		g.setColor(new Color(0x000000));
		g.fillRect(0, 0, width, height);
	}
	
	public void draw() {
	}
	
	public void finishDraw() {
		buffer.show();
		g.dispose();
	}
}
