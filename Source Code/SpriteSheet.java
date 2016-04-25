/**
* The class used for the spritesheets
* 
* 
* @author	Aemielvin Loremia
* @author	Ivan Martinez
*/
import java.awt.image.*;

public class SpriteSheet{
	
	private BufferedImage sheet;
	private BufferedImage[][] sprites;
	private int cellWidth, cellHeight;
	private int row, column;
	
	/*
	Contructor for a SpriteSheet object
	Opens the necessary spritsheet file and is spliced by this class
	@param image: BufferedImage object, from the spritesheet
	@param cellWidth: Width to splice a sprite from
	@param cellHeight: Height to splice a sprite from
	*/
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
	/*
	Splice a specfic sprite from the spritesheet, given the coordinates
	@param col: constant to adjust the width for splicing
	@param row: constant to adjust the height for splicing
	*/
	public BufferedImage get(int col, int row) {
		if(sprites[col][row] == null)
			sprites[col][row] = sheet.getSubimage(col*cellWidth, row*cellHeight, cellWidth, cellHeight);
		
		return sprites[col][row];
	}
	/*
	Method used to get the number of available columns of the spritesheet 
	based from the width of a cell, and the image's width
	*/
	public int getColumns() {
		return column;
	}
	/*
	Set the number of available columns
	@param column: the value of the total columns
	*/
	public void setColumns(int column) {
		this.column = column;
	}
	/*
	Method used to get the number of available rows	of the spritesheet 
	based from the height of a cell, and the image's height
	*/
	public int getRows() {
		return row;
	}
/*
	Set the number of available rows
	@param column: the value of the total row
	*/
	public void setRows(int row) {
		this.row = row;
	}
	
}
