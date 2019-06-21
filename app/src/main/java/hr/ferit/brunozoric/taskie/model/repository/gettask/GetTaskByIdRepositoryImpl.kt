package hr.ferit.brunozoric.taskie.data.repository

import hr.ferit.brunozoric.taskie.model.BackendTask
import hr.ferit.brunozoric.taskie.networking.TaskieApiService
import retrofit2.Call

class GetTaskByIdRepositoryImpl(private val authService: TaskieApiService): GetTaskByIdRepository{
    override fun getTask(id: String): Call<BackendTask> = authService.getTaskById(id)
}