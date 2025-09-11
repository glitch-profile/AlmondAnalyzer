package com.glitchdev.almondanalyzer.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imeAnimationTarget
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.glitchdev.almondanalyzer.core.presentation.navigationbar.NavigationBar
import com.glitchdev.almondanalyzer.core.utils.ScreenRoutes
import com.glitchdev.almondanalyzer.ui.components.AppSurface
import com.glitchdev.almondanalyzer.ui.theme.AlmondAnalyzerTheme
import com.glitchdev.almondanalyzer.ui.theme.AppTheme

@OptIn(ExperimentalLayoutApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AlmondAnalyzerTheme(
                useSystemTheme = true,
                useDarkTheme = isSystemInDarkTheme()
            ) {
                AppSurface(
                    modifier = Modifier.fillMaxSize(),
                    color = AppTheme.colorScheme.background
                ) {

                    val navController = rememberNavController()
                    val currentNavGraph = navController.currentBackStackEntryAsState().value
                        ?.destination
                        ?.parent

                    val density = LocalDensity.current
                    val keyboardTargetPadding = WindowInsets.imeAnimationTarget.getBottom(density)
                    val isBottomMenuVisibleState = remember { MutableTransitionState(true) }

                    LaunchedEffect(keyboardTargetPadding) {
                        isBottomMenuVisibleState.targetState = keyboardTargetPadding == 0
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .statusBarsPadding()
                            .navigationBarsPadding()
                            .imePadding()
                    ) {
                        AppSurface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .padding(horizontal = AppTheme.size.medium)
                                .padding(top = AppTheme.size.medium),
                            shape = AppTheme.shape.large
                        ) {
                            ScreensContent(
                                navController = navController
                            )
                        }
                        Spacer(modifier = Modifier.height(AppTheme.size.medium))
                        AnimatedVisibility(
                            visibleState = isBottomMenuVisibleState,
                            enter = expandVertically(
                                spring(stiffness = Spring.StiffnessMedium)
                            ),
                            exit = shrinkVertically(
                                spring(stiffness = Spring.StiffnessMedium)
                            )
                        ) {
                            AppSurface(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = AppTheme.size.medium)
                                    .padding(bottom = AppTheme.size.medium),
                                shape = AppTheme.shape.large
                            ) {
                                NavigationBar(
                                    currentNavGraph = currentNavGraph,
                                    onNavigateToRecentsScreen = {
                                        navController.navigate(ScreenRoutes.RecentsNavGraph) {
                                            popUpTo(ScreenRoutes.RecentsNavGraph) { inclusive = true }
                                        }
                                    },
                                    onNavigateToAnalyzeScreen = {
                                        navController.navigate(ScreenRoutes.AnalyzeImageNavGraph) {
                                            popUpTo(ScreenRoutes.AnalyzeImageNavGraph) { inclusive = true }
                                        }
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ScreensContent(
    navController: NavHostController
) {
    val startDestination = ScreenRoutes.RecentsNavGraph

    NavHost(
        modifier = Modifier
            .fillMaxSize(),
        navController = navController,
        startDestination = startDestination
    ) {
        navigation<ScreenRoutes.RecentsNavGraph>(startDestination = ScreenRoutes.RecentImagesScreen) {
            composable<ScreenRoutes.RecentImagesScreen> {

            }
        }
        navigation<ScreenRoutes.AnalyzeImageNavGraph>(startDestination = ScreenRoutes.UploadImageScreen) {
            composable<ScreenRoutes.UploadImageScreen> {

            }
        }
    }
}