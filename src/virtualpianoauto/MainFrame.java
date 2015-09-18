/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualpianoauto;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinUser.WNDENUMPROC;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import virtualpianoauto.midi.Event;
import virtualpianoauto.midi.Song;
import virtualpianoauto.midi.Track;

/**
 *
 * @author Rey
 */
public class MainFrame extends javax.swing.JFrame {

    private Song mSong;
    private List<Window> mWindows = new ArrayList<>();
    private SongPlayer mPlayer = new SongPlayer();
    private Event[] mEvents;
    
    
//    public interface User32 extends StdCallLibrary {
//        User32 INSTANCE = (User32) Native.loadLibrary("user32", User32.class);
//        HWND GetForegroundWindow();  // add this
//        int GetWindowTextA(PointerType hWnd, byte[] lpString, int nMaxCount);
//    }
    
    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
        
        ltAvailable.setModel(new DefaultListModel());
        ltSelect.setModel(new DefaultListModel());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btOpen = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btPlay = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbEvent = new javax.swing.JTable();
        cbWindow = new javax.swing.JComboBox();
        btRefresh = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        tf_milis = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        ltAvailable = new javax.swing.JList();
        jLabel3 = new javax.swing.JLabel();
        btRemove = new javax.swing.JButton();
        btAdd = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        ltSelect = new javax.swing.JList();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("VirtualPiano Auto");

