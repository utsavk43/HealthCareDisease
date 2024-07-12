package com.healthcare.disease.datasource.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "medications_classes")
data class MedicationsClassesEntity(
    @PrimaryKey
    val id: Int,
    val name: String?,
    val dose: String?,
    val strength: String?,
    val diseaseId: Int
)
