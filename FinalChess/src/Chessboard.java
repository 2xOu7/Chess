import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingConstants;

public class Chessboard extends JFrame implements MouseListener, KeyListener, Serializable {
	/**
	 * mics
	 */
	private static final long serialVersionUID = 1L;

	/** Show the GUI  */
    public static void main(String[] args) {
    	Chessboard gui = new Chessboard();
    }
    private final Color[] colors= new Color[] {Color.WHITE, Color.GRAY}; //the colors of the squares on the grid
    private final int row_number = 8;
    private final int column_number = 8;
    private Container cp = this.getContentPane();
    private ArrayList<Chesspiece> squares = new ArrayList<Chesspiece>(); //an ArrayList of all the pieces
    private boolean isFirstClick = true;
    private Chesspiece first_clicked; //the space/piece that is first clicked
    private Color turn = Color.WHITE; //the current turn
    private Chesspiece black_king; //black king
    private Chesspiece white_king; //white king
    private boolean white_in_check; //whether white is in check
    private boolean black_in_check; //whether black is in check
    private ArrayList<Chesspiece> available_moves = new ArrayList<Chesspiece>();
    private boolean transform;
    private Chesspiece pawn_to_transform;
    private String previous;
    
    public boolean getWCheck() {
    	return white_in_check;
    }
    
    public boolean getBCheck() {
    	return black_in_check;
    }
    /** Constructor: frame with title "Chessboard",
        8x8 grid
        GridLayout
     */
    public Chessboard() {
    	super("Chess");
    	this.setSize(2000, 2000);
    	setLocationRelativeTo(null);
    	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    	setLayout(new GridLayout(row_number, column_number));
    	BuildBoard(cp);
    	setVisible(true);
    	this.addKeyListener(this);
    }
    
    /*** Initializes the chessboard (8x8 grid with pieces set accordingly)
     * 
     * @param cp
     */
    private void BuildBoard(Container cp) {
    	for (int i = 0; i < row_number; i++) {
    		for (int j = 0; j < column_number; j++) {
    			Chesspiece square = new WhiteSpace("");
    			if (i == 0) {
    				if (j == 0 || j == 7) {
    					square = (new BlackRook());
    				}
    				if (j == 1 || j == 6) {
    					square = (new BlackKnight());
    				}
    				if (j == 2 || j == 5) {
    					square = (new BlackBishop());
    				}
    				if (j == 3) {
    					square = (new BlackQueen());
    				}
    				if (j == 4) {
    					square = (new BlackKing());
    					this.black_king = square;
    				}
    			}
    			
    			if (i == 7) {
    				if (j == 0 || j == 7) {
    					square = (new WhiteRook());
    				}
    				if (j == 1 || j == 6) {
    					square = (new WhiteKnight());
    				}
    				if (j == 2 || j == 5) {
    					square = (new WhiteBishop());
    				}
    				if (j == 3) {
    					square = (new WhiteQueen());
    				}
    				if (j == 4) {
    					square = (new WhiteKing());
    					this.white_king = square;
    				}
    			}
    			if (i == 1) {
    				square = (new BlackPawn());
    			}
    			if (i == 6) {
    				square = (new WhitePawn());
    			}
    			square.setCoordinates(i, j);
    			square.setHorizontalAlignment(SwingConstants.CENTER);
    			square.setOpaque(true);
    			square.setBackground(colors[color_index(square)]);
    			cp.add(square);
    			squares.add(square);
    			square.addMouseListener(this);
    		}
    	}
    }
    
    /*** Reconstructs the board after a move is made
     * 
     */
    private void ReconstructBoard() {
    	for (int i = 0; i < row_number; i++) {
    		for (int j = 0; j < column_number; j++) {
    			Chesspiece piece = FindChessPiece(i,j);
    			piece.setBorder(null);
    			if ((piece instanceof BlackKing && black_in_check == true) 
    					|| (piece instanceof WhiteKing && white_in_check == true)) {
    				piece.setBackground(Color.CYAN);
    			} else {
    				piece.setBackground(colors[color_index(piece)]);
    			}
    			cp.add(piece);
    		}
    	}
    }
    
