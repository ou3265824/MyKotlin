//package com.shanghaizhida.mykotlin;
//
//import android.annotation.SuppressLint;
//import android.annotation.TargetApi;
//import android.content.ClipData;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.net.Uri;
//import android.os.Build;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.view.KeyEvent;
//import android.view.View;
//import android.view.ViewGroup;
//import android.webkit.DownloadListener;
//import android.webkit.ValueCallback;
//import android.webkit.WebChromeClient;
//import android.webkit.WebSettings;
//import android.webkit.WebView;
//import android.webkit.WebViewClient;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.olq.baseframe.base.ui.BaseActivity;
//import com.shanghaizhida.newmtrader.utils.LogUtils;
//import com.shanghaizhida.newmtrader.utils.SelectPhotoUtil;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.OnClick;
//
//import static android.view.View.GONE;
//import static android.view.View.VISIBLE;
//
///**
// * 准备资料界面activity
// */
//@SuppressLint("SetJavaScriptEnabled")
//public class WebViewActivity extends BaseActivity {
////    @BindView(com.olq.baseframe.R.id.iv_actionbar_left)
////    ImageView ivActionbarLeft;
////    @BindView(com.olq.baseframe.R.id.tv_actionbar_title)
////    TextView tvActionbarTitle;
//    @BindView(R.id.progresswebview)
//    ProgressWebView webview;
//
//    private String mTitle = "";
//
//    private String mUrl = "";
//
//    private boolean videoFlag = false;
//    private ValueCallback<Uri> mUploadMessage;
//    private ValueCallback<Uri[]> mUploadCallbackAboveL;
//    private SelectPhotoUtil selectPhotoUtil;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(com.olq.baseframe.R.layout.activity_webview);
//        ButterKnife.bind(this);
//        initData();
//        initView();
//    }
//
//    private void initData() {
//        Intent intent = getIntent();
//        if (null == intent) {
//            return;
//        }
//        mTitle = intent.getStringExtra("WebActivityTitle");
//        mUrl = intent.getStringExtra("url");
//
//        selectPhotoUtil = new SelectPhotoUtil(this,this);
//    }
//
//    private void initView() {
////        ivActionbarLeft.setVisibility(VISIBLE);
////        if (!TextUtils.isEmpty(mTitle)) {
////            tvActionbarTitle.setVisibility(VISIBLE);
////            tvActionbarTitle.setText(mTitle);
////        } else {
////            tvActionbarTitle.setVisibility(GONE);
////        }
////        webview = (ProgressWebView) findViewById();
//        WebSettings webSettings = webview.getSettings();
//        // 设置WebView属性，能够执行Javascript脚本
//        webSettings.setJavaScriptEnabled(true);
//        // 支持通过JS打开新窗口
//        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
//        // 设置可以访问文件
//        webSettings.setAllowFileAccess(true);
//        // 是否可访问Content Provider的资源，默认值 true
//        webSettings.setAllowContentAccess(true);
//        // 设置支持缩放
//        webSettings.setBuiltInZoomControls(true);
//        // 支持缩放
//        webSettings.setSupportZoom(true);
//        webSettings.setDomStorageEnabled(true);
//        webSettings.setUseWideViewPort(true);
//        webSettings.setLoadWithOverviewMode(true);
//        webSettings.setDefaultTextEncodingName("UTF-8");
//        webSettings.setBlockNetworkImage(false); // 解决图片不显示
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
//            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
//        }
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//            // 是否允许通过file url加载的Javascript读取本地文件，默认值 false
//            webSettings.setAllowFileAccessFromFileURLs(false);
//            // 是否允许通过file url加载的Javascript读取全部资源(包括文件,http,https)，默认值 false
//            webSettings.setAllowUniversalAccessFromFileURLs(false);
//        }
//
//        //辅助WebView设置处理关于页面跳转，页面请求等操作
//        webview.setWebViewClient(new MyWebViewClient());
//        //辅助WebView处理图片上传操作
//        webview.setWebChromeClient(new MyChromeWebClient());
//
//        webview.setLayerType(View.LAYER_TYPE_HARDWARE, null);
//
//        webview.setDownloadListener(new DownloadListener() {
//            @Override
//            public void onDownloadStart(String url, String userAgent
//                    , String contentDisposition, String mimetype, long contentLength) {
//                if (url != null && url.startsWith("http://")) {
//                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
//                }
//            }
//        });
//
//        // 加载需要显示的网页
//        if (TextUtils.isEmpty(mUrl)) {
//            webview.loadUrl("https://www.directaccess.com.hk/");
//
//        } else {
//            webview.loadUrl(mUrl);
//        }
//
//    }
//
////    @OnClick({R.id.iv_actionbar_left})
////    public void onClick(View view) {
////        switch (view.getId()) {
////            case com.olq.baseframe.R.id.iv_actionbar_left:
////                finish();
////                break;
////            default:
////                break;
////        }
////    }
//
//    @Override
//    // 设置回退
//    // 覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if ((keyCode == KeyEvent.KEYCODE_BACK) && webview.canGoBack()) {
//            webview.goBack(); // goBack()表示返回WebView的上一页面
//            return true;
//        }
//        finish();// 结束退出程序
//        return false;
//    }
//
//    // Web视图
//    private class webViewClient extends WebViewClient {
//        @Override
//        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//            view.loadUrl(url);
//            return true;
//        }
//
//        @Override
//        public void onPageStarted(WebView view, String url, Bitmap favicon) {
//            super.onPageStarted(view, url, favicon);
//        }
//
//        @Override
//        public void onPageFinished(WebView view, String url) {
//            super.onPageFinished(view, url);
//            if (null == webview) {
////                LogUtils.d("WebViewActivity", "webView==null");
//                return;
//            }
//            if (TextUtils.isEmpty(mTitle) && !TextUtils.isEmpty(webview.getmTitle())) {
//                tvActionbarTitle.setVisibility(VISIBLE);
//                tvActionbarTitle.setText(webview.getmTitle());
//            }
//        }
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        webview.onPause();
//    }
//
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        webview.onResume();
//    }
//
//    @Override
//    protected void onDestroy() {
//        if (webview != null) {
//            webview.loadDataWithBaseURL(null, "", "text/html"
//                    , "utf-8", null);
//            webview.clearHistory();
//            ((ViewGroup) webview.getParent()).removeView(webview);
//            webview.destroy();
//            webview = null;
//            selectPhotoUtil = null;
//        }
//        super.onDestroy();
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if(requestCode == selectPhotoUtil.CAMERARESULT){
//            if (null == mUploadMessage && null == mUploadCallbackAboveL)
//                return;
//            Uri result = data == null || resultCode != RESULT_OK ? null : data.getData();
//            if (mUploadCallbackAboveL != null) {
//                onActivityResultAboveL(requestCode, resultCode, data);
//            } else if (mUploadMessage != null) {
//                if(result == null)
//                    result = selectPhotoUtil.headUri;
//                mUploadMessage.onReceiveValue(result);
//                mUploadMessage = null;
//            }
//        }else if (requestCode == selectPhotoUtil.VIDEO_REQUEST) {
//            if (null == mUploadMessage && null == mUploadCallbackAboveL) return;
//
//            Uri result = data == null || resultCode != RESULT_OK ? null : data.getData();
//            if (mUploadCallbackAboveL != null) {
//                if (resultCode == RESULT_OK) {
//                    mUploadCallbackAboveL.onReceiveValue(new Uri[]{result});
//                    mUploadCallbackAboveL = null;
//                } else {
//                    mUploadCallbackAboveL.onReceiveValue(new Uri[]{});
//                    mUploadCallbackAboveL = null;
//                }
//
//            } else if (mUploadMessage != null) {
//                if (resultCode == RESULT_OK) {
//                    mUploadMessage.onReceiveValue(result);
//                    mUploadMessage = null;
//                } else {
//                    mUploadMessage.onReceiveValue(Uri.EMPTY);
//                    mUploadMessage = null;
//                }
//
//            }
//        }
//    }
//
//    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
//    private void onActivityResultAboveL(int requestCode, int resultCode, Intent data) {
//        if (requestCode != selectPhotoUtil.CAMERARESULT || mUploadCallbackAboveL == null) {
//            return;
//        }
//        Uri[] results = null;
//        LogUtils.i("onActivityResultAboveL","data:"+data+"   headUri:"+selectPhotoUtil.headUri);
//        if (resultCode == RESULT_OK) {
//            if (data == null) {
//                results = new Uri[]{selectPhotoUtil.headUri};
//            } else {
//                String dataString = data.getDataString();
//                ClipData clipData = data.getClipData();
//                if (clipData != null) {
//                    results = new Uri[clipData.getItemCount()];
//                    for (int i = 0; i < clipData.getItemCount(); i++) {
//                        ClipData.Item item = clipData.getItemAt(i);
//                        results[i] = item.getUri();
//                    }
//                }
//
//                if (dataString != null)
//                    results = new Uri[]{Uri.parse(dataString)};
//            }
//        }
//        mUploadCallbackAboveL.onReceiveValue(results);
//        mUploadCallbackAboveL = null;
//    }
//
//    //自定义 WebViewClient 辅助WebView设置处理关于页面跳转，页面请求等操作【处理tel协议和视频通讯请求url的拦截转发】
//    private class MyWebViewClient extends WebViewClient {
//        @Override
//        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//            LogUtils.i("shouldOverrideUrlLoading","url:"+url);
//            if (!TextUtils.isEmpty(url)) {
//                videoFlag = url.contains("uploadvideo");
//            }
//            if (url.trim().startsWith("tel")) {//特殊情况tel，调用系统的拨号软件拨号【<a href="tel:1111111111">1111111111</a>】
//                Intent i = new Intent(Intent.ACTION_VIEW);
//                i.setData(Uri.parse(url));
//                startActivity(i);
//            } else {
//                String port = url.substring(url.lastIndexOf(":") + 1, url.lastIndexOf("/"));//尝试要拦截的视频通讯url格式(808端口)：【http://xxxx:808/?roomName】
//                if (port.equals("808")) {//特殊情况【若打开的链接是视频通讯地址格式则调用系统浏览器打开】
//                    Intent i = new Intent(Intent.ACTION_VIEW);
//                    i.setData(Uri.parse(url));
//                    startActivity(i);
//                } else {//其它非特殊情况全部放行
//                    view.loadUrl(url);
//                }
//            }
//            return true;
//        }
//
//        @Override
//        public void onPageStarted(WebView view, String url, Bitmap favicon) {
//            super.onPageStarted(view, url, favicon);
//        }
//
//        @Override
//        public void onPageFinished(WebView view, String url) {
//            super.onPageFinished(view, url);
//            if (null == webview) {
////                LogUtils.d("WebViewActivity", "webView==null");
//                return;
//            }
//            if (TextUtils.isEmpty(mTitle) && !TextUtils.isEmpty(webview.getmTitle())) {
//                tvActionbarTitle.setVisibility(VISIBLE);
//                tvActionbarTitle.setText(webview.getmTitle());
//            }
//        }
//    }
//
//    //自定义 WebChromeClient 辅助WebView处理图片上传操作【<input type=file> 文件上传标签】
//    public class MyChromeWebClient extends WebChromeClient {
//
//        @Override
//        public void onProgressChanged(WebView view, int newProgress) {
//            if (webview != null) {
//                if (newProgress == 100) {
//                    webview.progressbar.setVisibility(GONE);
//                } else {
//                    if (webview.progressbar.getVisibility() == GONE) {
//                        webview.progressbar.setVisibility(VISIBLE);
//                    }
//                    webview.progressbar.setProgress(newProgress);
//                }
//            }
//            super.onProgressChanged(view, newProgress);
//        }
//
//        @Override
//        public void onReceivedTitle(WebView view, String title) {
//            super.onReceivedTitle(view, title);
//            webview.setmTitle(title);
//        }
//
//        // For Android 3.0-
//        public void openFileChooser(ValueCallback<Uri> uploadMsg) {
//            LogUtils.i(TAG, "openFileChoose(ValueCallback<Uri> uploadMsg)");
//            mUploadMessage = uploadMsg;
//            if (videoFlag) {
//                selectPhotoUtil.recordVideo();
//            } else {
//                selectPhotoUtil.takephotos("zd_idcard");
//            }
//
//        }
//
//        // For Android 3.0+
//        public void openFileChooser(ValueCallback uploadMsg, String acceptType) {
//            LogUtils.i(TAG, "openFileChoose( ValueCallback uploadMsg, String acceptType )");
//            mUploadMessage = uploadMsg;
//            if (videoFlag) {
//                selectPhotoUtil.recordVideo();
//            } else {
//                selectPhotoUtil.takephotos("zd_idcard");
//            }
//        }
//
//        //For Android 4.1
//        public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
//            LogUtils.i(TAG, "openFileChoose(ValueCallback<Uri> uploadMsg, String acceptType, String capture)");
//            mUploadMessage = uploadMsg;
//            if (videoFlag) {
//                selectPhotoUtil.recordVideo();
//            } else {
//                selectPhotoUtil.takephotos("zd_idcard");
//            }
//        }
//
//        // For Android 5.0+
//        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
//            LogUtils.i(TAG, "onShowFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture)");
//            mUploadCallbackAboveL = filePathCallback;
//            if (videoFlag) {
//                selectPhotoUtil.recordVideo();
//            } else {
//                selectPhotoUtil.takephotos("zd_idcard");
//            }
//            return true;
//        }
//    }
//
//}
