package hr.ferit.brunozoric.taskie.data.repository

import hr.ferit.brunozoric.taskie.model.response.DeleteResponse
import retrofit2.Call

interface DeleteTaskRepository {
    fun deleteTask(id: String): Call<DeleteResponse>
}