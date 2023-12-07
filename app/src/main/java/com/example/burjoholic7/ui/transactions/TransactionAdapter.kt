package com.example.burjoholic7.ui.transactions

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.burjoholic7.R
import com.example.burjoholic7.api.Transaksi.Transaksi
import com.example.burjoholic7.ui.transaction_details.DetailTransactionPage
import com.example.burjoholic7.ui.transaction_details.TransactionsDetailsFragment

class TransactionAdapter(fragment: Fragment, list: ArrayList<Transaksi>?) : RecyclerView.Adapter<TransactionAdapter.ListViewHolder>() {
        private var listTransaction: ArrayList<Transaksi>?
        private var frag: Fragment

        init {
            listTransaction = list
            frag = fragment
        }

        //
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
            val view: View = LayoutInflater.from(parent.context).inflate(
                R.layout.recycle_transaction_summary,
                parent, false
            )

            return ListViewHolder(frag, view)
        }

        override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
            val transaction = listTransaction!![position]

            holder.tvIdTransaksi.text      = transaction.id.toString()
            holder.tvValIdPelanggan.text      = transaction.idpelanggan.toString()
            holder.tvValNamaPelanggan.text      = transaction.namapelanggan
            holder.tvValBanyakPesanan.text = "3"
            holder.tvValMeja.text          = transaction.kodemeja
            holder.tvValLamaMenunggu.text  = "3 Menit"
            holder.tvValStatus.text        = transaction.status

            holder.itemView.setOnClickListener {
                val detailIntent = Intent(
                    holder.itemView.context,
                    DetailTransactionPage::class.java
                )
                detailIntent.putExtra(DetailTransactionPage.KEY_ID, "W1972103")
                detailIntent.putExtra(DetailTransactionPage.KEY_KODEMEJA, "1234")
                detailIntent.putExtra(DetailTransactionPage.KEY_STATUS, "aktif")
                detailIntent.putExtra(DetailTransactionPage.KEY_IDPELANGGAN, "aktif")
                detailIntent.putExtra(DetailTransactionPage.KEY_NAMAPELANGGAN, "aktif")

                holder.itemView.context.startActivity(detailIntent)
            }
        }

        class ListViewHolder(fragment: Fragment, itemView: View) : RecyclerView.ViewHolder(itemView) {
            public var tvIdTransaksi:        TextView = itemView.findViewById(R.id.idTransaksi)
            public var tvValIdPelanggan:        TextView = itemView.findViewById(R.id.valDetailIdPelanggan)
            public var tvValNamaPelanggan:        TextView = itemView.findViewById(R.id.valDetailNamaPelanggan)
            public var tvValBanyakPesanan:   TextView = itemView.findViewById(R.id.valBanyakPesanan)
            public var tvValMeja:            TextView = itemView.findViewById(R.id.valMeja)
            public var tvValLamaMenunggu:    TextView = itemView.findViewById(R.id.valLamaMenunggu)
            public var tvValStatus:          TextView = itemView.findViewById(R.id.valStatus)
            public var tvLabelNamaPelanggan: TextView = itemView.findViewById(R.id.labelDetailNamaPelanggan)
            public var tvLabelIdPelanggan:   TextView = itemView.findViewById(R.id.labelDetailIdPelanggan)
            public var tvLabelBanyakPesanan: TextView = itemView.findViewById(R.id.labelBanyakPesanan)
            public var tvLabelMeja:          TextView = itemView.findViewById(R.id.labelMeja)
            public var tvLabelLamaMenunggu:  TextView = itemView.findViewById(R.id.labelLamaMenunggu)
            public var tvLabelStatus:        TextView = itemView.findViewById(R.id.labelStatus)
            public var root:                 CardView = itemView.findViewById(R.id.root)
            public var navController:   NavController = fragment.findNavController()

            init {
                tvLabelBanyakPesanan.text = "Banyak pesanan:"
                tvLabelMeja.text = "Meja"
                tvLabelLamaMenunggu.text = "Lama Menunggu"
                tvLabelStatus.text = "Status"
                tvLabelNamaPelanggan.text = "Nama Pelanggan"
                tvLabelIdPelanggan.text = "Id Pelanggan"

            }
        }

    override fun getItemCount(): Int {
        return listTransaction!!.size
    }
}