package com.petrov.vitaliy.caraccidentapp

import android.app.Application
import com.petrov.vitaliy.caraccidentapp.presentation.accident.statement.di.CarAccidentStatementModule
import com.petrov.vitaliy.caraccidentapp.presentation.login.di.LoginModule
import com.petrov.vitaliy.caraccidentapp.presentation.mainpage.di.MainPageModule
import com.petrov.vitaliy.caraccidentapp.presentation.messages.di.MessagesModule
import com.petrov.vitaliy.caraccidentapp.presentation.profile.di.ProfileModule
import com.petrov.vitaliy.caraccidentapp.presentation.registration.di.RegistrationModule

class CarAccidentApp : Application() {

    override fun onCreate() {
        super.onCreate()
        injector = Injector()
    }

    companion object {
        lateinit var injector: Injector
    }
}

class Injector {

    val registrationModule = RegistrationModule()

    val loginModule = LoginModule()

    val mainPageModule = MainPageModule()

    val profileModule = ProfileModule()

    val messagesModule = MessagesModule()

    val carAccidentStatementModule = CarAccidentStatementModule()

}

val injector: Injector get() = CarAccidentApp.injector