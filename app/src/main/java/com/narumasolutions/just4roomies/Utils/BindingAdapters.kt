package com.narumasolutions.just4roomies.Utils

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.databinding.BindingAdapter
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.narumasolutions.just4roomies.UI.Container.Fragments.SerachRoomies.RoomiesDeck_Adapter
import com.narumasolutions.just4roomies.UI.Container.Fragments.SerachRoomies.Roomies_Adapter
import com.yuyakaido.android.cardstackview.CardStackView

@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View, visibility: MutableLiveData<Int>?) {

    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && visibility != null) {
        visibility.observe(parentActivity, Observer { value -> view.visibility = value ?: View.VISIBLE })
    }
}

@BindingAdapter("mutableText")
fun setMutableText(view: TextView, text: MutableLiveData<String>?) {

    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value -> view.text = value ?: "" })
    }
}

@BindingAdapter("mutableImage")
fun setMutableImage(view: ImageView, image: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && image != null) {
        image.observe(parentActivity, Observer { value -> if (value != null) view.setImageResource(value) })
    }

}

