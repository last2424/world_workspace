package com.last2424.ogl.engine.tiled;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ObjectSolid {
	public float x;
	public float y;
	public float width;
	public float height;
	public void Init(JSONObject data) {
		width =  (float)(double)data.get("width");
		height =  (float)(double)data.get("height");
		x =  (float)(double)data.get("x");
		y =  (float)(double)data.get("y");
	}
}
