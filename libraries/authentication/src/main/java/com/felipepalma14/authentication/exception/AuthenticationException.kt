package com.felipepalma14.authentication.exception

sealed class AuthenticatorException(
    override val message: String?, override val cause: Throwable?, localizedMessage: String?
) : Exception() {

    class SignInException(
        exception: Exception
    ) : AuthenticatorException(
        exception.message,
        exception.cause,
        exception.cause?.localizedMessage
    )

    class SignUpException(
        exception: Exception
    ) : AuthenticatorException(
        exception.message,
        exception.cause,
        exception.cause?.localizedMessage
    )

}