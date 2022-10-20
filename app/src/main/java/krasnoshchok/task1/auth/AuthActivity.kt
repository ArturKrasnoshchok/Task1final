package krasnoshchok.task1.auth

import krasnoshchok.task1.databinding.ActivityAuthBinding
import krasnoshchok.task1.storage.UserStore
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.*

class AuthActivity : AppCompatActivity(), AuthView {

    private lateinit var binding: ActivityAuthBinding
    private lateinit var presenter: AuthPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = AuthPresenter(
            this,
            this,
            UserStore(this)
        )

        binding.registerButton.setOnClickListener {
            presenter.sighUp(binding.emailText.text.toString().lowercase(Locale.ROOT), binding.passwordText.text.toString().lowercase(Locale.ROOT))

        }
        binding.rememberMeCheckBox.setOnCheckedChangeListener { _, isChecked ->
            presenter.rememberUser(isChecked)
        }
    }

    override fun showIncorrectEmail() {
        binding.emailText.error = "Incorrect e-mail"
    }

    override fun showIncorrectPassword() {
        binding.passwordText.error = "Incorrect password"
    }
}