/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whymenu.service;

import com.google.api.services.sheets.v4.model.BatchGetValuesResponse;
import com.whymenu.data.MenuItem;
import com.whymenu.data.MenuItemAttribute;
import com.whymenu.data.MenuItemAttributeOption;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author moscac
 */
public class MenuItemService extends BaseService implements Serializable {

    private final String COLUMN_NAME_NAME = "Name";
    private final String COLUMN_NAME_DESCRIPTION = "Description";
    private final String COLUMN_NAME_ORDER = "Order";
    private final String COLUMN_NAME_A1_DESCRIPTION = "A1 Description";
    private final String COLUMN_NAME_A1_ORDER = "A1 Order";
    private final String COLUMN_NAME_A1_MULTISELECT = "A1 Multiselect";
    private final String COLUMN_NAME_A1_OPTION = "A1 Option";
    private final String COLUMN_NAME_A1_OPTION_ORDER = "A1 Option Order";
    private final String COLUMN_NAME_A1_OPTION_AVAILABLE = "A1 Option Available";
    private final String COLUMN_NAME_A2_DESCRIPTION = "A2 Description";
    private final String COLUMN_NAME_A2_ORDER = "A2 Order";
    private final String COLUMN_NAME_A2_MULTISELECT = "A2 Multiselect";
    private final String COLUMN_NAME_A2_OPTION = "A2 Option";
    private final String COLUMN_NAME_A2_OPTION_ORDER = "A2 Option Order";
    private final String COLUMN_NAME_A2_OPTION_AVAILABLE = "A2 Option Available";
    private final String COLUMN_NAME_A3_DESCRIPTION = "A3 Description";
    private final String COLUMN_NAME_A3_ORDER = "A3 Order";
    private final String COLUMN_NAME_A3_MULTISELECT = "A3 Multiselect";
    private final String COLUMN_NAME_A3_OPTION = "A3 Option";
    private final String COLUMN_NAME_A3_OPTION_ORDER = "A3 Option Order";
    private final String COLUMN_NAME_A3_OPTION_AVAILABLE = "A3 Option Available";

