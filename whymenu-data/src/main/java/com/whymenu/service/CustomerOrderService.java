/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whymenu.service;

import com.google.api.services.sheets.v4.model.AppendValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.whymenu.data.CustomerOrder;
import com.whymenu.data.CustomerOrderLine;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Logger;

/**
 *
 * @author moscac
 */
public class CustomerOrderService extends BaseService implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(CustomerOrderService.class.getName());

    public boolean saveOrder(String orderSheetId, CustomerOrder customerOrder, String timeZoneID) {
        boolean result = false;
        ValueRange vr = new ValueRange();
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
        TimeZone timeZone;
        if (timeZoneID == null) {
            timeZone = TimeZone.getTimeZone(timeZoneID);
        } else {
            timeZone = TimeZone.getTimeZone("America/New_York");
        }
        sdf.setTimeZone(timeZone);
        List<List<Object>> orderLines = new ArrayList<>();
        int row = 0;
        for (CustomerOrderLine customerOrderLine : customerOrder.getCustomerOrderLines()) {
            List<Object> rowValues = new ArrayList<>();
            if (row++ == 0) {
                rowValues.add(sdf.format(new Date()));
                rowValues.add(customerOrder.getName());
            } else {
                rowValues.add("");
                rowValues.add("");
            }
            rowValues.add(customerOrderLine.getDescription());
            orderLines.add(rowValues);
        }
        vr = vr.setRange("Sheet1!a1:c1").setValues(orderLines).setMajorDimension("ROWS");

        try {
            AppendValuesResponse response = service.spreadsheets().values()
                    .append(orderSheetId, "Sheet1!a1:c1", vr).setValueInputOption("USER_ENTERED")
                    .execute();
            result = ((response != null) && (response.getUpdates().getUpdatedRows() == 1));
        } catch (IOException e) {
            LOGGER.warning(e.getMessage());
        }
        return result;
    }
}
