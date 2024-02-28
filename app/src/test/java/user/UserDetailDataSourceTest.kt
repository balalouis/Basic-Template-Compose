package user

import CoroutineTestRule
import com.basic.template.compose.userdetail.data.datasource.UserDetailDataSourceImpl
import com.basic.template.network.api.ApiWebService
import com.basic.template.network.model.NetworkResponse
import com.basic.template.room.UserDao
import enqueueResponse
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class UserDetailDataSourceTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()
    private var mockWebServer: MockWebServer = MockWebServer()

    @RelaxedMockK
    lateinit var userDao: UserDao

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

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun testUserListSuccess() {
        mockWebServer.enqueueResponse("user_detail_success_response.json", 200)
        val dataSourceImpl = UserDetailDataSourceImpl(userDao, api)

        runBlocking {
            dataSourceImpl.fetchAndInsertUserIntoDB("8")
                .catch {

                }
                .collect {
                    val actualData = it as NetworkResponse.Success
                    assert(actualData.data?.user?.userFirstName == "Arunkumar")
                }
        }
    }

}