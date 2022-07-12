package com.marvelapp.utils

import java.security.MessageDigest

object HashUtils {
    fun generateHash(privateKey: String, publicKey: String, timeStamp: Long): String {
        return (timeStamp.toString() + privateKey + publicKey).convertToMD5()

    }

    private fun String.convertToMD5(): String {
        val md = MessageDigest.getInstance("MD5")
        val digested = md.digest(toByteArray())
        return digested.joinToString("") { String.format("%02x", it) }
    }
}