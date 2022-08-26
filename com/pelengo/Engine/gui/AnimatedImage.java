package com.pelengo.Engine.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

public class AnimatedImage extends GUIObject {
	Animation animation;
	Runnable onClick;
	public AnimatedImage(String anim, int x, int y, int width, int height) throws IOException {
		super(width, height, x, y);
		
		File file = new File(getClass().getClassLoader().getResource(anim).getFile().replaceAll("%20", " "));
		
		Scanner scanner = new Scanner(file);
		this.animation = new Animation();
		this.animation.fps=Integer.parseInt(scanner.next());
		String path = scanner.next();
		this.animation.image = ImageIO.read (new File(getClass().getClassLoader().getResource(path).getFile().replaceAll("%20", " ")));
		this.animation.loop= (Integer.parseInt(scanner.next())==1) ? true : false;
		this.animation.size=this.animation.image.getWidth();
		this.animation.frames = this.animation.image.getHeight()/this.animation.size;
		this.animation.images = new BufferedImage[this.animation.frames];
		
		for(int i = 0; i < this.animation.frames; i++) {
			this.animation.images[i]=animation.image.getSubimage(0, i*animation.size, animation.size, animation.size);
		}
		scanner.close();
		
		Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(new Runnable() {
			
			@Override
			public void run() {
				if(index<animation.frames-1)
					index++;
				else if(animation.loop)
					index=0;
				redraw();
//				System.out.println("Test");


				
			}
		}, 0, 1000/animation.fps, TimeUnit.MILLISECONDS);
		
	}
	public AnimatedImage(String anim, int x, int y, int width, int height, Runnable onClick) throws IOException {
		super(width, height, x, y);
		this.onClick=onClick;
		File file = new File(getClass().getClassLoader().getResource(anim).getFile().replaceAll("%20", " "));
		
		Scanner scanner = new Scanner(file);
		this.animation = new Animation();
		this.animation.fps=Integer.parseInt(scanner.next());
		String path = scanner.next();
		this.animation.image = ImageIO.read (new File(getClass().getClassLoader().getResource(path).getFile().replaceAll("%20", " ")));
		this.animation.loop= (Integer.parseInt(scanner.next())==1) ? true : false;
		this.animation.size=this.animation.image.getWidth();
		this.animation.frames = this.animation.image.getHeight()/this.animation.size;
		this.animation.images = new BufferedImage[this.animation.frames];
		
		for(int i = 0; i < this.animation.frames; i++) {
			this.animation.images[i]=animation.image.getSubimage(0, i*animation.size, animation.size, animation.size);
		}
		scanner.close();
		
		Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(new Runnable() {
			
			@Override
			public void run() {
				if(index<animation.frames-1)
					index++;
				else if(animation.loop)
					index=0;
				redraw();
				
			}
		}, 0, 1000/animation.fps, TimeUnit.MILLISECONDS);
		
	}
	int index;
	
	@Override
	public void render(Graphics g) {
		g.drawImage(animation.images[index],0,0,width,height,null);
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

class Animation{
	public int fps;
	public BufferedImage image;
	public BufferedImage[] images;
	public boolean loop;
	public int size;
	public int frames;
}
