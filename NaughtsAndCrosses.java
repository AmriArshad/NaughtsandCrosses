import java.util.*;
import java.util.List;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class NaughtsAndCrosses extends JPanel{
	
	private static final int ROWS = 3;
	private static final int COLS = 3;
	
	private enum GameStatus { NAUGHT_WON, CROSS_WON, IN_PROGRESS, DRAW };
	
	// Values to be used for button text within a board. 
	private static String NAUGHT = "0";
	private static String CROSS = "X";
	private static String BLANK = " ";
	
	// A 2 dimensional array of JButton objects. Each button is intended to 
	// display the text NAUGHT, CROSS or BLANK (see above). 
	private JButton[][] _board;

	public NaughtsAndCrosses() {
		// Build the GUI.
		buildGUI();
		
		// TO DO: set up event handlers.

	}
	
	private void buildGUI() {
		// Initialise the board.
		_board = new JButton[ROWS][COLS];
		
		for(int row = 0; row < ROWS; row++) {
			for(int col = 0; col < COLS; col++) {
				_board[row][col] = new JButton(BLANK);
				this.add(_board[row][col]);
			}
		}

		// TO DO: create other GUI components and lay them out as appropriate.
		// Note that _board only stores 9 JButton objects using a 3x3 array. 
		// The buttons still need to be added to the GUI - use GridLayout to 
		// add and layout the JButtons on a JPanel. 

		this.setLayout(new GridLayout(3,3));
		
	}
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// TO DO: Create and configure a JFrame to display the GUI.
				// Make class NaughtsAndCrosses extend JPanel so that a 
				// NaughtsAndCrosses object can be added to the JFrame.
				
				JFrame frame = new JFrame("Naughts and Crosses"); 
				JPanel p = new JPanel();

				JButton newGame = new JButton("New Game");
				newGame.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						frame.dispose();
						run();
					}
				});
				p.add(newGame);
				
				frame.add(new NaughtsAndCrosses());
				frame.add(p, BorderLayout.SOUTH);

				frame.setSize(300,300);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}

	// Helper method to determine the status of a Naughts and Crosses game. 
	// This method processes the 2 dimensional array of JButton objects, and 
	// uses the text value (NAUGHT, CROSS or BLANK) of each JButton to 
	// calculate the game's state of play.
	private GameStatus getGameStatus() {
		GameStatus status = GameStatus.DRAW;
		
		List<String> lines = new ArrayList<String>();
		
		// Top row
		lines.add(_board[0][0].getText() + _board[0][1].getText() + _board[0][2].getText());
		
		// Middle row
		lines.add(_board[1][0].getText() + _board[1][1].getText() + _board[1][2].getText());
		
		// Bottom row
		lines.add(_board[2][0].getText() + _board[2][1].getText() + _board[2][2].getText());
		
		// Left col
		lines.add(_board[0][0].getText() + _board[1][0].getText() + _board[2][0].getText());
		
		// Middle col
		lines.add(_board[0][1].getText() + _board[1][1].getText() + _board[2][1].getText());
		
		// Right col
		lines.add(_board[0][2].getText() + _board[1][2].getText() + _board[2][2].getText());
		
		// Diagonals
		lines.add(_board[0][0].getText() + _board[1][1].getText() + _board[2][2].getText());
		lines.add(_board[0][2].getText() + _board[1][1].getText() + _board[2][0].getText());
		
		// Check to see if there's any cell without a NAUGHT or a CROSS.
		for(String line : lines) {
			// System.out.println("Looking at line: |" + line + "|");
			if(line.contains(BLANK)) {
				status = GameStatus.IN_PROGRESS;
				// System.out.println("In progress");
				break;
			}
		}
		
		// Check to see if there's any line of NAUGHTs or CROSSes.
		if(lines.contains(CROSS + CROSS + CROSS)) {
			status = GameStatus.CROSS_WON; 
		} else if (lines.contains(NAUGHT + NAUGHT + NAUGHT)) {
			status = GameStatus.NAUGHT_WON;
		}
		
		return status;
	}
	
}