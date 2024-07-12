package com.healthcare.disease.data

import com.healthcare.disease.data.dashboard.dto.DiseaseProblemsListResponse
import retrofit2.http.GET


interface DashboardApiClient {

    @GET("77470b89-bbd4-4457-981e-3190f09856a6")
    suspend fun getDiseaseProblems(): DiseaseProblemsListResponse
}