package com.example.runningman.factory;

import com.example.runningman.model.RoadBlock;
/**
 * 路块生成器，生成路块但不控制路块
 * @author 任羡纲
 * @version 1.0
 */
public class RoadBlockGenerator {
	
	public RoadBlockGenerator(){
	}
	
	public RoadBlock createRoadBlock(){
		return new RoadBlock();
	}

}
