package com.example.burjoholic7.ui.transaction_details

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.burjoholic7.R
import com.example.burjoholic7.api.Transaksi.Transaksi
import com.example.burjoholic7.ui.transactions.TransactionAdapter
import com.example.burjoholic7.ui.transactions.TransactionsFragment

class DetailTransactionPage : AppCompatActivity() {
    private lateinit var rvlist: RecyclerView
    private var listMakanan = ArrayList<Transaksi>()
    companion object {
        const val KEY_ID = "key_id"
        const val KEY_KODEMEJA = "key_kodemeja"
        const val KEY_STATUS = "key_status"
        const val KEY_IDPELANGGAN = "key_idpelanggan"
        const val KEY_NAMAPELANGGAN = "key_namapelanggan"
        const val KEY_TOTAL = "total"

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_transaction_page)
        val labelId= findViewById<TextView>(R.id.labelDetailIdTransaksi)
        val labelIdPelanggan= findViewById<TextView>(R.id.labelDetailIdPelanggan)
        val labelNamaPelanggan= findViewById<TextView>(R.id.labelDetailNamaPelanggan)
        val labelKodeMeja = findViewById<TextView>(R.id.labelDetailKodeMeja)
        val labelStatus = findViewById<TextView>(R.id.labelDetailStatus)
        val labelTotal = findViewById<TextView>(R.id.labelDetailSummary)

        val tvId= findViewById<TextView>(R.id.valDetailIdTransaksi)
        val tvKodeMeja = findViewById<TextView>(R.id.valDetailKodeMeja)
        val tvStatus = findViewById<TextView>(R.id.valDetailStatus)
        val tvIdPelanggan = findViewById<TextView>(R.id.valDetailIdPelanggan)
        val tvNamaPelanggan = findViewById<TextView>(R.id.valDetailNamaPelanggan)
        val tvTotal = findViewById<TextView>(R.id.valDetailSummary)


        val id = intent.getStringExtra(KEY_ID)
        val kodemeja = intent.getStringExtra(KEY_KODEMEJA)
        val status = intent.getStringExtra(KEY_STATUS)
        val idpelanggan = intent.getStringExtra(KEY_IDPELANGGAN)
        val namapelanggan = intent.getStringExtra(KEY_NAMAPELANGGAN)
        val total = intent.getStringExtra(KEY_TOTAL)



        labelId.text = String.format("Id")
        labelKodeMeja.text = String.format("Kode Meja")
        labelStatus.text = String.format("Status")
        labelIdPelanggan.text = String.format("Id Pelanggan")
        labelNamaPelanggan.text = String.format("Nama Pelanggan")
        labelTotal.text = String.format("Total Transaksi")

        tvId.text = String.format("%s", id)
        tvKodeMeja.text = String.format("%s", kodemeja)
        tvStatus.text = String.format("%s", status)
        tvIdPelanggan.text = String.format("%s", idpelanggan)
        tvNamaPelanggan.text = String.format("%s", namapelanggan)
        tvTotal.text = String.format("%s", total)



        listMakanan.add(
            Transaksi(1, 1, 1,1,1,"01-10-2020", "20:23",
                1,"10K", "", "asd", "10%", "01-10-2020", "01-10-2020", "proses",
                "asd", "asd", "A1", "ASD", "", "asd", "","Ayam Goreng",150000,1))
        listMakanan.add(
            Transaksi(1, 1, 1,1,1,"01-10-2020", "20:23",
                1,"10K", "", "asd", "10%", "01-10-2020", "01-10-2020", "proses",
                "asd", "asd", "A1", "ASD", "", "asd", "","Ayam Goreng",150000,1))
        listMakanan.add(
            Transaksi(1, 1, 1,1,1,"01-10-2020", "20:23",
                1,"10K", "", "asd", "10%", "01-10-2020", "01-10-2020", "proses",
                "asd", "asd", "A1", "ASD", "", "asd", "","Ayam Goreng",150000,1))
        listMakanan.add(
            Transaksi(1, 1, 1,1,1,"01-10-2020", "20:23",
                1,"10K", "", "asd", "10%", "01-10-2020", "01-10-2020", "proses",
                "asd", "asd", "A1", "ASD", "", "asd", "","Ayam Goreng",150000,1))
        listMakanan.add(
            Transaksi(1, 1, 1,1,1,"01-10-2020", "20:23",
                1,"10K", "", "asd", "10%", "01-10-2020", "01-10-2020", "proses",
                "asd", "asd", "A1", "ASD", "", "asd", "","Ayam Goreng",150000,1))
        rvlist = findViewById(R.id.rv_list)
        rvlist.setHasFixedSize(true)
        rvlist.layoutManager = LinearLayoutManager(this)
        val detailMakananAdapter = DetailMakananAdapter(listMakanan)
        rvlist.adapter = detailMakananAdapter

        val buttonSubmit = findViewById<Button>(R.id.submit_pesanan)
        buttonSubmit.setOnClickListener {
            val transactionId = intent.getStringExtra(KEY_ID)?.toIntOrNull()
            val newStatus = "Aktif"
            if (transactionId != null && newStatus.isNotEmpty()) {
                val adapter = rvlist.adapter as? TransactionAdapter
                adapter?.updateStatusById(transactionId, newStatus)
            }
            val intent = Intent(this, TransactionsFragment::class.java)
            startActivity(intent)
        }
    }
}