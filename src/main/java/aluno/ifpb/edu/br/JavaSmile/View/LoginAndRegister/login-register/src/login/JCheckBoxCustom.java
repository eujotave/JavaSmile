/*
 * Copyright 2024 Pedro Paulo Delgado Alves e João Victor Moura Barbosa
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * The software licensed under the Apache License, Version 2.0 is further
 * subject to the Commons Clause License Condition v1.0.
 */
package login;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class JCheckBoxCustom extends javax.swing.JCheckBox {
    private final int border = 4;

    public JCheckBoxCustom() {
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setOpaque(false);
        setBackground(new Color(69, 124, 235));
    }

    @Override
    public void paint(Graphics grphcs) {
        super.paint(grphcs);
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int ly = (getHeight() - 16) / 2;
        if (isSelected()) {
            if (isEnabled()) {
                g2.setColor(getBackground());
            } else {
                g2.setColor(Color.GRAY);
            }
            g2.fillRoundRect(1, ly, 16, 16, border, border);
            //  Draw Check icon
            int px[] = {4, 8, 14, 12, 8, 6};
            int py[] = {ly + 8, ly + 14, ly + 5, ly + 3, ly + 10, ly + 6};
            g2.setColor(Color.WHITE);
            g2.fillPolygon(px, py, px.length);
        } else {
            g2.setColor(Color.GRAY);
            g2.fillRoundRect(1, ly, 16, 16, border, border);
            g2.setColor(Color.WHITE);
            g2.fillRoundRect(2, ly + 1, 14, 14, border, border);
        }
        g2.dispose();
    }
}
