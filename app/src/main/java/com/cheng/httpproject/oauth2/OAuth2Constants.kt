package com.cheng.httpproject.oauth2

class OAuth2Constants {
    companion object {
        val CLIENT_ID = "client_id"
        val CLIENT_SECRET = "client_secret"
        val ACCESS_TOKEN = "access_token"
        val REFRESH_TOKEN = "refresh_token"
        val USERNAME = "username"
        val PASSWORD = "password"
        val GRANT_TYPE = "grant_type"
        val SCOPE = "scope"
        val CODE = "code"
        val AUTHORIZATION_CODE = "authorization_code"
        val RESPONSE_TYPE = "response_type"
        val REDIRECT_URI = "redirect_uri"
        val REDIRECT_URI_VALUE = "com.cheng.httpproject.oauth:/oauth2redirect"
        val REDIRECT_URI_ROOT = "com.cheng.httpproject.oauth"
        val REQUEST_TOKEN_URL = "https://app-t.infoodle.com/apiv2/oauth2/token"
        val AUTHORIZATION_URL = "https://app-t.infoodle.com/apiv2/oauth2/authorise"
        val AUTHORIZATION = "Authorization"
        val EXPIRES_IN = "expires_in"
        val OAUTH2_CACHE = "OAuth2_cache"
    }
}