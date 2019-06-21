package hr.ferit.brunozoric.taskie.data.repository

import hr.ferit.brunozoric.taskie.model.request.UserDataRequest
import hr.ferit.brunozoric.taskie.model.response.RegisterResponse
import hr.ferit.brunozoric.taskie.networking.TaskieApiService
import retrofit2.Call

class RegisterRepositoryImpl(private val authService: TaskieApiService) : RegisterRepository {
    override fun register(body: UserDataRequest): Call<RegisterResponse> = authService.register(body)
}