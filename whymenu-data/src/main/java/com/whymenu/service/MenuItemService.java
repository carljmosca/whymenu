/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whymenu.service;

import com.google.api.services.sheets.v4.model.BatchGetValuesResponse;
import com.whymenu.data.MenuItem;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author moscac
 */
public class MenuItemService extends BaseService {

    private final String SHEET_NAME = "Locations";
    private final String COLUMN_NAME_NAME = "Name";
    private final String COLUMN_NAME_DESCRIPTION = "Description";
    private final String COLUMN_NAME_INDEX = "Index";
    private final String COLUMN_NAME_MULTISELECT = "Multiselect";

    public List<MenuItem> loadMenuItems(String locationName) {
        List<MenuItem> menuItems = new ArrayList<>();
        ranges.clear();
        ranges.add(locationName + " Menu!A1:Z1");
        ranges.add(locationName + " Menu!A1:Z1");
        try {
            BatchGetValuesResponse response
                    = service.spreadsheets().values().
                    batchGet(spreadsheetId).setRanges(ranges)
                    .execute();
            values = response.getValueRanges();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        if (values == null || values.isEmpty()) {
            System.out.println("No data found.");
        } else {
            values.get(0).getValues().stream().forEach((row) -> {
                for (int i = 0; i < row.size(); i++) {
                    if (null != getStringValue(row, i)) {
                        switch (getStringValue(row, i)) {
                            case COLUMN_NAME_NAME:
                                columns.put(COLUMN_NAME_NAME, i);
                                break;
                            case COLUMN_NAME_DESCRIPTION:
                                columns.put(COLUMN_NAME_DESCRIPTION, i);
                                break;
                            case COLUMN_NAME_INDEX:
                                columns.put(COLUMN_NAME_INDEX, i);
                                break;
                            default:
                                break;
                        }
                    }
                }
            });
            values.get(1).getValues().stream().forEach((row) -> {
                MenuItem menuItem = new MenuItem();
                menuItem.setName(getStringValue(row, columns.get(COLUMN_NAME_NAME)));
                menuItem.setDescription(getStringValue(row, columns.get(COLUMN_NAME_DESCRIPTION)));
                menuItem.setIndex(getIntValue(row, columns.get(COLUMN_NAME_INDEX)));
                menuItems.add(menuItem);
            });
        }
        return menuItems;
    }

}
