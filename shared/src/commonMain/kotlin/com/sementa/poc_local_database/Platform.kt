package com.sementa.poc_local_database

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform