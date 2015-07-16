package com.example.runningman.model;

import com.example.runningman.util.BitmapLoader;

/**
 * 公路块
 * @author 李明伟
 * @version 2.0
 *
 */
public class RoadBlock extends GameObj{

	public RoadBlock() {
		  initBitmap();
	}

	@Override
	protected void initBitmap() {
		// TODO Auto-generated method stub
		bitmap=BitmapLoader.b10;
		
		setObjWidth(bitmap.getWidth());
		setObjHeight(bitmap.getHeight());
	}

}
