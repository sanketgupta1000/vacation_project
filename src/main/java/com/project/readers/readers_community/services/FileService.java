package com.project.readers.readers_community.services;

import com.cloudinary.utils.ObjectUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.cloudinary.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import  java.util.Map;


@Service
public class FileService
{

    private final Cloudinary cloudinary;

    public FileService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public String uploadFile(MultipartFile file, String folder, String public_id) throws IOException {
        Map options = public_id == null ?
                ObjectUtils.asMap(
                        "asset_folder", folder
                )
                :
                ObjectUtils.asMap(
                        "public_id", public_id,
                        "overwrite", true,
                        "asset_folder", folder
                );

        Map uploadResult =  cloudinary.uploader().upload(file.getBytes(), options);
        return uploadResult.get("secure_url").toString();
    }

    public boolean deleteFile(String public_id) throws IOException {
        Map deleteResult = cloudinary.uploader().destroy(public_id, ObjectUtils.emptyMap());
        String result = deleteResult.get("result").toString();
        if(!result.equals("ok"))
        {
            System.out.println("\n\n\nError while deleting file: " + public_id + "\nresult: " + result + "\n\n\n" );
            return false;
        }

        return true;
    }
}
