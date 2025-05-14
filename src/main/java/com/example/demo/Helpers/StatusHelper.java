package com.example.demo.Helpers;

import com.example.demo.Enums.StatusEnum;
import org.apache.commons.lang3.EnumUtils;

public final class StatusHelper {
    private StatusHelper(){}

    public static StatusEnum parseStatus(String statusStr){
        return EnumUtils.getEnum(StatusEnum.class,statusStr.toUpperCase());
    }
}
