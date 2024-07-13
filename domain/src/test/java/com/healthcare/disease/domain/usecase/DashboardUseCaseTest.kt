package com.healthcare.disease.domain.usecase

import com.healthcare.disease.domain.dashboard.model.DiseaseModel
import com.healthcare.disease.domain.dashboard.repository.DashboardRepository
import com.healthcare.disease.domain.dashboard.usecase.DashboardUseCase
import kotlinx.coroutines.flow.Flow
import org.junit.Test
import kotlin.test.assertEquals

class DashboardUseCaseTest {
    private val fakeRepository = object : DashboardRepository {
        override suspend fun diseaseProblems() {
            TODO("Not yet implemented")
        }

        override fun getDiseaseProblems(): Flow<Map<String, List<DiseaseModel>>> {
            TODO("Not yet implemented")
        }
    }

    @Test
    fun `getting good morning for morning hours`() {
        val result = DashboardUseCase(fakeRepository).getGreetings(10)
        assertEquals(expected = 1, actual = result)
    }


}