package com.cheng.httpproject.util

import com.cheng.httpproject.model.PxOffer
import org.junit.Assert.assertTrue
import org.junit.Test

class JsonUtilTest {

    @Test
    fun testObjectToJson() {
        val pxOffer = PxOffer(
                id = 220,
                description = "qqq",
                title = "aaa",
                venueIds = listOf(2417)
        )

        val result = JsonUtil.objectToJson(pxOffer)

        assertTrue(result.contains("220"))
        assertTrue(result.contains("qqq"))
        assertTrue(result.contains("aaa"))
        assertTrue(result.contains("2417"))
    }

    @Test
    fun testJsonToObject() {
        val jsonString = "{\n" +
                "  \"id\": 220,\n" +
                "  \"title\": \"aaa\",\n" +
                "  \"description\": \"qqq\",\n" +
                "  \"isMerchantFavourite\": false,\n" +
                "  \"paymentAmount\": 0.0,\n" +
                "  \"paymentTaxRate\": 0.0,\n" +
                "  \"redemptionCount\": 0,\n" +
                "  \"venueIds\": [\n" +
                "    2417\n" +
                "  ]\n" +
                "}"

        val result = JsonUtil.jsonToObject<PxOffer>(jsonString, PxOffer::class.java)
        assertTrue(result?.id == 220)
        assertTrue(result?.title == "aaa")
        assertTrue(result?.description == "qqq")
        assertTrue(result?.venueIds!!.contains(2417))
    }

}
