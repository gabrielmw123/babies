package com.senneville.project.web.ui.listeners;

import lombok.AllArgsConstructor;
import lombok.Data;

import com.senneville.project.web.ui.BabyEditor;
import com.vaadin.ui.Button.ClickListener;

@AllArgsConstructor
@Data
public abstract class BabyClickListener implements ClickListener {

	private BabyEditor babyEditor;
}
