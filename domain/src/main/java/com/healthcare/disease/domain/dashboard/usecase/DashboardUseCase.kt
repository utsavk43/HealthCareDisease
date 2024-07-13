package com.healthcare.disease.domain.dashboard.usecase

import com.healthcare.disease.domain.dashboard.repository.DashboardRepository
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

class DashboardUseCase @Inject constructor(
    private val dashboardRepository: DashboardRepository,

) {

    suspend fun diseaseProblems() = dashboardRepository.diseaseProblems()

    fun getListOfDiseaseWithMedications() = dashboardRepository.getDiseaseProblems()

    fun getGreetings(hour: Int = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)): Int {
        return when (hour) {
            in 3..12 -> {
                1
            }

            in 12..16 -> {
                2
            }

            else -> {
                3
            }
        }
    }

}