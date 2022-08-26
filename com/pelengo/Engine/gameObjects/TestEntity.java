package com.pelengo.Engine.gameObjects;

import java.awt.Color;
import java.awt.Graphics;

import com.pelengo.Engine.input.KeyBindings;
import com.pelengo.Engine.input.KeyInput;

public class TestEntity extends GameObject {

	public TestEntity(float x, float y, TagID tag, KeyInput keyInput) {
		super(x, y, tag, keyInput);
	}

	@Override
	public void tick() {
		snapX=(int)x;
		snapY=(int)y;
		if(keyInput.getKeyDown(KeyBindings.UP)) {
			y-=1;
		}
		if(keyInput.getKeyDown(KeyBindings.DOWN)) {
			y+=1;
		}
		if(keyInput.getKeyDown(KeyBindings.LEFT)) {
			x-=1;
		}
		if(keyInput.getKeyDown(KeyBindings.RIGHT)) {
			x+=1;
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(snapX, snapY, 64, 64);

	}

}
