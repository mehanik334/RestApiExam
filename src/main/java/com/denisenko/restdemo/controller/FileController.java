package com.denisenko.restdemo.controller;

import com.denisenko.restdemo.model.File;
import com.denisenko.restdemo.service.FileService;

import java.util.List;

public class FileController {

    private FileService fileService;

    public FileController() {
        fileService = new FileService();
    }

    public File getByIdFile(Integer id) {
        return fileService.getById(id);
    }

    public File saveFile(File file) {
        return fileService.save(file);
    }

    public boolean deleteFileById(Integer id) {
        return fileService.delete(id);
    }

    public List<File> getAllFiles() {
        return fileService.getAll();
    }

    public File updateFile(File file) {
        return fileService.update(file);
    }
}
