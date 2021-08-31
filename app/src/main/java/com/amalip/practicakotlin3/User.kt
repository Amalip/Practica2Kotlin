package com.amalip.practicakotlin3

/**
 * Created by Amalip on 8/31/2021.
 */

class User(
    var username: String = "",
    var password: String = "",
    var loginType: LoginType? = null,
    var socialNetwork: SocialNetwork? = null,
    var level: UserLevel? = UserLevel.NORMAL_USER
) {

    companion object {
        val users = arrayOf(
            User("Amalip", "admin123", LoginType.CREDENTIALS, level = UserLevel.SUPER_USER),
            User("Amalip2", "123", LoginType.CREDENTIALS, level = UserLevel.MEDIUM_USER),

            User(
                "Amalip Facebook",
                loginType = LoginType.SOCIAL,
                socialNetwork = SocialNetwork.FACEBOOK
            ),
            User(
                "Amalip Google",
                loginType = LoginType.SOCIAL,
                socialNetwork = SocialNetwork.GOOGLE
            ),
            User("Amalip Apple", loginType = LoginType.SOCIAL, socialNetwork = SocialNetwork.APPLE),
            User(
                "Amalip Twitter",
                loginType = LoginType.SOCIAL,
                socialNetwork = SocialNetwork.TWITTER
            )
        )
    }

    fun validateUser() = users.firstOrNull { (it.username == this.username && it.password == this.password)
            || (this.loginType == LoginType.SOCIAL && it.socialNetwork == this.socialNetwork)
    }

}