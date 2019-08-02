package com.synergy.android.profile

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.lifecycle.LiveData
import com.synergy.android.login.LoginRepository
import com.synergy.android.model.entities.Resource
import com.synergy.android.model.network.NetworkBoundResource
import com.synergy.android.profile.entities.Profile
import com.synergy.android.profile.entities.ProfileResponse
import com.synergy.android.test_utils.InstantLiveDataExecutor
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.spekframework.spek2.Spek

object ProfileViewModelTest : Spek({
    val mainThreadSurrogate = newSingleThreadContext("UI thread")

    beforeEachTest {
        Dispatchers.setMain(mainThreadSurrogate)
        ArchTaskExecutor.getInstance().setDelegate(InstantLiveDataExecutor)
    }

    afterEachTest {
        ArchTaskExecutor.getInstance().setDelegate(null)
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    test("initial") {
        val expectedBoundResource = mockk<NetworkBoundResource<Profile, ProfileResponse>>()
        val expectedLiveData = mockk<LiveData<Resource<Profile>>>()
        val mockRepository = mockk<ProfileRepository>()
        val mockAuthenticationModel = mockk<LoginRepository>()
        every { mockRepository.loadProfile() } returns expectedBoundResource
        every { expectedBoundResource.asLiveData() } returns expectedLiveData

        val viewModel = ProfileViewModel(mockRepository, mockAuthenticationModel)

        Assert.assertEquals(viewModel.profileBoundResource, expectedBoundResource)
        Assert.assertEquals(viewModel.profileResponse, expectedLiveData)
    }

    test("Refresh") {
        val mockBoundResource = mockk<NetworkBoundResource<Profile, ProfileResponse>>()
        val mockRepository = mockk<ProfileRepository>()
        val mockAuthenticationModel = mockk<LoginRepository>()
        every { mockRepository.loadProfile() } returns mockBoundResource
        every { mockBoundResource.asLiveData() } returns mockk()
        every { mockBoundResource.fetchFromNetwork() } just Runs

        val viewModel = ProfileViewModel(mockRepository, mockAuthenticationModel)

        viewModel.updateProfile()

        verify { mockBoundResource.fetchFromNetwork() }
    }
})
