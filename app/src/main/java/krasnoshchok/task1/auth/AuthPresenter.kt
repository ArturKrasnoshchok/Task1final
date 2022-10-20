package krasnoshchok.task1.auth

import krasnoshchok.task1.MainActivity
import krasnoshchok.task1.storage.UserStore
import krasnoshchok.task1.storage.models.UserEntity
import android.content.Context
import java.util.*

class AuthPresenter(
    private val view: AuthView,
    private val context: Context,
    private val store: UserStore,
) {

    init {
        val currentUser = store.getCurrentUser()
        if (currentUser != null) {
            navigateToMain(currentUser)
        }
    }

    /**
     * Transition to the main activity.
     */
    private fun navigateToMain(userEntity: UserEntity) {
        context.startActivity(MainActivity.getIntent(context, userEntity.firstname, userEntity.lastname))
    }

    private var shouldRememberUser: Boolean = false

    /** The function checks whether the e-mail address and password are entered correctly.
     *  If the check passes, it goes to the main activity.
     */
    fun sighUp(email: String, password: String) {
        if (!checkEmail(email)) {
            view.showIncorrectEmail()
        } else if (!checkPassword(password)) {
            view.showIncorrectPassword()
        } else {
            performSignUp(email, password)
        }
    }

    /** The method accepts an e-mail and a password. Parses email and saves data to shared preferences if the user chooses to save data.
     *  Transition to the main activity.
     */
    private fun performSignUp(email: String, password: String) {
        val (firsName, lastName) = parseUser(email)
        val userEntity = UserEntity(firsName, lastName, email, password)
        if (shouldRememberUser) {
            store.saveUser(userEntity)
        }
        navigateToMain(userEntity)
    }

    /** Convert e-mail to username
     *
     */
    private fun parseUser(email: String): Pair<String, String> {
        val firstName = email.substringBefore(".").capitalize(Locale.getDefault())
        val lastName = email.substringAfter(".").substringBefore("@").capitalize(Locale.getDefault())
        return firstName to lastName
    }

    /**
     * The function allows you to save user data for the next automatic login to the program.
     */
    fun rememberUser(value: Boolean) {
        shouldRememberUser = value
    }

    /**
     * The method checks the correctness of filling out the e-mail.
     */
    private fun checkEmail(email: String): Boolean {
        return email.contains("@") && email.contains(".") &&
                email.contains(" ").not()
    }

    /**
     * The method checks the correctness of the password entry.
     */
    private fun checkPassword(password: String): Boolean {
        return password.any { it.isDigit() } && password.any { it.isLetter() }
    }
}