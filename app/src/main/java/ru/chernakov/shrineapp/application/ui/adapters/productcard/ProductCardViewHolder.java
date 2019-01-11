package ru.chernakov.shrineapp.application.ui.adapters.productcard;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.chernakov.shrineapp.R;

/**
 * {@link RecyclerView.ViewHolder} карточки товара.
 *
 * @author Chernakov M.E.
 */
public class ProductCardViewHolder extends RecyclerView.ViewHolder {

	/**
	 * Поле вывода изображения с товаром
	 */
	@BindView(R.id.iv_product)
	NetworkImageView productImage;

	/**
	 * Поле с названием товара
	 */
	@BindView(R.id.tv_title)
	TextView productTitle;

	/**
	 * Поле с ценой товара
	 */
	@BindView(R.id.tv_price)
	TextView productPrice;

	public ProductCardViewHolder(@NonNull View itemView) {
		super(itemView);
		ButterKnife.bind(this, itemView);
	}
}
