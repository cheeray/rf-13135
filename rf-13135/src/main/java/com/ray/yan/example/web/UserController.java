/*
 * JBoss, Home of Professional Open Source
 * Copyright 2012, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the 
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,  
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ray.yan.example.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.richfaces.component.UIExtendedDataTable;

import com.ray.yan.example.domain.User;

@Named
@RequestScoped
public class UserController {

	@Inject
	Logger logger;
	
    private Collection<Object> selection = new HashSet<Object>();
    
    /** A string represents the id of clicked row. */
	private String clickedRow;
	
	/** Selected users. */
	private List<User> selectedUsers = new ArrayList<User>();
	
	/**
	 * Listen on the AJAX selection event and grouping selections.
	 * 
	 * @param event
	 *            The AJAX selection event.
	 */
	public void selectionListener(AjaxBehaviorEvent event) {
		UIExtendedDataTable dataTable = (UIExtendedDataTable) event.getComponent();
        Object originalKey = dataTable.getRowKey();
        selectedUsers.clear();
        for (Object selectionKey : selection) {
            dataTable.setRowKey(selectionKey);
            if (dataTable.isRowAvailable()) {
                selectedUsers.add((User) dataTable.getRowData());
            }
        }
        dataTable.setRowKey(originalKey);
	}

	public Collection<Object> getSelection() {
		return selection;
	}

	public void setSelection(Collection<Object> selection) {
		this.selection = selection;
	}
	public String getClickedRow() {
		return clickedRow;
	}

	public void setClickedRow(String clickedRow) {
		this.clickedRow = clickedRow;
	}

	public List<User> getSelectedUsers() {
		return selectedUsers;
	}

	public void setSelectedUsers(List<User> selectedUsers) {
		this.selectedUsers = selectedUsers;
	}

}
