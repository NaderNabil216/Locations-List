package com.nadernabil216.locationslist.callbacks;

import com.nadernabil216.locationslist.base.BaseCallBack;
import com.nadernabil216.locationslist.models.objects.Location;

import java.util.List;

public interface HomeCallback extends BaseCallBack {
    void setData(List<Location> locations);
}
