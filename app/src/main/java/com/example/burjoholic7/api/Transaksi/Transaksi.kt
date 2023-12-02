package com.example.burjoholic7.api.Transaksi

data class TransaksiResponse(
    val data: ArrayList<Transaksi>
)
// Transaksi:
//{
//    "id": 4,
//    "idpelanggan": 4,
//    "idmeja": 6,
//    "idpengguna": 6,
//    "idpromosi": 4,
//    "tanggal": "2023-12-02",
//    "waktu": "00:00:00",
//    "shift": 1,
//    "total": "40000",
//    "namapelanggan": "Pelanggan 4",
//    "metodepembayaran": "tunai",
//    "totaldiskon": "0",
//    "created_at": "2023-12-02T00:16:46.000000Z",
//    "updated_at": "2023-12-02T00:16:46.000000Z",
//    "status": "selesai",
//    "namapengguna": "Petugas Kasir 2",
//    "username_pengguna": "petugas_kasir2",
//    "kodemeja": "A2",
//    "namawarung": "Burjo Holic 2",
//    "kodewarung": "WT2",
//    "namapromosi": "Promosi 4",
//    "kodepengguna": "WT2202310X01"
//},
data class Transaksi(
    val id: Int,
    val idpelanggan: Int,
    val idmeja: Int,
    val idpengguna: Int,
    val idpromosi: Int,
    val tanggal: String,
    val waktu: String,
    val shift: Int,
    val total: String,
    val namapelanggan: String,
    val metodepembayaran: String,
    val totaldiskon: String,
    val created_at: String,
    val updated_at: String,
    val status: String,
    val namapengguna: String,
    val username_pengguna: String,
    val kodemeja: String,
    val namawarung: String,
    val kodewarung: String,
    val namapromosi: String,
    val kodepengguna: String
)