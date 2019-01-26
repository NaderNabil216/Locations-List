package com.nadernabil216.locationslist.callbacks;

import com.nadernabil216.locationslist.models.objects.Location;

public interface LocationDetailCallBack {
    void initialization();
    void setDataAndDisableFields(Location location);
}
