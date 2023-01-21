package com.example.data.auth

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class AuthRepository(
    private val authApi: AuthApi
) {
    fun postCertificationInfo(
        clientId: String,
        clientSecret: String,
        code: String
    ): Flow<AuthResponse> {
        return callbackFlow {
            send(
                authApi.postCertificationInfo(
                    clientId = clientId,
                    clientSecret = clientSecret,
                    code = code
                )
            )
            awaitClose {}
        }
    }
}