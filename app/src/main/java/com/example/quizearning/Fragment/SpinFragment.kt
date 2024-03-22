package com.example.quizearning.Fragment

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.quizearning.ModelClass.User
import com.example.quizearning.databinding.FragmentSpinBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.Random


class SpinFragment : Fragment() {
    private lateinit var binding:FragmentSpinBinding
    private lateinit var timer: CountDownTimer
    private val itemtitles= arrayOf("100","Try Again","500","Try Again","200","Try Again")


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding=FragmentSpinBinding.inflate(inflater, container, false)
        binding.coin.setOnClickListener{
            val bottomSheetDialogFragment: BottomSheetDialogFragment =WithdrawalFragment()
            bottomSheetDialogFragment.show(requireActivity().supportFragmentManager,"TEST")
            bottomSheetDialogFragment.enterTransition
        }
        binding.CoinWithdrawal.setOnClickListener{
            val bottomSheetDialogFragment: BottomSheetDialogFragment =WithdrawalFragment()
            bottomSheetDialogFragment.show(requireActivity().supportFragmentManager,"TEST")
            bottomSheetDialogFragment.enterTransition
        }
        Firebase.database.reference.child("Users").child(Firebase.auth.currentUser!!.uid).addListenerForSingleValueEvent(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (datasnap in snapshot.children){
                        var user=snapshot.getValue(User::class.java)
                        binding.name.text=user?.name
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            }
        )





        return binding.root
    }
    private fun showresult(itemTitles:String){
        Toast.makeText(requireContext(),itemTitles,Toast.LENGTH_SHORT).show()
        binding.spin.isEnabled=true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.spin.setOnClickListener{
            binding.spin.isEnabled=false
            val spin=Random().nextInt(6)
            val degree=60f*spin
timer=object :CountDownTimer(5000,50){
   var rotation=0f
    override fun onTick(p0: Long) {
        rotation+=5f
        if (rotation>=degree){
            rotation=degree
            timer.cancel()
            showresult(itemtitles[spin])
        }
        binding.wheel.rotation=rotation
    }

    override fun onFinish() {}

}.start()
        }
    }


}