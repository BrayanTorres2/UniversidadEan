package com.ean.universidadean

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // ...
        // Initialize Firebase Auth
        auth = Firebase.auth
        val txt_correo=findViewById<EditText>(R.id.text_usuario_am)
        val txt_contraseña=findViewById<EditText>(R.id.text_contrasena_am)
        val boton_entrar=findViewById<Button>(R.id.bn_entrar_am)
        boton_entrar.setOnClickListener {
            try {
                val correo=txt_correo.text.toString().lowercase()
                val contraseña=txt_contraseña.text.toString()
                if(correo.isEmpty()){
                    throw Exception("LOS CAMPOS NO PUEDEN ESTAR VACIOS")
                }
                else{
                        auth.signInWithEmailAndPassword(correo,contraseña)
                            .addOnCompleteListener(this) { task ->
                                if (task.isSuccessful) {
                                    Toast.makeText(this,"Bienvenido",Toast.LENGTH_SHORT).show()
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "signInWithCustomToken:success")

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(this,"No pudimos inicar sesion",Toast.LENGTH_SHORT).show()
                                    Log.w(TAG, "signInWithCustomToken:failure", task.exception)
                                    Toast.makeText(baseContext, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show()

                                }
                            }
                    }

            }
            catch (e:Exception){
                Toast.makeText(this,e.message,Toast.LENGTH_SHORT).show()
            }


        }
    }
}