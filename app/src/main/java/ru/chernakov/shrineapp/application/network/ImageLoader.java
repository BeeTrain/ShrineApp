package ru.chernakov.shrineapp.application.network;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import ru.chernakov.shrineapp.application.ShrineApplication;

/**
 * Загрузчик изображений.
 *
 * @author Chernakov M.E.
 */
public class ImageLoader {
	private final Context context;
	private final RequestQueue requestQueue;
	private final com.android.volley.toolbox.ImageLoader imageLoader;
	private final int maxByteSize;

	/**
	 * Внутренний класс, "Initialization on Demand Holder"
	 */
	private static class ImageRequesterHolder {
		private final static ImageLoader instance = new ImageLoader();
	}

	/**
	 * Инициализирует экземпляр синглтона
	 *
	 * @return экземпляр синглтона
	 */
	public static ImageLoader getInstance() {
		return ImageRequesterHolder.instance;
	}

	/**
	 * Конструктор
	 */
	private ImageLoader() {
		context = ShrineApplication.getAppContext();
		this.requestQueue = Volley.newRequestQueue(context);
		this.requestQueue.start();
		this.maxByteSize = calculateMaxByteSize();
		this.imageLoader = new com.android.volley.toolbox.ImageLoader(requestQueue, new com.android.volley.toolbox.ImageLoader.ImageCache() {
			private final LruCache<String, Bitmap> lruCache = new LruCache<String, Bitmap>(maxByteSize) {
				@Override
				protected int sizeOf(String url, Bitmap bitmap) {
					return bitmap.getByteCount();
				}
			};

			@Override
			public synchronized Bitmap getBitmap(String url) {
				return lruCache.get(url);
			}

			@Override
			public synchronized void putBitmap(String url, Bitmap bitmap) {
				lruCache.put(url, bitmap);
			}
		});
	}

	/**
	 * Помещает изображение в указанное {@link NetworkImageView}, загруженное по заданному URL
	 *
	 * @param networkImageView {@link NetworkImageView}, в которое необходимо поместить изображение
	 * @param url              URL изображения
	 */
	public void setImageFromUrl(NetworkImageView networkImageView, String url) {
		networkImageView.setImageUrl(url, imageLoader);
	}

	private int calculateMaxByteSize() {
		DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
		final int screenBytes = displayMetrics.widthPixels * displayMetrics.heightPixels * 4;
		return screenBytes * 3;
	}
}