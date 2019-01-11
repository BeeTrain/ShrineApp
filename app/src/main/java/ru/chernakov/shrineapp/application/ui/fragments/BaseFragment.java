package ru.chernakov.shrineapp.application.ui.fragments;

import android.support.v4.app.Fragment;

import butterknife.Unbinder;

/**
 * Базовый класс фрагмента приложения.
 *
 * @author Chernakov M.E.
 */
public class BaseFragment extends Fragment {

	/**
	 * Инфраструктурный объект Butterknife
	 */
	protected Unbinder mUnBinder;

	@Override
	public void onDestroyView() {
		super.onDestroyView();

		if (mUnBinder != null) {
			mUnBinder.unbind();
		}
	}
}
