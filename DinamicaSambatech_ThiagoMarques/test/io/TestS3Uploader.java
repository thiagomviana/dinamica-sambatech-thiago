package io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestS3Uploader {

    public TestS3Uploader() {
    }

    @Test
    public void testConstructor() {
        S3Uploader s3 = new S3Uploader();

        assertNotNull(s3);
    }

    @Test
    public void testUpload() {
        S3Uploader s3 = new S3Uploader();
        String text = "Hello world";
        BufferedWriter output = null;
        File file = null;

        try {
            file = new File("test.txt");
            output = new BufferedWriter(new FileWriter(file));
            output.write(text);
            output.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (file != null) {
            //assertNotNull(s3.upload(file.));
            file.delete();
        } else {
            fail();
        }
    }
}
