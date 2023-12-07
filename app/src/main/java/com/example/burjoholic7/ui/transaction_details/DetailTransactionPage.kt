package com.example.burjoholic7.ui.transaction_details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.burjoholic7.R
import com.example.burjoholic7.api.Transaksi.Transaksi

class DetailTransactionPage : AppCompatActivity() {
    private lateinit var rv_list: RecyclerView
    private var listMakanan = ArrayList<Transaksi>()
    companion object {
        const val KEY_ID = "key_id"
        const val KEY_KODEMEJA = "key_kodemeja"
        const val KEY_STATUS = "key_status"
        const val KEY_IDPELANGGAN = "key_idpelanggan"
        const val KEY_NAMAPELANGGAN = "key_namapelanggan"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_transaction_page)
        // List Makanan
        listMakanan.add(Transaksi(1, 1, 1,1,1,"01-10-2020", "20:23",
            1,"150.000", "Ayam Goreng", "asd", "10%", "01-10-2020", "01-10-2020", "proses",
            "asd", "asd", "A1", "ASD", "", "asd", "",1))
        rv_list = findViewById(R.id.rv_list)
        rv_list.setHasFixedSize(true)
        rv_list.setLayoutManager(LinearLayoutManager(this))
        val DetailMakananAdapter = DetailMakananAdapter(listMakanan)
        rv_list.setAdapter(DetailMakananAdapter)

        val labelId= findViewById<TextView>(R.id.labelDetailIdTransaksi)
        val labelIdPelanggan= findViewById<TextView>(R.id.labelDetailIdPelanggan)
        val labelNamaPelanggan= findViewById<TextView>(R.id.labelDetailNamaPelanggan)
        val labelKodeMeja = findViewById<TextView>(R.id.labelDetailKodeMeja)
        val labelStatus = findViewById<TextView>(R.id.labelDetailStatus)

        val tvId= findViewById<TextView>(R.id.valDetailIdTransaksi)
        val tvKodeMeja = findViewById<TextView>(R.id.valDetailKodeMeja)
        val tvStatus = findViewById<TextView>(R.id.valDetailStatus)
        val tvIdPelanggan = findViewById<TextView>(R.id.valDetailIdPelanggan)
        val tvNamaPelanggan = findViewById<TextView>(R.id.valDetailNamaPelanggan)


        val id = intent.getStringExtra(KEY_ID)
        val KodeMeja = intent.getStringExtra(KEY_KODEMEJA)
        val status = intent.getStringExtra(KEY_STATUS)
        val idpelanggan = intent.getStringExtra(KEY_IDPELANGGAN)
        val namapelanggan = intent.getStringExtra(KEY_NAMAPELANGGAN)


        labelId.text = String.format("Id")
        labelKodeMeja.text = String.format("Kode Meja")
        labelStatus.text = String.format("Status")
        labelIdPelanggan.text = String.format("Id Pelanggan")
        labelNamaPelanggan.text = String.format("Nama Pelanggan")


        tvId.text = String.format("%s", id)
        tvKodeMeja.text = String.format("%s", KodeMeja)
        tvStatus.text = String.format("%s", status)
        tvIdPelanggan.text = String.format("%s", idpelanggan)
        tvNamaPelanggan.text = String.format("%s", namapelanggan)

    }
}