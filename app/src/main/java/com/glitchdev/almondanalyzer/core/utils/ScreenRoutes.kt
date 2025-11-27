package com.glitchdev.almondanalyzer.core.utils

import kotlinx.serialization.Serializable

object ScreenRoutes {

    @Serializable object FieldsNavGraph
    @Serializable object AllFieldsScreen
    @Serializable data class FieldInfoScreen(val fieldId: Long)
    @Serializable data class EditFieldScreen(val fieldId: Long?)

    @Serializable object ExpensesNavGraph
    @Serializable object ExpensesScreen

    @Serializable object RecentsNavGraph
    @Serializable object RecentImagesScreen
    @Serializable data class RecentResultScreen (
        val resultId: String
    )

    @Serializable object AnalyzeImageNavGraph
    @Serializable object UploadImageScreen
    @Serializable data class UploadImageResultsScreen(
        val imagesUris: List<String>
    )

}