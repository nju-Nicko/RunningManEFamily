package com.example.runningman.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import com.example.runningman.R;
import com.example.runningman.factory.BarrierBlockGenerator;
import com.example.runningman.factory.FoodGenerator;
import com.example.runningman.factory.PlayerGenerator;
import com.example.runningman.factory.RoadBlockGenerator;
import com.example.runningman.model.BarrierBlock;
import com.example.runningman.model.Food;
import com.example.runningman.model.GameObj;
import com.example.runningman.model.Player;
import com.example.runningman.model.RoadBlock;
import com.example.runningman.util.BitmapLoader;
import com.example.runningman.util.Constants;
import com.example.runningman.util.RunnerType;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * 游戏主界面，绘制各个游戏元素
 * @author 倪陆章
 *
 */
public class GameView extends SurfaceView implements SurfaceHolder.Callback, Runnable{
	
	SurfaceHolder sfh;
	private Thread thread;
	private boolean isRunning=true;
	private Paint paint;
	private Context context;
	private int screenWidth;
	private int screenHeight;
	private PlayerGenerator pg;
//	private HouseGenerator hg;
	private RoadBlockGenerator rbg;
	private BarrierBlockGenerator bbg; 
	private FoodGenerator fg;
//	private ArrayList<House> houses;
	private ArrayList<Food> foods;
	private ArrayList<RoadBlock> roadBlocks;
	private ArrayList<ArrayList<BarrierBlock>> barrierBlocks;
	private Player player;
	private RunnerType runnerType;
//	private int houseTime=0;
 //   private int houseFrequency=Constants.houseGap;
 //   private int speedChangeFrequency=Constants.speedChangeFrequency;
    private int meters=0;
	private Typeface font;
	private int barrierGap=Constants.barrierGap;
	private boolean isJump=false;
	private boolean up=true;
//	private int houseLayer;
	private int roadLayer;
	private int playerLayer;
	private int barrierLayer;
	private int foodLayer;
	private int runSpeed=Constants.runSpeed;
	private int adjustment=2; //调节图片之间的不协调，具体值和调节值的个数视情况
	private Bitmap bg;
	private int player_fps=Constants.player_fps;
	private Random random;
	private int rd;
	private int invisibleTime=0;   //隐身状态的剩余时长
	private int scoreUpShowTime=0; //加分提示剩余时长
	private int scoreUpVal=0; //加的分数
	private boolean isFlying=false;
	private Food tool=null;
	private int flyTime=0; //飞行的剩余时间
	private int jumpMaxHeight=Constants.jump_max_height;
	private int foodFrequency=Constants.foodFrequency;
	private boolean gameover=false;
	private int deathUpMaxHeight=Constants.deathUpMaxHeight;
	private boolean deathUp=true;
	private int deathY;
	private Handler handler;
	
	public GameView(Context context, RunnerType runnerType, Handler handler) {
		super(context);
		// TODO Auto-generated constructor stub
		this.runnerType=runnerType;
		this.context=context;
		this.handler=handler;
	   gameInit(runnerType, context);			  
	}
	
	/**
	 * 根据用户选择的角色初始化游戏数据
	 * @param runnerType 角色类型
	 * @param context 上下文信息
	 * @return 无返回值
	 */
	public void gameInit(RunnerType runnerType, Context context){
		  sfh=this.getHolder();
		  sfh.addCallback(this);
		  thread=new Thread(this);
		  paint=new Paint();
		  font=Typeface.createFromAsset(context.getAssets(), "STCAIYUN.TTF");
		  		  
		//  houses=new ArrayList<House>();	  
		  foods=new ArrayList<Food>();
		  roadBlocks=new ArrayList<RoadBlock>();
		  barrierBlocks=new ArrayList<ArrayList<BarrierBlock>>();
		  this.runnerType=runnerType;
		  this.context=context;
		  pg=new PlayerGenerator(runnerType);
		//  hg=new HouseGenerator(context);
		  rbg=new RoadBlockGenerator();
		  bbg=new BarrierBlockGenerator();
		  fg=new FoodGenerator();
		  
		  if(runnerType==RunnerType.LC){
			  jumpMaxHeight+=5;
		  }
		  else if(runnerType==RunnerType.DC){
			  foodFrequency-=200;
		  }
		  else if(runnerType==RunnerType.WBQ){
			  barrierGap+=200;
		  }
		  else if(runnerType==RunnerType.ZK){
			
		  }
		  
		  random=new Random();
		  rd=random.nextInt(Constants.randomUpper)*20+barrierGap;

	}
	
