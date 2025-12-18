package com.glitchdev.almondanalyzer.core.presentation

import android.net.Uri
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.core.net.toUri
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.glitchdev.almondanalyzer.core.presentation.navigationbar.NavigationBar
import com.glitchdev.almondanalyzer.core.utils.ScreenRoutes
import com.glitchdev.almondanalyzer.fields.presentation.allfields.FieldsScreen
import com.glitchdev.almondanalyzer.fields.presentation.allfields.FieldsScreenViewModel
import com.glitchdev.almondanalyzer.fields.presentation.editfield.EditFieldScreen
import com.glitchdev.almondanalyzer.fields.presentation.editfield.EditFieldViewModel
import com.glitchdev.almondanalyzer.fields.presentation.fieldinfo.FieldInfoScreen
import com.glitchdev.almondanalyzer.fields.presentation.fieldinfo.FieldInfoViewModel
import com.glitchdev.almondanalyzer.fields.presentation.fieldinfo.editexpense.EditExpenseComponent
import com.glitchdev.almondanalyzer.ui.components.AppSurface
import com.glitchdev.almondanalyzer.ui.components.notification.NotificationController
import com.glitchdev.almondanalyzer.ui.components.notification.ObserveAsEvents
import com.glitchdev.almondanalyzer.ui.components.notification.SwipeableNotification
import com.glitchdev.almondanalyzer.ui.components.notification.SwipeableNotificationState
import com.glitchdev.almondanalyzer.ui.theme.AlmondAnalyzerTheme
import com.glitchdev.almondanalyzer.ui.theme.AppTheme
import com.glitchdev.almondanalyzer.uploadscreen.presentation.UploadScreen
import com.glitchdev.almondanalyzer.uploadscreen.presentation.UploadScreenViewModel
import com.glitchdev.almondanalyzer.workscreen.presentation.WorkScreen
import com.glitchdev.almondanalyzer.workscreen.presentation.WorkScreenViewModel
import org.koin.androidx.compose.koinViewModel

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
                                    onNavigateToFieldsScreen = {
                                        navController.navigate(ScreenRoutes.FieldsNavGraph) {
                                            popUpTo(ScreenRoutes.FieldsNavGraph) { inclusive = true }
                                        }
                                    },
                                    onNavigateToWorksScreen = {
                                        navController.navigate(ScreenRoutes.WorksNavGraph) {
                                            popUpTo(ScreenRoutes.WorksNavGraph) { inclusive = true }
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

                    val swipeableNotificationState = remember {
                        SwipeableNotificationState()
                    }
                    ObserveAsEvents(NotificationController.notificationEvents) { event ->
                        swipeableNotificationState.showNotification(event)
                    }
                    SwipeableNotification(swipeableNotificationState)
                }
            }
        }
    }
}

