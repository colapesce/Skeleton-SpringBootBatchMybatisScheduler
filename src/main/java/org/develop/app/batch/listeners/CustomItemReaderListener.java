package org.develop.app.batch.listeners;

import org.develop.app.batch.dto.interfaces.Dto;
import org.springframework.batch.core.ItemReadListener;

public class CustomItemReaderListener implements ItemReadListener<Dto> {

	@Override
	public void beforeRead() {
		System.out.println("ItemReadListener - beforeRead");
	}

	@Override
	public void afterRead(Dto item) {
		System.out.println("ItemReadListener - afterRead");
	}

	@Override
	public void onReadError(Exception ex) {
		System.out.println("ItemReadListener - onReadError");
	}

}