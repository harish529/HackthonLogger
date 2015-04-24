package com.hackthon.interfaces;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public interface FileChangeListener
{
	
	Map<String, Long> fileSizeMap = new HashMap<String, Long>();
	public void onFileContentChange(File mFile) throws IOException;
}