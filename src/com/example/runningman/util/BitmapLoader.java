package com.example.runningman.util;

import com.example.runningman.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * 图片加载器，
 * 该类负责一次性加载所有图片到内存，
 * 加快游戏速度。<br>
 * 使用了加载器之后其他各类不需要回收bitmap，
 * 游戏结束后由Loader自行回收。
 * @author 倪陆章
 *
 */
public class BitmapLoader {
	
	public Context context;
    public static Bitmap b1, b2, b3, b4, b5;  //障碍物
    public static Bitmap b6, b7, b8, b9; //玩家李晨
    public static Bitmap b22, b23, b24, b25; //玩家陈赫
    public static Bitmap b26, b27, b28, b29; //玩家Baby
    public static Bitmap b30, b31, b32, b33; //玩家邓超
    public static Bitmap b34, b35, b36, b37; //玩家郑恺
    public static Bitmap b38, b39, b40, b41; //玩家王祖蓝
    public static Bitmap b42, b43, b44, b45; //玩家王宝强
    public static Bitmap b10; //路块
    public static Bitmap b11; //小型不劳而获果实
    public static Bitmap b13; //中型不劳而获果实
    public static Bitmap b15; //大型不劳而获果实
    public static Bitmap b17; //隐身药水
    public static Bitmap b19, b20; //竹蜻蜓
    public static Bitmap b21; //透明帧
    
  //  public static int scale;
    
    public BitmapLoader(Context context){
    	this.context=context;
   // 	scale =(int)context.getResources().getDisplayMetrics().density; 
    }
    
	/**
	 * 一次加载所有图片资源，<br>
	 * 使用时即可直接从内存读取，<br>
	 * 加快游戏运行速度
	 */
	public void loadBitmaps(){
		b1=BitmapFactory.decodeResource(context.getResources(), R.drawable.box1);
		b2=BitmapFactory.decodeResource(context.getResources(), R.drawable.box2);
		b3=BitmapFactory.decodeResource(context.getResources(), R.drawable.box3);
		b4=BitmapFactory.decodeResource(context.getResources(), R.drawable.box4);
		b5=BitmapFactory.decodeResource(context.getResources(), R.drawable.box5);
		
		b6=BitmapFactory.decodeResource(context.getResources(), R.drawable.lc_r0);
		b7=BitmapFactory.decodeResource(context.getResources(), R.drawable.lc_r1);
		b8=BitmapFactory.decodeResource(context.getResources(), R.drawable.lc_r2);
		b9=BitmapFactory.decodeResource(context.getResources(), R.drawable.lc_r3);
		
		b10=BitmapFactory.decodeResource(context.getResources(), R.drawable.ground);
		
		b11=BitmapFactory.decodeResource(context.getResources(), R.drawable.radish);
		
		b13=BitmapFactory.decodeResource(context.getResources(), R.drawable.apple);
		
		b15=BitmapFactory.decodeResource(context.getResources(), R.drawable.grape);
		
		b17=BitmapFactory.decodeResource(context.getResources(), R.drawable.wine);
		
		b19=BitmapFactory.decodeResource(context.getResources(), R.drawable.fly);
		b20=BitmapFactory.decodeResource(context.getResources(), R.drawable.fly2);
		
		b21=BitmapFactory.decodeResource(context.getResources(), R.drawable.r5);
		
		b22=BitmapFactory.decodeResource(context.getResources(), R.drawable.chh_r0);
		b23=BitmapFactory.decodeResource(context.getResources(), R.drawable.chh_r1);
		b24=BitmapFactory.decodeResource(context.getResources(), R.drawable.chh_r2);
		b25=BitmapFactory.decodeResource(context.getResources(), R.drawable.chh_r3);
		
		b26=BitmapFactory.decodeResource(context.getResources(), R.drawable.bb_r0);
		b27=BitmapFactory.decodeResource(context.getResources(), R.drawable.bb_r1);
		b28=BitmapFactory.decodeResource(context.getResources(), R.drawable.bb_r2);
		b29=BitmapFactory.decodeResource(context.getResources(), R.drawable.bb_r3);
		
		b30=BitmapFactory.decodeResource(context.getResources(), R.drawable.dc_r0);
		b31=BitmapFactory.decodeResource(context.getResources(), R.drawable.dc_r1);
    	b32=BitmapFactory.decodeResource(context.getResources(), R.drawable.dc_r2);
		b33=BitmapFactory.decodeResource(context.getResources(), R.drawable.dc_r3);
		
		b34=BitmapFactory.decodeResource(context.getResources(), R.drawable.zk_r0);
		b35=BitmapFactory.decodeResource(context.getResources(), R.drawable.zk_r1);
		b36=BitmapFactory.decodeResource(context.getResources(), R.drawable.zk_r2);
		b37=BitmapFactory.decodeResource(context.getResources(), R.drawable.zk_r3);

		b38=BitmapFactory.decodeResource(context.getResources(), R.drawable.wzl_r0);
		b39=BitmapFactory.decodeResource(context.getResources(), R.drawable.wzl_r1);
		b40=BitmapFactory.decodeResource(context.getResources(), R.drawable.wzl_r2);
		b41=BitmapFactory.decodeResource(context.getResources(), R.drawable.wzl_r3);

		b42=BitmapFactory.decodeResource(context.getResources(), R.drawable.wbq_r0);
		b43=BitmapFactory.decodeResource(context.getResources(), R.drawable.wbq_r1);
		b44=BitmapFactory.decodeResource(context.getResources(), R.drawable.wbq_r2);
		b45=BitmapFactory.decodeResource(context.getResources(), R.drawable.wbq_r3);

	}

}
