package user

import CoroutineTestRule
import com.basic.template.compose.login.data.datasource.LoginDataSourceImpl
import com.basic.template.compose.registeration.data.datasource.RegistrationDataSourceImpl
import com.basic.template.compose.userlist.data.datasource.UserListDataSourceImpl
import com.basic.template.network.api.ApiWebService
import com.basic.template.network.model.LoginRequestModel
import com.basic.template.network.model.NetworkResponse
import com.basic.template.network.model.RegistrationRequestModel
import enqueueResponse
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class UserListDataSourceTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()
    private var mockWebServer: MockWebServer = MockWebServer()

    private val client = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.SECONDS)
        .readTimeout(1, TimeUnit.SECONDS)
        .writeTimeout(1, TimeUnit.SECONDS)
        .build()

    private val api = Retrofit.Builder()
        .baseUrl(mockWebServer.url("/"))
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiWebService::class.java)

    private val dataSourceImpl = UserListDataSourceImpl(api)

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun testUserListSuccess() {
        mockWebServer.enqueueResponse("user_list_success_response.json", 200)

        runBlocking {
            dataSourceImpl.fetchUserList()
                .catch {

                }
                .collect {
                    val actualData = it as NetworkResponse.Success
                    assert(actualData.data?.userModelList?.size == 6)
                }
        }
    }

}