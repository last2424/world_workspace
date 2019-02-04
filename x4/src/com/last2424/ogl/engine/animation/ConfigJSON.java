package last2424.ogl.engine.animation;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import last2424.ogl.rendering.Texture;

public class ConfigJSON implements Config {
	JSONParser parser = new JSONParser();
	public JSONObject json;
	
	public ConfigJSON(String path) {
		
		try {
			json = (JSONObject) parser.parse(new FileReader("assets/"+path));
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