	@Override
	public void surfaceCreated(SurfaceHolder arg0){
	    screenWidth=this.getWidth();
		screenHeight=this.getHeight();

	    roadLayer=screenHeight;

	    /*初始添加2个房屋在界面上*/
/*		House fh1=hg.createHouse();
		fh1.setX(screenWidth/4); fh1.setY(houseLayer-fh1.getObjHeight());
		houses.add(fh1);
		  
		House fh2=hg.createHouse();
		fh2.setX(screenWidth/4+Constants.houseGap+(int)Math.random()*20); fh2.setY(houseLayer-fh2.getObjHeight());
		houses.add(fh2); */
		
		/*添加初始的路块*/
		RoadBlock rb=rbg.createRoadBlock();
		int numOfRoadBlocks=(int)Math.ceil((screenWidth+0.0)/rb.getObjWidth());
		for(int i=0; i<=numOfRoadBlocks-1; i++){
			RoadBlock r=rbg.createRoadBlock();
			r.setX(i*r.getObjWidth());
			r.setY(roadLayer-r.getObjHeight());
			roadBlocks.add(r);
		}

	    player=pg.createPlayer();
	    playerLayer=roadLayer-rb.getObjHeight()-player.getObjHeight()+adjustment;
		//玩家的起始位置
		player.setX(0);
		player.setY(playerLayer);
		
		ArrayList<BarrierBlock> tmp=bbg.createBarrierBlocks();
		barrierLayer=roadLayer-rb.getObjHeight()-tmp.get(0).getObjHeight()+adjustment;
			  
		  Bitmap pic_background=BitmapFactory.decodeResource(context.getResources(), R.drawable.gamebg);
		  bg=Bitmap.createScaledBitmap(pic_background, screenWidth, screenHeight, false);
		  pic_background.recycle();
		  
		  foodLayer=playerLayer-Constants.jump_max_height;   //foodLayer不随玩家的变化而变化
		  
		  initPaint();
		
	    thread.start();
	}
	
	/**
	 * 初始化画笔
	 */
	public void initPaint(){
		if(paint!=null){
			paint.setColor(Color.BLACK);
			paint.setTextSize(35);
			paint.setColor(Color.rgb(80, 80, 80));
			paint.setStrokeWidth(3);
			paint.setAntiAlias(true);
			paint.setDither(true);
			
			paint.setTypeface(font);
		}
	}
	
