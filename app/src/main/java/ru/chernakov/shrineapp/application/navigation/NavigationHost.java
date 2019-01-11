package ru.chernakov.shrineapp.application.navigation;

import android.support.v4.app.Fragment;

/**
 * Интерфейс хоста, который может отображать фрагменты и как осуществлять навигацию между фрагментами.
 *
 * @author Chernakov M.E.
 */
public interface NavigationHost {

	/**
	 * Осуществляет переход на указанный фрагмент
	 *
	 * @param fragment       фрагмент, на который осуществляется переход
	 * @param addToBackstack true, если транзакцию необходимо сохранить в стэк
	 */
	void navigateTo(Fragment fragment, boolean addToBackstack);
}
