package com.example.kttkpm_8_3.service.reader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReaderService {

    @Autowired
    private ReaderRepository readerRepository;

    public List<ReaderEntity> getAllReaders() {
        return readerRepository.findAll();
    }

    public Optional<ReaderEntity> getReaderById(Long id) {
        return readerRepository.findById(id);
    }

    public ReaderEntity createReader(ReaderEntity reader) {
        return readerRepository.save(reader);
    }

    public ReaderEntity updateReader(Long id, ReaderEntity updatedReader) {
        Optional<ReaderEntity> existingReader = readerRepository.findById(id);
        if (existingReader.isPresent()) {
            updatedReader.setId(existingReader.get().getId());
            return readerRepository.save(updatedReader);
        }
        return null;
    }

    public void deleteReader(Long id) {
        readerRepository.deleteById(id);
    }
}
