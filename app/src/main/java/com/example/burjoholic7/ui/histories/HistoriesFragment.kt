package com.example.burjoholic7.ui.histories

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.burjoholic7.api.Client
import com.example.burjoholic7.api.Transaksi.TransaksiResponse
import com.example.burjoholic7.databinding.FragmentHistoriesBinding
import com.example.burjoholic7.ui.transactions.TransactionAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HistoriesFragment : Fragment() {

    private var _binding: FragmentHistoriesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoriesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        Log.wtf("WTF", "Requesting data")
        Client.apiService.getTransaksiHistories().enqueue(object : Callback<TransaksiResponse> {
            override fun onResponse(call: Call<TransaksiResponse>, response: Response<TransaksiResponse>) {
                if (response.isSuccessful) {
                    binding.rvTransactions.setHasFixedSize(true)
                    binding.rvTransactions.layoutManager = LinearLayoutManager(root.context)

                    val adapter = TransactionAdapter(this@HistoriesFragment, response.body()?.data)
                    binding.rvTransactions.adapter = adapter
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