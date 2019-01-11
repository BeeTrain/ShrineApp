package ru.chernakov.shrineapp.application.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import ru.chernakov.shrineapp.R;
import ru.chernakov.shrineapp.application.navigation.NavigationHost;

/**
 * Фрагмент авторизации в приложении.
 *
 * @author Chernakov M.E.
 */
public class LoginFragment extends BaseFragment {

	/**
	 * Виджет обертки поля для ввода пароля
	 */
	@BindView(R.id.tv_password_input_text)
	TextInputLayout mPasswordLayout;

	/**
	 * Поле ввода пароля
	 */
	@BindView(R.id.et_password)
	TextInputEditText mPassword;

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_login, container, false);

		mUnBinder = ButterKnife.bind(this, v);

		return v;
	}

	@OnClick(R.id.bt_next)
	public void onNextClicked() {
		if (!isPasswordValid(mPassword.getText())) {
			mPasswordLayout.setError(getString(R.string.error_password));
		} else {
			mPasswordLayout.setError(null);
			((NavigationHost) getActivity()).navigateTo(new ProductGridFragment(), false);
		}
	}

	@OnTextChanged(R.id.et_password)
	public void onPasswordTextChanged() {
		if (isPasswordValid(mPassword.getText())) {
			mPasswordLayout.setError(null);
		}
	}

	/**
	 * Проверяет введенный пароль
	 *
	 * @param text введенный пароль
	 * @return true, если пароль введен корректно, false в противном случае
	 */
	private boolean isPasswordValid(@Nullable Editable text) {
		return text != null && text.length() >= 8;
	}
}
