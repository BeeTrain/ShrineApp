package ru.chernakov.shrineapp.application.ui.fragments;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.chernakov.shrineapp.R;
import ru.chernakov.shrineapp.application.model.Product;
import ru.chernakov.shrineapp.application.mvp.presenters.ProductsPresenter;
import ru.chernakov.shrineapp.application.mvp.views.ProductsView;
import ru.chernakov.shrineapp.application.navigation.NavigationIconClickListener;
import ru.chernakov.shrineapp.application.ui.adapters.productcard.ProductGridItemDecoration;
import ru.chernakov.shrineapp.application.ui.views.staggeredgridlayout.StaggeredProductCardRecyclerViewAdapter;

/**
 * Фрагмент списка товаров.
 *
 * @author Chernakov M.E.
 */
public class ProductsFragment extends BaseFragment implements ProductsView {
	public static final String TAG = ProductsFragment.class.getSimpleName();

	/**
	 * {@link NestedScrollView}, в котором размещен список товаров
	 */
	@BindView(R.id.lv_product_grid)
	NestedScrollView mProductGrid;

	/**
	 * {@link RecyclerView} с товарами
	 */
	@BindView(R.id.recycler_view)
	RecyclerView mRecyclerView;

	/**
	 * Презентер
	 */
	@InjectPresenter
	ProductsPresenter mPresenter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_product_grid, container, false);
		setUpToolbar(v);
		mUnBinder = ButterKnife.bind(this, v);

		initRecyclerView();

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			mProductGrid.setBackground(getContext().getDrawable(R.drawable.shape_product_grid_background));
		}

		return v;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
		menuInflater.inflate(R.menu.toolbar_menu, menu);
		super.onCreateOptionsMenu(menu, menuInflater);
	}

	/**
	 * Задает тулбар для фрагмента
	 *
	 * @param view {@link View} фрагмента
	 */
	private void setUpToolbar(View view) {
		Toolbar toolbar = view.findViewById(R.id.app_bar);
		AppCompatActivity activity = (AppCompatActivity) getActivity();
		if (activity != null) {
			activity.setSupportActionBar(toolbar);
		}

		toolbar.setNavigationOnClickListener(new NavigationIconClickListener(
				getContext(),
				view.findViewById(R.id.lv_product_grid),
				new AccelerateDecelerateInterpolator(),
				getContext().getResources().getDrawable(R.drawable.ic_menu_branded), // Menu open icon
				getContext().getResources().getDrawable(R.drawable.ic_menu_close))); // Menu close icon
	}

	/**
	 * Инициализирует список товаров
	 */
	private void initRecyclerView() {
		mRecyclerView.setHasFixedSize(true);
		GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.HORIZONTAL, false);
		gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
			@Override
			public int getSpanSize(int position) {
				return position % 3 == 2 ? 2 : 1;
			}
		});
		mRecyclerView.setLayoutManager(gridLayoutManager);
		StaggeredProductCardRecyclerViewAdapter adapter = new StaggeredProductCardRecyclerViewAdapter(
				Product.initProductEntryList(getResources()));
		mRecyclerView.setAdapter(adapter);
		int largePadding = getResources().getDimensionPixelSize(R.dimen.staggered_product_grid_spacing_large);
		int smallPadding = getResources().getDimensionPixelSize(R.dimen.staggered_product_grid_spacing_small);
		mRecyclerView.addItemDecoration(new ProductGridItemDecoration(largePadding, smallPadding));
	}
}
