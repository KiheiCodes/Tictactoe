package com.kihei.main;

import java.awt.*;

public class Display {
	
	public static int[][] board = new int[3][3];
	public static int ct = 1;
	public static int winner = 0;
	
	public void render(Graphics g, int width, int height) {
		int cX = ((width - 100) / 2) - 100;
		int cY = ((height - 100) / 2) - 100;
		boolean full = true;
		
		for(int r = 0; r < 3; r++) {
			if (winner == 0) {
				if (board[r][1] == board[r][0] && board[r][1] == board[r][2]) {
					winner = board[r][0];
				}
				// technically r means columns but it's using the same for loop so lol
				if (board[1][r] == board[0][r] && board[1][r] == board[2][r]) {
					winner = board[0][r];
				}
			}
			
			for(int c = 0; c < 3; c++) {
				if(board[r][c] == 0) full = false;
				
				g.setColor(new Color(0, 0, 0));
				g.fillRect(cX, cY, 100, 100); // outline
				g.setColor(new Color(235, 234, 228));
				g.fillRect(cX+5, cY+5, 90, 90); // inner
				g.setColor(new Color(0, 0, 0));
				
				cStr(g, tts(board[r][c]), cX, cY, 100, 100, 60);
				
				cX += 100;
			}
			cY += 100;
			cX -= 300;
		}
		
		if ((board[1][1] == board[0][0] && board[1][1] == board[2][2]) || (board[1][1] == board[0][2] && board[1][1] == board[2][0]))
			winner = board[1][1];
		
		if (winner == 1 || winner == 2) {
			cStr(g, tts(winner) + " won!", 0, -200, width, height, 40);
		} else if (full) {
			cStr(g, "Draw", 0, -200, width, height, 40);
			winner = 3;
		}
		
		if (winner != 0) {
			g.fillRect((width - 200) / 2, (height - 75) / 2 + 260, 200, 75);
			g.setColor(Color.white);
			cStr(g, "Play Again", (width - 200) / 2, (height - 75) / 2 + 260, 200, 75, 35);
		}
		
		g.setColor(new Color(0, 0, 0));
		g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, 40));
		g.drawString("Current turn: " + tts(Display.ct), 20, 50);
	}
	
	// current turn (integer) to string
	private String tts(int i) {
		if (i == 1)
			return "X";
		else if (i == 2)
			return "O";
		else
			return "";
	}
	
	// center string
	private void cStr(Graphics g, String text, int rectX, int rectY, int rectW, int rectH, int fontSize) {
	    FontMetrics metrics = g.getFontMetrics(new Font(g.getFont().getFontName(), Font.PLAIN, fontSize));
	    // Determine the X coordinate for the text
	    int x = rectX + (rectW - metrics.stringWidth(text)) / 2;
	    // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
	    int y = rectY + ((rectH - metrics.getHeight()) / 2) + metrics.getAscent();
	    g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, fontSize));
	    g.drawString(text, x, y);
	}
}
