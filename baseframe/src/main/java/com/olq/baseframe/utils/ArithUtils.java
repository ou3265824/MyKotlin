package com.olq.baseframe.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

import com.shanghaizhida.octopusbase.R;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by Administrator on 2017/2/28.
 */

public class ArithUtils {

    //默认除法运算精度
    private static final int DEF_DIV_SCALE = 10;
    //这个类不能实例化
    private ArithUtils(){

    }

    /**
     * 提供精确的加法运算。
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和
     */
    public static double add(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }
    /**
     * 提供精确的减法运算。
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */
    public static double sub(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }
    /**
     * 提供精确的乘法运算。
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积
     */
    public static double mul(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到
     * 小数点以后10位，以后的数字四舍五入。
     * @param v1 被除数
     * @param v2 除数
     * @return 两个参数的商
     */
    public static double div(double v1,double v2){
        return div(v1,v2,DEF_DIV_SCALE);
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。
     * @param v1 被除数
     * @param v2 除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double div(double v1,double v2,int scale){
        if(scale<0){
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2,scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 提供精确的小数位四舍五入处理。
     * @param v 需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static double round(double v,int scale){
        if(scale<0){
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one,scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 保留2位小数
     *
     * @param d1
     * @return
     */
    public static String retainTwo(double d1) {
        DecimalFormat df = new DecimalFormat("#########.##");
        return df.format(d1);
    }


    public static void countEarnings(Context context, TextView textView , double d1) {
        countEarnings(context,textView,d1,"%");
    }
    public static void countEarnings(Context context, TextView textView , double d1, String end) {
        String s=retainTwo(d1);
        if (d1>0){
            textView.setTextColor(context.getResources().getColor(R.color.strategy_number));
            textView.setText("+"+s+end);
        }else if (d1==0){
            textView.setTextColor(context.getResources().getColor(R.color.strategy_intro));
            textView.setText(s+end);
        }else{
            textView.setTextColor(context.getResources().getColor(R.color.minus));
            textView.setText(s+end);
        }
    }
    public static void countEarningsColor(Context context, TextView textView , double d1) {
        String s=retainTwo(d1);
        if (d1>0){
            textView.setTextColor(context.getResources().getColor(R.color.strategy_number));
        }else if (d1==0){
            textView.setTextColor(context.getResources().getColor(R.color.strategy_intro));
        }else{
            textView.setTextColor(context.getResources().getColor(R.color.minus));
        }
    }

    /**
     * 修改字体
     * @param context
     * @param textView
     */
    public static void setTypeface(Context context, TextView textView){
        Typeface typeFace = Typeface.createFromAsset(context.getAssets(), "FZH4FW.TTF");
        textView.setTypeface(typeFace);
    }

    /**
     * 格式化值
     * 科学计数法转正常
     * @param d
     * @return
     */
    public static String unitTenNumber(Double d){
//        String s=new DecimalFormat("0.00").format(d);
       String s= NumberFormat.getNumberInstance().format(d);
        return s;
    }

    public static String unitTenFormat(Double d){
        String s=new DecimalFormat("0.00").format(d);
//       String s=NumberFormat.getNumberInstance().format(d);
        return s;
    }

    public static void countEarningsUnit(Context context, TextView textView , double d1, String end) {
        if (d1>0){
            textView.setTextColor(context.getResources().getColor(R.color.strategy_number));
            textView.setText("+"+unitTenNumber(d1)+end);
        }else if (d1==0){
            textView.setTextColor(context.getResources().getColor(R.color.strategy_intro));
            textView.setText(unitTenNumber(d1)+end);
        }else{
            textView.setTextColor(context.getResources().getColor(R.color.minus));
            textView.setText(unitTenNumber(d1)+end);
        }
    }

    public static void getType(Context context, TextView textView, String type){
        if (type.equals("1")){
            textView.setText("买");
            textView.setTextColor(context.getResources().getColor(R.color.bg_bug));
        }else{
            textView.setTextColor(context.getResources().getColor(R.color.bg_sell));
            textView.setText("卖");
        }
    }

}
