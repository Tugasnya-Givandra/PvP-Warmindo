package com.example.burjoholic7.ui.transaction_add

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.android.material.R


class IconTextViewAdapter(ctx: Context, resource_id: Int, items: ArrayList<Any>) :
    ArrayAdapter<Any>(ctx, resource_id, items) {

    private var ctx: Context

    init {
        this.ctx = ctx
    }
    /*private view holder class*/
    private class ViewHolder {
        var imageView: ImageView? = null
        var txtTitle: TextView? = null
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        var holder: ViewHolder? = null
        val rowItem = getItem(position) as Map<String, String>
        val mInflater = ctx.getSystemService(Activity.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.abc_activity_chooser_view_list_item, null)
            holder = ViewHolder()
            holder.txtTitle = convertView.findViewById<View>(R.id.title) as TextView?
            holder.imageView = convertView.findViewById<View>(R.id.icon) as ImageView?
            convertView.setTag(holder)
        } else holder = convertView.tag as ViewHolder

        holder.txtTitle?.text = rowItem["namamenu"]

        Glide.with(parent)
            .load(rowItem["gambar"])
            .into(holder.imageView!!)

        return convertView!!
    }
}