package com.pelengo.Engine.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

public class Label extends GUIObject {
	String text;
	Font font;
	int size;
	Color col;
	public Label(String text, Color col, int size, int x, int y) {
		super(1, 1, x, y);
		
		this.col=col;
		this.text=text;
		this.font=new Font("Courier", 1, size);
		
		FontRenderContext frc = new FontRenderContext(new AffineTransform(), true, true);

		width = (int)(font.getStringBounds(text, frc).getWidth());
		height = (int)(font.getStringBounds(text, frc).getHeight());
		width=500;
		height=500;
		
		this.size=size;
		redraw();
	}


	@Override
	public void render(Graphics g) {
		g.setFont(font);
		g.setColor(col);
		g.drawString(text, 0, 0);

	}

	@Override
	public void tick() {

	}

	@Override
	public void componentMouseMove(int[] pos, boolean inBounds) {
		

	}

	@Override
	public void componentMouseClick(int[] pos, int button, boolean inBounds) {
		
	}

}
