package com.cheng.apikit.util

import com.cheng.apikit.testmodel.User
import org.junit.Assert
import org.junit.Assert.assertTrue
import org.junit.Test

class JsonUtilTest {

    @Test
    fun testObjectToJson() {
        val pxOffer = User(
            id = 0,
            name = "First Last",
            email = "abc@gmail.com",
            gender = "M",
            status = "active"
        )
        val result = JsonUtil.objectToJson(pxOffer)

        Assert.assertTrue(result.contains("First Last"))
        Assert.assertTrue(result.contains("abc@gmail.com"))
    }

    @Test
    fun testJsonToObject() {
        var jsonString = ""
        var result = JsonUtil.jsonToObject<User>(jsonString)
        Assert.assertNull(result)

        jsonString = "{\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"First Last\",\n" +
                "    \"email\": \"abc@gmail.com\",\n" +
                "    \"gender\": \"M\",\n" +
                "    \"status\": \"active\"\n" +
                "  }"
        result = JsonUtil.jsonToObject<User>(jsonString)
        Assert.assertEquals(1, result?.id)
        Assert.assertEquals("First Last", result?.name)
        Assert.assertEquals("M", result?.gender)
        Assert.assertEquals("active", result?.status)
    }

    @Test
    fun testJsonToList() {
        var jsonString = "[\"apple\", \"banana\", \"orange\"]"
        var list: List<String?> = JsonUtil.jsonToList<String>(jsonString)
        assertTrue(list.size == 3)
        assertTrue(list.contains("apple"))

        jsonString = "[null, \"Hello\", null]"
        list = JsonUtil.jsonToList(jsonString)
        assertTrue(list.size == 3)
        assertTrue(list.contains("Hello"))
        assertTrue(list.contains(null))
    }

}
