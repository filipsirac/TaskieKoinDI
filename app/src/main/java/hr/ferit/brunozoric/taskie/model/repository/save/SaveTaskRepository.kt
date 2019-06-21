package hr.ferit.brunozoric.taskie.data.repository

import hr.ferit.brunozoric.taskie.model.BackendTask
import hr.ferit.brunozoric.taskie.model.request.AddTaskRequest
import retrofit2.Call

interface SaveTaskRepository {
    fun save(body: AddTaskRequest): Call<BackendTask>
}