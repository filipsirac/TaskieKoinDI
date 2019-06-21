package hr.ferit.brunozoric.taskie.ui.activities.register

import android.content.Intent
import hr.ferit.brunozoric.taskie.R
import hr.ferit.brunozoric.taskie.common.displayToast
import hr.ferit.brunozoric.taskie.common.onClick
import hr.ferit.brunozoric.taskie.model.request.UserDataRequest
import hr.ferit.brunozoric.taskie.model.response.RegisterResponse
import hr.ferit.brunozoric.taskie.presentation.RegisterPresenter
import hr.ferit.brunozoric.taskie.ui.activities.login.LoginActivity
import hr.ferit.brunozoric.taskie.ui.activities.base.BaseActivity
import kotlinx.android.synthetic.main.activity_register.*
import org.koin.android.ext.android.inject

class RegisterActivity : BaseActivity(), RegisterContract.View {


    private val presenter: RegisterContract.Presenter by inject()

    override fun getLayoutResourceId(): Int = R.layout.activity_register

    override fun onResume() {
        super.onResume()
        presenter.setView(this)
    }

    override fun setUpUi() {
        register.onClick { signInClicked() }
        goToLogin.onClick { goToLoginClicked() }
    }

    private fun signInClicked() {
        presenter.onRegisterClicked(
            UserDataRequest(
                email = email.text.toString(),
                password = password.text.toString(),
                name = name.text.toString()
            )
        )
    }


    private fun goToLoginClicked() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onRegisterSuccessful() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onRegisterFailed(error: String) {
        displayToast(error)
    }


}