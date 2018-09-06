package com.shanghaizhida.mykotlin.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.AccelerateInterpolator
import com.alibaba.android.arouter.facade.annotation.Route
import com.lzy.okgo.model.HttpParams
import com.olq.baseframe.loader.OkgoLoader
import com.olq.baseframe.loader.call.GsonCallBack
import com.olq.baseframe.utils.LogUtils
import com.shanghaizhida.mykotlin.R
import com.shanghaizhida.mykotlin.base.InitActivity
import com.shanghaizhida.mykotlin.bean.UserBean
import com.shanghaizhida.mykotlin.config.RouterConfig
import com.zhy.changeskin.SkinManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*





@Route(path = RouterConfig.MAIN)
class MainActivity : InitActivity(), NavigationView.OnNavigationItemSelectedListener {
    override fun getLayout(): Int {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return R.layout.activity_main
    }

    override fun onCreate() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        setSupportActionBar(toolbar)
        fab.setOnClickListener { view ->


            //            startActivity(Intent(application,LoginActivity::class.java))
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            val params=HttpParams()
            params.put("username","admin")
            params.put("password",123456)
//            OkgoLoader.sendByPost("http://192.168.3.207:8080/ssm/user/login",null,params,object : GsonCallBack<UserBean>(){
//                override fun onSuccess(t: UserBean) {
//                    //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                    LogUtils.e("成功："+t.toString())
//                }
//
//
//                override fun onError(error: String) {
//                    //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                }
//            })


            OkgoLoader.sendByGet("http://192.168.3.207:8080/ssm/user/select?username=a",null,null,object : GsonCallBack<List<UserBean>>(){
                override fun onError(error: String) {
                    //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onSuccess(t: List<UserBean>) {
                    //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    LogUtils.e("成功了："+t.toString())
                }

            })

        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        onTab()
    }




    fun onTab(){
        text.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)

            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                    text, "show")

            ActivityCompat.startActivity(this, intent, options.toBundle())
        }
        rb_tab1.setOnClickListener {
            onArim(rb_tab1)
            SkinManager.getInstance().changeSkin("lv");
        }
        rb_tab2.setOnClickListener {
            onArim(rb_tab1)
            SkinManager.getInstance().changeSkin("gren");
        }
        rb_tab3.setOnClickListener {
            onArim(rb_tab1)
            SkinManager.getInstance().removeAnySkin()
        }
        rb_tab4.setOnClickListener {
            onArim(rb_tab1)
        }
        iv_tab.setOnClickListener {
            onArim(iv_tab)
        }
    }

    @SuppressLint("ResourceAsColor")
    fun onArim(view:View){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //  大于等于24即为5.0及以上执行内容
//            val animator = ViewAnimationUtils.createCircularReveal(view,
//                    view.getWidth() / 2,
//                    view.getHeight() / 2,
//                    view.getWidth().toFloat(), 0f)
//            animator.interpolator = AccelerateDecelerateInterpolator()
//            animator.duration = 1000
//            animator.start()


            val animator = ViewAnimationUtils.createCircularReveal(
                    view,
                    0,
                    0,
                    0f,
                    Math.hypot(view.getWidth().toDouble(), view.getHeight().toDouble()).toFloat())
            animator.interpolator = AccelerateInterpolator()
            animator.duration = 1000
            animator.start()
        } else {

        }

//        val anim=ValueAnimator()
//        anim.setIntValues(privateColor,color)
//        anim.setEvaluator(ArgbEvaluator())
//        anim.addUpdateListener {
//            val color=it.getAnimatedValue() as Int
////            iv_tab.setColorFilter(color)
////            rb_tab1.setBackgroundColor(R.color.colorAccent)
//        }
//        anim.setDuration(150)
//        anim.start()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