    /*** Finds the chesspiece in squares with the x-coordinate of "i" and the y_coordinate of "j"
     * 
     * @param i
     * @param j
     */
    public Chesspiece FindChessPiece(int i, int j) {
    	for (int k = 0; k < squares.size(); k++) {
    		Chesspiece q = squares.get(k);
    		if (q.getXCoordinate() == i && q.getYCoordinate() == j) {
    			return q;
    		}
    	}
    	return null;
    }
    
    /*** Determines what color the piece's background should be 
     * 
     * @param c
     * @return
     */
    private int color_index(Chesspiece c) {
    	if ((c.getXCoordinate() % 2 == 0 && c.getYCoordinate() % 2 == 0) 
    			|| (c.getXCoordinate() % 2 != 0 && c.getYCoordinate() % 2 != 0)) {
    		return 0;
    	} else {
    		return 1;
    	}
    }
    
    /*** Returns whether white is in check from c's most recent move
     * @return 
     * 
     * @return
     */
    private boolean determineWCheck(Chesspiece c) {
    	//System.out.println("Piece Coordinates: " + c.getXCoordinate() + " , " + c.getYCoordinate());
    	if (c instanceof BlackPawn) {
    		BlackPawn pawn = (BlackPawn) c;
    		pawn.allMoves(this);
    	}
    	if (c instanceof BlackRook) {
    		BlackRook rook = (BlackRook) c;
    		rook.allMoves(this);
    	}
    	if (c instanceof BlackBishop) {
    		BlackBishop bishop = (BlackBishop) c;
    		bishop.allMoves(this);
    	}
    	if (c instanceof BlackQueen) {
    		BlackQueen queen = (BlackQueen) c;
    		queen.allMoves(this);
    	}
    	if (c instanceof BlackKing) {
    		BlackKing king = (BlackKing) c;
    		king.allMoves(this);
    	}
    	if (c instanceof BlackKnight) {
    		BlackKnight knight = (BlackKnight) c;
    		knight.allMoves(this);
    	}
    	int i = 0;
    	for (Chesspiece piece: squares) {
    		if (piece.getBackground() == Color.YELLOW) {
    			//System.out.println("Coordinates: " + piece.getXCoordinate() + " , " + piece.getYCoordinate());
    			if (piece instanceof WhiteKing) {
    				//System.out.println("Coordinates: " + piece.getXCoordinate() + " , " + piece.getYCoordinate());
    				white_in_check = true;
    				i = 1;
    				cp.removeAll();
    				ReconstructBoard();
    				repaint();
    				revalidate();
    				isFirstClick = true;
    			}
    		}
    	}
    	if (i == 0) {
    		white_in_check = WinCheck();
	    	cp.removeAll();
			ReconstructBoard();
			repaint();
			revalidate();
			isFirstClick = true;
    	}
    	return white_in_check;
    }
    
    /*** Determines whether black is in check or not
     * 
     * @param c
     * @return
     */
    private boolean determineBCheck(Chesspiece c) {
    	if (c instanceof WhitePawn) {
    		WhitePawn pawn = (WhitePawn) c;
    		pawn.allMoves(this);
    	}
    	if (c instanceof WhiteRook) {
    		WhiteRook rook = (WhiteRook) c;
    		rook.allMoves(this);
    	}
    	if (c instanceof WhiteBishop) {
    		WhiteBishop bishop = (WhiteBishop) c;
    		bishop.allMoves(this);
    	}
    	if (c instanceof WhiteQueen) {
    		WhiteQueen queen = (WhiteQueen) c;
    		queen.allMoves(this);
    	}
    	if (c instanceof WhiteKing) {
    		WhiteKing king = (WhiteKing) c;
    		king.allMoves(this);
    	}
    	if (c instanceof WhiteKnight) {
    		WhiteKnight knight = (WhiteKnight) c;
    		knight.allMoves(this);
    	}
    	int i = 0;
    	for (Chesspiece piece: squares) {
    		if (piece.getBackground() == Color.YELLOW) {
    			if (piece instanceof BlackKing) {
    				i = 1;
    				black_in_check = true;
    				cp.removeAll();
    				ReconstructBoard();
    				repaint();
    				revalidate();
    				isFirstClick = true;
    			}
    		}
    	}
    	if (i == 0) { 
    		black_in_check = BinCheck();
	    	cp.removeAll();
			ReconstructBoard();
			repaint();
			revalidate();
			isFirstClick = true;
			return false;
    	}
		return black_in_check;
    }
    
