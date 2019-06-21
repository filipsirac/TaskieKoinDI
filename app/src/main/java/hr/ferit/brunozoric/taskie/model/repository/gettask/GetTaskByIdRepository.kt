package hr.ferit.brunozoric.taskie.data.repository

import hr.ferit.brunozoric.taskie.model.BackendTask
import retrofit2.Call

interface GetTaskByIdRepository {
    fun getTask(id: String): Call<BackendTask>
}