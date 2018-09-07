package com.olq.baseframe.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.olq.baseframe.R;


/**
 * author pangchao
 * created on 2017/5/20
 * email fat_chao@163.com.
 */

public abstract class FlexibleLayout extends FrameLayout {
    private View mLoadingView;
    private View mNetworkErrorView;
    private View mEmptyView;
    private ViewGroup mSuccessView;
    private ProgressBar mLoadingProgress;
    private TextView mLoadingText;
    private View title;

    public FlexibleLayout(Context context) {
        super(context);
//        setOrientation(VERTICAL);
        inflate(context, R.layout.layout_all, this);
        mSuccessView = initNormalView();
        title = mSuccessView.findViewWithTag("title");
        addView(mSuccessView);
        int childCount = getChildCount();
        Log.d("count---", String.valueOf(childCount));
    }


    public abstract ViewGroup initNormalView();

    public abstract void loadData();

    public void loadindData() {
        showPageWithState(State.Loading);
        loadData();
    }

    public void showPageWithState(State status) {
        if (status != State.Succeed && title != null) {
            String tag = (String) getChildAt(0).getTag();
            if (TextUtils.equals(tag, "title")) {
                //已经有标题栏
            } else {
                mSuccessView.removeView(title);
                addView(title, 0);
            }

        }
        switch (status) {
            case Succeed:
                mSuccessView.setVisibility(VISIBLE);
                String tag = (String) mSuccessView.getChildAt(0).getTag();
                if (!TextUtils.equals(tag, "title") && null != title) {
                    removeView(title);
                    mSuccessView.addView(title, 0);
                }
                if (mLoadingView != null) {
                    mLoadingView.setVisibility(GONE);
                }

                if (mNetworkErrorView != null) {
                    mNetworkErrorView.setVisibility(GONE);
                }

                if (mNetworkErrorView != null) {
                    mNetworkErrorView.setVisibility(GONE);
                }

                break;
            case Loading:
                mSuccessView.setVisibility(GONE);
                if (mEmptyView != null) {
                    mEmptyView.setVisibility(GONE);
                }

                if (mNetworkErrorView != null) {
                    mNetworkErrorView.setVisibility(GONE);
                }
                if (mLoadingView == null) {
                    ViewStub viewStub = (ViewStub) findViewById(R.id.vs_loading);
                    mLoadingView = viewStub.inflate();
                    mLoadingProgress = (ProgressBar) mLoadingView.findViewById(R.id.loading_progress);
                    mLoadingText = (TextView) mLoadingView.findViewById(R.id.loading_text);
                } else {
                    mLoadingView.setVisibility(VISIBLE);
                }
                mLoadingProgress.setVisibility(View.VISIBLE);
                mLoadingText.setText("正在加载");
                break;
            case Empty:
                mSuccessView.setVisibility(GONE);
                if (mLoadingView != null) {
                    mLoadingView.setVisibility(GONE);
                }

                if (mNetworkErrorView != null) {
                    mNetworkErrorView.setVisibility(GONE);
                }

                if (mEmptyView == null) {
                    ViewStub viewStub = (ViewStub) findViewById(R.id.vs_end);
                    mEmptyView = viewStub.inflate();
                    mEmptyView.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            loadindData();
                        }
                    });
                } else {
                    mEmptyView.setVisibility(VISIBLE);
                }
                break;
            case NetWorkError:
                mSuccessView.setVisibility(GONE);
                if (mLoadingView != null) {
                    mLoadingView.setVisibility(GONE);
                }

                if (mEmptyView != null) {
                    mEmptyView.setVisibility(GONE);
                }

                if (mNetworkErrorView == null) {
                    ViewStub viewStub = (ViewStub) findViewById(R.id.vs_error);
                    mNetworkErrorView = viewStub.inflate();
                    mNetworkErrorView.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            loadindData();
                        }
                    });

                } else {
                    mNetworkErrorView.setVisibility(VISIBLE);
                }
                break;
            case Error:
                mSuccessView.setVisibility(GONE);
                if (mLoadingView != null) {
                    mLoadingView.setVisibility(GONE);
                }

                if (mEmptyView != null) {
                    mEmptyView.setVisibility(GONE);
                }

                if (mNetworkErrorView == null) {
                    ViewStub viewStub = (ViewStub) findViewById(R.id.vs_error);
                    mNetworkErrorView = viewStub.inflate();
                    mNetworkErrorView.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            loadindData();
                        }
                    });

                } else {
                    mNetworkErrorView.setVisibility(VISIBLE);
                }
                break;
            default:
                break;
        }
    }

    public enum State {
        Succeed, Error,Empty, Loading, NetWorkError
    }

}
