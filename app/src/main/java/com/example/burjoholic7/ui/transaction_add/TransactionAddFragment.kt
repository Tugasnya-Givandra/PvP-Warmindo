package com.example.burjoholic7.ui.transactions

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.burjoholic7.R
import com.example.burjoholic7.api.Client
import com.example.burjoholic7.api.Transaksi.TransaksiDetailResponse
import com.example.burjoholic7.api.Transaksi.TransaksiResponse
import com.example.burjoholic7.databinding.FragmentTransactionAddBinding
import com.example.burjoholic7.databinding.FragmentTransactionsBinding
import com.example.burjoholic7.ui.transaction_add.ListMenuAddAdapter
import com.example.burjoholic7.ui.transaction_details.DetailMakananAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class TransactionAddFragment : Fragment() {

    private var _binding: FragmentTransactionAddBinding? = null

    private val binding get() = _binding!!

    // ui stuff
    private lateinit var rvTransactions: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        //required for back button to work
//        activity.getSupportActionBar
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTransactionAddBinding.inflate(inflater, container, false)
        val root: View = binding.root

        Log.wtf("WTF", "Requesting transaction details $id")
        Client.apiService.getTransaksiDetail(id).enqueue(object : Callback<TransaksiDetailResponse> {
            override fun onResponse(call: Call<TransaksiDetailResponse>, response: Response<TransaksiDetailResponse>) {
                Log.wtf("WTF", response.isSuccessful.toString())
                if (response.isSuccessful) {
                    binding.rvListMenu.setHasFixedSize(true)
                    binding.rvListMenu.layoutManager = LinearLayoutManager(root.context)
                    val ListMenu: ArrayList<Map<String, Any>> = response.body()?.detail_transaksi ?: ArrayList()
                    val adapter = ListMenuAddAdapter(this@TransactionAddFragment, ListMenu)
                    binding.rvListMenu.adapter = adapter

                } else {
                    val errorText = response.errorBody()?.string()
                    Log.wtf("WTF", errorText)
                }
            }
            override fun onFailure(call: Call<TransaksiDetailResponse>, t: Throwable) {
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