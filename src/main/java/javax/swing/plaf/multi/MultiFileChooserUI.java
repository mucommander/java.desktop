/*
 * Copyright (c) 1997, 2014, Oracle and/or its affiliates. All rights reserved.
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
package javax.swing.plaf.multi;

import java.util.Vector;
import javax.swing.plaf.FileChooserUI;
import javax.swing.filechooser.FileFilter;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileView;
import java.lang.String;
import java.io.File;
import javax.swing.plaf.ComponentUI;
import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Dimension;
import javax.accessibility.Accessible;

/**
 * A multiplexing UI used to combine <code>FileChooserUI</code>s.
 *
 * <p>This file was automatically generated by AutoMulti.
 *
 * @author  Otto Multey
 */
public class MultiFileChooserUI extends FileChooserUI {

    /**
     * The vector containing the real UIs.  This is populated
     * in the call to <code>createUI</code>, and can be obtained by calling
     * the <code>getUIs</code> method.  The first element is guaranteed to be the real UI
     * obtained from the default look and feel.
     */
    protected Vector<ComponentUI> uis = new Vector<>();

////////////////////
// Common UI methods
////////////////////

    /**
     * Returns the list of UIs associated with this multiplexing UI.  This
     * allows processing of the UIs by an application aware of multiplexing
     * UIs on components.
     *
     * @return an array of the UI delegates
     */
    public ComponentUI[] getUIs() {
        return MultiLookAndFeel.uisToArray(uis);
    }

////////////////////
// FileChooserUI methods
////////////////////

    /**
     * Invokes the <code>getAcceptAllFileFilter</code> method on each UI handled by this object.
     *
     * @return the value obtained from the first UI, which is
     * the UI obtained from the default <code>LookAndFeel</code>
     */
    public FileFilter getAcceptAllFileFilter(JFileChooser a) {
        FileFilter returnValue =
            ((FileChooserUI) (uis.elementAt(0))).getAcceptAllFileFilter(a);
        for (int i = 1; i < uis.size(); i++) {
            ((FileChooserUI) (uis.elementAt(i))).getAcceptAllFileFilter(a);
        }
        return returnValue;
    }

    /**
     * Invokes the <code>getFileView</code> method on each UI handled by this object.
     *
     * @return the value obtained from the first UI, which is
     * the UI obtained from the default <code>LookAndFeel</code>
     */
    public FileView getFileView(JFileChooser a) {
        FileView returnValue =
            ((FileChooserUI) (uis.elementAt(0))).getFileView(a);
        for (int i = 1; i < uis.size(); i++) {
            ((FileChooserUI) (uis.elementAt(i))).getFileView(a);
        }
        return returnValue;
    }

    /**
     * Invokes the <code>getApproveButtonText</code> method on each UI handled by this object.
     *
     * @return the value obtained from the first UI, which is
     * the UI obtained from the default <code>LookAndFeel</code>
     */
    public String getApproveButtonText(JFileChooser a) {
        String returnValue =
            ((FileChooserUI) (uis.elementAt(0))).getApproveButtonText(a);
        for (int i = 1; i < uis.size(); i++) {
            ((FileChooserUI) (uis.elementAt(i))).getApproveButtonText(a);
        }
        return returnValue;
    }

    /**
     * Invokes the <code>getDialogTitle</code> method on each UI handled by this object.
     *
     * @return the value obtained from the first UI, which is
     * the UI obtained from the default <code>LookAndFeel</code>
     */
    public String getDialogTitle(JFileChooser a) {
        String returnValue =
            ((FileChooserUI) (uis.elementAt(0))).getDialogTitle(a);
        for (int i = 1; i < uis.size(); i++) {
            ((FileChooserUI) (uis.elementAt(i))).getDialogTitle(a);
        }
        return returnValue;
    }

    /**
     * Invokes the <code>rescanCurrentDirectory</code> method on each UI handled by this object.
     */
    public void rescanCurrentDirectory(JFileChooser a) {
        for (int i = 0; i < uis.size(); i++) {
            ((FileChooserUI) (uis.elementAt(i))).rescanCurrentDirectory(a);
        }
    }

    /**
     * Invokes the <code>ensureFileIsVisible</code> method on each UI handled by this object.
     */
    public void ensureFileIsVisible(JFileChooser a, File b) {
        for (int i = 0; i < uis.size(); i++) {
            ((FileChooserUI) (uis.elementAt(i))).ensureFileIsVisible(a,b);
        }
    }

////////////////////
// ComponentUI methods
////////////////////

