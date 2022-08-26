package com.pelengo.Engine.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class Button extends GUIObject {
	Color normalCol;
	Color highlightedCol;
	Color textCol;
	String text;
	int hoverPos;
	Runnable onCLick;
	boolean hovering=false;
	public Button(String text, Color textCol, Color normalCol, Color highlightedCol, int width, int height, int x, int y, Runnable onClick) {
		super(width, height, x, y);
		this.normalCol=normalCol;
		this.textCol=textCol;
		this.highlightedCol=highlightedCol;
		this.onCLick=onClick;
		this.text=text;
		this.hoverPos=0;
	}
	@Override
	public void render(Graphics g) {

		g.setColor(normalCol);
		g.fillRect(0,0, width, height);
		g.setColor(highlightedCol);
		g.fillRect(0, 0, hoverPos, height);
		g.setColor(textCol);
        Font f = new Font("Arial", Font.PLAIN, height);
        g.setFont(f);
        FontMetrics fm = g.getFontMetrics();
        double shrink = ((double)height / (double)fm.getHeight());
        double newSize = (double)height * shrink;
        double newAsc  = (double)fm.getAscent() * shrink;
        int yOffset = (int)newAsc - fm.getLeading();
        f = f.deriveFont((float)newSize);
        g.setFont(f);

        g.drawString(text, 0, 0 + yOffset);
		//g.drawString(text, x, y+height);
	}

	@Override
	public void tick() {
		if(hovering) {
			if(hoverPos<width) {
				redraw();
				hoverPos+=40;
			}
			if(hoverPos>width) {
				redraw();
				hoverPos=width;
			}
		}else {
			if(hoverPos>0) {
				hoverPos-=40;
				redraw();
			}
			if(hoverPos<0) {
				hoverPos=0;
				redraw();
			}
			
		}
	}
	@Override
	public void componentMouseMove(int[] pos, boolean inBounds) {
		if(inBounds) {
			hovering=true;
		}else {
			hovering=false;
		}
		
	}
	
	@Override
	public void componentMouseClick(int[] pos, int button, boolean inBounds) {
		if(inBounds)
			onCLick.run();
	}
	

}
