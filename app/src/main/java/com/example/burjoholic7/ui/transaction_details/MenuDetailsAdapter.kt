//package com.example.burjoholic7.ui.transaction_details
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.TextView
//import android.widget.Toast
//import androidx.cardview.widget.CardView
//import androidx.recyclerview.widget.RecyclerView
//import com.example.burjoholic7.R
//import com.example.burjoholic7.api.Transaksi.Transaksi
//
//class MenuDetailsAdapter(list: ArrayList<Transaksi>?) : RecyclerView.Adapter<MenuDetailsAdapter.ListViewHolder>() {
//        private var listTransaction: ArrayList<Transaksi>?
//
//        init {
//            listTransaction = list
//        }
//
//        //
//        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
//            val view: View = LayoutInflater.from(parent.context).inflate(
//                R.layout.recycle_transaction_summary,
//                parent, false
//            )
//            return ListViewHolder(view)
//        }
//
//        override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
//            val transaction = listTransaction!![position]
//
//            holder.tvIdTransaksi.text   = transaction.id.toString()
//            holder.tvAtasNama.text      = transaction.namapelanggan
//            holder.tvValMeja.text          = transaction.kodemeja
//            holder.tvValLamaMenunggu.text  = "3 Menit"
//            holder.tvValStatus.text       = transaction.status
//
//            holder.root.setOnClickListener() {
//                Toast.makeText(
//                    holder.itemView.context,
//                    "Selected ${transaction.id.toString()}", Toast.LENGTH_SHORT
//                ).show()
//            }
//        }
//
//        class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//            var tvIdTransaksi:        TextView = itemView.findViewById(R.id.idTransaksi)
//            var tvAtasNama:   TextView = itemView.findViewById(R.id.valAtasNama)
//            var tvValMeja:            TextView = itemView.findViewById(R.id.valMeja)
//            var tvValLamaMenunggu:    TextView = itemView.findViewById(R.id.valLamaMenunggu)
//            var tvValStatus:          TextView = itemView.findViewById(R.id.valStatus)
//            var tvLabelAtasNama: TextView = itemView.findViewById(R.id.labelAtasNama)
//            var tvLabelMeja:          TextView = itemView.findViewById(R.id.labelMeja)
//            var tvLabelLamaMenunggu:  TextView = itemView.findViewById(R.id.labelLamaMenunggu)
//            var tvLabelStatus:        TextView = itemView.findViewById(R.id.labelStatus)
//            var root:                 CardView = itemView.findViewById(R.id.root)
//
//            init {
//                tvLabelAtasNama.text = "Atas Nama:"
//                tvLabelMeja.text = "Meja"
//                tvLabelLamaMenunggu.text = "Lama Menunggu"
//                tvLabelStatus.text = "Status"
//            }
//        }
//
//    override fun getItemCount(): Int {
//        return listTransaction!!.size
//    }
//}