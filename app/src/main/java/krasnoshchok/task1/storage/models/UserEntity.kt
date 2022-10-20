package krasnoshchok.task1.storage.models

/**
 * A class with user data
 */
data class UserEntity(
    val firstname: String,
    val lastname: String,
    val email: String,
    val password: String
)