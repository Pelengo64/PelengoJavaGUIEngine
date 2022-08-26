package com.pelengo.Engine.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import com.pelengo.Engine.window.Window;


public class KeyInput implements KeyListener {
	private HashMap<Integer, Boolean> keys = new HashMap<Integer, Boolean>();
	public Window win;
	@Override
	public void keyPressed(KeyEvent e) {
		keys.put(e.getKeyCode(), true);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys.put(e.getKeyCode(), false);
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}

	public boolean getKeyDown(int key) {
		try {
			return keys.get(key);
		} catch (Exception e) {
			keys.put(key, false);
			return false;
		}
	}
	public void tick() {
		if(win!=null) {
			if(!win.frame.isActive()) {
				keys.clear();
			}
		}
	}
}
