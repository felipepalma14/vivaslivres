package com.felipepalma14.authentication

interface IAuthenticator {

    /**
     * Signs out the user
     */
    fun signOut()

    /**
     * Checks if the user is signed in or not
     *
     * @return true if user is signed in otherwise false
     */
    fun isSignedIn(): Boolean

    /**
     * Gets the current signed in user
     *
     * @return current signed in user; otherwise null
     */
    fun getCurrentUser(): IUser?
}