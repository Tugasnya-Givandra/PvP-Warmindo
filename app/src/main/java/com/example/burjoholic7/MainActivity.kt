package com.example.burjoholic7

import android.R.attr.value
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.burjoholic7.api.ApiService
import com.example.burjoholic7.api.LoginRequest
import com.example.burjoholic7.api.LoginResponse
import com.example.burjoholic7.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getSupportActionBar()?.hide();
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)

        // onclick listener
        binding.btnLogin.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_login -> {
                // intent explicit
                val inputUsername: String = binding.username.text.toString()
                val inputPassword: String = binding.password.text.toString()

                // validasi input kosong
                if (inputUsername.isEmpty() || inputPassword.isEmpty()) {
                    binding.errorView.text = "Data tidak boleh kosong"

                } else {
                    // simpan username dan password sebagai request body untuk request api login
                    val retrofit = Retrofit.Builder()
                        .baseUrl("https://burjo.zzidzz.me/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()

                    val apiService = retrofit.create(ApiService::class.java)


                    // Log.wtf("WTF!", loginRequestBody.toString())

                    // Make the API call for login
                    apiService.login(inputUsername, inputPassword).enqueue(object : Callback<LoginResponse> {
                        override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                            Log.wtf("WTF", response.isSuccessful.toString())
                            Log.wtf("WTF", inputUsername)
                            Log.wtf("WTF", inputPassword)
                            if (response.isSuccessful) {
                                val receivedToken = response.body()?.token

                                // Store the received token in SharedPreferences
                                val sharedPreferences = applicationContext.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
                                val editor = sharedPreferences.edit()
                                editor.putString("token", receivedToken)
                                editor.apply()

                                Log.wtf("WTF", sharedPreferences.getString("token", "No token"))

                                // Token stored, redirect -> TransactionActivity (it is actually home)
                                this@MainActivity.startActivity(Intent(this@MainActivity, TransactionActivity::class.java))
                            } else {
                                // Handle unsuccessful login response
                                val errorText = response.errorBody()?.string()
//                                Log.wtf("WTF", errorText)
                                binding.errorView.text = errorText
                            }
                        }

                        override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                            // Handle failure to make the API call, write to errorView
                            binding.errorView.text = t.message
                            Log.wtf("WTF!",  t.message)
                        }
                    })
                }
            }
        }
    }
}