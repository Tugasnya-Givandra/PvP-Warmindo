package com.example.burjoholic7.api.Login

// roles,
// 1: pemilik
// 2: kasir
// 3: pengantar pesanan
// 4: koki
enum class Role (val roleid: Int) {
    PEMILIK(1),
    KASIR(2),
    PENGANTAR_PESANAN(3),
    KOKI(4),
//    PELANGGAN(5)
}

data class LoginResponse(
    val token: String,
    val idrole: Int,

//    "id": 2,
//"username": "petugas_kasir",
//"namapengguna": "Petugas Kasir",
//"idrole": 2,
//"status": "aktif",
//"foto": null,
//"remember_token": null,
//"created_at": "2023-12-01T06:43:02.000000Z",
//"updated_at": "2023-12-01T06:43:02.000000Z",
//"kodepengguna": "WT1202310X01",
//"idwarung": 1,
//"role": "petugas_kasir",
//"role_description": "Petugas Kasir",
//"namawarung": "Burjo Holic 1"
)