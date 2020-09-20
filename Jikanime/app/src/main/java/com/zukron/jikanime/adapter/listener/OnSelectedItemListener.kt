package com.zukron.jikanime.adapter.listener

/**
 * Project name is Jikanime
 * Created by Zukron Alviandy R on 9/12/2020
 * Contact me if any issues on zukronalviandy@gmail.com
 */
interface OnSelectedItemListener {
    fun onDetailIdItem(id: Int?, type: String)

    fun onDetailUrlItem(url: String?)
}