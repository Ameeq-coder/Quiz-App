package com.example.quizearning.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quizearning.Adapter.HistoryAdapterClass
import com.example.quizearning.ModelClass.HistoryModelClass
import com.example.quizearning.ModelClass.User
import com.example.quizearning.R
import com.example.quizearning.databinding.FragmentHistoryBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class HistoryFragment : Fragment() {
val binding by lazy{
    FragmentHistoryBinding.inflate(layoutInflater)
}
    private var ListHistory=ArrayList<HistoryModelClass>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ListHistory.add(HistoryModelClass("12:30","200"))
        ListHistory.add(HistoryModelClass("1:30","200"))
        ListHistory.add(HistoryModelClass("2:30","200"))
        ListHistory.add(HistoryModelClass("3:30","200"))


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

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




        // Inflate the layout for this fragment
binding.historyrv.layoutManager=LinearLayoutManager(requireContext())
        var adapter=HistoryAdapterClass(ListHistory)
        binding.historyrv.setHasFixedSize(true)
        binding.historyrv.adapter=adapter
        return binding.root
    }
    companion object{

    }
}