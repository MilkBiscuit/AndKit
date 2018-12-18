package com.cheng.httpproject.model

data class BibleModel(val id: String,
                      val bibleId: String,
                      val bookId: String,
                      val reference: String,
                      val content: String,
                      val copyRight: String)

data class BibleModelResult(val data: BibleModel)