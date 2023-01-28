package com.loeaf.file.domain.listener;


import com.loeaf.file.domain.FileInfo;

import javax.persistence.PreRemove;

public class FileInfoListener {
    @PreRemove
    public void preRemove(FileInfo fileInfo) {
        fileInfo.delete();
    }
}
