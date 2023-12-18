package com.example.burjoholic7.ui.transaction_add

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.burjoholic7.R


class ListMenuAddAdapter(fragment: Fragment, list: ArrayList<MutableMap<String, Any>>) : RecyclerView.Adapter<ListMenuAddAdapter.ListViewHolder>() {
    public var listMenu: ArrayList<MutableMap<String, Any>>
    private var totalSum: Int = 0
    private var frag: Fragment
    private var totalSumListener: TotalSumListener? = null
    init {
        listMenu = list
        frag = fragment
    }
    interface TotalSumListener {
        fun onTotalSumCalculated(totalSum: Int)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.activity_list_menu,
            parent, false
        )

        return ListViewHolder(frag,view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val menu = listMenu!![position]

        val gambar = ContextCompat.getDrawable(holder.itemView.context, R.drawable.home)
        Glide.with(holder.itemView.context)
            .load(menu["gambar"].toString().toInt())
            .into(holder.detail_gambar_makanan!!)

        holder.detail_nama_makanan!!.text      = menu["namamenu"].toString()
        holder.detail_harga_makanan!!.text     = menu["harga"].toString()


//        val subtotal = menu["harga"] as? Int ?: 0
//        val itemTotal = subtotal * jumlah

        holder.itemView.findViewById<Button>(R.id.plus).setOnClickListener {
            menu["jumlah"] = menu["jumlah"].toString().toInt() + 1
//            totalSum += itemTotal
            totalSumListener?.onTotalSumCalculated(totalSum)
        }

        holder.itemView.findViewById<Button>(R.id.minus).setOnClickListener {
            val jumlah = menu["jumlah"].toString().toInt() - 1
            menu["jumlah"] = jumlah
//            totalSum -= itemTotal

            if (jumlah == 0) {
                listMenu.removeAt(position)
                this.notifyItemRemoved(position)
            }

            totalSumListener?.onTotalSumCalculated(totalSum)
        }

        holder.itemView.setOnClickListener {
            Toast.makeText(
                holder.itemView.context,
                "Wareg", Toast.LENGTH_SHORT
            ).show()
        }
//        holder.jumlah!!.text = jumlah.toString()
    }
    class ListViewHolder(fragment: Fragment, itemView: View) : RecyclerView.ViewHolder(itemView) {
        var detail_gambar_makanan: ImageView? = itemView.findViewById(R.id.Detail_gambar_makanan)
        var detail_nama_makanan: TextView = itemView.findViewById(R.id.Detail_nama_makanan)
        var detail_harga_makanan: TextView = itemView.findViewById(R.id.Detail_harga_makanan)
        var jumlah: TextView = itemView.findViewById(R.id.jumlah)
        var rootdetail: CardView = itemView.findViewById(R.id.root_detail)
    }

    override fun getItemCount(): Int {
        return listMenu!!.size
    }
}