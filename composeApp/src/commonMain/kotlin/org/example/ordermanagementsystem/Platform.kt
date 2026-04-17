package org.example.ordermanagementsystem

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform