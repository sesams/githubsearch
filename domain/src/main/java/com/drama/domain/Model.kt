package com.drama.domain

class User(val name: String,
           val ImageUrl: String,
           var isFavorite: Boolean)

class Request(val name: String, val page: Int)