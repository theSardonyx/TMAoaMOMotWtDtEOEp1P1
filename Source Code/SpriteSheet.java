import java.awt.image.*;
import java.io.*;

import javax.imageio.ImageIO;

public class SpriteSheet{
	
	private BufferedImage sheet;
	private BufferedImage[][] sprites;
	private int cellWidth, cellHeight;
	private int row, column;
	
	public SpriteSheet(String address, int cellWidth, int cellHeight) {
		this.cellHeight = cellHeight;
		this.cellWidth = cellWidth;
		
		try {
			sheet = ImageIO.read(new File(address));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(sheet==null) return;
		int width = sheet.getWidth();
		int height = sheet.getHeight();
		
		this.setRows(height/cellHeight);
		this.setColumns(width/cellWidth);
		
		sprites = new BufferedImage[column][row];
		for(int i=0; i<column; i++)
			for(int j=0; j<row; j++)
				sprites[i][j] = sheet.getSubimage(i*cellWidth, j*cellHeight, cellWidth, cellHeight);
	}
	
	public BufferedImage get(int i, int j) {
		return sprites[i][j];
	}

	public int getColumns() {
		return column;
	}

	public void setColumns(int column) {
		this.column = column;
	}

	public int getRows() {
		return row;
	}

	public void setRows(int row) {
		this.row = row;
	}
	
	
}