    public List<MenuItem> loadMenuItems(String locationName) {
        List<MenuItem> menuItems = new ArrayList<>();
        ranges.clear();
        ranges.add(locationName + " Menu!A1:Z1");
        ranges.add(locationName + " Menu!A2:Z");
        try {
            BatchGetValuesResponse response
                    = service.spreadsheets().values().
                    batchGet(menuSpreadsheetId).setRanges(ranges)
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
                            case COLUMN_NAME_ORDER:
                                columns.put(COLUMN_NAME_ORDER, i);
                                break;
                            case COLUMN_NAME_A1_DESCRIPTION:
                                columns.put(COLUMN_NAME_A1_DESCRIPTION, i);
                                break;
                            case COLUMN_NAME_A1_ORDER:
                                columns.put(COLUMN_NAME_A1_ORDER, i);
                                break;
                            case COLUMN_NAME_A1_MULTISELECT:
                                columns.put(COLUMN_NAME_A1_MULTISELECT, i);
                                break;
                            case COLUMN_NAME_A1_OPTION:
                                columns.put(COLUMN_NAME_A1_OPTION, i);
                                break;
                            case COLUMN_NAME_A1_OPTION_ORDER:
                                columns.put(COLUMN_NAME_A1_OPTION_ORDER, i);
                                break;
                            case COLUMN_NAME_A1_OPTION_AVAILABLE:
                                columns.put(COLUMN_NAME_A1_OPTION_AVAILABLE, i);
                                break;
                            case COLUMN_NAME_A2_DESCRIPTION:
                                columns.put(COLUMN_NAME_A2_DESCRIPTION, i);
                                break;
                            case COLUMN_NAME_A2_ORDER:
                                columns.put(COLUMN_NAME_A2_ORDER, i);
                                break;
                            case COLUMN_NAME_A2_MULTISELECT:
                                columns.put(COLUMN_NAME_A2_MULTISELECT, i);
                                break;
                            case COLUMN_NAME_A2_OPTION:
                                columns.put(COLUMN_NAME_A2_OPTION, i);
                                break;
                            case COLUMN_NAME_A2_OPTION_ORDER:
                                columns.put(COLUMN_NAME_A2_OPTION_ORDER, i);
                                break;
                            case COLUMN_NAME_A2_OPTION_AVAILABLE:
                                columns.put(COLUMN_NAME_A2_OPTION_AVAILABLE, i);
                                break;
                            case COLUMN_NAME_A3_DESCRIPTION:
                                columns.put(COLUMN_NAME_A3_DESCRIPTION, i);
                                break;
                            case COLUMN_NAME_A3_ORDER:
                                columns.put(COLUMN_NAME_A3_ORDER, i);
                                break;
                            case COLUMN_NAME_A3_MULTISELECT:
                                columns.put(COLUMN_NAME_A3_MULTISELECT, i);
                                break;
                            case COLUMN_NAME_A3_OPTION:
                                columns.put(COLUMN_NAME_A3_OPTION, i);
                                break;
                            case COLUMN_NAME_A3_OPTION_ORDER:
                                columns.put(COLUMN_NAME_A3_OPTION_ORDER, i);
                                break;
                            case COLUMN_NAME_A3_OPTION_AVAILABLE:
                                columns.put(COLUMN_NAME_A3_OPTION_AVAILABLE, i);
                                break;
                            default:
                                break;
                        }
                    }
                }
            });
            MenuItem menuItem = new MenuItem();
            for (List<Object> row : values.get(1).getValues()) {
                if (columns.containsKey(COLUMN_NAME_NAME)
                        && !getStringValue(row, COLUMN_NAME_NAME).isEmpty()) {
                    menuItem = new MenuItem();
                    menuItems.add(menuItem);
                    menuItem.setName(getStringValue(row, COLUMN_NAME_NAME));
                    menuItem.setDescription(getStringValue(row, COLUMN_NAME_DESCRIPTION));
                    menuItem.setOrder(getIntValue(row, COLUMN_NAME_ORDER));
                }
                processAttribute(menuItem, row, COLUMN_NAME_A1_DESCRIPTION, COLUMN_NAME_A1_ORDER,
                        COLUMN_NAME_A1_MULTISELECT, 1);
                processAttribute(menuItem, row, COLUMN_NAME_A2_DESCRIPTION, COLUMN_NAME_A2_ORDER,
                        COLUMN_NAME_A2_MULTISELECT, 2);
                processAttribute(menuItem, row, COLUMN_NAME_A3_DESCRIPTION, COLUMN_NAME_A3_ORDER,
                        COLUMN_NAME_A3_MULTISELECT, 3);
                processAttributeOption(menuItem, row, COLUMN_NAME_A1_OPTION, COLUMN_NAME_A1_OPTION_ORDER, 
                        COLUMN_NAME_A1_OPTION_AVAILABLE, 1);
                processAttributeOption(menuItem, row, COLUMN_NAME_A2_OPTION, COLUMN_NAME_A2_OPTION_ORDER, 
                        COLUMN_NAME_A2_OPTION_AVAILABLE, 2);
                processAttributeOption(menuItem, row, COLUMN_NAME_A3_OPTION, COLUMN_NAME_A3_OPTION_ORDER, 
                        COLUMN_NAME_A3_OPTION_AVAILABLE, 3);
            }
        }
        return menuItems;
    }

    private void processAttribute(MenuItem menuItem, List<Object> row, String description,
            String order, String multiselect, int attribute) {
        if (columns.containsKey(description)
                && !getStringValue(row, description).isEmpty()
                && menuItem.getAttributes().size() < attribute) {
            MenuItemAttribute menuItemAttribute = new MenuItemAttribute();
            menuItem.getAttributes().add(menuItemAttribute);
            menuItemAttribute.setDescription(getStringValue(row, description));
            menuItemAttribute.setOrder(getIntValue(row, order));
            menuItemAttribute.setMultiSelect(getBooleanValue(row, multiselect));
        }
    }

    private void processAttributeOption(MenuItem menuItem, List<Object> row, String option,
            String order, String available, int attribute) {
        if (columns.containsKey(option)
                && !getStringValue(row, option).isEmpty()
                && menuItem.getAttributes().size() >= attribute) {
            MenuItemAttributeOption menuItemAttributeOption = new MenuItemAttributeOption();
            MenuItemAttribute menuItemAttribute = menuItem.getAttributes().get(attribute - 1);
            menuItemAttribute.getDetails().add(menuItemAttributeOption);
            menuItemAttributeOption.setDescription(getStringValue(row, option));
            menuItemAttributeOption.setOrder(getIntValue(row, order));
            menuItemAttributeOption.setAvailable(getBooleanValue(row, available));
            menuItemAttributeOption.setAttributeDescription(menuItemAttribute.getDescription());
        }

    }

}
