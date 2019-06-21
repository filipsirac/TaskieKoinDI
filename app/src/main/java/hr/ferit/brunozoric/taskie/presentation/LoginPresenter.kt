package hr.ferit.brunozoric.taskie.presentation

import hr.ferit.brunozoric.taskie.domain.login.LoginUseCase
import hr.ferit.brunozoric.taskie.model.request.UserDataRequest
import hr.ferit.brunozoric.taskie.model.response.LoginResponse
import hr.ferit.brunozoric.taskie.prefs.SharedPrefsHelper
import hr.ferit.brunozoric.taskie.ui.activities.login.LoginContract

class LoginPresenter(private val loginUseCase: LoginUseCase): LoginContract.Presenter {

    private lateinit var view: LoginContract.View

    override fun setView(view: LoginContract.View) {
        this.view = view
    }

    override fun onUserLogin(user: UserDataRequest) {
        loginUseCase.execute(UserDataRequest(user.email, user.password), ::onLoginSuccessful, ::onLoginFailed)
    }

    private fun onLoginFailed(throwable: Throwable) {
        view.onLoginFailed(throwable.localizedMessage)
    }

    private fun onLoginSuccessful(loginResponse: LoginResponse) {
        view.onLoginSuccessful(loginResponse)
    }

  /*  private fun loginCallback(): Callback<LoginResponse> = object : Callback<LoginResponse> {
        override fun onFailure(call: Call<LoginResponse>?, t: Throwable?) {
        }

        override fun onResponse(call: Call<LoginResponse>?, response: Response<LoginResponse>) {
            if (response.isSuccessful) {
                when (response.code()) {
                    RESPONSE_OK -> handleOkResponse(response.body())
                    else -> handleSomethingWentWrong()
                }
            }
        }
    }

    private fun handleOkResponse(loginReponse: LoginResponse?) {
        loginReponse?.token?.let { prefs.storeUserToken(it) }
        view.onLoginSuccessful()
    }

    private fun handleSomethingWentWrong() = view.onLoginFailed()*/

}