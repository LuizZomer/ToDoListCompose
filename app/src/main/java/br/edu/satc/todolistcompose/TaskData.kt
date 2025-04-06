package br.edu.satc.todolistcompose

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.Update

@Entity
data class TaskData (
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "complete")
    val complete: Boolean
)

@Dao
interface TaskDao {
    @Query("SELECT * FROM taskData")
    fun getAll(): List<TaskData>

    @Query("SELECT * FROM taskData WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<TaskData>

    @Update
    fun updateAll(vararg  taks: TaskData)

    @Insert
    fun insertAll(vararg tasks: TaskData)

    @Delete
    fun delete(tasks: TaskData);
}

@Database(entities = [TaskData::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}