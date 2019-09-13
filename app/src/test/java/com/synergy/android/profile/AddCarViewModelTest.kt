package com.synergy.android.profile

import androidx.lifecycle.Observer
import com.synergy.android.InstantExecutorExtension
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import org.junit.Assert
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantExecutorExtension::class)
internal class AddCarViewModelTest {
    @Test
    fun getEnableDoneTest_happyPath() {
        // arrange
        val viewModel = AddCarViewModel()
        val expectedEnableDone = true

        // act
        val actualEnableDone = viewModel.enableDone.value

        // assert
        assert(actualEnableDone == expectedEnableDone)
    }

    @Test
    fun getEnableDoneTest_numberIsInvalid_disabled() {
        // arrange
        val mockObserver = mockk<Observer<Boolean>>()
        val viewModel = AddCarViewModel()
        val expectedInvalidNumber = ""
        viewModel.number.postValue(expectedInvalidNumber)
        val expectedEnableDone = false
        every { mockObserver.onChanged(expectedEnableDone) } just Runs
        viewModel.enableDone.observeForever(mockObserver)

        // act
        val actualEnableDone = viewModel.enableDone.value

        // assert
        Assert.assertEquals(actualEnableDone, expectedEnableDone)
    }
}