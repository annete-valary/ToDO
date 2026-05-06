package com.annete.task.data.util

import io.github.jan.supabase.auth.exception.AuthErrorCode
import io.github.jan.supabase.auth.exception.AuthRestException

fun Throwable.toUserFriendlyMessage(): String {
    return when (this) {
        is AuthRestException -> {
            when (errorCode) {
                AuthErrorCode.UserNotFound, 
                AuthErrorCode.InvalidCredentials -> "Invalid email or password. Please try again."
                AuthErrorCode.EmailExists -> "An account with this email already exists."
                AuthErrorCode.EmailNotConfirmed -> "Please confirm your email address before logging in."
                AuthErrorCode.OverConfirmationLimit -> "Too many confirmation attempts. Please try again later."
                AuthErrorCode.TooManyRequests -> "Too many attempts. Please try again in a few minutes."
                AuthErrorCode.NetworkException -> "Network error. Please check your internet connection."
                else -> "An authentication error occurred. Please try again."
            }
        }
        else -> "An unexpected error occurred. Please check your internet and try again."
    }
}
