# Tampilan

![[Pasted image 20231221005903.png]]

# Alur

1. User memasukan username
2. User memasukan password
3. Menekan Login
# Bagian Penting kode

## `MainActivity.kt`
![[Pasted image 20231221012254.png]]

Mendengarkan event klik dan memberikan callback ketika btn_login di tekan

![[Pasted image 20231221012417.png]]

melakukan request login 

![[Pasted image 20231221012517.png]]
Jika berhasil kita akan membuat client baru dengan token yang sudah dimasukkan kedalam interceptor. Kita juga menyimpan shift, role id, dan token.

Setelah itu kita akan membuat intent yang akan berpindah ke [[Home]]