package hr.ferit.brunozoric.taskie.data.repository

import hr.ferit.brunozoric.taskie.model.BackendTask
import hr.ferit.brunozoric.taskie.model.request.UpdateTaskRequest
import retrofit2.Call

interface EditTaskRepository {
    fun editTask(body: UpdateTaskRequest): Call<BackendTask>
}