package andlima.hafizhfy.challengedelapan.func

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.widget.Toast

// Function to easy making Toast -------------------------------------------------------------------
fun toast(context: Context, message : String) {
    Toast.makeText(
        context,
        message,
        Toast.LENGTH_LONG
    ).show()
}

// Function to easy making SnackBar ----------------------------------------------------------------
//@SuppressLint("CoroutineCreationDuringComposition")
//@Composable
//fun SnackbarShort(message: String) {
//    val scaffoldState = rememberScaffoldState()
//    val coroutineScope = rememberCoroutineScope()
//
//    coroutineScope.launch {
//        val snack = scaffoldState.snackbarHostState.showSnackbar(
//            message
//        )
//    }
//}

// Function to easy making AlertDialog -------------------------------------------------------------
fun alertDialog(context: Context, title: String, message: String, action: Any.()->Unit) {
    AlertDialog.Builder(context)
        .setTitle(title)
        .setMessage(message)
        .setNegativeButton("No") { dialogInterface: DialogInterface, _: Int ->
            dialogInterface.dismiss()
        }
        .setPositiveButton("Yes") { _: DialogInterface, _: Int ->
            action(true)
        }
        .setCancelable(false)
        .show()
}

// IMAGE CONVERT METHOD ############################################################################

//private fun convertImageToByteArray(imageView: ImageView): ByteArray {
//    val bitmap = (imageView.drawable as BitmapDrawable).bitmap
//    val stream = ByteArrayOutputStream()
//    bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream)
//
//    return stream.toByteArray()
//}
//
//fun encodeImageBase64(imageView: ImageView): String {
//    return Base64.encodeToString(convertImageToByteArray(imageView), Base64.NO_WRAP)
//}
//
//fun decodeBase64Image(base64String: String): Bitmap {
//    val imageBytes = Base64.decode(base64String, Base64.DEFAULT)
//
//    return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
//}

// END OF IMAGE CONVERT METHOD #####################################################################

//fun matchUserLocalAndDatabase(context: Context, lifecycleOwner: LifecycleOwner, action: (result: Boolean, response: List<GetUserItem>, message: String) -> Unit) {
//    val userManager = UserManager(context)
//
//    userManager.email.asLiveData().observe(lifecycleOwner, { email ->
//        userManager.password.asLiveData().observe(lifecycleOwner, { password ->
//
//            if (email != "" && password != "") {
//                UserClient.instance.getUser(email)
//                    .enqueue(object : retrofit2.Callback<List<GetUserItem>>{
//                        override fun onResponse(
//                            call: Call<List<GetUserItem>>,
//                            response: Response<List<GetUserItem>>
//                        ) {
//                            if (response.isSuccessful) {
//                                if (response.body()!!.isEmpty()) {
//                                    action(true, emptyList(), "Account banned")
//                                } else {
//                                    if ( response.body()!![0].email != email || response.body()!![0].password != password) {
//                                        action(true, emptyList(), "User data changed, please re-login")
//                                    } else {
//
//                                        action(true, response.body()!!, "")
//                                    }
//                                }
//                            }
//                        }
//                        override fun onFailure(call: Call<List<GetUserItem>>, t: Throwable) {
//                            action(false, emptyList(), "Connection error")
//                        }
//                    })
//            }
//            else {
//                action(false, emptyList(), "")
//            }
//        })
//    })
//}
//
//@DelicateCoroutinesApi
//fun updateUserDataRealTime(context: Context, isDifferent: Boolean, response: List<GetUserItem>, message: String): Boolean {
//    val userManager = UserManager(context)
//
//    if (isDifferent) {
//        if (response.isNotEmpty()) {
//            GlobalScope.launch {
//                userManager.updateCurrentUserRealTimeData(
//                    response[0].username,
//                    response[0].avatar,
//                    response[0].complete_name,
//                    response[0].address,
//                    response[0].dateofbirth
//                )
//            }
//            return false
//        } else {
//            GlobalScope.launch { userManager.clearData() }
//            toast(context, message)
//            return true
//        }
//    } else {
//        return if (message != "") {
//            alertDialog(context, message, "Restart app or try again in few minutes") { }
//            false
//        } else {
//            false
//        }
//    }
//}