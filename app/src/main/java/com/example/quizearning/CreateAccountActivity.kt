package com.example.quizearning

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.quizearning.ModelClass.User
import com.example.quizearning.databinding.ActivityCreateAccountBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class CreateAccountActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityCreateAccountBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.signupuser.setOnClickListener {
            if (binding.username.text.toString().equals("") ||
                binding.userage.text.toString().equals("") ||
                binding.useremail.text.toString().equals("") ||
                binding.userpass.text.toString().equals("")
            ) {
                Toast.makeText(this, "Please Fill Details", Toast.LENGTH_SHORT).show()
            } else {
                Firebase.auth.createUserWithEmailAndPassword(
                    binding.useremail.text.toString(),
                    binding.userpass.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        var user = User(
                            binding.username.text.toString(),
                            binding.userage.text.toString().toInt(),
                            binding.useremail.text.toString(),
                            binding.userpass.text.toString()
                        )
                        Firebase.database.reference.child("Users")
                            .child(Firebase.auth.currentUser!!.uid).setValue(user).addOnSuccessListener {
                                startActivity(Intent(this, HomeActivity::class.java))
                            }
                    } else {
                        Toast.makeText(this, ""+it.exception?.localizedMessage, Toast.LENGTH_SHORT)
                            .show()
                    }
                }

            }

        }
    }

    override fun onStart() {
        super.onStart()
        if (Firebase.auth.currentUser != null) {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
    }
}