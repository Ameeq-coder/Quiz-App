package com.example.quizearning

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.quizearning.Fragment.WithdrawalFragment
import com.example.quizearning.ModelClass.Questionmodel
import com.example.quizearning.ModelClass.User
import com.example.quizearning.databinding.QuizBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.app

class Quiz : AppCompatActivity() {
    private val binding by lazy {
        QuizBinding.inflate(layoutInflater)
    }
    private lateinit var questionlist:ArrayList<Questionmodel>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        questionlist= ArrayList<Questionmodel>()
        var catText = intent.getStringExtra("questiontype")
        Firebase.firestore.collection("Questions")
            .document(catText.toString()).collection("question1").get()
            .addOnSuccessListener {
                    questionData ->
                questionlist.clear()
                for (data in questionData.documents) {
                    var question: Questionmodel? = data.toObject(Questionmodel::class.java)
                questionlist.add(question!!)
                }
                binding.question.text=questionlist.get(0).question
                binding.option1.text=questionlist.get(0).option1
                binding.option2.text=questionlist.get(0).option2
                binding.option3.text=questionlist.get(0).option3
                binding.option4.text=questionlist.get(0).option4


            }
        Firebase.database.reference.child("Users").child(Firebase.auth.currentUser!!.uid)
            .addListenerForSingleValueEvent(
                object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        for (datasnap in snapshot.children) {
                            var user = snapshot.getValue(User::class.java)
                            binding.name.text = user?.name
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }

                }
            )
        binding.coinWithdrawal.setOnClickListener {
            val bottomSheetDialogFragment: BottomSheetDialogFragment = WithdrawalFragment()
            bottomSheetDialogFragment.show(this@Quiz.supportFragmentManager, "TEST")
            bottomSheetDialogFragment.enterTransition
        }
        binding.coinWithdrawal1.setOnClickListener {
            val bottomSheetDialogFragment: BottomSheetDialogFragment = WithdrawalFragment()
            bottomSheetDialogFragment.show(this@Quiz.supportFragmentManager, "TEST")
            bottomSheetDialogFragment.enterTransition
        }
        var image = intent.getIntExtra("categoryimg", 0)
        binding.catiemg.setImageResource(image)
    }


}