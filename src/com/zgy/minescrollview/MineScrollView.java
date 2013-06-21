package com.zgy.minescrollview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;

@SuppressLint("NewApi")
public class MineScrollView extends ScrollView {

	private MineScrollViewListener OnScrollListener;
	
	private int scrollY = 0;
	private boolean onDrawScrolling;
	private boolean onTouchScrolling;

	private boolean isTouchScrollingListen;// 手指滑动是否视为滚动中

	public MineScrollView(Context context) {
		super(context);
		scrollY = 0;
	}

	public MineScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		scrollY = 0;
	}

	/**
	 * 务必设置此bool变量，当且仅当scrollView可以滚动时，设置为true
	 * 
	 * @Description:
	 * @param isTouchScrollingListen
	 * @see:
	 * @since:
	 * @author: zhuanggy
	 * @date:2013-6-19
	 */
	public void setTouchScrollingListen(boolean isTouchScrollingListen) {
		this.isTouchScrollingListen = isTouchScrollingListen;
	}

	/**
	 * 设置监听器
	 * @Description:
	 * @param onScrollListener
	 * @see: 
	 * @since: 
	 * @author: zhuanggy
	 * @date:2013-6-19
	 */
	public void setOnScrollListener(MineScrollViewListener onScrollListener) {
		OnScrollListener = onScrollListener;
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// Log.e("MineScrollView", "onDraw    getScrollY=" + getScrollY());

		int nowScrollY = getScrollY();

		if (scrollY == nowScrollY) {
			onDrawScrolling = false;
			if (!onTouchScrolling && !onDrawScrolling) {
				OnScrollListener.notScrolling();
			}
		} else {
			scrollY = nowScrollY;
			onDrawScrolling = true;
			if (onDrawScrolling || onTouchScrolling) {
				OnScrollListener.onScrolling();
			}

		}
		super.onDraw(canvas);
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		if (isTouchScrollingListen) {
			switch (ev.getAction()) {
			case MotionEvent.ACTION_DOWN:
				onTouchScrolling = false;
				break;
			case MotionEvent.ACTION_MOVE:
				onTouchScrolling = true;
				break;
			case MotionEvent.ACTION_UP:
				onTouchScrolling = false;
				break;
			default:
				break;
			}
		}

		return super.onTouchEvent(ev);
	}
}
