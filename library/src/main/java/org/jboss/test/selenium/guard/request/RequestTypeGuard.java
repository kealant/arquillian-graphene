/*
 * JBoss, Home of Professional Open Source
 * Copyright 2010, Red Hat, Inc. and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.test.selenium.guard.request;

import java.util.Set;

import org.jboss.test.selenium.encapsulated.JavaScript;
import static org.jboss.test.selenium.utils.text.SimplifiedFormat.format;
import org.jboss.test.selenium.framework.AjaxSelenium;
import org.jboss.test.selenium.guard.AbstractGuard;

/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public abstract class RequestTypeGuard extends AbstractGuard {

    /**
     * Commands, which should be guarded - it means commands that do some interaction on client side and can call the
     * request
     */
    
    private JavaScript clearRequestDone = new JavaScript("getRFS().clearRequestDone()");
    private JavaScript getRequestDone = new JavaScript("(getRFS() === undefined) ? 'HTTP' : getRFS().getRequestDone()");

    @Override
    protected Set<String> getGuardedCommands() {
        return INTERACTIVE_COMMANDS;
    }
    
    public void doBeforeCommand() {
        getSelenium().getJavaScriptPageExtension().install();
        getSelenium().getEval(clearRequestDone);
    }

    protected void checkRequestType(RequestType requestExpected) {
        RequestType requestDone = getRequestDone();

        if (requestDone != requestExpected) {
            throw new RequestGuardException(format("Request type '{0}' was expected, but type '{1}' was done instead",
                requestExpected, requestDone));
        }
    }

    private RequestType getRequestDone() {
        String requestDone = getSelenium().getEval(getRequestDone);
        try {
            return RequestType.valueOf(requestDone);
        } catch (IllegalArgumentException e) {
            throw new RequestGuardException(format("Request was evaluated to unknown type", requestDone));
        }
    }

    private AjaxSelenium getSelenium() {
        return AjaxSelenium.getCurrentContext(this);
    }
}
