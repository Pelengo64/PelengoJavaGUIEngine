package com.pelengo.Engine;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import com.pelengo.AirPollution.guis.Menu;
import com.pelengo.Engine.gameObjects.ObjectHandler;
import com.pelengo.Engine.gui.GUIHandler;
import com.pelengo.Engine.input.KeyInput;
import com.pelengo.Engine.window.Window;

public abstract class PelengoEngine extends Canvas implements Runnable {

	private static final long serialVersionUID = 6540806909358271948L;
	private boolean running = false;
	int FPS=0;
	Window win;
	GUIHandler guiHandler = new GUIHandler();
	private ObjectHandler handler=new ObjectHandler();
	private KeyInput keyInput=new KeyInput();
	private Thread thread;
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double frameCap = 60.0;
		double framens = 1000000000 / frameCap;
		double framedelta = 0;
		long lastTimeFrame = System.nanoTime();
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames=0;
		while(running) {
			loop();
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			if(running) {
				long _now = System.nanoTime();
				framedelta += (_now - lastTimeFrame) / framens;
				lastTimeFrame=now;
				while(framedelta >=1) {
					render();
					framedelta--;
				}
			}
			
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				FPS=frames;
				frames = 0;
			}
		}
		stop();
	}
	public PelengoEngine(String name, int width, int height) {
		win=new Window(width, height, name, this, keyInput, guiHandler);
		guiHandler.addGUI(new Menu(keyInput, guiHandler, handler, width, height));
	}
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();

		running=true;
	}
	void loop() {
		Loop();
	}
	void render() {
		
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);

		g.fillRect(0, 0, WIDTH, HEIGHT);
		handler.render(g);
		Render(g);
		guiHandler.render(g);
		g.dispose();
		bs.show();
		
		
	}
	void tick() {
		EarlyTick();
		handler.tick();
		keyInput.tick();
		guiHandler.tick();
		if(win.frame.isActive()) {
			win.frame.requestFocus();
		}
		LateTick();
	}
	public synchronized void stop() {
		try {
			Stop();
			thread.join();
			running=false;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public abstract void LateTick();
	public abstract void EarlyTick();
	public abstract void Render(Graphics g);
	public abstract void Loop();
	public abstract void Start();
	public abstract void Stop();
}