package com.example.userlist

import com.basic.template.network.model.NetworkResponse
import org.junit.Rule
import org.junit.Test

class UserListViewModelTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()
    private lateinit var viewModel: UserListViewModel

    @Test
    fun testLoginSuccess() {
        val success = "{\n" +
                "  \"page\": 2,\n" +
                "  \"per_page\": 6,\n" +
                "  \"total\": 12,\n" +
                "  \"total_pages\": 2,\n" +
                "  \"data\": [\n" +
                "    {\n" +
                "      \"id\": 7,\n" +
                "      \"email\": \"michael.lawson@reqres.in\",\n" +
                "      \"first_name\": \"Michael\",\n" +
                "      \"last_name\": \"Lawson\",\n" +
                "      \"avatar\": \"https://reqres.in/img/faces/7-image.jpg\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 8,\n" +
                "      \"email\": \"lindsay.ferguson@reqres.in\",\n" +
                "      \"first_name\": \"Lindsay\",\n" +
                "      \"last_name\": \"Ferguson\",\n" +
                "      \"avatar\": \"https://reqres.in/img/faces/8-image.jpg\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 9,\n" +
                "      \"email\": \"tobias.funke@reqres.in\",\n" +
                "      \"first_name\": \"Tobias\",\n" +
                "      \"last_name\": \"Funke\",\n" +
                "      \"avatar\": \"https://reqres.in/img/faces/9-image.jpg\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 10,\n" +
                "      \"email\": \"byron.fields@reqres.in\",\n" +
                "      \"first_name\": \"Byron\",\n" +
                "      \"last_name\": \"Fields\",\n" +
                "      \"avatar\": \"https://reqres.in/img/faces/10-image.jpg\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 11,\n" +
                "      \"email\": \"george.edwards@reqres.in\",\n" +
                "      \"first_name\": \"George\",\n" +
                "      \"last_name\": \"Edwards\",\n" +
                "      \"avatar\": \"https://reqres.in/img/faces/11-image.jpg\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 12,\n" +
                "      \"email\": \"rachel.howell@reqres.in\",\n" +
                "      \"first_name\": \"Rachel\",\n" +
                "      \"last_name\": \"Howell\",\n" +
                "      \"avatar\": \"https://reqres.in/img/faces/12-image.jpg\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"support\": {\n" +
                "    \"url\": \"https://reqres.in/#support-heading\",\n" +
                "    \"text\": \"To keep ReqRes free, contributions towards server costs are appreciated!\"\n" +
                "  }\n" +
                "}"
        val dataSource = FakeUserListDataSource(isApiSuccess = true, fileName = success)
        val repo = UserListRepoImpl(dataSource)
        val useCases = UserListUseCases(repo)
        viewModel = UserListViewModel(useCases)

        // Act
        val expectedData =
            TestDataUtil.getUserListRootSuccessResponse(
                success
            ) as NetworkResponse.Success

        viewModel.fetchUserListApiViaViewModel()
        coroutineTestRule.testDispatcher.scheduler.runCurrent()

        val actualData = viewModel.uiState.value as NetworkResponse.Success
        assert(actualData.data?.userModelList?.size == expectedData.data?.userModelList?.size)
    }

    @Test
    fun testLoginFailure() {
        val dataSource = FakeUserListDataSource(isApiSuccess = false)
        val repo = UserListRepoImpl(dataSource)
        val useCases = UserListUseCases(repo)
        viewModel = UserListViewModel(useCases)

        // Act
        val expectedData =
            TestDataUtil.getFailureResponse()

        viewModel.fetchUserListApiViaViewModel()
        coroutineTestRule.testDispatcher.scheduler.runCurrent()

        val actualData = viewModel.uiState.value as NetworkResponse.Failure
        assert(actualData.errorMessage == expectedData.errorMessage)
    }

}