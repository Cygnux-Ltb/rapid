/* Copyright (C) 2019 Interactive Brokers LLC. All rights reserved. This code is subject to the terms
 * and conditions of the IB API Non-Commercial License or the IB API Commercial License, as applicable. */

package com.ib.client;

public final class TagValue {

    public String m_tag;
    public String m_value;

    public TagValue() {
    }

    public TagValue(String p_tag, String p_value) {
        m_tag = p_tag;
        m_value = p_value;
    }

    @Override
    public boolean equals(Object p_other) {
        if (this == p_other) {
            return true;
        }
        if (!(p_other instanceof TagValue l_theOther)) {
            return false;
        }

        return Util.StringCompare(m_tag, l_theOther.m_tag) == 0
                && Util.StringCompare(m_value, l_theOther.m_value) == 0;
    }

    @Override
    public int hashCode() {
        int result = (m_tag == null || m_tag.isEmpty()) ? 0 : m_tag.hashCode();
        result = result * 31 + ((m_value == null || m_value.isEmpty()) ? 0 : m_value.hashCode());
        return result;
    }
}
