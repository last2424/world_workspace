package com.last2424.ogl.engine.tiled;

import java.util.ArrayList;

import org.json.simple.JSONArray;

import com.last2424.ogl.rendering.SpriteBatch;

public interface Layer {
	
	public void initializateData(JSONArray data);

	public ArrayList<Integer> getData();
	
	public int getGameLayer();

}
