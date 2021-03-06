package carlos.nicolau.galves.androidcwb.presentation.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import carlos.nicolau.galves.androidcwb.R
import carlos.nicolau.galves.androidcwb.framework.base.BaseActivity
import carlos.nicolau.galves.androidcwb.framework.module_android.ModuleName
import carlos.nicolau.galves.core.errors.ErrorType
import carlos.nicolau.galves.core.utils.Callback
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.viewmodel.ext.android.viewModel

class LoginActivity : BaseActivity() {

    //region actions
    fun errorLogin(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    fun goToHome() {

        installRegistrationModule(
            ModuleName.HOME,
            object : Callback<Boolean, String>() {
                override fun onSuccess(result: Boolean) {
                    super.onSuccess(result)
                    openModule(ModuleName.HOME)
                    finish()
                }

                override fun onError(error: String) {
                    super.onError(error)
                    errorLogin(error)
                }
            }
        )
    }

    fun showLoading() {
        loading.visibility = View.VISIBLE
    }

    fun hideLoading() {
        loading.visibility = View.INVISIBLE
    }

    //endregion actions
    val loginViewModel: LoginViewModelImpl by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setupView()
    }

    private fun setupView() {
        login.setOnClickListener {
            loginViewModel.viewState.observe(this, Observer { result ->
                when (result) {
                    is LoginViewModel.ViewState.isLoading -> {
                        when (result.load) {
                            true -> {
                                showLoading()
                            }
                            false -> {
                                hideLoading()
                            }
                        }
                    }

                    is LoginViewModel.ViewState.goToHome -> {
                        goToHome()
                    }

                    is LoginViewModel.ViewState.errorLogin -> {
                        when {
                            result.erroType == ErrorType.ERRO_404 ->
                                errorLogin(ErrorType.ERRO_404.value)

                            result.erroType == ErrorType.ERRO_265 ->
                                errorLogin(ErrorType.ERRO_265.value)

                            result.erroType == ErrorType.ERRO_INTERNET ->
                                errorLogin(ErrorType.ERRO_INTERNET.value)

                            result.erroType == ErrorType.ERRO_USER_NOT_FOUND ->
                                errorLogin(ErrorType.ERRO_USER_NOT_FOUND.value)
                        }
                    }
                }
            })

            loginViewModel.onClickBtnLogin(username.text.toString(), password.text.toString())
        }
    }
}
