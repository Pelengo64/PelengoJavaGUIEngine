package com.pelengo.Engine.gameObjects;

import java.awt.Graphics;

import com.pelengo.Engine.input.KeyInput;

public abstract class GameObject {
	protected float x, y;
	protected int snapX,snapY;
	protected TagID tag;
	protected int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	protected String spawnID;
	protected int velX, velY;
	protected KeyInput keyInput;
	
	
	public GameObject(float x, float y, TagID tag, KeyInput keyInput) {
		this.x = x;
		this.y = y;
		this.tag = tag;
		this.keyInput=keyInput;
	}
	public abstract void tick();
	public abstract void render(Graphics g);
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public TagID getTag() {
		return tag;
	}
	public void setTag(TagID tag) {
		this.tag = tag;
	}
	public String getSpawnID() {
		return spawnID;
	}
	public void setSpawnID(String spawnID) {
		this.spawnID = spawnID;
	}
	public int getVelX() {
		return velX;
	}
	public void setVelX(int velX) {
		this.velX = velX;
	}
	public int getVelY() {
		return velY;
	}
	public void setVelY(int velY) {
		this.velY = velY;
	}
	public int getSnapX() {
		return snapX;
	}
	public int getSnapY() {
		return snapY;
	}
}
