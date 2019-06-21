package hr.ferit.brunozoric.taskie.data.repository

import hr.ferit.brunozoric.taskie.model.response.GetTasksResponse
import retrofit2.Call

interface GetTasksRepository {
    fun getAllTasks(): Call<GetTasksResponse>
}