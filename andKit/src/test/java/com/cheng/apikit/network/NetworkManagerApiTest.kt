package com.cheng.apikit.network

import com.cheng.apikit.network.model.Success
import com.cheng.apikit.util.JsonUtil
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest= Config.NONE)
class NetworkManagerApiTest {

    inner class User(
        val id: Int,
        val name: String,
        val email: String,
        val gender: String,
        val status: String,
    )

    inner class GetUsersResponse(
        val data: List<User>
    )

    inner class CreateUserResponse(
        val data: User
    )

    private val testApiUrl = "https://gorest.co.in/public/v1/users"
    private val authenticationHeaders = mapOf(
        "Authorization" to "Bearer c67a8fdabfa51452882bd5f6be7e50780a7d8a15416538116088d40cf165966d"
    )

    @Ignore("This is integration test")
    @Test
    fun testGetApiRequest() {
        runBlocking {
            val result = NetworkManager.getApi(testApiUrl, authenticationHeaders)
            assertTrue(result is Success)
            val successResult = result as Success
            val getUsersResponse = JsonUtil.jsonToObject<GetUsersResponse>(successResult.value)
            assertNotNull(getUsersResponse!!.data)
            assertTrue(getUsersResponse.data.isNotEmpty())
        }
    }

    @Ignore("This is integration test")
    @Test
    fun testPostAndDelete() {
        runBlocking {
            var result = NetworkManager.postApi(
                testApiUrl,
                authenticationHeaders,
                mapOf(
                    "name" to "John McDonald",
                    "email" to "John123@McDonald.com",
                    "gender" to "male",
                    "status" to "active",
                )
            )
            assertTrue(result is Success)
            val successResult = result as Success
            val createUsersResponse = JsonUtil.jsonToObject<CreateUserResponse>(successResult.value)!!
            assertTrue(createUsersResponse.data.id > 0)

            result = NetworkManager.deleteApi(
                "${testApiUrl}/${createUsersResponse.data.id}",
                authenticationHeaders
            )
            assertTrue(result is Success)
        }
    }

}
