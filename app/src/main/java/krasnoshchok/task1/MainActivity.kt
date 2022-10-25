package krasnoshchok.task1

import krasnoshchok.task1.auth.AuthActivity
import krasnoshchok.task1.databinding.ActivityMainBinding
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    /**
     * Expand the intent with 2 additional parameters: firstname and lastname
     */
    companion object {
        private const val FIRST_NAME_ARG = "FIRST_NAME_ARG"
        private const val LAST_NAME_ARG = "LAST_NAME_ARG"

        fun getIntent(context: Context, firstName: String, lasName: String): Intent {
            return Intent(context, MainActivity::class.java).apply {
                putExtra(FIRST_NAME_ARG, firstName)
                putExtra(LAST_NAME_ARG, lasName)
            }
        }
    }

    private val firstName: String
        get() = intent.getStringExtra(FIRST_NAME_ARG)!!
    private val lastName: String
        get() = intent.getStringExtra(LAST_NAME_ARG)!!

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonEditProfile.setOnClickListener {
            startActivity(Intent(this, AuthActivity::class.java))
        }
        //We replace the username with what was entered in the e-mail
        binding.tvUserName.text = "$firstName $lastName"
       }
}