package com.cheng.httpproject.constant

import java.net.HttpURLConnection

class ApiConstants {
    companion object {
        val HTTP_OK = HttpURLConnection.HTTP_OK
        val HTTP_FORBIDDEN = HttpURLConnection.HTTP_FORBIDDEN
        val HTTP_UNAUTHORIZED = HttpURLConnection.HTTP_UNAUTHORIZED
        val JSON_CONTENT = "application/json"
        val XML_CONTENT = "application/xml"
        val URL_ENCODED_CONTENT = "application/x-www-form-urlencoded"
    }
}