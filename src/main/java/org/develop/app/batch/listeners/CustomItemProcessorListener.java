package org.develop.app.batch.listeners;

import org.develop.app.batch.dto.interfaces.Dto;
import org.springframework.batch.core.ItemProcessListener;

public class CustomItemProcessorListener implements ItemProcessListener<Dto, Dto>{

	@Override
	public void beforeProcess(Dto item) {
		System.out.println("ItemProcessListener - beforeProcess");		
	}

	@Override
	public void afterProcess(Dto item, Dto result) {
		System.out.println("ItemProcessListener - afterProcess");
		
	}

	@Override
	public void onProcessError(Dto item, Exception e) {
		System.out.println("ItemProcessListener - onErrorProcess");
		
	}

}
