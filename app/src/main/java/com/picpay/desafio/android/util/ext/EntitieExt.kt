package com.picpay.desafio.android.util.ext

import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import com.picpay.desafio.android.R
import com.picpay.desafio.android.data.dao.entities.UserTable
import com.picpay.desafio.android.data.entities.User

fun User.toUserTable(): UserTable {
    return UserTable(id, username, img, name)
}

val navOptions = NavOptions.Builder()
    .setEnterAnim(R.anim.fade_in)
    .setExitAnim(R.anim.fade_out)
    .setPopEnterAnim(R.anim.fade_in)
    .setPopExitAnim(R.anim.fade_out).build()

fun UserTable.toUser(): User {
    return User(img, name, id, username)
}

fun NavController.navigateWithAnimation(destinationId: Int) {
    this.navigate(destinationId, null, navOptions)
}
fun NavController.navigateWithAnimation(navDirections: NavDirections) {
    this.navigate(navDirections, navOptions)
}