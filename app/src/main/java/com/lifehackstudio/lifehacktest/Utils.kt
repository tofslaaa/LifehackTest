package com.lifehackstudio.lifehacktest

fun String.toPhoneNumber(): String {
    return if (this.isNotEmpty()) {
        val reversed = this.reversed()
        val replaced =
            reversed.replaceFirst("(\\d{2})(\\d{2})(\\d{3})(\\d{3})".toRegex(), "$1-$2-$3-$4-7+")
        replaced.substring(0, 16).reversed()
    } else ""
}