
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.JOptionPane;

public class MainForm extends javax.swing.JFrame {
    
    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
    Thread t;
    ServerThread serverThread;
    public Vector socketList = new Vector();
    public Vector clientList = new Vector();
    public Vector clientFileSharingUsername = new Vector();
    public Vector clientFileSharingSocket = new Vector();

    ServerSocket server;
    
    public MainForm() {
        initComponents();
    }
    
    
    public void appendMessage(String msg){
        Date date = new Date();
        jTextArea1.append(sdf.format(date) +": "+ msg +"\n");
        jTextArea1.setCaretPosition(jTextArea1.getText().length() - 1);
    }
    
    public void setSocketList(Socket socket){
        try {
            socketList.add(socket);
            appendMessage("[setSocketList]: Added");
        } catch (Exception e) { appendMessage("[setSocketList]: "+ e.getMessage()); }
    }
    public void setClientList(String client){
        try {
            clientList.add(client);
            appendMessage("[setClientList]: Added");
        } catch (Exception e) { appendMessage("[setClientList]: "+ e.getMessage()); }
    }
    public void setClientFileSharingUsername(String user){
        try {
            clientFileSharingUsername.add(user);
        } catch (Exception e) { }
    }
    
    public void setClientFileSharingSocket(Socket soc){
        try {
            clientFileSharingSocket.add(soc);
        } catch (Exception e) { }
    }

    public Socket getClientList(String client){
        Socket tsoc = null;
        for(int x=0; x < clientList.size(); x++){
            if(clientList.get(x).equals(client)){
                tsoc = (Socket) socketList.get(x);
                break;
            }
        }
        return tsoc;
    }
    
    
    public void removeFromTheList(String client){
        try {
            for(int x=0; x < clientList.size(); x++){
                if(clientList.elementAt(x).equals(client)){
                    clientList.removeElementAt(x);
                    socketList.removeElementAt(x);
                    appendMessage("[Removed]: "+ client);
                    break;
                }
            }
        } catch (Exception e) {
            appendMessage("[RemovedException]: "+ e.getMessage());
        }
    }
    
    public Socket getClientFileSharingSocket(String username){
        Socket tsoc = null;
        for(int x=0; x < clientFileSharingUsername.size(); x++){
            if(clientFileSharingUsername.elementAt(x).equals(username)){
                tsoc = (Socket) clientFileSharingSocket.elementAt(x);
                break;
            }
        }
        return tsoc;
    }
    

    public void removeClientFileSharing(String username){
        for(int x=0; x < clientFileSharingUsername.size(); x++){
            if(clientFileSharingUsername.elementAt(x).equals(username)){
                try {
                    Socket rSock = getClientFileSharingSocket(username);
                    if(rSock != null){
                        rSock.close();
                    }
                    clientFileSharingUsername.removeElementAt(x);
                    clientFileSharingSocket.removeElementAt(x);
                    appendMessage("[FileSharing]: Removed "+ username);
                } catch (IOException e) {
                    appendMessage("[FileSharing]: "+ e.getMessage());
                    appendMessage("[FileSharing]: Unable to Remove "+ username);
                }
                break;
            }
        }
    }
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Server");
        setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setText("Port:");

        jTextField1.setText("4444");

        jButton1.setText("Start Server");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Stop Server");
        jButton2.setEnabled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new java.awt.Color(0, 0, 0));
        jTextArea1.setColumns(20);
        jTextArea1.setForeground(new java.awt.Color(0, 255, 255));
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 108, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        int port = Integer.parseInt(jTextField1.getText());
        serverThread = new ServerThread(port, this);
        t = new Thread(serverThread);
        t.start();
        
        new Thread(new OnlineListThread(this)).start();
        
        jButton1.setEnabled(false);
        jButton2.setEnabled(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int confirm = JOptionPane.showConfirmDialog(null, "Close Server.?");
        if(confirm == 0){
            serverThread.stop();
        }
    }//GEN-LAST:event_jButton2ActionPerformed


    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables


}
