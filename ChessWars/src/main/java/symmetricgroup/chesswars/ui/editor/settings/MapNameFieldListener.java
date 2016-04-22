/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.ui.editor.settings;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import symmetricgroup.chesswars.ui.editor.EditorPanel;
import symmetricgroup.chesswars.ui.editor.EditorRoom;
import symmetricgroup.chesswars.ui.map.MapAndNamePanel;

/**
 *
 * @author Simo
 */
public class MapNameFieldListener implements DocumentListener {
    EditorPanel panel;

    public MapNameFieldListener(EditorPanel panel) {
        this.panel = panel;
    }
    

    @Override
    public void insertUpdate(DocumentEvent e) {
        setName(e);
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        setName(e);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        setName(e);
    }
    
    public void setName(DocumentEvent e) {
        Document name = e.getDocument();
        EditorRoom editor = panel.getEditorRoom();
        MapAndNamePanel mapAndName = editor.getMapAndName();
        
        try {
            mapAndName.setMapName(name.getText(0, Math.min(name.getLength(), 17)));
        } catch (BadLocationException ex) {
        }
        
    }
}
