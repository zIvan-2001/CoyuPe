package edu.tecsup.coyupe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.firebase.auth.FirebaseAuth
import edu.tecsup.coyupe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private lateinit var user: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        user = FirebaseAuth.getInstance()

        /** Funcion */
        // SignCessionFine()

        binding.btnLogin.setOnClickListener{
            registerUser()
        }

        binding.ButtoRegister.setOnClickListener{
            registerUserCoyuPe()
        }
    }

    private  fun registerUserCoyuPe(){
        startActivity(Intent(this, RegistroActivity::class.java))
    }

/** Funcion para que cuando cierres la aplicacion se mantenga el login del usuario*/
//    private fun SignCessionFine(){
//        if (user.currentUser !=null){
//            startActivity(Intent(this, HomeActivity::class.java))
//            finish()
//        }
//    }

    private fun registerUser(){

        val email = binding.CorreoEdit.text.toString()
        val password = binding.PassEdit.text.toString()

        if (email.isNotEmpty() && password.isNotEmpty()){

            user.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(MainActivity()) { task ->
                    if (task.isSuccessful){
                        Toast.makeText(this,
                            "User added successfully",
                            Toast.LENGTH_SHORT).show()

                        startActivity(Intent(this, HomeActivity::class.java))
                        finish()

                    }else{
                        user.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener {mtask ->
                                if (mtask.isSuccessful){
                                    startActivity(Intent(this, HomeActivity::class.java))
                                    finish()
                                }else{
                                    Toast.makeText(this,
                                        task.exception!!.message,
                                        Toast.LENGTH_SHORT).show()
                                }
                            }
                    }
                }
        }else{
            Toast.makeText(this,
                "El campo correo y contraseña estan vacios",
                Toast.LENGTH_SHORT).show()
        }
    }
}