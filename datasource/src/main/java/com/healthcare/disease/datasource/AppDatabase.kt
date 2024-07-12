package com.healthcare.disease.datasource

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.healthcare.disease.datasource.dao.DiseaseDao
import com.healthcare.disease.datasource.entity.DiseaseEntity
import com.healthcare.disease.datasource.entity.MedicationsClassesEntity

/**
 * The Room database for this app
 */
@Database(
    entities = [DiseaseEntity::class, MedicationsClassesEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getDiseaseDao(): DiseaseDao

    companion object {
        private const val DATABASE_NAME = "db_disease"

        // For Singleton instantiation
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .build()
        }
    }
}