/*
 * Copyright (c) 2011, 2012, Oracle and/or its affiliates. All rights reserved.
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

//
//  AquaFocus.java
//  Copyright (c) 2009 Apple Inc. All rights reserved.
//

package com.apple.laf;

import java.awt.*;

import javax.swing.*;

import sun.java2d.*;
import apple.laf.JRSUIFocus;

import com.apple.laf.AquaUtils.Painter;

public class AquaFocus {
    interface Drawable {
        public void draw(final Graphics2D sg2d);
    }

    static boolean paintFocus(final Graphics g, final Drawable drawable) {
        // TODO: requires OSXSurfaceData
        return false;
        /*if (!(g instanceof SunGraphics2D)) return false;
        final SunGraphics2D sg2d = (SunGraphics2D)g;

        final SurfaceData surfaceData = sg2d.getSurfaceData();
        if (!(surfaceData instanceof OSXSurfaceData)) return false;

        try {
            ((OSXSurfaceData)surfaceData).performCocoaDrawing(sg2d, new OSXSurfaceData.CGContextDrawable() {
                @Override
                public void drawIntoCGContext(final long cgContext) {
                    final JRSUIFocus focus = new JRSUIFocus(cgContext);
                    focus.beginFocus(JRSUIFocus.RING_BELOW);
                    drawable.draw(sg2d);
                    focus.endFocus();
                }
            });
        } finally {
            sg2d.dispose();
        }
        return true;*/
    }

    public static Icon createFocusedIcon(final Icon tmpIcon, final Component c, final int slack) {
        return new FocusedIcon(tmpIcon, slack);
    }

/* -- disabled until we can get the real JRSUI focus painter working

    static class FocusedIcon implements Icon {
        final Icon icon;
        final int slack;

        public FocusedIcon(final Icon icon, final int slack) {
            this.icon = icon;
            this.slack = slack;
        }

        @Override
        public int getIconHeight() {
            return icon.getIconHeight() + slack + slack;
        }

        @Override
        public int getIconWidth() {
            return icon.getIconWidth() + slack + slack;
        }

        @Override
        public void paintIcon(final Component c, final Graphics g, final int x, final int y) {
            final boolean painted = paintFocus(g, new Drawable() {
                @Override
                public void draw(SunGraphics2D sg2d) {
                    icon.paintIcon(c, sg2d, x + slack, y + slack);
                }
            });
            if (!painted) {
                icon.paintIcon(c, g, x + slack, y + slack);
            }
        }
    }
 */

    static class FocusedIcon extends AquaUtils.ShadowBorder implements Icon {
        final Icon icon;
        final int slack;

        public FocusedIcon(final Icon icon, final int slack) {
            super(
                new Painter() {
                    public void paint(Graphics g, int x, int y, int w, int h) {
                        Graphics2D imgG = (Graphics2D)g;
                        imgG.setComposite(AlphaComposite.Src);
                        imgG.setColor(UIManager.getColor("Focus.color"));
                        imgG.fillRect(x, y, w - (slack * 2), h - (slack * 2));
                        imgG.setComposite(AlphaComposite.DstAtop);
                        icon.paintIcon(null, imgG, x, y);
                    }
                },
                new Painter() {
                    public void paint(Graphics g, int x, int y, int w, int h) {
                        ((Graphics2D)g).setComposite(AlphaComposite.SrcAtop);
                        icon.paintIcon(null, g, x, y);
                    }
                },
                slack, slack, 0.0f, 1.8f, 7
            );
            this.icon = icon;
            this.slack = slack;
        }

        @Override
        public int getIconHeight() {
            return icon.getIconHeight() + slack + slack;
        }

        @Override
        public int getIconWidth() {
            return icon.getIconWidth() + slack + slack;
        }

        @Override
        public void paintIcon(final Component c, final Graphics g, final int x, final int y) {
            paintBorder(c, g, x, y, getIconWidth(), getIconHeight());
            icon.paintIcon(c, g, x + slack, y + slack);
        }
    }
}
