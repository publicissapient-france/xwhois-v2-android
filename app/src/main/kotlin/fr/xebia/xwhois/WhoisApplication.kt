package fr.xebia.xwhois

import android.app.Application
import com.crashlytics.android.Crashlytics
import io.fabric.sdk.android.Fabric

class WhoisApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Fabric.with(this, Crashlytics())
    }
}
