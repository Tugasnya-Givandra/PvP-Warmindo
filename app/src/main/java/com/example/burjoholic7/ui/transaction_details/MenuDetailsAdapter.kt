package com.example.burjoholic7.ui.transaction_details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.burjoholic7.R
import com.example.burjoholic7.api.Transaksi.Transaksi

class MenuDetailsAdapter(list: ArrayList<Transaksi>?) : RecyclerView.Adapter<MenuDetailsAdapter.ListViewHolder>() {
        private var listTransaction: ArrayList<Transaksi>?

        init {
            listTransaction = list
        }

        //
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
            val view: View = LayoutInflater.from(parent.context).inflate(
                R.layout.recycle_transaction_summary,
                parent, false
            )
            return ListViewHolder(view)
        }

        override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
            val transaction = listTransaction!![position]

            holder.tvIdTransaksi.text   = transaction.id.toString()
            holder.tvValBanyakPesanan.text = "3"
            holder.tvValMeja.text          = transaction.kodemeja
            holder.tvValLamaMenunggu.text  = "3 Menit"
            holder.tvValStatus.text       = transaction.status

            holder.root.setOnClickListener() {
                Toast.makeText(
                    holder.itemView.context,
                    "Selected ${transaction.id.toString()}", Toast.LENGTH_SHORT
                ).show()
            }
        }

        class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            public var tvIdTransaksi:        TextView = itemView.findViewById(R.id.idTransaksi)
            public var tvValBanyakPesanan:   TextView = itemView.findViewById(R.id.valBanyakPesanan)
            public var tvValMeja:            TextView = itemView.findViewById(R.id.valMeja)
            public var tvValLamaMenunggu:    TextView = itemView.findViewById(R.id.valLamaMenunggu)
            public var tvValStatus:          TextView = itemView.findViewById(R.id.valStatus)
            public var tvLabelBanyakPesanan: TextView = itemView.findViewById(R.id.labelBanyakPesanan)
            public var tvLabelMeja:          TextView = itemView.findViewById(R.id.labelMeja)
            public var tvLabelLamaMenunggu:  TextView = itemView.findViewById(R.id.labelLamaMenunggu)
            public var tvLabelStatus:        TextView = itemView.findViewById(R.id.labelStatus)
            public var root:                 CardView = itemView.findViewById(R.id.root)

            init {
                tvLabelBanyakPesanan.text = "Banyak pesanan:"
                tvLabelMeja.text = "Meja"
                tvLabelLamaMenunggu.text = "Lama Menunggu"
                tvLabelStatus.text = "Status"
            }
        }

    override fun getItemCount(): Int {
        return listTransaction!!.size
    }
}