package org.ez.vk.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
@Service
public class TextHelper {
	private List<String> goodText = new ArrayList();
	private Integer curGoodWord = 0;

	public TextHelper() {
		setGoodText();
	}

	private void setGoodText() {
		goodText.add("Круто");
		goodText.add("Я в восторге");
		goodText.add("Крутяк");
		goodText.add("Так держать");
		goodText.add("Красота");
		goodText.add("Прекрасно");
		goodText.add("Сойдет");
		goodText.add("Улет");
		goodText.add("Здорово");
		goodText.add("Неплохо");
		goodText.add("Годится");
		goodText.add("Достойно");
		goodText.add("Идеально");
		goodText.add("Годно");
		goodText.add("Годиться");

	}

	public String getGoodText() {

		return goodText.get(curGoodWord++ % goodText.size());
	}
}
