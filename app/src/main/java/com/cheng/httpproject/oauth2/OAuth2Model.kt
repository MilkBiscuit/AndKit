package com.cheng.httpproject.oauth2

data class OAuth2Detail(val clientId: String,
                        val clientSecret: String,
                        var accessToken: String,
                        var refreshToken: String,
                        val tokenType: String,
                        var expireAt: Long)

data class RefreshTokenBody(val refresh_token: String,
                            val client_id: String,
                            val client_secret: String,
                            val grant_type: String = OAuth2Constants.REFRESH_TOKEN)

data class RefreshTokenResult(val access_token: String,
                              val expires_in: String,
                              val token_type: String,
                              val scope: String,
                              val refresh_token: String)
