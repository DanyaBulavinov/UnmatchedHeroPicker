# UnmatchedHeroPicker

UnmatchedHeroPicker is a mobile application developed using Kotlin. It is built with the Android Jetpack Compose UI toolkit and follows the Material Design guidelines.

## Features

- Provides a user interface for selecting heroes from a predefined list.
- The list of heroes is defined in the `DataSource.kt` file and includes characters such as Achilles, Alice, Dr. Jekyll, Dracula, and many more.
- Each hero is represented by an `ImageItem` object, which includes the hero's name and dominant color.
- The main user interface is defined in the `UnmatchedApp.kt` file. It uses a variety of Compose components, including `Scaffold`, `TopAppBar`, `DropdownMenu`, and `DropdownMenuItem`.
- The application also includes a preview function, `UnmatchedAppPreview`, for visualizing the UI during development.

## Build

The project uses Gradle for build and dependency management. The `settings.gradle.kts` file configures the plugin and dependency resolution management, specifying Google's Maven repository, Maven Central, and the Gradle Plugin Portal as the sources for dependencies.

## Development

Please note that this project is still under development and may undergo significant changes.