    /**
     * Invokes the <code>contains</code> method on each UI handled by this object.
     *
     * @return the value obtained from the first UI, which is
     * the UI obtained from the default <code>LookAndFeel</code>
     */
    public boolean contains(JComponent a, int b, int c) {
        boolean returnValue =
            uis.elementAt(0).contains(a,b,c);
        for (int i = 1; i < uis.size(); i++) {
            uis.elementAt(i).contains(a,b,c);
        }
        return returnValue;
    }

    /**
     * Invokes the <code>update</code> method on each UI handled by this object.
     */
    public void update(Graphics a, JComponent b) {
        for (int i = 0; i < uis.size(); i++) {
            uis.elementAt(i).update(a,b);
        }
    }

    /**
     * Returns a multiplexing UI instance if any of the auxiliary
     * <code>LookAndFeel</code>s supports this UI.  Otherwise, just returns the
     * UI object obtained from the default <code>LookAndFeel</code>.
     *
     * @param  a the component to create the UI for
     * @return the UI delegate created
     */
    public static ComponentUI createUI(JComponent a) {
        MultiFileChooserUI mui = new MultiFileChooserUI();
        return MultiLookAndFeel.createUIs(mui, mui.uis, a);
    }

    /**
     * Invokes the <code>installUI</code> method on each UI handled by this object.
     */
    public void installUI(JComponent a) {
        for (int i = 0; i < uis.size(); i++) {
            uis.elementAt(i).installUI(a);
        }
    }

    /**
     * Invokes the <code>uninstallUI</code> method on each UI handled by this object.
     */
    public void uninstallUI(JComponent a) {
        for (int i = 0; i < uis.size(); i++) {
            uis.elementAt(i).uninstallUI(a);
        }
    }

    /**
     * Invokes the <code>paint</code> method on each UI handled by this object.
     */
    public void paint(Graphics a, JComponent b) {
        for (int i = 0; i < uis.size(); i++) {
            uis.elementAt(i).paint(a,b);
        }
    }

    /**
     * Invokes the <code>getPreferredSize</code> method on each UI handled by this object.
     *
     * @return the value obtained from the first UI, which is
     * the UI obtained from the default <code>LookAndFeel</code>
     */
    public Dimension getPreferredSize(JComponent a) {
        Dimension returnValue =
            uis.elementAt(0).getPreferredSize(a);
        for (int i = 1; i < uis.size(); i++) {
            uis.elementAt(i).getPreferredSize(a);
        }
        return returnValue;
    }

    /**
     * Invokes the <code>getMinimumSize</code> method on each UI handled by this object.
     *
     * @return the value obtained from the first UI, which is
     * the UI obtained from the default <code>LookAndFeel</code>
     */
    public Dimension getMinimumSize(JComponent a) {
        Dimension returnValue =
            uis.elementAt(0).getMinimumSize(a);
        for (int i = 1; i < uis.size(); i++) {
            uis.elementAt(i).getMinimumSize(a);
        }
        return returnValue;
    }

    /**
     * Invokes the <code>getMaximumSize</code> method on each UI handled by this object.
     *
     * @return the value obtained from the first UI, which is
     * the UI obtained from the default <code>LookAndFeel</code>
     */
    public Dimension getMaximumSize(JComponent a) {
        Dimension returnValue =
            uis.elementAt(0).getMaximumSize(a);
        for (int i = 1; i < uis.size(); i++) {
            uis.elementAt(i).getMaximumSize(a);
        }
        return returnValue;
    }

    /**
     * Invokes the <code>getAccessibleChildrenCount</code> method on each UI handled by this object.
     *
     * @return the value obtained from the first UI, which is
     * the UI obtained from the default <code>LookAndFeel</code>
     */
    public int getAccessibleChildrenCount(JComponent a) {
        int returnValue =
            uis.elementAt(0).getAccessibleChildrenCount(a);
        for (int i = 1; i < uis.size(); i++) {
            uis.elementAt(i).getAccessibleChildrenCount(a);
        }
        return returnValue;
    }

    /**
     * Invokes the <code>getAccessibleChild</code> method on each UI handled by this object.
     *
     * @return the value obtained from the first UI, which is
     * the UI obtained from the default <code>LookAndFeel</code>
     */
    public Accessible getAccessibleChild(JComponent a, int b) {
        Accessible returnValue =
            uis.elementAt(0).getAccessibleChild(a,b);
        for (int i = 1; i < uis.size(); i++) {
            uis.elementAt(i).getAccessibleChild(a,b);
        }
        return returnValue;
    }
}
