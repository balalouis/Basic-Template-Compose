package com.basic.template.compose.appbar

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class AppBarActionData(
    @DrawableRes val icon: Int,
    @StringRes val description: Int,
    val onClick: () -> Unit
)
