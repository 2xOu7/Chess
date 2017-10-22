import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Chesspiece extends JLabel {
	
	private int x;
	private int y;
	private int numberOfMoves;
	protected Color color;
    /*** Scales an image according to the dimensions wxh
     * 
     * @param srcImg
     * @param w
     * @param h
     * @return
     */
	public Chesspiece(ImageIcon piece) {
		super(getScaledIcon(piece));
	}
	
	/*** Constructor that mirror original JLabel constructor
	 * 
	 * @param x
	 */
	public Chesspiece(String x) {
		super(x);
	}
	
	/*** sets the coordinates for this chesspiece
	 * 
	 * @param i
	 * @param j
	 */
	protected void setCoordinates(int first, int second){
		this.x = first;
		this.y = second;
	}
	
	/*** Returns the x-coordinate for this chesspiece
	 * 
	 */
	public int getXCoordinate() {
		return x;
	}
	
	/*** Returns the y-coordinate for this chesspiece
	 * 
	 */
	public int getYCoordinate() {
		return y;
	}
	
	/*** Returns a scaled image
	 * 
	 * @param srcImg
	 * @param w
	 * @param h
	 * @return
	 */
    protected static Image getScaledImage(Image srcImg, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }
    
    /*** Returns a scaled image icon on a JLabel to put onto the board 
     * 
     * @param x
     * @return
     */
    protected static ImageIcon getScaledIcon(ImageIcon x) {
		Image y = x.getImage();
		Image scaled_image = getScaledImage(y,75,75);
		ImageIcon new_icon = new ImageIcon(scaled_image);
		return new_icon;

    }
    
    /*** Swaps the coordinates of two chesspieces
     * 
     * @param y
     */
    protected void swap_coordinates(Chesspiece y) {
    	int x_coor = this.getXCoordinate();
    	int y_coor = this.getYCoordinate();
    	this.setCoordinates(y.getXCoordinate(), y.getYCoordinate());
    	y.setCoordinates(x_coor, y_coor);
    }
    
    /*** Increments the number of moves by this chesspiece by 1
     * 
     */
    public void increment() {
    	numberOfMoves += 1;
    }
    
    /*** Getter for numberOfMoves
     * 
     * @return
     */
    public int getMoves() {
    	return numberOfMoves;
    }
    
    /*** getter for the color of this piece
     * 
     * @return
     */
    public Color getPieceColor() {
    	return color;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
