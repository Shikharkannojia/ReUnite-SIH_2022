package com.shikhar.reunite

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged

class ForgotPasswordActivity : AppCompatActivity(){
    private lateinit var buttonresetpassword : Button
    lateinit var frgtxtemail: EditText
    lateinit var frgtxtmob: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title="Reset Password"
        setContentView(R.layout.activity_forgotpassword)


        buttonresetpassword=findViewById(R.id.buttonresetpassword)
        frgtxtemail=findViewById(R.id.frgtxtemail1)
        frgtxtmob=findViewById(R.id.frgtxtmob1)
       frgtxtmob.doOnTextChanged { text, start, before, count ->
           if(text!!.length > 10){
               frgtxtmob.error="Enter 10 digit Mobile Number"
           }
       }
        buttonresetpassword.setOnClickListener {
    val intent=Intent(this@ForgotPasswordActivity,LoginActivity::class.java)
    startActivity(intent)
    finish()
}
    }
}