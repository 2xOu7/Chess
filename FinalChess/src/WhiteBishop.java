import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

public class WhiteBishop extends Chesspiece {
	
	private static ImageIcon image = new ImageIcon("/Users/jonathan/Documents/Photos/whitebishop.png");
	
	public WhiteBishop() {
		super(getScaledIcon(image));
		color = Color.WHITE;
		// TODO Auto-generated constructor stub
	}
	
    
	/*** Returns all possible areas where the black bishop can move to the left forwards
     * 
     * @param b
     * @return
     */
    public void FLeftMoves(Chessboard c) {
    	Chesspiece left = c.FindChessPiece(this.getXCoordinate()-1, this.getYCoordinate()-1);
    	while (left != null) {
    		if (left.getIcon() == null) {
    			left.setBackground(Color.YELLOW);
    			left.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
    		} else if (left.getPieceColor() == Color.BLACK) {
    			left.setBackground(Color.YELLOW);
    			left.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
    			break;
    		} else {
    			break;
    		}
     		Chesspiece NextMove = c.FindChessPiece(left.getXCoordinate()-1, left.getYCoordinate()-1);
    		left = NextMove;
    	}
    }
    
    /*** Returns all possible areas where the black bishop can move to the right forwards
     * 
     * @param b
     * @return
     */
    public void FRightMoves(Chessboard c) {
    	Chesspiece right = c.FindChessPiece(this.getXCoordinate()-1, this.getYCoordinate()+1);
    	while (right != null) {
    		if (right.getIcon() == null) {
    			right.setBackground(Color.YELLOW);
    			right.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
    		} else if (right.getPieceColor() == Color.BLACK) {
    			right.setBackground(Color.YELLOW);
    			right.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
    			break;
    		} else {
    			break;
    		}
    		Chesspiece NextMove = c.FindChessPiece(right.getXCoordinate()-1, right.getYCoordinate()+1);
    		right = NextMove;
    	}
    }
    
    /*** Returns all possible areas where the black bishop can move to the left backwards
     * 
     * @param b
     * @return
     */
    public void BLeftMoves(Chessboard c) {
    	Chesspiece left = c.FindChessPiece(this.getXCoordinate()+1, this.getYCoordinate()-1);
    	while (left != null) {
    		if (left.getIcon() == null) {
    			left.setBackground(Color.YELLOW);
    			left.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
    		} else if (left.getPieceColor() == Color.BLACK) {
    			left.setBackground(Color.YELLOW);
    			left.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
    			break;
    		} else {
    			break;
    		}
    		Chesspiece NextMove = c.FindChessPiece(left.getXCoordinate()+1, left.getYCoordinate()-1);
    		left = NextMove;
    	}
    } 
    
    /*** Returns all possible areas where the black bishop can move to the left backwards
     * 
     * @param b
     * @return
     */
    public void BRightMoves(Chessboard c) {
    	Chesspiece right = c.FindChessPiece(this.getXCoordinate()+1, this.getYCoordinate()+1);
    	while (right != null) {
    		if (right.getIcon() == null) {
    			right.setBackground(Color.YELLOW);
    			right.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
    		} else if (right.getPieceColor() == Color.BLACK) {
    			right.setBackground(Color.YELLOW);
    			right.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
    			break;
    		} else {
    			break;
    		}
    		Chesspiece NextMove = c.FindChessPiece(right.getXCoordinate()+1, right.getYCoordinate()+1);
    		right = NextMove;
    	}
    } 
    
    /*** Returns all possible areas where this black bishop can move
     * 
     * @param c
     */
    public void allMoves(Chessboard c) {
    	if (this.getIcon() != null) {
	    	FLeftMoves(c);
	    	FRightMoves(c);
	    	BLeftMoves(c);
	    	BRightMoves(c);
    	}
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
