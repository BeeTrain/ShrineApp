package ru.chernakov.shrineapp.application.ui.views.staggeredgridlayout;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ru.chernakov.shrineapp.R;
import ru.chernakov.shrineapp.application.model.Product;
import ru.chernakov.shrineapp.application.network.ImageLoader;

/**
 * Адаптер, используемый для отображения ассиметричной сетки товаров, с 2 товарами в перевой колонке
 * и 1 товаром во второй, и так далее.
 *
 * @author Chernakov M.E.
 */
public class StaggeredProductCardRecyclerViewAdapter extends RecyclerView.Adapter<StaggeredProductCardViewHolder> {

	/**
	 * Список товаров
	 */
	private List<Product> mProducts;

	/**
	 * Загрузчик изображений
	 */
	private ImageLoader mImageLoader;

	public StaggeredProductCardRecyclerViewAdapter(List<Product> products) {
		this.mProducts = products;
		mImageLoader = ImageLoader.getInstance();
	}

	@Override
	public int getItemViewType(int position) {
		return position % 3;
	}

	@NonNull
	@Override
	public StaggeredProductCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		int layoutId = R.layout.item_staggered_product_card_first;
		if (viewType == 1) {
			layoutId = R.layout.item_staggered_product_card_second;
		} else if (viewType == 2) {
			layoutId = R.layout.item_staggered_product_card_third;
		}

		View layoutView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
		return new StaggeredProductCardViewHolder(layoutView);
	}

	@Override
	public void onBindViewHolder(@NonNull StaggeredProductCardViewHolder holder, int position) {
		if (mProducts != null && position < mProducts.size()) {
			Product product = mProducts.get(position);
			holder.productTitle.setText(product.title);
			holder.productPrice.setText(product.price);
			mImageLoader.setImageFromUrl(holder.productImage, product.url);
		}
	}

	@Override
	public int getItemCount() {
		return mProducts.size();
	}
}