	/**
	 * 游戏各个元素的生成包括他们的初始位置
	 * @param context 上下文信息
	 * @return 无返回值
	 */
/*	public void gameElementGenerate(Context context){
	   if(houseTime==houseFrequency){
		   House house=hg.createHouse();
		   house.setX(screenWidth);
		   house.setY(houseLayer-house.getObjHeight());
		   houses.add(house);
		   houseTime=0;
	   } 
	}*/
	
	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		isRunning=false;
		try{
			Thread.sleep(300);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Override 
    public void run(){
	/*	if(sfh!=null){
			Canvas canvas=sfh.lockCanvas();
			paint.setStyle(Style.FILL);
			paint.setColor(Color.WHITE);
			if(canvas!=null)
				canvas.drawRect(new Rect(0, 0, screenWidth-1, screenHeight-1), paint);
			if(canvas!=null)
				sfh.unlockCanvasAndPost(canvas);
		}*/
		
		while(isRunning){
	//		Log.i("道具的个数", foods.size()+"");
			long m1=new Date().getTime();
			
			/*绘制UI*/
			drawGameView();
			/*生成新的游戏元素，如路、房屋、道具、障碍等*/
	//	    gameElementGenerate(context);
	//		houseTime++;  
			if(!gameover)
				meters++;
			if(invisibleTime!=0){
				invisibleTime--;
				if(invisibleTime==0){
					player.setInvisible(false);
			    	player.getBitmaps().remove(4);
			    	/*由于删除了一帧所以要检测player的当前帧是不是在被删除的帧上
			    	   如果是则把当前帧定位到第0帧*/
			    	if(player.getCurrentFrame()==4) player.setCurrentFrame(0);
				}
			}
			if(scoreUpShowTime!=0){
				scoreUpShowTime--;
				if(scoreUpShowTime==0){
					scoreUpVal=0;
				}
			}
			
			long m2=new Date().getTime();
			if(m2-m1<6){
				try{
					Thread.sleep(6-(m2-m1));
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 游戏UI的绘制
	 * @return 无返回值
	 */
	public void drawGameView(){
		if(sfh!=null){
			Canvas canvas=sfh.lockCanvas();
			
//			long m1=new Date().getTime();
			/*绘制背景*/
			if(canvas!=null)
				canvas.drawBitmap(bg, null, new Rect(0, 0, screenWidth, screenHeight), paint);
			//绘制分数
			drawScore(canvas);
			//绘制道具
			drawFood(canvas);
			//绘制障碍物
			drawBarrierBlocks(canvas);
			//绘制房屋
   //			drawHouse(canvas);
			//绘制道路
			drawRoadBlocks(canvas);
			//绘制玩家
			drawPlayer(canvas);
			//碰撞探测
			if(barrierBlocks.size()>1&&barrierCollisionDetect()){	//障碍物的数目不为0时才进行碰撞检测
				gameover=true;
				deathY=player.getY();
			}
			if(isJump&&foods.size()>0)
				foodCollisionDetect();         //当道具大于0个并且跳起来时才进行食物的碰撞检测
//			long m2=new Date().getTime();
	//		Log.i("绘制时间", ""+(m2-m1));
			if(canvas!=null)
				sfh.unlockCanvasAndPost(canvas);
		}
	}
	
	/**
	 * 游戏碰撞检测
	 * @return true，玩家碰撞到了障碍物; false，没有撞到障碍物
	 */
	public boolean barrierCollisionDetect(){
		boolean c=false;
		if(player.getInvisible()==true&&
				invisibleTime!=0){
			return false;
		}
		for(int i=0; i<=barrierBlocks.size()-1; i++){
			ArrayList<BarrierBlock> list=barrierBlocks.get(i);
			for(int j=0; j<=list.size()-1; j++){
				BarrierBlock bb=list.get(j);
				c=isCollision(player, bb);			
				if(c) return true;
			}
		}
		return false;
	}
	
	/**
	 * 检测有没有吃到道具
	 * @return 无
	 */
	public void foodCollisionDetect(){
		for(int i=0; i<=foods.size()-1; i++){
			Food f=foods.get(i);
			if(isCollision(player, f)){
				switch(f.getType()){
				case 0:
					meters+=50;
					scoreUpShowTime=Constants.scoreUpShowTime;
					scoreUpVal=50;
					foods.remove(i);  //道具被吃掉，从链表中删除
					i--;
					break;
				case 1:
					meters+=100;
					scoreUpShowTime=Constants.scoreUpShowTime;
					scoreUpVal=100;
					foods.remove(i);  //道具被吃掉，从链表中删除
					i--;
					break;
				case 2:
					meters+=200;
					scoreUpShowTime=Constants.scoreUpShowTime;
					scoreUpVal=200;
					foods.remove(i);  //道具被吃掉，从链表中删除
					i--;
					break;
				case 3:
					player.getBitmaps().add(BitmapLoader.b21);
					player.setInvisible(true);
					invisibleTime=Constants.invisibleTime;
					foods.remove(i);  //道具被吃掉，从链表中删除
					i--;
					break;
				case 4:
				    isFlying=true;
				    isJump=false;
				    tool=f;
				    flyTime=Constants.flyTime;
					break;
				}
			}
		}
	}
	
	/**
	 * 绘制道具，道具的生成在此控制
	 * @param canvas 画布对象
	 */
	public void drawFood(Canvas canvas){
	//	Log.i("fs", foods.size()+"");
		if(canvas!=null){
			for(int i=0; i<=foods.size()-1; i++){		
				Food f=foods.get(i);
				if(f.getType()!=4){
					canvas.save();
					Matrix matrix=new Matrix();
					matrix.setRotate(f.getRotate(), f.getX()+f.getObjWidth()/2, f.getY()+f.getObjHeight()/2);
					canvas.setMatrix(matrix);
					canvas.drawBitmap(f.getBitmap(), f.getX(), f.getY(), paint);
					canvas.restore();
				}
				else{
					canvas.drawBitmap(f.getBitmaps().get(f.getCurrentFrame()), f.getX(), f.getY(), paint);
					f.setCurrentFrame((f.getCurrentFrame()+1)%f.getBitmaps().size());
				}
				if(!gameover&&(!isFlying||(isFlying&&flyTime==0)))
					f.setX(f.getX()-runSpeed);
				if(f.getX()+f.getObjWidth()<=0){
					foods.remove(f);
					i--;
				}
			}
			if(whetherFood()){
				Food newFood=fg.createFood();
				newFood.setX(screenWidth);
				newFood.setY(foodLayer);
				foods.add(newFood);
			}
		}
	}
	
	/**
	 * 控制是否生成道具的逻辑
	 * @return true 生成道具; false 不生成道具
	 */
	public boolean whetherFood(){
		if(meters!=0&&meters%foodFrequency==0) return true;  //游戏刚开始时不立即产生道具
		return false;
	}
	
	/**
	 * 绘制分数
	 * @param canvas 画布对象
	 */
	public void drawScore(Canvas canvas){
		if(canvas!=null){
			canvas.drawText(meters+"", 20, 50, paint);
			if(scoreUpShowTime!=0){
				paint.setColor(Color.rgb(50, 205, 50));
				canvas.drawText(scoreUpVal+"↑", 20+paint.measureText(meters+"")+10, 50, paint);
				paint.setColor(Color.rgb(80, 80,  80));
			}
		}
	}
	
	/**
	 * 绘制玩家
	 * @param canvas 画布对象
	 */
	public void drawPlayer(Canvas canvas){
		if(canvas!=null){		
	         if(gameover){  //玩家输掉状态
	        	    int y=player.getY();
					canvas.drawBitmap(player.getBitmaps().get(player.getCurrentFrame()), player.getX(), player.getY(), paint);
                    if(deathUp&&deathY-y<deathUpMaxHeight){
                    	player.setY(player.getY()-runSpeed);
                    }
                    else if(deathUp&&deathY-y==deathUpMaxHeight){
                    	deathUp=false;
                    }
                    else if(!deathUp&&player.getY()<screenHeight){
                    	player.setY(player.getY()+runSpeed);
                    }
                    else if(!deathUp&&player.getY()>=screenHeight){
                    	isRunning=false;	
                    	if(runnerType==RunnerType.WZL)
                    		meters=(int)(meters*1.2);
                	    Message message=Message.obtain();
                	    message.obj=meters;
                	    handler.sendMessage(message);
                    }
	         	}
	        else 	if(!isJump&&!isFlying){    //正常的地面奔跑状态
				canvas.drawBitmap(player.getBitmaps().get(player.getCurrentFrame()), player.getX(), player.getY(), paint);
				if(player.getX()<screenWidth/4)
					player.setX(player.getX()+runSpeed);
				if(meters%(1000/player_fps)==0)
					player.setCurrentFrame((player.getCurrentFrame()+1)%player.getBitmaps().size());
			}
			else if(isJump){   //跳跃状态
				drawJump(canvas);
			}
			else if(isFlying){  //飞翔状态
				drawFlying(canvas);
			}
	
		}
	}
	
	/**
	 * 玩家跳跃的绘制
	 * @param canvas 画布
	 */
	public void drawJump(Canvas canvas){
		if(canvas!=null){
			int y=player.getY();
			if(up&&playerLayer-y<jumpMaxHeight){
				player.setY(y-runSpeed);
			}
			else if(up&&playerLayer-y==jumpMaxHeight){
				up=false;
			}
			else if(!up&&playerLayer-y>0){
				player.setY(y+runSpeed);
			}
			else if(!up&&playerLayer-y==0){
				up=true;
				isJump=false;
			}
			if(player.getCurrentFrame()==4) player.setCurrentFrame(0);  //如果是透明帧则转为绘制第一帧
			canvas.drawBitmap(player.getBitmaps().get(player.getCurrentFrame()), player.getX(), player.getY(), paint);
/*			if(meters%(1000/player_fps)==0)
				player.setCurrentFrame((player.getCurrentFrame()+1)%player.getBitmaps().size()); */
			//为了更形象，跳起来到时候保持人物的姿态不变
		}
	}
	
	/**
	 * 玩家飞翔的绘制
	 * @param canvas 画布
	 */
	public void drawFlying(Canvas canvas){
		if(canvas!=null){
			int y=player.getY();
			if(flyTime!=0&&playerLayer-y<Constants.flyMaxHeight){
				player.setY(y-runSpeed);
				tool.setX(player.getX());
				tool.setY(player.getY()-tool.getObjHeight());
			}
			else{
				if(flyTime>0)
					flyTime--;
				if(flyTime==0){
					player.setY(player.getY()+runSpeed);
					if(player.getY()==playerLayer){
						isFlying=false;
					}
					if(player.getY()==playerLayer-25){
						player.getBitmaps().add(BitmapLoader.b21);
						player.setInvisible(true);
						invisibleTime=Constants.inVisibleTime2;
					}
				}
			}
			canvas.drawBitmap(player.getBitmaps().get(player.getCurrentFrame()), player.getX(), player.getY(), paint);
		}
	}
	
	/**
	 * 绘制房屋
	 * @param canvas 画布
	 */
	/*public void drawHouse(Canvas canvas){
		if(canvas!=null){
			for(int i=0; i<=houses.size()-1; i++){
				House h=houses.get(i);
				canvas.drawBitmap(h.getBitmap(), h.getX(), houseLayer-h.getObjHeight(), paint);
				if(player.getX()>=screenWidth/4){
					h.setX(h.getX()-runSpeed);
					if(h.getX()+h.getObjWidth()<=0){
						houses.remove(i);	
						h.getBitmap().recycle();//回收不要用的对象的bitmap
						i--;
					}

				}
			}
		}
	}*/
	
	/**
	 * 绘制路块，路块的生成在此控制
	 * @param canvas 画布
	 */
	public void drawRoadBlocks(Canvas canvas){
		if(canvas!=null){
			for(int i=0; i<=roadBlocks.size()-1; i++){
				RoadBlock rb=roadBlocks.get(i);
				canvas.drawBitmap(rb.getBitmap(), rb.getX(), rb.getY(), paint);  //绘制路块
				if(!gameover&&player.getX()>=screenWidth/4)
					rb.setX(rb.getX()-runSpeed);       //移动路块
				if(rb.getX()+rb.getObjWidth()<=0){   //删除路块回收资源
			          roadBlocks.remove(i);
		//	          rb.getBitmap().recycle();
			          i--;
				}
				if(i==roadBlocks.size()-1){
					if(rb.getX()+rb.getObjWidth()<=screenWidth){  //生成新路块
						RoadBlock newBlock=rbg.createRoadBlock();
						newBlock.setX(rb.getX()+rb.getObjWidth());
						newBlock.setY(roadLayer-rb.getObjHeight());
						roadBlocks.add(newBlock);
						break;
					}
				}
			}
		}
	}
	
	/**
	 * 绘制障碍物，并在此控制障碍物的生成。<br>
	 * 障碍物生成方法：决定一个合适的值，在此值上浮动，
	 * 作为障碍物之间的间隔
	 * @param canvas 画布
	 */
	public void drawBarrierBlocks(Canvas canvas){
	//	Log.i("bbs", barrierBlocks.size()+"");
		if(canvas!=null){
			if(barrierBlocks.size()>=1){
				ArrayList<BarrierBlock> theList=barrierBlocks.get(barrierBlocks.size()-1);
				BarrierBlock theOne=theList.get(theList.size()-1);
			    int  thePos=theOne.getX()+theOne.getObjWidth();
		    	if(screenWidth-thePos-rd>=0){
		    		addNewBarrier();
		         	}
		    	}
			else{
				addNewBarrier();
			}
			for(int i=0; i<=barrierBlocks.size()-1; i++){
				ArrayList<BarrierBlock> list=barrierBlocks.get(i);
				for(int j=0; j<=list.size()-1; j++){
					canvas.drawBitmap(list.get(j).getBitmap(), list.get(j).getX(), list.get(j).getY(), paint);
					if(!gameover&&player.getX()>=screenWidth/4)
						list.get(j).setX(list.get(j).getX()-runSpeed);
					if(j==list.size()-1){
						if(list.get(j).getX()+list.get(j).getObjWidth()<=0){
							barrierBlocks.remove(i);
							i--;
			/*				for(int k=0; k<=list.size()-1; k++){
								list.get(k).getBitmap().recycle();
							} */
						}
					}
				}
			}
		}
	}
	
	public void addNewBarrier(){
		ArrayList<BarrierBlock> bbs=bbg.createBarrierBlocks();
		int len=bbs.size();
		for(int i=0; i<=len-1; i++){
			if(i==0){
				bbs.get(i).setX(screenWidth);
			}
			else
				bbs.get(i).setX(bbs.get(i-1).getX()+bbs.get(i-1).getObjWidth());
			bbs.get(i).setY(barrierLayer);
			}
		barrierBlocks.add(bbs);
		  rd=random.nextInt(Constants.randomUpper)*20+barrierGap; //重置rd
	}

	@Override
	public boolean onTouchEvent(MotionEvent event){
		if(event.getAction()==MotionEvent.ACTION_DOWN){
           if(player.getX()>=screenWidth/4)
        	   if(!isFlying&&!gameover)
        		   isJump=true;
		}
		return true;
	}
	
	 public boolean isCollision(GameObj o1, GameObj o2) {
		 if(o1!=null&&o2!=null){ 
			 //如果图片不相交的话，不进行像素碰撞检测
			 int thisLeft=o1.getX(); int thisTop=o1.getY();
			 int thisRight=o1.getX()+o1.getObjWidth(); int thisBottom=o1.getY()+o1.getObjHeight();
			 int otherLeft=o2.getX(); int otherTop=o2.getY();
			 int otherRight=o2.getX()+o2.getObjWidth(); int otherBottom=o2.getY()+o2.getObjHeight();
		 
			 if (thisLeft > otherRight || thisTop > otherBottom || otherLeft > thisRight || otherTop > thisBottom) {
				 return false;
			 }
		  
			 //计算相交区域的矩形的左上角和右下角坐标，以及矩形的宽度和高度
			 int intersectLeft = thisLeft > otherLeft ? thisLeft : otherLeft;
			 int intersectTop = thisTop > otherTop ? thisTop : otherTop;
			 int intersectRight = thisRight < otherRight ? thisRight : otherRight;
			 int intersectBottom = thisBottom < otherBottom ? thisBottom : otherBottom;
			 int intersectWidth = intersectRight - intersectLeft;
			 int intersectHeight = intersectBottom - intersectTop;
		  
			 //计算图片相对于相交区域的偏移量（每张图片要去的相交区域的ARGB数据，其实最重要的还是取得A）
			 int thisImageXOffset = intersectLeft - thisLeft;
			 int thisImageYOffset = intersectTop - thisTop;
			 int otherImageXOffset = intersectLeft - otherLeft;
		   	int otherImageYOffset = intersectTop - otherTop;
		  
		   	//进行像素碰撞检测
		   	boolean isCollide;
			  isCollide = doPixelCollision(thisImageXOffset, thisImageYOffset, otherImageXOffset, otherImageYOffset, o1.getBitmap(), o2.getBitmap(), intersectWidth, intersectHeight);
			  return isCollide;
		 	}
		 	return false;
		 }
	 
		 //（算法思路：取得两张图片的相交区域的ARBG数据，然后取得的A（透明色）的数据，如果两张图片的A的数
		//据都不等于0则说明碰上了）
		 private boolean doPixelCollision(int firstImageXOffset, int firstImageYOffset, int secondImageXOffset, int secondImageYOffset,
		   Bitmap firstImage, Bitmap secondImage, int intersectWidth, int intersectHeight) {
			 if(firstImage!=null&&secondImage!=null){
				 int numPixels = intersectHeight * intersectWidth;
				 int[] argbData1 = new int[numPixels];
				 int[] argbData2 = new int[numPixels];

				 firstImage.getPixels(argbData1, 0, intersectWidth, // scanlength = width
						 firstImageXOffset, firstImageYOffset, intersectWidth, intersectHeight);
				 secondImage.getPixels(argbData2, 0, intersectWidth, secondImageXOffset, secondImageYOffset, intersectWidth, intersectHeight);
		  
				 //像素碰撞检测
				 for (int row = 0; row < intersectHeight; row++) {
					 for (int col = 0; col < intersectWidth; col++) {
						 if ((argbData1[row * intersectWidth + col] & 0xff000000) != 0 && (argbData2[row * intersectWidth + col] & 0xff000000) != 0) {
							 return true;
						 }
					 }
				 } 
				 return false;
			 }
		 return false;
		 }
}
