package com.example.davaleban3

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import com.example.davaleban3.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var user: User
    private val emailPattern = "^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@" +
            "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        
        val linearEditText = binding.linearEt
        val emailInput = binding.emailEt
        val usernameInput = binding.usernameEt
        val firstNameInput = binding.nameEt
        val lastNameInput = binding.surnameEt
        val ageInput = binding.ageEt
        val linearTextView = binding.linearTv
        val emailText = binding.emailTv
        val usernameText = binding.usernameTv
        val fullNameText = binding.fullNameTv
        val ageText = binding.ageTv

        binding.saveBtn.setOnClickListener {
            if (isValid(
                    emailInput.text.toString(),
                    usernameInput.text.toString(),
                    firstNameInput.text.toString(),
                    lastNameInput.text.toString(),
                    ageInput.text.toString()
                )
            ) {
                val toast = Toast.makeText(this, "Congrats User Has Been Saved", Toast.LENGTH_SHORT)
                toast.show()
                user = User(
                    email = emailInput.text.toString(),
                    userName = usernameInput.text.toString(),
                    firstName = firstNameInput.text.toString(),
                    lastName = lastNameInput.text.toString(),
                    age = ageInput.text.toString().toInt()
                )
                linearEditText.visibility = View.GONE

                linearTextView.visibility = View.VISIBLE
                putText(emailText, usernameText, fullNameText, ageText)

            }
        }

        binding.clearBtn.setOnLongClickListener {
            emailInput.setText("")
            usernameInput.setText("")
            firstNameInput.setText("")
            lastNameInput.setText("")
            ageInput.setText("")
            return@setOnLongClickListener true
        }
        
        binding.againBtn.setOnClickListener {

            linearTextView.visibility = View.GONE
            linearEditText.visibility = View.VISIBLE
//            emailInput.setText(user.email)
//            usernameInput.setText(user.userName)
//            firstNameInput.setText(user.firstName)
//            lastNameInput.setText(user.lastName)
//            ageInput.setText(user.age.toString())
        }

    }

    private fun isValid(
        email: String?,
        username: String?,
        firstName: String?,
        lastName: String?,
        age: String?
    ): Boolean {

        if (email == "" || username == "" || firstName == "" || lastName == "" || age == "") {
            d("validation", arrayOf(email, username, firstName, lastName, age).joinToString())
            val toast = Toast.makeText(this, "Fill All Fields!", Toast.LENGTH_SHORT)
            toast.show()
            return false
        } else if (!email!!.matches(emailPattern.toRegex())) {
            val toast = Toast.makeText(this, "Email Is Not Valid", Toast.LENGTH_SHORT)
            toast.show()
            return false
        } else if (username!!.length < 10) {
            val toast =
                Toast.makeText(this, "Username Must Be More Than 10 Characters", Toast.LENGTH_SHORT)
            toast.show()
            return false
        } else if (age?.toIntOrNull() == null || age.toIntOrNull()!! <= 0) {
            d("ageValidation", age.toString())
            val toast =
                Toast.makeText(this, "Age Must Be Positive Whole Number", Toast.LENGTH_SHORT)
            toast.show()
            return false
        }
        return true
    }

    private fun putText(email: AppCompatTextView, userName: AppCompatTextView, fullName: AppCompatTextView, age: AppCompatTextView){
        email.text = user.email
        userName.text = user.userName
        "${user.firstName} ${user.lastName}".also { fullName.text = it }
        age.text = user.age.toString()
    }




}