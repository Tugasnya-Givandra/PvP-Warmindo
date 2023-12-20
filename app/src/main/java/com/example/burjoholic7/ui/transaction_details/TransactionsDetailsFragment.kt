package com.example.burjoholic7.ui.transaction_details

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.burjoholic7.R
import com.example.burjoholic7.api.BasicResponse
import com.example.burjoholic7.api.Client
import com.example.burjoholic7.api.Transaksi.Transaksi
import com.example.burjoholic7.api.Transaksi.TransaksiResponse
import com.example.burjoholic7.databinding.FragmentTransactionDetailsBinding
import com.example.burjoholic7.databinding.FragmentTransactionsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class TransactionsDetailsFragment : Fragment() {

    private var _binding: FragmentTransactionDetailsBinding? = null
    private val binding get() = _binding!!
    // ui stuff
    private lateinit var rvTransactions: RecyclerView
    private var listTransactions = ArrayList<Map<String, Any>>()

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTransactionDetailsBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        if (findNavController().previousBackStackEntry?.destination?.id == R.id.navigation_histories)
//            || ) {
//            binding.submitPesanan.visibility = View.GONE
//        }

//        val buttonSubmit = binding.submitPesanan
//        buttonSubmit.setOnClickListener {
//            Client.apiService.updateTransaksi(id).enqueue(object : Callback<BasicResponse> {
//                override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
//                    val newStatus = response.body()?.data?.get("status")
//                    Toast.makeText(root.context, "status berhasil diubah menjadi $newStatus", Toast.LENGTH_SHORT).show()
//                    finish()
//                    startActivity(getIntent());
//                }
//
//                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {
//                    Toast.makeText(this@DetailTransactionPage, t.message, Toast.LENGTH_SHORT).show()
//                }
//            })
//        }

        return root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}