/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ect.web.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.omnifaces.filter.HttpFilter;
import org.primefaces.context.RequestContext;

/**
 *
 * @author totoland
 */
//@WebFilter("/pages/*")
public class LoginFilter extends HttpFilter {

    @Override
    public void doFilter
        (HttpServletRequest request, HttpServletResponse response, HttpSession session, FilterChain chain)
            throws ServletException, IOException
    {
        if (session != null && session.getAttribute("userAuthen") != null) {
            chain.doFilter(request, response);
        }
        else {
//            response.sendRedirect(request.getContextPath() + "/login/login.xhtml?y=y");
            RequestContext.getCurrentInstance().execute("alert(0);");
        }
    }

}

