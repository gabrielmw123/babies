package com.senneville.project.web.ui.listeners;

import com.senneville.project.web.ui.BabyEditor;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.ui.Button.ClickEvent;

public class SaveButtonClickListener extends BabyClickListener {

	public SaveButtonClickListener(BabyEditor babyEditor) {
		super(babyEditor);
	}

	@Override
	public void buttonClick(ClickEvent event) {
		try {
			this.getBabyEditor().getFieldGroup().commit();
		}
		catch (CommitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
