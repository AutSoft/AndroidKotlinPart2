package hu.aut.security.demo.model

data class User(
        var id: Long = 0,
        var username: String? = null,
        var password: String? = null,
        var welcomeMessage: String? = null)
