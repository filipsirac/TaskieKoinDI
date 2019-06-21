package hr.ferit.brunozoric.taskie.data.repository

import hr.ferit.brunozoric.taskie.model.request.UserDataRequest
import hr.ferit.brunozoric.taskie.model.response.RegisterResponse
import retrofit2.Call

interface RegisterRepository {
    fun register(body: UserDataRequest): Call<RegisterResponse>
}