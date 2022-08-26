package com.pelengo.Engine.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Image extends GUIObject {
	BufferedImage image;
	Runnable onClick;
	public Image(String image, int x, int y, int width, int height) throws IOException {
		super(width, height, x, y);
		this.image=ImageIO.read(getClass().getClassLoader().getResource(image));
		redraw();
	}
	public Image(String image, int x, int y, int width, int height, Runnable onClick) throws IOException {
		super(width, height, x, y);
		this.onClick=onClick;
		this.image=ImageIO.read(getClass().getClassLoader().getResource(image));
		redraw();
	}
	@Override
	public void render(Graphics g) {
		g.drawImage(image, 0, 0, width, height, null);
	}

	@Override
	public void tick() {
	}
	@Override
	public void componentMouseMove(int[] pos, boolean inBounds) {}
	
	@Override
	public void componentMouseClick(int[] pos, int button, boolean inBounds) {
		if(inBounds) {
			if(onClick!=null) {
				onClick.run();
			}
		}
	}
	

}
