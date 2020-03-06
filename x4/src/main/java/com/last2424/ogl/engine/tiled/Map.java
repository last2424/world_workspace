package com.last2424.ogl.engine.tiled;

import static org.lwjgl.opengl.GL11.glFlush;

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
import com.last2424.utils.Rectangle;

public class Map {
	private Tileset tileset;
	private ArrayList<Layer> layers;

	private ArrayList<Layer> layersObj;
	private int mapWidth, mapHeight;
	
	public Map() {
		layers = new ArrayList<Layer>();
		layersObj = new ArrayList<Layer>();
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
	public boolean IsSolid(Vector2f pos,Vector2f size) {
		for(int i=0;i<layersObj.size();i++) {
			ObjectSolid[] solids = (ObjectSolid[])layersObj.get(i).getData();
			for(int j=0;j<solids.length;j++) {
				if(solids[j].getRect().isColid(new Rectangle(pos,size)))
					return true;
			}
		}
		return false;
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
				else if (obj.get("type").toString().equals("objectgroup")) {
					JSONArray arr = (JSONArray) parser.parse(obj.get("objects").toString());
					layersObj.add(new ObjectLayer());
					layersObj.get(layersObj.size()-1).initializateData(arr);
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}		
	}
	public Vector2f getSize() {

		Vector2f tilesize = tileset.tiles[0].GetRegionSize();
		return new Vector2f(tilesize.x*mapWidth,tilesize.y*mapHeight);
	}
	public void drawLayer(SpriteBatch batch,Vector2f start,Vector2f end,int layer) {
		Vector2f tilesize = tileset.tiles[0].GetRegionSize();
			Integer[] data = ((TileLayer) (layers.get(layer))).getData();
			//posStart = new Vector2f(0,0);
			//posEnd =  new Vector2f(mapWidth,mapHeight);
			if(start.y<=0) start.y = 0;
			if(start.x<=0) start.x = 0;
			for (int y = (int)start.y;y <(int)end.y;y++) {
				
				if(y>=this.mapHeight) break;
				for(int x = (int)start.x;x<(int)end.x;x++){
					
					if(x>=this.mapWidth) break;
					int idData = (y * this.mapWidth) + x;
					if (data[idData] != -1) {
						TextureRegion currentTextureRegion = tileset.tiles[data[idData]];
						batch.draw(currentTextureRegion, x*(int)tilesize.x, y*(int)tilesize.y, (int)tilesize.x, (int)tilesize.y, 255, 255, 255, 255, layers.get(layer).getGameLayer(), 1, 1);
				}
			}
		}
		glFlush();
	}
	public void drawBackgrond(SpriteBatch batch,Vector2f pos,Vector2f size) {
		Vector2f tilesize = tileset.tiles[0].GetRegionSize();
		for (int i = 0; i < 2; i++) {
			Vector2f posStart = new Vector2f(pos.x/tilesize.x, pos.y/tilesize.y);
			Vector2f mapTile = new Vector2f(size.x/tilesize.x,size.y/tilesize.y);
			Vector2f posEnd =  new Vector2f(posStart.x+mapTile.x+2,posStart.y+mapTile.y+2);
			drawLayer(batch,posStart,posEnd,i);
		}
	}
	public void draw(SpriteBatch batch,Vector2f pos,Vector2f size,float posY,boolean start) {
		Vector2f tilesize = tileset.tiles[0].GetRegionSize();
		for (int i = 2; i < layers.size(); i++) {
			Vector2f posStart = new Vector2f(pos.x/tilesize.x, pos.y/tilesize.y);
			Vector2f mapTile = new Vector2f(size.x/tilesize.x,size.y/tilesize.y);
			Vector2f posEnd =  new Vector2f(posStart.x+mapTile.x+2,posStart.y+mapTile.y+2);
			if(start) {
				posEnd.y = (posY/tilesize.y)+1;
			}
			else {
				posStart.y = (posY/tilesize.y)+1;
			}
			drawLayer(batch,posStart,posEnd,i);
		}
	}
}
