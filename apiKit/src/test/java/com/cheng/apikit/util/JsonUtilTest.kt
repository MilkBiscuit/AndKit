package com.cheng.apikit.util

import com.cheng.apikit.testmodel.User
import com.google.gson.JsonSyntaxException
import com.google.gson.stream.MalformedJsonException
import org.junit.Assert
import org.junit.Test

class JsonUtilTest {

    @Test
    fun `Given a user object Then objectToJson works`() {
        val user = User(
            id = 0,
            name = "First Last",
            email = "abc@gmail.com",
            gender = "M",
            status = "active"
        )
        val result = JsonUtil.objectToJson(user)

        Assert.assertTrue(result.contains("First Last"))
        Assert.assertTrue(result.contains("abc@gmail.com"))
    }

    @Test
    fun `Given a valid JSON string Then jsonToObject works`() {
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
    fun `Given an invalid JSON object Then jsonToObject throws exception`() {
        var jsonString = """
{
    "id": 1,
}
        """.trimIndent()
        var throwable = Assert.assertThrows(JsonSyntaxException::class.java) {
            JsonUtil.jsonToObject<User>(jsonString)
        }
        Assert.assertTrue(throwable.cause is MalformedJsonException)

        jsonString = """
 {
    "id": 1,
    "name": ["First Name", "Last Name"]
}           
        """.trimIndent()
        throwable = Assert.assertThrows(JsonSyntaxException::class.java) {
            JsonUtil.jsonToObject<User>(jsonString)
        }
        Assert.assertTrue(throwable.message!!.contains("Expected a string but was BEGIN_ARRAY"))
    }

    @Test
    fun `Given a JSON array of strings Then jsonToList works`() {
        var jsonString = """
["apple", "banana", "orange"]
        """.trimIndent()
        var list: List<String?> = JsonUtil.jsonToList<String>(jsonString)
        Assert.assertTrue(list.size == 3)
        Assert.assertTrue(list.contains("apple"))

        jsonString = "[null, \"Hello\", null]"
        list = JsonUtil.jsonToList(jsonString)
        Assert.assertTrue(list.size == 3)
        Assert.assertTrue(list.contains("Hello"))
        Assert.assertTrue(list.contains(null))
    }

}
