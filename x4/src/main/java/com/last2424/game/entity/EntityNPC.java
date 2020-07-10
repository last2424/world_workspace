package com.last2424.game.entity;

import java.awt.Color;
import java.util.List;

import org.joml.Vector2f;
import org.joml.Vector2i;

import com.last2424.game.Textures;
import com.last2424.ogl.engine.Sprite;

public class EntityNPC extends Entity  {

	Vector2i direction = new Vector2i(0, 0);
	Vector2f addition = new Vector2f(0,0);
	int idSchedule;
	List<Behaivor> ScheduleGdt;
	public EntityNPC(Vector2f pos) {
		this.position = pos;
		this.sprite = new Sprite(Textures.player, Color.WHITE, new Vector2f(0, 0), new Vector2f(32, 32), 0);
	}
	public void Schedupdate(GameDayTime gdt) {
		if(gdt.More(ScheduleGdt.get(idSchedule).end)) {
			
			if(idSchedule+1>=ScheduleGdt.size() && gdt.More(ScheduleGdt.get(0).start)) {
					idSchedule=0;
			}
			else if(idSchedule+1<ScheduleGdt.size() && gdt.More(ScheduleGdt.get(idSchedule++).start)) {
					idSchedule++;
			}
			//выполняем действие промежуточное(обычно идем к цели)
		}else { 
			//выполняем действие со текущим действием
		}
	}
}
