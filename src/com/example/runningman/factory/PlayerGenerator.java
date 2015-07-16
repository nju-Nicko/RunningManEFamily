package com.example.runningman.factory;

import com.example.runningman.model.Player;
import com.example.runningman.util.RunnerType;

/**
 * 玩家生成器，生成玩家，但不控制玩家
 * @author 任羡纲
 *@version 1.0
 */
public class PlayerGenerator {
	
	 private RunnerType rt;
     
	 public PlayerGenerator( RunnerType rt){
		 this.rt=rt;
	 }
	 
	 public Player createPlayer(){
		 return new Player(rt);
	 }
}
