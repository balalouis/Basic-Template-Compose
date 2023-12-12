package user

import CoroutineTestRule
import com.basic.template.compose.userdetail.data.repo.UserDetailRepoImpl
import com.basic.template.compose.userdetail.domain.usecases.UserDetailUseCases
import com.basic.template.compose.userdetail.ui.UserDetailViewModel
import com.basic.template.network.model.NetworkResponse
import fake.FakeUserDetailDataSource
import fake.TestDataUtil
import org.junit.Rule
import org.junit.Test

class UserDetailViewModelTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()
    private lateinit var viewModel: UserDetailViewModel

    @Test
    fun testUserDetailNetworkSuccess() {
        val successFilename = "{\n" +
                "    \"data\": {\n" +
                "        \"id\": 2,\n" +
                "        \"email\": \"janet.weaver@reqres.in\",\n" +
                "        \"first_name\": \"Janet\",\n" +
                "        \"last_name\": \"Weaver\",\n" +
                "        \"avatar\": \"https://reqres.in/img/faces/2-image.jpg\"\n" +
                "    },\n" +
                "    \"support\": {\n" +
                "        \"url\": \"https://reqres.in/#support-heading\",\n" +
                "        \"text\": \"To keep ReqRes free, contributions towards server costs are appreciated!\"\n" +
                "    }\n" +
                "}"

        val dataSource = FakeUserDetailDataSource(isApiSuccess = true, fileName = successFilename)
        val repo = UserDetailRepoImpl(dataSource)
        val useCases = UserDetailUseCases(repo)
        viewModel = UserDetailViewModel(useCases)

        // Act
        val expectedData =
            TestDataUtil.getUserDetailSuccessResponseFromNetwork(
                successFilename
            ) as NetworkResponse.Success

        viewModel.fetchUserAndInsertIntoDB("2")
        coroutineTestRule.testDispatcher.scheduler.runCurrent()

        val actualData = viewModel.uiStateNetwork.value as NetworkResponse.Success
        assert(expectedData.data?.user?.id == actualData.data?.user?.id)
    }

    @Test
    fun testUserDetailDataBaseSuccess() {
        val successFilename = "{\n" +
                "    \"data\": {\n" +
                "        \"id\": 2,\n" +
                "        \"email\": \"janet.weaver@reqres.in\",\n" +
                "        \"first_name\": \"Janet\",\n" +
                "        \"last_name\": \"Weaver\",\n" +
                "        \"avatar\": \"https://reqres.in/img/faces/2-image.jpg\"\n" +
                "    },\n" +
                "    \"support\": {\n" +
                "        \"url\": \"https://reqres.in/#support-heading\",\n" +
                "        \"text\": \"To keep ReqRes free, contributions towards server costs are appreciated!\"\n" +
                "    }\n" +
                "}"

        val dataSource = FakeUserDetailDataSource(isApiSuccess = true, fileName = successFilename)
        val repo = UserDetailRepoImpl(dataSource)
        val useCases = UserDetailUseCases(repo)
        viewModel = UserDetailViewModel(useCases)

        // Act
        val expectedData =
            TestDataUtil.getUserDetailSuccessResponseFromDB(
                successFilename
            )

        viewModel.fetchRoomUserFromDBViaViewModel(2)
        coroutineTestRule.testDispatcher.scheduler.runCurrent()

        val actualData = viewModel.uiState.value as NetworkResponse.Success
        assert(expectedData.userId == actualData.data?.userId)
    }
}