/*
 * Copyright (c) 2005, 2006, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
package javax.swing.plaf.nimbus;

import java.awt.*;
import javax.swing.*;


class ProgressBarIndeterminateState extends State<JComponent> {
    ProgressBarIndeterminateState() {
        super("Indeterminate");
    }

    @Override protected boolean isInState(JComponent c) {

        return c instanceof JProgressBar &&
               ((JProgressBar)c).isIndeterminate();
               
    }
}

