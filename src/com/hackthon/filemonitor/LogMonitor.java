package com.hackthon.filemonitor;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import com.hackthon.interfaces.FileChangeListener;



public class LogMonitor {
	
	public FileChangeListener mFileChangeListener;
	
	public static final String FOLDER =
            "C:/logs";
	 FileAlterationMonitor monitor;
	 public LogMonitor(FileChangeListener mFileListner) {
		// TODO Auto-generated constructor stub
		 mFileChangeListener = mFileListner;
	}

	public void monitorLogs() throws Exception {
	        // The monitor will perform polling on the folder every 5 seconds
	        final long pollingInterval = 5 * 1000;

	        File folder = new File(FOLDER);

	        if (!folder.exists()) {
	            // Test to see if monitored folder exists
	            throw new RuntimeException("Directory not found: " + FOLDER);
	        }

	        FileAlterationObserver observer = new FileAlterationObserver(folder);
	         monitor =
	                new FileAlterationMonitor(pollingInterval);
	        FileAlterationListener listener = new FileAlterationListenerAdaptor() {
	            // Is triggered when a file is created in the monitored folder
	            @Override
	            public void onFileCreate(File file) {
	                try {
	                    // "file" is the reference to the newly created file
	                    System.out.println("File created: "
	                            + file.getCanonicalPath());
	                } catch (IOException e) {
	                    e.printStackTrace(System.err);
	                }
	            }

	            // Is triggered when a file is deleted from the monitored folder
	            @Override
	            public void onFileDelete(File file) {
	                try {
	                    // "file" is the reference to the removed file
	                    System.out.println("File removed: "
	                            + file.getCanonicalPath());
	                    // "file" does not exists anymore in the location
	                    System.out.println("File still exists in location: "
	                            + file.exists());
	                } catch (IOException e) {
	                    e.printStackTrace(System.err);
	                }
	            }
	            
	            @Override
	            public void onFileChange(File file) {
	            	super.onFileChange(file);
	            	try {
						System.out.println("*********************" + "File change listener called");
	            		mFileChangeListener.onFileContentChange(file);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            }
	            
	            
	        };

	        observer.addListener(listener);
	        monitor.addObserver(observer);
	        monitor.start();
	    }
	 
	 public void stopMonitor() throws Exception{
		 monitor.stop();
	 }

}
