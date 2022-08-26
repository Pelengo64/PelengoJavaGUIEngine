package com.pelengo.Engine.gui;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GUIHandler implements KeyListener{
	private ArrayList<GUI> guis = new ArrayList<GUI>();
	MouseEvent tempEvent;

	public void addGUI(GUI gui) {
		guis.add(gui);
		if(tempEvent!=null) {
			mouseMoved(tempEvent);
		}
	}
	public void removeGUI(String id) {
		for (int i = 0; i < guis.size(); i++) {
			if(guis.get(i).id.equals(id)) {
				guis.remove(i);
			}
		}
		if(tempEvent!=null) {
			mouseMoved(tempEvent);
		}
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		for (GUI gui : guis) {
			gui.keyPressed(arg0);
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		for (GUI gui : guis) {
			gui.keyReleased(arg0);
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		for (GUI gui : guis) {
			gui.keyTyped(arg0);
		}
	}
	public void render(Graphics g) {
		try {
			for (GUI gui : guis) {
				gui.render(g);
			}
		}catch (Exception e){}

	}
	public void tick() {
		for (GUI gui : guis) {
			gui.tick();
		}
	}
	
	public void mouseMoved(MouseEvent e) {
		try {
		GUI gui = guis.get(guis.size()-1);
		for (GUIObject object : gui.objects) {
			object.MouseMove(e);
		}
		for (GUI tempGui : guis) {
			tempGui.mouseMoved(e);
		}
		tempEvent=e;
		}catch(Exception e2) {}
	}
	public void mouseClicked(MouseEvent e) {
		try {
			GUI gui = guis.get(guis.size()-1);
			for (GUIObject object : gui.objects) {
				object.MouseClick(e);
			}
			for (GUI tempGui : guis) {
				tempGui.mouseClicked(e);
			}
			
			
		}catch (Exception e2) {}

	}
}
