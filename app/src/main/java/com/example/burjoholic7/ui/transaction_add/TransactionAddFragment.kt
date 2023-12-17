package com.example.burjoholic7.ui.transactions

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.burjoholic7.R
import com.example.burjoholic7.databinding.FragmentTransactionAddBinding

import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.burjoholic7.R
import com.example.burjoholic7.api.Client
import com.example.burjoholic7.api.Transaksi.TransaksiDetailResponse
import com.example.burjoholic7.api.Transaksi.TransaksiResponse
import com.example.burjoholic7.databinding.FragmentTransactionAddBinding
import com.example.burjoholic7.ui.transaction_add.ListMenuAddAdapter
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

        val textview = binding.tambahMenu
        val arrayList = ArrayList<Any>()

        arrayList.add("DSA Self Paced")
        arrayList.add("Complete Interview Prep")
        arrayList.add("Amazon SDE Test Series")
        arrayList.add("Compiler Design")
        arrayList.add("Git & Github")
        arrayList.add("Python foundation")
        arrayList.add("Operating systems")
        arrayList.add("Theory of Computation")

        textview.setOnClickListener {
            val dialog = Dialog(binding.root.context)

            dialog.setContentView(R.layout.dialog_pilih_menu)

            dialog.getWindow()?.setLayout(650, 800)

            // set transparent background
            dialog.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            // show dialog
            dialog.show()

            // Initialize and assign variable
            val editText: EditText = dialog.findViewById(R.id.edit_text)
            val listView: ListView = dialog.findViewById(R.id.list_view)

            // Initialize array adapter
            val adapter = ArrayAdapter(
                binding.root.context,
                com.google.android.material.R.layout.abc_activity_chooser_view_list_item,
                arrayList
            )

            // set adapter
            listView.adapter = adapter
            editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(
                    s: CharSequence,
                    start: Int,
                    before: Int,
                    count: Int
                ) {
                    adapter.filter.filter(s)
                }

                override fun afterTextChanged(s: Editable) {}
            })
            listView.onItemClickListener =
                OnItemClickListener { parent, view, position, id -> // when item selected from list
                    // set selected item on textView
                    textview.text = adapter.getItem(position).toString()

                    dialog.dismiss()
                }
        }
    }
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTransactionAddBinding.inflate(inflater, container, false)
        val root: View = binding.root

        Log.wtf("WTF", "Requesting data")
        Client.apiService.createTransaksi().enqueue(object : Callback<TransaksiDetailResponse> {
            override fun onResponse(call: Call<TransaksiDetailResponse>, response: Response<TransaksiDetailResponse>) {
                Log.wtf("WTF", response.isSuccessful.toString())
                if (response.isSuccessful) {
                    binding.rvListMenu.setHasFixedSize(true)
                    binding.rvListMenu.layoutManager = LinearLayoutManager(root.context)
                    Log.wtf("WTF", response.body().toString())
                    val adapter = ListMenuAddAdapter(this@TransactionAddFragment, response.body()!!.detail_transaksi)
                    binding.rvListMenu.adapter = adapter
                } else {
                    val errorText = response.errorBody()?.string()
                    Log.wtf("WTFFF", errorText)
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