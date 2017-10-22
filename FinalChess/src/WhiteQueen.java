import java.awt.Color;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class WhiteQueen extends Chesspiece {
	
	private static ImageIcon image = new ImageIcon("/Users/jonathan/Documents/Photos/whitequeen.png");
	private WhiteRook rook = new WhiteRook();
	private WhiteBishop bishop = new WhiteBishop();
	
	public WhiteQueen() {
		super(getScaledIcon(image));
		color = Color.WHITE;
		
		// TODO Auto-generated constructor stub
	}
	
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
