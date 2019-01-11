package ru.chernakov.shrineapp.application.ui.adapters.productcard;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ru.chernakov.shrineapp.R;
import ru.chernakov.shrineapp.application.network.ImageLoader;
import ru.chernakov.shrineapp.application.model.Product;

/**
 * Адаптер, используемый для отображения простой сетки товаров.
 *
 * @author Chernakov M.E.
 */
public class ProductCardRecyclerViewAdapter extends RecyclerView.Adapter<ProductCardViewHolder> {

	/**
	 * Список товаров
	 */
	private List<Product> productList;

	/**
	 * Загрузчик изображений для товаров
	 */
	private ImageLoader imageLoader;

	/**
	 * Конструктор
	 *
	 * @param productList список товаров
	 */
	public ProductCardRecyclerViewAdapter(List<Product> productList) {
		this.productList = productList;
		imageLoader = ImageLoader.getInstance();
	}

	@NonNull
	@Override
	public ProductCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_card, parent, false);
		return new ProductCardViewHolder(layoutView);
	}

	@Override
	public void onBindViewHolder(@NonNull ProductCardViewHolder holder, int position) {
		if (productList != null && position < productList.size()) {
			Product product = productList.get(position);
			holder.productTitle.setText(product.title);
			holder.productPrice.setText(product.price);
			imageLoader.setImageFromUrl(holder.productImage, product.url);
		}
	}

	@Override
	public int getItemCount() {
		return productList.size();
	}
}