    /*** Calls all moves of all black pieces
     * 
     */
    private void callBlackMoves() {
    	for (Chesspiece piece: squares) {
    		if (piece.getPieceColor() == Color.BLACK) {
    			if (piece instanceof BlackPawn) {
    				BlackPawn pawn = (BlackPawn) piece;
    				pawn.allMoves(this);
    			}
    			if (piece instanceof BlackRook) {
    				BlackRook pawn = (BlackRook) piece;
    				pawn.allMoves(this);
    			}
    			if (piece instanceof BlackBishop) {
    				BlackBishop pawn = (BlackBishop) piece;
    				pawn.allMoves(this);
    			}
    			if (piece instanceof BlackQueen) {
    				BlackQueen pawn = (BlackQueen) piece;
    				pawn.allMoves(this);
    			}
    			if (piece instanceof BlackKing) {
    				BlackKing pawn = (BlackKing) piece;
    				pawn.allMoves(this);
    			}
    			if (piece instanceof BlackKnight) {
    				BlackKnight pawn = (BlackKnight) piece;
    				pawn.allMoves(this);
    			}
    		}
    	}
    }
    
    /*** Calls all moves of all white pieces
     * 
     */
    private void callWhiteMoves() {
    	for (Chesspiece piece: squares) {
    		if (piece.getPieceColor() == Color.WHITE) {
    			if (piece instanceof WhitePawn) {
    				WhitePawn pawn = (WhitePawn) piece;
    				pawn.allMoves(this);
    			}
    			if (piece instanceof WhiteRook) {
    				WhiteRook pawn = (WhiteRook) piece;
    				pawn.allMoves(this);
    			}
    			if (piece instanceof WhiteBishop) {
    				WhiteBishop pawn = (WhiteBishop) piece;
    				pawn.allMoves(this);
    			}
    			if (piece instanceof WhiteQueen) {
    				WhiteQueen pawn = (WhiteQueen) piece;
    				pawn.allMoves(this);
    			}
    			if (piece instanceof WhiteKing) {
    				WhiteKing pawn = (WhiteKing) piece;
    				pawn.allMoves(this);
    			}
    			if (piece instanceof WhiteKnight) {
    				WhiteKnight pawn = (WhiteKnight) piece;
    				pawn.allMoves(this);
    			}
    		}
    	}
    }
    
    /*** Determines whether white is currently in check or not
     * 
     * @return
     */
    private boolean WinCheck() {
    	callBlackMoves();
    	for (Chesspiece piece: squares) {
    		if (piece.getBackground() == Color.YELLOW && piece instanceof WhiteKing) {
    			cp.removeAll();
				ReconstructBoard();
				repaint();
				revalidate();
    			return true;
    		}
    	}
    	cp.removeAll();
		ReconstructBoard();
		repaint();
		revalidate();
    	return false;
    }
    
    /*** Determines whether black is currently in check or not
     * 
     * @return
     */
    private boolean BinCheck() {
    	callWhiteMoves();
    	for (Chesspiece piece: squares) {
    		if (piece.getBackground() == Color.YELLOW && piece instanceof BlackKing) {
    			cp.removeAll();
				ReconstructBoard();
				repaint();
				revalidate();
    			return true;
    		}
    	}
    	cp.removeAll();
		ReconstructBoard();
		repaint();
		revalidate();
    	return false;
    }
    
