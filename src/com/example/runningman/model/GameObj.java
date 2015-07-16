package com.example.runningman.model;

import android.graphics.Bitmap;

/**
 * @author 李明伟
 * @version 1.0
 */
	
public abstract class GameObj {
	protected int currentFrame;
	protected int x;
	protected int y;
	protected int objWidth;
	protected int objHeight;
	protected boolean isAlive;
	protected Bitmap bitmap;
	
	public GameObj(){}
	
	/**
	 * 初始化图片资源
	 */
	protected abstract void initBitmap();
	
    /**
     * 对象的逻辑方法
     */
	public void logic(){}

	/**
	 * 获取对象的横坐标
	 * @return 对象的横坐标值
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * 设置对象的横坐标
	 * @param x 横坐标值
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * 获取对象的纵坐标
	 * @return 对象的纵坐标值
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * 设置对象的纵坐标
	 * @param y 对象的纵坐标值
	 */
	public void setY(int y) {
		this.y = y;
	}
	public int getObjWidth() {
		return objWidth;
	}
	public void setObjWidth(int objWidth) {
		this.objWidth = objWidth;
	}
	public int getObjHeight() {
		return objHeight;
	}
	public void setObjHeight(int objHeight) {
		this.objHeight = objHeight;
	}
	public boolean isAlive() {
		return isAlive;
	}
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	
	public int getCurrentFrame(){
		return currentFrame;
	}
	
	public void setCurrentFrame(int currentFrame){
	    this.currentFrame=currentFrame;
	}		
	
	/**
	 * 获取对象的Bitmap
	 * @return 对象的Bitmap
	 */
	public Bitmap getBitmap(){
		return bitmap;
	}
	
}
