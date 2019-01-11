package ru.chernakov.shrineapp.application.model;

import android.content.res.Resources;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import ru.chernakov.shrineapp.R;

/**
 * Сущность товара.
 *
 * @author Chernakov M.E.
 */
public class Product {
	private static final String TAG = Product.class.getSimpleName();

	/**
	 * Название товара
	 */
	public final String title;

	/**
	 * Url для загрузки изображения
	 */
	public final String url;

	/**
	 * Цена товара
	 */
	public final String price;

	/**
	 * Описание товара
	 */
	public final String description;

	/**
	 * Конструктор
	 *
	 * @param title       название товара
	 * @param url         ссылка на скачивание изображения
	 * @param price       цена товара
	 * @param description описание товара
	 */
	public Product(String title, String url, String price, String description) {
		this.title = title;
		this.url = url;
		this.price = price;
		this.description = description;
	}


	/**
	 * Загрузка списка товаров из JSON файла ресурсов
	 *
	 * @param resources JSON файл ресурсов, из которого можно загрузить товар
	 * @return список товаров
	 */
	public static List<Product> initProductEntryList(Resources resources) {
		InputStream inputStream = resources.openRawResource(R.raw.products);
		Writer writer = new StringWriter();
		char[] buffer = new char[1024];
		try {
			Reader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
			int pointer;
			while ((pointer = reader.read(buffer)) != -1) {
				writer.write(buffer, 0, pointer);
			}
		} catch (IOException exception) {
			Log.e(TAG, "Ошибка чтения/записи из JSON файла.", exception);
		} finally {
			try {
				inputStream.close();
			} catch (IOException exception) {
				Log.e(TAG, "Ошибка при закрытии потока.", exception);
			}
		}
		String jsonProductsString = writer.toString();
		Gson gson = new Gson();
		Type productListType = new TypeToken<ArrayList<Product>>() {
		}.getType();
		return gson.fromJson(jsonProductsString, productListType);
	}
}