package com.client.shop.ui.auth.contract

import com.client.shop.R
import com.domain.interactor.auth.ForgotPasswordUseCase
import com.domain.interactor.auth.SignInUseCase
import com.domain.validator.FieldValidator
import com.ui.base.contract.BaseLcePresenter
import com.ui.base.contract.BaseLceView
import javax.inject.Inject

interface SignInView : BaseLceView<Unit> {

    fun showEmailError()

    fun showPasswordError()

    fun onCheckPassed()

    fun onFailure()
}

class SignInPresenter @Inject constructor(
        private val signInUseCase: SignInUseCase,
        private val forgotPasswordUseCase: ForgotPasswordUseCase
) :
        BaseLcePresenter<Unit, SignInView>(signInUseCase, forgotPasswordUseCase) {

    private val validator = FieldValidator()

    fun logIn(email: String, password: String) {

        if (!validator.isEmailValid(email)) {
            view?.showEmailError()
        } else if (!validator.isPasswordValid(password)) {
            view?.showPasswordError()
        } else {
            view?.onCheckPassed()
            signInUseCase.execute(
                    { view?.showContent(Unit) },
                    {
                        view?.onFailure()
                        resolveError(it)
                    },
                    SignInUseCase.Params(email, password))
        }
    }

    fun forgotPassword(email: String) {

        if (!validator.isEmailValid(email)) {
            view?.showMessage(R.string.invalid_email_error_message)
        } else {
            forgotPasswordUseCase.execute(
                    { view?.showMessage(R.string.check_inbox_message) },
                    { resolveError(it) },
                    email
            )
        }
    }
}