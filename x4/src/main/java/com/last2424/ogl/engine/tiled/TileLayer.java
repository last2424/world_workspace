package com.last2424.ogl.engine.tiled;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.last2424.ogl.rendering.SpriteBatch;

public class TileLayer implements Layer<Integer> {
	Integer[] data;
	
	int gameLayer = 0;
	
	public TileLayer() {
	}

	@Override
	public void initializateData(JSONArray data) {
		this.data = new Integer[data.size()];
		for (int i = 0; i < data.size(); i++) {
			this.data[i] = (Integer.parseInt(data.get(i).toString())-1);
		}
	}
	
	public void setLayer(JSONArray properties, JSONParser parser) throws ParseException {
		for (int i = 0; i < properties.size(); i++) {
			JSONObject obj = (JSONObject) parser.parse(properties.get(i).toString());
			if (obj.get("name").toString().equals("layer")) {
				gameLayer = Integer.parseInt(obj.get("value").toString());
			}
		}
	}

	@Override
	public Integer[] getData() {
		// TODO Auto-generated method stub
		return data;
	}

	@Override
	public int getGameLayer() {
		// TODO Auto-generated method stub
		return 0;
	}

}
