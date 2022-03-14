package com.denisenko.restdemo.service;

import com.denisenko.restdemo.model.File;
import com.denisenko.restdemo.repository.FileRepository;
import com.denisenko.restdemo.repository.hibernateImpl.HibernateFileRepository;

import java.util.List;

public class FileService {

    private FileRepository fileRepository = new HibernateFileRepository();

    public FileRepository getFileRepository() {
        return fileRepository;
    }

    public void setFileRepository(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public File getById(Integer id) {
        return fileRepository.getById(id);
    }

    public File save(File file) {
        return fileRepository.save(file);
    }

    public boolean delete(Integer id) {
        return fileRepository.deleteById(id);
    }

    public List<File> getAll() {
        return fileRepository.getAll();
    }

    public File update(File file) {
        return fileRepository.update(file);
    }
}
