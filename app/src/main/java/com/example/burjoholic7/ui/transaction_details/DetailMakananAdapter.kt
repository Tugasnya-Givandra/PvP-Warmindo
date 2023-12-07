package com.example.burjoholic7.ui.transaction_details
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.burjoholic7.R
import com.example.burjoholic7.api.Transaksi.Transaksi
import com.bumptech.glide.Glide

class DetailMakananAdapter(list: ArrayList<Transaksi>?) : RecyclerView.Adapter<DetailMakananAdapter.ListViewHolder>() {
    private var listTransaction: ArrayList<Transaksi>?

    init {
        listTransaction = list
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.recycle_detailmakanan,
            parent, false
        )
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailMakananAdapter.ListViewHolder, position: Int) {
        val transaction = listTransaction!![position]
        val gambar = ContextCompat.getDrawable(holder.itemView.context, R.drawable.home)
        Glide.with(holder.itemView.context)
            .load(gambar)
            .into(holder.Detail_gambar_makanan!!)
        holder.Detail_nama_makanan!!.text      = transaction.namapelanggan
        holder.Detail_harga_makanan!!.text     = transaction.total

        holder.itemView.setOnClickListener {
            fun onClick(view: View?) {
                Toast.makeText(
                    holder.itemView.context,
                    "Wareg", Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        public var Detail_gambar_makanan: ImageView? = null
        public var Detail_nama_makanan: TextView = itemView.findViewById(R.id.Detail_nama_makanan)
        public var Detail_harga_makanan: TextView = itemView.findViewById(R.id.Detail_harga_makanan)
        public var root_detail: CardView = itemView.findViewById(R.id.root_detail)

    }

    override fun getItemCount(): Int {
        return listTransaction!!.size
    }
}