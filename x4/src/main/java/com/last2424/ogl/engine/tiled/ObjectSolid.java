package com.last2424.ogl.engine.tiled;

import org.joml.Vector2f;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.last2424.ogl.engine.objects.GameObject;
import com.last2424.utils.Rectangle;

public class ObjectSolid {
	public float x;
	public float y;
	public float width;
	public float height;
	public boolean solid;
	public TypeAction typeAction;
	public IAction action;	
	public Long id;
	public GameObject anchorObject;
	float toFloat(Object obj) {
		if(obj instanceof Long) { 
			return (float)(long) obj;
		}else return (float)(double)obj;
	}
	public void AddEvent(TypeAction typeA,IAction action) {
		this.typeAction = typeA;
		this.action = action;
	}
	public void Use(float delta) {
		if(action!=null && typeAction == TypeAction.USE) {
			action.Activate(this, delta);
		}
	}
	public void Colide(float delta) {
		if(action!=null && typeAction == TypeAction.COLIDER) {
			action.Activate(this, delta);
		}
	}
	public void Update(float delta) {
		if(action!=null && typeAction == TypeAction.AUTORUN) {
			action.Activate(this, delta);
		}
		if(this.anchorObject!=null) {
			x = anchorObject.getHalf().x-width/2;
			y = anchorObject.getHalf().y-height/2;
		}
	}
	public void SetAnchorGameObject(GameObject anchorObject) {
		this.anchorObject = anchorObject;
	}
	public void Init(JSONObject data) {
		id =  (Long)data.get("id");
		width =  toFloat(data.get("width"));
		height =  toFloat(data.get("height"));
		x =  toFloat(data.get("x"));
		y =  toFloat(data.get("y"));
		if( data.get("solid") != null) {
			solid = (boolean)(data.get("solid"));
		}
		else 
			solid = true;
	}
	public Rectangle getRect() {
		return new Rectangle(new Vector2f(x,y), new Vector2f(width,height));
	}
}
