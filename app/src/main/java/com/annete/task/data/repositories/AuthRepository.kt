package com.annete.task.data.repositories

import io.github.jan.supabase.auth.user.UserInfo

interface AuthRepository {
    suspend fun signUp(email: String, password: String, fullName: String): Result<Unit>
    suspend fun login(email: String, password: String): Result<Unit>
    suspend fun logout(): Result<Unit>
    suspend fun resetPassword(email: String): Result<Unit>
    fun getCurrentUser(): UserInfo?
}
