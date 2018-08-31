package com.shanghaizhida.mykotlin;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.webkit.WebView;
import android.widget.ProgressBar;

@SuppressWarnings("deprecation")
public class ProgressWebView extends WebView {

	public ProgressBar progressbar;
	private String mTitle ="";
	public ProgressWebView(Context context, AttributeSet attrs) {
		super(context, attrs);

		progressbar = new ProgressBar(context, null,
				android.R.attr.progressBarStyleHorizontal);

		progressbar.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
                5, 0, 0));

		Drawable drawable = context.getResources().getDrawable(
				R.drawable.progress_bar_states);
		progressbar.setProgressDrawable(drawable);

		addView(progressbar);

//		setWebChromeClient(new WebChromeClient());
	}

	public class WebChromeClient extends android.webkit.WebChromeClient {
		@Override
		public void onProgressChanged(WebView view, int newProgress) {
			if (newProgress == 100) {
				progressbar.setVisibility(GONE);
			} else {
				if (progressbar.getVisibility() == GONE) {
					progressbar.setVisibility(VISIBLE);
				}
				progressbar.setProgress(newProgress);
			}
			super.onProgressChanged(view, newProgress);
		}

		@Override
		public void onReceivedTitle(WebView view, String title) {
			super.onReceivedTitle(view, title);
			mTitle = title;
		}
	}

	public String getmTitle() {
		return mTitle;
	}

	public void setmTitle(String mTitle){
		this.mTitle = mTitle;
	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		LayoutParams lp = (LayoutParams) progressbar.getLayoutParams();
		lp.x = l;
		lp.y = t;
		progressbar.setLayoutParams(lp);
		super.onScrollChanged(l, t, oldl, oldt);
	}
}
