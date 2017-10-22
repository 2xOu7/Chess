import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

public class BlackRook extends Chesspiece {
	
	private static ImageIcon image = new ImageIcon("/Users/jonathan/Documents/Photos/blackrook.png");
	
	public BlackRook() {
		super(getScaledIcon(image));
		color = Color.BLACK;
	}
	
	/*** Returns all possible areas where the white rook can move to forward
     * 
     * @param b
     * @return
     */
    public void FMoves(Chessboard c) {
    	Chesspiece forward = c.FindChessPiece(this.getXCoordinate()+1, this.getYCoordinate());
    	int i = 0;
    	while (forward != null) {
    		if (forward.getIcon() == null) {
    			forward.setBackground(Color.YELLOW);
    			if (i == 0) {
    				forward.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,Color.BLACK));
    			} else {
    				forward.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1,Color.BLACK));
    			}
    		} else if (forward.getPieceColor() == Color.WHITE) {
    			forward.setBackground(Color.YELLOW);
    			if (i == 0) {
    				forward.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,Color.BLACK));
    			} else {
    				forward.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1,Color.BLACK));
    			}
    			break;
    		} else {
    			break;
    		}
    		Chesspiece next = c.FindChessPiece(forward.getXCoordinate()+1, forward.getYCoordinate());
    		forward = next;
    		i++;
    	}
    	
    }
    
    /*** Returns all possible areas where the white bishop can move backwards
     * 
     * @param b
     * @return
     */
    public void BMoves(Chessboard c) {
    	Chesspiece back = c.FindChessPiece(this.getXCoordinate()-1, this.getYCoordinate());
    	int i = 0;
    	while (back != null) {
    		if (back.getIcon() == null) {
    			back.setBackground(Color.YELLOW);
    			if (i == 0) {
    				back.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,Color.BLACK));
    			} else {
    				back.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1,Color.BLACK));
    			}
    		} else if (back.getPieceColor() == Color.WHITE) {
    			back.setBackground(Color.YELLOW);
    			if (i == 0) {
    				back.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,Color.BLACK));
    			} else {
    				back.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1,Color.BLACK));
    			}
    			break;
    		} else {
    			break;
    		}
    		Chesspiece next = c.FindChessPiece(back.getXCoordinate()-1, back.getYCoordinate());
    		back = next;
    		i++;
    	}
    }
    
    /*** Returns all possible areas where the white rook can move to the left
     * 
     * @param b
     * @return
     */
    public void LMoves(Chessboard c) {
    	Chesspiece left = c.FindChessPiece(this.getXCoordinate(), this.getYCoordinate()-1);
    	int i = 0;
    	while (left != null) {
    		if (left.getIcon() == null) {
    			left.setBackground(Color.YELLOW);
    			if (i == 0) {
    				left.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,Color.BLACK));
    			} else {
    				left.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 0,Color.BLACK));
    			}
    		} else if (left.getPieceColor() == Color.WHITE) {
    			left.setBackground(Color.YELLOW);
    			if (i == 0) {
    				left.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,Color.BLACK));
    			} else {
    				left.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 0,Color.BLACK));
    			}
    			break;
    		} else {
    			break;
    		}
    		Chesspiece next = c.FindChessPiece(left.getXCoordinate(), left.getYCoordinate()-1);
    		left = next;
    		i++;
    	}
    	
    } 
    
    /*** Returns an ArrayList of possible areas where the white bishop can move to the left backwards
     * 
     * @param b
     * @return
     */
    public void RMoves(Chessboard c) {
    	
    	Chesspiece right = c.FindChessPiece(this.getXCoordinate(), this.getYCoordinate()+1);
    	int i = 0;
    	while (right != null) {
    		if (right.getIcon() == null) {
    			right.setBackground(Color.YELLOW);
    			if (i == 0) {
    				right.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,Color.BLACK));
    			} else {
    				right.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1,Color.BLACK));
    			}
    		} else if (right.getPieceColor() == Color.WHITE) {
    			right.setBackground(Color.YELLOW);
    			if (i == 0) {
    				right.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,Color.BLACK));
    			} else {
    				right.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1,Color.BLACK));
    			}
    			break;
    		} else {
    			break;
    		}
    		Chesspiece next = c.FindChessPiece(right.getXCoordinate(), right.getYCoordinate()+1);
    		right = next;
    		i++;
    	}
    }
    
    /*** Returns all possible moves of this WhiteRook
     * 
     * @param c
     */
    public void allMoves(Chessboard c) {
    	if (this.getIcon() != null) {
	    	this.FMoves(c);
	    	this.BMoves(c);
	    	this.LMoves(c);
	    	this.RMoves(c);
    	}
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
