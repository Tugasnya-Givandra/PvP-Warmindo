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

class DetailMakananAdapter(list: ArrayList<Map<String, Any>>?) : RecyclerView.Adapter<DetailMakananAdapter.ListViewHolder>() {
        private var listTransaction: ArrayList<Map<String, Any>>?

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

//    {
//        "id": 1,
//        "idtransaksi": 1,
//        "idmenu": 1,
//        "namamenu": "Mie Dog Dog",
//        "harga": "10000",
//        "jumlah": "2",
//        "subtotal": "20000",
//        "status": "aktif",
//        "created_at": "2023-12-08T15:56:58.000000Z",
//        "updated_at": "2023-12-08T15:56:58.000000Z",
//        "kategori": "Makanan",
//        "gambar": "https://cdn.discordapp.com/attachments/1179985192346206278/1180183339014037554/photo.jpg"
//    }

        override fun onBindViewHolder(holder: DetailMakananAdapter.ListViewHolder, position: Int) {
            val transactionDetails = listTransaction!![position]
            val gambar = ContextCompat.getDrawable(holder.itemView.context, R.drawable.home)
            Glide.with(holder.itemView.context)
                .load(transactionDetails["gambar"])
                .into(holder.detail_gambar_makanan!!)

            var amount_string = if (transactionDetails["jumlah"] == 1) "${transactionDetails["jumlah"]}x " else ""
            holder.detail_nama_makanan!!.text      = "${amount_string}${transactionDetails["namamenu"]}"
            holder.detail_harga_makanan!!.text     = transactionDetails["subtotal"].toString()

            holder.itemView.setOnClickListener {
                Toast.makeText(
                    holder.itemView.context,
                    "Wareg", Toast.LENGTH_SHORT
                ).show()
            }
        }

        class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            public var detail_gambar_makanan: ImageView? = itemView.findViewById(R.id.Detail_gambar_makanan)
            public var detail_nama_makanan: TextView = itemView.findViewById(R.id.Detail_nama_makanan)
            public var detail_harga_makanan: TextView = itemView.findViewById(R.id.Detail_harga_makanan)
            public var rootdetail: CardView = itemView.findViewById(R.id.root_detail)

        }

        override fun getItemCount(): Int {
            return listTransaction!!.size
        }
}