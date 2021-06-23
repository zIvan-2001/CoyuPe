package edu.tecsup.coyupe

import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.google.firebase.auth.FirebaseAuth
import edu.tecsup.coyupe.databinding.ActivityHomeBinding
import edu.tecsup.coyupe.databinding.ActivityMainBinding

class   HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var user: FirebaseAuth
    private lateinit var toogle: ActionBarDrawerToggle

//    var ButtonSalir:Button=findViewById(R.id.ButtonSalir)
//    var NombreId:TextView=findViewById(R.id.NombreId)
//    var EmailUser:TextView=findViewById(R.id.EmailUsers)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toogle = ActionBarDrawerToggle(this, binding.draweLayout, R.string.open_drawer, R.string.close_drawer)
        binding.draweLayout.addDrawerListener(toogle)

        toogle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.NavContainer.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_home ->   {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragmentContainer,HomeFragment() )
                        commit()
                    }
                }

                R.id.nav_contact ->{
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragmentContainer, ContactFragment())
                        commit()
                    }
                }

                R.id.nav_help ->{
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragmentContainer, HelpFragment())
                        commit()
                    }
                }

                R.id.nav_ubication ->{
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragmentContainer, LocationFragment())
                        commit()
                    }
                }

                R.id.nav_carrito ->{
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragmentContainer, ShoppingFragment())
                        commit()
                    }
                }

                R.id.nav_SignUp -> Toast.makeText(this, "Salte D:", Toast.LENGTH_SHORT).show()

            }
            binding.draweLayout.closeDrawer(GravityCompat.START)
            true
        }

//        user = FirebaseAuth.getInstance()
//
//        if (user.currentUser != null){
//            user.currentUser?.let {
//
//                binding.NombreId.text = it.email
//            }
//        }
//        binding.ButtonSalir.setOnClickListener{
//            user.signOut()
//            startActivity(
//                Intent(
//                    this,
//                    MainActivity::class.java
//                )
//            )
//            finish()
//        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toogle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}