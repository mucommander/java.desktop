/*
 * Copyright (c) 2000, 2017, Oracle and/or its affiliates. All rights reserved.
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

package javax.print.attribute;

/**
 * Interface {@code PrintJobAttributeSet} specifies the interface for a set of
 * print job attributes, i.e. printing attributes that implement interface
 * {@link PrintJobAttribute PrintJobAttribute}. In the Print Service API, a
 * service uses a {@code PrintJobAttributeSet} to report the status of a print
 * job.
 * <p>
 * A {@code PrintJobAttributeSet} is just an {@link AttributeSet AttributeSet}
 * whose constructors and mutating operations guarantee an additional invariant,
 * namely that all attribute values in the {@code PrintJobAttributeSet} must be
 * instances of interface {@link PrintJobAttribute PrintJobAttribute}. The
 * {@link #add(Attribute) add(Attribute)}, and
 * {@link #addAll(AttributeSet) addAll(AttributeSet)} operations are respecified
 * below to guarantee this additional invariant.
 *
 * @author Alan Kaminsky
 */
public interface PrintJobAttributeSet extends AttributeSet {

    /**
     * Adds the specified attribute value to this attribute set if it is not
     * already present, first removing any existing value in the same attribute
     * category as the specified attribute value (optional operation).
     *
     * @param  attribute attribute value to be added to this attribute set
     * @return {@code true} if this attribute set changed as a result of the
     *         call, i.e., the given attribute value was not already a member of
     *         this attribute set
     * @throws UnmodifiableSetException if this attribute set does not support
     *         the {@code add()} operation
     * @throws ClassCastException if the {@code attribute} is not an instance of
     *         interface {@link PrintJobAttribute PrintJobAttribute}
     * @throws NullPointerException if the {@code attribute} is {@code null}
     */
    public boolean add(Attribute attribute);

    /**
     * Adds all of the elements in the specified set to this attribute. The
     * outcome is the same as if the {@link #add(Attribute) add(Attribute)}
     * operation had been applied to this attribute set successively with each
     * element from the specified set. If none of the categories in the
     * specified set are the same as any categories in this attribute set, the
     * {@code addAll()} operation effectively modifies this attribute set so
     * that its value is the <i>union</i> of the two sets.
     * <p>
     * The behavior of the {@code addAll()} operation is unspecified if the
     * specified set is modified while the operation is in progress.
     * <p>
     * If the {@code addAll()} operation throws an exception, the effect on this
     * attribute set's state is implementation dependent; elements from the
     * specified set before the point of the exception may or may not have been
     * added to this attribute set.
     *
     * @param  attributes whose elements are to be added to this attribute set
     * @return {@code true} if this attribute set changed as a result of the
     *         call
     * @throws UnmodifiableSetException if this attribute set does not support
     *         the {@code addAll()} method
     * @throws ClassCastException if some element in the specified set is not an
     *         instance of interface {@link PrintJobAttribute PrintJobAttribute}
     * @throws NullPointerException if the specified set is {@code null}
     * @see #add(Attribute)
     */
    public boolean addAll(AttributeSet attributes);
}
