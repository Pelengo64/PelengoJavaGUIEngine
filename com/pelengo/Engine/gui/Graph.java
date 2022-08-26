package com.pelengo.Engine.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Graph extends GUIObject {
	float[] values;

	public Graph(int x, int y, int width, int height, float[] values) {
		super(width, height, x, y);
		this.values = values;
		redraw();
	}

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(255, 255, 255, 100));
		g.fillRect(0, 0, width - 1, height - 1);
		((Graphics2D) g).setStroke(new BasicStroke(3));

		g.setColor(Color.red);
		float maxVal = 1;
		for (int i = 0; i < values.length; i++) {
			if(values[i]>maxVal) {
				maxVal=values[i];
			}
		}
		float lineWidth = width/(values.length-1);
		float lineHeight = height/(maxVal);
		for (int i = 0; i < values.length - 1; i++) {
			float y1 = values[i];
			float y2 = values[i + 1];
			float x1 = (i == 0) ? 0 : lineWidth*(float)i;
			float x2 = lineWidth*((float)i + 1);
			
			System.out.println(y2*lineHeight);
			
			

			g.drawLine(Math.round(x1), Math.round(height-(y1*lineHeight)), Math.round(x2), Math.round(height-(y2*lineHeight)));
		}
		g.setColor(new Color(0, 0, 0));
		((Graphics2D) g).setStroke(new BasicStroke(15));
		g.drawRect(0, 0, width - 1, height - 1);

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
