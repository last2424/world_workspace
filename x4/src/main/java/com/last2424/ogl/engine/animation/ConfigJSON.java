package com.last2424.ogl.engine.animation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.last2424.ogl.Window;
import com.last2424.ogl.rendering.Texture;

public class ConfigJSON implements Config {
	JSONParser parser = new JSONParser();
	public JSONObject json;
	public Texture texture;
	
	public ConfigJSON(String path, Texture texture) {
		this.texture = texture;
		try {
			InputStream i = Window.class.getClass().getResourceAsStream("/com/last2424/assets/json/"+path);
			json = (JSONObject) parser.parse(new InputStreamReader(i));
		} 
		catch (IOException | ParseException e) {
			e.printStackTrace();
		}
	}
	
	public ConfigJSON(String path, Texture texture, String animation) {
		this.texture = texture;
		try {
			InputStream i = Window.class.getClass().getResourceAsStream("/com/last2424/assets/json/"+path);
			JSONObject jsonTemp = (JSONObject) parser.parse(new InputStreamReader(i));
			json = (JSONObject) parser.parse(jsonTemp.get(animation).toString());
		} 
		catch (IOException | ParseException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Frame[] GenerateFrames() {
		Frame[] frames = null;
		try {
			JSONObject jsonFrames = (JSONObject) parser.parse(json.get("frames").toString());
			frames = new Frame[jsonFrames.size()];
			for(int i = 0; i < frames.length; i++) {
				JSONArray frame = (JSONArray) jsonFrames.get("frame"+(i+1));
				frames[i] = new Frame(new Float(frame.get(0).toString()), new Float(frame.get(1).toString()), new Float(frame.get(2).toString()), new Float(frame.get(3).toString()));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return frames;
	}

}
