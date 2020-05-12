package com.picpay.desafio.android.util.ext

import com.picpay.desafio.android.data.dao.entities.UserTable
import com.picpay.desafio.android.data.entities.User

fun User.toUserTable(): UserTable{
    return UserTable(id, username, img, name)
}

fun UserTable.toUser() :User{
    return User(img, name, id, username)
}