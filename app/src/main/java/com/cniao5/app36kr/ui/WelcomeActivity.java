package com.cniao5.app36kr.ui;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cniao5.app36kr.R;
import com.cniao5.app36kr.ui.base.BaseActivity;
import com.cniao5.app36kr.widget.CustomVideoView;

/**
 * 当前类注释:启动欢迎界面
 * ProjectName：App36Kr
 * Author:<a href="http://www.cniao5.com">菜鸟窝</a>
 * Description：
 * 菜鸟窝是一个只专注做Android开发技能的在线学习平台，课程以实战项目为主，对课程与服务”吹毛求疵”般的要求，
 * 打造极致课程，是菜鸟窝不变的承诺
 */
public class WelcomeActivity extends BaseActivity {
    private Button welcome_button;
    private CustomVideoView welcome_videoview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_layout);
        welcome_button=(Button)this.findViewById(R.id.welcome_button);
        welcome_videoview = (CustomVideoView) this.findViewById(R.id.welcome_videoview);
        welcome_videoview.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.kr36));
        welcome_videoview.start();
        welcome_videoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                welcome_videoview.start();
            }
        });
        welcome_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(welcome_videoview.isPlaying()){
                    welcome_videoview.stopPlayback();
                }
                Intent mIntent=new Intent(WelcomeActivity.this,MainActivity.class);
                WelcomeActivity.this.startActivity(mIntent);
                WelcomeActivity.this.finish();
            }
        });
    }
}
