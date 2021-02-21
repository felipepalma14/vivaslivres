
object Version {
    // kotlin
    const val kotlin = "1.4.21"
    const val kotlinCoroutines = "1.0.1"
    const val gradle = "4.1.1"
    const val appCompat = "1.2.0"
    const val coreKtx = "1.3.2"
    const val constraintLayout = "2.0.4"
    const val swiperefresh = "1.1.0"
    const val material = "1.2.1"
    const val interceptor = "4.8.1"
    const val retrofit = "2.9.0"
    const val lifecycle = "2.2.0"
    const val picasso = "2.71828"
    const val sharedPreference = "1.1.1"
    const val navigation = "2.3.2"
    const val recyclerViewVersion = "1.2.0-alpha05"
    const val roomVersion = "2.3.0-alpha02"
    const val hilt = "2.28-alpha"
    const val hiltViewModel = "1.0.0-alpha02"
    const val firebaseBoom = "26.5.0"
}


object Libs {

    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Version.navigation}"
    const val navigationKtx = "androidx.navigation:navigation-ui-ktx:${Version.navigation}"
    const val picasso = "com.squareup.picasso:picasso:${Version.picasso}"
    const val sharedPreference = "androidx.preference:preference-ktx:${Version.sharedPreference}"
    const val interceptor = "com.squareup.okhttp3:logging-interceptor:${Version.interceptor}"
    const val swipeRefresh = "androidx.swiperefreshlayout:swiperefreshlayout:${Version.swiperefresh}"
    const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Version.kotlin}"
    const val appCompat = "androidx.appcompat:appcompat:${Version.appCompat}"
    const val lifecycle = "androidx.lifecycle:lifecycle-extensions:${Version.lifecycle}"
    const val lifecycleKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Version.lifecycle}"
    const val lifecycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Version.lifecycle}"
    const val lifecycleCommon = "androidx.lifecycle:lifecycle-common:${Version.lifecycle}"
    const val coreKtx =  "androidx.core:core-ktx:${Version.coreKtx}"
    const val material = "com.google.android.material:material:${Version.material}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.kotlinCoroutines}"
    const val coroutinesCore =  "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.kotlinCoroutines}"
    // ViewModel
    const val viewmodel =  "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.lifecycle}"
    //Layout
    const val constraintLayout =  "androidx.constraintlayout:constraintlayout:${Version.constraintLayout}"
    const val recyclerView =  "androidx.recyclerview:recyclerview:${Version.recyclerViewVersion}"

    //Networking
    const val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofit}"
    const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Version.retrofit}"

    //DI Hilt
    const val hiltAndroid  = "com.google.dagger:hilt-android:${Version.hilt}"
    const val hiltViewmodel = "androidx.hilt:hilt-lifecycle-viewmodel:${Version.hiltViewModel}"
    const val hiltCompiler =  "com.google.dagger:hilt-android-compiler:${Version.hilt}"
    const val hiltCompilerViewmodel = "androidx.hilt:hilt-compiler:${Version.hiltViewModel}"

    const val roomRuntime =  "androidx.room:room-runtime:${Version.roomVersion}"
    const val roomKtx =  "androidx.room:room-ktx:${Version.roomVersion}"
    const val roomCompiler  =  "androidx.room:room-compiler:${Version.roomVersion}"

    //firebase
    const val firebaseBoom  = "com.google.firebase:firebase-bom:${Version.firebaseBoom}"
    const val firebaseAnalytics = "com.google.firebase:firebase-analytics-ktx"
}