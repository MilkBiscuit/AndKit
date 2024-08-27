package com.cheng.andkit.util

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import junit.framework.TestCase.assertTrue
import kotlinx.serialization.Serializable
import log.LumberjackRule
import org.junit.ClassRule
import org.junit.Test

class JsonUtilTest {

    @Test
    fun `objectToJson - Given a user object, Then returns correct string`() {
        val user = User(
            id = 0,
            name = "First Last",
            email = "abc@gmail.com",
            gender = "M",
            status = "inactive"
        )
        val result = JsonUtil.objectToJson(user)

        assertEquals("""
{
    "id": 0,
    "name": "First Last",
    "email": "abc@gmail.com",
    "gender": "M",
    "status": "inactive"
}
        """.trimIndent(), result)
    }

    @Test
    fun `jsonToObject - Given an invalid JSON string, Then returns null`() {
        var result = JsonUtil.jsonToObject<User>("")
        assertNull(result)

        var jsonString = """
{
    "id": 1,
}
        """.trimIndent()
        result = JsonUtil.jsonToObject<User>(jsonString)
        assertNull(result)

        jsonString = """
 {
    "id": 1,
    "name": ["First Name", "Last Name"]
}           
        """.trimIndent()
        result = JsonUtil.jsonToObject<User>(jsonString)
        assertNull(result)
    }

    @Test
    fun `jsonToObject - Given a valid JSON string, Then returns correct object`() {
        val jsonString = """
{
  "id": 1,
  "name": "First Last",
  "email": "apple@gmail.com",
  "gender": "M",
  "status": "inactive"
}
        """.trimIndent()
        val result = JsonUtil.jsonToObject<User>(jsonString)
        assertEquals(1, result?.id)
        assertEquals("First Last", result?.name)
        assertEquals("apple@gmail.com", result?.email)
        assertEquals("M", result?.gender)
        assertEquals("inactive", result?.status)
    }

    @Test
    fun `jsonToObject - Given json string missing fields with default values, Then returns correct object`() {
        val jsonString = """
{
  "id": 1,
  "name": "First Last",
  "email": "apple@gmail.com",
  "gender": "M"
}
        """.trimIndent()
        val result = JsonUtil.jsonToObject<User>(jsonString)
        assertEquals(1, result?.id)
        assertEquals("First Last", result?.name)
        assertEquals("apple@gmail.com", result?.email)
        assertEquals("M", result?.gender)
        assertEquals("active", result?.status)
    }

    @Test
    fun `jsonToList - Given a valid JSON array, Then returns correct value`() {
        val jsonString = """
[
{
  "id": 1,
  "name": "First Name",
  "email": "first.name@gmail.com",
  "gender": "M",
  "status": "inactive"
},
{
  "id": 2,
  "name": "Second Name",
  "email": "second.name@gmail.com",
  "gender": "F",
  "status": "active"
}
]
        """.trimIndent()
        val list: List<User?> = JsonUtil.jsonToList<User>(jsonString)
        assertTrue(list.size == 2)

        assertEquals(
            User(
                id = 1,
                name = "First Name",
                email = "first.name@gmail.com",
                gender = "M",
                status = "inactive"
            ),
            list[0]
        )
        assertEquals(
            User(
                id = 2,
                name = "Second Name",
                email = "second.name@gmail.com",
                gender = "F",
                status = "active"
            ),
            list[1]
        )
    }

    companion object {
        @get:ClassRule
        @JvmStatic
        var lumberjackRule = LumberjackRule()
    }

}

@Serializable
private data class User(
    val id: Int,
    val name: String,
    val email: String,
    val gender: String,
    val status: String = "active",
)

