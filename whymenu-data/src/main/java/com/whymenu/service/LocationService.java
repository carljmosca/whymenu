/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whymenu.service;

import com.google.api.services.sheets.v4.model.BatchGetValuesResponse;
import com.whymenu.data.Location;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author moscac
 */
public class LocationService extends BaseService {

    private final String SHEET_NAME = "Locations";
    private final String COLUMN_NAME_NAME = "Name";
    private final String COLUMN_NAME_DESCRIPTION = "Description";
    private final String COLUMN_NAME_ADDRESS = "Address";
    private final String COLUMN_NAME_CITY = "City";
    private final String COLUMN_NAME_STATE = "State";
    private final String COLUMN_NAME_ZIP = "Zip";
    private final String COLUMN_NAME_HOURS = "Hours";

    public List<Location> loadLocations() {
        List<Location> locations = new ArrayList<>();
        ranges.add(SHEET_NAME + "!A1:Z1");
        ranges.add(SHEET_NAME + "!A2:Z");
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
                    if (null != getStringValue(row, i)) switch (getStringValue(row, i)) {
                        case COLUMN_NAME_ADDRESS:
                            columns.put(COLUMN_NAME_ADDRESS, i);
                            break;
                        case COLUMN_NAME_NAME:
                            columns.put(COLUMN_NAME_NAME, i);
                            break;
                        case COLUMN_NAME_DESCRIPTION:
                            columns.put(COLUMN_NAME_DESCRIPTION, i);
                            break;
                        case COLUMN_NAME_CITY:
                            columns.put(COLUMN_NAME_CITY, i);
                            break;
                        case COLUMN_NAME_STATE:
                            columns.put(COLUMN_NAME_STATE, i);
                            break;
                        case COLUMN_NAME_ZIP:
                            columns.put(COLUMN_NAME_ZIP, i);
                            break;
                        case COLUMN_NAME_HOURS:
                            columns.put(COLUMN_NAME_HOURS, i);
                            break;
                        default:
                            break;
                    }
                }
            });
            values.get(1).getValues().stream().forEach((row) -> {
                Location location = new Location();
                location.setName(getStringValue(row, columns.get(COLUMN_NAME_NAME)));
                location.setDescription(getStringValue(row, columns.get(COLUMN_NAME_DESCRIPTION)));
                location.setAddress(getStringValue(row, columns.get(COLUMN_NAME_ADDRESS)));
                location.setCity(getStringValue(row, columns.get(COLUMN_NAME_CITY)));
                location.setState(getStringValue(row, columns.get(COLUMN_NAME_STATE)));
                location.setPostalCode(getStringValue(row, columns.get(COLUMN_NAME_ZIP)));
                location.setHours(getStringValue(row, columns.get(COLUMN_NAME_HOURS)));
                locations.add(location);
            });
        }
        return locations;
    }

}
