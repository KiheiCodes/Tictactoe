package com.kihei.main;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if (Display.winner == 0) {
			int cX = ((784 - 100) / 2) - 100;
			int cY = ((761 - 100) / 2) - 100;
			
			for(int r = 0; r < 3; r++) {
				for(int c = 0; c < 3; c++) {
					if (Display.board[r][c] == 0 && mouseOver(mx, my, cX, cY, 100, 100)) {
						Display.board[r][c] = Display.ct;
						swapTurn();
						break;
					}
					
					cX += 100;
				}
				cY += 100;
				cX -= 300;
			}
		} else {
			if (mouseOver(mx, my, (784 - 200) / 2, (761 - 75) / 2 + 260, 200, 75)) {
				Display.board = new int[3][3];
				Display.winner = 0;
				Display.ct = 1;
			}
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if (mx > x && mx < x + width && my > y && my < y + height)
			return true;
		else
			return false;
	}
	
	private void swapTurn() {
		if (Display.ct == 1)
			Display.ct = 2;
		else
			Display.ct = 1;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		
	}
	
}
