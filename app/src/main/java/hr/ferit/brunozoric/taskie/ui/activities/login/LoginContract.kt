package hr.ferit.brunozoric.taskie.ui.activities.login

import hr.ferit.brunozoric.taskie.model.request.UserDataRequest
import hr.ferit.brunozoric.taskie.model.response.LoginResponse

interface LoginContract {

    interface View{

        fun onLoginSuccessful(loginResponse: LoginResponse)

        fun onLoginFailed(error: String)

    }

    interface Presenter{

        fun setView(view: LoginContract.View)

        fun onUserLogin(user: UserDataRequest)

    }
}