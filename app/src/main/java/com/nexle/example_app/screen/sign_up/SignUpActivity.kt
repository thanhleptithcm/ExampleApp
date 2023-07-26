package com.nexle.example_app.screen.sign_up

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.View
import android.view.View.*
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.nexle.example_app.R
import com.nexle.example_app.core.activity.BaseActivity
import com.nexle.example_app.utils.CommonUtils
import com.nexle.example_app.extension.isDigit
import com.nexle.example_app.extension.isUppercase
import com.nexle.example_app.extension.isValidEmail
import com.nexle.example_app.screen.category.CategoriesActivity
import com.nexle.example_app.utils.text_html.TextHtmlUtils

class SignUpActivity : BaseActivity(R.layout.activity_sign_up), OnClickListener {

    private lateinit var mViewModel: SignUpViewModel


    private lateinit var tvByClicking: TextView

    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText

    private lateinit var tvHintEmail: TextView
    private lateinit var tvHintPassword: TextView

    private lateinit var tvStatusPassword: TextView
    private lateinit var frViewPassword: FrameLayout

    private lateinit var pgbPassword: ProgressBar
    private lateinit var cbOver16: CheckBox
    private lateinit var tvOver16: TextView

    private lateinit var imbBack: ImageButton
    private lateinit var imbNext: ImageButton

    override fun performDependencyInjection() {
        mViewModel = ViewModelProvider(
            this,
            mViewModelFactory
        )[SignUpViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        tvByClicking = findViewById(R.id.tv_by_clicking)

        edtEmail = findViewById(R.id.edt_email)
        edtPassword = findViewById(R.id.edt_password)

        tvHintEmail = findViewById(R.id.tv_hint_email)
        tvHintPassword = findViewById(R.id.tv_hint_password)

        tvStatusPassword = findViewById(R.id.tv_status_pass)
        frViewPassword = findViewById(R.id.fr_view_pass)

        pgbPassword = findViewById(R.id.pgb_password)
        cbOver16 = findViewById(R.id.cb_over16)
        tvOver16 = findViewById(R.id.tv_over16)

        imbBack = findViewById(R.id.imb_back)
        imbNext = findViewById(R.id.imb_next)

        initData()
        initListener()
    }

    private fun initData() {
        TextHtmlUtils.setTextViewHTML(
            this, tvByClicking, getString(R.string.by_clicking),
            object : TextHtmlUtils.OnLinkClicked<String> {
                override fun onLinkClicked(link: String) {

                }
            }, false, R.color.purple
        )
    }


    private fun initListener() {
        mViewModel.loginLiveData.observe(this) {
            startActivity(Intent(this, CategoriesActivity::class.java))
        }

        imbNext.setOnClickListener(this)
        tvOver16.setOnClickListener(this)
        frViewPassword.setOnClickListener(this)
        edtEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(c: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (c?.isEmpty() == true) {
                    tvHintEmail.visibility = INVISIBLE
                } else {
                    tvHintEmail.visibility = VISIBLE
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })
        edtPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(c: CharSequence?, p1: Int, p2: Int, p3: Int) {
                handleInputPassword(c.toString())
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })
    }

    private fun handleInputPassword(text: String) {
        if (text.isEmpty()) {
            tvHintPassword.visibility = INVISIBLE
            frViewPassword.visibility = INVISIBLE
            setProgressPassword(100, "", ContextCompat.getColor(this, R.color.purple))
        } else {
            tvHintPassword.visibility = VISIBLE
            frViewPassword.visibility = VISIBLE
            val length = text.length
            if (length < 6) {
                setProgressPassword(0, getString(R.string.too_short), ContextCompat.getColor(this, R.color.white_43))
            } else {
                val isUpper = text.isUppercase()
                val isDigit = text.isDigit()
                val isSpecial = CommonUtils.isValidSpecial(text)
                if (isUpper && !isDigit && !isSpecial) {
                    setProgressPassword(50, getString(R.string.fair), ContextCompat.getColor(this, R.color.fair))
                } else if (isUpper && isDigit && !isSpecial) {
                    setProgressPassword(75, getString(R.string.good), ContextCompat.getColor(this, R.color.purple))
                } else if (isUpper && isDigit && isSpecial) {
                    setProgressPassword(100, getString(R.string.strong), ContextCompat.getColor(this, R.color.strong))
                } else {
                    setProgressPassword(25, getString(R.string.week), ContextCompat.getColor(this, R.color.week))
                }
            }

        }
    }

    private fun setProgressPassword(progress: Int, status: String, statusColor: Int) {
        tvStatusPassword.text = status
        tvStatusPassword.setTextColor(statusColor)
        pgbPassword.progress = progress
        pgbPassword.progressTintList = ColorStateList.valueOf(statusColor)
    }

    override fun onClick(v: View?) {
        if (v == imbNext) {
            handleNextScreen()
        } else if (v == tvOver16) {
            cbOver16.isChecked = !cbOver16.isChecked
        } else if (v == frViewPassword) {
            frViewPassword.isSelected = !frViewPassword.isSelected
            if (frViewPassword.isSelected) {
                edtPassword.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            } else {
                edtPassword.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            }
        }
    }

    private fun handleNextScreen() {
        val email = edtEmail.text.toString()
        if (email.isEmpty()) {
            Toast.makeText(
                this,
                getString(R.string.common_input_email),
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        if (!email.isValidEmail()) {
            Toast.makeText(
                this,
                getString(R.string.common_invalid_email),
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        val password = edtPassword.text.toString()
        if (password.isEmpty()) {
            Toast.makeText(
                this,
                getString(R.string.common_input_pass),
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        if (password.length < 6) {
            Toast.makeText(
                this,
                getString(R.string.common_pass_at_least),
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        if (password.length > 18) {
            Toast.makeText(
                this,
                getString(R.string.common_pass_up_to),
                Toast.LENGTH_SHORT
            ).show()
            return
        }
        mViewModel.requestSignUp(email, password)
    }
}
