package com.zgy.minescrollview;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		MineScrollView scrollview = (MineScrollView) findViewById(R.id.scrollview);

		scrollview.setTouchScrollingListen(true);// 若可以滚动，则设置为true(可通过容器的高度和内容的高度对比判断)
		scrollview.setOnScrollListener(new MineScrollViewListener() {

			@Override
			public void onScrolling() {
				Log.v("MainActivity", "scrolling");
			}

			@Override
			public void notScrolling() {
				Log.v("MainActivity", "not scrolling");
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
