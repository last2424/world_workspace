package com.last2424.ogl.engine.tiled;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.joml.Vector2f;
import org.joml.Vector2i;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.last2424.ogl.rendering.SpriteBatch;
import com.last2424.ogl.rendering.Texture;
import com.last2424.ogl.rendering.TextureRegion;

public class Map {
	private Tileset tileset;
	private ArrayList<Layer> layers;
	
	private int mapWidth, mapHeight;
	
	public Map() {
		layers = new ArrayList<Layer>();
	}
	
	public static Map Load(String name) {
		try {
			
			Map myMap = new Map();
			
			JSONParser parser = new JSONParser();
			JSONObject json = (JSONObject) parser.parse(new FileReader("src/main/java/com/last2424/assets/maps/"+name+".json"));
			myMap.mapWidth = Integer.parseInt(json.get("width").toString());
			myMap.mapHeight = Integer.parseInt(json.get("height").toString());
			myMap.makeTileset(json.get("tilesets"));
			myMap.makeLayers(json.get("layers"));
			return myMap;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
		
	}

	private void makeTileset(Object object) {
		try {
			JSONParser parser = new JSONParser();
			JSONArray json = (JSONArray) parser.parse(object.toString());
			int countTiles = 0;
			for (int i = 0; i < json.size(); i++) {
				JSONObject obj = (JSONObject) parser.parse(json.get(i).toString());
				countTiles += Integer.parseInt(obj.get("tilecount").toString());
			}
			
			this.tileset = new Tileset(countTiles);
			
			for (int i = 0; i < json.size(); i++) {
				JSONObject obj = (JSONObject) parser.parse(json.get(i).toString());
				tileset.setTileSize(Integer.parseInt(obj.get("tilewidth").toString()), Integer.parseInt(obj.get("tileheight").toString()));
				tileset.MakeTiles(new Texture("maps/"+obj.get("image").toString()), Integer.parseInt(obj.get("firstgid").toString()));
			}
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		}
	}
	
	private void makeLayers(Object object) {
		try {
			JSONParser parser = new JSONParser();
			JSONArray json = (JSONArray) parser.parse(object.toString());
			for (int i = 0; i < json.size(); i++) {
				JSONObject obj = (JSONObject) parser.parse(json.get(i).toString());
				if (obj.get("type").toString().equals("tilelayer")) {
					JSONArray arr = (JSONArray) parser.parse(obj.get("data").toString());
					JSONArray prop = (JSONArray) parser.parse(obj.get("properties").toString());
					layers.add(new TileLayer());
					layers.get(layers.size()-1).initializateData(arr);
					((TileLayer)layers.get(layers.size()-1)).setLayer(prop, parser);
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}		
	}
	
	public void draw(SpriteBatch batch) {
		for (int i = 0; i < layers.size(); i++) {
			int x = 0, y = 0;
			int[] data = layers.get(i).getData();
			for (int j = 0; j < data.length; j++) {
				if (data[j] != -1) {
					TextureRegion currentTextureRegion = tileset.tiles[data[j]];
					Vector2f tilesize = currentTextureRegion.GetRegionSize();
					batch.draw(currentTextureRegion, x*(int)tilesize.x, y*(int)tilesize.y, (int)tilesize.x, (int)tilesize.y, 255, 255, 255, 255, layers.get(i).getGameLayer(), 1, 1);
				}
				x++;
				if (x >= this.mapWidth) {
					x = 0;
					y++;
					if (y >= this.mapHeight) {
						break;
					}
				}
			}
		}
	}
}
