package com.example.burjoholic7.ui.transactions

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.burjoholic7.R
import com.example.burjoholic7.api.Client
import com.example.burjoholic7.api.Transaksi.Transaksi
import com.example.burjoholic7.api.Transaksi.TransaksiResponse
import com.example.burjoholic7.databinding.FragmentTransactionsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class TransactionsFragment : Fragment() {

    private var _binding: FragmentTransactionsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    // ui stuff
    private lateinit var rvTransactions: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTransactionsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val sampleTransaction = Transaksi(1, 1, 1,1,1,"01-10-2020", "20:23",
            1,"20K", "", "asd", "10%", "01-10-2020", "01-10-2020", "proses",
            "asd", "asd", "A1", "ASD", "", "asd", "")


        Log.wtf("WTF", "Requesting data")
        Client.apiService.getTransaksiList().enqueue(object : Callback<TransaksiResponse> {
            override fun onResponse(call: Call<TransaksiResponse>, response: Response<TransaksiResponse>) {
                Log.wtf("WTF", response.isSuccessful.toString())
                if (response.isSuccessful) {
                    rvTransactions = root.findViewById(R.id.rvTransactions)
                    rvTransactions.setHasFixedSize(true)
                    rvTransactions.layoutManager = LinearLayoutManager(root.context)
                    Log.wtf("WTF", response.body().toString())
                    val adapter = TransactionAdapter(this@TransactionsFragment, response.body()?.data)
                    rvTransactions.adapter = adapter

                } else {
                    val errorText = response.errorBody()?.string()
                    Log.wtf("WTF", errorText)
                }
            }

            override fun onFailure(call: Call<TransaksiResponse>, t: Throwable) {
                Log.wtf("WTF!",  t.message)
            }
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}