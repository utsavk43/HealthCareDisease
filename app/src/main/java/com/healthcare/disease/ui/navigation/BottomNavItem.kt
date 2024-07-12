package com.healthcare.disease.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.healthcare.disease.R

enum class BottomNavItem(
    @StringRes val title: Int,
    val screenRoute: String,
    val isVisible: Boolean = true
) {
    LOGIN(R.string.nav_login, "login"),
    DASHBOARD(R.string.nav_dashboard, "dashboard")
}
