package com.example.burjoholic7.api.Transaksi

class TransaksiCreateRequest internal constructor(
    val kodemeja: String,
    val shift: Int,
    val namapelanggan: String,
    val metodepembayaran: String,
    val detail_transaksi: ArrayList<MutableMap<String, Any>>
)

data class TransaksiCreateResponse(
    var transaksi: Map<String, Any>,
    val detail_transaksi: ArrayList<Map<String, Any>>,
)