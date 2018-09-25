package org.develop.app.batch.listeners;

import java.util.List;

import org.develop.app.batch.dto.interfaces.Dto;
import org.springframework.batch.core.ItemWriteListener;

public class CustomItemWriterListener implements ItemWriteListener<Dto> {

	@Override
	public void beforeWrite(List<? extends Dto> items) {
		System.out.println("ItemWriteListener - beforeWrite");
	}

	@Override
	public void afterWrite(List<? extends Dto> items) {
		System.out.println("ItemWriteListener - afterWrite");
	}

	@Override
	public void onWriteError(Exception exception, List<? extends Dto> items) {
		System.out.println("ItemWriteListener - onWriteError");
	}

}