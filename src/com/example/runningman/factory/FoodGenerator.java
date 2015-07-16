package com.example.runningman.factory;

import java.util.Random;

import com.example.runningman.model.Food;
import com.example.runningman.util.Constants;

/**
 * 道具生成器，随机生成一样道具，不控制它的位置以及何时生成等。
 * @author 任羡纲
 *
 */
public class FoodGenerator {
	
	Random random;
	
	public FoodGenerator(){
		random=new Random();
	}
	
	public Food createFood(){
		int val=random.nextInt(100)+1;  //随机生成1到100
		if(val>=1&&val<=Constants.food1_possibility){
			return new Food(0);
		}
		else if(val>Constants.food1_possibility&&val<=Constants.food1_possibility+Constants.food2_possibility){
			return new Food(1);
		}
		else if(val>Constants.food1_possibility+Constants.food2_possibility
				&&val<=Constants.food1_possibility+Constants.food2_possibility+Constants.food3_possibility){
			return new Food(2);
		}
		else if(val>Constants.food1_possibility+Constants.food2_possibility+Constants.food3_possibility
				&&val<=Constants.food1_possibility+Constants.food2_possibility+Constants.food3_possibility+Constants.food4_possibility){
			return new Food(3);
		}
		else
			return new Food(4);
	}

}
