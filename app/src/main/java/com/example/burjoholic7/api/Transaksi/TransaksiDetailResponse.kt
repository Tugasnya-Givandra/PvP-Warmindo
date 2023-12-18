package com.example.burjoholic7.api.Transaksi

//@Parcelize
data class TransaksiDetailResponse(
    var data: Map<String, Any>,
    val detail_transaksi: ArrayList<Map<String, Any>>,
) //: Parcelable



//{
//    "data": {
//    "id": 1,
//    "idpelanggan": 1,
//    "idmeja": 1,
//    "idpengguna": 2,
//    "idpromosi": 1,
//    "tanggal": "2023-12-02",
//    "waktu": "00:00:00",
//    "shift": 1,
//    "total": "10000",
//    "namapelanggan": "Pelanggan 1",
//    "metodepembayaran": "tunai",
//    "totaldiskon": "0",
//    "created_at": "2023-12-02T00:16:46.000000Z",
//    "updated_at": "2023-12-02T00:16:46.000000Z",
//    "status": "baru",
//    "namapengguna": "Petugas Kasir",
//    "username_pengguna": "petugas_kasir",
//    "kodemeja": "A1",
//    "namawarung": "Burjo Holic 1",
//    "kodewarung": "WT1",
//    "namapromosi": "Promosi 1",
//    "kodepengguna": "WT1202310X01"
//},
//    "detail_transaksi": [
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
//    ]
//}

