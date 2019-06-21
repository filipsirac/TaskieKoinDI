package hr.ferit.brunozoric.taskie.ui.activities.login

import android.content.Intent
import hr.ferit.brunozoric.taskie.R
import hr.ferit.brunozoric.taskie.common.displayToast
import hr.ferit.brunozoric.taskie.common.onClick
import hr.ferit.brunozoric.taskie.model.request.UserDataRequest
import hr.ferit.brunozoric.taskie.model.response.LoginResponse
import hr.ferit.brunozoric.taskie.prefs.SharedPrefsHelper
import hr.ferit.brunozoric.taskie.prefs.provideSharedPrefs
import hr.ferit.brunozoric.taskie.ui.activities.base.BaseActivity
import hr.ferit.brunozoric.taskie.ui.activities.main.MainActivity
import hr.ferit.brunozoric.taskie.ui.activities.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.ext.android.inject

class LoginActivity : BaseActivity(), LoginContract.View {


    private val presenter: LoginContract.Presenter by inject()
    private val prefsHelper: SharedPrefsHelper = provideSharedPrefs()

    override fun getLayoutResourceId(): Int = R.layout.activity_login

    override fun onResume() {
        super.onResume()
        presenter.setView(this)
    }

    override fun setUpUi() {
        login.onClick { signInClicked() }
        goToLogin.onClick { goToRegistrationClicked() }
    }

    private fun signInClicked() {
        presenter.onUserLogin(
            UserDataRequest(password = password.text.toString(), email = email.text.toString())
        )
    }


    private fun goToRegistrationClicked() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onLoginSuccessful(loginResponse: LoginResponse) {
        loginResponse?.token?.let { prefsHelper.storeUserToken(it) }
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onLoginFailed(error: String) {
        displayToast(error)
    }


}