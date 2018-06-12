package com.drama.githubsearch.view.model.mapper

import com.drama.domain.User
import com.drama.githubsearch.view.model.ItemUser

object Mapper {

    fun convertToItemUser(user: User): ItemUser {
        return ItemUser(user.name, user.ImageUrl, user.isFavorite)
    }
}