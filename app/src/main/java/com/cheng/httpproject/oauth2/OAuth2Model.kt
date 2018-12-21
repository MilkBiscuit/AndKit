package com.cheng.httpproject.oauth2

data class OAuth2Detail(val clientId: String,
                        val clientSecret: String,
                        val accessToken: String,
                        val refreshToken: String,
                        val tokenType: String,
                        val expireAt: Long)