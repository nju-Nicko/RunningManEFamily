package com.example.runningman.util;

/**
 * 游戏各个常量数据的定义
 * @author 倪陆章
 *
 */
public final class Constants {
 	 public static final int barrierGap=200; 
     public static final int randomUpper=20;
     public static final int player_fps=250;
     public static final int barrier_types=5;
     public static final int barrier_max_len=3;
     public static final int runSpeed=5;
     public static final int jump_max_height=((int)Math.ceil(barrier_max_len*25/runSpeed/2))*runSpeed+(50-25)*2+20; //25是障碍物宽度的px，
     //50是人物高度的px，20是调节值
     public static final int food_types=5;
     public static final int food1_possibility=30; 
     public static final int food2_possibility=20;
     public static final int food3_possibility=10;
     public static final int food4_possibility=20;
     public static final int food5_possibility=20;
     public static final int foodFrequency=1000;
     public static final int rotateSpeed=3;
     public static final int invisibleTime=400;
     public static final int inVisibleTime2=100;
     public static final int scoreUpShowTime=100;
     public static final int flyMaxHeight=jump_max_height+50+50;   //50为玩家高度，50为调节值
     public static final int flyTime=400;
     public static final int deathUpMaxHeight=50;
}
