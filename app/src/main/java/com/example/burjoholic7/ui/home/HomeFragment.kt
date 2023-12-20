package com.example.burjoholic7.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.burjoholic7.databinding.FragmentHomeBinding

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
//        val homeViewModel =
//            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val shift: TextView = binding.shiftButton

        val pref = root.context.getSharedPreferences("pref", Context.MODE_PRIVATE)
        var shift_num = pref.getInt("shift", 1)

        shift.text = "SHIFT " + if (shift_num == 1) "PAGI" else "MALAM"

        shift.setOnClickListener {
            if (shift_num == 1) {
                shift_num = 2
            } else {
                shift_num = 1
            }

            shift.text = "SHIFT " +  if (shift_num == 1) "PAGI" else "MALAM"
            pref.edit().putInt("shift", shift_num).apply()
        }




//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}