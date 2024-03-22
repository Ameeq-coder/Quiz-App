package com.example.quizearning.Fragment

import android.os.Bundle
import android.renderscript.Sampler.Value
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.quizearning.ModelClass.User
import com.example.quizearning.R
import com.example.quizearning.databinding.FragmentProfileBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class ProfileFragment : Fragment() {

    val binding by lazy {
        FragmentProfileBinding.inflate(layoutInflater)
    }
var isExpand=true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.imageButton.setOnClickListener{
            if (isExpand){
                binding.expconstraintLayout.visibility=View.VISIBLE
                binding.imageButton.setImageResource(R.drawable.arrowup)
            }else{
                binding.expconstraintLayout.visibility=View.GONE
                binding.imageButton.setImageResource(R.drawable.downarrow)
            }
            isExpand=!isExpand
        }
Firebase.database.reference.child("Users").child(Firebase.auth.currentUser!!.uid).addListenerForSingleValueEvent(
    object :ValueEventListener{
        override fun onDataChange(snapshot: DataSnapshot) {
            for (datasnap in snapshot.children){
                var user=snapshot.getValue(User::class.java)
                binding.nametxt.text=user?.name
                binding.emailtxt.text=user?.email
                binding.agetxt.text=user?.age.toString()
                binding.passtxt.text=user?.password
                binding.usernameup.text=user?.name

            }
        }

        override fun onCancelled(error: DatabaseError) {
            TODO("Not yet implemented")
        }

    }
)
        // Inflate the layout for this fragment
        return binding.root
    }
companion object{

}

}