    /*** Validifies all potential moves by a black chess piece c
     * 
     * @return
     */
    private void Bvalidify(Chesspiece c) {
    	ArrayList<Chesspiece> new_moves = new ArrayList<Chesspiece>();
    	for (Chesspiece piece: available_moves) {
    		Icon image = piece.getIcon();
    		c.swap_coordinates(piece);
    		piece.setIcon(null);
    		if (BinCheck() == false || (black_in_check == false && WinCheck() == true)) {
    			new_moves.add(piece);
    		}
    		c.swap_coordinates(piece);
    		piece.setIcon(image);
    		ReconstructBoard();
    	}
    	for (Chesspiece piece: new_moves) {
    		piece.setBackground(Color.YELLOW);
    		piece.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
    	}
    }
    
    /*** Validifies all potential moves by a white chess piece c
     * 
     * @return
     */
    private void Wvalidify(Chesspiece c) {
    	ArrayList<Chesspiece> new_moves = new ArrayList<Chesspiece>();
    	for (Chesspiece piece: available_moves) {
    		Icon image = piece.getIcon();
    		c.swap_coordinates(piece);
    		piece.setIcon(null);
    		if (WinCheck() == false || (white_in_check == false && BinCheck())) {
    			new_moves.add(piece);
    		}
    		c.swap_coordinates(piece);
    		piece.setIcon(image);
    		ReconstructBoard();
    	}
    	for (Chesspiece piece: new_moves) {
    		piece.setBackground(Color.YELLOW);
    		piece.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
    	}
    }
    
    /***Determines if white is in checkmate or not
     * 
     * @return
     */
    private boolean WinCheckmate() {
    	boolean active = true;
    	for (Chesspiece piece: squares) {
    		if (piece.getPieceColor() == Color.WHITE && piece instanceof WhitePawn) {
    			available_moves = new ArrayList<Chesspiece>();
    			WhitePawn q = (WhitePawn) piece;
    			q.allMoves(this);
    			for (Chesspiece piece2: squares) {
    				if (piece2.getBackground() == Color.YELLOW) {
    					available_moves.add(piece2);
    				}
    			}
    			Wvalidify(q);
    			for (Chesspiece piece3: squares) {
    				if (piece3.getBackground() == Color.YELLOW) {
    					active = false;
    				}
    			}
    			ReconstructBoard();
    		}
    		if (piece.getPieceColor() == Color.WHITE && piece instanceof WhiteRook) {
    			available_moves = new ArrayList<Chesspiece>();
    			WhiteRook q = (WhiteRook) piece;
    			q.allMoves(this);
    			for (Chesspiece piece2: squares) {
    				if (piece2.getBackground() == Color.YELLOW) {
    					available_moves.add(piece2);
    				}
    			}
    			Wvalidify(q);
    			for (Chesspiece piece3: squares) {
    				if (piece3.getBackground() == Color.YELLOW) {
    					active = false;
    				}
    			}
    			ReconstructBoard();
    		}
    		if (piece.getPieceColor() == Color.WHITE && piece instanceof WhiteKnight) {
    			available_moves = new ArrayList<Chesspiece>();
    			WhiteKnight q = (WhiteKnight) piece;
    			q.allMoves(this);
    			for (Chesspiece piece2: squares) {
    				if (piece2.getBackground() == Color.YELLOW) {
    					available_moves.add(piece2);
    				}
    			}
    			Wvalidify(q);
    			for (Chesspiece piece3: squares) {
    				if (piece3.getBackground() == Color.YELLOW) {
    					active = false;
    				}
    			}
    			ReconstructBoard();
    		}
    		if (piece.getPieceColor() == Color.WHITE && piece instanceof WhiteBishop) {
    			available_moves = new ArrayList<Chesspiece>();
    			WhiteBishop q = (WhiteBishop) piece;
    			q.allMoves(this);
    			for (Chesspiece piece2: squares) {
    				if (piece2.getBackground() == Color.YELLOW) {
    					available_moves.add(piece2);
    				}
    			}
    			Wvalidify(q);
    			for (Chesspiece piece3: squares) {
    				if (piece3.getBackground() == Color.YELLOW) {
    					active = false;
    				}
    			}
    			ReconstructBoard();
    		}
    		if (piece.getPieceColor() == Color.WHITE && piece instanceof WhiteQueen) {
    			available_moves = new ArrayList<Chesspiece>();
    			WhiteQueen q = (WhiteQueen) piece;
    			q.allMoves(this);
    			for (Chesspiece piece2: squares) {
    				if (piece2.getBackground() == Color.YELLOW) {
    					available_moves.add(piece2);
    				}
    			}
    			Wvalidify(q);
    			for (Chesspiece piece3: squares) {
    				if (piece3.getBackground() == Color.YELLOW) {
    					active = false;
    				}
    			}
    			ReconstructBoard();
    		}
    		if (piece.getPieceColor() == Color.WHITE && piece instanceof WhiteKing) {
    			available_moves = new ArrayList<Chesspiece>();
    			WhiteKing q = (WhiteKing) piece;
    			q.allMoves(this);
    			for (Chesspiece piece2: squares) {
    				if (piece2.getBackground() == Color.YELLOW) {
    					available_moves.add(piece2);
    				}
    			}
    			Wvalidify(q);
    			for (Chesspiece piece3: squares) {
    				if (piece3.getBackground() == Color.YELLOW) {
    					active = false;
    				}
    			}
    			ReconstructBoard();
    		}
    	}
    	cp.removeAll();
    	ReconstructBoard();
    	repaint();
    	revalidate();
    	return active;
    }
    
