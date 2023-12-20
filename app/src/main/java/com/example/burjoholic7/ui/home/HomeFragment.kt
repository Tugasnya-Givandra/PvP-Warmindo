package com.example.burjoholic7.ui.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.burjoholic7.api.BasicResponse
import com.example.burjoholic7.api.Client
import com.example.burjoholic7.api.Transaksi.TransaksiCreateResponse
import com.example.burjoholic7.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val shift: TextView = binding.shiftButton

        val pref = root.context.getSharedPreferences("pref", Context.MODE_PRIVATE)
        var shift_num = pref.getInt("shift", 1)
        requestDataRekap(shift_num, false)

        shift.text = "SHIFT " + if (shift_num == 1) "PAGI" else "MALAM"
        shift.setOnClickListener {
            if (shift_num == 1) {
                shift_num = 2
            } else {
                shift_num = 1
            }

            val shift_text = "SHIFT " +  if (shift_num == 1) "PAGI" else "MALAM"

            shift.text = shift_text
            pref.edit().putInt("shift", shift_num).apply()
            requestDataRekap(shift_num, true)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun requestDataRekap(shift: Int, withToast: Boolean) {
        Client.apiService.getRekapShift(shift).enqueue(object : Callback<BasicResponse> {
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                Log.wtf("WTF", response.isSuccessful.toString())
                if (response.isSuccessful) {
                    binding.tvPenjualan.text = response.body()?.data?.get("total_penjualan").toString()
                    binding.tvTransaksi.text = response.body()?.data?.get("total_transaksi").toString().toFloat().toInt().toString()
                    binding.tvMasakan.text = response.body()?.data?.get("total_masakan_dibuat").toString()

                    @Suppress("UNCHECKED_CAST")
                    val listMakananTerlaris = response.body()?.data?.get("masakan_terlaris") as List<Map<String, String>>
                    binding.tvTerlaris.text = listMakananTerlaris[0]["namamenu"]

                    if (withToast)
                        Toast.makeText(
                            binding.root.context,
                            "Mengganti ke shift ${if (shift == 1) "PAGI" else "MALAM"}", Toast.LENGTH_SHORT
                        ).show()
                } else {
                    val errorText = response.errorBody()?.string()
                    Log.wtf("WTFFF", errorText)
                    Toast.makeText(
                        binding.root.context,
                        errorText, Toast.LENGTH_SHORT
                    ).show()
                }
            }
            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {
                Log.wtf("WTF!",  t.message)
                Toast.makeText(
                    binding.root.context,
                    t.message, Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}