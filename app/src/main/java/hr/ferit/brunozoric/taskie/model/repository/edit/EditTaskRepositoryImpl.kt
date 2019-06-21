package hr.ferit.brunozoric.taskie.data.repository

import hr.ferit.brunozoric.taskie.model.BackendTask
import hr.ferit.brunozoric.taskie.model.request.UpdateTaskRequest
import hr.ferit.brunozoric.taskie.networking.TaskieApiService
import retrofit2.Call

class EditTaskRepositoryImpl(private val authService: TaskieApiService): EditTaskRepository {
    override fun editTask(body: UpdateTaskRequest): Call<BackendTask> = authService.editTask(body)
}