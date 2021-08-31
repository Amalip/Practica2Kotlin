package com.amalip.practicakotlin3

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by Amalip on 8/31/2021.
 */

@Parcelize
enum class LoginType : Parcelable {

    CREDENTIALS, SOCIAL

}