package com.pelengo.Engine.gui;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.pelengo.Engine.gameObjects.ObjectHandler;
import com.pelengo.Engine.input.KeyInput;

public abstract class GUI{
	protected BufferedImage logoImage;
	protected KeyInput keyInput;
	protected GUIHandler guiHandler;
	protected String id;
	protected ObjectHandler handler;
	protected int height;
	protected int width;
	protected ArrayList<GUIObject> objects = new ArrayList<GUIObject>();
	
	public GUI(KeyInput keyInput, GUIHandler guiHandler, String id, ObjectHandler handler, int width, int height) {
		this.keyInput=keyInput;
		this.id=id;
		this.handler=handler;
		this.guiHandler=guiHandler;
		this.width=width;
		this.height=height;
		
		try {
			logoImage=ImageIO.read(getClass().getClassLoader().getResource("menus/logo.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public abstract void mouseMoved(MouseEvent e);
	public abstract void mouseClicked(MouseEvent e);
	public abstract void keyPressed(KeyEvent arg0);
	public abstract void keyReleased(KeyEvent arg0);
	public abstract void keyTyped(KeyEvent arg0);
	public abstract void render(Graphics g);
	public abstract void tick();
	protected void swapMenu(GUI menu) {
		guiHandler.addGUI(menu);
		guiHandler.removeGUI(id);
	}
	protected void drawGUIS(Graphics g) {
		for (GUIObject object : objects) {
			object.Render(g);
		}
	}

}
