package com.example.quizapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.quizapp.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        
        firebaseAuth = FirebaseAuth.getInstance()
        btnLogin.setOnClickListener{
            login()
        }

        btnSignUp.setOnClickListener{
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish() //It destroys the previous activity that we dont want
        }
    }
    private fun login(){
        val email = etEmailAddress.text.toString()
        val password = etPassword.text.toString()
        
        if(email.isBlank() || password.isBlank()){
            Toast.makeText(this, "Enter valid email or password", Toast.LENGTH_SHORT).show()
            return
        }
        
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this){
            if(it.isSuccessful){
                Toast.makeText(this, "Success", Toast.LENGTH_LONG).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent);
                finish()
            }else{
                Toast.makeText(this, "Auth is failed", Toast.LENGTH_LONG).show()
            }
        }
    }
}