import java.awt.Color;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

public class BlackPawn extends Chesspiece {
	
	private static ImageIcon image = new ImageIcon("/Users/jonathan/Documents/Photos/blackpawn.png");
	
	public BlackPawn() {
		super(getScaledIcon(image));
		color = Color.BLACK;
	}
	
	/*** returns the forward move of this pawn
	 * 
	 * @return
	 */
	public void getFMove(Chessboard c) {
		Chesspiece forward = c.FindChessPiece(this.getXCoordinate()+1, this.getYCoordinate());
		Chesspiece more_forward = c.FindChessPiece(this.getXCoordinate()+2, this.getYCoordinate());
		if (forward != null && forward.getIcon() == null) {
			forward.setBackground(Color.YELLOW);
			forward.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		}
		if (this.getMoves() == 0 && more_forward != null && more_forward.getIcon() == null) {
			more_forward.setBackground(Color.YELLOW);
			more_forward.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.BLACK));
		}
	}
	
	/*** Returns the right diagonal move of this pawn
	 * 
	 * @return
	 */
	public void getRDMove(Chessboard c) {
		Chesspiece right_diagonal = c.FindChessPiece(this.getXCoordinate()+1, this.getYCoordinate()+1);
		if (right_diagonal != null && right_diagonal.getPieceColor() == Color.WHITE && right_diagonal.getIcon() != null) {
			right_diagonal.setBackground(Color.YELLOW);
			right_diagonal.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		}
	}
	
	/*** Returns the left_diagonal move of this pawn
	 * 
	 * @return
	 */
	public void getLDMove(Chessboard c) {
		Chesspiece left_diagonal = c.FindChessPiece(this.getXCoordinate()+1, this.getYCoordinate()-1);
		if (left_diagonal != null && left_diagonal.getPieceColor() == Color.WHITE && left_diagonal.getIcon() != null) {
			left_diagonal.setBackground(Color.YELLOW);
			left_diagonal.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		}
	}
	
	/*** Returns all possible moves for this pawn
	 * 
	 * @param c
	 */
	public void allMoves(Chessboard c) {
		if (this.getIcon() != null) {
			getLDMove(c);
			getRDMove(c);
			getFMove(c);
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
