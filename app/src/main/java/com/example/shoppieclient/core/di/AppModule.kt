package com.example.shoppieclient.core.di

import android.app.Application
import com.example.shoppieclient.data.datamanager.LocalUserManagerImpl
import com.example.shoppieclient.data.remote.api.ShoppieApi
import com.example.shoppieclient.data.repository.ShoppieRepoImpl
import com.example.shoppieclient.domain.auth.models.signin.SignInEmailValidationType
import com.example.shoppieclient.domain.auth.models.signin.SignInPasswordValidationType
import com.example.shoppieclient.domain.auth.repository.ShoppieRepo
import com.example.shoppieclient.domain.auth.use_cases.signIn.SignInValidationUseCases
import com.example.shoppieclient.domain.auth.use_cases.signIn.ValidationEmailUseCase
import com.example.shoppieclient.domain.auth.use_cases.signIn.ValidationPasswordUseCase
import com.example.shoppieclient.domain.main.datamanager.LocalUserManager
import com.example.shoppieclient.domain.main.use_cases.DataStoreUseCases
import com.example.shoppieclient.domain.main.use_cases.ReadOnBoardingUseCase
import com.example.shoppieclient.domain.main.use_cases.ReadTokenUseCase
import com.example.shoppieclient.domain.main.use_cases.SaveOnBoardingUseCase
import com.example.shoppieclient.domain.main.use_cases.SaveTokenUseCase
import com.example.shoppieclient.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ShoppieAppModule {
    private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    @Provides
    @Singleton
    fun provideLocalDataManager(
        application: Application
    ): LocalUserManager = LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideTokenUseCases(
        localUserManager: LocalUserManager
    ): DataStoreUseCases = DataStoreUseCases(
        SaveTokenUseCase(localUserManager),
        ReadTokenUseCase(localUserManager),
        SaveOnBoardingUseCase(localUserManager),
        ReadOnBoardingUseCase(localUserManager)
    )

    @Provides
    @Singleton
    fun provideShoppieApi(): ShoppieApi {
        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build()
            .create(ShoppieApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthRepo(api: ShoppieApi): ShoppieRepo {
        return ShoppieRepoImpl(api)
    }

    @Provides
    @Singleton
    fun provideValidationEmailUseCase(): ValidationEmailUseCase {
        return ValidationEmailUseCase()
    }

    @Provides
    @Singleton
    fun provideValidationPasswordUseCase(): ValidationPasswordUseCase {
        return ValidationPasswordUseCase()
    }

    @Provides
    @Singleton
    fun provideValidationUseCases(
        validationEmailUseCase: ValidationEmailUseCase,
        validationPasswordUseCase: ValidationPasswordUseCase
    ): SignInValidationUseCases = SignInValidationUseCases(
        validationEmailUseCase, validationPasswordUseCase
    )


}