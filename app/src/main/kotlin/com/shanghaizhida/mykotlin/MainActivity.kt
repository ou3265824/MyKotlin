package com.shanghaizhida.mykotlin

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.lzy.okgo.model.HttpParams
import com.olq.baseframe.loader.OkgoLoader
import com.olq.baseframe.loader.call.GsonCallBack
import com.olq.baseframe.utils.LogUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->


//            startActivity(Intent(application,LoginActivity::class.java))
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            val params=HttpParams()
            params.put("username","admin")
            params.put("password",123456)
            OkgoLoader.sendByPost("http://192.168.3.207:8080/ssm/user/login",null,params,object : GsonCallBack<String>(){
                override fun onSuccess(t: String) {
                    //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    LogUtils.e("成功："+t.toString())
                }


                override fun onError(error: String) {
                    //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }
            })


//            OkgoLoader.sendByGet("http://192.168.3.207:8080/ssm/user/select?username=a",null,null,object :GsonCallBack<List<UserBean>>(){
//                override fun onError(error: String) {
//                    //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                }
//
//                override fun onSuccess(t: List<UserBean>) {
//                    //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                    LogUtils.e("成功了："+t.toString())
//                }
//
//            })

        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

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
