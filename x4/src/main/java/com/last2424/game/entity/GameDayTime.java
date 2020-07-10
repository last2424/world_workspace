package com.last2424.game.entity;

public class GameDayTime {
	public float hour;
	public float minute;
	float second;
	public boolean More(GameDayTime gdt) {
		if(hour>gdt.hour) return true;
		else if(hour == gdt.hour && minute>=gdt.minute) return true;
		return false;
	}
	public void UpdateTime(float delta) {
		second+=delta;
		if(second>=1) {
			second = 0;
			minute++;
			if(minute>=60) {
				minute = 0;
				hour++;
				if(hour>=24) {
					hour = 0;
				}
			}
		}
	}
}