    /*** Determiens if black is in checkmate or not
     * 
     * @return
     */
    private boolean BinCheckmate() {
    	return false;
    }
    
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (transform == true) {
			if (e.getKeyCode() == 32) {
				if (pawn_to_transform.getPieceColor() == Color.BLACK) {
					determineWCheck(pawn_to_transform);
					if (white_in_check && WinCheckmate()) {
						System.out.println("Checkmate!");
						white_king.setBackground(Color.RED);
						return;
					}
				} else {
					determineBCheck(first_clicked);
					if (black_in_check && BinCheckmate()) {
						System.out.println("Checkmate!");
						black_king.setBackground(Color.RED);
						return;
					}
				}
				transform = false;
			}
			if (e.getKeyCode() == 39) {
				if (pawn_to_transform.getPieceColor() == Color.WHITE) {
					Chesspiece c = new WhiteSpace("");
					if (previous == null || previous.equals("K")) {
						c = new WhiteQueen();
						previous = "Q";
					} else if (previous.equals("Q")) {
						c = new WhiteRook();
						previous = "R";
					} else if (previous.equals("R")) {
						c = new WhiteBishop();
						previous = "B";
					} else {
						c = new WhiteKnight();
						previous = "K";
					}
					c.setCoordinates(pawn_to_transform.getXCoordinate(), pawn_to_transform.getYCoordinate());
	            	c.setHorizontalAlignment(SwingConstants.CENTER);
	    			c.setOpaque(true);
	    			c.addMouseListener(this);
	    			squares.set(squares.indexOf(pawn_to_transform), c);
	    			pawn_to_transform = c;
	    			cp.removeAll();
					ReconstructBoard();
					repaint();
					revalidate();
				} else {
					Chesspiece c = new WhiteSpace("");
					if (previous == null || previous.equals("K")) {
						c = new BlackQueen();
						previous = "Q";
					} else if (previous.equals("Q")) {
						c = new BlackRook();
						previous = "R";
					} else if (previous.equals("R")) {
						c = new BlackBishop();
						previous = "B";
					} else {
						c = new BlackKnight();
						previous = "K";
					}
					c.setCoordinates(pawn_to_transform.getXCoordinate(), pawn_to_transform.getYCoordinate());
	            	c.setHorizontalAlignment(SwingConstants.CENTER);
	    			c.setOpaque(true);
	    			c.addMouseListener(this);
	    			squares.set(squares.indexOf(pawn_to_transform), c);
	    			pawn_to_transform = c;
	    			cp.removeAll();
					ReconstructBoard();
					repaint();
					revalidate();
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (transform == false) {
			if (isFirstClick) {
				Object o1 = e.getSource();
				first_clicked = (Chesspiece) o1;
				if (first_clicked.getPieceColor() == turn && first_clicked.getIcon() != null) {
					if (o1 instanceof WhitePawn) {
						((WhitePawn) o1).allMoves(this);
					}
					if (o1 instanceof BlackPawn) {
						((BlackPawn) o1).allMoves(this);
					}
					if (o1 instanceof WhiteRook) {
						((WhiteRook) o1).allMoves(this);
					}
					if (o1 instanceof BlackRook) {
						((BlackRook) o1).allMoves(this);
					}
					if (o1 instanceof BlackBishop) {
						((BlackBishop) o1).allMoves(this);
					}
					if (o1 instanceof WhiteBishop) {
						((WhiteBishop) o1).allMoves(this);
					}
					if (o1 instanceof BlackQueen) {
						((BlackQueen) o1).allMoves(this);
					}
					if (o1 instanceof WhiteQueen) {
						((WhiteQueen) o1).allMoves(this);
					}
					if (o1 instanceof BlackKnight) {
						((BlackKnight) o1).allMoves(this);
					}
					if (o1 instanceof WhiteKnight) {
						((WhiteKnight) o1).allMoves(this);
					}
					if (o1 instanceof WhiteKing) {
						((WhiteKing) o1).allMoves(this);
					}
					if (o1 instanceof BlackKing) {
						((BlackKing) o1).allMoves(this);
					}
					available_moves = new ArrayList<Chesspiece>();
					for (Chesspiece piece: squares) {
						if (piece.getBackground() == Color.YELLOW) {
							available_moves.add(piece);
						}
					}
					ReconstructBoard();
					if (first_clicked.getPieceColor()== Color.BLACK) {
						Bvalidify(first_clicked);
					} else {
						Wvalidify(first_clicked);
					}
				}
				isFirstClick = false;
			} else {
				Chesspiece o2 = (Chesspiece) e.getSource();
				if (o2.getBackground() == Color.YELLOW) {
					if (white_in_check) {
						white_in_check = false;
					}
					if (black_in_check) {
						black_in_check = false;
					}
					o2.swap_coordinates(first_clicked);
					o2.setIcon(null);
					first_clicked.increment();
					ReconstructBoard();
					if (first_clicked.getPieceColor() == Color.BLACK) {
						determineWCheck(first_clicked);
						if (white_in_check && WinCheckmate()) {
							System.out.println("Checkmate!");
							white_king.setBackground(Color.RED);
							return;
						}
					} else {
						determineBCheck(first_clicked);
						if (black_in_check && BinCheckmate()) {
							System.out.println("Checkmate!");
							black_king.setBackground(Color.RED);
							return;
						}
					}
					if (first_clicked instanceof WhitePawn && first_clicked.getXCoordinate() == 0 || 
							first_clicked instanceof BlackPawn && first_clicked.getXCoordinate() == 7) {
						transform = true;
						pawn_to_transform = first_clicked;
					}
					if (turn == Color.BLACK) {
						turn = Color.WHITE;
					} else {
						turn = Color.BLACK;
					}
				} else {
					cp.removeAll();
					ReconstructBoard();
					repaint();
					revalidate();
					isFirstClick = true;
				}
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}