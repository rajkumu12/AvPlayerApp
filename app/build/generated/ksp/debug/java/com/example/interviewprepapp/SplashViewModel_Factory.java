package com.example.interviewprepapp;

import com.example.core_module.usecase.GetLoginStateUseCase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class SplashViewModel_Factory implements Factory<SplashViewModel> {
  private final Provider<GetLoginStateUseCase> getLoginStateUseCaseProvider;

  private SplashViewModel_Factory(Provider<GetLoginStateUseCase> getLoginStateUseCaseProvider) {
    this.getLoginStateUseCaseProvider = getLoginStateUseCaseProvider;
  }

  @Override
  public SplashViewModel get() {
    return newInstance(getLoginStateUseCaseProvider.get());
  }

  public static SplashViewModel_Factory create(
      Provider<GetLoginStateUseCase> getLoginStateUseCaseProvider) {
    return new SplashViewModel_Factory(getLoginStateUseCaseProvider);
  }

  public static SplashViewModel newInstance(GetLoginStateUseCase getLoginStateUseCase) {
    return new SplashViewModel(getLoginStateUseCase);
  }
}
