package com.pelengo.Engine.gui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public abstract class GUIObject {
	protected int width; 
	protected int height; 
	protected int x;
	protected int y;
	protected boolean constantUpdate=false;
	public BufferedImage cachedImage;
	public abstract void render(Graphics g);
	public abstract void tick();
	public abstract void componentMouseMove(int[] pos, boolean inBounds);
	public abstract void componentMouseClick(int[] pos, int button, boolean inBounds);
	public void MouseMove(MouseEvent e) {
		int[] pos = new int[2];
		pos[0]=e.getPoint().x;
		pos[1]=e.getPoint().y;
		if(pos[0]>=x && pos[0] <= x+width) {
			if(pos[1]>=y && pos[1] <= y+height) {
				componentMouseMove(new int[]{pos[0]-x,pos[1]-y}, true);
				return;
			}
		}
		componentMouseMove(new int[]{pos[0]-x,pos[1]-y}, false);
		
	}
	public void MouseClick(MouseEvent e) {
		int[] pos = new int[2];
		pos[0]=e.getPoint().x;
		pos[1]=e.getPoint().y;
		if(pos[0]>=x && pos[0] <= x+width) {
			if(pos[1]>=y && pos[1] <= y+height) {
				componentMouseClick(new int[]{pos[0]-x,pos[1]-y}, e.getButton(), true);
				return;
			}
		}
		componentMouseClick(new int[]{pos[0]-x,pos[1]-y}, e.getButton(), false);
	}
	public GUIObject(int width, int height, int x, int y) {
		this.width=width;
		this.height=height;
		this.x=x;
		this.y=y;
		cachedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	}
	public GUIObject(int width, int height, int x, int y, boolean constantUpdate) {
		this.width=width;
		this.height=height;
		this.x=x;
		this.y=y;
		this.constantUpdate=constantUpdate;
		//cachedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	}
	
	public void Render(Graphics g) {

		if(doRedraw || constantUpdate) {
			cachedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			Graphics tempGraphics = cachedImage.getGraphics();
			render(tempGraphics);
			doRedraw=false;
			g.drawImage(cachedImage, x, y, width, height, null);
			
		}else {
			g.drawImage(cachedImage, x, y, width, height, null);
		}
	}
	boolean doRedraw=true;
	public void redraw() {
		doRedraw=true;
	}
}
