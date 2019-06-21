package hr.ferit.brunozoric.taskie.data.repository

import hr.ferit.brunozoric.taskie.model.response.DeleteResponse
import hr.ferit.brunozoric.taskie.networking.TaskieApiService
import retrofit2.Call

class DeleteTaskRepositoryImpl(private val authService: TaskieApiService): DeleteTaskRepository {
    override fun deleteTask(id: String): Call<DeleteResponse> = authService.delete(id)
}