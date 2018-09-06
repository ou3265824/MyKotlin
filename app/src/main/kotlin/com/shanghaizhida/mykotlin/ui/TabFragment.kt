package com.shanghaizhida.mykotlin.ui

import com.shanghaizhida.mykotlin.R
import com.shanghaizhida.mykotlin.base.InitFragment


class TabFragment : InitFragment() {



    companion object {
        var fragment: TabFragment? = null
        fun newInstance(): TabFragment {
            if (fragment == null) {
                fragment = TabFragment()
            }
            return fragment as TabFragment
        }
    }

    override fun onLoadData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onInVisibe() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun init() {
       // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//        showStateNetWorkError()
    }

    override fun getLayoutId(): Int {
       // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return R.layout.fragment_tab
    }


}
