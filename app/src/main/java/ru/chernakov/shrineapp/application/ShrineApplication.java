package ru.chernakov.shrineapp.application;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatDelegate;

/**
 * Класс инициализации приложения.
 *
 * @author Chernakov M.E.
 */
public class ShrineApplication extends Application {

	/**
	 * Экземпляр приложения
	 */
	private static ShrineApplication instance;

	/**
	 * Контекст приложения
	 */
	private static Context appContext;

	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;

		this.setAppContext(getApplicationContext());
		AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
	}

	public static ShrineApplication getInstance() {
		return instance;
	}

	public static Context getAppContext() {
		return appContext;
	}

	public void setAppContext(Context mAppContext) {
		this.appContext = mAppContext;
	}
}