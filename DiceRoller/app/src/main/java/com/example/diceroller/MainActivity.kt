package com.example.diceroller

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import com.example.diceroller.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var diceImage: ImageView
    private lateinit var binding: ActivityMainBinding
    private val myName: MyName = MyName()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.myName = MyName()

        binding.rollButton.setOnClickListener{
            addNickName(it)
        }
    }

    private fun addNickName(it: View?) {
        binding.apply {
            myName?.nickname = editTextTextPersonName.text.toString()
            textView.text = editTextTextPersonName.text
        }
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (it != null) {
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }

//    private fun rollDice(it: View) {
//        binding.apply {
//        val randomInt = Random().nextInt(6) + 1
//        val drawableResource = when(randomInt){
//                1 -> R.drawable.dice_1
//                2 -> R.drawable.dice_2
//                3 -> R.drawable.dice_3
//                4 -> R.drawable.dice_4
//                5 -> R.drawable.dice_5
//            else ->  R.drawable.dice_6
//        }
//        imageView.setImageResource(drawableResource)
//    }}
//}
 }


