package com.example.runningman.activity;

import com.example.runningman.R;
import com.example.runningman.util.RunnerType;
import com.example.runningman.view.GameOverDialog;
import com.example.runningman.view.GameView;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;

/**
 * ÓÎÏ·½çÃæ
 * @author ÄßÂ½ÕÂ
 *
 */
public class MainActivity extends Activity {
	
	GameView gv;
	GameOverDialog god;
	RunnerType rt;
	Handler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		Bundle bundle=getIntent().getExtras();
		String guy=bundle.getString("runnertype");
		rt=RunnerType.CHH;
		if(guy.equals("chh")) rt=RunnerType.CHH;
		else if(guy.equals("bb")) rt=RunnerType.BB;
		else if(guy.equals("dc")) rt=RunnerType.DC;
		else if(guy.equals("lc")) rt=RunnerType.LC;
		else if(guy.equals("zk")) rt=RunnerType.ZK;
		else if(guy.equals("wzl")) rt=RunnerType.WZL;
		else if(guy.equals("wbq")) rt=RunnerType.WBQ;
		
		god= new GameOverDialog(MainActivity.this, R.style.dialog);
		god.setCancelable(false);
		handler=new Handler(){
			@Override
			public void handleMessage(Message message){
				god.setScore(Integer.parseInt(message.obj.toString()));
				god.setReGameListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					gv=new GameView(MainActivity.this, rt, handler);
			    	MainActivity.this.setContentView(gv);
			    	god.dismiss();
				}

				});

				god.setReturnListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
				god.dismiss();
				MainActivity.this.finish();
				}

				});

				god.show();
			}
		};
		if(gv==null){
			gv=new GameView(this, rt, handler);
		}
		setContentView(gv);
	}
}
