package com.example.quizearning

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.quizearning.Fragment.HistoryFragment
import com.example.quizearning.Fragment.HomeFragment
import com.example.quizearning.Fragment.ProfileFragment
import com.example.quizearning.Fragment.SpinFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        lateinit var bottomNav: BottomNavigationView
            loadFragment(HomeFragment())
            bottomNav = findViewById(R.id.bottomNavigationView) as BottomNavigationView
            bottomNav.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.homeFragment -> {
                        loadFragment(HomeFragment())
                        true
                    }
                    R.id.spinFragment -> {
                        loadFragment(SpinFragment())
                        true
                    }
                    R.id.historyFragment -> {
                        loadFragment(HistoryFragment())
                        true
                    }
                    R.id.profileFragment->{
                        loadFragment(ProfileFragment())
                    true
                    }

                    else -> {
                        return@setOnItemSelectedListener true
                    }
                }
        }



    }
    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }
}