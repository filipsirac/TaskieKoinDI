package hr.ferit.brunozoric.taskie.ui.activities.register

import hr.ferit.brunozoric.taskie.model.request.UserDataRequest

interface RegisterContract {

    interface View {
        fun onRegisterSuccessful()

        fun onRegisterFailed(error: String)
    }

    interface Presenter {

        fun setView(view: RegisterContract.View)

        fun onRegisterClicked(user: UserDataRequest)

    }
}