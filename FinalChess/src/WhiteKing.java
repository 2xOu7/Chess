import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

public class WhiteKing extends Chesspiece {
	
	private static ImageIcon image = new ImageIcon("/Users/jonathan/Documents/Photos/whiteking.png");
	
	public WhiteKing() {
		super(getScaledIcon(image));
		color = Color.WHITE;
		// TODO Auto-generated constructor stub
	}
	
	/*** Returns an ArrayList of all possible moves this king can make
	 * 
	 * @param c
	 * @return
	 */
	public void allMoves(Chessboard c) {
		if (this.getIcon() != null) {
			ArrayList<Chesspiece> moves = new ArrayList<Chesspiece>();
			moves.add(c.FindChessPiece(this.getXCoordinate()-1, this.getYCoordinate()));
			moves.add(c.FindChessPiece(this.getXCoordinate()-1, this.getYCoordinate()-1));
			moves.add(c.FindChessPiece(this.getXCoordinate()-1, this.getYCoordinate()+1));
			moves.add(c.FindChessPiece(this.getXCoordinate(), this.getYCoordinate()+1));
			moves.add(c.FindChessPiece(this.getXCoordinate(), this.getYCoordinate()-1));
			moves.add(c.FindChessPiece(this.getXCoordinate()+1, this.getYCoordinate()));
			moves.add(c.FindChessPiece(this.getXCoordinate()+1, this.getYCoordinate()-1));
			moves.add(c.FindChessPiece(this.getXCoordinate()+1, this.getYCoordinate()+1));
			
			for (Chesspiece piece: moves) {
				if (piece != null) {
					if (piece.getIcon() == null) {
						piece.setBackground(Color.YELLOW);
						piece.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
					} else if (piece.getPieceColor() != this.getPieceColor()) {
						piece.setBackground(Color.YELLOW);
						piece.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
					}
				}
			}
			left_castle(c);
			right_castle(c);
		}
	}
	
	/*** Deterines whether the left castling maneuver is possible
	 * 
	 * @param c
	 */
	public void left_castle(Chessboard c) {
		Chesspiece pawn_1 = c.FindChessPiece(this.getXCoordinate(), 0);
		if (this.getMoves() == 0 && pawn_1.getMoves() == 0 && c.getWCheck() == false) {
			if (pawn_1 instanceof WhiteRook && pawn_1.getIcon() != null) {
				boolean active = true;
				for (int i = 1; i < 4; i++) {
					if (c.FindChessPiece(this.getXCoordinate(), i).getIcon() != null) {
						active = false;
					}
				}
				if (active) {
					pawn_1.setBackground(Color.YELLOW);
				}
			}
		}
	}
	
	/*** Deterines whether the right castling maneuver is possible
	 * 
	 * @param c
	 */
	public void right_castle(Chessboard c) {
		Chesspiece pawn_1 = c.FindChessPiece(this.getXCoordinate(), 7);
		if (this.getMoves() == 0 && pawn_1.getMoves() == 0 && c.getWCheck() == false) {
			if (pawn_1 instanceof WhiteRook && pawn_1.getIcon() != null) {
				boolean active = true;
				for (int i = 5; i < 7; i++) {
					if (c.FindChessPiece(this.getXCoordinate(), i).getIcon() != null) {
						active = false;
					}
				}
				if (active) {
					pawn_1.setBackground(Color.YELLOW);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
