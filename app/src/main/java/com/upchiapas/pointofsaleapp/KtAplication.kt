package com.upchiapas.pointofsaleapp
import android.app.Application
import com.upchiapas.pointofsaleapp.di.AppContainer

class KtAplication: Application() {

    lateinit var container: AppContainer
        private set

    override fun onCreate() {
        super.onCreate()
        //se inicializa el contenedor
        container = AppContainer(this)
    }
}