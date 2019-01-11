package ru.chernakov.shrineapp.application.ui.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import ru.chernakov.shrineapp.application.ui.fragments.LoginFragment;
import ru.chernakov.shrineapp.application.navigation.NavigationHost;
import ru.chernakov.shrineapp.R;

/**
 * Основное активити приложения.
 *
 * @author Chernakov M.E.
 */
public class MainActivity extends AppCompatActivity implements NavigationHost {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager()
					.beginTransaction()
					.add(R.id.container, new LoginFragment())
					.commit();
		}
	}

	/**
	 * Перенаправляет на указанный фрагмент
	 *
	 * @param fragment       фрагмент, на который осуществляется переход
	 * @param addToBackstack true, если транзакцию необходимо сохранить в стэк
	 */
	@Override
	public void navigateTo(Fragment fragment, boolean addToBackstack) {
		FragmentTransaction transaction =
				getSupportFragmentManager()
						.beginTransaction()
						.replace(R.id.container, fragment);

		if (addToBackstack) {
			transaction.addToBackStack(null);
		}

		transaction.commit();
	}
}