@Composable
private fun ScreensContent(
    navController: NavHostController
) {
    val startDestination = ScreenRoutes.FieldsNavGraph

    NavHost(
        modifier = Modifier
            .fillMaxSize(),
        navController = navController,
        startDestination = startDestination
    ) {
        navigation<ScreenRoutes.FieldsNavGraph>(startDestination = ScreenRoutes.AllFieldsScreen) {
            composable<ScreenRoutes.AllFieldsScreen> {
                val viewModel: FieldsScreenViewModel = koinViewModel()
                val state by viewModel.fieldsState.collectAsState()
                FieldsScreen(
                    state = state,
                    onUpdateFieldsInfo = viewModel::loadFields,
                    onSelectField = { viewModel.selectField(it) },
                    onUnselectField = { viewModel.selectField(null) },
                    onOpenFieldInfoClicked = { navController.navigate(ScreenRoutes.FieldInfoScreen(it)) },
                    onAddNewFieldClicked = { navController.navigate(ScreenRoutes.EditFieldScreen(null)) },
                    onEditFieldClicked = { navController.navigate(ScreenRoutes.EditFieldScreen(it)) },
                    onDeleteFieldClicked = viewModel::deleteField
                )
            }
            composable<ScreenRoutes.FieldInfoScreen> { backStackEntry ->
                val fieldInfoArgs: ScreenRoutes.FieldInfoScreen = backStackEntry.toRoute()
                val fieldId = fieldInfoArgs.fieldId

                val viewModel: FieldInfoViewModel = koinViewModel()
                val fieldState by viewModel.fieldState.collectAsState()
                val expenseEditorState by viewModel.expenseEditorState.collectAsState()

                LaunchedEffect(fieldId) { viewModel.loadDataForField(fieldId) }

                FieldInfoScreen(
                    state = fieldState,
                    onBackClicked = { navController.popBackStack() },
                    onReloadClicked = { if (fieldState.fieldInfo?.id != null) viewModel.loadDataForField(fieldState.fieldInfo!!.id) },
                    onOpenExpenseEditor = viewModel::openExpenseEditor,
                )
                EditExpenseComponent(
                    state = expenseEditorState,
                    onDismissRequest = viewModel::closeExpenseEditor,
                    onUpdateDescription = viewModel::updateExpenseDescription,
                    onUpdateDate = viewModel::updateExpenseDate,
                    onUpdateAmount = viewModel::updateExpenseAmount,
                    onAddExpenseClicked = viewModel::addExpenses,
                    onUpdateExpenseClicked = viewModel::editExpenses,
                    onDeleteExpenseClicked = {
                        if (expenseEditorState.editedExpense != null)
                            viewModel.removeExpenses(expenseEditorState.editedExpense!!.id)
                    }
                )
            }
            composable<ScreenRoutes.EditFieldScreen> { backStackEntry ->
                val editFieldScreenArgs: ScreenRoutes.EditFieldScreen = backStackEntry.toRoute()
                val fieldId = editFieldScreenArgs.fieldId

                val viewModel: EditFieldViewModel = koinViewModel()
                val state by viewModel.state.collectAsState()

                LaunchedEffect(fieldId) {
                    viewModel.loadFieldData(fieldId)
                }
                EditFieldScreen(
                    state = state,
                    onNameChange = viewModel::updateName,
                    onVarietyChange = viewModel::updateVariety,
                    onCadastralNumberChange = viewModel::updateCadastralNumber,
                    onPlantingYearChange = viewModel::updatePlantingYear,
                    onAddRow = viewModel::addSeedlingsRow,
                    onEditRow = viewModel::editSeedlingCountForRow,
                    onRemoveRow = viewModel::removeSeedlingsRow,
                    onBackClicked = { navController.popBackStack() },
                    onUpdateFieldClicked = { viewModel.updateField {  } },
                    onAddFieldClicked = { viewModel.addField {  } },
                    onResetToDefaults = viewModel::resetToDefault
                )
            }
        }
        navigation<ScreenRoutes.WorksNavGraph>(startDestination = ScreenRoutes.WorksScreen) {
            composable<ScreenRoutes.WorksScreen> {
                val viewModel: WorkScreenViewModel = koinViewModel()
                // все переменные из view model передаются в экран тут
                WorkScreen()
            }
        }
        navigation<ScreenRoutes.AnalyzeImageNavGraph>(startDestination = ScreenRoutes.UploadImageScreen) {
            composable<ScreenRoutes.UploadImageScreen> {
                val uploadScreenViewModel: UploadScreenViewModel = koinViewModel()
                val cameraState by uploadScreenViewModel.cameraState.collectAsState()
                val imagePickerState by uploadScreenViewModel.pickerState.collectAsState()
                val context = LocalContext.current
                UploadScreen(
                    cameraState = cameraState,
                    imagePickerState = imagePickerState,
                    onUpdateCameraPermissions = uploadScreenViewModel::onUpdateCameraPermissions,
                    onUpdateCameraFullscreenMode = uploadScreenViewModel::onUpdateCameraFullscreenMode,
                    onSwitchSelectedCamera = uploadScreenViewModel::onUpdateSelectedCamera,
                    onUpdateCameraStreamStatus = uploadScreenViewModel::onUpdateCameraStreamAvailability,
                    onPhotoTaken = { uploadScreenViewModel.onPhotoTaken(it) },
                    onOpenImagePreview = { imageUri ->
                        // TODO: Add logic for image preview
                    },
                    onUpdateImagePickerPermissions = { isPermissionsGranted ->
                        uploadScreenViewModel.onUpdateImagePickerPermissions(isPermissionsGranted)
                        if (isPermissionsGranted) uploadScreenViewModel.loadImagesUris(context)
                    },
                    onHideTempOnlyWarning = uploadScreenViewModel::hideTempOnlyFilesWarning,
                    onUpdateImagesList = { uploadScreenViewModel.loadImagesUris(context) },
                    onSelectImage = uploadScreenViewModel::addImageToSelection,
                    onUnselectImage = uploadScreenViewModel::removeImageFromSelection,
                    onClearImageSelection = uploadScreenViewModel::clearSelection,
                    onUploadImages = {
                        val selectedImagesUris = imagePickerState.selectedImages.map { it.toString() }
                        navController.navigate(ScreenRoutes.UploadImageResultsScreen(imagesUris = selectedImagesUris))
                    }
                )
            }
            composable<ScreenRoutes.UploadImageResultsScreen> { backStackEntry ->
                val uploadImagesRouteArgs: ScreenRoutes.UploadImageResultsScreen = backStackEntry.toRoute()
                val imagesUris: List<Uri> = uploadImagesRouteArgs.imagesUris.map { it.toUri() }
                // TODO()
            }
        }
    }
}