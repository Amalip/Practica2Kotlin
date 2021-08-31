package com.amalip.practicakotlin3

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.amalip.practicakotlin3.LoginType.*

class MainActivity : AppCompatActivity() {

    private val LOGIN_TYPE = "LOGIN_KEY"
    private lateinit var loginType: LoginType
    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        user = User()
        loginType = savedInstanceState?.getParcelable(LOGIN_TYPE) ?: CREDENTIALS

        initViews()
    }

    override fun onSaveInstanceState(outState: Bundle) {

        outState.run {
            putParcelable(LOGIN_TYPE, loginType)
        }

        super.onSaveInstanceState(outState)
    }

    private lateinit var clCredentials: ConstraintLayout
    private lateinit var clSocialLogin: ConstraintLayout

    private lateinit var edtUsername: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var txvGoToSocialLogin: TextView

    private lateinit var txvLoginCredentials: TextView

    private lateinit var btnLoginFb: ImageView
    private lateinit var btnLoginGoogle: ImageView
    private lateinit var btnLoginApple: ImageView
    private lateinit var btnLoginTwitter: ImageView

    private fun initViews() {
        clCredentials = findViewById(R.id.clLoginCredentials)
        clSocialLogin = findViewById(R.id.clSocialLogin)

        edtUsername = findViewById(R.id.edtUsername)
        edtPassword = findViewById(R.id.edtPassword)
        btnLogin = findViewById(R.id.btnLogin)
        txvGoToSocialLogin = findViewById(R.id.txvGoToSocialLogin)

        txvLoginCredentials = findViewById(R.id.txvLoginCredentials)

        btnLoginFb = findViewById(R.id.btnLoginFb)
        btnLoginGoogle = findViewById(R.id.btnLoginGoogle)
        btnLoginApple = findViewById(R.id.btnLoginApple)
        btnLoginTwitter = findViewById(R.id.btnLoginTwitter)

        when (loginType) {
            CREDENTIALS -> {
                initCredentials()
            }
            SOCIAL -> {
                initSocial()
            }
        }

        setCredentialsListeners()
        setSocialListeners()
    }

    private fun setCredentialsListeners() {
        txvGoToSocialLogin.setOnClickListener {
            initSocial()
        }

        btnLogin.setOnClickListener {
            user.apply {
                loginType = CREDENTIALS
                socialNetwork = null
                username = edtUsername.text.toString()
                password = edtPassword.text.toString()
            }
            doLogin()
        }
    }

    private fun setSocialListeners() {
        btnLoginFb.setOnClickListener {
            doSocialLogin(SocialNetwork.FACEBOOK)
        }
        btnLoginGoogle.setOnClickListener {
            doSocialLogin(SocialNetwork.GOOGLE)
        }
        btnLoginApple.setOnClickListener {
            doSocialLogin(SocialNetwork.APPLE)
        }
        btnLoginTwitter.setOnClickListener {
            doSocialLogin(SocialNetwork.TWITTER)
        }

        txvLoginCredentials.setOnClickListener {
            initCredentials()
        }
    }

    private fun doSocialLogin(socialNetwork: SocialNetwork) {
        user.apply {
            username = ""
            password = ""
            loginType = SOCIAL
            this.socialNetwork = socialNetwork
        }
        doLogin()
    }

    private fun doLogin() =
        user.validateUser()?.let {
            showToast("Hello ${it.username}")
        } ?: showToast("User not found")


    private fun showToast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    private fun initCredentials() {
        clCredentials.isVisible = true
        clSocialLogin.isGone = true

        loginType = CREDENTIALS
    }

    private fun initSocial() {
        clSocialLogin.isVisible = true
        clCredentials.isGone = true

        loginType = SOCIAL
    }

}