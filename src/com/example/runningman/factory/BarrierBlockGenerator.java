package com.example.runningman.factory;

import java.util.ArrayList;
import java.util.Random;

import com.example.runningman.model.BarrierBlock;
import com.example.runningman.util.Constants;

/**
 * 障碍物生成器，控制障碍物的长度，不控制他的位置和变换等
 * @author 任羡纲
 *
 */
public class BarrierBlockGenerator {
	
	Random random;
	
	public BarrierBlockGenerator(){
		random=new Random();
	}
	
	public ArrayList<BarrierBlock> createBarrierBlocks(){
		int type=random.nextInt(Constants.barrier_types);
		int len=random.nextInt(Constants.barrier_max_len)+1;
		ArrayList<BarrierBlock> blocks=new ArrayList<BarrierBlock>();
		for(int i=0; i<=len-1; i++)
			blocks.add(new BarrierBlock(type));
		return blocks;
	}

}
