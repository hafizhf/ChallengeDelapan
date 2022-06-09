package andlima.hafizhfy.challengedelapan.view.main

import andlima.hafizhfy.challengedelapan.func.alertDialog
import andlima.hafizhfy.challengedelapan.func.toast
import andlima.hafizhfy.challengedelapan.local.datastore.UserManager
import andlima.hafizhfy.challengedelapan.model.film.GetMockFilmResponse
import andlima.hafizhfy.challengedelapan.repository.DummyRepository
import andlima.hafizhfy.challengedelapan.ui.component.*
import andlima.hafizhfy.challengedelapan.ui.theme.*
import andlima.hafizhfy.challengedelapan.view.start.LoginActivity
import andlima.hafizhfy.challengedelapan.viewmodel.HomeViewModel
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@DelicateCoroutinesApi
@AndroidEntryPoint
class HomeActivity : ComponentActivity() {

    // Get data store
    private lateinit var userManager: UserManager

    // Used for double back to exit app
    private var doubleBackToExit = false

    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userManager = UserManager(this)

        setContent {
            ChallengeDelapanTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val movieViewModel = viewModel(modelClass = HomeViewModel::class.java)
                    val moviesData by movieViewModel.filmState.collectAsState()

                    var username = ""
                    userManager.username.asLiveData().observeForever {
                        username = it
                    }
                    Home(moviesData, userManager, username)

                    // GET FROM THE MOVIE DB =======================================================
//                    val moviesViewModel = viewModel(modelClass = HomeViewModel::class.java)
//                    moviesViewModel.latestMovie(1) {
//                        it.observe(this, { data ->
//                            Log.d("DATA DI HOME", data.toString())
//                        })
//                    }
                }
            }
        }
        // Check if user click back button twice
        doubleBackExit()
    }

    // Function to exit app with double click on back button----------------------------------------
    private fun doubleBackExit() {
        onBackPressedDispatcher
            .addCallback(this, object : OnBackPressedCallback(true){
                override fun handleOnBackPressed() {
                    if (doubleBackToExit) {
                        finish()
                    } else {
                        doubleBackToExit = true
                        toast(this@HomeActivity, "Press again to exit")

                        Handler(Looper.getMainLooper()).postDelayed({
                            kotlin.run {
                                doubleBackToExit = false
                            }
                        }, 2000)
                    }
                }
            })
    }
}

@DelicateCoroutinesApi
@OptIn(ExperimentalMaterialApi::class)
@ExperimentalFoundationApi
@Composable
fun Home(movies: List<GetMockFilmResponse>, userManager: UserManager, username: String) {
    val scrollState = rememberLazyListState()
    val scrollOffset: Float = minOf(
        1f,
        1 - (scrollState.firstVisibleItemScrollOffset/10f)
                + scrollState.firstVisibleItemIndex
    )
    val headerHeight by animateDpAsState(targetValue = max(0.dp, 45.dp*scrollOffset))
    val headerPaddingTop by animateDpAsState(targetValue = max(0.dp, 25.dp*scrollOffset))
    val headerAlpha by animateFloatAsState(targetValue = kotlin.math.max(0.0F, 1.0F*scrollOffset))

    val context = LocalContext.current
    Column(
        modifier = Modifier
            .padding(25.dp, 0.dp)
//            .verticalScroll(rememberScrollState())
    ) {
        HeaderHome(
            modifier = Modifier
                .height(headerHeight)
                .alpha(headerAlpha),
            paddingTop =  headerPaddingTop,
            username = username
        ) {
            Log.d("USERNAME DATASTORE", userManager.username.asLiveData().value.toString())
            alertDialog(context, "Logout", "Are you sure want to log out?") {
                proceedLogout(context, userManager)
            }
        }

        RecyclerMovie(movies, scrollState) {
            val detail = Intent(context, DetailActivity::class.java)
            val bundle = Bundle()
            bundle.putParcelable("SELECTED_DATA", it)
            detail.putExtra("DATA", bundle)
            context.startActivity(detail)
        }
    }
}

@DelicateCoroutinesApi
private fun proceedLogout(context: Context, userManager: UserManager) {
    GlobalScope.launch {
        userManager.clearData()
    }
    toast(context, "You are logged out")
    val gotoLogin = Intent(context, LoginActivity::class.java)
    gotoLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK) // since idk how to finish(), hopefully this is good
    context.startActivity(gotoLogin)
}

@DelicateCoroutinesApi
@ExperimentalFoundationApi
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview3() {
    val context = LocalContext.current
    ChallengeDelapanTheme {
        Home(DummyRepository().getDummyMovie(), UserManager(context), "User")
    }
}