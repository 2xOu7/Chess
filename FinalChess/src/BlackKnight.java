import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

public class BlackKnight extends Chesspiece{

	private static ImageIcon image = new ImageIcon("/Users/jonathan/Documents/Photos/blackhorse.png");
	
	public BlackKnight() {
		super(getScaledIcon(image));
		color = Color.BLACK;
	}
	
	/*** Find all possible moves the knight can move to
	 * 
	 */
	public void FindFMoves(Chessboard c) {
			Chesspiece first = c.FindChessPiece(this.getXCoordinate()+2, this.getYCoordinate()-1);
			Chesspiece second = c.FindChessPiece(this.getXCoordinate()+2, this.getYCoordinate()+1);
			Chesspiece third = c.FindChessPiece(this.getXCoordinate()+1, this.getYCoordinate()-2);
			Chesspiece fourth = c.FindChessPiece(this.getXCoordinate()+1, this.getYCoordinate()+2);
			
			if (first != null) {
				if (first.getIcon() == null) {
					first.setBackground(Color.YELLOW);
					first.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
				} else if (first.getPieceColor() == Color.WHITE) {
					first.setBackground(Color.YELLOW);
					first.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
				}
			}
			if (second != null) {
				if (second.getIcon() == null) {
					second.setBackground(Color.YELLOW);
					second.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
				} else if (second.getPieceColor() == Color.WHITE) {
					second.setBackground(Color.YELLOW);
					second.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
				}
			}
			if (third != null) {
				if (third.getIcon() == null) {
					third.setBackground(Color.YELLOW);
					third.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
				} else if (third.getPieceColor() == Color.WHITE) {
					third.setBackground(Color.YELLOW);
					third.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
				}
			}
			if (fourth != null) {
				if (fourth.getIcon() == null) {
					fourth.setBackground(Color.YELLOW);
					fourth.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
				} else if (fourth.getPieceColor() == Color.WHITE) {
					fourth.setBackground(Color.YELLOW);
					fourth.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
				}
			}
	}
	
	/*** Find all possible moves the knight can move to backwards
	 * 
	 */
	public void FindBMoves(Chessboard c) {
			Chesspiece first = c.FindChessPiece(this.getXCoordinate()-2, this.getYCoordinate()-1);
			Chesspiece second = c.FindChessPiece(this.getXCoordinate()-2, this.getYCoordinate()+1);
			Chesspiece third = c.FindChessPiece(this.getXCoordinate()-1, this.getYCoordinate()-2);
			Chesspiece fourth = c.FindChessPiece(this.getXCoordinate()-1, this.getYCoordinate()+2);
			if (first != null) {
				if (first.getIcon() == null) {
					first.setBackground(Color.YELLOW);
					first.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
				} else if (first.getPieceColor() == Color.WHITE) {
					first.setBackground(Color.YELLOW);
					first.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
				}
			}
			if (second != null) {
				if (second.getIcon() == null) {
					second.setBackground(Color.YELLOW);
					second.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
				} else if (second.getPieceColor() == Color.WHITE) {
					second.setBackground(Color.YELLOW);
					second.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
				}
			}
			if (third != null) {
				if (third.getIcon() == null) {
					third.setBackground(Color.YELLOW);
					third.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
				} else if (third.getPieceColor() == Color.WHITE) {
					third.setBackground(Color.YELLOW);
					third.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
				}
			}
			if (fourth != null) {
				if (fourth.getIcon() == null) {
					fourth.setBackground(Color.YELLOW);
					fourth.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
				} else if (fourth.getPieceColor() == Color.WHITE) {
					fourth.setBackground(Color.YELLOW);
					fourth.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
				}
			}
	}
	
	/*** Highlights all possible moves this piece can make
	 * 
	 */
	public void allMoves(Chessboard c) {
		if (this.getIcon() != null) {
			FindFMoves(c);
			FindBMoves(c);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
