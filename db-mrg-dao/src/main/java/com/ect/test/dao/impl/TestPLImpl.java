/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ect.test.dao.impl;

import com.ect.test.dao.TestPL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Totoland
 */
public class TestPLImpl implements TestPL{
    private static Logger logger = LoggerFactory.getLogger(TestPLImpl.class);
    @Override
    public Object gectursor() {
        logger.debug("gectursor");
        return null;
    }

}
