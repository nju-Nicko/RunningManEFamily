package com.example.runningman.model;

import java.util.ArrayList;

import android.graphics.Bitmap;

import com.example.runningman.util.BitmapLoader;
import com.example.runningman.util.Constants;

/**
 * 道具类
 * @author 李明伟
 * @version 1.0
 *
 */
public class Food extends GameObj {
	
	private int type;
	private ArrayList<Bitmap> bitmaps;
	private int rotate;
	private boolean up;

	public Food() {
		// TODO Auto-generated constructor stub
		this(0);
	}
	
	public Food(int type){
		this.type=type;
		bitmaps=new ArrayList<Bitmap>();
		rotate=0;
		up=true;
		initBitmap();
	}

	@Override
	protected void initBitmap() {
		// TODO Auto-generated method stub
		switch(type){
		case 0:  
	//		bitmaps.add(BitmapLoader.b11);
	//		bitmaps.add(BitmapLoader.b12);
			bitmap=BitmapLoader.b11; break;        //小型不劳而获果实，积分+50
		case 1:  
	//		bitmaps.add(BitmapLoader.b13);
	//		bitmaps.add(BitmapLoader.b14);
			bitmap=BitmapLoader.b13; break;         //中型不劳而获果实，积分+100
		case 2:
	//		bitmaps.add(BitmapLoader.b15);
	//		bitmaps.add(BitmapLoader.b16);
			bitmap=BitmapLoader.b15; break;         //大型不劳而获果实，积分+200
		case 3:
	//		bitmaps.add(BitmapLoader.b17);
	//		bitmaps.add(BitmapLoader.b18);
			bitmap=BitmapLoader.b17; break;         //隐身药水，可以穿越障碍物，有时限
		case 4:
			bitmaps.add(BitmapLoader.b19);
			bitmaps.add(BitmapLoader.b19);
			bitmaps.add(BitmapLoader.b20);
			bitmaps.add(BitmapLoader.b20);            //增加两帧一样的是为了用户能看出竹蜻蜓旋转的效果
			bitmap=BitmapLoader.b19; break;         //竹蜻蜓，可以在天上飞，有时限
		}
		
		setObjWidth(bitmap.getWidth());
		setObjHeight(bitmap.getHeight());
	}
	
	public int getType(){
		return type;
	}
	
	public int getRotate(){
		int tmp=rotate;
		if(up&&rotate<45){
			rotate+=Constants.rotateSpeed;
		}
		else if(up&&rotate==45){
			up=false;
		}
		else if(!up&&rotate>-45){
			rotate-=Constants.rotateSpeed;
		}
		else if(!up&&rotate==-45){
			up=true;
		}
		return tmp;
	}
	
	public ArrayList<Bitmap> getBitmaps(){
		return bitmaps;
	}
}
