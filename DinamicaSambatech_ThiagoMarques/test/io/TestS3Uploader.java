package io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
            fail();
        }

        if (file != null) {
            try {
                assertNotNull(s3.upload("test.txt", new FileInputStream(file)));
                file.delete();
            } catch (FileNotFoundException ex) {
                fail();
            }
            
        } else {
            fail();
        }
    }
}
