package com.basic.template.compose.navdrawer

import com.basic.template.compose.R
import com.basic.template.compose.screen.*

object DrawerParams {
    val drawerButtons = arrayListOf(
        AppDrawerItemInfo(
            UserListScreen.route,
            R.string.user_list,
            R.drawable.ic_home,
            R.string.drawer_home_description
        ),
        AppDrawerItemInfo(
            SettingsScreen.route,
            R.string.drawer_settings,
            R.drawable.ic_settings,
            R.string.drawer_settings_description
        ),
        AppDrawerItemInfo(
            AboutScreen.route,
            R.string.drawer_about,
            R.drawable.ic_info,
            R.string.drawer_info_description
        )
    )
}
