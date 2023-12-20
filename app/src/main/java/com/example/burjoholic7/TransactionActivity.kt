package com.example.burjoholic7

import android.os.Bundle
import android.transition.Slide
import android.transition.TransitionManager
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.burjoholic7.databinding.ActivityTransactionBinding
import com.example.burjoholic7.ui.transaction_details.TransactionsDetailsFragment
import com.example.burjoholic7.ui.transactions.TransactionAddFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class TransactionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTransactionBinding
    companion object {
        const val KEY_INTENT = "key_intent"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransactionBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
//        windowInsetsController.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

//        setSupportActionBar(findViewById(R.id.app_toolbar))

        val navView: BottomNavigationView = binding.navView


        val navController = findNavController(R.id.nav_host_fragment_activity_transaction)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_transactions, R.id.navigation_histories
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

//        navController.addOnDestinationChangedListener { _, destination, _ ->
//            when (destination.id) {
//                R.id. -> showBottomNav()
//                R.id.mineFragment -> showBottomNav()
//                else -> hideBottomNav()
//            }
//        }
        supportFragmentManager.registerFragmentLifecycleCallbacks(object : FragmentManager.FragmentLifecycleCallbacks() {
            override fun onFragmentViewCreated(fm: FragmentManager, f: Fragment, v: View, savedInstanceState: Bundle?) {
                TransitionManager.beginDelayedTransition(binding.root, Slide(Gravity.BOTTOM).excludeTarget(R.id.nav_host_fragment_activity_transaction, true))
                when (f) {
                    is TransactionAddFragment -> {
                        binding.navView.visibility = View.GONE
                    }
                    else -> {
                        binding.navView.visibility = View.VISIBLE
                    }
                }
            }
        }, true)

        actionBar?.setDisplayHomeAsUpEnabled(true);
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                findNavController(R.id.nav_host_fragment_activity_transaction).popBackStack()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun showBottomNav() {
        binding.navView.visibility = View.GONE

    }

    private fun hideBottomNav() {
        binding.navView.visibility  = View.GONE
    }


}