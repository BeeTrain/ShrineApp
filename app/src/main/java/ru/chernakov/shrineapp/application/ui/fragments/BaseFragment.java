package ru.chernakov.shrineapp.application.ui.fragments;

import com.arellomobile.mvp.MvpAppCompatFragment;

import butterknife.Unbinder;

/**
 * Базовый класс фрагмента приложения.
 *
 * @author Chernakov M.E.
 */
public class BaseFragment extends MvpAppCompatFragment {

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
