package com.example.runningman.activity;

import com.example.runningman.R;
import com.example.runningman.util.BitmapLoader;
import com.example.runningman.view.XCRoundRectImageView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 选人界面，供玩家选择跑男
 * @author 倪陆章
 *
 */
public class IndexActivity extends Activity {
	
	private XCRoundRectImageView selected=null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_index);
		
		final XCRoundRectImageView chh=(XCRoundRectImageView)findViewById(R.id.chh);
		final XCRoundRectImageView bb=(XCRoundRectImageView)findViewById(R.id.bb);
		final XCRoundRectImageView dc=(XCRoundRectImageView)findViewById(R.id.dc);
		final XCRoundRectImageView lc=(XCRoundRectImageView)findViewById(R.id.lc);
		final XCRoundRectImageView zk=(XCRoundRectImageView)findViewById(R.id.zk);
		final XCRoundRectImageView wzl=(XCRoundRectImageView)findViewById(R.id.wzl);
		final XCRoundRectImageView wbq=(XCRoundRectImageView)findViewById(R.id.wbq);
		final XCRoundRectImageView pic=(XCRoundRectImageView)findViewById(R.id.pic);
		final TextView name=(TextView)findViewById(R.id.name);
		final TextView ability=(TextView)findViewById(R.id.ability);
		
		
		BitmapLoader bl=new BitmapLoader(IndexActivity.this);
		bl.loadBitmaps();
		
		chh.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				pic.setImageBitmap(BitmapFactory.decodeResource(IndexActivity.this.getResources(), R.drawable.chenhe));
				name.setText(getResources().getString(R.string.g1));
				ability.setText(getResources().getString(R.string.a1));
				if(selected!=null)
					selected.setBorderColor(Color.rgb(0, 0, 0));
				selected=chh;
				chh.setBorderColor(Color.RED);
			}
			
		});
		
		bb.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				pic.setImageBitmap(BitmapFactory.decodeResource(IndexActivity.this.getResources(), R.drawable.baby));
				name.setText(getResources().getString(R.string.g2));
				ability.setText(getResources().getString(R.string.a2));
				if(selected!=null)
					selected.setBorderColor(Color.rgb(0, 0, 0));
				selected=bb;
				bb.setBorderColor(Color.RED);
			}
			
		});
		
		dc.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				pic.setImageBitmap(BitmapFactory.decodeResource(IndexActivity.this.getResources(), R.drawable.dengchao));
				name.setText(getResources().getString(R.string.g3));
				ability.setText(getResources().getString(R.string.a3));
				if(selected!=null)
					selected.setBorderColor(Color.rgb(0, 0, 0));
				selected=dc;
				dc.setBorderColor(Color.RED);
			}
			
		});
		
		lc.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
	            pic.setImageBitmap(BitmapFactory.decodeResource(IndexActivity.this.getResources(), R.drawable.lichen));
				name.setText(getResources().getString(R.string.g4));
				ability.setText(getResources().getString(R.string.a4));
				if(selected!=null)
					selected.setBorderColor(Color.rgb(0, 0, 0));
				selected=lc;
				lc.setBorderColor(Color.RED);
			}
			
		});
		
		zk.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				pic.setImageBitmap(BitmapFactory.decodeResource(IndexActivity.this.getResources(), R.drawable.zhengkai));
				name.setText(getResources().getString(R.string.g5));
				ability.setText(getResources().getString(R.string.a5));
				if(selected!=null)
					selected.setBorderColor(Color.rgb(0, 0, 0));
				selected=zk;
				zk.setBorderColor(Color.RED);
			}
			
		});
		
		wzl.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				pic.setImageBitmap(BitmapFactory.decodeResource(IndexActivity.this.getResources(), R.drawable.wangzulan));
				name.setText(getResources().getString(R.string.g6));
				ability.setText(getResources().getString(R.string.a6));
				if(selected!=null)
					selected.setBorderColor(Color.rgb(0, 0, 0));
				selected=wzl;
				wzl.setBorderColor(Color.RED);
			}
			
		});
		
		wbq.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				pic.setImageBitmap(BitmapFactory.decodeResource(IndexActivity.this.getResources(), R.drawable.wangbaoqiang));
				name.setText(getResources().getString(R.string.g7));
				ability.setText(getResources().getString(R.string.a7));
				if(selected!=null)
					selected.setBorderColor(Color.rgb(0, 0, 0));
				selected=wbq;
				wbq.setBorderColor(Color.RED);
			}
			
		});
		
		Button btn=(Button)findViewById(R.id.start);
		btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(selected==null){
					Toast.makeText(IndexActivity.this, "请选择跑男", Toast.LENGTH_LONG).show();
				}
				else{
					Intent intent=new Intent(IndexActivity.this, MainActivity.class);
					String guy=null;
					if(selected==chh) guy="chh";
					else if(selected==bb) guy="bb";
					else if(selected==dc) guy="dc";
					else if(selected==lc) guy="lc";
					else if(selected==zk) guy="zk";
					else if(selected==wzl) guy="wzl";
					else if(selected==wbq) guy="wbq";
					Bundle bundle=new Bundle();
					bundle.putCharSequence("runnertype", guy);
					intent.putExtras(bundle);
					startActivity(intent);
				}
			}
			
		});
	}
	
	
	@Override
	protected void onDestroy(){  //退出游戏，销毁所有资源
		super.onDestroy();
		System.exit(0);
	}
}
