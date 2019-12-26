/*
 * Copyright (c) 1997, 2015, Oracle and/or its affiliates. All rights reserved.
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
package javax.swing;

import java.awt.Component;
import java.awt.Container;
import java.awt.FocusTraversalPolicy;
import java.util.Comparator;


/**
 * This class has been obsoleted by the 1.4 focus APIs. While client code may
 * still use this class, developers are strongly encouraged to use
 * <code>java.awt.KeyboardFocusManager</code> and
 * <code>java.awt.DefaultKeyboardFocusManager</code> instead.
 * <p>
 * Please see
 * <a href="http://docs.oracle.com/javase/tutorial/uiswing/misc/focus.html">
 * How to Use the Focus Subsystem</a>,
 * a section in <em>The Java Tutorial</em>, and the
 * <a href="../../java/awt/doc-files/FocusSpec.html">Focus Specification</a>
 * for more information.
 *
 * @author Arnaud Weber
 * @author David Mendenhall
 * @since 1.2
 */
@SuppressWarnings("serial") // Obsolete class
public class DefaultFocusManager extends FocusManager {

    final FocusTraversalPolicy gluePolicy =
        new LegacyGlueFocusTraversalPolicy(this);
    private final FocusTraversalPolicy layoutPolicy =
        new LegacyLayoutFocusTraversalPolicy(this);
    private final LayoutComparator comparator =
        new LayoutComparator();

    /**
     * Constructs a {@code DefaultFocusManager}.
     */
    public DefaultFocusManager() {
        setDefaultFocusTraversalPolicy(gluePolicy);
    }

    /**
     * Returns the component after.
     * @return the component after
     * @param aContainer a container
     * @param aComponent a component
     */
    public Component getComponentAfter(Container aContainer,
                                       Component aComponent)
    {
        Container root = (aContainer.isFocusCycleRoot())
            ? aContainer
            : aContainer.getFocusCycleRootAncestor();

        // Support for mixed 1.4/pre-1.4 focus APIs. If a particular root's
        // traversal policy is non-legacy, then honor it.
        if (root != null) {
            FocusTraversalPolicy policy = root.getFocusTraversalPolicy();
            if (policy != gluePolicy) {
                return policy.getComponentAfter(root, aComponent);
            }

            comparator.setComponentOrientation(root.getComponentOrientation());
            return layoutPolicy.getComponentAfter(root, aComponent);
        }

        return null;
    }

    /**
     * Returns the component before.
     * @return the component before
     * @param aContainer a container
     * @param aComponent a component
     */
    public Component getComponentBefore(Container aContainer,
                                        Component aComponent)
    {
        Container root = (aContainer.isFocusCycleRoot())
            ? aContainer
            : aContainer.getFocusCycleRootAncestor();

        // Support for mixed 1.4/pre-1.4 focus APIs. If a particular root's
        // traversal policy is non-legacy, then honor it.
        if (root != null) {
            FocusTraversalPolicy policy = root.getFocusTraversalPolicy();
            if (policy != gluePolicy) {
                return policy.getComponentBefore(root, aComponent);
            }

            comparator.setComponentOrientation(root.getComponentOrientation());
            return layoutPolicy.getComponentBefore(root, aComponent);
        }

        return null;
    }

    /**
     * Returns the first component.
     * @return the first component
     * @param aContainer a container
     */
    public Component getFirstComponent(Container aContainer) {
        Container root = (aContainer.isFocusCycleRoot())
            ? aContainer
            : aContainer.getFocusCycleRootAncestor();

        // Support for mixed 1.4/pre-1.4 focus APIs. If a particular root's
        // traversal policy is non-legacy, then honor it.
        if (root != null) {
            FocusTraversalPolicy policy = root.getFocusTraversalPolicy();
            if (policy != gluePolicy) {
                return policy.getFirstComponent(root);
            }

            comparator.setComponentOrientation(root.getComponentOrientation());
            return layoutPolicy.getFirstComponent(root);
        }

        return null;
    }

    /**
     * Returns the last component.
     * @return the last component
     * @param aContainer a container
     */
    public Component getLastComponent(Container aContainer) {
        Container root = (aContainer.isFocusCycleRoot())
            ? aContainer
            : aContainer.getFocusCycleRootAncestor();

        // Support for mixed 1.4/pre-1.4 focus APIs. If a particular root's
        // traversal policy is non-legacy, then honor it.
        if (root != null) {
            FocusTraversalPolicy policy = root.getFocusTraversalPolicy();
            if (policy != gluePolicy) {
                return policy.getLastComponent(root);
            }

            comparator.setComponentOrientation(root.getComponentOrientation());
            return layoutPolicy.getLastComponent(root);
        }

        return null;
    }

    /**
     * Compares the components by their focus traversal cycle order.
     * @param a the first component
     * @param b the second component
     * @return a comparison of the components by their focus traversal cycle order
     */
    public boolean compareTabOrder(Component a, Component b) {
        return (comparator.compare(a, b) < 0);
    }
}

@SuppressWarnings("serial") // JDK-implementation class
final class LegacyLayoutFocusTraversalPolicy
    extends LayoutFocusTraversalPolicy
{
    LegacyLayoutFocusTraversalPolicy(DefaultFocusManager defaultFocusManager) {
        super(new CompareTabOrderComparator(defaultFocusManager));
    }
}

final class CompareTabOrderComparator implements Comparator<Component> {
    private final DefaultFocusManager defaultFocusManager;

    CompareTabOrderComparator(DefaultFocusManager defaultFocusManager) {
        this.defaultFocusManager = defaultFocusManager;
    }

    public int compare(Component o1, Component o2) {
        if (o1 == o2) {
            return 0;
        }
        return (defaultFocusManager.compareTabOrder(o1, o2)) ? -1 : 1;
    }
}
