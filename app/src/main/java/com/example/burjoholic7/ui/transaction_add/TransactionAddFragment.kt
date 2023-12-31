package com.example.burjoholic7.ui.transactions

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.burjoholic7.R
import com.example.burjoholic7.databinding.FragmentTransactionAddBinding

import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.burjoholic7.api.BasicListResponse
import com.example.burjoholic7.api.BasicResponse
import com.example.burjoholic7.api.Client
import com.example.burjoholic7.api.Transaksi.TransaksiCreateRequest
import com.example.burjoholic7.api.Transaksi.TransaksiCreateResponse
import com.example.burjoholic7.api.Transaksi.TransaksiDetailResponse
import com.example.burjoholic7.ui.transaction_add.IconTextViewAdapter
import com.example.burjoholic7.ui.transaction_add.ListMenuAddAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TransactionAddFragment : Fragment() {

    private var _binding: FragmentTransactionAddBinding? = null
    private val binding get() = _binding!!
    // ui stuff
    private val listMenuAdapter = ListMenuAddAdapter(this, ArrayList())

    val arrayList = ArrayList<Any>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
    }
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTransactionAddBinding.inflate(inflater, container, false)
        val root: View = binding.root

//      method transaksi
        var list_metodeTransaksi = arrayOf("tunai","kredit","debit","qris")
        val arrayAdapter = ArrayAdapter(requireContext(),R.layout.dropdown_transaksi,list_metodeTransaksi)
        binding!!.metodePembayaran.setAdapter(arrayAdapter)

//      list menu
        binding.rvListMenu.setHasFixedSize(true)
        binding.rvListMenu.layoutManager = LinearLayoutManager(root.context)
        binding.rvListMenu.adapter = listMenuAdapter

        Client.apiService.getMenuList().enqueue(object : Callback<BasicListResponse> {
            override fun onResponse(
                call: Call<BasicListResponse>,
                response: Response<BasicListResponse>
            ) {
                if (response.body() != null) {
                    arrayList.addAll(response.body()!!.data)
                }
            }

            override fun onFailure(call: Call<BasicListResponse>, t: Throwable) {
                Toast.makeText(binding.root.context, t.message, Toast.LENGTH_SHORT).show()
            }
        })

        binding.btTambahMenu.setOnClickListener {
            val dialog = Dialog(binding.root.context)

            dialog.setContentView(R.layout.dialog_pilih_menu)
            dialog.window?.setLayout(720, 800)

            // set transparent background
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()

            // Initialize and assign variable
            val editText: EditText = dialog.findViewById(R.id.edit_text)
            val listView: ListView = dialog.findViewById(R.id.list_view)

            // Initialize array adapter
            val adapter = IconTextViewAdapter(
                dialog.context,
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
                    val menu = adapter.getItem(position) as MutableMap<String, Any>
                    menu.put("jumlah", 1)


                    listMenuAdapter.listMenu.add(menu)
                    Log.wtf("WTF", listMenuAdapter.listMenu.toString())
                    listMenuAdapter.notifyDataSetChanged()

                    adapter.remove(menu);
                    dialog.dismiss()
                }
        }

        binding.btBuatTransaksi.setOnClickListener {
            if (listMenuAdapter.itemCount != 0) {
                listMenuAdapter.listMenu.map {
                    it["id"]?.let { it1 -> it.put("idmenu", it1) }
                }
                val sharedPreferences = root.context.getSharedPreferences("pref", Context.MODE_PRIVATE)
                val body = TransaksiCreateRequest(
                    kodemeja = binding.inputKodemeja.text.toString(),
                    shift = sharedPreferences.getInt("shift", 1),
                    namapelanggan = binding.inputNamapelanggan.text.toString(),
                    metodepembayaran = binding.metodePembayaran.text.toString(),
                    detail_transaksi = listMenuAdapter.listMenu
                )
                Client.apiService.createTransaksi(body).enqueue(object : Callback<TransaksiCreateResponse> {
                    override fun onResponse(call: Call<TransaksiCreateResponse>, response: Response<TransaksiCreateResponse>) {
                        Log.wtf("WTF", response.isSuccessful.toString())
                        if (response.isSuccessful) {
                            Toast.makeText(
                                binding.root.context,
                                "Transaksi berhasil dibuat!", Toast.LENGTH_SHORT
                            ).show()
                            findNavController().popBackStack()
                        } else {
                            val errorText = response.errorBody()?.string()
                            Log.wtf("WTFFF", errorText)
                            Toast.makeText(
                                binding.root.context,
                                errorText, Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                    override fun onFailure(call: Call<TransaksiCreateResponse>, t: Throwable) {
                        Log.wtf("WTF!",  t.message)
                        Toast.makeText(
                            binding.root.context,
                            t.message, Toast.LENGTH_SHORT
                        ).show()
                    }
                })
            } else {
                Toast.makeText(
                    binding.root.context,
                    "Pilih menu dulu bg", Toast.LENGTH_SHORT
                ).show()
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}