package com.glitchdev.almondanalyzer.core.utils

import kotlinx.serialization.Serializable

object ScreenRoutes {

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