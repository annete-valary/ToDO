package com.annete.task.data.util

import io.github.jan.supabase.auth.exception.AuthErrorCode
import io.github.jan.supabase.auth.exception.AuthRestException
import io.ktor.client.plugins.HttpRequestTimeoutException
import java.net.UnknownHostException

fun Throwable.toUserFriendlyMessage(): String {
    return when (this) {
        is AuthRestException -> {
            when (errorCode) {
                AuthErrorCode.UserNotFound, 
                AuthErrorCode.InvalidCredentials -> "Invalid email or password. Please try again."
                AuthErrorCode.EmailExists,
                AuthErrorCode.UserAlreadyExists -> "An account with this email already exists."
                AuthErrorCode.EmailNotConfirmed -> "Please confirm your email address before logging in."
                AuthErrorCode.OverRequestRateLimit,
                AuthErrorCode.OverEmailSendRateLimit,
                AuthErrorCode.OverSmsSendRateLimit -> "Too many attempts. Please try again in a few minutes."
                AuthErrorCode.WeakPassword -> "Password is too weak. Please use a stronger password."
                else -> "An authentication error occurred. Please try again."
            }
        }
        is UnknownHostException -> "No internet connection. Please check your network."
        is HttpRequestTimeoutException -> "Request timed out. Please try again later."
        else -> "An unexpected error occurred. Please try again."
    }
}
