/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import java.io.InputStream;

/**
 *
 * @author Thiago
 */
public interface Uploader {
    public String upload(String fileName, InputStream file);
    public String getStatus();
    public String getProgress();
    public boolean isDone();
    
}
