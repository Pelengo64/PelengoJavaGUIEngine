package com.pelengo.Engine.gameObjects;

import java.awt.Graphics;
import java.util.ArrayList;

public class ObjectHandler {
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	int curID=0;
	public void tick() {
		for (GameObject object : objects) {
			object.tick();
		}
	}
	public void render(Graphics g) {
		for (GameObject object : objects) {
			object.render(g);
		}
	}
	public GameObject addObject(GameObject object) {
		curID++;
		object.id=curID;
		objects.add(object);
		return object;
	}
	public void removeObject(GameObject object) {
		for (int i = 0; i<objects.size(); i++) {
			if(object.id==objects.get(i).id) {
				objects.remove(i);
			}
		}
	}
}
