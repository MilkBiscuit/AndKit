package com.cheng.andkit.sample.ui.activity.bible

import kotlinx.serialization.Serializable

@Serializable
data class BibleModel(val id: String,
                      val bibleId: String,
                      val bookId: String,
                      val reference: String,
                      val content: String,
                      val copyright: String)

@Serializable
data class BibleModelResult(val data: BibleModel)
