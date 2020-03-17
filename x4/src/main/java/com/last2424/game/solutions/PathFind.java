package com.last2424.game.solutions;


import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.joml.Vector2f;
import org.lwjgl.opengl.GL11;

import com.last2424.ogl.engine.tiled.Map;
import com.last2424.ogl.rendering.SpriteBatch;
import com.last2424.utils.Rectangle;

public class PathFind {
	class Path{

		List<Vector2f> points = new ArrayList<Vector2f>();
		Vector2f start,end;
		boolean isEnd;
		boolean intComparateVector2f(Vector2f number1,Vector2f number2) {
			return intComparateFloat(number1.x, number2.x) && intComparateFloat(number1.y, number2.y);
		}
		boolean intComparateFloat(float number1,float number2) {
			return (int)number1==(int)number2;
		}
		List<Vector2f> getLowerPath(List<Vector2f> path1,List<Vector2f> path2, Vector2f end){
			Vector2f finalPath2 = path2.get(path2.size()-1);
			if(!intComparateVector2f(finalPath2, end) || path1.size()<path2.size()) return path1;
			return path2;
		}
		public List<Vector2f> getFind(Vector2f start, Vector2f end,Map map,int stacks,int maxStacks) {
			this.start = start;
			this.end = end;
			float endX = start.x;
			float endY = start.y;
			if(start.x!=end.x) {
				if(this.start.x<=this.end.x) {
					for(int i=(int) start.x;i<=(int)end.x;i++)
					{
						if(map.IsSolid(new Rectangle(new Vector2f(i*16,start.y*16),new Vector2f(16,16))))
						{
							break;
						}
						endX = i;
					}
				}

				else {
					for(int i=(int) end.x;i>(int)start.x;i++)
					{
						if(map.IsSolid(new Rectangle(new Vector2f(i*16,start.y*16),new Vector2f(16,16))))
						{
							break;
						}
						endX = i;
					}
				}
			}
			if(start.y!=end.y) {
				if(this.start.y<=this.end.y) {
					for(int i=(int) start.y;i<=(int)end.y;i++)
					{
						if(map.IsSolid(new Rectangle(new Vector2f(endX*16,i*16),new Vector2f(16,16))))
						{
							break;
						}
						endY = i;
					}
				}

				else {
					for(int i=(int) end.y;i>(int)start.y;i++)
					{
						if(map.IsSolid(new Rectangle(new Vector2f(endX*16,i*16),new Vector2f(16,16))))
						{
							break;
						}
						endY = i;
					}
				}
			}
			if(start.x != endX) points.add(new Vector2f(endX,start.y));
			points.add(new Vector2f(endX,endY));
			if(stacks>=maxStacks) return points;
			if(endX != end.x || endY != end.y) {
				List<Vector2f> newPathVertical = getLowerPath(new Path().getFind(new Vector2f(endX,endY+1), end, map,stacks+1,maxStacks),
						new Path().getFind(new Vector2f(endX,endY-1), end, map,stacks+1,maxStacks),end);
				List<Vector2f> backPath;
				if((int)endX<(int)end.x) {
					backPath= getLowerPath(new Path().getFind(new Vector2f(endX+1,endY+1), end, map,stacks+1,maxStacks),new Path().getFind(new Vector2f(endX+1,endY-1), end, map,stacks+1,maxStacks),end);
					
				}
				else if((int)endX>(int)end.x) {
					backPath= getLowerPath(new Path().getFind(new Vector2f(endX-1,endY+1), end, map,stacks+1,maxStacks),new Path().getFind(new Vector2f(endX-1,endY-1), end, map,stacks+1,maxStacks),end);
					
				}
				else {
					List<Vector2f>	backPathRight= getLowerPath(new Path().getFind(new Vector2f(endX+1,endY+1), end, map,stacks+1,maxStacks),
							new Path().getFind(new Vector2f(endX+1,endY-1), end, map,stacks+1,maxStacks),end);
					List<Vector2f>	backPathLeft =  getLowerPath(new Path().getFind(new Vector2f(endX-1,endY+1), end, map,stacks+1,maxStacks),
							new Path().getFind(new Vector2f(endX-1,endY-1), end, map,stacks+1,maxStacks),end);
					backPath = getLowerPath(backPathRight, backPathLeft, end);
				}
				List<Vector2f> getFinal = getLowerPath(backPath, newPathVertical, end);
				
				for(int i=0;i<getFinal.size();i++) {
					points.add(getFinal.get(i));
					endX = getFinal.get(i).x;
					endY = getFinal.get(i).y;
				}
			}
			return points;
		}
	}
	Vector2f start, end;
	List<Vector2f> points = new ArrayList<Vector2f>();
	
	public PathFind() {
		
	}
	Vector2f toGrid(Vector2f global) {
		return new Vector2f(global.x/16.0f,global.y/16.0f);
	}Vector2f toGlobal(Vector2f global) {
		return new Vector2f(global.x*16.0f,global.y*16.0f);
	}
	public void find(Vector2f start, Vector2f end,Map map,int maxStacks) {
		points = new ArrayList<Vector2f>();
		this.start = start;
		this.end = end;
		points.add(start);
		List<Vector2f> getFinal = new Path().getFind(toGrid(start),toGrid(end),map,0,maxStacks);
		Vector2f endPath = toGrid(new Vector2f(start.x,start.y));
		for(int i=0;i<getFinal.size();i++) {
			Vector2f currentPath = getFinal.get(i);
			if(endPath.x != currentPath.x && endPath.y != currentPath.y )
			{
				points.add(toGlobal(new Vector2f(endPath.x,currentPath.y)));
			}
			points.add(toGlobal(currentPath));
			endPath = currentPath;
		}
		System.out.println(points.size());
	}
	
	public void debugDraw(SpriteBatch batch) {
		for(int i=0;i<points.size()-1;i++)
			batch.drawLine(points.get(i), points.get(i+1), 5);
	}
}
