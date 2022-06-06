package andlima.hafizhfy.challengedelapan.ui.component

import andlima.hafizhfy.challengedelapan.R
import andlima.hafizhfy.challengedelapan.ui.theme.MainGrey
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun coba() {
    InputField("A")
}

@SuppressLint("ComposableNaming")
@Composable
fun InputField(label: String, isRequired: Boolean? = null, isInputError: Boolean?= null, errorMessage: String? = null): String {
    var string by remember { mutableStateOf("") }
    var isError by rememberSaveable { mutableStateOf(false) }

    if (isInputError != null) {
        isError = isInputError
    }

    fun validate(text: String) {
        isError = text == ""
    }

    OutlinedTextField(
        value = string,
        onValueChange = {string = it},
        label = { Text( text = label) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 7.dp, 0.dp, 4.dp),
        shape = RoundedCornerShape(20.dp),
        textStyle = MaterialTheme.typography.body2,
        isError = isError,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = if (isError) MaterialTheme.colors.error else MaterialTheme.colors.primary,
            unfocusedBorderColor = if (isError) MaterialTheme.colors.error else MainGrey
        ),
        keyboardActions = KeyboardActions{ validate(string) }
    )

    if (isRequired == true) {
        when {
            isError == true && string == "" -> {
                ErrorText(text = "Field cannot be empty")
            }
            isInputError == true -> {
                if (errorMessage != null) {
                    ErrorText(text = errorMessage)
                }
            }
        }
    }

    return string
}

@SuppressLint("ComposableNaming")
@Composable
fun InputFieldEmail(label: String, validate: Boolean): String {
    var email by remember { mutableStateOf("") }
    var isError by rememberSaveable { mutableStateOf(false) }

    isError = validate

    fun validate(text: String) {
        isError = text == ""
    }

    OutlinedTextField(
        value = email,
        onValueChange = {email = it},
        label = { Text( text = label) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 7.dp, 0.dp, 4.dp),
        shape = RoundedCornerShape(20.dp),
        textStyle = MaterialTheme.typography.body2,
        isError = isError,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = if (isError) MaterialTheme.colors.error else MaterialTheme.colors.primary,
            unfocusedBorderColor = if (isError) MaterialTheme.colors.error else MainGrey
        ),
        keyboardActions = KeyboardActions{ validate(email) }
    )

    if (isError) {
        if (email != "") {
            ErrorText(text = "Email not registered")
        }
        else {
            ErrorText(text = "Field cannot be empty")
        }
    }

    return email
}

@SuppressLint("ComposableNaming")
@Composable
fun InputFieldPassword(label: String, validate: Boolean, errorMessage: String): String {
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var isError by rememberSaveable { mutableStateOf(false) }

    isError = validate

    fun validate(text: String) {
        isError = text == ""
    }

    OutlinedTextField(
        value = password,
        onValueChange = {password = it},
        label = { Text( text = label) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            val image = if (passwordVisible)
                R.drawable.ic_eye_off
            else R.drawable.ic_eye

            val description = if (passwordVisible) "Hide password" else "Show password"

            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Image(painter = painterResource(image), contentDescription = description)
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 7.dp, 0.dp, 4.dp),
        shape = RoundedCornerShape(20.dp),
        textStyle = MaterialTheme.typography.body2,
        isError = isError,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = if (isError) MaterialTheme.colors.error else MaterialTheme.colors.primary,
            unfocusedBorderColor = if (isError) MaterialTheme.colors.error else MainGrey
        ),
        keyboardActions = KeyboardActions{ validate(password) }
    )

    if (isError) {
        if (password != "")
            ErrorText(text = errorMessage)
        else
            ErrorText(text = "Field cannot be empty")
    }

    return password
}