package org.wbx.gcmonitor.reporter;

import org.wbx.gcmonitor.GCInfo;

public interface GCReporter {

    void report(GCInfo gcInfo);
}
