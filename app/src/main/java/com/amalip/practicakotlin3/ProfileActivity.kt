package com.amalip.practicakotlin3

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.amalip.practicakotlin3.MainActivity.Companion.doLogout

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        setView()
    }

    private lateinit var imgUserImage: ImageView
    private lateinit var txvUsername: TextView
    private lateinit var txvUserLevel: TextView
    private lateinit var txvLoginType: TextView
    private lateinit var user: User

    private fun setView() {
        user = intent.getParcelableExtra("loggedUser") ?: User()

        imgUserImage = findViewById(R.id.imgUserImage)
        txvUsername = findViewById(R.id.txvUsername)
        txvUserLevel = findViewById(R.id.txvUserLevel)
        txvLoginType = findViewById(R.id.txvLoginType)

        findViewById<Button>(R.id.btnLogout).setOnClickListener {
            doLogout = true
            finish()
        }

        printData()
    }

    private fun printData() {
        imgUserImage.setImageResource(if (user.loginType == LoginType.CREDENTIALS) R.drawable.ic_profile else user.socialNetwork!!.resource)
        txvUsername.text = user.username
        txvUserLevel.setText(user.level.text)

        val extraString =
            getString(user.socialNetwork?.text ?: R.string.empty_text) // Facebook || ""

        txvLoginType.text = getString(user.loginType!!.text, extraString)
    }

}