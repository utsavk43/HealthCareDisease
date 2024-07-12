package com.healthcare.disease.datasource.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "disease")
data class DiseaseEntity(
    @PrimaryKey
    val id: Int,
    val name: String
)
