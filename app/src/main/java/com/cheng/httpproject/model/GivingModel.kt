package com.cheng.httpproject.model

data class GivingTransaction(val userId: String,
                             val userToken: String,
                             val ChannelPartnerId: String,
                             val amount: String,
                             val appealId: String,
                             val siteId: String,
                             val interval: String,
                             val startDate: String,
                             val isTransactionFeesIncluded: String)

data class GivingToken(val token: String)