package carlos.nicolau.galves.androidcwb.presentation.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import carlos.nicolau.galves.androidcwb.R
import carlos.nicolau.galves.core.interators.GetUserUseCase
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginPresenter.View {
    private lateinit var loginPresenterImpl: LoginPresenterImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupView()
    }

    private fun setupView() {
        loginPresenterImpl = LoginPresenterImpl(GetUserUseCase())
        loginPresenterImpl.attach(this)
        login.setOnClickListener {
            loginPresenterImpl.onClickButtonLogin(username.text.toString(), password.text.toString())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        loginPresenterImpl.detach()
    }

    override fun showLoading() {
        TODO("Not yet implemented")
    }

    override fun hideLoading() {
        TODO("Not yet implemented")
    }
}