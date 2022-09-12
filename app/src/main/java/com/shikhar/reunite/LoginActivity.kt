package com.shikhar.reunite

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged

class LoginActivity : AppCompatActivity() {
    //Initialising
    lateinit var ettxtmob: EditText
    lateinit var ettxtpass: EditText
    lateinit var btnlogin: Button
    lateinit var txtforgotpassword: TextView
    lateinit var txtRegister: TextView
    lateinit var sharedPreferences: SharedPreferences
   // lateinit var sessionManager: SessionManager
    val correctmobno = "9027529435"


    //Oncreate Function
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*Initialising the session variables*/

        sharedPreferences = getSharedPreferences(
            getString(R.string.preferences_file_name),
            Context.MODE_PRIVATE
        )
        val isLoggedin = sharedPreferences.getBoolean("isLoggedin", false)

        setContentView(R.layout.activity_login)


        if (isLoggedin) {
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        title = "Log In"

        //Extract Text
        ettxtmob= findViewById(R.id.etMobileNumber)
        ettxtpass = findViewById(R.id.txtpass1)
        btnlogin = findViewById(R.id.btnLogin)
        txtforgotpassword= findViewById(R.id.txtforgotpassword)
        txtRegister = findViewById(R.id.txtRegister)

       //errors
        ettxtmob.doOnTextChanged { text, start, before, count ->
            if(text!!.length > 10){
                ettxtmob.error="Only 10 digit mobile number"
            }else if(text.length < 10){
                ettxtmob.error=null
            }
        }
        ettxtpass.doOnTextChanged { text, start, before, count ->
            if(text!!.length > 14){
                ettxtpass.error="Enter password of maximum 14 characters"
            }
        }

        txtforgotpassword.setOnClickListener {
    val intent1=Intent(this@LoginActivity,ForgotPasswordActivity::class.java)
    startActivity(intent1)
    finish()
}
//Log In button
        btnlogin.setOnClickListener {

            val enteredmobilenumber= ettxtmob.text.toString()
            val enteredpassword = ettxtpass.text.toString()
            val password = arrayOf("Stark", "Hulk", "Captian America", "Shikhar")
            var nameOfAvengers = "Avengers"
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            if (enteredmobilenumber == correctmobno) {
                when (enteredpassword) {
                    password[0] -> {
                        nameOfAvengers = "Iron Man"
                        savedPreferences(nameOfAvengers)
                        startActivity(intent)
                    }
                    password[1] -> {
                        nameOfAvengers = "Hulk"
                        savedPreferences(nameOfAvengers)
                        startActivity(intent)
                    }
                    password[2] -> {
                        nameOfAvengers = "Captian America"
                        savedPreferences(nameOfAvengers)
                        startActivity(intent)
                    }
                    password[3] -> {
                        nameOfAvengers = "Shikhar"
                        savedPreferences(nameOfAvengers)
                        startActivity(intent)
                    }
                    else -> Toast.makeText(
                        this@LoginActivity,
                        "Incorrect Password",
                        Toast.LENGTH_LONG
                    ).show()
                }

            }
            else
            {
                Toast.makeText(this@LoginActivity, "Incorrect Credentials", Toast.LENGTH_LONG).show()
            }
        }
        txtRegister.setOnClickListener {
           val intent2=Intent(this@LoginActivity,RegisterationActivity::class.java)
            startActivity(intent2)
            finish()
        }
    }
    override fun onPause() {
        super.onPause()
        finish()
    }

    fun savedPreferences(title:String){
        sharedPreferences.edit().putBoolean("isLoggedin",true).apply()
        sharedPreferences.edit().putString("Title",title).apply()
    }
}


