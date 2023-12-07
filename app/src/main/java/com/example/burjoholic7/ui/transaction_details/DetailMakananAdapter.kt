package com.example.burjoholic7.ui.transaction_details

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.burjoholic7.R
import com.example.burjoholic7.api.Transaksi.Transaksi
import com.bumptech.glide.Glide

class DetailMakananAdapter(fragment: Fragment, list: ArrayList<Transaksi>?) : RecyclerView.Adapter<DetailMakananAdapter.ListViewHolder>() {
    private var listTransaction: ArrayList<Transaksi>?
    private var frag: Fragment

    init {
        listTransaction = list
        frag = fragment
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.recycle_detailmakanan,
            parent, false
        )

        return ListViewHolder(frag, view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val transaction = listTransaction!![position]
        val gambar = ContextCompat.getDrawable(holder.itemView.context, R.drawable.home)
        Glide.with(holder.itemView.context)
            .load(gambar)
            .into(holder.Detail_gambar_makanan!!)
        holder.Detail_nama_makanan.text      = "ayam goreng"
        holder.Detail_harga_makanan.text     = "130 000"

        holder.itemView.setOnClickListener {
            fun onClick(view: View?) {
                Toast.makeText(
                    holder.itemView.context,
                    "Navigating to Transaction Details ${transaction.id.toString()}", Toast.LENGTH_SHORT
                ).show()
                holder.navController.navigate(R.id.navigation_transaction_details)
            }
        }
    }

    class ListViewHolder(fragment: Fragment, itemView: View) : RecyclerView.ViewHolder(itemView) {
        public var navController: NavController = fragment.findNavController()
        public var Detail_gambar_makanan: ImageView? = null
        public var Detail_nama_makanan: TextView = itemView.findViewById(R.id.Detail_nama_makanan)
        public var Detail_harga_makanan: TextView = itemView.findViewById(R.id.Detail_harga_makanan)
        public var root: CardView = itemView.findViewById(R.id.root)

    }

    override fun getItemCount(): Int {
        return listTransaction!!.size
    }
}