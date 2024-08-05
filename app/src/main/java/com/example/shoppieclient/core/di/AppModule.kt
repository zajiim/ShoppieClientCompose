package com.example.shoppieclient.core.di

import android.app.Application
import com.example.shoppieclient.data.datamanager.LocalUserManagerImpl
import com.example.shoppieclient.data.remote.api.ShoppieApi
import com.example.shoppieclient.data.repository.ShoppieRepoImpl
import com.example.shoppieclient.domain.auth.repository.ShoppieRepo
import com.example.shoppieclient.domain.auth.use_cases.home.GetNewArrivalsUseCase
import com.example.shoppieclient.domain.auth.use_cases.home.GetPopularBrandsIndividualUseCase
import com.example.shoppieclient.domain.auth.use_cases.home.GetPopularBrandsUseCase
import com.example.shoppieclient.domain.auth.use_cases.home.GetSuggestedUseCase
import com.example.shoppieclient.domain.auth.use_cases.home.GetTopRatedUseCase
import com.example.shoppieclient.domain.auth.use_cases.home.GetTrendingShoesUseCase
import com.example.shoppieclient.domain.auth.use_cases.home.HomeApiUseCases
import com.example.shoppieclient.domain.auth.use_cases.signIn.SignInValidationUseCases
import com.example.shoppieclient.domain.auth.use_cases.signIn.SignInValidationEmailUseCase
import com.example.shoppieclient.domain.auth.use_cases.signIn.SignInValidationPasswordUseCase
import com.example.shoppieclient.domain.auth.use_cases.signUp.SignUpConfirmPasswordUseCase
import com.example.shoppieclient.domain.auth.use_cases.signUp.SignUpNameValidationUseCase
import com.example.shoppieclient.domain.auth.use_cases.signUp.SignUpValidationEmailUseCase
import com.example.shoppieclient.domain.auth.use_cases.signUp.SignUpValidationPasswordUseCase
import com.example.shoppieclient.domain.auth.use_cases.signUp.SignUpValidationUseCases
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

    private val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

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
        return Retrofit.Builder().client(client).addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL).build().create(ShoppieApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthRepo(api: ShoppieApi): ShoppieRepo {
        return ShoppieRepoImpl(api)
    }

    @Provides
    @Singleton
    fun provideSignInValidationEmailUseCase(): SignInValidationEmailUseCase {
        return SignInValidationEmailUseCase()
    }

    @Provides
    @Singleton
    fun provideSignInValidationPasswordUseCase(): SignInValidationPasswordUseCase {
        return SignInValidationPasswordUseCase()
    }

    @Provides
    @Singleton
    fun provideSignInValidationUseCases(
        signInValidationEmailUseCase: SignInValidationEmailUseCase,
        signInValidationPasswordUseCase: SignInValidationPasswordUseCase
    ): SignInValidationUseCases = SignInValidationUseCases(
        signInValidationEmailUseCase, signInValidationPasswordUseCase
    )


    @Provides
    @Singleton
    fun provideSignUpValidationNameUseCase(): SignUpNameValidationUseCase {
        return SignUpNameValidationUseCase()
    }

    @Provides
    @Singleton
    fun provideSignUpValidationEmailUseCase(): SignUpValidationEmailUseCase {
        return SignUpValidationEmailUseCase()
    }

    @Provides
    @Singleton
    fun provideSignUpValidationPasswordUseCase(): SignUpValidationPasswordUseCase {
        return SignUpValidationPasswordUseCase()
    }

    @Provides
    @Singleton
    fun provideSignUpValidationConfirmPasswordUseCase(): SignUpConfirmPasswordUseCase {
        return SignUpConfirmPasswordUseCase()
    }

    @Provides
    @Singleton
    fun provideSignUpValidationUseCases(
        signUpNameValidationUseCase: SignUpNameValidationUseCase,
        signUpValidationEmailUseCase: SignUpValidationEmailUseCase,
        signUpValidationPasswordUseCase: SignUpValidationPasswordUseCase,
        signUpConfirmPasswordUseCase: SignUpConfirmPasswordUseCase
    ): SignUpValidationUseCases = SignUpValidationUseCases(
        signUpNameValidationUseCase,
        signUpValidationEmailUseCase,
        signUpValidationPasswordUseCase,
        signUpConfirmPasswordUseCase
    )

    @Provides
    @Singleton
    fun provideGetPopularBrandsIndividualUseCase(repo: ShoppieRepo): GetPopularBrandsIndividualUseCase {
        return GetPopularBrandsIndividualUseCase(repo)
    }

    @Provides
    @Singleton
    fun provideGetNewArrivalsUseCase(repo: ShoppieRepo): GetNewArrivalsUseCase {
        return GetNewArrivalsUseCase(repo)
    }

    @Provides
    @Singleton
    fun provideGetTrendingShoesUseCase(repo: ShoppieRepo): GetTrendingShoesUseCase {
        return GetTrendingShoesUseCase(repo)
    }

    @Provides
    @Singleton
    fun provideGetTopRatedShoesUseCase(repo: ShoppieRepo): GetTopRatedUseCase {
        return GetTopRatedUseCase(repo)
    }

    @Provides
    @Singleton
    fun provideGetSuggestedUseCase(repo: ShoppieRepo): GetSuggestedUseCase {
        return GetSuggestedUseCase(repo)
    }


    @Provides
    @Singleton
    fun provideHomeApiUseCases(
        getPopularBrandsUseCase: GetPopularBrandsUseCase,
        getPopularBrandsIndividualUseCase: GetPopularBrandsIndividualUseCase,
        getNewArrivalsUseCase: GetNewArrivalsUseCase,
        getTrendingShoesUseCase: GetTrendingShoesUseCase,
        getTopRatedUseCase: GetTopRatedUseCase,
        getSuggestedUseCase: GetSuggestedUseCase
    ): HomeApiUseCases {
        return HomeApiUseCases(
            getPopularBrandsUseCase,
            getPopularBrandsIndividualUseCase,
            getNewArrivalsUseCase,
            getTrendingShoesUseCase,
            getTopRatedUseCase,
            getSuggestedUseCase
        )
    }


}