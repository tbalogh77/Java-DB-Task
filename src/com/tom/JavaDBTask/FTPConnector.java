package com.tom.JavaDBTask;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
 
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

public class FTPConnector {

	private final String m_strServer; 
	private final String m_strUser; 
	private final String m_strPass;
	
	public FTPConnector(String strServer, String strUser, String strPass) {
		m_strServer = strServer; 
		m_strUser   = strUser; 
		m_strPass   = strPass;
	}
	
    public boolean upload(String strLocalFilePath, String strRemoteFilePath) {
    	boolean bSuccess = false;
        int port = 21;
 
        FTPClient ftpClient = new FTPClient();
        
        try {
 
            System.out.println("Connecting to FTP server " + m_strServer + " as user \"" + m_strUser + "\"");
            ftpClient.connect(m_strServer, port);
            ftpClient.login(m_strUser, m_strPass);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
 
            File firstLocalFile = new File(strLocalFilePath);
            InputStream inputStream = new FileInputStream(firstLocalFile);
            boolean done = ftpClient.storeFile(strRemoteFilePath, inputStream);
            inputStream.close();
            if (done) {
            	bSuccess = true;
                System.out.println(strLocalFilePath + " uploaded successfully to FTP server " + m_strServer);
            }
 
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return bSuccess;
    }
 	
	
}
