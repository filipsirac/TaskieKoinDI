package hr.ferit.brunozoric.taskie.presentation

import hr.ferit.brunozoric.taskie.domain.registration.RegisterUseCase
import hr.ferit.brunozoric.taskie.model.request.UserDataRequest
import hr.ferit.brunozoric.taskie.model.response.RegisterResponse
import hr.ferit.brunozoric.taskie.ui.activities.register.RegisterContract

class RegisterPresenter(private val registerUseCase: RegisterUseCase): RegisterContract.Presenter {

    private lateinit var view: RegisterContract.View

    override fun setView(view: RegisterContract.View) {
        this.view = view
    }

    override fun onRegisterClicked(user: UserDataRequest) {
        registerUseCase.execute(UserDataRequest(user.email, user.password, user.name), ::onRegisterSuccessful, ::onRegisterFailed)
    }

    private fun onRegisterFailed(throwable: Throwable) {
        view.onRegisterFailed(throwable.localizedMessage)
    }

    private fun onRegisterSuccessful(registerResponse: RegisterResponse) {
        view.onRegisterSuccessful()
    }

   /* private fun registerCallback(): Callback<RegisterResponse> = object : Callback<RegisterResponse> {
        override fun onFailure(call: Call<RegisterResponse>?, t: Throwable?) {
        }

        override fun onResponse(call: Call<RegisterResponse>?, response: Response<RegisterResponse>) {
            if (response.isSuccessful) {
                when (response.code()) {
                    RESPONSE_OK -> handleOkResponse()
                    else -> handleSomethingWentWrong()
                }
            }
        }
    }

    private fun handleOkResponse() = view.onRegisterSuccessful()

    private fun handleSomethingWentWrong() = view.onRegisterFailed()
*/
}