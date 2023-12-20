package com.example.burjoholic7.ui.transactions

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.burjoholic7.R
import com.example.burjoholic7.api.Transaksi.Transaksi
import com.example.burjoholic7.ui.histories.HistoriesFragment
import com.example.burjoholic7.ui.transaction_details.DetailTransactionPage

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
            holder.tvValBanyakPesanan.text = "3"
            holder.tvIdTransaksi.text      = transaction.id.toString()
            holder.tvValMeja.text          = transaction.kodemeja
            holder.tvValLamaMenunggu.text  = "3 Menit"
            holder.tvValStatus.text        = transaction.status

            holder.itemView.setOnClickListener {
                val detailIntent = Intent(
                    holder.itemView.context,
                    DetailTransactionPage::class.java
                )
                detailIntent.putExtra(DetailTransactionPage.KEY_ID, transaction.id)
                detailIntent.putExtra(DetailTransactionPage.KEY_KODEMEJA, transaction.kodemeja)
                detailIntent.putExtra(DetailTransactionPage.KEY_STATUS, transaction.status)
                detailIntent.putExtra(DetailTransactionPage.KEY_IDPELANGGAN, transaction.idpelanggan.toString())
                detailIntent.putExtra(DetailTransactionPage.KEY_NAMAPELANGGAN, transaction.namapelanggan)
                detailIntent.putExtra(DetailTransactionPage.KEY_TOTAL, transaction.total)

                detailIntent.putExtra("hideButton", frag is HistoriesFragment)


                holder.itemView.context.startActivity(detailIntent)
            }
        }
        fun updateStatusById(transactionId: Int?, newStatus: String) {
            val position = listTransaction?.indexOfFirst { it.id == transactionId }
            if (position != null && position != -1) {
                listTransaction?.get(position)?.status = newStatus
                notifyItemChanged(position)
            }
        }

        class ListViewHolder(fragment: Fragment, itemView: View) : RecyclerView.ViewHolder(itemView) {
            var tvIdTransaksi:        TextView = itemView.findViewById(R.id.idTransaksi)
            var tvValBanyakPesanan:   TextView = itemView.findViewById(R.id.valBanyakPesanan)
            var tvValMeja:            TextView = itemView.findViewById(R.id.valMeja)
            var tvValLamaMenunggu:    TextView = itemView.findViewById(R.id.valLamaMenunggu)
            var tvValStatus:          TextView = itemView.findViewById(R.id.valStatus)
            var tvLabelBanyakPesanan: TextView = itemView.findViewById(R.id.labelBanyakPesanan)
            var tvLabelMeja:          TextView = itemView.findViewById(R.id.labelMeja)
            var tvLabelLamaMenunggu:  TextView = itemView.findViewById(R.id.labelLamaMenunggu)
            var tvLabelStatus:        TextView = itemView.findViewById(R.id.labelStatus)
            var root:                 CardView = itemView.findViewById(R.id.root)

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