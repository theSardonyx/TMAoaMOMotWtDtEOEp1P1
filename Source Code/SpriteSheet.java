import java.awt.image.*;

public class SpriteSheet{
	
	private BufferedImage sheet;
	private BufferedImage[][] sprites;
	private int cellWidth, cellHeight;
	private int row, column;
	
	public SpriteSheet(BufferedImage image, int cellWidth, int cellHeight) {
		this.cellHeight = cellHeight;
		this.cellWidth = cellWidth;
		
		sheet = image;
		
		if(sheet==null) return;
		int width = sheet.getWidth();
		int height = sheet.getHeight();
		
		this.setRows(height/this.cellHeight);
		this.setColumns(width/this.cellWidth);
		
		sprites = new BufferedImage[column][row];
	}
	
	public BufferedImage get(int i, int j) {
		if(sprites[i][j] == null)
			sprites[i][j] = sheet.getSubimage(i*cellWidth, j*cellHeight, cellWidth, cellHeight);
		
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
