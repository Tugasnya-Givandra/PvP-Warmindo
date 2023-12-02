package com.example.burjoholic7.ui.transaction_details

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
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


class TransactionsDetailsFragment : Fragment() {

    private var _binding: FragmentTransactionsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    // ui stuff
    private lateinit var rvTransactions: RecyclerView
    private var listTransactions = ArrayList<Transaksi>()

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTransactionsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // biarin lah deprec
        // val transactions = savedInstanceState?.getParcelable<Transaksi>("Parcel bang") as Transaksi

//        Client.apiService.getTransaksiList().enqueue(object : Callback<TransaksiResponse> {
//            override fun onResponse(call: Call<TransaksiResponse>, response: Response<TransaksiResponse>) {
//                Log.wtf("WTF", response.isSuccessful.toString())
//                if (response.isSuccessful) {
//                    rvTransactions = root.findViewById(R.id.rvTransactions)
//                    rvTransactions.setHasFixedSize(true)
//                    rvTransactions.layoutManager = LinearLayoutManager(root.context)
//                    val adapter = MenuDetailsAdapter(response.body()?.data)
//                    rvTransactions.adapter = adapter
//
//                } else {
//                    // Handle unsuccessful login response
//                    val errorText = response.errorBody()?.string()
//                    Log.wtf("WTF", errorText)
//
//                }
//            }
//
//            override fun onFailure(call: Call<TransaksiResponse>, t: Throwable) {
//                Log.wtf("WTF!",  t.message)
//            }
//        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}