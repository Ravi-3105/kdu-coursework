package com.caching.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Fixed {

    API_KEY("d2c2d006074d565d2db14e1709db28e2"),
    FORWARD("http://api.positionstack.com/v1/forward?access_key="),
    REVERSE("http://api.positionstack.com/v1/reverse?access_key=");

    final String value;
}
