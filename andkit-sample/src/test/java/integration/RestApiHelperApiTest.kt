package integration

import com.cheng.andkit.network.RestApiHelper
import com.cheng.andkit.util.JsonUtil
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.Serializable
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class RestApiHelperApiTest {

    @Test
    fun `getApi - returns success result`() = runTest {
        val result = RestApiHelper.getApi(TEST_URL, AUTH_HEADERS)
        assertTrue(result.isSuccess)

        val successResult = result.getOrThrow()
        println("GET result is $successResult")
        val getUsersResponse = JsonUtil.jsonToObject<GetUsersResponse>(successResult)
        assertNotNull(getUsersResponse!!.data)
        assertTrue(getUsersResponse.data.isNotEmpty())
    }

    @Test
    fun `postApi - returns success result`() = runTest {
        val bodyJson = JsonUtil.objectToJson(mapOf(
            "name" to "John McDonald",
            "email" to "John123@McDonald.com",
            "gender" to "male",
            "status" to "active",
        ))
        val result = RestApiHelper.postApi(
            url = TEST_URL,
            headers = AUTH_HEADERS,
            bodyJson = bodyJson,
        )
        assertTrue(result.isSuccess)
        val successResult = result.getOrThrow()
        println("POST result is $successResult")
        val createUsersResponse = JsonUtil.jsonToObject<CreateUserResponse>(successResult)!!
        assertTrue(createUsersResponse.data.id > 0)
    }

    @Test
    fun `deleteApi - Given some user data is provided, When delete one, Then newly fetched result does not contain the deleted`() = runTest {
        val fetchUserResult = RestApiHelper.getApi(TEST_URL, AUTH_HEADERS)
        assertTrue(fetchUserResult.isSuccess)
        val successResult = fetchUserResult.getOrThrow()
        val fetchedUsers = JsonUtil.jsonToObject<GetUsersResponse>(successResult)!!.data
        val firstUser = fetchedUsers.first()
        println("fetched users: $fetchedUsers")

        val deleteResult = RestApiHelper.deleteApi(
            "$TEST_URL/${firstUser.id}",
            AUTH_HEADERS
        )
        assertTrue(deleteResult.isSuccess)

        val newlyFetchUserResult = RestApiHelper.getApi(TEST_URL, AUTH_HEADERS)
        val newlyFetchedUsers = JsonUtil.jsonToObject<GetUsersResponse>(newlyFetchUserResult.getOrThrow())!!.data
        assertTrue(newlyFetchedUsers.none { it.id == firstUser.id })
        println("newly fetched users: $newlyFetchedUsers")
    }

    @Serializable
    private data class User(
        val id: Int,
        val name: String,
        val email: String,
        val gender: String,
        val status: String,
    )

    @Serializable
    private data class GetUsersResponse(
        val data: List<User>
    )

    @Serializable
    private data class CreateUserResponse(
        val data: User
    )

    companion object {
        private const val TEST_URL = "https://gorest.co.in/public/v1/users"
        private val AUTH_HEADERS = mapOf(
            "Authorization" to "Bearer c67a8fdabfa51452882bd5f6be7e50780a7d8a15416538116088d40cf165966d"
        )
    }

}
