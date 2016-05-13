package com.senneville.project.web.ui.listeners;

import com.senneville.project.web.ui.BabyEditor;
import com.vaadin.ui.Button.ClickEvent;

public class CancelButtonClickListener extends BabyClickListener {

	public CancelButtonClickListener(BabyEditor babyEditor) {
		super(babyEditor);
	}

	@Override
	public void buttonClick(ClickEvent event) {
		this.getBabyEditor().getFieldGroup().discard();
	}

}
