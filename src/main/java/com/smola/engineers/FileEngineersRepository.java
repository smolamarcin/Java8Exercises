package com.smola.engineers;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

class FileEngineersRepository {
    private List<String[]> lines;

    public FileEngineersRepository(List<String[]> lines) {
        this.lines = lines;
    }

    Collection<String[]> getAllLines() {
        return Collections.unmodifiableCollection(lines);
    }

    public boolean save(String[] line) {
        return lines.add(line);
    }
}
