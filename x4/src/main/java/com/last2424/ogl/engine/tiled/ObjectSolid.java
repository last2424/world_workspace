package com.last2424.ogl.engine.tiled;

import org.joml.Vector2f;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.last2424.utils.Rectangle;

public class ObjectSolid {
	public float x;
	public float y;
	public float width;
	public float height;
	float toFloat(Object obj) {
		if(obj instanceof Long) { 
			return (float)(long) obj;
		}else return (float)(double)obj;
	}
	public void Init(JSONObject data) {
		width =  toFloat(data.get("width"));
		height =  toFloat(data.get("height"));
		x =  toFloat(data.get("x"));
		y =  toFloat(data.get("y"));
	}
	public Rectangle getRect() {
		return new Rectangle(new Vector2f(x,y), new Vector2f(width,height));
	}
}
