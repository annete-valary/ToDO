package com.annete.task.data.remote

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.postgrest.Postgrest

object SupabaseClient {
    val client = createSupabaseClient(
        supabaseUrl = "https://cnrqknuaznurdoxbjkgl.supabase.co",
        supabaseKey = "sb_publishable_O5pFZwqBsSCztFbCyR9hVQ_g1_EBWKe"
    ) {
        install(Auth)
        install(Postgrest)

    }
}
