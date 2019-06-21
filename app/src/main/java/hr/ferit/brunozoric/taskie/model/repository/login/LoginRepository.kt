package hr.ferit.brunozoric.taskie.data.repository

import hr.ferit.brunozoric.taskie.model.request.UserDataRequest
import hr.ferit.brunozoric.taskie.model.response.LoginResponse
import retrofit2.Call

interface LoginRepository {
    fun login(body: UserDataRequest): Call<LoginResponse>
}