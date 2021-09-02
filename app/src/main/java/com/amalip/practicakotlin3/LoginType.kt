package com.amalip.practicakotlin3

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by Amalip on 8/31/2021.
 */

@Parcelize
enum class LoginType(val text: Int) : Parcelable {

    CREDENTIALS(R.string.text_login_credentials_label),
    SOCIAL(R.string.text_login_social_label)

}