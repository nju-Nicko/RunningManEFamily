package com.example.runningman.model;

import java.util.ArrayList;

import com.example.runningman.util.BitmapLoader;
import com.example.runningman.util.RunnerType;

import android.graphics.Bitmap;

/**
 * 玩家类
 * @author 李明伟
 * @version 1.0
 *
 */
public class Player extends GameObj{
    private RunnerType runnerType;
    private ArrayList<Bitmap> bitmaps;
    private boolean invisible;
    
	public Player(RunnerType runnerType) {
	   this.runnerType=runnerType;
	   bitmaps=new ArrayList<Bitmap>();
	   invisible=false;
	   initBitmap();
	}

	@Override
	protected void initBitmap() {
		// TODO Auto-generated method stub
		if(runnerType==RunnerType.CHH){
			Bitmap d1=BitmapLoader.b22;
			Bitmap d2=BitmapLoader.b23;
			Bitmap d3=BitmapLoader.b24;
			Bitmap d4=BitmapLoader.b25;
			bitmap=d1;
			bitmaps.add(d1);
			bitmaps.add(d2);
			bitmaps.add(d3);
			bitmaps.add(d4);
		}
		else if(runnerType==RunnerType.BB){
			Bitmap d1=BitmapLoader.b26;
			Bitmap d2=BitmapLoader.b27;
			Bitmap d3=BitmapLoader.b28;
			Bitmap d4=BitmapLoader.b29;
			bitmap=d1;
			bitmaps.add(d1);
			bitmaps.add(d2);
			bitmaps.add(d3);
			bitmaps.add(d4);
		}
		else if(runnerType==RunnerType.DC){
			Bitmap d1=BitmapLoader.b30;
			Bitmap d2=BitmapLoader.b31;
			Bitmap d3=BitmapLoader.b32;
			Bitmap d4=BitmapLoader.b33;
			bitmap=d1;
			bitmaps.add(d1);
			bitmaps.add(d2);
			bitmaps.add(d3);
			bitmaps.add(d4);
		}
		else if(runnerType==RunnerType.LC){
			Bitmap d1=BitmapLoader.b6;
			Bitmap d2=BitmapLoader.b7;
			Bitmap d3=BitmapLoader.b8;
			Bitmap d4=BitmapLoader.b9;
			bitmap=d1;
			bitmaps.add(d1);
			bitmaps.add(d2);
			bitmaps.add(d3);
			bitmaps.add(d4);
		}
		else if(runnerType==RunnerType.ZK){
			Bitmap d1=BitmapLoader.b34;
			Bitmap d2=BitmapLoader.b35;
			Bitmap d3=BitmapLoader.b36;
			Bitmap d4=BitmapLoader.b37;
			bitmap=d1;
			bitmaps.add(d1);
			bitmaps.add(d2);
			bitmaps.add(d3);
			bitmaps.add(d4);
		}
		else if(runnerType==RunnerType.WZL){
			Bitmap d1=BitmapLoader.b38;
			Bitmap d2=BitmapLoader.b39;
			Bitmap d3=BitmapLoader.b40;
			Bitmap d4=BitmapLoader.b41;
			bitmap=d1;
			bitmaps.add(d1);
			bitmaps.add(d2);
			bitmaps.add(d3);
			bitmaps.add(d4);
		}
		else{
			Bitmap d1=BitmapLoader.b42;
			Bitmap d2=BitmapLoader.b43;
			Bitmap d3=BitmapLoader.b44;
			Bitmap d4=BitmapLoader.b45;
			bitmap=d1;
			bitmaps.add(d1);
			bitmaps.add(d2);
			bitmaps.add(d3);
			bitmaps.add(d4);
		}
		
		setObjWidth(bitmaps.get(0).getWidth());
		setObjHeight(bitmaps.get(0).getHeight());
	}
	
	public ArrayList<Bitmap> getBitmaps(){
		return bitmaps;
	}
	
	public boolean getInvisible(){
		return invisible;
	}
	
	public void setInvisible(boolean invisible){
		this.invisible=invisible;
	}
}
