package com.last2424.ogl.engine.tiled;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.last2424.ogl.rendering.SpriteBatch;

public class TileLayer implements Layer {
	ArrayList<Integer> data;
	
	int gameLayer = 0;
	
	public TileLayer() {
		data = new ArrayList<Integer>();
	}

	@Override
	public void initializateData(JSONArray data) {
		for (int i = 0; i < data.size(); i++) {
			this.data.add(Integer.parseInt(data.get(i).toString())-1);
		}
	}
	
	public void setLayer(JSONArray properties, JSONParser parser) throws ParseException {
		for (int i = 0; i < properties.size(); i++) {
			JSONObject obj = (JSONObject) parser.parse(properties.get(i).toString());
			if (obj.get("name").toString() == "layer") {
				gameLayer = Integer.parseInt(obj.get("value").toString());
			}
		}
	}

	@Override
	public ArrayList<Integer> getData() {
		return data;
	}

	@Override
	public int getGameLayer() {
		return this.gameLayer;
	}
}
