package com.healthcare.disease.domain.login.usecase

import java.util.regex.Matcher
import java.util.regex.Pattern
import javax.inject.Inject


class PasswordValidationUseCase @Inject constructor() {

    fun isPasswordValid(password: String): Boolean {
        val pattern: Pattern
        val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$"
        pattern = Pattern.compile(passwordPattern)
        val matcher: Matcher = pattern.matcher(password)

        return matcher.matches()
    }
}