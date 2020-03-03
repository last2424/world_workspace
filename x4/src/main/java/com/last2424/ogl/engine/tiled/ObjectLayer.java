package com.last2424.ogl.engine.tiled;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.last2424.ogl.rendering.SpriteBatch;

public class ObjectLayer implements Layer<ObjectSolid> {
	ObjectSolid[] solids;
	@Override
	public void initializateData(JSONArray data) {
		solids = new ObjectSolid[data.size()];
		for(int i=0;i<data.size();i++) {
			solids[i] = new ObjectSolid();
			solids[i].Init((JSONObject)data.get(i));
		}
	}

	@Override
	public ObjectSolid[] getData() {
		// TODO Auto-generated method stub
		return solids;
	}

	@Override
	public int getGameLayer() {
		return 0;
	}
}
