import java.awt.Color;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class BlackQueen extends Chesspiece {
	
	private static ImageIcon image = new ImageIcon("/Users/jonathan/Documents/Photos/blackqueen.png");
	private BlackBishop bishop = new BlackBishop();
	private BlackRook rook = new BlackRook();
	public BlackQueen() {
		super(getScaledIcon(image));
		color = Color.BLACK;
		
		// TODO Auto-generated constructor stub
	}
	
	/*** Returns all moves of this black queen
	 * 
	 * @param c
	 */
	public void allMoves(Chessboard c) {
		if (this.getIcon() != null) {
			rook.setCoordinates(this.getXCoordinate(), this.getYCoordinate());
			bishop.setCoordinates(this.getXCoordinate(), this.getYCoordinate());
			rook.allMoves(c);
			bishop.allMoves(c);
		}
	}
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
