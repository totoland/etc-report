/*
 * ----------------------------------------------------------------------------
 * Copyright  2009 by Mobile-Technologies Co.,Ltd. . All rights reserved.
 * All intellectual property rights in and/or in the computer program and its related
 * documentation and technology are the sole Mobile-Technologies' property.
 * This computer program is under Mobile-Technologies copyright and cannot be in whole or in part
 * reproduced, sublicensed, leased, sold or
 * used in any form or by any means, including without limitation graphic,
 * electronic, mechanical,
 * photocopying, recording, taping or information storage and
 * retrieval systems without Mobile-Technologies prior written consent. The
 * downloading, exporting or reexporting of this computer program or any related
 * documentation or technology is subject to any export rules, including US
 * regulations.
 * ----------------------------------------------------------------------------
 */
package com.ect.db.common.dao.hibernate;

import com.ect.db.entity.EctConf;
import com.ect.db.entity.ReportName;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Totoland
 */
public class EctConfManager extends ArrayList<EctConf> {
    private static final long serialVersionUID = -3470980841405137340L;
    
    private List<EctConf>ectConf;

    private List<ReportName>reportNames;
    
    public EctConf getConf(String confName) {
        List<EctConf> q = ectConf;

        for(EctConf islConf : q){
            if(islConf.getConfName().equals(confName)){
                return islConf;
            }
        }

        return null;
    }

    public String getConfValue(String confName) {
        EctConf islConf = getConf(confName);

        if (islConf != null) {
            return islConf.getConfValue();
        }

        return null;
    }
    
    /**
     * @return the islConfs
     */
    public List<EctConf> getEctConfs() {
        return ectConf;
    }

    /***
     * Get Report Object
     * @param reportCode
     * @return 
     */
    public ReportName getReportObj(String reportCode) {
        List<ReportName> q = reportNames;

        for(ReportName islConf : q){
            if(islConf.getReportCode().equals(reportCode)){
                return islConf;
            }
        }

        return null;
    }
    
    /***
     * Get Report Name From Report Code
     * @param reportCode
     * @return 
     */
    public String getReportName(String reportCode) {
        ReportName islConf = getReportObj(reportCode);

        if (islConf != null) {
            return islConf.getReportName();
        }

        return null;
    }
    
    /**
     * @param islConfs the islConfs to set
     */
    public void setEctConfs(List<EctConf> ectConf) {
        this.ectConf = ectConf;
    }

    /**
     * @return the reportNames
     */
    public List<ReportName> getReportNames() {
        return reportNames;
    }

    /**
     * @param reportNames the reportNames to set
     */
    public void setReportNames(List<ReportName> reportNames) {
        this.reportNames = reportNames;
    }
    
}
