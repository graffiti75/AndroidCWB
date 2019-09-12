package carlos.nicolau.galves.androidcwb.feature.login

import carlos.nicolau.galves.androidcwb.presentation.login.ILoginPresenter
import carlos.nicolau.galves.androidcwb.presentation.login.LoginPresenterImpl
import carlos.nicolau.galves.core.domain.User
import carlos.nicolau.galves.core.interators.IGetUserUseCase
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.atLeast
import org.mockito.MockitoAnnotations

class LoginPresenterImplTest {

    private val mView = Mockito.mock(ILoginPresenter.View::class.java)
    private val getUserUseCase: IGetUserUseCase = Mockito.mock(IGetUserUseCase::class.java)

    private val presenter =
        LoginPresenterImpl(getUserUseCase)

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        presenter.attach(mView)
    }

    @Test
    fun whenClickBtnLogin_shouldReturnUserDidLogin_Sucess() {
        val user = User()
        Mockito.`when`(getUserUseCase.execute("","")).thenReturn(user)
        presenter.onClickBtnLogin("","")

        Mockito.verify(mView, atLeast(1)).showLoading()
        Mockito.verify(getUserUseCase).execute("","")
        Mockito.verify(mView, atLeast(1)).hideLoading()
    }

    @After
    fun after() {
        presenter.detach()
    }
}