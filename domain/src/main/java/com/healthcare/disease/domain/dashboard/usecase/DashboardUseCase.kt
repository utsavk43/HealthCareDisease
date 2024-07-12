package com.healthcare.disease.domain.dashboard.usecase

import com.healthcare.disease.domain.dashboard.repository.DashboardRepository
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

class DashboardUseCase @Inject constructor(
    private val dashboardRepository: DashboardRepository
) {

    suspend fun diseaseProblems() = dashboardRepository.diseaseProblems()
    fun getListOfDiseaseWithMedications() = dashboardRepository.getDiseaseProblems()
    fun getGreetings(): Int {
        val date = Date()
        val cal: Calendar = Calendar.getInstance()
        cal.setTime(date)
        val hour: Int = cal.get(Calendar.HOUR_OF_DAY)

        return when (hour) {
            in 5..12 -> {
                1
            }

            in 12..26 -> {
                2
            }

            else -> {
                3
            }
        }
    }

}