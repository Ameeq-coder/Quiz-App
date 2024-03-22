package com.example.quizearning.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.quizearning.Adapter.CategoryAdapter
import com.example.quizearning.ModelClass.CategoryModelClass
import com.example.quizearning.ModelClass.User
import com.example.quizearning.R
import com.example.quizearning.databinding.FragmentHomeBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class HomeFragment : Fragment() {

    private val bindings:FragmentHomeBinding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }
    private var categoryList=ArrayList<CategoryModelClass>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?):
            View? {
        bindings.coinWithdrawal.setOnClickListener{
            val bottomSheetDialogFragment:BottomSheetDialogFragment=WithdrawalFragment()
            bottomSheetDialogFragment.show(requireActivity().supportFragmentManager,"TEST")
        bottomSheetDialogFragment.enterTransition
        }
        bindings.coinWithdrawal1.setOnClickListener{
            val bottomSheetDialogFragment:BottomSheetDialogFragment=WithdrawalFragment()
            bottomSheetDialogFragment.show(requireActivity().supportFragmentManager,"TEST")
            bottomSheetDialogFragment.enterTransition
        }
        Firebase.database.reference.child("Users").child(Firebase.auth.currentUser!!.uid).addListenerForSingleValueEvent(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (datasnap in snapshot.children){
                        var user=snapshot.getValue(User::class.java)
                        bindings.name.text=user?.name
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            }
        )

        // Inflate the layout for this fragment
      return bindings.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            categoryList.add(CategoryModelClass(R.drawable.scince,"Science"))
        categoryList.add(CategoryModelClass(R.drawable.englishs,"English"))
        categoryList.add(CategoryModelClass(R.drawable.historyimg,"History"))
        categoryList.add(CategoryModelClass(R.drawable.mathmetic,"Mathmatics"))
        bindings.categoryrv.layoutManager=GridLayoutManager(requireContext(),2)
var adapter=CategoryAdapter(categoryList,requireActivity())
        bindings.categoryrv.adapter=adapter
        bindings.categoryrv.setHasFixedSize(true)

    }

    }