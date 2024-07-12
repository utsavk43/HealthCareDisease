package com.healthcare.disease.datasource.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.healthcare.disease.datasource.entity.DiseaseEntity
import com.healthcare.disease.datasource.entity.MedicationsClassesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DiseaseDao {

    @Upsert
    suspend fun insertDiseaseList(list: List<DiseaseEntity>)

    @Upsert
    suspend fun insertMedicationsClasses(list: List<MedicationsClassesEntity>)

    @Query("SELECT * FROM disease JOIN medications_classes ON disease.id = medications_classes.diseaseId")
    fun getDiseaseWithMedications(): Flow<Map<DiseaseEntity, List<MedicationsClassesEntity>>>

    @Query("SELECT * FROM medications_classes WHERE id=:medicationId")
    suspend fun getDiseaseDetail(medicationId: Int): MedicationsClassesEntity
}