        btOpen.setText("Open File");
        btOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btOpenActionPerformed(evt);
            }
        });

        jLabel1.setText("Events:");

        btPlay.setText("Start");
        btPlay.setEnabled(false);
        btPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPlayActionPerformed(evt);
            }
        });

        tbEvent.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Key", "Tick", "Down"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Long.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbEvent.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tbEvent);

        btRefresh.setText("Refresh");
        btRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRefreshActionPerformed(evt);
            }
        });

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("MilisPerTick");

        tf_milis.setEnabled(false);

        jScrollPane2.setViewportView(ltAvailable);

        jLabel3.setText("Available");

        btRemove.setText("-");
        btRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRemoveActionPerformed(evt);
            }
        });

        btAdd.setText("+");
        btAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAddActionPerformed(evt);
            }
        });

        jLabel4.setText("Select");

        jScrollPane3.setViewportView(ltSelect);

        jLabel5.setText("Apps");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(144, 144, 144)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(0, 105, Short.MAX_VALUE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btOpen, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btRefresh))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tf_milis, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbWindow, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel2))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tf_milis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbWindow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btPlay)
                            .addComponent(btRefresh)
                            .addComponent(btOpen)))
                    .addComponent(btAdd)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(btRemove)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btOpenActionPerformed
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "MIDI file", "mid", "midi");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            parseFile(chooser.getSelectedFile());      
            if(mWindows.isEmpty())
                btRefreshActionPerformed(null);
        }
    }//GEN-LAST:event_btOpenActionPerformed

    private void btPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPlayActionPerformed
        if(mPlayer.isPlaying()){
            mPlayer.stop();
            btPlay.setText("Start");
            tf_milis.setEnabled(true);
            btOpen.setEnabled(true);
            ltAvailable.setEnabled(true);
            ltSelect.setEnabled(true);
        }
        else{
            Window window = mWindows.get(cbWindow.getSelectedIndex());
            User32.INSTANCE.SetForegroundWindow(window.hWnd);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            System.out.println("Started!");

            try {
                mPlayer.play(mEvents, getMilisPerTick(), new Runnable() {

                    @Override
                    public void run() {
                        mPlayer.stop();
                        btPlay.setText("Start");
                        tf_milis.setEnabled(true);
                        btOpen.setEnabled(true);
                        ltAvailable.setEnabled(true);
                        ltSelect.setEnabled(true);
                    }
                    
                });
                
                btPlay.setText("Stop");
                tf_milis.setEnabled(false);
                btOpen.setEnabled(false);
                ltAvailable.setEnabled(false);
                ltSelect.setEnabled(false);
            
            } catch (Exception ex) {
                System.out.println("error" + ex);
                ex.printStackTrace();
            }
        }  
    }//GEN-LAST:event_btPlayActionPerformed

    private void btRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRefreshActionPerformed
        mWindows.clear();
        final DefaultComboBoxModel model = (DefaultComboBoxModel)cbWindow.getModel();
        model.removeAllElements();
        
        User32.INSTANCE.EnumWindows(new WNDENUMPROC() {
            int count = 0;
            @Override
            public boolean callback(HWND hWnd, Pointer arg1) {
                char[] windowText = new char[512];
                User32.INSTANCE.GetWindowText(hWnd, windowText, 512);
                String wText = Native.toString(windowText);

                // get rid of this if block if you want all windows regardless of whether
                // or not they have text
                if (wText.isEmpty()) {
                   return true;
                }
                
                Window window = new Window(hWnd, wText);
                mWindows.add(window);
                model.addElement(wText);
                
                return true;
            }
        }, null);
    }//GEN-LAST:event_btRefreshActionPerformed

    private void btRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRemoveActionPerformed
        int[] indexes = ltSelect.getSelectedIndices();
        if(indexes == null || indexes.length == 0)
            return;
        
        DefaultListModel modelAvail = (DefaultListModel)ltAvailable.getModel();
        DefaultListModel modelSelect = (DefaultListModel)ltSelect.getModel();
        
        for(int index : indexes){
            Track track = (Track)modelSelect.getElementAt(index);
            modelAvail.addElement(track);
        }
        
        for(int i = indexes.length - 1; i >= 0; i--)
            modelSelect.remove(indexes[i]);
        
        onTrackChanged();
    }//GEN-LAST:event_btRemoveActionPerformed

    private void btAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAddActionPerformed
        int[] indexes = ltAvailable.getSelectedIndices();
        if(indexes == null || indexes.length == 0)
            return;
        
        DefaultListModel modelAvail = (DefaultListModel)ltAvailable.getModel();
        DefaultListModel modelSelect = (DefaultListModel)ltSelect.getModel();
        for(int index : indexes){
            Track track = (Track)modelAvail.getElementAt(index);
            modelSelect.addElement(track);
        }
        
        for(int i = indexes.length - 1; i >= 0; i--)
            modelAvail.remove(indexes[i]);
        
        onTrackChanged();
    }//GEN-LAST:event_btAddActionPerformed

    private void parseFile(File file){
        try {
            mSong = MidiParser.parse(file);            
            populateTracks(mSong);
            tf_milis.setText(String.valueOf(mSong.miliPerTick));
            tf_milis.setEnabled(true);
            btPlay.setEnabled(true);
            btPlay.setText("Start");
            
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
            ex.printStackTrace();
        }
    }
    
    private void populateTracks(Song song){
        DefaultListModel modelAvail = (DefaultListModel)ltAvailable.getModel();
        modelAvail.removeAllElements();
        for(int i = 0; i < song.events.length; i++)
            modelAvail.addElement(new Track(i));
        
        DefaultListModel modelSelect = (DefaultListModel)ltSelect.getModel();
        modelSelect.removeAllElements();
        
        ltAvailable.setEnabled(true);
        ltSelect.setEnabled(true);
                
        onTrackChanged();
    }
    
    private void onTrackChanged(){
        DefaultListModel modelSelect = (DefaultListModel)ltSelect.getModel();
        if(modelSelect.getSize() == 0)
            populateEvents(null);
        
        List<Event> list = new ArrayList<>();
        for(int i = 0, size = modelSelect.getSize(); i < size; i++){
            int index = ((Track)modelSelect.getElementAt(i)).index;
            
            for(Event event : mSong.events[index])
                list.add(event);
        }
        
        Collections.sort(list, new Comparator<Event>() {

            @Override
            public int compare(Event e1, Event e2) {
                if(e1.tick < e2.tick)
                    return -1;
                else if(e1.tick > e2.tick)
                    return 1;
                else
                    return 0;
            }
            
        });
        
        populateEvents(list.toArray(new Event[list.size()]));
    }
    
    private void populateEvents(Event[] events){        
        mEvents = events;
        DefaultTableModel model = (DefaultTableModel) tbEvent.getModel();
        
        //clear old data
        for (int i = model.getRowCount() - 1; i >= 0; i--) 
            model.removeRow(i);
        
        if(events != null)
            for(Event event : events)
                model.addRow(new Object[]{event.key.character, event.tick, event.down});    
    }
    
    private long getMilisPerTick(){
        try{
            return Long.parseLong(tf_milis.getText());
        }
        catch(Exception ex){
            tf_milis.setText(String.valueOf(mSong.miliPerTick));
            return mSong.miliPerTick;
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            javax.swing.UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAdd;
    private javax.swing.JButton btOpen;
    private javax.swing.JButton btPlay;
    private javax.swing.JButton btRefresh;
    private javax.swing.JButton btRemove;
    private javax.swing.JComboBox cbWindow;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList ltAvailable;
    private javax.swing.JList ltSelect;
    private javax.swing.JTable tbEvent;
    private javax.swing.JTextField tf_milis;
    // End of variables declaration//GEN-END:variables
}
