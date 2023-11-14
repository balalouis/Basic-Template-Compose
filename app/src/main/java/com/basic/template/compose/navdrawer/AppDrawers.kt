package com.basic.template.compose.navdrawer

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.basic.template.compose.R

object DrawerParams {
    val drawerButtons = arrayListOf(
        AppDrawerItemInfo(
            R.string.drawer_home,
            R.drawable.ic_home,
            R.string.drawer_home_description
        ),
        AppDrawerItemInfo(
            R.string.drawer_settings,
            R.drawable.ic_settings,
            R.string.drawer_settings_description
        ),
        AppDrawerItemInfo(
            R.string.drawer_about,
            R.drawable.ic_info,
            R.string.drawer_info_description
        )
    )
}

data class AppDrawerItemInfo(
    @StringRes val title: Int,
    @DrawableRes val drawableId: Int,
    @StringRes val descriptionId: Int
)