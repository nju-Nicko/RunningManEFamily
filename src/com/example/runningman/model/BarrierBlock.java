package com.example.runningman.model;

import com.example.runningman.util.BitmapLoader;

/**
 * 路障类
 * @author 李明伟
 * @version 1.0
 */
public class BarrierBlock extends GameObj {
	
	private int type;
	
	public BarrierBlock(){
		this(0);
	}
	
	/**
	 * 
	 * @param barrier_type 路障类型
	 */
	public BarrierBlock(int barrier_type){
		this.type=barrier_type;
		initBitmap();
	}

	@Override
	protected void initBitmap() {
		// TODO Auto-generated method stub
		switch(type){
		case 0: bitmap=BitmapLoader.b1; break;
		case 1: bitmap=BitmapLoader.b2; break; 
		case 2: bitmap=BitmapLoader.b3; break;
		case 3: bitmap=BitmapLoader.b4; break;
		case 4: bitmap=BitmapLoader.b5; break;
		}
		
		setObjWidth(bitmap.getWidth());
		setObjHeight(bitmap.getHeight());
	}
}
