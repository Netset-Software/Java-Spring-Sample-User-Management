package com.efx.user.services.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.efx.user.services.IMultipartFileServices;

@Service
public class MultipartServices implements IMultipartFileServices{

	@Override
	public String saveUserImage(MultipartFile file, UUID id) {
		try {
			String dir = "EfxUserManagement/resources/images/users";
			String path = "images/users";
			
			if (!new File(dir).exists()) {
				new File(dir).mkdirs();
			}

			String fileName = file.getOriginalFilename();
			
			String ext = FilenameUtils.getExtension(fileName);
			fileName = "users_"+id+"."+ext;
			
			InputStream fileContent = file.getInputStream();
			OutputStream outputStream = new FileOutputStream(new File(dir+"/"+fileName));
			int read = 0;
			byte[] bytes = new byte[1024];
			while ((read = fileContent.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
			String n = path+"/"+fileName;
			outputStream.close();
			fileContent.close();
			return n;
		} catch (Exception e) {
			return null;
		}
	}

}
