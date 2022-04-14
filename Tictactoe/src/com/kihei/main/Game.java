package com.kihei.main;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.*;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = -4995321819197236202L;

	public static final int WIDTH = 800, HEIGHT = 800;
	
	private Thread thread;
	private boolean running = false;
	
	private Display display;
	private final Window w;
	
	public Game() {
		this.addMouseListener(new MouseInput());
		
		display = new Display();
		
		w = new Window(WIDTH, HEIGHT, "Tictactoe", this);
		
		
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public static int framesDisplay = 0;
	
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.00;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			if (running)
				render();
			frames++;
			
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				framesDisplay = frames;
				frames = 0;
			}
		}
	}
	
	private void tick() {
		
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(new Color(255, 252, 236));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		int width = w.frame.getContentPane().getWidth();
		int height = w.frame.getContentPane().getHeight();
		
		display.render(g, width, height);
		
		g.dispose();
		bs.show();
	}
	
	public static void main(String args[]) {
		new Game();
	}
	
}