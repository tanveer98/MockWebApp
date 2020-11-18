package com.tanveer.timezone;

import com.tanveer.common.Constants;
import com.tanveer.common.ResponseModelBase;

public class ResponseModel extends ResponseModelBase {

    public long zipCode;
    public String timeZoneString;
    public int utcOffset;

    public ResponseModel() {
        super(200, Constants.RESULT_OK);
    }

    public long getZipCode() {
        return zipCode;
    }

    public void setZipCode(long zipCode) {
        this.zipCode = zipCode;
    }

    public String getTimeZoneString() {
        return timeZoneString;
    }

    public void setTimeZoneString(String timeZoneString) {
        this.timeZoneString = timeZoneString;
    }

    public int getUtcOffset() {
        return utcOffset;
    }

    public void setUtcOffset(int utcOffset) {
        this.utcOffset = utcOffset;
    }

    @Override
    public String toString() {
        return "TimeZoneModel{" +
                "zipCode=" + zipCode +
                ", timeZoneString='" + timeZoneString + '\'' +
                ", utcOffset=" + utcOffset +
                '}';
    }

}
