package io;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.Upload;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class S3Uploader implements Uploader {

    private AWSCredentials credentials;
    private Upload upload;
    private String s3Bucket;

    public S3Uploader() {
        try {
            credentials = new DefaultAWSCredentialsProviderChain().getCredentials();
            s3Bucket = "dinamica-sambatech-thiago";
        } catch (Exception e) {
            throw new AmazonClientException(
                    "Cannot load the credentials from the credential profiles file. "
                    + "Please make sure that your credentials file is at the correct "
                    + "location (~/.aws/credentials), and is in valid format.",
                    e);
        }
    }

    @Override
    public String upload(String fileName, InputStream file) {
        upload = null;
        TransferManager tx = null;

        try {
            tx = new TransferManager(credentials);
            upload = tx.upload(s3Bucket, fileName, file, null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        try {
            upload.waitForCompletion();
            tx.shutdownNow();
            return "http://" + s3Bucket + ".s3.amazonaws.com/" + fileName;
        } catch (AmazonClientException ex) {
            Logger.getLogger(S3Uploader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(S3Uploader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public String getStatus() {
        return upload.getState().name();
    }

    @Override
    public String getProgress() {
        return String.valueOf(upload.getProgress().getBytesTransferred());
    }

    @Override
    public boolean isDone() {
        return upload.isDone();
    }
}
