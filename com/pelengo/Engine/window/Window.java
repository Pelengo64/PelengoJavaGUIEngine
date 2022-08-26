package com.pelengo.Engine.window;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.GraphicsEnvironment;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

import com.pelengo.Engine.PelengoEngine;
import com.pelengo.Engine.gui.GUIHandler;
import com.pelengo.Engine.input.KeyInput;

public class Window extends Canvas{

	private static final long serialVersionUID = 7533424942916683483L;
	public JFrame frame;
	//private static final long serialVersionUID = -240840600533728354L;
	
	public Window(int width, int height, String title, PelengoEngine game, KeyInput keyInput, GUIHandler gui) {
		frame = new JFrame(title);
		//frame.setFocusable(true);
		
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		frame.setSize(new Dimension(width, height));
		frame.setUndecorated(true);
		frame.setBounds(0, 0, width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		if(!System.getProperty("os.name").equals("Windows 10"))
		for ( java.awt.Window w : java.awt.Window.getWindows() ) {
		    GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow( w );
		}
		
		frame.setLocationRelativeTo(null);
		game.setFocusable(true);
		frame.add(game);
		frame.setFocusable(true);
		frame.addKeyListener(keyInput);
		frame.addKeyListener(gui);

		game.addMouseMotionListener(new MouseAdapter() {
			
			@Override
			public void mouseMoved(MouseEvent arg0) {
				gui.mouseMoved(arg0);
				
				
			}

			@Override
			public void mouseDragged(MouseEvent arg0) {
				
				
			}
		});
		game.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				gui.mouseClicked(arg0);
				
			}
		});
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH | DisplayMode.BIT_DEPTH_MULTI);
		
		//frame.addKeyListener(keyInput);
		frame.setVisible(true);
		
		
		
		game.start();
	}
}
