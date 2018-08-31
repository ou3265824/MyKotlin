//package com.shanghaizhida.mykotlin;
//
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.net.Uri;
//import android.os.Build;
//import android.provider.MediaStore;
//import android.support.v4.content.FileProvider;
//
//import com.facebook.drawee.backends.pipeline.Fresco;
//import com.facebook.drawee.view.SimpleDraweeView;
//import com.facebook.imagepipeline.core.ImagePipeline;
//import com.shanghaizhida.newmtrader.activity.BaseActivity;
//import com.shanghaizhida.newmtrader.appbase.Global;
//import com.shanghaizhida.newmtrader.fragment.BaseFragment;
//import com.shanghaizhida.newmtrader.http.BaseCallback;
//import com.shanghaizhida.newmtrader.http.HttpAPI;
//import com.shanghaizhida.newmtrader.http.RetrofitGenerator;
//import com.shanghaizhida.newmtrader.http.entity.UserImageEntity;
//import com.shanghaizhida.newmtrader.http.jsonbean.UserImageBean;
//import com.shanghaizhida.newmtrader.utils.encrypt.ImgBase64;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//
//import retrofit2.Call;
//
///**
// * Created by Administrator on 2018/1/8.
// */
//
//public class SelectPhotoUtil {
//    private Context context;
//    private BaseFragment fragment;
//    public final int CAMERARESULT = 1;
//    public final int CUTDOWNRESULT = 2;
//    public final int PHOTORESULT = 3;
//    public final int VIDEO_REQUEST = 4;
//    private final String IMAGE_UNSPECIFIED = "image/*";
//    private IoUtils ioUtils;
//    public Uri headUri;
//    private Uri zoomedUri;
//    private HttpAPI httpAPI;
//    private Call<UserImageBean> userImageCall;
//    private SimpleDraweeView ivLoginHead;
//
//    private BaseActivity activity;
//
//    public SelectPhotoUtil(Context context, BaseFragment fragment, SimpleDraweeView ivLoginHead){
//        this.context = context;
//        this.fragment = fragment;
//        ioUtils = new IoUtils();
//        httpAPI = RetrofitGenerator.generator(HttpAPI.class);
//        this.ivLoginHead = ivLoginHead;
//    }
//
//    public SelectPhotoUtil(Context context, BaseActivity activity){
//        this.context = context;
//        this.activity = activity;
//        ioUtils = new IoUtils();
//    }
//
//    public void selectFromPhoto() {
//        Intent intent = new Intent(Intent.ACTION_PICK, null);
//        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, IMAGE_UNSPECIFIED);
//        fragment.startActivityForResult(intent, PHOTORESULT);
//    }
//
//    /**
//     * 调用系统照相机拍照作为头像
//     */
//    public void takephotos(String imageStr) {
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        File file = new File( IoUtils.getZdPath() + "/"+ imageStr+ System.currentTimeMillis() + ".jpg");
//
//        if(Build.VERSION.SDK_INT >= 24){  //7.0及以上
//            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
//            headUri = FileProvider.getUriForFile(context,"com.shanghaizhida.newmtrader.fileProvider",file);
//        }else{   //7.0以下
//            headUri = Uri.fromFile(file);
//        }
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, headUri);
//        if(fragment != null) {
//            fragment.startActivityForResult(intent, CAMERARESULT);
//        }
//        else if(activity != null)
//            activity.startActivityForResult(intent,CAMERARESULT);
//    }
//
//    /**
//     * 录像
//     */
//    public void recordVideo() {
//        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
//        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
//        //限制时长
//        intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 3);
//        //开启摄像机
//        if(activity != null)
//            activity.startActivityForResult(intent, VIDEO_REQUEST);
//    }
//
//    public void startPhotoZoom(String zoomStr) {
//        // 裁剪图片意图
//        Intent intent = new Intent("com.android.camera.action.CROP");
//        intent.setDataAndType(headUri, IMAGE_UNSPECIFIED);
//        intent.putExtra("crop", "true");
//        // 裁剪框的比例，1：1
//        intent.putExtra("aspectX", 1);
//        intent.putExtra("aspectY", 1);
//        // 裁剪后输出图片的尺寸大小
//        intent.putExtra("outputX", 500);
//        intent.putExtra("outputY", 500);
//        // 图片格式
//        intent.putExtra("outputFormat", "JPEG");
//        intent.putExtra("noFaceDetection", true);// 取消人脸识别
//        intent.putExtra("return-data", false);// true:不返回uri，false：返回uri
//        //设置传递返回uri，而不是直接的bitmap,以防止返回的intent过大出异常
//        File file = new File(IoUtils.getZdPath() + "/"+zoomStr+ System.currentTimeMillis()+ ".jpg");
//        if(Build.VERSION.SDK_INT >= 24){  //7.0及以上
//            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
////            zoomedUri = FileProvider.getUriForFile(context,"com.shanghaizhida.newmtrader.fileProvider",file);
//        }else{   //7.0以下
//        }
//        zoomedUri = Uri.fromFile(file);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, zoomedUri);
//        fragment.startActivityForResult(intent, CUTDOWNRESULT);
//    }
//
//    public void afterZoomPhoto(){
//        Bitmap photo = null;
//        try {
//            photo = BitmapFactory.decodeStream(((MainActivity)context).getContentResolver().openInputStream(zoomedUri));
//            if (photo != null) {
////                ByteArrayOutputStream stream = new ByteArrayOutputStream();
////                photo.compress(Bitmap.CompressFormat.JPEG, 75, stream);// (0 – 100)压缩文件
//                //这里 需要存到本地 再已file 表单的方式上传
//                //将图片保存到本地
//                File file = ioUtils.saveBitmapFile(photo);  //将图片保存到本地
//                upLoadHeadFile(file);
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void upLoadHeadFile(File file) {
//        if (!file.exists() || file.isDirectory()) {
//            ToastUtil.showShort(getString(R.string.tab3part_changeicon_nofail));
//            return;
//        }
//        // 将图片文件转换成字符串
//        String imageStr = ImgBase64.GetImageStr(ioUtils.getZdPath()+"/",file.getName()).replace("+","%2B");
//
//        UserImageEntity entity = new UserImageEntity();
//        entity.setMobile(Global.accountInfo.getScUser().getMobile());
//        entity.setImg(imageStr);
//        userImageCall = httpAPI.upLoadUserIcon(Global.accountInfo.getToken(),entity);
//        userImageCall.enqueue(new BaseCallback<UserImageBean>() {
//            @Override
//            public void onSuccess(UserImageBean data) {
//                if (data.isResult()){
//                    ToastUtil.showShort(SelectPhotoUtil.this.getString(R.string.tab3part_changeicon_setsuccess));
//                    //清理fresco的图片缓存，不然会换不了头像
//                    ImagePipeline imagePipeline = Fresco.getImagePipeline();
//                    imagePipeline.clearCaches();
//                    setHeadIcon(data.getData());
//                }else{
//                    ToastUtil.showShort(SelectPhotoUtil.this.getString(R.string.tab3part_changeicon_setfail));
//                }
//            }
//
//            @Override
//            public void onFail(String message) {
//                ToastUtil.showShort(message);
//            }
//        });
//    }
//
//    public void setHeadIcon(UserImageBean.DataBean userBean){
//        if(userBean != null && !userBean.getImage().equals("") && userBean.getImage() != null){
//            Uri uri = Uri.parse(userBean.getImage());
//            ivLoginHead.setImageURI(uri);
//        }
//    }
//
//    private String getString(int str){
//        return context.getString(str);
//    }
//}
//
